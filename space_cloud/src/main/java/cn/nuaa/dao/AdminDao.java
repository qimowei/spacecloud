package cn.nuaa.dao;

import cn.nuaa.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;


/**
 * @Author: wpc
 * @Date: 2020/3/5 17:06
 * @Description: <使用mybatisPlus  继承BaseMapper<>不用再写简单方法>
 */
@Repository
public interface AdminDao extends BaseMapper<Admin> {


    /**
     * 根据登录名查询信息
     * @param adminAccount
     * @return
     */
    Admin selectByAdminAccount(String adminAccount);

}
