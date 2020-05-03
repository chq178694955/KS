<%--
  Created by IntelliJ IDEA.
  User: 17869
  Date: 2020/4/10
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:;"><spring:message code="sys.menu.sysMgr"/></a>
        <a><cite>Echarts-2.2.7</cite></a>
    </span>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body">
                    <div id="echarts_demo" style="height: 300px;"></div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function(){
        WebUtils.Chart.createRemoteChart('echarts_demo',APP_ENV + 'sys/echarts/getChart',{});

        // var myChart = echarts.init(document.getElementById('echarts_demo'),'macarons');
        // $.ajax({
        //     url: APP_ENV + 'sys/echarts/getChart',
        //     dataType:'json',
        //     success: function(option){
        //         debugger;
        //         myChart.setOption(option);
        //     }
        // })


    });
</script>