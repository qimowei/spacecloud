package cn.nuaa.service;

import cn.nuaa.common.exception.UserExistException;
import cn.nuaa.entity.User;
import cn.nuaa.vo.UserVo;

import javax.validation.constraints.NotNull;

/**
 * @Author: wpc
 * @Date: 2020/3/24 15:52
 * @Description: <描述>
 */

public interface UserService {

    /**
     * 根据登录账号查询信息
     * @param userAccount
     * @return
     * @throws UserExistException
     */
    UserVo findByUserAccount(String userAccount) throws UserExistException;

    /**
     * 修改用户信息
     * @param userVo
     * @throws UserExistException
     */
    void modify(UserVo userVo) throws UserExistException;

    /**
     * 修改密码
     * @param userVo
     * @throws UserExistException
     */
    void modifyPassword(UserVo userVo) throws UserExistException;

    /**
     * 修改邮箱
     * @param userVo
     * @throws UserExistException
     */
    void modifyEmail(UserVo userVo) throws UserExistException;

    /**
     * 会员充值接口
     * @param userAccount
     * @param rechargeNum
     */
    void recharge(String userAccount,Double rechargeNum) throws UserExistException;

    /**
     * 查余额接口
     * @param userAccount
     * @param productPrice
     * @return 返回0表示资金够了，返回1表示余额不足
     */
    int checkBalance(String userAccount, Double productPrice);

    /**
     * 用户注册
     * @param userVo
     * @return
     */
    void add(UserVo userVo) throws UserExistException;

    /**
     * 用户登录接口
     * @param userVo
     * @return
     */
    UserVo login(UserVo userVo) throws UserExistException;
}
