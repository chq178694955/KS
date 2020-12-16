<%--
  Created by IntelliJ IDEA.
  User: 17869
  Date: 2020/4/10
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:;">评估系统</a>
        <a><cite>指标分类</cite></a>
    </span>
    <a class="layui-btn layui-btn-sm layui-btn-primary" style="line-height:2.5em;margin-top:4px;float:right;" onclick="Frame.goBack('${menuId}','/em/indexMgr/toMain');" title="<spring:message code="com.goBack"/>">
        <i class="iconfont">&#xe650;</i><spring:message code="com.goBack"/>
    </a>
</div>

<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <form class="layui-form layui-form-pane" lay-filter="updateForm" action="${ctx}/em/indexMgr/updateCategory">
                <input type="hidden" name="id" value="${category.id}"/>
                <div class="layui-card">
                    <div class="layui-card-header">修改指标分类</div>
                    <div class="layui-card-body">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label" style="width: 170px;">分类名称</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="name" value="${category.name}" lay-verify="required" lay-verType="tips" placeholder="请输入分类名称" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                        </div>
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
                        Frame.goBack('${menuId}','/em/indexMgr/toMain');
                    }
                    Frame.alert(result.msg);
                }
            })
            return false;
        });
        form.render();
    });

</script>