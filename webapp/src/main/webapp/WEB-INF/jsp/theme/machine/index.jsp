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
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1"/>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <title><spring:message code="com.king.website.name"/></title>
    <link rel="icon" href="${ctx}/static/images/favicon.ico" type="image/x-icon">
    <%@include file="/WEB-INF/jsp/common/include_machine.jsp"%>
    <script>
        function demoTest(){
            //Frame.changeTab(0);//手动切换TAB
            //Frame.delMainTab(Frame.getTabId());//删除当前TAB
            //alert(WebUtils.hasPermission('sys/user/toMain1'));
            Frame.addMainTab({id:1,title:{icon:'&#xe68e;',name:'测试'},url:APP_ENV + '/welcome1'});
        }
        function logout(){
            $.ajax({
                url: APP_ENV + '/logout',
                dataType:'json',
                success: function(result){
                    if(result.code == 0){
                        location.href=APP_ENV + '/login';
                    }else{
                        layer.alert(result.msg,{
                            shade:0,
                            icon:5,
                            offset:'rb',
                            time:3000,
                            anim:2
                        });
                    }
                }
            });
        }

        function showOrHideVersion(){
            var display = $('#layui-layer10').css('display');
            if(display == 'none'){
                $('#layui-layer10').show();
            }else{
                $('#layui-layer10').hide();
            }
        }
    </script>
</head>
<body>
<!-- 顶部开始 -->
<div class="container">
    <div class="logo">
<%--        <a href="${ctx}/index" class="king-font-default" title="<spring:message code="com.login.title"/>"><spring:message code="com.login.title"/></a>--%>
<%--        <img src="${ctx}/static/images/hunan_university.jfif" width="45px" style="float: left;"/>--%>
        <a href="${ctx}/index" title="湖南大学">
            <img src="${ctx}/static/images/hn_university_logo.png" width="200px"/>
<%--            <img src="${ctx}/static/images/keke_logo.png" width="200px"/>--%>
        </a>
    </div>
    <div class="left_open">
        <a><i title="展开左侧栏" class="iconfont">&#xe699;</i></a>
    </div>
    <ul class="layui-nav left fast-add" lay-filter="">
        <li class="layui-nav-item">
            伺服电机性能评估系统v1.0
