<%--
  Created by IntelliJ IDEA.
  User: 17869
  Date: 2020/3/27
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="version" value="<%=new java.util.Date().getTime() %>"/>


