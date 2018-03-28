package top.catalinali.util;

import top.catalinali.enums.CodeEnum;

/**
 * <pre>
 * Description: 通用转换字段
 * Author:		lllx
 * Version:		1.0
 * Created at:	2018/3/28
 * </pre>
 */
public class EnumUtil {
    public <T extends CodeEnum> T getByCode(Integer code, Class<T> enumClass){
        for (T each : enumClass.getEnumConstants()) {
            if(code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}
