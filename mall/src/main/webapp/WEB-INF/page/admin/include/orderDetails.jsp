<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
    var contextPath = "${ctx}";
</script>
<html>
<head>
    <script>
        $(function () {
            //设置订单状态
            var status_index = '${requestScope.order.productOrderStatus}';
            switch (status_index) {
                case '0':
                    $("#wait_point_1").addClass("wait_point_select").children(".wait_point_text").addClass('td_special');
                    break;
                case '1':
                    $("#wait_point_2").addClass("wait_point_select").children(".wait_point_text").addClass('td_special');
                    break;
                case '2':
                    $("#wait_point_3").addClass("wait_point_select").children(".wait_point_text").addClass('td_special');
                    break;
                case '3':
                    $("#wait_point_4").addClass("wait_point_select").children(".wait_point_text").addClass('td_special');
                    break;
                default:
                    $("#wait_point_1").children(".wait_point_text").addClass('td_special').text("交易关闭").prev("span").text('×');
                    $("#wait_point_1").addClass("wait_point_select");
            }

            /******
             * event
             ******/
            //单击取消按钮时
            $("#btn_order_cancel").click(function () {
                $(".menu_li[data-toggle=order]").click();
            });
            //单击发货按钮时
            $("#btn_order_save").click(function () {
                var orderId = '${requestScope.order.productOrderId}';
                $.ajax({
                    url: "admin/order/" + orderId,
                    type: "PUT",
                    data: null,
                    success: function (data) {
                        $("#btn_order_save").remove();
                        if (data.success) {
                            $("#btn-ok,#btn-close").unbind("click").click(function () {
                                $('#modalDiv').modal("hide");
                                setTimeout(function () {
                                    //ajax请求页面
                                    ajaxUtil.getPage("order/" + data.orderId, null, true);
                                }, 170);
                            });
                            $(".modal-body").text("发货成功！");
                            $('#modalDiv').modal();
                        }
                    },
                    beforeSend: function () {
                        $("#btn_order_save").attr("disabled", true).val("发货中...");
                    },
                    error: function () {

                    }
                });
            });
        });

        //获取产品子界面
        function getChildPage(obj) {
            //设置样式
            $("#div_home_title").children("span").text("产品详情");
            document.title = "Tmall管理后台 - 产品详情";
            //ajax请求页面
            ajaxUtil.getPage("product/" + $(obj).parents("tr").find(".productId").text(), null, true);
        }

        //获取用户子界面
        function getUserPage(id) {
            //设置样式
            $("#div_home_title").children("span").text("用户详情");
            document.title = "Tmall管理后台 - 用户详情";
            //ajax请求页面
            ajaxUtil.getPage("user/" + id, null, true);
        }
    </script>
    <style rel="stylesheet">
        #wait {
            width: 600px;
            height: 2px;
            background-color: #ccc;
            border-radius: 5px;
            position: relative;
            margin: 30px 0 0 50px;
        }

        .wait_point {
            position: absolute;
            width: 1.4em;
            height: 1.4em;
            line-height: 1.4em;
            text-align: center;
            border-radius: 50%;
            background: #ccc;
        }

        .wait_point_select {
            background: #FF7874;
        }

        .wait_point > span {
            position: relative;
            top: 1px;
            color: white;
        }

        .wait_point > .wait_point_text {
            font-size: 12px;
            width: 6em;
            margin-left: -25px;
            color: #666;
            margin-top: 3px;
        }

        .details_status_spacial {
            height: 110px;
        }

        #table_orderItem_list th:first-child {
            width: auto;
        }
    </style>
</head>
<body>
<div class="details_div_first">
    <input type="hidden" value="${requestScope.order.productOrderId}" id="details_order_id"/>
    <div class="frm_div">
        <label class="frm_label text_info" id="lbl_order_id">订单号</label>
        <span class="details_value" id="span_order_id">${requestScope.order.productOrderCode}</span>
    </div>
    <div class="frm_div">
        <label class="frm_label text_info" id="lbl_order_user">所属用户</label>
        <span class="details_value td_wait"><a id="span_order_user" href="javascript:void(0)"
                                               onclick="getUserPage(${requestScope.order.productOrderUser.userId})">${requestScope.order.productOrderUser.userNickName}</a></span>
    </div>
</div>
<div class="details_div">
    <span class="details_title text_info">基本信息</span>
    <div class="frm_div">
        <label class="frm_label text_info" id="lbl_order_receiver">收货人姓名</label>
        <span class="details_value" id="span_order_receiver">${requestScope.order.productOrderReceiver}</span>
    </div>
    <div class="frm_div">
        <label class="frm_label text_info" id="lbl_order_address">收货地址</label>
        <span class="details_value details_value_noRows"
              id="span_order_address">${requestScope.order.productOrderDetailAddress}</span>
    </div>
    <div class="frm_div">
        <label class="frm_label text_info" id="lbl_product_title">邮政编码</label>
        <span class="details_value" id="span_order_post">${requestScope.order.productOrderPost}</span>
        <label class="frm_label text_info" id="lbl_order_mobile">联系电话</label>
        <span class="details_value" id="span_order_mobile">${requestScope.order.productOrderMobile}</span>
    </div>
