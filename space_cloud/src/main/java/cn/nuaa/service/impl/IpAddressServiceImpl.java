package cn.nuaa.service.impl;

import cn.nuaa.common.constant.IpAddressStatusConstant;
import cn.nuaa.common.exception.IpAddressExistException;
import cn.nuaa.entity.IpAddress;
import cn.nuaa.dao.IpAddressDao;
import cn.nuaa.service.IpAddressService;
import cn.nuaa.vo.IpAddressVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * (IpAddress)表服务实现类
 *
 * @author wpc
 * @since 2020-04-02 17:31:58
 */
@Service("ipAddressService")
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public class IpAddressServiceImpl implements IpAddressService {
    @Autowired
    private IpAddressDao ipAddressDao;

    @Override
    public List<IpAddress> findAll(IpAddressVo ipAddressVo) {
        IpAddress ipAddress = new IpAddress();
        BeanUtils.copyProperties(ipAddressVo,ipAddress);
        return ipAddressDao.queryAll(ipAddress);
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public IpAddress findById(Integer id) {
        return this.ipAddressDao.queryById(id);
    }

    /**
     * 新增数据
     *
     * @param ipAddressVo 实例对象
     * @return 实例对象
     */
    @Override
    public IpAddress add(IpAddressVo ipAddressVo) throws IpAddressExistException {
        IpAddress queryByIp = ipAddressDao.queryByIp(ipAddressVo.getPublicIp());
        if(queryByIp!=null){
            throw new IpAddressExistException("ip地址已存在");
        }
        IpAddress ipAddress = new IpAddress();
        BeanUtils.copyProperties(ipAddressVo,ipAddress);
        ipAddress.setIpStatus(IpAddressStatusConstant.IPADDRESS_NO_STATUS);
        ipAddress.setCreateDate(new Date());
        this.ipAddressDao.insert(ipAddress);
        return ipAddress;
    }

    /**
     * 修改数据
     *
     * @param ipAddressVo 实例对象
     * @return 实例对象
     */
    @Override
    public IpAddress modify(IpAddressVo ipAddressVo) throws IpAddressExistException {
        IpAddress ipAddress = new IpAddress();
        BeanUtils.copyProperties(ipAddressVo,ipAddress);

        if (ipAddressVo.getIpStatus()!=null){
            if (ipAddressVo.getIpStatus()==IpAddressStatusConstant.IPADDRESS_NO_STATUS){
                ipAddress.setIpStatus(IpAddressStatusConstant.IPADDRESS_CLOSE_STATUS);
            }else{
                ipAddress.setIpStatus(IpAddressStatusConstant.IPADDRESS_NO_STATUS);
            }
        }
        this.ipAddressDao.updateById(ipAddress);
        return this.findById(ipAddressVo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean removeById(Integer id) throws IpAddressExistException {
        IpAddress findById = this.findById(id);
        if(findById.getIpStatus()== IpAddressStatusConstant.IPADDRESS_ON_STATUS){
            throw new IpAddressExistException("此ip正在有用户使用，不可删除");
        }
        return this.ipAddressDao.deleteById(id) > 0;
    }
}