package com.accentrix.hku.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * @author Jaye.Lin 
 * @date 创建时间：2018年2月1日 下午5:02:45 
 * @version 1.0 
 */

public class UserUtils {

    private static final Logger LOG = LoggerFactory.getLogger(UserUtils.class);

    public static final int SALT_SIZE = 8;
    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;

    public static final String TOKEN = "token";
    public static final String USER = "user";
    public static final String UUID = "uuid";
    public static final String APPLICATION_ID = "applicationId";

    /**
     * Generate a secure password, generate a random 16 bit salt and pass 1024 times SHA-1 hash
     */
    public static String entryptPassword(String plainPassword) {
        byte[] salt = Digests.generateSalt(SALT_SIZE);
        byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), salt, HASH_INTERATIONS);
        return Encodes.encodeHex(salt) + Encodes.encodeHex(hashPassword);
    }

    /**
     * Verify password
     * 
     * @param plainPassword
     *            enable password
     * @param password
     *            
     * @return Success return true
     */
    public static boolean validatePassword(String plainPassword, String password) {

        if (StringUtils.isAnyBlank(plainPassword, password)) {
            return false;
        }

        String salt = password.substring(0, 16);
        byte[] saltByte = Encodes.decodeHex(salt);
        byte[] hashPassword = Digests.sha1(plainPassword.getBytes(), saltByte, HASH_INTERATIONS);
        return password.equals(salt + Encodes.encodeHex(hashPassword));
    }

    public static String transcodingId(String id) {
        try {
            byte[] salt = Digests.generateSalt(SALT_SIZE);
            byte[] idByte = id.getBytes("UTF-8");
            return Encodes.encodeHex(salt) + Encodes.encodeHex(idByte);
        } catch (Exception e) {
            LOG.debug(e.getMessage(), e);
            return "";
        }
    }

    public static String decodeId(String saltId) {
        try {
            String id = saltId.substring(16, saltId.length());
            byte[] idByte = Encodes.decodeHex(id);
            return new String(idByte, "UTF-8");
        } catch (Exception e) {
            LOG.debug(e.getMessage(), e);
            return "";
        }
    }
}
