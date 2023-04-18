<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
    var contextPath = "${ctx}";
</script>
<html>
<head>
    <script>
        //检索数据集
        var dataList = {
            "productName": null,
            "categoryId": null,
            "productSalePrice": null,
            "productPrice": null,
            "productIsEnabledArray": null,
            "orderBy": null,
            "isDesc": true
        };
        $(function () {
            //刷新下拉框
            $('#select_product_category').selectpicker('refresh');
            /******
             * event
             ******/
            //点击查询按钮时
            $("#btn_product_submit").click(function () {
                var productName = $.trim($("#input_product_name").val());
                var categoryId = parseInt($("#select_product_category").val());
                var lowest_price = $.trim($("#input_product_sale_price").val());
                var highest_price = $.trim($("#input_product_price").val());
                //产品状态数组
                var status_array = [];
                $("input[name = checkbox_product_isEnabled]:checked").each(function () {
                    status_array.push($(this).val());
                });
                //校验数据合法性
                if( isNaN(lowest_price) || isNaN(highest_price) ){
                    styleUtil.errorShow($('#text_product_msg'),"金额输入格式有误！");
                    return;
                }
                //封装数据
                dataList.productName = encodeURI(productName);
                dataList.categoryId = categoryId;
                dataList.productSalePrice = lowest_price;
                dataList.productPrice = highest_price;
                dataList.productIsEnabledArray = status_array;

                getData($(this), "admin/product/0/10", dataList);
            });
            //点击刷新按钮时
            $("#btn_product_refresh").click(function () {
                //清除数据
                dataList.productName = null;
                dataList.categoryId = null;
                dataList.productSalePrice = null;
                dataList.productPrice = null;
                dataList.productIsEnabledArray = null;
                dataList.orderBy = null;
                dataList.isDesc = true;
                //获取数据
                getData($(this), "admin/product/0/10", null);
                //清除排序样式
                var table = $("#table_product_list");
                table.find("span.orderByDesc,span.orderByAsc").css("opacity","0");
                table.find("th.data_info").attr("data-sort","asc");
            });
            //点击th排序时
            $("th.data_info").click(function () {
                var table = $("#table_product_list");
                if(table.find(">tbody>tr").length <= 1){
                    return;
                }
                //获取排序字段
                dataList.orderBy = $(this).attr("data-name");
                //是否倒序排序
                dataList.isDesc = $(this).attr("data-sort")==="asc";

                getData($(this), "admin/product/0/10", dataList);
                //设置排序
                table.find("span.orderByDesc,span.orderByAsc").css("opacity","0");
                if(dataList.isDesc){
                    $(this).attr("data-sort","desc").children(".orderByAsc.orderBySelect").removeClass("orderBySelect").css("opacity","1");
                    $(this).children(".orderByDesc").addClass("orderBySelect").css("opacity","1");
                } else {
                    $(this).attr("data-sort","asc").children(".orderByDesc.orderBySelect").removeClass("orderBySelect").css("opacity","1");
                    $(this).children(".orderByAsc").addClass("orderBySelect").css("opacity","1");
                }
            });
            //点击table中的数据时
            $("#table_product_list").find(">tbody>tr").click(function () {
                trDataStyle($(this));
            });
        });
        //获取产品数据
        function getData(object,url,dataObject) {
            var table = $("#table_product_list");
            var tbody = table.children("tbody").first();
            $.ajax({
                url: url,
                type: "get",
                data: dataObject,
                traditional: true,
                success: function (data) {
                    //清空原有数据
                    tbody.empty();
                    //设置样式
                    $(".loader").css("display","none");
                    object.attr("disabled",false);
                    //显示产品统计数据
                    $("#product_count_data").text(data.productCount);
                    if (data.productList.length > 0) {
                        for (var i = 0; i < data.productList.length; i++) {
                            var isEnabledClass;
                            var isEnabledTitle;
                            var isEnabled;
                            switch (data.productList[i].productIsEnabled) {
                                case 0:
                                    isEnabledClass = "td_success";
                                    isEnabledTitle = "产品正常销售中";
                                    isEnabled = "销售中";
                                    break;
                                case 2:
                                    isEnabledClass = "td_warn";
                                    isEnabledTitle = "产品显示在主页促销中";
                                    isEnabled = "促销中";
                                    break;
                                default:
                                    isEnabledClass = "td_error";
                                    isEnabledTitle = "产品缺货或违规停售中";
                                    isEnabled = "停售中";
                                    break;
                            }
                            var productPrice = data.productList[i].productPrice.toFixed(1);
                            var productSalePrice = data.productList[i].productSalePrice.toFixed(1);
                            var productId = data.productList[i].productId;
                            var productName = data.productList[i].productName;
                            var productTitle = data.productList[i].productTitle;
                            var productCreateDate = data.productList[i].productCreateDate;
                            //显示产品数据
                            tbody.append("<tr><td><input type='checkbox' class='cbx_select' id='cbx_product_select_" + productId + "'><label for='cbx_product_select_" + productId + "'></label></td><td title='"+productName+"'>" + productName + "</td><td title='"+productTitle+"'>" + productTitle + "</td><td title='"+productPrice+"'>" + productPrice + "</td><td title='"+productSalePrice+"'>" + productSalePrice + "</td><td title='"+productCreateDate+"'>" + productCreateDate + "</td><td><span class='" + isEnabledClass + "' title='"+isEnabledTitle+"'>"+ isEnabled + "</span></td><td><span class='td_special' title='查看产品详情'><a href='javascript:void(0);' onclick='getChildPage(this)'>详情</a></span></td><td hidden><span class='productId'>" + productId + "</span></td></tr>");
                        }
                        //绑定事件
                        tbody.children("tr").click(function () {
                            trDataStyle($(this));
                        });
                        //分页
                        var pageUtil = {
                            index: data.pageUtil.index,
                            count: data.pageUtil.count,
                            total: data.pageUtil.total,
                            totalPage: data.totalPage
                        };
                        createPageDiv($(".loader"), pageUtil);
                    }
                },
                beforeSend: function () {
                    $(".loader").css("display","block");
                    object.attr("disabled",true);
                },
                error: function () {

                }
            });
        }

        //获取产品子界面
        function getChildPage(obj) {
            var url;
            var title;
            if(obj === null){
                title = "添加产品";
                url = "product/new";
            } else {
                title = "产品详情";
                url = "product/"+$(obj).parents("tr").find(".productId").text();
            }

            //设置样式
            $("#div_home_title").children("span").text(title);
            document.title = "Tmall管理后台 - "+title;
            //ajax请求页面
            ajaxUtil.getPage(url,null,true);
        }

        //获取页码数据
        function getPage(index) {
            getData($(this), "admin/product/" + index + "/10", dataList);
        }
    </script>
    <style rel="stylesheet">
        #text_cut{
            position: relative;
            right: 10px;
            color: #ccc;
        }
        #lbl_product_isEnabled_special{
            margin-right: 20px;
        }
    </style>
