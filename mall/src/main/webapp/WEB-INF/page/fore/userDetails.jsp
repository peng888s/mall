<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="include/header.jsp" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
    var contextPath = "${ctx}";
</script>
<head>
    <script src="${ctx}/res/js/fore/fore_userDatiles.js"></script>
    <link href="${ctx}/res/css/fore/fore_userDatiles.css" rel="stylesheet">
    <title>Mall商城mall.com - 个人中心</title>
    <style rel="stylesheet">
        #baseNavigator {
            padding: 22px 0;
            width: 1190px;
            height: 44px;
            margin: auto;
        }

        #baseNavigator img {
            width: 190px;
            margin-top: 8px;
        }

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
    <%@ include file="include/navigator.jsp" %>
    <div class="header">
        <div id="mallLogo">
            <a href="${ctx}"><img
                    src="${ctx}/res/images/fore/WebsiteImage/tmallLogoD.png"><span
                    class="span_tmallRegister">个人中心</span></a>
        </div>
    </div>
</nav>
<div class="content">
    <div class="mt-menu" id="J_MtSideMenu">
        <div class="mt-avatar">
            <img src="${ctx}/res/images/item/userProfilePicture/${requestScope.user.userProfilePictureSrc}"
                 onerror="this.src='${ctx}/res/images/admin/loginPage/default_profile_picture-128x128.png'"
                 width="128px" height="128px">
        </div>
        <div class="mt-menu-tree">
            <p>个人信息</p>
        </div>
    </div>
    <div class="sns-config" id="profile">
        <div class="sns-tab tab-app">
            <span>个人资料</span>
        </div>
        <div class="sns-main">
            <div id="tips-box">
                <label class="font_we">亲爱的</label>
                <b>${requestScope.user.userName}</b>，
                <label  class="font_we">填写真实的资料，有助于好友找到你哦。</label>
            </div>
            <form action="${ctx}/user/update" method="post" id="register_form">
                <div class="form-item">
                    <label class="form-label tsl">当前头像：</label>
                    <ul class="details_picList" id="product_single_list">
                        <li class="details_picList_fileUpload">
                            <img src="${ctx}/res/images/item/userProfilePicture/${requestScope.user.userProfilePictureSrc}"
                                 onerror="this.src='${ctx}/res/images/admin/loginPage/default_profile_picture-128x128.png'"
                                 id="header_image" width="128px" height="128px">
                            <input type="file" onchange="uploadImage(this)" id="userProfilePictureSrc" accept="image/*">
                            <input name="userProfilePictureSrc" id="user_profile_picture_src_value" type="hidden"/>
                        </li>
                    </ul>
                </div>
                <div class="form-item">
                    <label class="form-label tsl">昵称：</label>
                    <input name="userNickName" value="${requestScope.user.userNickName}" id="userNickName"
                           class="form-text err-input" maxlength="20">
                    <span class="form_span"></span>
                </div>
                <div class="form-item">
                    <label class="form-label tsl">真实姓名：</label>
                    <input name="userRealName" value="${requestScope.user.userRealName}" id="userRealName"
                           class="form-text err-input" maxlength="20">
                    <span class="form_span"></span>
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
                    <label class="form-label tsl">性别：</label>
                    <input name="userGender" type="radio" id="form_radion" value="0"
                           <c:if test="${requestScope.user.userGender == 0}">checked="checked"</c:if>><span
                        class="radio_value">男</span>
                    <input name="userGender" type="radio" id="form_radions" value="1"
                           <c:if test="${requestScope.user.userGender == 1}">checked="checked"</c:if>><span
                        class="radio_value">女</span>
                </div>
                <div class="form-item">
                    <label class="form-label tsl">生日：</label>
                    <input type="date" name="userBirthday" id="userBirthday" class="form-text err-input"
                           value="${requestScope.user.userBirthday}" maxlength="20">
                    <span class="form_span"></span>
                </div>
                <div class="form-item last-item">
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
                    <select name="userAddress" class="selectpicker" id="select_user_address_district" data-size="8" data-live-search="true">
                        <c:forEach items="${requestScope.districtList}" var="address" varStatus="i">
                            <option value="${address.addressAreaId}"
                                    <c:if test="${requestScope.districtAddressId==address.addressAreaId}">selected</c:if>>${address.addressName}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="form-item">
                    <input type="submit" id="register_sub" class="btns btn-large tsl" value="提 交"/>
                </div>
            </form>
        </div>
    </div>
</div>
<%@include file="include/footer.jsp" %>
<link href="${ctx}/res/css/fore/fore_foot_special.css" rel="stylesheet"/>
</body>

