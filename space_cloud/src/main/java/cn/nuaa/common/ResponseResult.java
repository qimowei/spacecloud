package cn.nuaa.common;


import java.io.Serializable;
import cn.nuaa.common.constant.ResponseStatusConstant;

/**
 * @Author: wpc
 * @Date: 2020/2/10 19:40
 * @Description: <描述>
 */

public class ResponseResult implements Serializable{

    /**
     * 状态码
     * */
    private Integer code;

    /**
     * 消息
     * */
    private String msg;

    /**
     *  数据
     * */

    private Object data;

    /**
     *  分页时候才用到，是返回数据的总条数
     * */
    private long count;

    public static ResponseResult success(){
        ResponseResult result = new ResponseResult();
        result.setCode(ResponseStatusConstant.SUCCESS);
        result.setMsg("success");
        return result;
    }

    public static ResponseResult success(String msg){
        ResponseResult result = new ResponseResult();
        result.setCode(ResponseStatusConstant.SUCCESS);
        result.setMsg(msg);
        return result;
    }

    public static ResponseResult success(Object data){
        ResponseResult result = new ResponseResult();
        result.setCode(ResponseStatusConstant.SUCCESS);
        result.setMsg("success");
        result.setData(data);
        return result;
    }

    public static ResponseResult success(long count,Object data){
        ResponseResult result = new ResponseResult();
        result.setCode(ResponseStatusConstant.SUCCESS);
        result.setMsg("success");
        result.setCount(count);
        result.setData(data);
        return result;
    }

    public static ResponseResult fail(){
        ResponseResult result = new ResponseResult();
        result.setCode(ResponseStatusConstant.FAIL);
        result.setMsg("fail");
        return result;
    }

    public static ResponseResult fail(String msg){
        ResponseResult result = new ResponseResult();
        result.setCode(ResponseStatusConstant.FAIL);
        result.setMsg(msg);
        return result;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
