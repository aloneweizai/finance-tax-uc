package com.abc.controller.userinfo;

import com.abc.application.SpringCtxHolder;
import com.abc.bean.userinfo.OrderBO;
import com.abc.bean.userinfo.OrderBack;
import com.abc.bean.userinfo.OrderProductBO;
import com.abc.cache.RedisCacheService;
import com.abc.common.exception.SoaException;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.common.util.FileOperateUtil;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.*;
import com.abc.soa.request.userinfo.*;
import com.abc.soa.response.*;
import com.abc.soa.response.userinfo.*;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Author:zlk
 * @Description:退换换申请
 * @Date:2017-08-11
 * @Time:18:28
 */
@Controller
@RequestMapping(value = "/orderback")
public class UserOrderBackController extends BaseController{

    private final static Logger logger = LoggerFactory.getLogger(UserOrderBackController.class);

    @GetMapping("/back.php")
    public String list(@RequestParam(value = "page",defaultValue = "1")int page,HttpServletRequest request,HttpSession session,Model model){
        try {
            UserBo userBo = getInfoService.getUserBo(request);
            String id = userBo.getId();
            String username = userBo.getUsername();
            CompleteOrderReq orderListReq =  new CompleteOrderReq();
            orderListReq.setName(username);
            orderListReq.setPage(0);
            orderListReq.setSize(0);
            orderListReq.setUserId(id);
            orderListReq.setStatus("6");//表示已完成订单
            orderListReq.setIsReturn(true);
            //orderListReq.setIsInvoice(false);
            //orderListReq.setTradeMethod("RMB");//表示非积分兑换的商品和服务
            OrderListResp orders = SoaConnectionFactory.get(request, ConstantsUri.ORDER_COMPLETED, orderListReq, OrderListResp.class);
            List<OrderBO> orderList = JSON.parseArray(orders.getDataList(), OrderBO.class);
            model.addAttribute("orders",orderList);

            DictRes orderStatus = RedisCacheService.getRedisDictRes(request,getInfoService,"orderStatus");
            //查询并添加订单状态数据字典
            model.addAttribute("orderStatus", orderStatus.getDataList());

            ExchangeRecord record = new ExchangeRecord();
            record.setUserId(id);
            record.setOrderNo(null);
            record.setPage(0);
            record.setSize(0);
            OrderExchangeResp exchangeResp = SoaConnectionFactory.get(request, ConstantsUri.ORDER_BACK_RECORD, record, OrderExchangeResp.class);
            model.addAttribute("exchanges",exchangeResp.getDataList());
            model.addAttribute("path", SpringCtxHolder.getProperty("imagedomain"));
            DictRes exchange_status = RedisCacheService.getRedisDictRes(request,getInfoService,"exchange_status");
            //查询并添加订单状态数据字典
            model.addAttribute("exchangeStatus", exchange_status.getDataList());

            UserTzxxRes userTzxxRes=SoaConnectionFactory.getRestful(request,ConstantsUri.USER_TZXX,null, UserTzxxRes.class,userBo.getId());
            model.addAttribute("usertzxx",userTzxxRes.getData());
            return "userinfo/order_list_back";
        } catch (SoaException e) {
            logger.debug("异常:"+e.getMessage());
            String cswdomain= SpringCtxHolder.getProperty("cswdomain");
            model.addAttribute("cswurl",cswdomain);
            return "soaerror";
        }
    }

    /**
     * 退换货页面
     * @param id
     * @param request
     * @return
     */
    @RequestMapping(value = "/back/{id}",method = RequestMethod.GET)
    public ModelAndView toBack(@PathVariable(value = "id")String id,HttpServletRequest request,HttpSession session){
        try {
            ModelAndView mav = new ModelAndView("userinfo/order_back_replace");
            UserBo userBo = getInfoService.getUserBo(request);
            OrderDetailResp orderDetailResp = SoaConnectionFactory.getRestful(request, ConstantsUri.ORDER_DETAIL, null, OrderDetailResp.class, id);
            mav.addObject("orderNo",id);
            //mav.addObject("orderStatus",status);
            List<OrderProductBO> productBOList = orderDetailResp.getData().getOrderProductBOList();
            mav.addObject("productList", productBOList);
            if(productBOList!=null && productBOList.size()>0 ){
                mav.addObject("goodsBO",productBOList.get(0));
            }

            DictRes exchange_reason = RedisCacheService.getRedisDictRes(request,getInfoService,"exchange_reason");
            //查询并添加订单状态数据字典
            mav.addObject("exchangeReason", exchange_reason.getDataList());

            AddressRes addressRes=SoaConnectionFactory.getRestful(request, ConstantsUri.USER_ADDRESS,null, AddressRes.class,userBo.getId());
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
            mav.addObject("addresslist",addressRes.getDataList());

            //mav.addObject("path", SpringCtxHolder.getProperty("imagedomain"));
            return mav;
        } catch (SoaException e) {
            logger.debug("异常:"+e.getMessage());
            ModelAndView mav= new ModelAndView("soaerror");
            return mav;
        }
    }


