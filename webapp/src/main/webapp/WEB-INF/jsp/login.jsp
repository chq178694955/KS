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
    <style>
        .loginItemContainer{
            position: relative;
        }
        .loginItemContainer .loginIcon{
            position: absolute;
            left: 2px;
            top: 2px;
            display: inline-block;
            z-index: 1;
            font-size: 46px;
            color: #999999;
        }
    </style>
</head>
<body class="login-bg">

<div class="login layui-anim layui-anim-up">
<%--    <div class="message"><spring:message code="com.login.title"/></div>--%>
    <div class="message">伺服电机性能评估系统</div>
    <div id="darkbannerwrap"></div>

    <form method="post" class="layui-form" >
        <div class="layui-form-item loginItemContainer">
            <i class="layui-icon layui-icon-username loginIcon"></i>
            <input id="username" name="username" placeholder="<spring:message code="com.login.username"/>" type="text" lay-verify="required|username" lay-verType="tips" class="layui-input" >
        </div>
        <div class="layui-form-item loginItemContainer">
            <i class="layui-icon layui-icon-password loginIcon"></i>
            <input name="password" placeholder="<spring:message code="com.login.password"/>" type="password" lay-verify="required|password" lay-verType="tips" class="layui-input">
        </div>
        <input value="<spring:message code="com.login.loginBtn"/>" lay-submit lay-filter="login" style="width:100%;" type="submit">
    </form>
</div>

<script>
    $(function  () {
        layui.use('form', function(){
            var form = layui.form;

            //表单验证
            form.verify({
                username: function(value, item){
                    if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
                        return '用户名不能有特殊字符';
                    }
                    if(/(^\_)|(\__)|(\_+$)/.test(value)){
                        return '用户名首尾不能出现下划线\'_\'';
                    }
                    if(/^\d+\d+\d$/.test(value)){
                        return '用户名不能全为数字';
                    }
                },
                password: [
                    /^[\S]{6,12}$/
                    ,'密码必须6到12位，且不能出现空格'
                ]
            });

            //监听提交
            form.on('submit(login)', function(data){
                $.ajax({
                    url: data.form.action,
                    type: data.form.method,
                    data: data.field,
                    dataType: 'json',
                    success: function(result){
                        if(result.code == 0){
                            window.location= APP_ENV + '/index';
                        }else{
                            layer.alert(result.msg,{
                                shade:0,
                                icon:5,
                                offset:'rb',
                                time:3000,
                                anim:2
                            });
                            // layer.open({
                            //     type:4,
                            //     content:[result.msg,'#username'],
                            //     shade:0,
                            //     time:2000
                            // })
                        }
                    }
                });
                return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
            });
        });
    })
</script>
<!-- 底部结束 -->
</body>
</html>
