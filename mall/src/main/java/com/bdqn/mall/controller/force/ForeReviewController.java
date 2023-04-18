package com.bdqn.mall.controller.force;

import com.alibaba.fastjson.JSONObject;
import com.bdqn.mall.controller.BaseController;
import com.bdqn.mall.entity.*;
import com.bdqn.mall.service.*;
import com.bdqn.mall.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
public class ForeReviewController extends BaseController {
    @Resource
    private ReviewService reviewService;
    @Resource
    private UserService userService;
    @Resource
    private ProductOrderItemService productOrderItemService;
    @Resource
    private ProductOrderService productOrderService;
    @Resource
    private ProductService productService;
    @Resource
    private ProductImageService productImageService;

    //转到前台Mall-评论添加页
    @RequestMapping(value = "review/{orderItem_id}", method = RequestMethod.GET)
    public String goToPage(HttpSession session, Map<String, Object> map,
                           @PathVariable("orderItem_id") Integer orderItem_id) {
        logger.info("检查用户是否登录");
        Object userId = checkUser(session);
        User user;
        if (userId != null) {
            logger.info("获取用户信息");
            user = userService.get(Integer.parseInt(userId.toString()));
            map.put("user", user);
        } else {
            return "redirect:/login";
        }
        logger.info("获取订单项信息");
        ProductOrderItem orderItem = productOrderItemService.get(orderItem_id);
        if (orderItem == null) {
            logger.warn("订单项不存在，返回订单页");
            return "redirect:/order/0/10";
        }
        if (!orderItem.getProductOrderItemUser().getUserId().equals(userId)) {
            logger.warn("订单项与用户不匹配，返回订单页");
            return "redirect:/order/0/10";
        }
        if (orderItem.getProductOrderItemOrder() == null) {
            logger.warn("订单项状态有误，返回订单页");
            return "redirect:/order/0/10";
        }
        ProductOrder order = productOrderService.get(orderItem.getProductOrderItemOrder().getProductOrderId());
        if (order == null || order.getProductOrderStatus() != 3) {
            logger.warn("订单项状态有误，返回订单页");
            return "redirect:/order/0/10";
        }
        if (reviewService.getTotalByOrderItemId(orderItem_id) > 0) {
            logger.warn("订单项所属商品已被评价，返回订单页");
            return "redirect:/order/0/10";
        }
        logger.info("获取订单项所属产品信息和产品评论信息");
        Product product = productService.get(orderItem.getProductOrderItemProduct().getProductId());
        product.setProductReviewCount(reviewService.getTotalByProductId(product.getProductId()));
        product.setSingleProductImageList(productImageService.getList(product.getProductId(), (byte) 0, new PageUtil(0, 1)));
        orderItem.setProductOrderItemProduct(product);

        map.put("orderItem", orderItem);

        logger.info("转到前台Mall-评论添加页");
        return "fore/addReview";
    }

    //添加一条评论
    @RequestMapping(value = "review", method = RequestMethod.POST)
    public String addReview(HttpSession session, Map<String, Object> map,
                            @RequestParam Integer orderItem_id,
                            @RequestParam String reviewContent) throws UnsupportedEncodingException {
        logger.info("检查用户是否登录");
        Object userId = checkUser(session);
        User user;
        if (userId != null) {
            logger.info("获取用户信息");
            user = userService.get(Integer.parseInt(userId.toString()));
            map.put("user", user);
        } else {
            return "redirect:/login";
        }
        logger.info("获取订单项信息");
        ProductOrderItem orderItem = productOrderItemService.get(orderItem_id);
        if (orderItem == null) {
            logger.warn("订单项不存在，返回订单页");
            return "redirect:/order/0/10";
        }
        if (!orderItem.getProductOrderItemUser().getUserId().equals(userId)) {
            logger.warn("订单项与用户不匹配，返回订单页");
            return "redirect:/order/0/10";
        }
        if (orderItem.getProductOrderItemOrder() == null) {
            logger.warn("订单项状态有误，返回订单页");
            return "redirect:/order/0/10";
        }
        ProductOrder order = productOrderService.get(orderItem.getProductOrderItemOrder().getProductOrderId());
        if (order == null || order.getProductOrderStatus() != 3) {
            logger.warn("订单项状态有误，返回订单页");
            return "redirect:/order/0/10";
        }
        if (reviewService.getTotalByOrderItemId(orderItem_id) > 0) {
            logger.warn("订单项所属商品已被评价，返回订单页");
            return "redirect:/order/0/10";
        }
        logger.info("整合评论信息");
        Review review = new Review();
        review.setReviewProduct(orderItem.getProductOrderItemProduct());
        review.setReviewContent(reviewContent);
        review.setReviewCreateDate(new Date());
        review.setReviewUser(user);
        review.setReviewOrderItem(orderItem);
        logger.info("添加评论");
        Boolean yn = reviewService.add(review);
        if (!yn) {
            throw new RuntimeException();
        }

        return "redirect:/product/" + orderItem.getProductOrderItemProduct().getProductId();
    }

    //获取产品评论信息-ajax
    @ResponseBody
    @RequestMapping(value = "review", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getReviewInfo(@RequestParam("productId") Integer productId,
                                @RequestParam("index") Integer index/* 页数 */,
                                @RequestParam("count") Integer count/* 行数*/) {
        logger.info("获取产品评论信息");
        List<Review> reviewList = reviewService.getListByProductId(productId, new PageUtil(index, 10));
        if (reviewList != null) {
            for (Review review : reviewList) {
                review.setReviewUser(userService.get(review.getReviewUser().getUserId()));
            }
        }
        Integer total = reviewService.getTotalByProductId(productId);

        JSONObject object = new JSONObject();
        object.put("reviewList", reviewList);
        object.put("pageUtil", new PageUtil().setTotal(total).setIndex(index).setCount(count));

        return object.toJSONString();
    }
}
