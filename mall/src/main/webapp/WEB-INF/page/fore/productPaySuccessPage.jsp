<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="include/header.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
    var contextPath = "${ctx}";
</script>
<head>
    <link href="${ctx}/res/css/fore/fore_orderPaySuccess.css" rel="stylesheet"/>
    <title>Mall商城mall.com - 网上支付</title>
</head>
<body>
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
<div class="content">
    <div class="content_main">
        <div id="J_AmountList">
            <h2>您已成功付款</h2>
            <div class="summary_pay_done">
                <ul>
                    <li>
                        收货地址：<span>${requestScope.productOrder.productOrderDetailAddress} ${requestScope.productOrder.productOrderReceiver} ${requestScope.productOrder.productOrderMobile}</span>
                    </li>
                    <li>实付款：<span><em>￥${requestScope.orderTotalPrice}</em></span></li>
                </ul>
            </div>
        </div>
        <div id="J_ButtonList">
            <span class="info">您可以 </span>
            <a class="J_MakePoint" href="${ctx}/order/0/10">查看已买到的宝贝</a>
        </div>
        <div id="J_RemindList">
            <ul>
                <li class="alertLi">
                    <p>
                        <strong>安全提醒：</strong>
                        <span class="info">下单后，</span>
                        <span class="warn">用QQ给您发送链接办理退款的都是骗子</span>
                        <span class="info">！Mall商城不存在系统升级，订单异常等问题，谨防假冒客服电话诈骗！</span>
                    </p>
                </li>
            </ul>
        </div>
        <div id="J_Qrcode">
            <div class="mui-tm">
                <a target="_blank" href="http://pages.mall.com/wow/portal/act/app-download">
                    <img class="type2-info"
                         src="${ctx}/res/images/fore/WebsiteImage/TB1c1dwRFXXXXaMapXXXXXXXXXX-259-81.png"/>
                    <img class="type2-qrcode"
                         src="${ctx}/res/images/fore/WebsiteImage/TB1A2aISXXXXXX4XXXXwu0bFXXX.png"/>
                </a>
            </div>
        </div>
    </div>
</div>
<%@include file="include/footer_two.jsp" %>
<%@include file="include/footer.jsp" %>
</body>