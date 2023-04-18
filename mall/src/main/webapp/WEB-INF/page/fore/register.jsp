<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="include/header.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
    var contextPath = "${ctx}";
</script>
<head>
    <script src="${ctx}/res/js/fore/fore_register.js"></script>
    <link href="${ctx}/res/css/fore/fore_register.css" rel="stylesheet">
    <title>Mall商城mall.com - 网上注册</title>
    <script>
        $(function () {
            $("span.address_province").text($("#select_order_address_province").find("option:selected").text());
            $("span.address_city").text($("#select_order_address_city").find("option:selected").text());
            $("span.address_district").text($("#select_order_address_district").find("option:selected").text());
        })
    </script>
    <style rel="stylesheet">
        #nav {
            width: auto;
            height: 32px;
            font-family: "Microsoft YaHei UI", Tahoma, serif;
            font-size: 12px;
            position: relative !important;
            background: #f2f2f2;
            z-index: 999;
            border-bottom: 1px solid #e5e5e5;
        }
    </style>
</head>
<body>
<nav>
    <div class="header">
        <div id="mallLogo">
            <a href="${ctx}"><img
                    src="${ctx}/res/images/fore/WebsiteImage/tmallLogoD.png"><span
                    class="span_tmallRegister">用户注册${ctx}111</span></a>
        </div>
    </div>
</nav>
<div class="content">
    <div class="steps">
        <div class="steps_main">
            <span class="steps_tsl">填写账号信息</span>
        </div>
    </div>
    <div class="form-list">
        <div class="form-item">
            <label class="form-label form-label-b tsls">设置会员名</label>
        </div>
        <div class="form-item">
            <label class="form-label tsl">用户名：</label>
            <input name="userName" id="userName" class="form-text err-input" placeholder="请输入用户名" maxlength="20">
            <span class="form_span"></span>
        </div>
        <div class="form-item">
            <label class="form-label form-label-b tsls">设置登录密码</label>
            <label class="form-label tsl">登录时验证，保护账号信息</label>
        </div>
        <div class="form-item">
            <label class="form-label tsl">登录密码：</label>
            <input name="userPassword" type="password" id="userPassword" class="form-text err-input"
                   placeholder="请设置登录密码" maxlength="20">
            <span class="form_span"></span>
        </div>
        <div class="form-item">
            <label class="form-label tsl">确认密码：</label>
            <input name="user_password_one" type="password" id="user_password_one" class="form-text err-input"
                   placeholder="请再次输入你的密码" maxlength="20">
            <span class="form_span"></span>
        </div>
        <div class="form-item">
            <label class="form-label form-label-b tsls">填写基本信息</label>
        </div>
        <div class="form-item">
            <label class="form-label tsl">昵称：</label>
            <input name="userNickName" id="userNickName" class="form-text err-input" placeholder="请输入昵称"
                   maxlength="20">
            <span class="form_span"></span>
        </div>
        <div class="form-item">
            <label class="form-label tsl">性别：</label>
            <input name="userGender" type="radio" id="form_radion" value="0" checked="checked">男
            <input name="userGender" type="radio" id="form_radions" value="1">女
        </div>
        <div class="form-item">
            <label class="form-label tsl">出生日期：</label>
            <input type="date" name="userBirthday" id="userBirthday" class="form-text err-input"/>
            <span class="form_span"></span>
        </div>
        <div class="form-item">
            <label class="form-label tsl">居住地址：</label>
            <select class="selectpicker" id="select_user_address_province" data-size="8" data-live-search="true">
                <c:forEach items="${requestScope.addressList}" var="address" varStatus="i">
                    <option value="${address.addressAreaId}"
                            <c:if test="${requestScope.addressId==address.addressAreaId}">selected</c:if>>${address.addressName}</option>
                </c:forEach>
            </select>
            <select class="selectpicker" id="select_user_address_city" data-size="8" data-live-search="true">
                <c:forEach items="${requestScope.cityList}" var="address" varStatus="i">
                    <option value="${address.addressAreaId}"
                            <c:if test="${requestScope.cityAddressId==address.addressAreaId}">selected</c:if>>${address.addressName}</option>
                </c:forEach>
            </select>
            <select name="userAddress" class="selectpicker" id="select_user_address_district" data-size="8"
                    data-live-search="true">
                <c:forEach items="${requestScope.districtList}" var="address" varStatus="i">
                    <option value="${address.addressAreaId}"
                            <c:if test="${requestScope.districtAddressId==address.addressAreaId}">selected</c:if>>${address.addressName}</option>
                </c:forEach>
            </select>
        </div>
        <div class="form-item">
            <input type="button" id="register_sub" class="btns btn-large tsl" value="注 册"/>
        </div>
    </div>
</div>
<%@include file="include/footer.jsp" %>
<link href="${ctx}/res/css/fore/fore_foot_special.css" rel="stylesheet"/>
<div class="msg">
    <span>注册成功，跳转到登陆页面</span>
</div>
</body>

