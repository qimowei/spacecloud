package cn.nuaa.service.impl;

import cn.nuaa.entity.UserBusiness;
import cn.nuaa.dao.UserBusinessDao;
import cn.nuaa.service.UserBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * (UserBusiness)表服务实现类
 *
 * @author wpc
 * @since 2020-03-28 22:34:41
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class UserBusinessServiceImpl implements UserBusinessService {
    @Autowired
    private UserBusinessDao userBusinessDao;

    @Override
    public List<UserBusiness> findAll(UserBusiness userBusiness) {
        return userBusinessDao.queryAll(userBusiness);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public UserBusiness findById(Long id) {
        return this.userBusinessDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param userBusiness 实例对象
     * @return 实例对象
     */
    @Override
    public UserBusiness add(UserBusiness userBusiness) {
        this.userBusinessDao.insert(userBusiness);
        return userBusiness;
    }

    /**
     * 修改数据
     *
     * @param userBusiness 实例对象
     * @return 实例对象
     */
    @Override
    public UserBusiness modify(UserBusiness userBusiness) {
        this.userBusinessDao.updateById(userBusiness);
        return this.findById(userBusiness.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean removeById(Integer id) {
        return this.userBusinessDao.deleteById(id) > 0;
    }
}