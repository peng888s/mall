package com.bdqn.mall.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bdqn.mall.controller.BaseController;
import com.bdqn.mall.entity.Address;
import com.bdqn.mall.entity.Product;
import com.bdqn.mall.entity.ProductOrder;
import com.bdqn.mall.entity.ProductOrderItem;
import com.bdqn.mall.service.*;
import com.bdqn.mall.util.OrderUtil;
import com.bdqn.mall.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 后台管理-订单页
 */
@Controller
public class OrderController extends BaseController {
    @Resource
    private ProductOrderService productOrderService;
    @Resource
    private AddressService addressService;
    @Resource
    private UserService userService;
    @Resource
    private ProductOrderItemService productOrderItemService;
    @Resource
    private ProductService productService;
    @Resource
    private ProductImageService productImageService;
    @Resource
    private LastIDService lastIDService;

    //转到后台管理-订单页-ajax
    @RequestMapping(value = "admin/order", method = RequestMethod.GET)
    public String goToPage(HttpSession session, Map<String, Object> map){
        logger.info("检查管理员权限");
        Object adminId = checkAdmin(session);
        if(adminId == null){
            return "admin/include/loginMessage";
        }

        logger.info("获取前10条订单列表");
        PageUtil pageUtil = new PageUtil(0, 10);
        List<ProductOrder> productOrderList = productOrderService.getList(null, null, new OrderUtil("productOrderId", true), pageUtil);
        map.put("productOrderList",productOrderList);
        logger.info("获取订单总数量");
        Integer productOrderCount = productOrderService.getTotal(null, null);
        map.put("productOrderCount", productOrderCount);
        logger.info("获取分页信息");
        pageUtil.setTotal(productOrderCount);
        map.put("pageUtil", pageUtil);

        logger.info("转到后台管理-订单页-ajax方式");
        return "admin/orderManagePage";
    }

    //转到后台管理-订单详情页-ajax
    @RequestMapping(value = "admin/order/{oid}", method = RequestMethod.GET)
    public String goToDetailsPage(HttpSession session, Map<String, Object> map, @PathVariable Integer oid/* 订单ID */) {
        logger.info("检查管理员权限");
        Object adminId = checkAdmin(session);
        if(adminId == null){
            return "admin/include/loginMessage";
        }

        logger.info("获取order_id为{}的订单信息",oid);
        ProductOrder order = productOrderService.get(oid);
        logger.info("获取订单详情-地址信息");
        Address address = addressService.get(order.getProductOrderAddress().getAddressAreaId());
        Stack<String> addressStack = new Stack<>();
        //详细地址
        addressStack.push(order.getProductOrderDetailAddress());
        //最后一级地址
        addressStack.push(address.getAddressName() + " ");
        //如果不是第一级地址
        while (!address.getAddressAreaId().equals(address.getAddressRegionId().getAddressAreaId())) {
            address = addressService.get(address.getAddressRegionId().getAddressAreaId());
            addressStack.push(address.getAddressName() + " ");
        }
        StringBuilder builder = new StringBuilder();
        while (!addressStack.empty()) {
            builder.append(addressStack.pop());
        }
        logger.warn("订单地址字符串：{}", builder);
        order.setProductOrderDetailAddress(builder.toString());
        logger.info("获取订单详情-用户信息");
        order.setProductOrderUser(userService.get(order.getProductOrderUser().getUserId()));
        logger.info("获取订单详情-订单项信息");
        List<ProductOrderItem> productOrderItemList = productOrderItemService.getListByOrderId(oid, null);
        if (productOrderItemList != null) {
            logger.info("获取订单详情-订单项对应的产品信息");
            for (ProductOrderItem productOrderItem : productOrderItemList) {
                Integer productId = productOrderItem.getProductOrderItemProduct().getProductId();
                logger.warn("获取产品ID为{}的产品信息", productId);
                Product product = productService.get(productId);
                if (product != null) {
                    logger.warn("获取产品ID为{}的第一张预览图片信息", productId);
                    product.setSingleProductImageList(productImageService.getList(productId, (byte) 0, new PageUtil(0, 1)));
                }
                productOrderItem.setProductOrderItemProduct(product);
            }
        }
        order.setProductOrderItemList(productOrderItemList);
        map.put("order", order);
        logger.info("转到后台管理-订单详情页-ajax方式");
        return "admin/include/orderDetails";
    }

    //更新订单信息-ajax
    @ResponseBody
    @RequestMapping(value = "admin/order/{orderId}", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    public String updateOrder(@PathVariable("orderId") String orderId) {
        JSONObject jsonObject = new JSONObject();
        logger.info("整合订单信息");
        ProductOrder productOrder = new ProductOrder(Integer.valueOf(orderId),(byte) 2,new Date());
        logger.info("更新订单信息，订单ID值为：{}", orderId);
        boolean yn = productOrderService.update(productOrder);
        if (yn) {
            logger.info("更新成功！");
            jsonObject.put("success", true);
        } else {
            logger.info("更新失败！事务回滚");
            jsonObject.put("success", false);
            throw new RuntimeException();
        }
        jsonObject.put("orderId", orderId);
        return jsonObject.toJSONString();
    }

    //按条件查询订单-ajax
    @ResponseBody
    @RequestMapping(value = "admin/order/{index}/{count}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String getOrderBySearch(@RequestParam(required = false) String productOrderCode/* 订单号 */,
                                   @RequestParam(required = false) String productOrderPost/* 订单邮政编码 */,
                                   @RequestParam(required = false) Byte[] productOrderStatusArray/* 订单状态数组 */,
                                   @RequestParam(required = false) String orderBy/* 排序字段 */,
                                   @RequestParam(required = false,defaultValue = "true") Boolean isDesc/* 是否倒序 */,
                                   @PathVariable Integer index/* 页数 */,
                                   @PathVariable Integer count/* 行数 */){
        //移除不必要条件
        if (productOrderStatusArray != null && (productOrderStatusArray.length <= 0 || productOrderStatusArray.length >=5)) {
            productOrderStatusArray = null;
        }
        if (productOrderCode != null){
            productOrderCode = productOrderCode.equals("") ? null : productOrderCode;
        }
        if(productOrderPost != null){
            productOrderPost = productOrderPost.equals("") ? null : productOrderPost;
        }
        if(orderBy != null && orderBy.equals("")){
            orderBy = null;
        }
        //封装查询条件
        ProductOrder productOrder = new ProductOrder(productOrderCode, productOrderPost);
        OrderUtil orderUtil = null;
        if (orderBy != null) {
            logger.info("根据{}排序，是否倒序:{}",orderBy,isDesc);
            orderUtil = new OrderUtil(orderBy, isDesc);
        }

        JSONObject object = new JSONObject();
        logger.info("按条件获取第{}页的{}条订单", index + 1, count);
        PageUtil pageUtil = new PageUtil(index, count);
        List<ProductOrder> productOrderList = productOrderService.getList(productOrder, productOrderStatusArray, orderUtil, pageUtil);
        object.put("productOrderList", JSONArray.parseArray(JSON.toJSONString(productOrderList)));
        logger.info("按条件获取订单总数量");
        Integer productOrderCount = productOrderService.getTotal(productOrder, productOrderStatusArray);
        object.put("productOrderCount", productOrderCount);
        logger.info("获取分页信息");
        pageUtil.setTotal(productOrderCount);
        object.put("totalPage", pageUtil.getTotalPage());
        object.put("pageUtil", pageUtil);

        return object.toJSONString();
    }
}