    /**
     * 退换货须知
     * @return
     */
    @RequestMapping(value = "/back/rule",method = RequestMethod.GET)
    public ModelAndView backRule(){
        ModelAndView mav = new ModelAndView("userinfo/order_back_rule");
        return mav;
    }

    /**
     * 退换货申请,只有在完成订单的状态下,才能提交退换货申请
     * @param orderBack
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/back_replace/submit",method = RequestMethod.POST)
    public ModelAndView back(@RequestBody OrderBack orderBack,
                             HttpServletRequest request,HttpSession session){
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            UserBo obj = getInfoService.getUserBo(request);
            OrderBackReq orderBackReq = new OrderBackReq();
            //添加地址
            if(orderBack.getAddressId() != null) {
                AddressBoReq addressBoReq = new AddressBoReq();
                addressBoReq.setAddressId(orderBack.getAddressId());
                AddressRes addressRes = SoaConnectionFactory.getRestful(request, ConstantsUri.USER_ADDRESS, addressBoReq, AddressRes.class, obj.getId());
                List<AddressBo> addressBoList = addressRes.getDataList();
                for(AddressBo addressBo : addressBoList){
                    if(orderBack.getAddressId().equals(addressBo.getId())) {
                        orderBackReq.setConsignee(addressBo.getName());
                        orderBackReq.setContactNumber(addressBo.getPhone());
                        String shipAddress = addressBo.getProvinceName() + addressBo.getCityName() + addressBo.getAreaName() + addressBo.getDetail();
                        orderBackReq.setShippingAddress(shipAddress);
                    }
                }
            }
            orderBackReq.setOrderNo(orderBack.getOrderNo());
            orderBackReq.setReason(orderBack.getReason());
            orderBackReq.setType(orderBack.getType());
            orderBackReq.setUserRemark(orderBack.getUserRemark());
            orderBackReq.setFirstPicture(orderBack.getFirstPicture());
            orderBackReq.setSecondPicture(orderBack.getSecondPicture());
            orderBackReq.setThirdPicture(orderBack.getThirdPicture());
            OrderExchangeBO exchangeBO  = SoaConnectionFactory.post(request, ConstantsUri.ORDER_BACK, orderBackReq, OrderExchangeBO.class);
            mav.addObject("data",exchangeBO);
        } catch (SoaException e) {
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
     * 退换货明细
     * @return
     */
    @RequestMapping(value = "/exchange/{id}/{orderNo}",method = RequestMethod.GET)
    public ModelAndView exchange(@PathVariable(value = "id")String id,
                                 @PathVariable(value = "orderNo")String orderNo,
                                 HttpServletRequest request,HttpSession session){
        try {
            ModelAndView mav = new ModelAndView("userinfo/order_back_detail");
            OrderExchangeBO exchangeBO = SoaConnectionFactory.getRestful(request, ConstantsUri.ORDER_BACK_ID, null, OrderExchangeBO.class, id);
            mav.addObject("exchange",exchangeBO.getData());
            DictRes exchange_status = RedisCacheService.getRedisDictRes(request,getInfoService,"exchange_status");
            DictRes exchange_reason = RedisCacheService.getRedisDictRes(request,getInfoService,"exchange_reason");

            //查询并添加订单状态数据字典
            mav.addObject("exchangeStatus", exchange_status.getDataList());
            //查询并添加退换货原因数据字典
            mav.addObject("exchangeReason", exchange_reason.getDataList());

            OrderLogReq orderLogReq = new OrderLogReq();
            orderLogReq.setOrderNo(orderNo);
            orderLogReq.setLogType("1");//退单日志
            orderLogReq.setExchangeId(id);
            OrderLogResp orderLogResp = SoaConnectionFactory.get(request, ConstantsUri.ORDER_LOG, orderLogReq, OrderLogResp.class);
            mav.addObject("orderlogs",orderLogResp.getDataList());

            OrderDetailResp orderDetailResp = SoaConnectionFactory.getRestful(request, ConstantsUri.ORDER_DETAIL, null, OrderDetailResp.class, orderNo);
            mav.addObject("orderBO",orderDetailResp.getData());
            return mav;
        } catch (SoaException e) {
            logger.debug("异常:"+e.getMessage());
            ModelAndView mav= new ModelAndView("soaerror");
            return mav;
        }
    }

