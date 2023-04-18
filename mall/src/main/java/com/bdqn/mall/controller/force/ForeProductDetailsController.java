package com.bdqn.mall.controller.force;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bdqn.mall.controller.BaseController;
import com.bdqn.mall.entity.*;
import com.bdqn.mall.service.*;
import com.bdqn.mall.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 前台Mall-产品详情页
 */
@Controller
public class ForeProductDetailsController extends BaseController {
    @Resource
    private ProductService productService;
    @Resource
    private UserService userService;
    @Resource
    private ProductImageService productImageService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private PropertyValueService propertyValueService;
    @Resource
    private PropertyService propertyService;
    @Resource
    private ReviewService reviewService;
    @Resource
    private ProductOrderItemService productOrderItemService;

    //转到前台Mall-产品详情页
    @RequestMapping(value = "product/{pid}", method = RequestMethod.GET)
    public String goToPage(HttpSession session, Map<String, Object> map,
                           @PathVariable("pid") String pid /*产品ID*/) {
        logger.info("检查用户是否登录");
        Object userId = checkUser(session);
        if (userId != null) {
            logger.info("获取用户信息");
            User user = userService.get(Integer.parseInt(userId.toString()));
            map.put("user", user);
        }
        logger.info("获取产品ID");
        Integer productId = Integer.parseInt(pid);
        logger.info("获取产品信息");
        Product product = productService.get(productId);
        if (product == null || product.getProductIsEnabled() == 1) {
            return "redirect:/404";
        }
        logger.info("获取产品子信息-分类信息");
        product.setProductCategory(categoryService.get(product.getProductCategory().getCategoryId()));
        logger.info("获取产品子信息-产品图片信息");
        List<ProductImage> productImageList = productImageService.getList(productId, null, null);
        List<ProductImage> singleProductImageList = new ArrayList<>(5);
        List<ProductImage> detailsProductImageList = new ArrayList<>(8);
        for (ProductImage productImage : productImageList) {
            if (productImage.getProductImageType() == 0) {
                singleProductImageList.add(productImage);
            } else {
                detailsProductImageList.add(productImage);
            }
        }
        product.setSingleProductImageList(singleProductImageList);
        product.setDetailProductImageList(detailsProductImageList);
        PropertyValue propertyValue = new PropertyValue();
        propertyValue.setPropertyValueProduct(product);

        logger.info("获取产品子信息-产品属性值信息");
        List<PropertyValue> propertyValueList = propertyValueService.
                getList(propertyValue, null);
        logger.info("获取产品子信息-分类信息对应的属性列表");
        Property property = new Property();
        property.setPropertyCategory(product.getProductCategory());
        List<Property> propertyList = propertyService.
                getList(property, null);
        logger.info("属性列表和属性值列表合并");
        for (Property propertyEach : propertyList) {
            for (PropertyValue propertyValueEach : propertyValueList) {
                if (propertyEach.getPropertyId().equals(propertyValueEach.getPropertyValueProperty().getPropertyId())) {
                    List<PropertyValue> propertyValueItem = new ArrayList<>(1);
                    propertyValueItem.add(propertyValueEach);
                    propertyEach.setPropertyValueList(propertyValueItem);
                    break;
                }
            }
        }
        logger.info("获取产品子信息-产品评论信息");
        product.setReviewList(reviewService.getListByProductId(productId, null));
        if (product.getReviewList() != null) {
            for (Review review : product.getReviewList()) {
                review.setReviewUser(userService.get(review.getReviewUser().getUserId()));
            }
        }

        logger.info("获取产品子信息-销量数和评论数信息");
        product.setProductSaleCount(productOrderItemService.getSaleCountByProductId(productId));
        product.setProductReviewCount(reviewService.getTotalByProductId(productId));

        logger.info("获取猜你喜欢列表");
        Integer categoryId = product.getProductCategory().getCategoryId();
        Product product1 = new Product();
        product1.setProductCategory(new Category(categoryId));
        Integer total = productService.getTotal(product1, new Byte[]{0, 2});
        logger.info("分类ID为{}的产品总数为{}条", categoryId, total);
        //生成随机数
        int i = new Random().nextInt(total);
        if (i + 2 >= total) {
            i = total - 3;
        }
        if (i < 0) {
            i = 0;
        }
        List<Product> loveProductList = productService.
                getList(product1, new Byte[]{0, 2}, null, new PageUtil().setCount(3).setPageStart(i));
        if (loveProductList != null) {
            logger.info("获取产品列表的相应的一张预览图片");
            for (Product loveProduct : loveProductList) {
                loveProduct.setSingleProductImageList(productImageService.getList(loveProduct.getProductId(), (byte) 0, new PageUtil(0, 1)));
            }
        }
        logger.info("获取分类列表");
        List<Category> categoryList = categoryService.getList(null, new PageUtil(0, 3));

        map.put("loveProductList", loveProductList);
        map.put("categoryList", categoryList);
        map.put("propertyList", propertyList);
        map.put("product", product);
        map.put("guessNumber", i);
        map.put("pageUtil", new PageUtil(0, 10).setTotal(product.getProductReviewCount()));
        logger.info("转到前台-产品详情页");
        return "fore/productDetailsPage";
    }