</div>
<div class="details_div details_status_spacial">
    <span class="details_title text_info">订单状态</span>
    <div id="wait">
        <div class="wait_point" id="wait_point_1" style="top:-0.7em;left: 0;">
            <span>1</span>
            <div class="wait_point_text">等待买家付款</div>
        </div>
        <div class="wait_point" id="wait_point_2" style="top:-0.7em;left: 33%;">
            <span>2</span>
            <div class="wait_point_text">等待卖家发货</div>
        </div>
        <div class="wait_point" id="wait_point_3" style="top:-0.7em;left: 66%;">
            <span>3</span>
            <div class="wait_point_text">等待买家确认</div>
        </div>
        <div class="wait_point" id="wait_point_4" style="top:-0.7em;left: 100%;">
            <span>4</span>
            <div class="wait_point_text">订单交易完成</div>
        </div>
    </div>
</div>
<div class="details_div">
    <span class="details_title text_info">订单项信息</span>
    <table class="table_normal" id="table_orderItem_list">
        <thead class="text_info">
        <tr>
            <th>产品图片</th>
            <th>产品名称</th>
            <th>单价</th>
            <th>数量</th>
            <th>价格</th>
            <th>备注</th>
            <th>操作</th>
            <th hidden class="productId">产品ID</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.order.productOrderItemList}" var="item" varStatus="i">
            <tr>
                <td title="产品图片"><img
                        src="${ctx}/res/images/item/productSinglePicture/${item.productOrderItemProduct.singleProductImageList[0].productImageSrc}"
                        id="pic_single_${item.productOrderItemProduct.singleProductImageList[0].productImageId}"
                        width="42px" height="42px"
                        name="${item.productOrderItemProduct.singleProductImageList[0].productImageId}"/></td>
                <td title="${item.productOrderItemProduct.productName}">${item.productOrderItemProduct.productName}</td>
                <td title="${item.productOrderItemPrice/item.productOrderItemNumber}">${item.productOrderItemPrice/item.productOrderItemNumber}</td>
                <td title="${item.productOrderItemNumber}">${item.productOrderItemNumber}</td>
                <td title="${item.productOrderItemPrice}">${item.productOrderItemPrice}</td>
                <td title="${item.productOrderItemUserMessage}">${item.productOrderItemUserMessage}</td>
                <td><span class="td_special" title="查看产品详情"><a href="javascript:void(0)"
                                                               onclick="getChildPage(this)">详情</a></span></td>
                <td hidden><span class="productId">${item.productOrderItemProduct.productId}</span></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<c:if test="${requestScope.order.productOrderStatus != 0 && requestScope.order.productOrderStatus != 4}">
    <div class="details_div details_div_last">
        <span class="details_title text_info">流程时间</span>
        <div class="frm_div">
            <label class="frm_label text_info" id="lbl_order_pay_date">支付日期</label>
            <span class="details_value details_value_noRows"
                  id="span_order_pay_date">${requestScope.order.productOrderPayDate}</span>
        </div>
        <c:if test="${requestScope.order.productOrderStatus != 1}">
            <div class="frm_div">
                <label class="frm_label text_info" id="lbl_order_delivery_date">发货日期</label>
                <span class="details_value details_value_noRows"
                      id="span_order_delivery_date">${requestScope.order.productOrderDeliveryDate}</span>
            </div>
            <c:if test="${requestScope.order.productOrderStatus == 3}">
                <div class="frm_div">
                    <label class="frm_label text_info" id="lbl_order_confirm_date">确认日期</label>
                    <span class="details_value details_value_noRows"
                          id="span_order_confirm_date">${requestScope.order.productOrderConfirmDate}</span>
                </div>
            </c:if>
        </c:if>
    </div>
</c:if>
<div class="details_tools_div">
    <c:if test="${requestScope.order.productOrderStatus==1}">
        <input class="frm_btn" id="btn_order_save" type="button" value="发货"/>
    </c:if>
    <input class="frm_btn frm_clear" id="btn_order_cancel" type="button" value="取消"/>
</div>

<%-- 模态框 --%>
<div class="modal fade" id="modalDiv" tabindex="-1" role="dialog" aria-labelledby="modalDiv" aria-hidden="true"
     data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">提示</h4>
            </div>
            <div class="modal-body">您确定要删除该订单吗？</div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-primary" id="btn-ok">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal" id="btn-close">关闭</button>
            </div>
        </div>
        <%-- /.modal-content --%>
    </div>
    <%-- /.modal --%>
</div>
<div class="loader"></div>
</body>
</html>
