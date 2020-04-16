package cn.nuaa.dao;

import cn.nuaa.entity.Sort;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author: wpc
 * @Date: 2020/2/10 14:37
 * @Description: <描述>
 */


/**
 * 使用mybatisPlus  继承BaseMapper<>不用再写方法
 * */
@Repository
public interface SortDao extends BaseMapper<Sort> {

    /**
     * 根据name查询信息
     * @param name
     * @return
     */
    Sort selectByName(@Param("name") String name);
}
