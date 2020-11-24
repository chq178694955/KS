<%--
  Created by IntelliJ IDEA.
  User: 17869
  Date: 2020/4/10
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:;">伺服电机</a>
        <a><cite>我的指标</cite></a>
    </span>
    <a class="layui-btn layui-btn-sm layui-btn-primary" style="line-height:2.5em;margin-top:4px;float:right;" onclick="goBack()" title="<spring:message code="com.goBack"/>">
        <i class="iconfont">&#xe650;</i>
    </a>
</div>

<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <form class="layui-form layui-form-pane" lay-filter="updateForm" action="${ctx}/em/myIndex/update">
                <div class="layui-card">
                    <div class="layui-card-header">修改指标分类</div>
                    <div class="layui-card-body">
                        <fieldset class="layui-elem-field">
                            <legend>用户自定义的指标需要给他起一个唯一名称，用于后面计算时进行选择区分</legend>
                            <div class="layui-field-box">
                                <div class="layui-form-item">
                                    <div class="layui-inline">
                                        <label class="layui-form-label" style="width: 170px;">自定义指标分组名称</label>
                                        <div class="layui-input-inline">
                                            <input type="hidden" name="groupId" value="${group.id}"/>
                                            <input type="text" name="groupName" value="${group.name}" lay-verify="required"  placeholder="请输入自定义指标分组名称" autocomplete="off" class="layui-input">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </fieldset>
                        <c:forEach var="category" items="${categories}">
                            <fieldset class="layui-elem-field">
                                <legend>${category.name}</legend>
                                <div class="layui-field-box">
                                    <c:forEach var="template" items="${templates}">
                                        <c:if test="${category.id == template.categoryId}">
                                            <blockquote class="layui-elem-quote">
                                                <i class="layui-icon" style="color: #FF5722;">&#xe642;</i>&nbsp;&nbsp;<span style="color: #FF5722;font-weight: bold;">${template.name}</span>
                                            </blockquote>
                                            <!-- 用户自定义指标赋值 -->
                                            <c:forEach var="detail" items="${details}">
                                                <c:if test="${detail.indexId == template.id}">
                                                    <!-- 符合条件的指标才进行渲染 -->
                                                    <input type="hidden" name="id_${template.id}" value="${detail.id}"/>
                                                    <!-- 赋值 -->
                                                    <div class="layui-form-item">
                                                        <div class="layui-inline">
                                                            <label class="layui-form-label" style="width: 170px;">自定义指标</label>
                                                            <div class="layui-input-inline">
                                                                <!-- 表单数据名称采用 "val_" 加 原始指标id的方式，后台按照此规则进行解析 -->
                                                                <input type="text" name="val_${template.id}" value="${detail.val}" lay-verify="required|number"  placeholder="请输入自定义指标值" autocomplete="off" class="layui-input">
                                                            </div>
                                                        </div>

                                                        <div class="layui-inline">
                                                            <label class="layui-form-label" style="width: 170px;">自定义权重</label>
                                                            <div class="layui-input-inline">
                                                                <!-- 表单数据名称采用 "weight_" 加 原始指标id的方式，后台按照此规则进行解析 -->
                                                                <input type="text" name="weight_${template.id}" value="${detail.weight}" lay-verify="required|number"  placeholder="请输入自定义权重" autocomplete="off" class="layui-input">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:if>
                                            </c:forEach>
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </fieldset>
                        </c:forEach>

                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit="" lay-filter="updateForm"><spring:message code="com.btn.save"/></button>
                                <button class="layui-btn layui-btn-primary" type="reset"><spring:message code="com.btn.reset"/></button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    layui.use(['element','form'],function(){
        var element = layui.element;
        var form = layui.form;
        element.render('breadcrumb');

        form.render();

        form.on('submit(updateForm)',function(data){
            console.log(data.elem);
            console.log(data.form);
            console.log(data.field);
            $.ajax({
                url: data.form.action,
                data: data.field,
                dataType: 'json',
                success: function(result){
                    if(result.code == 0){
                        $('#query_${menuId}').click();
                        layer.close(Frame.Layer.layerIndex);
                    }
                    Frame.alert(result.msg);
                }
            })
            return false;
        });
        form.render();
    });

    function goBack(){
        Frame.modMainTab({
            id:'${menuId}',
            url:APP_ENV + '/em/myIndex/toMain',
            params:{
                menuId:'${menuId}'
            }
        });
    }
</script>