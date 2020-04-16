package cn.nuaa.common.utils;

import java.util.UUID;

/**
 * @Author: wpc
 * @Date: 2020/3/25 18:49
 * @Description: <身份证加密用>
 */

public class IdentityUtil {

    public static String identityStar(String identity){
        String idBegin = identity.substring(0,8);
        String idEnd=identity.substring(14);
        return idBegin+"******"+idEnd;
    }
}