    //按产品ID加载产品评论列表-ajax
    @Deprecated
    @ResponseBody
    @RequestMapping(value = "review/{pid}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String loadProductReviewList(@PathVariable("pid") String pid/*产品ID*/,
                                        @RequestParam Integer index/* 页数 */,
                                        @RequestParam Integer count/* 行数 */) {
        logger.info("获取产品ID");
        Integer productId = Integer.parseInt(pid);
        logger.info("获取产品评论列表");
        List<Review> reviewList = reviewService.getListByProductId(productId, new PageUtil(index, count));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reviewList", JSONArray.parseArray(JSON.toJSONString(reviewList)));

        return jsonObject.toJSONString();
    }

    //按产品ID加载产品属性列表-ajax
    @Deprecated
    @ResponseBody
    @RequestMapping(value = "property/{pid}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String loadProductPropertyList(@PathVariable("pid") String pid/*产品ID*/) {
        logger.info("获取产品ID");
        Integer productId = Integer.parseInt(pid);

        logger.info("获取产品详情-属性值信息");
        Product product = new Product();
        product.setProductId(productId);
        PropertyValue propertyValue = new PropertyValue();
        propertyValue.setPropertyValueProduct(product);
        List<PropertyValue> propertyValueList = propertyValueService.getList(propertyValue, null);

        logger.info("获取产品详情-分类信息对应的属性列表");
        Property property = new Property();
        property.setPropertyCategory(product.getProductCategory());
        List<Property> propertyList = propertyService.
                getList(property, null);

        logger.info("属性列表和属性值列表合并");
        for (Property propertyEach : propertyList) {
            for (PropertyValue propertyValueEach : propertyValueList) {
                if (propertyEach.getPropertyId().equals(propertyValueEach.getPropertyValueProperty().getPropertyId())) {
                    List<PropertyValue> propertyValueItem = new ArrayList<>(1);
                    propertyValueItem.add(propertyValueEach);
                    propertyEach.setPropertyValueList(propertyValueItem);
                    break;
                }
            }
        }
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("propertyList", JSONArray.parseArray(JSON.toJSONString(propertyList)));

        return jsonObject.toJSONString();
    }

    //加载猜你喜欢列表-ajax
    @ResponseBody
    @RequestMapping(value = "guess/{cid}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String guessYouLike(@PathVariable("cid") Integer cid, @RequestParam Integer guessNumber) {
        logger.info("获取猜你喜欢列表");
        Product product = new Product();
        product.setProductCategory(new Category(cid));
        Integer total = productService.
                getTotal(product, new Byte[]{0, 2});
        logger.info("分类ID为{}的产品总数为{}条", cid, total);
        //生成随机数
        int i = new Random().nextInt(total);
        if (i + 2 >= total) {
            i = total - 3;
        }
        if (i < 0) {
            i = 0;
        }
        while (i == guessNumber) {
            i = new Random().nextInt(total);
            if (i + 2 >= total) {
                i = total - 3;
            }
            if (i < 0) {
                i = 0;
                break;
            }
        }

        logger.info("guessNumber值为{}，新guessNumber值为{}", guessNumber, i);
        List<Product> loveProductList = productService.
                getList(product, new Byte[]{0, 2}, null, new PageUtil().setCount(3).setPageStart(i));
        if (loveProductList != null) {
            logger.info("获取产品列表的相应的一张预览图片");
            for (Product loveProduct : loveProductList) {
                loveProduct.setSingleProductImageList(productImageService.getList(loveProduct.getProductId(), (byte) 0, new PageUtil(0, 1)));
            }
        }

        JSONObject jsonObject = new JSONObject();
        logger.info("获取数据成功！");
        jsonObject.put("success", true);
        jsonObject.put("loveProductList", JSONArray.parseArray(JSON.toJSONString(loveProductList)));
        jsonObject.put("guessNumber", i);
        return jsonObject.toJSONString();
    }
}
