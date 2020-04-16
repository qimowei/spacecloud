package cn.nuaa.controller.backend;

import cn.nuaa.common.ResponseResult;
import cn.nuaa.common.constant.PaginationConstant;
import cn.nuaa.common.exception.IpAddressExistException;
import cn.nuaa.entity.IpAddress;
import cn.nuaa.entity.IpAddress;
import cn.nuaa.service.IpAddressService;
import cn.nuaa.vo.IpAddressVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * (IpAddress)表控制层
 *
 * @author wpc
 * @since 2020-04-02 17:31:58
 */
@RestController
@RequestMapping("/backend/ipAddress")
public class IpAddressController {
    /**
     * 服务对象
     */
    @Autowired
    private IpAddressService ipAddressService;

    /**
     * 查询所有ip信息并分类
     * @param ipAddressVo
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/findAll")
    @ResponseBody
    public ResponseResult findAll(IpAddressVo ipAddressVo, Integer page, @RequestParam(name = "limit", required = false) Integer pageSize) {
        /**
         * pagehelper插件分页,page和limit(pageSize)从前端获取
         */
        if (ObjectUtils.isEmpty(page)) {
            //判断pageNum是否为空，如果是，则给PAGE_NUM的值1
            page = PaginationConstant.PAGE_NUM;
        }
        if (ObjectUtils.isEmpty(pageSize)) {
            //判断pageSize是否为空，如果是，则给PAGE_SIZE的值1
            pageSize = PaginationConstant.PAGE_SIZE;
        }
        //初始化pageHelper
        PageHelper.startPage(page, pageSize);
        //查询所有商品名称
        List<IpAddress> ipAddresss = ipAddressService.findAll(ipAddressVo);
        //将查询结果放到PageInfo里，方便调用
        PageInfo<IpAddress> pageInfo = new PageInfo<>(ipAddresss);
        return ResponseResult.success(pageInfo.getTotal(), pageInfo.getList());
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/findById")
    public ResponseResult selectOne(Integer id) {
        IpAddress ipAddress = this.ipAddressService.findById(id);
        return ResponseResult.success(ipAddress);
    }

    /**
     * 根据id删除ip
     * @param id
     * @return
     */
    @GetMapping("/removeById")
    public ResponseResult removeById(Integer id){
        try {
            this.ipAddressService.removeById(id);
            return ResponseResult.success();
        } catch (IpAddressExistException e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

    /**
     * 修改ip地址
     * @param ipAddressVo
     * @return
     */
    @GetMapping("/modify")
    public ResponseResult modify(IpAddressVo ipAddressVo){
        try {
            ipAddressService.modify(ipAddressVo);
            return ResponseResult.success();
        } catch (IpAddressExistException e) {
            return ResponseResult.fail(e.getMessage());
        }

    }

    /**
     * 新增ip参数
     * @param ipAddressVo
     * @return
     */
    @GetMapping("/add")
    public ResponseResult add(IpAddressVo ipAddressVo){
        try {
            ipAddressService.add(ipAddressVo);
            return ResponseResult.success();
        } catch (IpAddressExistException e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

}