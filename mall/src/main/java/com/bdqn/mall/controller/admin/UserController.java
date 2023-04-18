package com.bdqn.mall.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bdqn.mall.controller.BaseController;
import com.bdqn.mall.entity.Address;
import com.bdqn.mall.entity.Product;
import com.bdqn.mall.entity.ProductOrderItem;
import com.bdqn.mall.entity.User;
import com.bdqn.mall.service.*;
import com.bdqn.mall.util.OrderUtil;
import com.bdqn.mall.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 后台管理-用户页
 */
@Controller
public class UserController extends BaseController {
    @Resource
    private UserService userService;
    @Resource
    private AddressService addressService;
    @Resource
    private ReviewService reviewService;
    @Resource
    private ProductOrderItemService productOrderItemService;
    @Resource
    private ProductService productService;
    @Resource
    private ProductImageService productImageService;

    //转到后台管理-用户页-ajax
    @RequestMapping(value = "admin/user", method = RequestMethod.GET)
    public String goUserManagePage(HttpSession session, Map<String, Object> map){
        logger.info("检查管理员权限");
        Object adminId = checkAdmin(session);
        if(adminId == null){
            return "admin/include/loginMessage";
        }

        logger.info("获取前十条用户信息");
        PageUtil pageUtil = new PageUtil(0, 10);
        List<User> userList = userService.getList(null, null, pageUtil);
        map.put("userList", userList);
        logger.info("获取用户总数量");
        Integer userCount = userService.getTotal(null);
        map.put("userCount", userCount);
        logger.info("获取分页信息");
        pageUtil.setTotal(userCount);
        map.put("pageUtil", pageUtil);

        logger.info("转到后台管理-用户页-ajax方式");
        return "admin/userManagePage";
    }


    //转到后台管理-用户详情页-ajax
    @RequestMapping(value = "admin/user/{uid}", method = RequestMethod.GET)
    public String getUserById(HttpSession session, Map<String,Object> map, @PathVariable Integer uid/* 用户ID */){
        logger.info("检查管理员权限");
        Object adminId = checkAdmin(session);
        if(adminId == null){
            return "admin/include/loginMessage";
        }

        logger.info("获取user_id为{}的用户信息",uid);
        User user = userService.get(uid);
        logger.info("获取用户详情-所在地地址信息");
        Address address = addressService.get(user.getUserAddress().getAddressAreaId());
        Stack<String> addressStack = new Stack<>();
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
        logger.info("所在地地址字符串：{}", builder);
        Address address1 =new Address();
        address1.setAddressName(builder.toString());
        user.setUserAddress(address1);

        logger.info("获取用户详情-家乡地址信息");
        address = addressService.get(user.getUserHomePlace().getAddressAreaId());
        //最后一级地址
        addressStack.push(address.getAddressName() + " ");
        //如果不是第一级地址
        while (!address.getAddressAreaId().equals(address.getAddressRegionId().getAddressAreaId())) {
            address = addressService.get(address.getAddressRegionId().getAddressAreaId());
            addressStack.push(address.getAddressName() + " ");
        }
        builder = new StringBuilder();
        while (!addressStack.empty()) {
            builder.append(addressStack.pop());
        }
        logger.info("家乡地址字符串：{}", builder);
        Address address2 = new Address();
        address2.setAddressName(builder.toString());
        user.setUserHomePlace(address2);

        logger.info("获取用户详情-购物车订单项信息");
        List<ProductOrderItem> productOrderItemList = productOrderItemService.getListByUserId(user.getUserId(), null);
        if (productOrderItemList != null) {
            logger.info("获取用户详情-购物车订单项对应的产品信息");
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
        user.setProductOrderItemList(productOrderItemList);

        if (user.getUserRealName() != null) {
            logger.info("用户隐私加密");
            user.setUserRealName(user.getUserRealName().substring(0, 1) + "*");
        } else {
            user.setUserRealName("未命名");
        }

        map.put("user",user);

        logger.info("转到后台管理-用户详情页-ajax方式");
        return "admin/include/userDetails";
    }

    //按条件查询用户-ajax
    @ResponseBody
    @RequestMapping(value = "admin/user/{index}/{count}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String getUserBySearch(@RequestParam(required = false) String userName/* 用户名称 */,
                                  @RequestParam(required = false) Byte[] user_gender_array/* 用户性别数组 */,
                                  @RequestParam(required = false) String orderBy/* 排序字段 */,
                                  @RequestParam(required = false,defaultValue = "true") Boolean isDesc/* 是否倒序 */,
                                  @PathVariable Integer index/* 页数 */,
                                  @PathVariable Integer count/* 行数 */) throws UnsupportedEncodingException {
        //移除不必要条件
        Byte gender = null;
        if (user_gender_array != null && user_gender_array.length == 1) {
            gender = user_gender_array[0];
        }

        if (userName != null) {
            //如果为非空字符串则解决中文乱码：URLDecoder.decode(String,"UTF-8");
            userName = userName.equals("") ? null : URLDecoder.decode(userName, "UTF-8");
        }
        if(orderBy != null && orderBy.equals("")){
            orderBy = null;
        }
        //封装查询条件
        User user = new User();
        user.setUserName(userName);
        user.setUserGender(gender);

        OrderUtil orderUtil = null;
        if (orderBy != null) {
            logger.info("根据{}排序，是否倒序:{}",orderBy,isDesc);
            orderUtil = new OrderUtil(orderBy, isDesc);
        }

        JSONObject object = new JSONObject();
        logger.info("按条件获取第{}页的{}条用户", index + 1, count);
        PageUtil pageUtil = new PageUtil(index, count);
        List<User> userList = userService.getList(user, orderUtil, pageUtil);
        object.put("userList", JSONArray.parseArray(JSON.toJSONString(userList)));
        logger.info("按条件获取用户总数量");
        Integer userCount = userService.getTotal(user);
        object.put("userCount", userCount);
        logger.info("获取分页信息");
        pageUtil.setTotal(userCount);
        object.put("totalPage", pageUtil.getTotalPage());
        object.put("pageUtil", pageUtil);

        return object.toJSONString();
    }
}
