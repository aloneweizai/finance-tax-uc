package com.abc.controller.userinfo;

import com.abc.bean.userinfo.OrderBO;
import com.abc.bean.userinfo.OrderInvoiceBO;
import com.abc.cache.RedisCacheService;
import com.abc.common.exception.SoaException;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.MatrixToImageWriter;
import com.abc.controller.BaseController;
import com.abc.controller.SendCodeController;
import com.abc.service.RedisCode;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.*;
import com.abc.soa.request.InvoiceBo;
import com.abc.soa.response.*;
import com.abc.soa.response.userinfo.ExpressCompResp;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by stuy on 2017/7/19.
 */
@Controller
@RequestMapping(value = "/userinfo")
public class UserInvoiceController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(UserInvoiceController.class);

    /**
     * 显示索取发票订单和历史发票
     * @param session
     * @param request
     * @return
     */
    @GetMapping("/userinvoice.html")
    public ModelAndView user_index(HttpSession session, HttpServletRequest request) {
        try {
            ModelAndView mav= new ModelAndView("userinfo/fapiao");
            UserBo loginUserBo = getInfoService.getUserBo(request);
            if(loginUserBo!=null){
                HistoryReq historyBo=new HistoryReq();
                historyBo.setUserId(loginUserBo.getId());
                InvoiceRes invoiceRes= SoaConnectionFactory.get(request, ConstantsUri.USER_HISTORY_INVOICE,historyBo, InvoiceRes.class);
                OrderReq orderReq=new OrderReq();
                orderReq.setStatus("6");
                orderReq.setUserId(loginUserBo.getId());
                orderReq.setTradeMethod("RMB");
                orderReq.setIsInvoice(false);
                OrderFapiaoRes orderRes=SoaConnectionFactory.get(request,ConstantsUri.USER_ORDER_USER,orderReq, OrderFapiaoRes.class);
                List<OrderBO> orderList = JSON.parseArray(orderRes.getDataList(), OrderBO.class);
                mav.addObject("invoicelist",invoiceRes.getDataList());
                mav.addObject("orderlist",orderList);

                String key="fqsqstatus";
                DictRes dictRes = RedisCacheService.getRedisDictRes(request,getInfoService,key);
                mav.addObject("fqsqstatus",dictRes.getDataList());
                UserTzxxRes userTzxxRes=SoaConnectionFactory.getRestful(request,ConstantsUri.USER_TZXX,null, UserTzxxRes.class,loginUserBo.getId());
                mav.addObject("usertzxx",userTzxxRes.getData());
            }
            return mav;
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            ModelAndView mav= new ModelAndView("soaerror");
            return mav;
        }
    }


    /**
     * 跳转索取发票页面
     * @param ids
     * @param je
     * @param session
     * @param request
     * @return
     */
    @GetMapping("/addinvice.html")
    public ModelAndView addinvice( @RequestParam(value = "ids", required = false) String ids,
                                   @RequestParam(value = "je", required = false) String je,@RequestParam(value = "jes", required = false) String jes,HttpSession session, HttpServletRequest request) {
        try {
            ModelAndView mav= new ModelAndView("userinfo/addfapiao");
            UserBo loginUserBo = getInfoService.getUserBo(request);
            if(loginUserBo!=null){
                AddressRes addressRes=SoaConnectionFactory.getRestful(request, ConstantsUri.USER_ADDRESS,null, AddressRes.class,loginUserBo.getId());
                //发票类型
                String key="invoicetype";
                DictRes dictRes = RedisCacheService.getRedisDictRes(request,getInfoService,key);
                mav.addObject("fplx",dictRes.getDataList());

                //发票内容
                key="invoicecontent";
                dictRes = RedisCacheService.getRedisDictRes(request,getInfoService,key);
                mav.addObject("fpnr",dictRes.getDataList());

                //发票抬头
                key="invoicename";
                dictRes = RedisCacheService.getRedisDictRes(request,getInfoService,key);
                List<AddressBo> data = addressRes.getDataList();
                for (AddressBo addressBo : data){
                    DizhiIDBo dizhiIDBo=new DizhiIDBo();
                    boolean bool=false;
                    if(isNull(addressBo.getProvince())){
                        bool=true;
                        dizhiIDBo.setProvinceId(addressBo.getProvince());
                    }
                    if(isNull(addressBo.getCity())){
                        bool=true;
                        dizhiIDBo.setCityId(addressBo.getCity());
                    }
                    if(isNull(addressBo.getArea())){
                        bool=true;
                        dizhiIDBo.setAreaId(addressBo.getArea());
                    }
                    if(bool){
                        DizhiNameBo dizhiNameBo=SoaConnectionFactory.get(request,ConstantsUri.DIQU_QUERY_ID,dizhiIDBo,DizhiNameBo.class);
                        addressBo.setDizhi(dizhiNameBo);
                    }
                }

                mav.addObject("fptt",dictRes.getDataList());
                mav.addObject("addresslist",addressRes.getDataList());
                mav.addObject("ids",ids);
                mav.addObject("je",je);
                mav.addObject("jes",String.valueOf(jes));

            }
            return mav;
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            ModelAndView mav= new ModelAndView("soaerror");
            return mav;
        }
    }



    @GetMapping("/tpinvice.html")
    public ModelAndView tpinvice(HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav= new ModelAndView("userinfo/tpfapiao");
        return mav;
    }

    /**
     * 跳转至发票详细页面页面
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.GET)
    public ModelAndView edit(@PathVariable(value = "id") String id, HttpSession session, HttpServletRequest request) {
        try {
            ModelAndView mav = new ModelAndView("userinfo/xqfapiao");
            UserBo userbo = getInfoService.getUserBo(request);
            OrderInvoiceBO orderInvoiceRes=SoaConnectionFactory.getRestful(request,ConstantsUri.USER_ID_INVICE,null, OrderInvoiceBO.class,id,userbo.getId());
            Invoice invoice = orderInvoiceRes.getData();
            mav.addObject("orderinvoice",invoice);
            //发票类型
            String key="invoicetype";
            DictRes dictRes = RedisCacheService.getRedisDictRes(request,getInfoService,key);
            mav.addObject("fplx",dictRes.getDataList());
            //发票内容
            key="invoicecontent";
            dictRes = RedisCacheService.getRedisDictRes(request,getInfoService,key);
            mav.addObject("fpnr",dictRes.getDataList());
            //发票抬头
            key="invoicename";
            dictRes = RedisCacheService.getRedisDictRes(request,getInfoService,key);
            mav.addObject("fptt",dictRes.getDataList());

            key="fqsqstatus";
            dictRes = RedisCacheService.getRedisDictRes(request,getInfoService,key);
            mav.addObject("fqsqstatus",dictRes.getDataList());

            //查询物流
            if(invoice!= null && invoice.getExpressCompId() != null) {
                ExpressCompResp expressComp = SoaConnectionFactory.getRestful(request, ConstantsUri.UC_EXPRESS, null, ExpressCompResp.class, invoice.getExpressCompId());
                mav.addObject("expressComp", expressComp.getData());
            }
            InvoiceUrlBo orderInvoiceBo=null;
            if(invoice!=null){
                Map<String,String> map=new HashMap<String,String>();
                if(invoice.getInvoiceNo()!=null&&!"".equals(invoice.getInvoiceNo())){
                    map.put("invoiceNo",invoice.getInvoiceNo());
                    orderInvoiceBo=SoaConnectionFactory.get(request,ConstantsUri.INVOICE_DOWLOAD,map, InvoiceUrlBo.class);
                }
            }


            key="wechat_hongbao";
            dictRes = RedisCacheService.getRedisDictRes(request,getInfoService,key);
            //DictRes wechat_hongbao = SoaConnectionFactory.getRestful(request, ConstantsUri.DICT, null, DictRes.class, "wechat_hongbao");
            if(dictRes!=null){
                if(dictRes.getDataList()!=null&&dictRes.getDataList().size()>0){
                    String value=dictRes.getDataList().get(0).getFieldValue();
                    WxRedpackDataBo brs = SoaConnectionFactory.get(request, ConstantsUri.WX_REDPACK, null, WxRedpackDataBo.class, value, id);
                    if(brs.getData()!=null){
                        int width = 150; // 图像宽度
                        int height = 150; // 图像高度
                        String imagebase64 = MatrixToImageWriter.qrcodeABC(brs.getData().getUrl(),width,height);
                        mav.addObject("ewm",imagebase64);
                    }
                }
            }

            //添加发票下载
            if(orderInvoiceBo!=null){
                if("2000".equals(orderInvoiceBo.getCode())){
                    if(orderInvoiceBo.getData()!= null) {
                        String pdUrl = orderInvoiceBo.getData().getPdfUrl();
                        if (pdUrl != null) {
                            mav.addObject("url", pdUrl);
                        }
                    }
                }
            }
            return mav;
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            ModelAndView mav= new ModelAndView("soaerror");
            return mav;
        }
    }

    /**
     * 下载电子发票
     *
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/invoice/{id}", method = RequestMethod.POST)
    public ModelAndView edit1(@PathVariable(value = "id") String id, HttpSession session, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            UserBo userbo = getInfoService.getUserBo(request);
            Map<String,String> map=new HashMap<String,String>();
            map.put("invoiceNo",id);
            InvoiceUrlBo orderInvoiceBo=SoaConnectionFactory.get(request,ConstantsUri.INVOICE_DOWLOAD,map, InvoiceUrlBo.class);
            if("2000".equals(orderInvoiceBo.getCode())){
                if(orderInvoiceBo.getData()!= null) {
                    String pdUrl = orderInvoiceBo.getData().getPdfUrl();
                    if (pdUrl != null) {
                        mav.addObject("url", pdUrl);
                    }
                }
            }
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            if ( e instanceof SoaException){
                mav.addObject("soacode","8888");
                mav.addObject("message","服务器繁忙,请稍后再试...");
            }else{
                mav.addObject("soacode","7777");
                mav.addObject("message","操作失败,请稍后再试...");
            }
            return mav;
        }

        return mav;
    }


    /**
     * 索取发票保存操作
     * @param session
     * @param request
     * @param invoiceReq
     * @return
     */
    @RequestMapping(value = "/invoice/save.html",method = RequestMethod.POST)
    public ModelAndView add(HttpSession session,HttpServletRequest request, @RequestBody InvoiceBo invoiceReq) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            UserBo userbo = getInfoService.getUserBo(request);
            invoiceReq.setStatus("1");
            invoiceReq.setUserId(userbo.getId());
            invoiceReq.setUsername(userbo.getUsername());
            if(isNull(invoiceReq.getOrderstrNos())){
                String [] str=invoiceReq.getOrderstrNos().split(",");
                invoiceReq.setOrderNos(str);
            }

            if("1".equals(invoiceReq.getName())){
                invoiceReq.setAddressId(null);
            }
            BaseResponse baseResponse=SoaConnectionFactory.post(request,ConstantsUri.USER_SAVE_INVOICE,invoiceReq, BaseResponse.class,userbo.getId());
            mav.addObject("data",baseResponse);
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            if ( e instanceof SoaException){
                mav.addObject("soacode","8888");
                mav.addObject("message","服务器繁忙,请稍后再试...");
            }else{
                mav.addObject("soacode","7777");
                mav.addObject("message","操作失败,请稍后再试...");
            }
            return mav;
        }
        return mav;
    }

    /**
     * 发票收货确认
     * @param session
     * @param request
     * @param id
     * @return
     */
    @RequestMapping(value = "/invoice/shouhuo/{id}",method = RequestMethod.POST)
    public ModelAndView shouhuo(HttpSession session,HttpServletRequest request,
                                @PathVariable(value = "id")String id) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            UserBo userbo = getInfoService.getUserBo(request);
            BaseResponse baseResponse=SoaConnectionFactory.post(request,ConstantsUri.USER_INVOICE_SHOUHUO,null, BaseResponse.class,userbo.getId(),id);
            mav.addObject("data",baseResponse);
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            if ( e instanceof SoaException){
                mav.addObject("soacode","8888");
                mav.addObject("message","服务器繁忙,请稍后再试...");
            }else{
                mav.addObject("soacode","7777");
                mav.addObject("message","操作失败,请稍后再试...");
            }
            return mav;
        }
        return mav;
    }






}
