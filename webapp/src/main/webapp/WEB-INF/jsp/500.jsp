<%--
  Created by IntelliJ IDEA.
  User: 17869
  Date: 2020/3/16
  Time: 9:53
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="/WEB-INF/jsp/common/tags.jsp" %>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <title><spring:message code="com.king.website.name"/></title>
    <link rel="icon" href="${ctx}/static/images/favicon.ico" type="image/x-icon">
    <%@include file="/WEB-INF/jsp/common/include.jsp"%>
</head>
<body>
    <div class="layui-container">
        <div class="fly-panel">
            <div class="fly-none">
                <h2><i class="layui-icon layui-icon-404"></i></h2>
                <p>页面或者数据被<a href=""> 纸飞机 </a>运到火星了，啥都看不到了…</p>
            </div>
        </div>
    </div>
</body>
</html>
