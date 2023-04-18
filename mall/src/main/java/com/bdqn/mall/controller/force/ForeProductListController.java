package com.bdqn.mall.controller.force;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.bdqn.mall.controller.BaseController;
import com.bdqn.mall.entity.Category;
import com.bdqn.mall.entity.Product;
import com.bdqn.mall.entity.User;
import com.bdqn.mall.service.*;
import com.bdqn.mall.util.OrderUtil;
import com.bdqn.mall.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class ForeProductListController extends BaseController {
    @Resource
    private ProductService productService;
    @Resource
    private UserService userService;
    @Resource
    private CategoryService categoryService;
    @Resource
    private ProductImageService productImageService;
    @Resource
    private ReviewService reviewService;
    @Resource
    private ProductOrderService productOrderService;
    @Resource
    private ProductOrderItemService productOrderItemService;


    //转到前台Mall-产品搜索列表页
    @RequestMapping(value = "product", method = RequestMethod.GET)
    public String goToPage(HttpSession session, Map<String, Object> map,
                           @RequestParam(value = "categoryId", required = false) Integer categoryId/* 分类ID */,
                           @RequestParam(value = "productName", required = false) String productName/* 产品名称 */) throws UnsupportedEncodingException {
        logger.info("检查用户是否登录");
        Object userId = checkUser(session);
        if (userId != null) {
            logger.info("获取用户信息");
            User user = userService.get(Integer.parseInt(userId.toString()));
            map.put("user", user);
        }
        if (categoryId == null && productName == null) {
            return "redirect:/";
        }
        if (productName != null && productName.trim().equals("")) {
            return "redirect:/";
        }

        logger.info("整合搜索信息");
        Product product = new Product();
        OrderUtil orderUtil = null;
        String searchValue = null;
        Integer searchType = null;

        if (categoryId != null) {
            product.setProductCategory(new Category(categoryId));
            searchType = categoryId;
        }
        //关键词数组
        String[] productNameSplit = null;
        //产品列表
        List<Product> productList;
        //产品总数量
        Integer productCount;
        //分页工具
        PageUtil pageUtil = new PageUtil(0, 20);
        if (productName != null) {
            productNameSplit = productName.split(" ");
            logger.warn("提取的关键词有{}", Arrays.toString(productNameSplit));
            product.setProductName(productName);
            searchValue = productName;
        }
        if (productNameSplit != null && productNameSplit.length > 1) {
            logger.info("获取组合商品列表");
            productList = productService.getMoreList(product, new Byte[]{0, 2}, null, pageUtil, productNameSplit);
            logger.info("按组合条件获取产品总数量");
            productCount = productService.getMoreListTotal(product, new Byte[]{0, 2}, productNameSplit);
        } else {
            logger.info("获取商品列表");
            productList = productService.getList(product, new Byte[]{0, 2}, null, pageUtil);
            logger.info("按条件获取产品总数量");
            productCount = productService.getTotal(product, new Byte[]{0, 2});
        }
        logger.info("获取商品列表的对应信息");
        for (Product p : productList) {
            p.setSingleProductImageList(productImageService.getList(p.getProductId(), (byte) 0, null));
            p.setProductSaleCount(productOrderItemService.getSaleCountByProductId(p.getProductId()));
            p.setProductReviewCount(reviewService.getTotalByProductId(p.getProductId()));
            p.setProductCategory(categoryService.get(p.getProductCategory().getCategoryId()));
        }
        logger.info("获取分类列表");
        List<Category> categoryList = categoryService.getList(null, new PageUtil(0, 5));
        logger.info("获取分页信息");
        pageUtil.setTotal(productCount);

        map.put("categoryList", categoryList);
        map.put("totalPage", pageUtil.getTotalPage());
        map.put("pageUtil", pageUtil);
        map.put("productList", productList);
        map.put("searchValue", searchValue);
        map.put("searchType", searchType);

        logger.info("转到前台Mall-产品搜索列表页");
        return "fore/productListPage";
    }

    //产品高级查询
    @RequestMapping(value = "product/{index}/{count}", method = RequestMethod.GET)
    public String searchProduct(HttpSession session, Map<String, Object> map,
                                @PathVariable("index") Integer index/* 页数 */,
                                @PathVariable("count") Integer count/* 行数*/,
                                @RequestParam(value = "categoryId", required = false) Integer categoryId/* 分类ID */,
                                @RequestParam(value = "productName", required = false) String productName/* 产品名称 */,
                                @RequestParam(required = false) String orderBy/* 排序字段 */,
                                @RequestParam(required = false, defaultValue = "true") Boolean isDesc/* 是否倒序 */) throws UnsupportedEncodingException {
        logger.info("整合搜索信息");
        Product product = new Product();
        OrderUtil orderUtil = null;
        String searchValue = null;
        Integer searchType = null;

        if (categoryId != null) {
            product.setProductCategory(new Category(categoryId));
            searchType = categoryId;
        }
        if (productName != null) {
            product.setProductName(productName);
        }
        if (orderBy != null) {
            logger.info("根据{}排序，是否倒序:{}", orderBy, isDesc);
            orderUtil = new OrderUtil(orderBy, isDesc);
        }
        //关键词数组
        String[] productNameSplit = null;
        //产品列表
        List<Product> productList;
        //产品总数量
        Integer productCount;
        //分页工具
        PageUtil pageUtil = new PageUtil(0, 20);
        if (productName != null) {
            productNameSplit = productName.split(" ");
            logger.warn("提取的关键词有{}", Arrays.toString(productNameSplit));
            product.setProductName(productName);
            searchValue = productName;
        }
        if (productNameSplit != null && productNameSplit.length > 1) {
            logger.info("获取组合商品列表");
            productList = productService.getMoreList(product, new Byte[]{0, 2}, orderUtil, pageUtil, productNameSplit);
            logger.info("按组合条件获取产品总数量");
            productCount = productService.getMoreListTotal(product, new Byte[]{0, 2}, productNameSplit);
        } else {
            logger.info("获取商品列表");
            productList = productService.getList(product, new Byte[]{0, 2}, orderUtil, pageUtil);
            logger.info("按条件获取产品总数量");
            productCount = productService.getTotal(product, new Byte[]{0, 2});
        }
        logger.info("获取商品列表的对应信息");
        for (Product p : productList) {
            p.setSingleProductImageList(productImageService.getList(p.getProductId(), (byte) 0, null));
            p.setProductSaleCount(productOrderItemService.getSaleCountByProductId(p.getProductId()));
            p.setProductReviewCount(reviewService.getTotalByProductId(p.getProductId()));
            p.setProductCategory(categoryService.get(p.getProductCategory().getCategoryId()));
        }
        logger.info("获取分类列表");
        List<Category> categoryList = categoryService.getList(null, new PageUtil(0, 5));
        logger.info("获取分页信息");
        pageUtil.setTotal(productCount);

        map.put("productCount", productCount);
        map.put("totalPage", pageUtil.getTotalPage());
        map.put("pageUtil", pageUtil);
        map.put("productList", JSONArray.parseArray(JSON.toJSONString(productList)));
        map.put("orderBy", orderBy);
        map.put("isDesc", isDesc);
        map.put("searchValue", searchValue);
        map.put("searchType", searchType);
        map.put("categoryList", categoryList);

        return "fore/productListPage";
    }
}