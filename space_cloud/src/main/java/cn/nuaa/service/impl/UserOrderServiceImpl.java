package cn.nuaa.service.impl;

import cn.nuaa.common.constant.BalanceConstant;
import cn.nuaa.common.constant.IpAddressStatusConstant;
import cn.nuaa.common.constant.OrderStatusConstant;
import cn.nuaa.common.exception.UserOrderExistException;
import cn.nuaa.common.utils.StepOrSubMonthUtil;
import cn.nuaa.dao.IpAddressDao;
import cn.nuaa.dao.ProductRoomDao;
import cn.nuaa.dao.UserDao;
import cn.nuaa.entity.*;
import cn.nuaa.dao.UserOrderDao;
import cn.nuaa.service.*;
import cn.nuaa.vo.IpAddressVo;
import cn.nuaa.vo.UserOrderVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * (UserOrder)表服务实现类
 *
 * @author wpc
 * @since 2020-03-28 19:43:12
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class UserOrderServiceImpl implements UserOrderService {
    @Autowired
    private UserOrderDao userOrderDao;
    @Autowired
    private ProductRoomDao productRoomDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private IpAddressDao ipAddressDao;
    @Autowired
    private UserService userService;
    @Autowired
    private UserBusinessService userBusinessService;
    @Autowired
    private IpAddressService ipAddressService;
    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private ProductService productService;

    @Override
    public List<UserOrder> findAll(UserOrderVo userOrderVo) {
        String productTypeName = userOrderVo.getProductTypeName();
        String userAccount = userOrderVo.getUserAccount();
        List<UserOrder> userOrders = userOrderDao.queryAll(productTypeName, userAccount);
        for (UserOrder userOrder : userOrders) {
            //用BigDecimal解决double相乘问题
            BigDecimal orderPrice = BigDecimal.valueOf(userOrder.getOrderPrice());
            BigDecimal orderTerm = BigDecimal.valueOf(userOrder.getOrderTerm());
            userOrder.setOrderPrice(orderPrice.multiply(orderTerm).doubleValue());
        }
        return userOrders;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserOrderVo queryById(Long id) {
        UserOrder userOrder = this.userOrderDao.queryById(id);
        UserOrderVo userOrderVo = null;
        if (userOrder != null) {
            userOrderVo = new UserOrderVo();
            //用工具把entity复制到vo中，少写代码
            BeanUtils.copyProperties(userOrder, userOrderVo);
            //根据productType的id来查机房
            ProductRoom productRoom = productRoomDao.selectByProductTypeId(userOrder.getProductType().getId());
            userOrderVo.setProductRoom(productRoom);
        }
        return userOrderVo;
    }

    /**
     * 新增数据
     *
     * @param userOrderVo 实例对象
     * @return 实例对象
     */
    @Override
    public UserOrder add(UserOrderVo userOrderVo) throws UserOrderExistException {
        if (userOrderVo.getProductTypeId()==null||userOrderVo.getProductSystemId()==null||userOrderVo.getOrderPrice()==null||userOrderVo.getOrderTerm()==null||userOrderVo.getUserAccount()==null||"".equals(userOrderVo.getUserAccount())){
            throw new UserOrderExistException("有不能为空的参数出现了空");
        }
        UserOrder userOrder = new UserOrder();
        //用复制工具将vo复制给entity
        BeanUtils.copyProperties(userOrderVo, userOrder);
        //以下是一些特殊的
        if (userOrderVo.getRemark()==null||"".equals(userOrderVo.getRemark())){
            userOrder.setRemark("消费");
        }
        //提交订单,这个时候的状态为未付款
        userOrder.setOrderStatus(OrderStatusConstant.ORDER_STATUS_FAIL);
        //订单创建时间
        userOrder.setCreateDate(new Date());
        this.userOrderDao.insert(userOrder);
        return userOrder;
    }

    /**
     * 修改数据
     *
     * @param userOrder 实例对象
     * @return 实例对象
     */
    @Override
    public UserOrderVo update(UserOrder userOrder) {
        this.userOrderDao.updateById(userOrder);
        return this.queryById(userOrder.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) throws UserOrderExistException {
        UserOrder userOrder = this.userOrderDao.queryById(id);
        if (userOrder == null) {
            throw new UserOrderExistException("没有找到订单，请重试");
        }
        if (userOrder.getOrderStatus() == OrderStatusConstant.ORDER_STATUS_SUCCESS) {
            throw new UserOrderExistException("删除失败");
        }
        return this.userOrderDao.deleteById(id) > 0;
    }

    @Override
    public List<UserOrder> findOrderStatusByUserAccount(String userAccount) {
        List<UserOrder> userOrders = userOrderDao.queryAll(null, userAccount);
        return userOrders;
    }

    @Override
    public void pay(String userAccount, Long orderId) throws UserOrderExistException {
        //状态码，用作判断的
        int code = -1;
        //1.根据orderId查询出订单的信息
        UserOrder userOrder = this.userOrderDao.queryById(orderId);
        //引入BigDecimal解决double相乘问题
        BigDecimal orderPrice = BigDecimal.valueOf(userOrder.getOrderPrice());
        BigDecimal orderTerm = BigDecimal.valueOf(userOrder.getOrderTerm());
        //单价和月份相乘,得到总价
        double priceNum = orderPrice.multiply(orderTerm).doubleValue();
        //2.查余额,0表时资金充足
        int checkBalanceCode = userService.checkBalance(userAccount, priceNum);
        //创建一个时间，都用这个
        Date date = new Date();
        if (checkBalanceCode == BalanceConstant.BALANCE_ENOUGH) {
            //调用新增交易记录方法
            this.AddPayBusinessRecord(userAccount,priceNum,date,userOrder);
            //调用更新用户的资金
            this.updateUserBalance(userAccount,priceNum,date);
            //调用订单状态
            this.updateUserOrderStatus(orderId,date);
            //调用增加用户的产品,钱付过了，订单也完成了，得把用户的ip给人家
            try {
                this.AddUserProduct(userAccount,userOrder,date);
            } catch (UserOrderExistException e) {
                e.printStackTrace();
                throw new UserOrderExistException(e.getMessage());
            }
        }
    }

    /**
     * 添加交易记录的函数，pay()调用,消费用
     * @param userAccount
     * @param priceNum
     * @param date
     * @param userOrder
     */
    public void AddPayBusinessRecord(String userAccount, Double priceNum, Date date, UserOrder userOrder) {
        if (ObjectUtils.isEmpty(date)) {
            date = new Date();
        }
        UserBusiness userBusiness = new UserBusiness();
        //set充值的账户
        userBusiness.setOrderId(userOrder.getId());
        userBusiness.setProductTypeId(userOrder.getProductType().getId());
        userBusiness.setBuyType("消费");
        userBusiness.setBuyMoney(priceNum);
        userBusiness.setBuyDate(date);
        userBusiness.setRemark(userOrder.getRemark());
        userBusiness.setUserAccount(userAccount);
        //调用userBusinessService.add增加交易记录
        userBusinessService.add(userBusiness);
    }

    /**
     * 更新用户的余额和消费资金等，pay()调用,消费用
     * @param userAccount
     * @param priceNum
     * @param date
     */
    public void updateUserBalance(String userAccount,Double priceNum,Date date){
        if (ObjectUtils.isEmpty(date)) {
            date = new Date();
        }
        //先根据账号名查信息
        User userByUserAccount = userDao.selectByUserAccount(userAccount);
        BigDecimal priceNumBigAfter = BigDecimal.valueOf(priceNum);
        BigDecimal balance = BigDecimal.valueOf(userByUserAccount.getBalance());
        BigDecimal allPay = BigDecimal.valueOf(userByUserAccount.getAllPay());
        User user = new User();
        //用BigDecimal提供精确减法运算的subtract方法,余额减法运算
        user.setBalance(balance.subtract(priceNumBigAfter).doubleValue());
        //用BigDecimal提供精确减法运算的add方法,总消费加法运算
        user.setAllPay(allPay.add(priceNumBigAfter).doubleValue());
        user.setEndpayDate(date);
        user.setUserAccount(userAccount);
        userDao.updateBalanceByUserAccount(user);
    }

    /**
     * 更新订单的状态和消费时间
     * @param orderId
     * @param date
     */
    public void updateUserOrderStatus(Long orderId,Date date){
        if(ObjectUtils.isEmpty(date)){
            date=new Date();
        }
        UserOrder userOrder = new UserOrder();
        userOrder.setId(orderId);
        userOrder.setOrderStatus(OrderStatusConstant.ORDER_STATUS_SUCCESS);
        userOrder.setOrderDate(date);
        this.update(userOrder);
    }

    /**
     * 增加用户的产品,钱付过了，订单也完成了，得把用户的ip给人家，pay()调用
     * @param userAccount
     * @param userOrder
     */
    public void AddUserProduct(String userAccount,UserOrder userOrder,Date date) throws UserOrderExistException {
        if(ObjectUtils.isEmpty(date)){
            date=new Date();
        }
        IpAddressVo ipAddressVo = new IpAddressVo();
        //查找开启且无人使用的ip地址
        ipAddressVo.setIpStatus(IpAddressStatusConstant.IPADDRESS_NO_STATUS);
        List<IpAddress> ipAddresses = ipAddressService.findAll(ipAddressVo);
        if (ipAddresses==null||ipAddresses.size()<=0){
            throw new UserOrderExistException("暂时没有可用的ip地址分配，请联系管理员");
        }
        //获取集合里第一个无人使用的ip地址
        IpAddress ipAddressFirst = ipAddresses.get(0);
        //并把这个ip地址的状态改成1有人使用
        IpAddress ipAddress = new IpAddress();
        ipAddress.setId(ipAddressFirst.getId());
        ipAddress.setIpStatus(IpAddressStatusConstant.IPADDRESS_ON_STATUS);
        ipAddressDao.updateById(ipAddress);

        //根据productTypeId查sort_id
        ProductType productType = productTypeService.findById(userOrder.getProductType().getId());

        Product product = new Product();
        //将这个ip地址的id给product
        product.setIpAddressId(ipAddressFirst.getId());
        product.setOrderId(userOrder.getId());
        product.setProductStatus(1);
        product.setStartDate(date);
        //增加月份
        product.setEndDate(StepOrSubMonthUtil.stepMonth(date,userOrder.getOrderTerm()));
        //将查到的sort_id给product
        product.setSortId(productType.getSort().getId());
        product.setUserAccount(userAccount);
        product.setCreateDate(date);
        productService.add(product);
    }
}