</head>
<body>
<div class="frm_div text_info">
    <div class="frm_group">
        <label class="frm_label" id="lbl_product_name" for="input_product_name">产品名称</label>
        <input class="frm_input" id="input_product_name" type="text" maxlength="50"/>
        <label class="frm_label" id="lbl_product_category_id" for="select_product_category">产品类型</label>
        <select class="selectpicker" id="select_product_category" data-size="8">
            <option value="0">全部</option>
            <c:forEach items="${requestScope.categoryList}" var="category">
                <option value="${category.categoryId}">${category.categoryName}</option>
            </c:forEach>
        </select>
        <input class="frm_btn" id="btn_product_submit" type="button" value="查询"/>
        <input class="frm_btn frm_clear" id="btn_clear" type="button" value="重置"/>
    </div>
    <div class="frm_group">
        <label class="frm_label" id="lbl_product_isEnabled" for="checkbox_product_isEnabled_true">产品状态</label>
        <input id="checkbox_product_isEnabled_true" name="checkbox_product_isEnabled" type="checkbox" value="0" checked>
        <label class="frm_label" id="lbl_product_isEnabled_true" for="checkbox_product_isEnabled_true">销售中</label>
        <input id="checkbox_product_isEnabled_false" name="checkbox_product_isEnabled" type="checkbox" value="1" checked>
        <label class="frm_label" id="lbl_product_isEnabled_false" for="checkbox_product_isEnabled_false">停售中</label>
        <input id="checkbox_product_isEnabled_special" name="checkbox_product_isEnabled" type="checkbox" value="2" checked>
        <label class="frm_label" id="lbl_product_isEnabled_special" for="checkbox_product_isEnabled_special">促销中</label>

        <label class="frm_label"  id="lbl_product_sale_price" for="input_product_sale_price">金额</label>
        <input class="frm_input frm_num"  id="input_product_sale_price" type="text" placeholder="最低价" maxlength="10">
        <span id="text_cut">—</span>
        <input class="frm_input frm_num"  id="input_product_price" type="text" placeholder="最高价" maxlength="10">
        <span class="frm_error_msg" id="text_product_msg"></span>
    </div>
    <div class="frm_group_last">
        <input class="frm_btn frm_add" id="btn_product_add" type="button" value="添加一件产品" onclick="getChildPage(null)"/>
        <input class="frm_btn frm_refresh" id="btn_product_refresh" type="button" value="刷新产品列表"/>
        <span class="frm_error_msg" id="text_tools_msg"></span>
    </div>
