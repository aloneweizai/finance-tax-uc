package com.abc.controller.userinfo;

import com.abc.application.SpringCtxHolder;
import com.abc.bean.userinfo.*;
import com.abc.cache.RedisCacheService;
import com.abc.common.exception.SoaException;
import com.abc.common.soa.SoaConnectionFactory;
import com.abc.common.soa.response.BaseResponse;
import com.abc.controller.BaseController;
import com.abc.soa.ConstantsUri;
import com.abc.soa.request.userinfo.OrderConfirm;
import com.abc.soa.request.userinfo.OrderListReq;
import com.abc.soa.response.DictRes;
import com.abc.soa.response.UserBo;
import com.abc.soa.response.userinfo.OrderDetailResp;
import com.abc.soa.response.userinfo.OrderListResp;
import com.alibaba.fastjson.JSON;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 我的订单
 * Created by zlk on 2017-07-17.
 */
@Controller
@RequestMapping(value = "/userinfo")
public class UserOrderController extends BaseController {
    private final static Logger logger = LoggerFactory.getLogger(UserOrderController.class);

    /**
     * 我的订单
     * @param page
     * @param session
     * @param request
     * @param model
     * @return
     */
    @GetMapping("/order.php")
    public String list(@RequestParam(value = "index", required = false,defaultValue = "1") String index,
                       @RequestParam(value = "size",required = false,defaultValue = "7")Integer size,
                       @RequestParam(value = "type", defaultValue = "0")String type,
                       HttpSession session, HttpServletRequest request,Model model){
        try {
            UserBo userBo = getInfoService.getUserBo(request);
            String id = userBo.getId();
            String username = userBo.getUsername();
            OrderListReq orderListReq =  new OrderListReq();
            orderListReq.setName(username);
            orderListReq.setPage(Integer.valueOf(index));
            orderListReq.setSize(size);
            orderListReq.setUserId(id);
            orderListReq.setGoodsType(null);
            orderListReq.setStartTime(null);
            orderListReq.setEndTime(null);
            if("0".equals(type)){
                orderListReq.setStatus(null);//全部
            }else if("1".equals(type)){
                orderListReq.setStatus("2,3,8");//待付款
            }else if("2".equals(type)){
                orderListReq.setStatus("4,5");//待收货
            }else if("3".equals(type)){
                orderListReq.setStatus("6");//已完成
            }else if("4".equals(type)){
                orderListReq.setStatus("7,9");//已取消
            }
            model.addAttribute("type", type);
            OrderListResp orders = null;
            if("0".equals(type)) {
                orders = SoaConnectionFactory.get(request, ConstantsUri.ORDER_ALL, orderListReq, OrderListResp.class);
            }else{
                orders = SoaConnectionFactory.get(request, ConstantsUri.ORDER_LIST_STATUS, orderListReq, OrderListResp.class);
            }
            List<OrderBO> orderList = JSON.parseArray(orders.getDataList(),OrderBO.class);
            model.addAttribute("orders",orderList);

            DictRes orderStatus = RedisCacheService.getRedisDictRes(request,getInfoService,"orderStatus");
            //查询并添加订单状态数据字典
            model.addAttribute("orderStatus", orderStatus.getDataList());
            if(orders!=null && orders.getTotal()>0){
                model.addAttribute("page",index);
                model.addAttribute("count", orders.getTotal());
            }else{
                model.addAttribute("page",0);
                model.addAttribute("count",0);
            }
            String imagedomain= SpringCtxHolder.getProperty("imagedomain");
            model.addAttribute("picurl",imagedomain);
            return "userinfo/order";
        } catch (SoaException e) {
            logger.debug("异常:"+e.getMessage());
            String cswdomain= SpringCtxHolder.getProperty("cswdomain");
            model.addAttribute("cswurl",cswdomain);
            return "soaerror";
        }
    }

