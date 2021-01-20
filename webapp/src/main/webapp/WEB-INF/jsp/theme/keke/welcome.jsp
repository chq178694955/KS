<%@ include file="/WEB-INF/jsp/common/tags.jsp" %>
<%--
  Created by IntelliJ IDEA.
  User: 17869
  Date: 2020/4/2
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<div class="layui-fluid">
    <fieldset class="layui-elem-field layui-field-title site-title">
        <legend>系统说明</legend>
        <div class="layui-card">
            <div class="layui-card-body">
                <blockquote class="layui-elem-quote">
                    <p style="color: #1E9FFF;font-weight: bold;">
                        <span class="layui-badge-dot"></span>
                        <span class="layui-badge-dot layui-bg-orange"></span>
                        <span class="layui-badge-dot layui-bg-green"></span>
                        <span class="layui-badge-dot layui-bg-cyan"></span>
                        <span class="layui-badge-dot layui-bg-blue"></span>
                        <span class="layui-badge-dot layui-bg-black"></span>
                        <span class="layui-badge-dot layui-bg-gray"></span>
                        <span class="layui-badge layui-bg-cyan">开始体验吧！</span>
                        <span class="layui-badge-dot layui-bg-gray"></span>
                        <span class="layui-badge-dot layui-bg-black"></span>
                        <span class="layui-badge-dot layui-bg-blue"></span>
                        <span class="layui-badge-dot layui-bg-cyan"></span>
                        <span class="layui-badge-dot layui-bg-green"></span>
                        <span class="layui-badge-dot layui-bg-orange"></span>
                        <span class="layui-badge-dot"></span>
                    </p>
                </blockquote>
            </div>
        </div>
    </fieldset>
</div>

<script>
    $(document).ready(function(){
        <%--$("#downloadExcelTemplateButton").bind("click",function(){--%>
        <%--    window.location.href=APP_ENV+"/em/home/downloadExcelTemplate"--%>
        <%--});--%>

        <%--$("#importExcelDataButton").bind("click",function(){--%>
        <%--    Frame.loadPage('${menuId}','em/home/importExcelDataPage?menuId=-999',{},'导入实验数据',600,200);--%>
        <%--});--%>
    });
</script>

