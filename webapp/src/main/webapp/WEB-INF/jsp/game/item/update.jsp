<%--
  Created by IntelliJ IDEA.
  User: 17869
  Date: 2020/4/11
  Time: 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                    <form class="layui-form" action="${ctx}/game/voteItem/update">
                        <input type="hidden" name="id" value="${item.id}"/>
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label"><spring:message code="vote.item.field.name"/></label>
                                <div class="layui-input-inline">
                                    <input type="text" name="name" value="${item.name}" class="layui-input" lay-verify="required" lay-verType="tips">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label"><spring:message code="vote.item.field.value"/></label>
                                <div class="layui-input-inline">
                                    <input type="text" name="value" value="${item.value}" class="layui-input" lay-verify="required" lay-verType="tips">
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label"><spring:message code="vote.item.field.description"/></label>
                                <div class="layui-input-inline">
                                    <input type="text" name="description" value="${item.description}" class="layui-input" lay-verify="required" lay-verType="tips">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label"><spring:message code="vote.item.field.groupName"/></label>
                                <div class="layui-input-inline">
                                    <select id="groupId" name="groupId">
                                        <c:forEach items="${groups}" var="group">
                                            <c:choose>
                                                <c:when test="${item.groupId == group.id}">
                                                    <option value="${group.id}" selected>${group.name}</option>
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${group.id}">${group.name}</option>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit="" lay-filter="updateFrom"><spring:message code="com.btn.modify"/></button>
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

        form.on('submit(updateFrom)',function(data){
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
</script>