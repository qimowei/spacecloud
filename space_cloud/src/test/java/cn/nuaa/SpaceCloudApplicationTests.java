package cn.nuaa;

import cn.nuaa.common.ResponseResult;
import cn.nuaa.common.constant.PaginationConstant;
import cn.nuaa.common.exception.ProductTypeExistException;
import cn.nuaa.common.exception.UserExistException;
import cn.nuaa.dao.*;
import cn.nuaa.entity.*;
import cn.nuaa.service.ProductSystemService;
import cn.nuaa.service.ProductTypeService;
import cn.nuaa.service.UserService;
import cn.nuaa.vo.ProductTypeVo;
import cn.nuaa.vo.UserVo;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ObjectUtils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootTest
class SpaceCloudApplicationTests {

    @Autowired
    private ProductTypeDao productTypeDao;
    @Autowired
    private SortDao sortDao;
    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private ProductSystemDao productSystemDao;
    @Autowired
    private ProductSystemService productSystemService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;
    @Autowired
    private ProductDao productDao;

    @Test
    void contextLoads() {
    }

    @Test
    void Sort() {
        //查询所有sort
        List<Sort> sorts = sortDao.selectList(null);
        sorts.forEach(System.out::println);
        //System.out.println(sorts);
    }

    @Test
    void ProductTypeSelect(){
        //查询所有productType
        //List<ProductType> productTypes = productTypeDao.selectAll();
        //productTypes.forEach(System.out::println);
        //根据名称查找信息
        ProductType productType = productTypeDao.selectByName("国内主机");
        System.out.println(productType);
        //PageHelper.startPage(1,PaginationConstant.PAGE_SIZE);
        //List<ProductType> productTypes = productTypeService.findAll();
        //PageInfo<ProductType> pageInfo = new PageInfo<>(productTypes);
        //System.out.println(ResponseResult.success(pageInfo.getTotal(),pageInfo.getList()));
    }

    //@Test
    //void ProductTypeInsert(){
    //    ProductType productType = new ProductType();
    //    productType.setName("测试名");
    //    productType.setStatus(1);
    //    productType.setSort_id(1);
    //    //Sort sort = new Sort();
    //    //sort.setId(1);
    //    //productType.setSort(sort);
    //    productTypeDao.insert(productType);
    //    System.out.println(productType);
    //}

    //@Test
    //void ProductTypeUpdate() throws ProductTypeExistException {
    //    //productTypeService.modifyStatus(1);
    //    ProductTypeVo productTypeVo = new ProductTypeVo();
    //    productTypeVo.setId(1);
    //    productTypeVo.setName("123123");
    //    productTypeVo.setSort_id(2);
    //    productTypeService.modify(productTypeVo);
    //}

    @Test
    void findAll() {
        QueryWrapper<ProductSystem> qw = new QueryWrapper<>();
        List<ProductSystem> productSystems;
        String sys_type="Wind";
        if (sys_type == null || sys_type == "") {
            productSystems = productSystemDao.selectList(null);
        }else {
            qw.like("sys_type", sys_type);
            productSystems = productSystemDao.selectList(qw);
        }
        System.out.println(productSystems);
    }

    @Test
    void findUser() throws UserExistException {
        String userAccount=null;
        if(ObjectUtils.isEmpty(userAccount)){
            userAccount="qimowei";
        }
        try {
            UserVo userVo=userService.findByUserAccount(userAccount);
            System.out.println(Arrays.asList(userVo));
        } catch (UserExistException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void findProduct(){
        //String userAccount = productVo.getUserAccount();
        //String sortName = productVo.getSortName();
        List<Product> products = productDao.queryAll("user","主机","2020-08-23");
        System.out.println(products.toString());
    }

    //@Test
    //void updateUserBalance(){
    //    //先根据账号名查信息
    //    User userByUserAccount = userDao.selectByUserAccount("user");
    //    BigDecimal priceNumBigAfter = BigDecimal.valueOf(199.9);
    //    BigDecimal balance = BigDecimal.valueOf(userByUserAccount.getBalance());
    //    BigDecimal allPay = BigDecimal.valueOf(userByUserAccount.getAllPay());
    //    User user = new User();
    //    //用BigDecimal提供精确减法运算的subtract方法,余额减法运算
    //    user.setBalance(balance.subtract(priceNumBigAfter).doubleValue());
    //    //用BigDecimal提供精确减法运算的add方法,总消费加法运算
    //    user.setAllPay(allPay.add(priceNumBigAfter).doubleValue());
    //    user.setEndpayDate(new Date());
    //    user.setUserAccount("user");
    //    userDao.updateBalanceByUserAccount(user);
    //}
}
