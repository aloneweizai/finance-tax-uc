package com.abc.service;

import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * RSA加密解密
 */
public class RsaV2Service {

    private final static Logger logger = LoggerFactory.getLogger(RsaV2Service.class);

    /**
     * rsa加密
     * @param enValue
     * @param publickey
     * @return
     */
    public static byte[] rsaV2Encryption(String enValue,RSAPublicKey publickey){
        try {
            if(enValue!=null&&!"".equals(enValue)){
                Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                cipher.init(Cipher.ENCRYPT_MODE, publickey);
                byte[] output = cipher.doFinal(enValue.getBytes());
                return output;
            }else{
                return null;
            }
        } catch (Exception e) {
            logger.info("RSA加密:"+e.getMessage());
            return null;
        }
    }


    /**
     * rsa解密
     * @param enValue
     * @param privateKey
     * @return
     */
    public static byte[] rsaV2Decrypt(String enValue, RSAPrivateKey privateKey){
        try {
            if(enValue!=null&&!"".equals(enValue)){
                Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                cipher.init(Cipher.DECRYPT_MODE, privateKey);
                byte[] b = Hex.decodeHex(enValue.toCharArray());
                byte[] output = cipher.doFinal(b);
                return output;
            }else{
                return null;
            }
        } catch (Exception e) {
            logger.info("RSA解密:"+e.getMessage());
            return null;
        }
    }

}
