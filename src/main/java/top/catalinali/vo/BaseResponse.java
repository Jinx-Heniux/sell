package top.catalinali.vo;

import lombok.Data;

/**
 * <pre>
 * Description: http请求返回的最外层对象
 * Copyright:	Copyright (c)2017
 * Author:		lllx
 * Version:		1.0
 * Created at:	2018/1/22
 * </pre>
 */
@Data
public class BaseResponse<T> {

    private Integer code;//返回code
    private String msg;//返回消息
    private T data;//返回数据

}
