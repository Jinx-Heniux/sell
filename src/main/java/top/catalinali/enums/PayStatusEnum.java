package top.catalinali.enums;

import lombok.Getter;

/**
 * <pre>
 * Author:		lllx
 * Version:		1.0
 * Created at:	2018/1/24
 * </pre>
 */
@Getter
public enum PayStatusEnum {

    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功");

    private Integer code;
    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