    private AddressBo getDefaultAddress(HttpServletRequest request, HttpSession session) throws SoaException {
        UserBo user = getInfoService.getUserBo(request);
        AddressRes addressRes = SoaConnectionFactory.getRestful(request, ConstantsUri.USER_ADDRESS,null, AddressRes.class, user.getId());
        List<AddressBo> dataList = addressRes.getDataList();
        AddressBo defaultAddress = null;
        if(dataList!= null && !dataList.isEmpty()){
            for (AddressBo address : dataList){
                if(address.getIsDefault()!= null && address.getIsDefault()){
                    defaultAddress = address;
                    break;
                }
            }
            if(defaultAddress == null){
                defaultAddress = dataList.get(0);
            }
        }
        return defaultAddress;
    }

    /**
     * 退换货确认信息
     * @return
     */
    @RequestMapping(value = "/exchangeConfirm/{id}",method = RequestMethod.GET)
    public ModelAndView replaceConfirm(@PathVariable(value = "id")String id,
                                       HttpServletRequest request){
        try {
            ModelAndView mav = new ModelAndView("userinfo/order_back_confirm");
            OrderExchangeBO exchangeBO = SoaConnectionFactory.getRestful(request, ConstantsUri.ORDER_BACK_ID, null, OrderExchangeBO.class, id);
            mav.addObject("exchange",exchangeBO.getData());

            DictRes receive_info = RedisCacheService.getRedisDictRes(request,getInfoService,"receive_info");
            DictRes exchange_status = RedisCacheService.getRedisDictRes(request,getInfoService,"exchange_status");
            DictRes exchange_reason = RedisCacheService.getRedisDictRes(request,getInfoService,"exchange_reason");
            //查询并添加订单状态数据字典
            mav.addObject("receiveInfos", receive_info.getDataList());
            mav.addObject("exchangeStatus", exchange_status.getDataList());
            mav.addObject("exchangeReason",exchange_reason.getDataList());
            //mav.addObject("path", SpringCtxHolder.getProperty("imagedomain"));
            return mav;
        } catch (SoaException e) {
            logger.debug("异常:"+e.getMessage());
            ModelAndView mav= new ModelAndView("soaerror");
            return mav;
        }
    }

