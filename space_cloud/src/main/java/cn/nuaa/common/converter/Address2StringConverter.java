package cn.nuaa.common.converter;

import cn.nuaa.vo.UserVo;
import org.springframework.core.convert.converter.Converter;

/**
 * @Author: wpc
 * @Date: 2020/3/23 21:29
 * @Description: <描述>
 */

public class Address2StringConverter implements Converter<UserVo,String> {

    @Override
    public String convert(UserVo userVo) {
        return userVo.getProvince()+"-"+userVo.getCity()+"-"+userVo.getCounty();
    }
}
