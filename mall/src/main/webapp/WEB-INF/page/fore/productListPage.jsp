<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="include/header.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
    var contextPath = "${ctx}";
</script>
<script src="${ctx}/res/js/fore/fore_productList.js"></script>
<link href="${ctx}/res/css/fore/fore_productList.css" rel="stylesheet">
<body>
<title><c:choose><c:when test="${requestScope.searchValue != null}">${requestScope.searchValue}</c:when>
    <c:otherwise><c:choose><c:when
            test="${requestScope.productList != null && fn:length(requestScope.productList)>0}">${requestScope.productList[0].productCategory.categoryName}</c:when><c:otherwise>没找到相关商品</c:otherwise></c:choose></c:otherwise></c:choose>-Mall商城mall.com-理想生活上Mall商城</title>
<nav>
    <%@ include file="include/navigator.jsp" %>
    <div class="header">
        <div id="mallLogo">
            <a href="${ctx}"><img
                    src="${ctx}/res/images/fore/WebsiteImage/tmallLogoD.png"></a>
        </div>
        <div class="shopSearchHeader">
            <form action="${ctx}/product" method="get">
                <div class="shopSearchInput">
                    <input type="text" class="searchInput" name="productName" placeholder="搜索 商品/品牌/店铺"
                           value="${requestScope.searchValue}" maxlength="50">
                    <input type="submit" value="搜 索" class="searchBtn">
                </div>
            </form>
            <ul>
                <c:forEach items="${requestScope.categoryList}" var="category" varStatus="i">
                    <li>
                        <a href="${ctx}/product?categoryId=${category.categoryId}">${category.categoryName}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</nav>
<div class="context">
    <c:choose>
        <c:when test="${requestScope.productList != null && fn:length(requestScope.productList)>0}">
            <div class="context_menu">
                <ul <c:choose><c:when
                        test="${requestScope.searchValue != null}"> data-value="${requestScope.searchValue}"</c:when>
                    <c:otherwise>data-type = ${requestScope.searchType}</c:otherwise></c:choose>>
                    <li data-name="productName"
                        <c:if test="${requestScope.orderBy =='productName' || requestScope.orderBy ==null}">class="orderBySelect"</c:if>>
                        <span>综合</span>
                        <span class="orderByAsc"></span>
                    </li>
                    <li data-name="productCreateDate"
                        <c:if test="${requestScope.orderBy =='productCreateDate'}">class="orderBySelect"</c:if>>
                        <span>新品</span>
                        <span class="orderByAsc"></span>
                    </li>
                    <li data-name="productSaleCount"
                        <c:if test="${requestScope.orderBy =='productSaleCount'}">class="orderBySelect"</c:if>>
                        <span>销量</span>
                        <span class="orderByAsc"></span>
                    </li>
                    <li data-name="productSalePrice"
                        <c:if test="${requestScope.orderBy =='productSalePrice'}">class="orderBySelect"</c:if>>
                        <span style="position: relative;left: 3px">价格</span>
                        <span class="orderByDesc <c:if test="${requestScope.isDesc == true}">orderBySelect</c:if>"
                              style="bottom: 5px; left: 6px;"></span>
                        <span class="orderByAsc <c:if test="${requestScope.isDesc == false}">orderBySelect</c:if>"
                              style="top:4px;right: 5px;"></span>
                    </li>
                </ul>
            </div>
            <div class="context_main">
                <c:forEach items="${requestScope.productList}" var="product">
                    <div class="context_productStyle">
                        <div class="context_product">
                            <a href="${ctx}/product/${product.productId}"
                               target="_blank"><img class="context_product_imgMain"
                                                    src="${ctx}/res/images/item/productSinglePicture/${product.singleProductImageList[0].productImageSrc}"/></a>
                            <ul class="context_product_imgList">
                                <c:forEach items="${product.singleProductImageList}" var="img">
                                    <li><img
                                            src="${ctx}/res/images/item/productSinglePicture/${img.productImageSrc}"/>
                                    </li>
                                </c:forEach>
                            </ul>
                            <p class="context_product_price"><span>¥</span>${product.productSalePrice}</p>
                            <p class="context_product_name"><a href="/mall/product/${product.productId}"
                                                               target="_blank">${product.productName}</a></p>
                            <p class="context_product_shop"><span>贤趣${product.productCategory.categoryName}旗舰店</span>
                            </p>
                            <p class="context_product_status">
                                <span class="status_left">总成交<em><c:choose><c:when
                                        test="${product.productSaleCount != null}">${product.productSaleCount}</c:when><c:otherwise>0</c:otherwise></c:choose>笔</em></span>
                                <span class="status_middle">评价<em>${product.productReviewCount}</em></span>
                                <span class="status_right"><img
                                        src="${ctx}/res/images/fore/WebsiteImage/T11lggFoXcXXc1v.nr-93-93.png"/></span>
                            </p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <div class="error">
                <h2>喵~没找到与“${requestScope.searchValue}”相关的 商品 哦，要不您换个关键词我帮您再找找看</h2>
                <h3>建议您：</h3>
                <ol>
                    <li>看看输入的文字是否有误</li>
                    <li>调整关键词，如“全铜花洒套件”改成“花洒”或“花洒 套件”</li>
                    <li>
                        <form action="${ctx}/product" method="get">
                            <input title="查询产品" type="text" class="errorInput" name="productName"
                                   value="${requestScope.searchValue}">
                            <input type="submit" value="去Mall搜索" class="errorBtn">
                        </form>
                    </li>
                </ol>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<%@ include file="include/footer_two.jsp" %>
<%@ include file="include/footer.jsp" %>
</body>