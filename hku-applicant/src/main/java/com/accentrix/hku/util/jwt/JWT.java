package com.accentrix.hku.util.jwt;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.accentrix.hku.util.ConstantsUtils;
import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Jaye.Lin
 * @date 创建时间：2018年3月1日 下午3:11:24
 * @version 1.0
 */

public class JWT {

    private static final Logger LOG = LoggerFactory.getLogger(JWT.class);

    public static final String SECRET = "XX#$%()(#*!()!KL<><MQLMNQNQJQK sdfkjsdrow32234545fdf>?N<:{LWPW";

    public static final String EXP = "exp";

    public static final String PAYLOAD = "payload";

    // 加密，传入一个对象和有效期
    public static <T> String sign(T object) {
        try {
            final JWTSigner signer = new JWTSigner(SECRET);
            final Map<String, Object> claims = new HashMap<String, Object>();
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(object);
            claims.put(PAYLOAD, jsonString);
            claims.put(EXP, System.currentTimeMillis() + ConstantsUtils.LOGIN_TIME);
            String token = signer.sign(claims);
            return token;
        } catch (Exception e) {
            LOG.debug("token create failure", e);
            return null;
        }
    }

    // 解密，传入一个加密后的token字符串和解密后的类型
    public static <T> T unsign(String jwt, Class<T> classT) {
        final JWTVerifier verifier = new JWTVerifier(SECRET);
        try {
            final Map<String, Object> claims = verifier.verify(jwt);
            if (claims.containsKey(EXP) && claims.containsKey(PAYLOAD)) {
                long exp = (Long) claims.get(EXP);
                long currentTimeMillis = System.currentTimeMillis();
                if (exp > currentTimeMillis) {
                    String json = (String) claims.get(PAYLOAD);
                    ObjectMapper objectMapper = new ObjectMapper();
                    return objectMapper.readValue(json, classT);
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
