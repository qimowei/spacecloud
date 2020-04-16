package cn.nuaa.service.impl;

import cn.nuaa.common.constant.BalanceConstant;
import cn.nuaa.common.converter.Address2StringConverter;
import cn.nuaa.common.converter.String2AddressConverter;
import cn.nuaa.common.exception.UserExistException;
import cn.nuaa.common.utils.IdentityUtil;
import cn.nuaa.dao.UserBusinessDao;
import cn.nuaa.dao.UserDao;
import cn.nuaa.entity.User;
import cn.nuaa.entity.UserBusiness;
import cn.nuaa.service.UserService;
import cn.nuaa.service.UserService;
import cn.nuaa.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author: wpc
 * @Date: 2020/3/5 17:59
 * @Description: <描述>
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserBusinessDao userBusinessDao;

    @Override
    public UserVo findByUserAccount(String userAccount) throws UserExistException {
        User user = userDao.selectByUserAccount(userAccount);
        if (user != null) {
            UserVo userVo = new UserVo();
            userVo.setId(user.getId());
            userVo.setEmail(user.getEmail());
            userVo.setName(user.getName());
            userVo.setIdentity(user.getIdentity());
            userVo.setQqNum(user.getQqNum());
            userVo.setPhone(user.getPhone());
            userVo.setAddress(user.getAddress());
            userVo.setSex(user.getSex());
            userVo.setSignature(user.getSignature());
            if(!ObjectUtils.isEmpty(user.getCity())){
                UserVo convert = new String2AddressConverter().convert(user.getCity());
                userVo.setProvince(convert.getProvince());
                userVo.setCity(convert.getCity());
                userVo.setCounty(convert.getCounty());
            }
            userVo.setBalance(user.getBalance());
            userVo.setAllPay(user.getAllPay());
            userVo.setEndpayDate(user.getEndpayDate());
            userVo.setCreateDate(user.getCreateDate());
            return userVo;
        }
        throw new UserExistException("未查到相应信息,请联系管理员");
    }

    @Override
    public void modify(UserVo userVo) throws UserExistException {
        if (null == userVo.getName() || "".equals(userVo.getName())) {
            throw new UserExistException("昵称不能为空");
        }
        if (null == userVo.getIdentity() || "".equals(userVo.getIdentity())) {
            throw new UserExistException("身份证不能为空");
        }
        if (null == userVo.getQqNum() || "".equals(userVo.getQqNum())) {
            throw new UserExistException("QQ不能为空");
        }
        if (null == userVo.getPhone() || "".equals(userVo.getPhone())) {
            throw new UserExistException("手机号码不能为空");
        }
        if ("".equals(userVo.getProvince()) || "".equals(userVo.getCity())) {
            throw new UserExistException("所在地区不能为空");
        }
        if (null == userVo.getId() || "".equals(userVo.getId())) {
            throw new UserExistException("修改信息必须携带id，请重试");
        }
        User user = new User();
        user.setId(userVo.getId());
        user.setName(userVo.getName());
        user.setIdentity(userVo.getIdentity());
        user.setQqNum(userVo.getQqNum());
        user.setPhone(userVo.getPhone());
        //将地址转换成字符串存入
        user.setCity(new Address2StringConverter().convert(userVo));
        user.setAddress(userVo.getAddress());
        user.setSex(userVo.getSex());
        user.setSignature(userVo.getSignature());
        userDao.updateById(user);
    }

    @Override
    public void modifyPassword(UserVo userVo) throws UserExistException {
        if (null == userVo.getNew_password() || "".equals(userVo.getNew_password())) {
            throw new UserExistException("密码不能为空");
        }
        if (null == userVo.getUserAccount() || "".equals(userVo.getUserAccount())) {
            throw new UserExistException("修改信息必须携带登录名，请重试");
        }
        User selectByUserAccount = userDao.selectByUserAccount(userVo.getUserAccount());
        if (!userVo.getOld_password().equals(selectByUserAccount.getPassword())) {
            throw new UserExistException("原密码错误,请重试或者找回密码");
        }
        User user = new User();
        user.setUserAccount(userVo.getUserAccount());
        user.setPassword(userVo.getNew_password());
        userDao.updateById(user);
    }

    @Override
    public void modifyEmail(UserVo userVo) throws UserExistException {
        if (null == userVo.getEmail() || "".equals(userVo.getEmail())) {
            throw new UserExistException("邮箱不能为空");
        }
        if (null == userVo.getId() || "".equals(userVo.getId())) {
            throw new UserExistException("修改信息必须携带id，请重试");
        }
        User user = new User();
        user.setId(userVo.getId());
        user.setEmail(userVo.getEmail());
        userDao.updateById(user);
    }

    @Override
    public void recharge(String userAccount, Double rechargeNum) throws UserExistException {
        if (null == userAccount || "".equals(userAccount)) {
            throw new UserExistException("登录名不能为空");
        }
        if (null == rechargeNum || "".equals(rechargeNum)) {
            throw new UserExistException("充值金额不能为空");
        }
        User selectByUserAccount = userDao.selectByUserAccount(userAccount);
        if (selectByUserAccount == null) {
            throw new UserExistException("错误!没有此用户，请联系管理员!");
        }
        //先查询账户内又多少余额
        Double balance = selectByUserAccount.getBalance();
        //余额和充值金额相加
        double balanceAfter = balance + rechargeNum;
        User user = new User();
        Date date = new Date();
        user.setUserAccount(userAccount);
        user.setBalance(balanceAfter);
        user.setEndpayDate(date);
        userDao.updateBalanceByUserAccount(user);
        //调用添加交易记录的函数,添加充值记录
        this.AddRechargeBusinessRecord(userAccount, rechargeNum, date);
    }

    @Override
    public int checkBalance(String userAccount, Double productPrice) {
        int code = 123;
        User user = userDao.selectByUserAccount(userAccount);
        if (user!=null) {
            //获取账户余额
            Double balance = user.getBalance();
            //用compareTo的方法比较大小，如果前者大于后者，就大于0，反之就小于0，相等时等于0
            if (balance.compareTo(productPrice) >= 0) {
                code = BalanceConstant.BALANCE_ENOUGH;
            } else {
                code = BalanceConstant.BALANCE_NOT_ENOUGH;
            }
        }
        return code;
    }

    @Override
    public void add(UserVo userVo) throws UserExistException {
        if (userVo.getUserAccount()==null||"".equals(userVo.getUserAccount())){
            throw new UserExistException("会员名不能为空");
        }
        if (userVo.getPassword()==null||"".equals(userVo.getPassword())){
            throw new UserExistException("密码不能为空");
        }
        if (userVo.getPhone()==null||"".equals(userVo.getPhone())){
            throw new UserExistException("手机号不能为空");
        }
        User selectByUserAccount = userDao.selectByUserAccount(userVo.getUserAccount());
        if (selectByUserAccount!=null){
            throw new UserExistException("会员名已被注册，请更换其他用户名");
        }
        User user = new User();
        BeanUtils.copyProperties(userVo,user);
        user.setCreateDate(new Date());
        user.setBalance(0.00);
        user.setAllPay(0.00);
        userDao.insert(user);
    }

    @Override
    public UserVo login(UserVo userVo) throws UserExistException {
        User selectByUserAccount = userDao.selectByUserAccount(userVo.getUserAccount());
        if (selectByUserAccount==null){
            throw new UserExistException("用户不存在，请重试或注册");
        }
        if (!selectByUserAccount.getPassword().equals(userVo.getPassword())){
            throw new UserExistException("密码错误,请重试");
        }
        UserVo userVoAfter = new UserVo();
        userVoAfter.setUserAccount(selectByUserAccount.getUserAccount());
        userVoAfter.setPhone(selectByUserAccount.getPhone());
        return userVoAfter;
    }

    /**
     * 添加交易记录的函数，供recharge()调用
     *
     * @param userAccount
     * @param rechargeNum
     * @param date
     */
    public void AddRechargeBusinessRecord(String userAccount, Double rechargeNum, Date date) {
        UserBusiness userBusiness = new UserBusiness();
        //set充值的账户
        userBusiness.setUserAccount(userAccount);
        userBusiness.setProductTypeId(0);
        userBusiness.setBuyType("充值");
        userBusiness.setBuyMoney(rechargeNum);
        userBusiness.setRemark("充值");
        if (ObjectUtils.isEmpty(date)) {
            date = new Date();
        }
        userBusiness.setOrderId(date.getTime());
        userBusiness.setBuyDate(date);
        userBusinessDao.insert(userBusiness);
    }
}
