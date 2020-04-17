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
        <a href="javascript:;"><spring:message code="game.menu.gameMgr"/></a>
        <a><cite><spring:message code="game.menu.voteItemMgr"/></cite></a>
    </span>
</div>

<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body">
                    <form class="layui-form" action="${ctx}/game/voteItem/add" lay-filter="addVoteItemForm">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label"><spring:message code="vote.item.field.name"/></label>
                                <div class="layui-input-inline">
                                    <input type="text" name="name" class="layui-input" lay-verify="required" lay-verType="tips">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label"><spring:message code="vote.item.field.value"/></label>
                                <div class="layui-input-inline">
                                    <input type="text" name="value" class="layui-input" lay-verify="required" lay-verType="tips">
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label"><spring:message code="vote.item.field.description"/></label>
                                <div class="layui-input-inline">
                                    <input type="text" name="description" class="layui-input" lay-verify="required" lay-verType="tips">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label"><spring:message code="vote.item.field.groupName"/></label>
                                <div class="layui-input-inline">
                                    <select id="groupId" name="groupId"></select>
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit="" lay-filter="addForm"><spring:message code="com.btn.add"/></button>
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

        list(function(){
            form.render('select');
            // form.render('select','addVoteItemForm');
        });

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

    function list(callback){
        $.ajax({
            url: APP_ENV + '/game/voteItemGroup/list',
            dataType: 'json',
            success: function(datas){
                if(datas){
                    for(var i=0;i<datas.length;i++){
                        var group = datas[i];
                        $('#groupId').append('<option value="'+group.id+'">'+group.name+'</option>')
                    }
                    if(typeof callback == 'function'){
                        callback();
                    }
                }
            }
        });
    }
</script>