<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<script type="text/javascript">
    var contextPath = "${ctx}";
</script>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="${ctx}/res/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="${ctx}/res/css/bootstrap-select.min.css"/>
    <script src="${ctx}/res/js/jquery-1.11.3.min.js"></script>
    <script src="${ctx}/res/js/bootstrap.min.js"></script>
    <script src="${ctx}/res/js/bootstrap-select.min.js"></script>
    <script src="${ctx}/res/js/defaults-zh_CN.min.js"></script>
    <script src="${ctx}/res/js/echarts.js"></script>
    <script src="${ctx}/res/js/base.js"></script>
</head>