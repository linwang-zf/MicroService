package com.oes.util.json;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author zhang chenghuai
 * @Title:
 * @Package
 * @Description:
 * @date 2020/9/1715:50
 */
public class JsonUtils {
    public static String toJsonString(Object object) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("object", object);
        return jsonObject.toString(SerializerFeature.WriteMapNullValue);
    }

    public static JSONObject toJsonObject(String jsonString) {
        System.out.println(jsonString);
        return JSONObject.parseObject(jsonString);
    }
}
