package cn.nuaa.service;

import cn.nuaa.common.exception.AdminExistException;
import cn.nuaa.entity.Admin;
import cn.nuaa.vo.AdminVo;

/**
 * @Author: wpc
 * @Date: 2020/3/5 18:12
 * @Description: <描述>
 */

public interface AdminService {

    /**
     * 根据登录账号查询信息
     * @param adminAccount
     * @return
     */
    Admin findByAdminAccount(String adminAccount);

    /**
     * 修改管理员信息
     * @param adminVo
     * @throws AdminExistException
     */
    void modify(AdminVo adminVo) throws AdminExistException;

    /**
     * 修改密码
     * @param adminVo
     * @throws AdminExistException
     */
    void modifyPassword(AdminVo adminVo) throws AdminExistException;

    void add(AdminVo adminVo) throws AdminExistException;

    AdminVo login(AdminVo adminVo) throws AdminExistException;
}
