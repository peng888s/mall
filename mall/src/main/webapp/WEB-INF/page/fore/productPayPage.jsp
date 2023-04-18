<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="include/header.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
    var contextPath = "${ctx}";
</script>
<head>
    <link href="${ctx}/res/css/fore/fore_orderPay.css" rel="stylesheet"/>
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
    </div>
</nav>
<div class="content">
    <div class="order_div">
        <img src="${ctx}/res/images/fore/WebsiteImage/payCode.png" width="100px"
             height="100px"/>
        <c:choose>
            <c:when test="${fn:length(requestScope.productOrder.productOrderItemList)==1}">
                <div class="order_name">
                    <span>Mall商城 -- ${requestScope.productOrder.productOrderItemList[0].productOrderItemProduct.productName}</span>
                </div>
                <div class="order_shop_name">
                    <span>卖家昵称：Mall${requestScope.productOrder.productOrderItemList[0].productOrderItemProduct.productCategory.categoryName}旗舰店</span>
                </div>
            </c:when>
            <c:otherwise>
                <div class="order_name">
                    <span>Mall商城 -- 合并订单：${fn:length(requestScope.productOrder.productOrderItemList)}笔</span>
                </div>
            </c:otherwise>
        </c:choose>
        <div class="order_price">
            <span class="price_value">${requestScope.orderTotalPrice}</span>
            <span class="price_unit">元</span>
        </div>
    </div>
    <div class="order_pay_div">
        <script>
            function pay() {
                $.ajax({
                    url: "${ctx}/order/pay/${requestScope.productOrder.productOrderCode}",
                    type: "PUT",
                    data: null,
                    dataType: "json",
                    success: function (data) {
                        if (data.success !== true) {
                            alert("订单处理异常，请稍候再试！");
                        }
                        location.href = "/mall" + data.url;
                    },
                    beforeSend: function () {

                    },
                    error: function () {
                        alert("订单支付出现问题，请重新支付！");
                        location.href = "/mall/order/0/10";
                    }
                });
            }
        </script>
        <a class="order_pay_btn" href="javascript:void(0)" onclick="pay()">确认支付</a>
    </div>
</div>
<%@include file="include/footer_two.jsp" %>
<%@include file="include/footer.jsp" %>
</body>