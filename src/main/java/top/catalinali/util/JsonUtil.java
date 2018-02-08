package top.catalinali.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * <pre>
 * Author:		lllx
 * Version:		1.0
 * Created at:	2018/2/5
 * </pre>
 */
public class JsonUtil {
    public static String toJson(Object object) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();
        return gson.toJson(object);
    }
}
