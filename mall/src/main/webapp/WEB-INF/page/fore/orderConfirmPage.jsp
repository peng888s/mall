<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="include/header.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
    var contextPath = "${ctx}";
</script>
<head>
    <link href="${ctx}/res/css/fore/fore_orderConfirmPage.css" rel="stylesheet"/>
    <title>确认收货 - mall.com-理想生活上Mall商城</title>
    <script>

    </script>
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
<div class="headerLayout">
    <div class="headerContext">
        <ol class="header-extra">
            <li class="step-done">
                <div class="step-name">拍下商品</div>
                <div class="step-no_first"></div>
                <div class="step-time">
                    <div class="step-time-wraper">${productOrder.productOrderPayDate}</div>
                </div>
            </li>
            <li class="step-done">
                <div class="step-name">付款到支付宝</div>
                <div class="step-no step-no-select"></div>
                <div class="step-time">
                    <div class="step-time-wraper">${productOrder.productOrderPayDate}</div>
                </div>
            </li>
            <li class="step-done">
                <div class="step-name">卖家发货</div>
                <div class="step-no step-no-select"></div>
                <div class="step-time">
                    <div class="step-time-wraper">${productOrder.productOrderDeliveryDate}</div>
                </div>
            </li>
            <li class="step-no">
                <div class="step-name">确认收货</div>
                <div class="step-no">4</div>
            </li>
            <li class="step-no">
                <div class="step-name">评价</div>
                <div class="step-no_last">5</div>
            </li>
        </ol>
    </div>
</div>
<div class="content">
    <h1>我已收到货，同意支付宝付款</h1>
    <div class="order_info">
        <h2>确认订单信息</h2>
        <table class="table_order_orderItem">
            <thead>
            <tr>
                <th>店铺宝贝</th>
                <th>单价</th>
                <th>数量</th>
                <th>小计</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.productOrder.productOrderItemList}" var="orderItem" varStatus="i">
                <tr class="tr_product_info">
                    <td width="500px"><img
                            src="${ctx}/res/images/item/productSinglePicture/${orderItem.productOrderItemProduct.singleProductImageList[0].productImageSrc}"
                            style="width: 50px;height: 50px;"/><span class="span_product_name"><a
                            href="${ctx}/product/${orderItem.productOrderItemProduct.productId}"
                            target="_blank">${orderItem.productOrderItemProduct.productName}</a></span>
                    </td>
                    <td><span
                            class="span_product_sale_price">${orderItem.productOrderItemProduct.productSalePrice}0</span>
                    </td>
                    <td><span class="span_productOrderItem_number">${orderItem.productOrderItemNumber}</span></td>
                    <td><span class="span_productOrderItem_price"
                              style="font-weight: bold">${orderItem.productOrderItemPrice}0</span></td>
                </tr>
            </c:forEach>
            <tr class="order-ft">
                <td colspan="4">
                    <div class="total-price">实付款：￥<strong>${requestScope.orderTotalPrice}0</strong></div>
                </td>
            </tr>
            </tbody>
            <tbody class="misc-info">
            <tr class="set-row">
                <td colspan="4"></td>
            </tr>
            <tr>
                <td colspan="4">
                    <span class="info_label">订单编号：</span>
                    <span class="info_value">${requestScope.productOrder.productOrderCode}</span>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <span class="info_label">卖家商铺昵称：</span>
                    <span class="info_value">贤趣模拟旗舰店</span>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <span class="info_label">成交时间：</span>
                    <span class="info_value">${requestScope.productOrder.productOrderPayDate}</span>
                </td>
            </tr>
            </tbody>
        </table>
        <div class="order-dashboard">
            <div class="bd">
                <ul>
                    <li>请收到货后，再确认收货！否则您可能钱货两空！</li>
                    <li class="message">提示：本系统不会进行真实交易，请放心测试</li>
                </ul>
                <script>
                    function confirmOrder() {
                        var yn = confirm("点击确认后，您之前付款到支付宝的 ${requestScope.orderTotalPrice}0 元将直接到卖家账户里，请务必收到货再确认！");
                        if (yn) {
                            $.ajax({
                                url: "/mall/order/success/${requestScope.productOrder.productOrderCode}",
                                type: "PUT",
                                data: null,
                                dataType: "json",
                                success: function (data) {
                                    if (data.success) {
                                        location.href = "/mall/order/success/${requestScope.productOrder.productOrderCode}";
                                    } else {
                                        alert("订单确认异常，请稍后再试！");
                                        location.href = "/mall/order/0/10";
                                    }
                                },
                                error: function (data) {
                                    alert("订单确认异常，请稍后再试！");
                                    location.href = "/mall/order/0/10";
                                }
                            });
                        }
                    }
                </script>
                <a href="javascript:void(0)" onclick="confirmOrder()">确定</a>
            </div>
        </div>
    </div>
</div>
<%@include file="include/footer_two.jsp" %>
<%@include file="include/footer.jsp" %>
</body>
