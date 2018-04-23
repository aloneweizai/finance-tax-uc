package com.abc.common.util;

import com.abc.common.exception.SoaException;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.RSAResponse;

import javax.servlet.http.HttpServletRequest;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * Created by stuy on 2017/9/11.
 */
public class GetRsaKey {


    public static RSAPrivateKey getPrivateKey(HttpServletRequest request) throws SoaException{
        RSAResponse br=SoaConnectionFactory.get(request, ConstantsUri.UC_PRIVATE,null, RSAResponse.class);
        if(br!=null){
            RSAPrivateKey privatekey = RSA.getRSAPrivateKey(br.getModulus(), br.getExponent());
            return privatekey;
        }
        return null;
    }


    public static RSAPublicKey getPublicKey(HttpServletRequest request) throws SoaException{
        RSAResponse br=SoaConnectionFactory.get(request, ConstantsUri.UC_PUBLIC,null, RSAResponse.class);
        if(br!=null){
            RSAPublicKey publickey = RSA.getRSAPublidKey(br.getModulus(), br.getExponent());
            return publickey;
        }
        return null;
    }

    public static RSAPrivateKey getV2PrivateKey(HttpServletRequest request) throws SoaException{
        RSAResponse br=SoaConnectionFactory.get(request, ConstantsUri.UC_PRIVATE_V2,null, RSAResponse.class);
        if(br!=null){
            RSAPrivateKey privatekey = RSA.getRSAPrivateKey(br.getModulus(), br.getExponent());
            return privatekey;
        }
        return null;
    }


    public static RSAPublicKey getV2PublicKey(HttpServletRequest request) throws SoaException{
        RSAResponse br=SoaConnectionFactory.get(request, ConstantsUri.UC_PUBLIC_V2,null, RSAResponse.class);
        if(br!=null){
            RSAPublicKey publickey = RSA.getRSAPublidKey(br.getModulus(), br.getExponent());
            return publickey;
        }
        return null;
    }
}
