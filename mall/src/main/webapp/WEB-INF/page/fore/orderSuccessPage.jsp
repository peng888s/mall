<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="include/header.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
    var contextPath = "${ctx}";
</script>
<head>
    <link href="${ctx}/res/css/fore/fore_orderSuccessPage.css" rel="stylesheet"/>
    <title>交易成功 - mall.com-理想生活上Mall商城</title>
</head>
<body>
<nav>
    <%@ include file="include/navigator.jsp" %>
</nav>
<div class="header">
    <div id="mallLogo">
        <a href="${ctx}"><img
                src="${ctx}/res/images/fore/WebsiteImage/tmallLogoD.png"></a>
    </div>
    <div class="shopSearchHeader">
        <form action="${ctx}/product" method="get">
            <div class="shopSearchInput">
                <input type="text" class="searchInput" name="productName" placeholder="搜索 商品/品牌/店铺"
                       maxlength="50">
                <input type="submit" value="搜 索" class="searchBtn">
            </div>
        </form>
    </div>
</div>
<div class="content">
    <div class="take-delivery">
        <div class="summary-status">
            <h2>交易已经成功，卖家将收到您的货款。</h2>
            <c:if test="${requestScope.product != null}">
                <div class="successInfo">
                    <ul class="info-rate-coin">
                        <li>
                            <span class="review_msg">认真填写商品评价，就有机会获得20点Mall达人经验值！</span>
                            <a class="J_makePoint"
                               href="${ctx}/review/${requestScope.orderItem.productOrderItemId}">
                                <img src="${ctx}/res/images/item/productSinglePicture/${requestScope.product.singleProductImageList[0].productImageSrc}"
                                     width="100px" height="100px"/>
                                <p class="productName"
                                   title="${requestScope.product.productName}">${requestScope.product.productName}</p>
                                <span class="vi-btn">立即评价</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </c:if>
            <p>您可以查看：<a href="${ctx}/order/0/10">已买到的宝贝</a></p>
        </div>
    </div>
</div>
<%@include file="include/footer_two.jsp" %>
<%@include file="include/footer.jsp" %>
</body>