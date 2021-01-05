package com.oes.util;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @author dxh924
 * @Title:
 * @Package
 * @Description:
 * @date 11/5/20208:44 PM
 */
public class EncryptionUtils {
    /**
     * 使用后台公钥加密
     * @param str
     * @return
     */
    public static String RSABackendEncrypt(String str)  {
        String result = null;
        try {
            result = RSAUtils.publicEncrypt(str, RSAUtils.getPublicKey(RSAUtils.keyMap.get("backendPublicKey")));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 使用前台公钥加密
     * @param str
     * @return
     */
    public static String RSAFrontendEncrypt(String str)  {
        String result = null;
        try {
            result = RSAUtils.publicEncrypt(str, RSAUtils.getPublicKey(RSAUtils.keyMap.get("frontendPublicKey")));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 使用私钥解密
     * @param str
     * @return
     */
    public static String RSADecrypt(String str)  {
        String result = null;
        try {
            result = RSAUtils.privateDecrypt(str, RSAUtils.getPrivateKey(RSAUtils.keyMap.get("privateKey")));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 使用支付接口时，加密后的信息需要替换特殊字符
     * @param str
     * @return
     */
    public static String replaceAfterEncryption(String str) {
        return str.replace("+", "-").replace("/", "_");
    }


    /**
     * 使用接口发送数据后会返回加密后的信息，此处替换特殊字符
     * @param str
     * @return
     */
    public static String replaceAfterReceive (String str) {
         return str.replace("-", "+");
    }
}
