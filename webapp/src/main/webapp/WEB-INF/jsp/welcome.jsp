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
        <legend><a name="color-design">软件说明</a></legend>
    </fieldset>
    <div class="layui-col-md12">
        <div class="layui-card">
            <div class="layui-card-body ">
                <blockquote class="layui-elem-quote">
                    <h3>本软件用于机器人交流伺服电机性能评估！</h3>
                    <h3>请您下载Excel表格，按照格式填入数据并导入。</h3>
                    <h3>您也可以选择使用数据库中已有的数据，这样您将不用额外输入任何数据。</h3>
                    <%--<span class="x-red">test</span>当前时间:2018-04-25 20:50:53--%>
                </blockquote>
            </div>
        </div>
        <div class="layui-card">
            <div class="layui-card-body">
                <div class="layui-btn-container">
                    <button id="downloadExcelTemplateButton" type="button" class="layui-btn layui-btn-lg layui-btn-normal">下载Excel表格</button>
                    <button id="importExcelDataButton" type="button" class="layui-btn layui-btn-lg layui-btn-normal">导入Excel表格</button>
                    <button type="button" class="layui-btn layui-btn-lg layui-btn-normal">使用数据库现有数据</button>
                </div>
            </div>
        </div>

        <div class="layui-card">
            <div class="layui-card-header">最近一次测评结果：</div>
            <div class="layui-card-body">
                您好，暂无测评数据。赶快测评吧！
            </div>
        </div>

    </div>
</div>

<script>
    $(document).ready(function(){
        $("#downloadExcelTemplateButton").bind("click",function(){
            window.location.href=APP_ENV+"/em/home/downloadExcelTemplate"
        });

        $("#importExcelDataButton").bind("click",function(){
            Frame.loadPage('${menuId}','em/home/importExcelDataPage?menuId=-999',{},'导入实验数据',600,200);
        });
    });
</script>

