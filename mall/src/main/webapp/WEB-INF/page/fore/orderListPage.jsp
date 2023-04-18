<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="include/header.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
    var contextPath = "${ctx}";
</script>
<head>
    <link href="${ctx}/res/css/fore/fore_orderList.css" rel="stylesheet"/>
    <title>已买到的宝贝</title>
    <script>
        $(function () {
            $('#btn-ok').click(function () {
                $.ajax({
                    url: "${ctx}/order/close/" + $("#order_id_hidden").val(),
                    type: "PUT",
                    data: null,
                    dataType: "json",
                    success: function (data) {
                        if (data.success !== true) {
                            alert("订单处理异常，请稍候再试！");
                        }
                        location.href = "/mall/order/0/10";
                    },
                    beforeSend: function () {

                    },
                    error: function () {
                        alert("订单取消出现问题，请稍后再试！");
                        location.href = "/mall/order/0/10";
                    }
                });
            });
        });

        function closeOrder(orderCode) {
            if (isNaN(orderCode) || orderCode === null) {
                return;
            }
            $("#order_id_hidden").val(orderCode);
            $('#modalDiv').modal();
        }

        function getPage(index) {
            var name = $(".tab_select").children("a").attr("name");
            if (name === undefined) {
                name = "";
            }
            location.href = "${ctx}/order/" + index + "/10" + "?" + name;
        }
    </script>
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
                           maxlength="50">
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
    <ul class="tabs_ul">
        <li <c:if test="${requestScope.status == null}">class="tab_select"</c:if>><a
                href="${ctx}/order/0/10">所有订单</a></li>
        <li <c:if test="${requestScope.status == 0}">class="tab_select"</c:if>><a
                href="${ctx}/order/0/10?status=0" name="status=0">待付款</a></li>
        <li <c:if test="${requestScope.status == 1}">class="tab_select"</c:if>><a
                href="${ctx}/order/0/10?status=1" name="status=1">待发货</a></li>
        <li <c:if test="${requestScope.status == 2}">class="tab_select"</c:if>><a
                href="${ctx}/order/0/10?status=2" name="status=2">待收货</a></li>
        <li <c:if test="${requestScope.status == 3}">class="tab_select"</c:if>><a
                href="${ctx}/order/0/10?status=3" name="status=3">已完成</a></li>
    </ul>
    <%@include file="include/page.jsp" %>
    <table class="table_orderList">
        <thead>
        <tr>
            <th>宝贝</th>
            <th width="80px">单价</th>
            <th width="80px">数量</th>
            <th width="140px">实付款</th>
            <th width="140px">交易状态</th>
            <th width="140px">交易操作</th>
        </tr>
        </thead>
        <c:choose>
            <c:when test="${requestScope.productOrderList != null && fn:length(requestScope.productOrderList)>0}">
                <c:forEach items="${requestScope.productOrderList}" var="productOrder">
                    <tbody>
                    <tr class="tr_order_info">
                        <td colspan="6"><span class="span_pay_date">${productOrder.productOrderPayDate}</span><span
                                class="span_order_code_title">订单号:</span><span
                                class="span_order_code">${productOrder.productOrderCode}</span></td>
                    </tr>
                    <c:forEach items="${productOrder.productOrderItemList}" var="productOrderItem" varStatus="i">
                        <tr class="tr_orderItem_info">
                            <td><img class="orderItem_product_image"
                                     src="${ctx}/res/images/item/productSinglePicture/${productOrderItem.productOrderItemProduct.singleProductImageList[0].productImageSrc}"
                                     style="width: 80px;height: 80px;"/><span class="orderItem_product_name"><a
                                    href="${ctx}/product/${productOrderItem.productOrderItemProduct.productId}">${productOrderItem.productOrderItemProduct.productName}</a></span>
                            </td>
                            <td><span
                                    class="orderItem_product_price">￥${productOrderItem.productOrderItemPrice/productOrderItem.productOrderItemNumber}</span>
                            </td>
                            <td><span
                                    class="orderItem_product_number">${productOrderItem.productOrderItemNumber}</span>
                            </td>
                            <td class="td_order_content"><span
                                    class="orderItem_product_realPrice">￥${productOrderItem.productOrderItemPrice}</span>
                            </td>
                            <c:if test="${i.count == 1}">
                                <c:choose>
                                    <c:when test="${productOrder.productOrderStatus==0}">
                                        <td class="td_order_content"
                                            rowspan="${fn:length(requestScope.productOrderItemList)}">
                                            <span class="span_order_status" title="等待买家付款">等待买家付款</span>
                                        </td>
                                        <td class="td_order_content"
                                            rowspan="${fn:length(requestScope.productOrderItemList)}">
                                            <a class="order_btn pay_btn"
                                               href="${ctx}/order/pay/${productOrder.productOrderCode}">立即付款</a>
                                            <p class="order_close"><a class="order_close" href="javascript:void(0)"
                                                                      onclick="closeOrder('${productOrder.productOrderCode}')">取消订单</a>
                                            </p>
                                        </td>
                                    </c:when>
                                    <c:when test="${productOrder.productOrderStatus==1}">
                                        <td class="td_order_content"
                                            rowspan="${fn:length(requestScope.productOrderItemList)}">
                                            <span class="span_order_status" title="买家已付款，等待卖家发货">等待卖家发货</span>
                                        </td>
                                        <td class="td_order_content"
                                            rowspan="${fn:length(requestScope.productOrderItemList)}">
                                            <a class="order_btn delivery_btn"
                                               href="${ctx}/order/delivery/${productOrder.productOrderCode}">提醒发货</a>
                                        </td>
                                    </c:when>
                                    <c:when test="${productOrder.productOrderStatus==2}">
                                        <td class="td_order_content"
                                            rowspan="${fn:length(requestScope.productOrderItemList)}">
                                            <span class="span_order_status" title="卖家已发货，等待买家确认">等待买家确认</span>
                                        </td>
                                        <td class="td_order_content"
                                            rowspan="${fn:length(requestScope.productOrderItemList)}">
                                            <a class="order_btn confirm_btn"
                                               href="${ctx}/order/confirm/${productOrder.productOrderCode}">确认收货</a>
                                        </td>
                                    </c:when>
                                    <c:when test="${productOrder.productOrderStatus==3}">
                                        <td class="td_order_content"
                                            rowspan="${fn:length(requestScope.productOrderItemList)}">
                                            <span class="span_order_status" title="交易成功">交易成功</span>
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td class="td_order_content"
                                            rowspan="${fn:length(requestScope.productOrderItemList)}">
                                            <span class="td_error" title="交易关闭">交易关闭</span>
                                        </td>
                                        <td class="td_order_content"
                                            rowspan="${fn:length(requestScope.productOrderItemList)}">
                                        </td>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                            <c:if test="${productOrder.productOrderStatus==3 && productOrderItem.isReview != null && !productOrderItem.isReview}">
                                <td class="td_order_content">
                                    <a class="order_btn review_btn"
                                       href="${ctx}/review/${productOrderItem.productOrderItemId}">评价</a>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                    </tbody>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tbody>
                <tr>
                    <td colspan="6" class="no_search_result"><img
                            src="${ctx}/res/images/fore/WebsiteImage/T1MQ1cXhtiXXXXXXXX-78-120.png"/><span
                            class="error_msg">没有符合条件的宝贝，请尝试其他搜索条件。</span></td>
                </tr>
                </tbody>
            </c:otherwise>
        </c:choose>
    </table>
    <%@include file="include/page.jsp" %>
</div>
<%-- 模态框 --%>
<div class="modal fade" id="modalDiv" tabindex="-1" role="dialog" aria-labelledby="modalDiv" aria-hidden="true"
     data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">提示</h4>
            </div>
            <div class="modal-body">您确定要取消该订单吗？取消订单后，不能恢复。</div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-primary" id="btn-ok">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal" id="btn-close">关闭</button>
                <input type="hidden" id="order_id_hidden">
            </div>
        </div>
        <%-- /.modal-content --%>
    </div>
    <%-- /.modal --%>
</div>
<%@include file="include/footer_two.jsp" %>
<%@include file="include/footer.jsp" %>
</body>