    /**
     * 退换货确认收货
     * @return
     */
    @RequestMapping(value = "/receive/{id}",method = RequestMethod.POST)
    public ModelAndView replace(@PathVariable(value = "id")String id,
                                HttpServletRequest request){
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(),null);
        try {
            OrderExchangeBO exchangeBO = SoaConnectionFactory.put(request, ConstantsUri.ORDER_BACK_RECEIVE, null, OrderExchangeBO.class, id);
            mav.addObject("data",exchangeBO);
        } catch (SoaException e) {
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

    public Map<String, String> FILE_TYPE_MAP = new HashMap<String, String>();
    public String getFileTypeByStream(byte[] b) {
        String filetypeHex = String.valueOf(getFileHexString(b));
        System.out.println(filetypeHex);
        Iterator<Map.Entry<String, String>> entryiterator = FILE_TYPE_MAP
                .entrySet().iterator();
        while (entryiterator.hasNext()) {
            Map.Entry<String, String> entry = entryiterator.next();
            String fileTypeHexValue = entry.getValue().toUpperCase();
            if (filetypeHex.toUpperCase().startsWith(fileTypeHexValue)) {
                return entry.getKey();
            }
        }
        return null;
    }

    public void getPicFileType() {
        FILE_TYPE_MAP.put("jpg", "FFD8FF"); // JPEG (jpg)
        FILE_TYPE_MAP.put("png", "89504E47"); // PNG (png)
        FILE_TYPE_MAP.put("bmp","424D");
        FILE_TYPE_MAP.put("bmp","89504E47");
    }

    public String getFileHexString(byte[] b) {
        StringBuilder stringBuilder = new StringBuilder();
        if (b == null || b.length <= 0) {
            return null;
        }
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 上传退换货图片
     * @param multipartRequest
     * @param exchangeType
     * @param orderNo
     * @param reason
     * @param session
     * @param request
     * @return
     */
    @RequestMapping(value = "/upload.html", method = RequestMethod.POST)
    public ModelAndView upload(MultipartHttpServletRequest multipartRequest
            , @RequestParam(value = "exchangeType", required = false) String exchangeType
            ,@RequestParam(value = "orderNo", required = false) String orderNo
            ,@RequestParam(value = "reason", required = false) String reason
            ,@RequestParam(value = "userRemark", required = false) String userRemark
            , HttpSession session, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            UserBo userbo = getInfoService.getUserBo(request);
            if(isNull(exchangeType)&&isNull(orderNo) &&isNull(reason)){
                MultipartFile file =  multipartRequest.getFile("first");
                MultipartFile file1 =  multipartRequest.getFile("second");
                MultipartFile file2 =  multipartRequest.getFile("third");
                CmsFileUploadDto cmsFileUploadDto = new CmsFileUploadDto();
                getPicFileType();
                FjDto fjDto = new FjDto();
                fjDto.setFileName(file.getOriginalFilename());
                try {
                    byte[] img = file.getBytes();
                    if(file.getSize()>1024*200){
                        BaseResponse base = new BaseResponse("300", "文件大小不能大于200k");
                        mav.addObject("data",base);
                        return mav;
                    }
                    if (getFileTypeByStream(img) == null) {
                        BaseResponse base =new BaseResponse("300", "上传文件类型错误!");
                        mav.addObject("data",base);
                        return mav;
                    }
                    byte[] img1 = file1.getBytes();
                    if(file1.getSize()>1024*200){
                        BaseResponse base = new BaseResponse("300", "文件大小不能大于200k");
                        mav.addObject("data",base);
                        return mav;
                    }
                    if (getFileTypeByStream(img1) == null) {
                        BaseResponse base =new BaseResponse("300", "上传文件类型错误!");
                        mav.addObject("data",base);
                        return mav;
                    }
                    byte[] img2 = file2.getBytes();
                    if(file1.getSize()>1024*200){
                        BaseResponse base = new BaseResponse("300", "文件大小不能大于200k");
                        mav.addObject("data",base);
                        return mav;
                    }
                    if (getFileTypeByStream(img2) == null) {
                        BaseResponse base =new BaseResponse("300", "上传文件类型错误!");
                        mav.addObject("data",base);
                        return mav;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fjDto.setContent(FileOperateUtil.fileBytesToList(file.getBytes()));
                    cmsFileUploadDto.setDirectory(userbo.getId());
                    cmsFileUploadDto.getFjBo().add(fjDto);
                    fjDto = new FjDto();
                    fjDto.setFileName(file1.getOriginalFilename());
                    fjDto.setContent(FileOperateUtil.fileBytesToList(file1.getBytes()));
                    cmsFileUploadDto.getFjBo().add(fjDto);
                    fjDto = new FjDto();
                    fjDto.setFileName(file2.getOriginalFilename());
                    fjDto.setContent(FileOperateUtil.fileBytesToList(file2.getBytes()));
                    cmsFileUploadDto.getFjBo().add(fjDto);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                FileListDto fileListDto = SoaConnectionFactory.post(multipartRequest, ConstantsUri.SYSFILEUP, cmsFileUploadDto, FileListDto.class);
                OrderBack orderBack = new OrderBack();
                orderBack.setType(exchangeType);
                orderBack.setOrderNo(orderNo);
                orderBack.setReason(reason);
                orderBack.setUserRemark(userRemark);
                orderBack.setFirstPicture("/images" + fileListDto.getDataList().get(0).getFilePath());
                orderBack.setSecondPicture("/images" + fileListDto.getDataList().get(1).getFilePath());
                orderBack.setThirdPicture("/images" + fileListDto.getDataList().get(2).getFilePath());

                if(fileListDto.getDataList().size()>0){
                    BaseResponse exchangeBO  = SoaConnectionFactory.post(request, ConstantsUri.ORDER_BACK, orderBack, OrderExchangeBO.class);
                    if(exchangeBO.getCode()=="2000"){
                        BaseResponse base =new BaseResponse("200", "已经申请，请耐心等待审核");
                        mav.addObject("data",base);
                        return mav;
                    }else{
                        mav.addObject("data",exchangeBO);
                        return mav;
                    }

                }else{
                    BaseResponse base =new BaseResponse("300", "上传失败");
                    mav.addObject("data",base);
                    return mav;
                }
            }
        } catch (SoaException e) {
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
