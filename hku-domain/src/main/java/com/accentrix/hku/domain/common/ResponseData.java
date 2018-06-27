package com.accentrix.hku.domain.common;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月1日 下午3:31:15
 * @version 1.0
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.PROPERTY)
public class ResponseData {

    private final String message;
    private final int code;
    private final Map<String, Object> data = new HashMap<String, Object>();

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }

    public Object getData(String key) {
        return data.get(key);
    }

    public ResponseData putDataValue(String key, Object value) {
        data.put(key, value);
        return this;
    }

    private ResponseData(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ResponseData ok() {
        return new ResponseData(200, "Ok");
    }

    public static ResponseData notFound() {
        return new ResponseData(404, "Not Found");
    }

    public static ResponseData badRequest() {
        return new ResponseData(400, "Bad Request");
    }

    public static ResponseData forbidden() {
        return new ResponseData(403, "Forbidden");
    }

    public static ResponseData unauthorized() {
        return new ResponseData(401, "unauthorized");
    }

    public static ResponseData serverInternalError() {
        return new ResponseData(500, "Server Internal Error");
    }

    public static ResponseData customerError() {
        return new ResponseData(1001, "customer Error");
    }
}
