package com.abc.cache;

import com.abc.common.exception.SoaException;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.GetRsaKey;
import com.abc.common.util.MD5;
import com.abc.service.GetInfoService;
import com.abc.service.RedisCode;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.UserSlatBo;
import com.abc.soa.response.DictRes;
import com.abc.soa.response.UserClientBo;
import com.abc.soa.response.region.AreaListRs;
import com.abc.soa.response.region.CityListRs;
import com.abc.soa.response.region.ProvinceListRs;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map;

/**
 * 对不变的数据进行redis缓存
 * 周亚
 * 2017-12-21
 */
public class RedisCacheService {


    /**
     * 缓存数据字典数据
     */
    public static DictRes getRedisDictRes(HttpServletRequest request, GetInfoService getInfoService,String key) throws SoaException{
        Object dictobj = getInfoService.getObject(RedisCode.DICT + ":" + key,DictRes.class);
        DictRes dictRes =null;
        if(dictobj==null){
            dictRes = SoaConnectionFactory.getRestful(request, ConstantsUri.DICT, null, DictRes.class, key);
            if(dictRes.getDataList()!=null&&dictRes.getDataList().size()>0){
                getInfoService.setObject(RedisCode.TIME_24_60_60,RedisCode.DICT+":"+key,dictRes);
            }
        }else{
            dictRes=(DictRes)dictobj;
        }
        return dictRes;
    }

    /**
     * 缓存rsa 公钥
     * @param request
     * @param getInfoService
     * @return
     * @throws Exception
     */
    public static RSAPublicKey getRedisPublicKey(HttpServletRequest request, GetInfoService getInfoService) throws SoaException{
        RSAPublicKey publickey  = GetRsaKey.getPublicKey(request);
        return publickey;
    }

    /**
     * 缓存rsa 公钥
     * @param request
     * @param getInfoService
     * @return
     * @throws Exception
     */
    public static RSAPublicKey getRedisV2PublicKey(HttpServletRequest request, GetInfoService getInfoService) throws SoaException{
        RSAPublicKey publickey = GetRsaKey.getV2PublicKey(request);
        return publickey;
    }


    /**
     * 缓存rsa 私钥
     * @param request
     * @param getInfoService
     * @return
     * @throws Exception
     */
    public static RSAPrivateKey getRedisPrivateKey(HttpServletRequest request, GetInfoService getInfoService)throws SoaException {
        RSAPrivateKey privatekey=GetRsaKey.getPrivateKey(request);
            return privatekey;
    }

    /**
     * 缓存rsa 私钥
     * @param request
     * @param getInfoService
     * @return
     * @throws Exception
     */
    public static RSAPrivateKey getRedisV2PrivateKey(HttpServletRequest request, GetInfoService getInfoService)throws SoaException {
        RSAPrivateKey privatekey= GetRsaKey.getV2PrivateKey(request);
        return privatekey;
    }

    /**
     * 省份缓存
     * @param request
     * @param getInfoService
     * @return
     * @throws SoaException
     */
    public static ProvinceListRs getRedisProvince(HttpServletRequest request, GetInfoService getInfoService)throws SoaException{
        Object province_list = getInfoService.getObject(RedisCode.PROVINCE_LIST,ProvinceListRs.class);
        if(province_list==null){
            ProvinceListRs list = SoaConnectionFactory.get(request, ConstantsUri.PROVINCE, null, ProvinceListRs.class);
            getInfoService.setObject(RedisCode.TIME_30_24_60_60,RedisCode.PROVINCE_LIST,list);
            return list;
        }else{
            return (ProvinceListRs)province_list;
        }
    }


    /**
     * 市缓存
     * @param request
     * @param getInfoService
     * @param key 省份id
     * @return
     * @throws SoaException
     */
    public static CityListRs getRedisCity(HttpServletRequest request, GetInfoService getInfoService,String key) throws SoaException{
        Object city_list = getInfoService.getObject(RedisCode.CITY_LIST+":"+key,CityListRs.class);
        if(city_list==null){
            CityListRs cityListRs = SoaConnectionFactory.getRestful(request, ConstantsUri.CITY, null, CityListRs.class, key);
            getInfoService.setObject(RedisCode.TIME_30_24_60_60,RedisCode.CITY_LIST+":"+key,cityListRs);
            return cityListRs;
        }else{
            return (CityListRs)city_list;
        }

    }

    /**
     * 区县缓存
     * @param request
     * @param getInfoService
     * @param key 市编号
     * @throws SoaException
     */
    public static AreaListRs getRedisArea(HttpServletRequest request, GetInfoService getInfoService,String key)throws SoaException{
        Object area_list = getInfoService.getObject(RedisCode.AREA_LIST+":"+key,AreaListRs.class);
        if(area_list==null){
            AreaListRs areaListRs = SoaConnectionFactory.getRestful(request, ConstantsUri.AREA, null, AreaListRs.class, key);
            getInfoService.setObject(RedisCode.TIME_30_24_60_60,RedisCode.AREA_LIST+":"+key,areaListRs);
            return areaListRs;
        }else{
            return (AreaListRs)area_list;
        }
    }

    /**
     * 客户端accesstoken和usertoken缓存
     * @param request
     * @param getInfoService
     * @param AccessToken
     * @param userToken
     * @return
     * @throws SoaException
     */
    public static UserClientBo getRedisUserClient(HttpServletRequest request, GetInfoService getInfoService,String AccessToken,String userToken)throws SoaException{
        String accesstoken=getInfoService.getString(RedisCode.ACCESSTOKEN+":"+userToken);
        if(!(accesstoken!=null&&!"".equals(accesstoken))){
            getInfoService.setString(RedisCode.TIME_30,RedisCode.ACCESSTOKEN+":"+userToken,AccessToken);
        }
        Object obj = getInfoService.getObject(RedisCode.CLIENT_USER + ":" + AccessToken + ":" + userToken,UserClientBo.class);
        if(obj==null){
            UserClientBo baseResponse=SoaConnectionFactory.getRestful(request,ConstantsUri.GETUSER_TOKEN,null,UserClientBo.class,userToken);
            getInfoService.setObject(RedisCode.TIME_30,RedisCode.CLIENT_USER + ":" + AccessToken + ":" + userToken,baseResponse);
            return baseResponse;
        }else{
            return (UserClientBo)obj;
        }
    }

}