<%--            可可小城投票管理系统v1.0--%>
        </li>
        <%--<li class="layui-nav-item">--%>
            <%--<a href="javascript:;" layadmin-event="fullscreen">--%>
                <%--<i class="layui-icon layui-icon-screen-full"></i>--%>
            <%--</a>--%>
        <%--</li>--%>
        <%--<li class="layui-nav-item" lay-unselect="">--%>
            <%--<a href="${ctx}/index" title="刷新">--%>
                <%--<i class="layui-icon layui-icon-refresh-3"></i>--%>
            <%--</a>--%>
        <%--</li>--%>
        <%--<li class="layui-nav-item layui-hide-xs" lay-unselect="">--%>
            <%--<input type="text" placeholder="搜索..." autocomplete="off" class="layui-input layui-input-search" layadmin-event="serach" lay-action="template/search/keywords=">--%>
        <%--</li>--%>
        <%--<li class="layui-nav-item">--%>
            <%--<a href="javascript:;"><i class="iconfont layui-icon-taobao"></i>+新增</a>--%>
            <%--<dl class="layui-nav-child">--%>
                <%--<!-- 二级菜单 -->--%>
                <%--<dd>--%>
                    <%--<a onclick="xadmin.open('最大化','http://www.baidu.com','','',true)">--%>
                        <%--<i class="iconfont">&#xe6a2;</i>弹出最大化</a></dd>--%>
                <%--<dd>--%>
                    <%--<a onclick="xadmin.open('弹出自动宽高','http://www.baidu.com')">--%>
                        <%--<i class="iconfont">&#xe6a8;</i>弹出自动宽高</a></dd>--%>
                <%--<dd>--%>
                    <%--<a onclick="xadmin.open('弹出指定宽高','http://www.baidu.com',500,300)">--%>
                        <%--<i class="iconfont">&#xe6a8;</i>弹出指定宽高</a></dd>--%>
                <%--<dd>--%>
                    <%--<a onclick="xadmin.add_tab('在tab打开','member-list.html')">--%>
                        <%--<i class="iconfont">&#xe6b8;</i>在tab打开</a></dd>--%>
                <%--<dd>--%>
                    <%--<a onclick="xadmin.add_tab('在tab打开刷新','member-del.html',true)">--%>
                        <%--<i class="iconfont">&#xe6b8;</i>在tab打开刷新</a></dd>--%>
            <%--</dl>--%>
        <%--</li>--%>
        <%--<span class="layui-nav-bar" style="left: 198px; top: 48px; width: 0px; opacity: 0;"></span>--%>
    </ul>
    <ul class="layui-nav right" lay-filter="">
        <%--<li class="layui-nav-item" lay-unselect="">--%>
            <%--<a lay-href="app/message/" layadmin-event="message">--%>
                <%--<i class="layui-icon layui-icon-notice"></i>--%>

                <%--<!-- 如果有新消息，则显示小圆点 -->--%>
                <%--<script type="text/html" template="" lay-url="./json/message/new.js">--%>
                    <%--{{# if(d.data.newmsg){ }}--%>
                    <%--<span class="layui-badge-dot"></span>--%>
                    <%--{{# } }}--%>
                <%--</script>  <span class="layui-badge-dot"></span>--%>

            <%--</a>--%>
        <%--</li>--%>
        <%--<li class="layui-nav-item layui-hide-xs" lay-unselect="">--%>
            <%--<a href="javascript:;" layadmin-event="theme">--%>
                <%--<i class="layui-icon layui-icon-theme"></i>--%>
            <%--</a>--%>
        <%--</li>--%>
        <%--<li class="layui-nav-item layui-hide-xs" lay-unselect="">--%>
            <%--<a href="javascript:;" layadmin-event="note">--%>
                <%--<i class="layui-icon layui-icon-note"></i>--%>
            <%--</a>--%>
        <%--</li>--%>
        <li class="layui-nav-item">
            <a href="javascript:;">
                <cite>[${userInfo != null && userInfo.name != null ? userInfo.name : ''}]</cite>
                <span class="layui-nav-more"></span>
            </a>
            <dl class="layui-nav-child">
                <%--<!-- 二级菜单 -->--%>
                <%--<dd>--%>
                    <%--<a onclick="demoTest()">--%>
                        <%--<i class="iconfont">&#xe6a7;</i>--%>
                        <%--<cite>测试链接</cite>--%>
                    <%--</a>--%>
                <%--</dd>--%>
                <%--<dd>--%>
                    <%--<a onclick="xadmin.open('切换帐号','http://www.baidu.com')">--%>
                        <%--<i class="iconfont">&#xe6a7;</i>--%>
                        <%--<cite>个人信息</cite>--%>
                    <%--</a>--%>
                <%--</dd>--%>
                <%--<hr>--%>
                <dd>
                    <a href="javascript:;" onclick="logout()">
                        <i class="iconfont">&#xe6a7;</i>
                        <cite><spring:message code="com.login.logout"/></cite>
                    </a>
                </dd>
            </dl>
        </li>
        <%--<li class="layui-nav-item">--%>
            <%--<a href="javascript:;" layadmin-event="more" onclick="showOrHideVersion();"><i class="layui-icon layui-icon-more-vertical"></i></a>--%>
        <%--</li>--%>
        <%--<span class="layui-nav-bar" style="left: 29px; top: 48px; width: 0px; opacity: 0;"></span>--%>
    </ul>
</div>
<!-- 顶部结束 -->
<!-- 中部开始 -->
<!-- 左侧菜单开始 -->
<div class="left-nav">
    <div id="side-nav">
        <ul id="nav">
            <%--<li>--%>
                <%--<a href="javascript:;">--%>
                    <%--<i class="iconfont left-nav-li" lay-tips="会员管理">&#xe6b8;</i>--%>
                    <%--<cite>会员管理</cite>--%>
                    <%--<i class="iconfont nav_right">&#xe697;</i></a>--%>
                <%--<ul class="sub-menu">--%>
                    <%--<li>--%>
                        <%--<a onclick="xadmin.add_tab('统计页面','welcome1')">--%>
                            <%--<i class="iconfont">&#xe6a7;</i>--%>
                            <%--<cite>统计页面</cite></a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a onclick="xadmin.add_tab('会员列表(静态表格)','member-list.html')">--%>
                            <%--<i class="iconfont">&#xe6a7;</i>--%>
                            <%--<cite>会员列表(静态表格)</cite></a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a onclick="xadmin.add_tab('会员列表(动态表格)','member-list1.html',true)">--%>
                            <%--<i class="iconfont">&#xe6a7;</i>--%>
                            <%--<cite>会员列表(动态表格)</cite></a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a onclick="xadmin.add_tab('会员删除','member-del.html')">--%>
                            <%--<i class="iconfont">&#xe6a7;</i>--%>
                            <%--<cite>会员删除</cite></a>--%>
                    <%--</li>--%>
                    <%--<li>--%>
                        <%--<a href="javascript:;">--%>
                            <%--<i class="iconfont">&#xe70b;</i>--%>
                            <%--<cite>会员管理</cite>--%>
                            <%--<i class="iconfont nav_right">&#xe697;</i></a>--%>
                        <%--<ul class="sub-menu">--%>
                            <%--<li>--%>
                                <%--<a onclick="xadmin.add_tab('会员删除','member-del.html')">--%>
                                    <%--<i class="iconfont">&#xe6a7;</i>--%>
                                    <%--<cite>会员删除</cite></a>--%>
                            <%--</li>--%>
                            <%--<li>--%>
                                <%--<a onclick="xadmin.add_tab('等级管理','member-list1.html')">--%>
                                    <%--<i class="iconfont">&#xe6a7;</i>--%>
                                    <%--<cite>等级管理</cite></a>--%>
                            <%--</li>--%>
                        <%--</ul>--%>
                    <%--</li>--%>
                <%--</ul>--%>
            <%--</li>--%>
        </ul>
    </div>
</div>
<!-- <div class="x-slide_left"></div> -->
<!-- 左侧菜单结束 -->
<!-- 右侧主体开始 -->
<div class="page-content">
    <div class="layui-tab tab" lay-filter="mainFrameTab" lay-allowclose="true" id="mainFrameTab">
        <ul class="layui-tab-title">
            <li class="home layui-this" lay-id="mainFrameHomePage">
                <i class="layui-icon">&#xe68e;</i><span style="font-weight: bold;">首页</span>
            </li>
        </ul>
        <div class="layui-unselect layui-form-select layui-form-selected" id="tab_right">
            <dl>
                <dd data-type="this">关闭当前</dd>
                <dd data-type="other">关闭其它</dd>
                <dd data-type="all">关闭全部</dd>
            </dl>
        </div>
        <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <div id="mainFrameHomePage" style="height: 100%;overflow-y: auto"></div>
            </div>
        </div>
    </div>
</div>
<div class="page-content-bg"></div>
<style id="theme_style"></style>
<div class="layui-layer layui-layer-page layui-anim layui-anim-rl layui-layer-adminRight" id="layui-layer10" type="page" times="10" showtime="0" contype="string" style="z-index: 19891024; width: 300px; height: 100%; top: 45px; left: 1236px; display: none;">
    <div id="LAY_adminPopupAbout" class="layui-layer-content"><div class="layui-card-header">版本信息</div>
        <div class="layui-card-body layui-text layadmin-about">
            <p>当前版本：layuiAdmin-v1.4.0 pro</p>
            <p>基于框架：layui-v2.5.6</p>
            <div class="layui-btn-container">
                <a href="http://www.layui.com/admin/" target="_blank" class="layui-btn layui-btn-danger">获取授权</a>
                <a href="http://fly.layui.com/download/layuiAdmin/" target="_blank" class="layui-btn">下载新版</a>
            </div>
        </div>
        <div class="layui-card-header">关于版权</div>
        <div class="layui-card-body layui-text layadmin-about">
            <blockquote class="layui-elem-quote" style="border: none;">
                layuiAdmin 受国家计算机软件著作权保护（登记号：<a href="http://cdn.layui.com/images/layuiAdmin-show.jpg" target="_blank">2018SR410669</a>），必须经<a href="https://www.layui.com/admin/" target="_blank">官网</a>授权才可获得源文件使用权。不得恶意分享产品源代码、二次转售等，违者将承担相应的法律责任。
                <br><br>详见：<a href="https://fly.layui.com/jie/26280/" target="_blank">《layui 付费产品服务条款》</a>
            </blockquote>
            <p>© 2020 <a href="http://www.layui.com/">layui.com</a> 版权所有</p>
        </div>
    </div>
    <span class="layui-layer-setwin"></span><span class="layui-layer-resize"></span>
</div>

<script>
    layui.use('element',function(){
        var element = layui.element;
        $(document).ready(function(){
            //Frame.init();
            Frame.Menu.init();//菜单初始化
            loadWelcome();
        });

        function loadWelcome(){
            $.ajax({
                url: APP_ENV + '/welcome',
                success: function (html) {
                    // $('#mainFrameContent').find('.layui-tab-item:first').html(html);
                    $('#mainFrameHomePage').html(html);
                    element.render('tab','mainFrameTab');
                }
            });

            //Frame.addMainTab({id:1,title:{icon:'&#xe68e;',name:'测试'},url:APP_ENV + '/welcome1'});
            //Frame.addMainTab({id:0,title:{icon:'&#xe68e;',name:'首页'},url:APP_ENV + '/welcome'});
            // Frame.changeTab(0);
        }
    });
</script>
</body>
</html>