</div>
<div class="data_count_div text_info">
    <svg class="icon" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg" p-id="2522" width="16" height="16">
            <path d="M401.976676 735.74897c-88.721671 0-172.124196-34.635845-234.843656-97.526197-62.724577-62.86784-97.271394-146.453537-97.271394-235.358379s34.546817-172.490539 97.276511-235.361449c62.715367-62.887282 146.117892-97.522104 234.838539-97.522104 88.719624 0 172.135452 34.633798 234.881518 97.522104 62.704111 62.875003 97.235578 146.4607 97.235578 235.361449 0 88.901773-34.530444 172.487469-97.231485 235.358379C574.112128 701.116195 490.6963 735.74897 401.976676 735.74897zM401.976676 121.204479c-75.012438 0-145.533584 29.290093-198.572568 82.474386-109.585861 109.834524-109.585861 288.539602-0.004093 398.36901 53.043077 53.188386 123.564223 82.47848 198.577684 82.47848 75.015507 0 145.553027-29.291117 198.620663-82.47848C710.126918 492.220514 710.126918 313.511343 600.593246 203.678866 547.530726 150.496619 476.992183 121.204479 401.976676 121.204479z" p-id="2523" fill="#FF7874">
            </path>
            <path d="M932.538427 958.228017c-6.565533 0-13.129019-2.508123-18.132986-7.52437L606.670661 642.206504c-9.989515-10.014074-9.969049-26.231431 0.045025-36.220946s26.230408-9.969049 36.220946 0.045025l307.73478 308.497143c9.989515 10.014074 9.969049 26.231431-0.045025 36.220946C945.627537 955.735244 939.081447 958.228017 932.538427 958.228017z" p-id="2524" fill="#FF7874">
            </path>
        </svg>
    <span class="data_count_title">查看合计</span>
    <span>产品总数:</span>
    <span class="data_count_value" id="product_count_data">${requestScope.productCount}</span>
    <span class="data_count_unit">件</span>
</div>
<div class="table_normal_div">
    <table class="table_normal" id="table_product_list">
        <thead class="text_info">
        <tr>
            <th><input type="checkbox" class="cbx_select" id="cbx_select_all"><label for="cbx_select_all"></label></th>
            <th class="data_info" data-sort="asc" data-name="productName">
                <span>产品名称</span>
                <span class="orderByDesc"></span>
                <span class="orderByAsc orderBySelect"></span>
            </th>
            <th class="data_info" data-sort="asc" data-name="productTitle">
                <span>产品标题</span>
                <span class="orderByDesc"></span>
                <span class="orderByAsc orderBySelect"></span>
            </th>
            <th class="data_info" data-sort="asc" data-name="productPrice">
                <span>原价</span>
                <span class="orderByDesc"></span>
                <span class="orderByAsc orderBySelect"></span>
            </th>
            <th class="data_info" data-sort="asc" data-name="productSalePrice">
                <span>促销价</span>
                <span class="orderByDesc"></span>
                <span class="orderByAsc orderBySelect"></span>
            </th>
            <th class="data_info" data-sort="asc" data-name="productCreateDate">
                <span>创建时间</span>
                <span class="orderByDesc"></span>
                <span class="orderByAsc orderBySelect"></span>
            </th>
            <th class="data_info" data-sort="asc" data-name="productIsEnabled">
                <span>上架状态</span>
                <span class="orderByDesc"></span>
                <span class="orderByAsc orderBySelect"></span>
            </th>
            <th>操作</th>
            <th hidden>产品ID</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.productList}" var="product">
            <tr>
                <td><input type="checkbox" class="cbx_select" id="cbx_product_select_${product.productId}"><label for="cbx_product_select_${product.productId}"></label></td>
                <td title="${product.productName}">${product.productName}</td>
                <td title="${product.productTitle}">${product.productTitle}</td>
                <td title="${product.productPrice}">${product.productPrice}</td>
                <td title="${product.productSalePrice}">${product.productSalePrice}</td>
                <td title="${product.productCreateDate}">${product.productCreateDate}</td>
                <td>
                    <c:choose>
                        <c:when test="${product.productIsEnabled==0}"><span class="td_success" title="产品正常销售中">销售中</span></c:when>
                        <c:when test="${product.productIsEnabled==2}"><span class="td_warn" title="产品显示在主页促销中">促销中</span></c:when>
                        <c:otherwise><span class="td_error" title="产品缺货或违规停售中">停售中</span></c:otherwise>
                    </c:choose>
                </td>
                <td><span class="td_special" title="查看产品详情"><a href="javascript:void(0)" onclick="getChildPage(this)">详情</a></span></td>
                <td hidden><span class="productId">${product.productId}</span></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <%@ include file="include/page.jsp" %>
    <div class="loader"></div>
</div>
</body>
</html>
