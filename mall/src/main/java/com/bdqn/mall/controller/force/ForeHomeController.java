package com.bdqn.mall.controller.force;

import com.alibaba.fastjson.JSONObject;
import com.bdqn.mall.controller.BaseController;
import com.bdqn.mall.entity.Category;
import com.bdqn.mall.entity.Product;
import com.bdqn.mall.entity.User;
import com.bdqn.mall.service.CategoryService;
import com.bdqn.mall.service.ProductImageService;
import com.bdqn.mall.service.ProductService;
import com.bdqn.mall.service.UserService;
import com.bdqn.mall.util.OrderUtil;
import com.bdqn.mall.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 前台Mall-主页
 */
@Controller
public class ForeHomeController extends BaseController {
    @Resource
    private UserService userService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private ProductService productService;
    @Resource
    private ProductImageService productImageService;

    //转到前台Mall-主页
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String goToPage(HttpSession session, Map<String, Object> map) {
        logger.info("检查用户是否登录");
        Object userId = checkUser(session);
        if (userId != null) {
            logger.info("获取用户信息");
            User user = userService.get(Integer.parseInt(userId.toString()));
            map.put("user", user);
        }
        logger.info("获取产品分类列表");
        List<Category> categoryList = categoryService.getList(null, null);
        logger.info("获取每个分类下的产品列表");
        for (Category category : categoryList) {
            logger.info("获取分类id为{}的产品集合，按产品ID倒序排序", category.getCategoryId());
            Product product = new Product();
            product.setProductCategory(category);
            List<Product> productList = productService.getList(
                    product, new Byte[]{0, 2}, new OrderUtil("productId", true), new PageUtil(0, 8));
            if (productList != null) {
                for (Product productTemp : productList) {
                    Integer productId = productTemp.getProductId();
                    logger.info("获取产品id为{}的产品预览图片信息", productId);
                    productTemp.setSingleProductImageList(productImageService.getList(productId, (byte) 0, new PageUtil(0, 1)));
                }
            }
            category.setProductList(productList);
        }
        map.put("categoryList", categoryList);
        logger.info("获取促销产品列表");
        List<Product> specialProductList = productService.getList(null, new Byte[]{2}, null, new PageUtil(0, 6));
        map.put("specialProductList", specialProductList);

        logger.info("转到前台主页");
        return "fore/homePage";
    }

    //转到前台Mall-错误页
    @RequestMapping(value = "error", method = RequestMethod.GET)
    public String goToErrorPage() {
        return "fore/errorPage";
    }

    //获取主页分类下产品信息-ajax
    @ResponseBody
    @RequestMapping(value = "product/nav/{categoryId}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getProductByNav(@PathVariable("categoryId") Integer categoryId) {
        JSONObject object = new JSONObject();
        if (categoryId == null) {
            object.put("success", false);
            return object.toJSONString();
        }
        logger.info("获取分类ID为{}的产品标题数据", categoryId);
        Product product = new Product();
        product.setProductCategory(new Category(categoryId));
        List<Product> productList = productService.getTitle(
                product,
                new PageUtil(0, 40));
        List<List<Product>> complexProductList = new ArrayList<>(8);
        List<Product> products = new ArrayList<>(5);
        for (int i = 0; i < productList.size(); i++) {
            //如果临时集合中产品数达到5个，加入到产品二维集合中，并重新实例化临时集合
            if (i % 5 == 0) {
                complexProductList.add(products);
                products = new ArrayList<>(5);
            }
            products.add(productList.get(i));
        }
        complexProductList.add(products);
        Category category = new Category();
        category.setCategoryId(categoryId);
        category.setComplexProductList(complexProductList);
        object.put("success", true);
        object.put("category", category);
        return object.toJSONString();
    }
}
