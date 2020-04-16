package cn.nuaa.service.impl;

import cn.nuaa.common.exception.AdminExistException;
import cn.nuaa.common.exception.AdminExistException;
import cn.nuaa.dao.AdminDao;
import cn.nuaa.entity.Admin;
import cn.nuaa.entity.User;
import cn.nuaa.service.AdminService;
import cn.nuaa.service.AdminService;
import cn.nuaa.vo.AdminVo;
import cn.nuaa.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;

/**
 * @Author: wpc
 * @Date: 2020/3/5 17:59
 * @Description: <描述>
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao adminDao;

    @Value("${admin.invitationCode}")
    private String invitationCode;

    @Override
    public Admin findByAdminAccount(String adminAccount) {
        return adminDao.selectByAdminAccount(adminAccount);
    }

    @Override
    public void modify(AdminVo adminVo) throws AdminExistException {
        if (adminVo.getPhone() == null || adminVo.getPhone() == "") {
            throw new AdminExistException("手机号码不能为空");
        }
        Admin admin = new Admin();
        admin.setId(adminVo.getId());
        admin.setName(adminVo.getName());
        admin.setPhone(adminVo.getPhone());
        admin.setAddress(adminVo.getAddress());
        admin.setEmail(adminVo.getEmail());
        adminDao.updateById(admin);
    }

    @Override
    public void modifyPassword(AdminVo adminVo) throws AdminExistException {
        if (adminVo.getPassword() == null || adminVo.getPassword() == "") {
            throw new AdminExistException("密码不能为空");
        }
        Admin admin = new Admin();
        admin.setId(adminVo.getId());
        admin.setPassword(adminVo.getPassword());
        adminDao.updateById(admin);
    }

    @Override
    public void add(AdminVo adminVo) throws AdminExistException {
        if (adminVo.getAdminAccount()==null||"".equals(adminVo.getAdminAccount())){
            throw new AdminExistException("会员名不能为空");
        }
        if (adminVo.getPassword()==null||"".equals(adminVo.getPassword())){
            throw new AdminExistException("密码不能为空");
        }
        if (adminVo.getPhone()==null||"".equals(adminVo.getPhone())){
            throw new AdminExistException("手机号不能为空");
        }
        if (!invitationCode.equals(adminVo.getInvitationCode())){
            throw new AdminExistException("邀请码不正确,请联系管理员获取邀请码");
        }
        Admin selectByAdminAccount = adminDao.selectByAdminAccount(adminVo.getAdminAccount());
        if (selectByAdminAccount!=null){
            throw new AdminExistException("会员名已被注册，请更换其他用户名");
        }
        Admin admin = new Admin();
        BeanUtils.copyProperties(adminVo,admin);
        admin.setCreateDate(new Date());
        adminDao.insert(admin);
    }

    @Override
    public AdminVo login(AdminVo adminVo) throws AdminExistException {
        Admin selectByAdminAccount = adminDao.selectByAdminAccount(adminVo.getAdminAccount());
        if (selectByAdminAccount==null){
            throw new AdminExistException("用户不存在，请重试或注册");
        }
        if (!selectByAdminAccount.getPassword().equals(adminVo.getPassword())){
            throw new AdminExistException("密码错误,请重试");
        }
        AdminVo adminVoAfter = new AdminVo();
        adminVoAfter.setAdminAccount(selectByAdminAccount.getAdminAccount());
        adminVoAfter.setPhone(selectByAdminAccount.getPhone());
        return adminVoAfter;
    }
}
