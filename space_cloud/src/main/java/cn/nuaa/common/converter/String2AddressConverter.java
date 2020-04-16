package cn.nuaa.common.converter;

import cn.nuaa.vo.UserVo;
import org.springframework.core.convert.converter.Converter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author: wpc
 * @Date: 2020/3/23 21:29
 * @Description: <后台的字符串转变成前台的三个省级三级联动>
 */

public class String2AddressConverter implements Converter<String, UserVo> {
    /**
     *  定义静态常量，预编译正则表达式
     */
    private static Pattern NUMBER_PATTERN = Pattern.compile("(.*)-(.*)-(.*)");

    @Override
    public UserVo convert(String s) {
        Pattern pattern = NUMBER_PATTERN;
        Matcher matcher = pattern.matcher(s);
        if (matcher.matches()){
            String province = matcher.group(1);
            String city = matcher.group(2);
            String county = matcher.group(3);
            UserVo userVo = new UserVo();
            userVo.setProvince(province);
            userVo.setCity(city);
            userVo.setCounty(county);
            return userVo;
        }else{
            throw new RuntimeException("转换失败");
        }
    }
}
