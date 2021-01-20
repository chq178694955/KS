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
        <legend><a name="color-design" style="font-weight: bold;">软件说明</a></legend>
        <div class="layui-card">
            <div class="layui-card-body ">
                <blockquote class="layui-elem-quote">
                    <p style="color: #1E9FFF;font-weight: bold;">
                        本软件用于机器人交流伺服电机性能评估！<br/><br/>
                        请您下载Excel表格，按照格式填入数据并导入。<br/><br/>
                        您也可以选择使用数据库中已有的数据，这样您将不用额外输入任何数据。
                    </p>
                    <%--<span class="x-red">test</span>当前时间:2018-04-25 20:50:53--%>
                </blockquote>
            </div>
        </div>
    </fieldset>

    <fieldset class="layui-elem-field layui-field-title site-title">
        <legend style="font-weight: bold;">操作说明</legend>
        <div class="layui-card">
            <div class="layui-card-body">
                <blockquote class="layui-elem-quote">
                    <p style="color: #1E9FFF;font-weight: bold;">
                        1.性能评估必须有可以试验的数据作为支撑，数据可以在<span class="x-red">首页</span>的Excel导入按钮将数据导入到系统当中。<br/><br/>
                        2.导入数据的Excel<span class="x-red">文件名</span>会作为本次数据的名称保存到系统当中，请取自己理解的文件名。<span class="x-red">相同文件名导入的数据会对系统已存在的数据进行覆盖。</span><br/><br/>
                        3.本系统预设了基本参数值，<span class="x-red">非专业人士要请勿修改</span>。如果对于基本参数不满意可以自行添加自己需要的即可。<br/><br/>
                        4.本系统预设了最优指标以及默认指标权重，<span class="x-red">非专业人士要请勿修改</span>。如果对于预设指标不满意可以自行添加自己需要的即可（每个用户都可以配置属于自己的指标，但系统预设指标属于共用）。<br/><br/>
                        <span class="layui-badge-dot"></span>
                        <span class="layui-badge-dot layui-bg-orange"></span>
                        <span class="layui-badge-dot layui-bg-green"></span>
                        <span class="layui-badge-dot layui-bg-cyan"></span>
                        <span class="layui-badge-dot layui-bg-blue"></span>
                        <span class="layui-badge-dot layui-bg-black"></span>
                        <span class="layui-badge-dot layui-bg-gray"></span>
                        <span class="layui-badge layui-bg-cyan">开始实验吧！</span>
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

    <div class="layui-card">
        <div class="layui-card-body">
            <div class="layui-btn-container">
                <button id="downloadExcelTemplateButton" type="button" class="layui-btn layui-btn-lg layui-btn-normal">下载Excel表格</button>
                <button id="importExcelDataButton" type="button" class="layui-btn layui-btn-lg layui-btn-normal">导入Excel表格</button>
                <%--<button type="button" class="layui-btn layui-btn-lg layui-btn-normal">使用数据库现有数据</button>--%>
            </div>
        </div>
    </div>

    <%--<div class="layui-card">--%>
        <%--<div class="layui-card-header">最近一次测评结果：</div>--%>
        <%--<div class="layui-card-body">--%>
            <%--您好，暂无测评数据。赶快测评吧！--%>
        <%--</div>--%>
    <%--</div>--%>
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

