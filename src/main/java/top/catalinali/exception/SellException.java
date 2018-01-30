package top.catalinali.exception;

import top.catalinali.enums.ResultEnum;

/**
 * <pre>
 * Author:		lllx
 * Version:		1.0
 * Created at:	2018/1/27
 * </pre>
 */
public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(String message, Integer code) {
        super(message);
        this.code = code;
    }
}
