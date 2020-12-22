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
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <form class="layui-form layui-form-pane" lay-filter="addVoteForm" action="${ctx}/keke/vote/add">
                <div class="layui-card">
                    <div class="layui-card-body">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">投票名称</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="title" lay-verify="required"  placeholder="请输入一个投票名称" class="layui-input">
                                </div>
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit="" lay-filter="addVoteForm"><spring:message code="com.btn.save"/></button>
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

        form.on('submit(addVoteForm)',function(data){
            $.ajax({
                url: data.form.action,
                data: data.field,
                dataType: 'json',
                success: function(result){
                    if(result.code == 0){
                        //$('#query_${menuId}').click();
                        Frame.goBack('${menuId}','/keke/vote/toMain');
                        Frame.info(result.msg);
                    }else{
                        Frame.info(result.msg,2);
                    }

                }
            })
            return false;
        });
        form.render();
    });

</script>