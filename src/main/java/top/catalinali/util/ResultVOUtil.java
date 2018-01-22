package top.catalinali.util;

import top.catalinali.vo.BaseResponse;

/**
 * <pre>
 * Description:
 * Copyright:	Copyright (c)2017
 * Author:		lllx
 * Version:		1.0
 * Created at:	2018/1/22
 * </pre>
 */
public class ResultVOUtil {

    public static BaseResponse success(Object ob){
        BaseResponse response = new BaseResponse();
        response.setCode(0);
        response.setMsg("成功");
        response.setData(ob);
        return response;
    }

    public static BaseResponse success(){
        return success(null);
    }

    public static BaseResponse error(Integer code,String msg){
        BaseResponse response = new BaseResponse();
        response.setCode(code);
        response.setMsg(msg);
        return response;
    }
}
