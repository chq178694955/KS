<%--
  Created by IntelliJ IDEA.
  User: 17869
  Date: 2020/4/11
  Time: 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:;"><spring:message code="sys.menu.sysMgr"/></a>
        <a><cite><spring:message code="sys.menu.roleMgr"/></cite></a>
    </span>
</div>

<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body">
                    <form class="layui-form" action="${ctx}/sys/user/update">
                        <input type="hidden" name="id" value="${user.id}">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label"><spring:message code="sys.user.field.name"/></label>
                                <div class="layui-input-inline">
                                    <input type="text" id="name" name="name" value="${user.name}" class="layui-input" lay-verify="required" lay-verType="tips">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label"><spring:message code="sys.user.field.idCardNo"/></label>
                                <div class="layui-input-inline">
                                    <input type="text" id="idCardNum" name="idCardNum" value="${user.idCardNum}" class="layui-input" lay-verify="" lay-verType="tips">
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label"><spring:message code="sys.user.field.state"/></label>
                                <div class="layui-input-inline">
                                    <select name="state">
                                        <option value="0" ${user.state == 0 ? 'selected' :''}><spring:message code="sys.user.field.state.normal"/></option>
                                        <option value="1" ${user.state == 1 ? 'selected' :''}><spring:message code="sys.user.field.state.locked"/></option>
                                    </select>
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label"><spring:message code="sys.user.file.roleName"/></label>
                                <div class="layui-input-inline">
                                    <select id="roleId" name="roleId">

                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit="" lay-filter="addForm"><spring:message code="com.btn.modify"/></button>
                                <button class="layui-btn layui-btn-primary" type="reset"><spring:message code="com.btn.reset"/></button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    layui.use(['element','form'],function(){
        var element = layui.element;
        var form = layui.form;
        element.render('breadcrumb');

        loadRoles(function(){
            form.render('select');
        })

        form.on('submit(addForm)',function(data){
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

    function loadRoles(callback){
        $.ajax({
            url: APP_ENV + '/sys/role/list',
            dataType: 'json',
            success: function(roles){
                if(roles){
                    var _roleId = '${user.roleId}';
                    for(var i=0;i<roles.length;i++){
                        var role = roles[i];
                        if(_roleId == role.id){
                            $('#roleId').append('<option value="'+role.id+'" selected>'+role.name+'</option>')
                        }else{
                            $('#roleId').append('<option value="'+role.id+'">'+role.name+'</option>')
                        }
                    }
                }
                if(typeof callback == 'function'){
                    callback();
                }
            }
        })
    }
</script>