    /**
     * 查询订单详情
     * @param id
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/orderList/{id}",method = RequestMethod.GET)
    public ModelAndView orderDetail(@PathVariable(value = "id")String id,HttpServletRequest request,HttpSession session){
        try {
            ModelAndView mav = new ModelAndView("userinfo/order_cancel");
            OrderDetailResp orderDetailResp = SoaConnectionFactory.getRestful(request, ConstantsUri.ORDER_DETAIL, null, OrderDetailResp.class,id);
            String orderNo = null;
            String orderStatus = null;
            if(orderDetailResp != null && orderDetailResp.getData()!=null) {
                orderNo = orderDetailResp.getData().getOrderNo();
                orderStatus= orderDetailResp.getData().getOrderStatus();
            }
            mav.addObject("orderNo",orderNo);
            mav.addObject("orderStatus",orderStatus);

            return mav;
        } catch (SoaException e) {
            logger.debug("异常:"+e.getMessage());
            ModelAndView mav= new ModelAndView("soaerror");
            return mav;
        }
    }

    @RequestMapping(value = "/orderList/{id}/{status}",method = RequestMethod.GET)
    public ModelAndView toCancel(@PathVariable(value = "id")String id,@PathVariable(value = "status")String status){
        try {
            ModelAndView mav = new ModelAndView("userinfo/order_cancel");
            mav.addObject("orderNo",id);
            mav.addObject("orderStatus",status);
            return mav;
        } catch (Exception e) {
            logger.debug("异常:"+e.getMessage());
            ModelAndView mav= new ModelAndView("soaerror");
            return mav;
        }
    }

    /**
     * 取消订单
     * @param cancelBO
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/orderList/cancel",method = RequestMethod.POST)
    public ModelAndView cancel(@RequestBody OrderCancelBO cancelBO,
                               HttpServletRequest request,HttpSession session){
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            UserBo userBo = getInfoService.getUserBo(request);
            cancelBO.setUserId(userBo.getId());
            OrderBO cancelOrder = SoaConnectionFactory.post(request, ConstantsUri.ORDER_CANCEL, cancelBO, OrderBO.class);
            mav.addObject("data",cancelOrder);
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
     * 删除订单
     * @param orderNo
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "/orderList/delete/{orderNo}",method = RequestMethod.POST)
    public ModelAndView delete(@PathVariable(value = "orderNo")String orderNo,
                               HttpServletRequest request,HttpSession session){
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(), null);
        try {
            UserBo userBo = getInfoService.getUserBo(request);
            OrderBO cancelOrder = SoaConnectionFactory.delete(request, ConstantsUri.ORDER_DELETE, null, OrderBO.class,userBo.getId(),orderNo);
            mav.addObject("data",cancelOrder);
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
     * 确认收货
     * @param orderNo
     * @return
     */
    @RequestMapping(value = "/orderList/confirm/{orderNo}",method = RequestMethod.POST)
    public ModelAndView confirmOrder(@PathVariable(value = "orderNo") String orderNo,HttpServletRequest request,HttpSession session) {
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(),null);
        try {
            UserBo userBo = getInfoService.getUserBo(request);
            OrderConfirm orderConfirm = new OrderConfirm();
            orderConfirm.setOrderNo(orderNo);
            orderConfirm.setUserId(userBo.getId());
            BaseResponse cancelOrder = SoaConnectionFactory.post(request, ConstantsUri.ORDER_CONFIRM, null, BaseResponse.class,orderNo,userBo.getId());
            mav.addObject("data",cancelOrder);
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
     * 根据日期查询订单
     * @param start
     * @param end
     * @return
     */
    @RequestMapping(value = "/orderList/date",method = RequestMethod.GET)
    public ModelAndView confirmOrder(@RequestParam(value = "index", required = false,defaultValue = "1") String index,
                                     @RequestParam(value = "size",required = false,defaultValue = "20")Integer size,
                                        @RequestParam(value = "start",required = false)String start,
                                     @RequestParam(value = "end",required = false)String end,
                                     @RequestParam(value = "type", defaultValue = "0")String type,
                                     HttpServletRequest request,HttpSession session) {
        ModelAndView mav = new ModelAndView("userinfo/order");
        try {
            UserBo userBo = getInfoService.getUserBo(request);
            OrderListReq orderListReq =  new OrderListReq();
            orderListReq.setUserId(userBo.getId());
            orderListReq.setPage(Integer.valueOf(index));
            orderListReq.setSize(size);
            orderListReq.setGoodsType(null);
            orderListReq.setStartTime(start);
            orderListReq.setEndTime(end);
            if("0".equals(type)){
                orderListReq.setStatus(null);//全部
            }else if("1".equals(type)){
                orderListReq.setStatus("2,3,8");//待付款
            }else if("2".equals(type)){
                orderListReq.setStatus("4,5");//待收货
            }else if("3".equals(type)){
                orderListReq.setStatus("6");//已完成
            }else if("4".equals(type)){
                orderListReq.setStatus("7,9");//已取消
            }
            mav.addObject("type", type);
            OrderListResp orders = null;
            if("0".equals(type)) {
                orders = SoaConnectionFactory.get(request, ConstantsUri.ORDER_ALL, orderListReq, OrderListResp.class);
            }else{
                orders = SoaConnectionFactory.get(request, ConstantsUri.ORDER_LIST_STATUS, orderListReq, OrderListResp.class);
            }
            List<OrderBO> orderList = JSON.parseArray(orders.getDataList(),OrderBO.class);
            mav.addObject("orders", orderList);
            mav.addObject("start", start);
            mav.addObject("end", end);
            DictRes orderStatus = RedisCacheService.getRedisDictRes(request,getInfoService,"orderStatus");
            //查询并添加订单状态数据字典
            mav.addObject("orderStatus", orderStatus.getDataList());
            if(orders!=null && orders.getTotal()>0){
                mav.addObject("page", index);
                mav.addObject("count", orders.getTotal());
            }else{
                mav.addObject("page", 0);
                mav.addObject("count",0);
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

    /**
     * 课程查询是否购买
     * @param request
     * @param goodsId
     * @return
     */
    @GetMapping(value = "/ketang")
    public ModelAndView ketang(HttpServletRequest request,@RequestParam(value = "goodsId")String goodsId){
        ModelAndView mav = new ModelAndView(new MappingJackson2JsonView(),null);
        try{
            UserBo userBo = getInfoService.getUserBo(request);
            Map<String,String> map=new HashMap<String,String>();
            map.put("goodsId",goodsId);
            map.put("userId",userBo.getId());
            OrderDetailResp orderDetailResp = SoaConnectionFactory.get(request,ConstantsUri.UC_BOOL_GOUMAI,map,OrderDetailResp.class);
            if(orderDetailResp.getData()!=null&&"6".equals(orderDetailResp.getData().getOrderStatus())){
                mav.addObject("kechen",true);
            }else{
                mav.addObject("kechen",false);
            }
        }catch (SoaException e){
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
