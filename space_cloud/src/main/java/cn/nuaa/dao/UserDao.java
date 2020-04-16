package cn.nuaa.dao;

import cn.nuaa.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: wpc
 * @Date: 2020/3/5 17:06
 * @Description: <使用mybatisPlus  继承BaseMapper<>不用再写简单方法>
 */
@Repository
public interface UserDao extends BaseMapper<User> {


    /**
     * 根据登录名查询信息
     * @param userAccount
     * @return
     */
    User selectByUserAccount(String userAccount);

    /**
     * 根据登录名修改信息
     * 预留了user实体类，暂时就可以修改余额和总消费和最近消费时间，因为修改其他信息可以用mybatisplus的updata
     *      如后期需要，可以在加
     * @param user
     */
    void updateBalanceByUserAccount(User user);
}
