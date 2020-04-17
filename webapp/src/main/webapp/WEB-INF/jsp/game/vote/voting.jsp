<%--
  Created by IntelliJ IDEA.
  User: 17869
  Date: 2020/4/10
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:;"><spring:message code="game.menu.gameMgr"/></a>
        <a><cite><spring:message code="game.menu.voteMgr"/></cite></a>
    </span>
    <a class="layui-btn layui-btn-sm layui-btn-primary" style="line-height:2.5em;margin-top:4px;float:right;" onclick="goBack()" title="<spring:message code="com.goBack"/>">
        <i class="iconfont">&#xe650;</i>
    </a>
</div>

<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body">
                    <blockquote class="layui-elem-quote"><spring:message code="vote.tip.myVote"/></blockquote>
                </div>
            </div>

            <div class="layui-form layui-form-pane" lay-filter="votingForm">
                <div class="layui-card">
                    <div class="layui-card-header">${vote.title}</div>
                    <div class="layui-card-body">
                        ${vote.subTitle}
                    </div>
                </div>

                <c:forEach var="group" items="${groups}">
                <div class="layui-card">
                    <div class="layui-card-header">${group.name}</div>
                    <div class="layui-card-body">
                        <div class="layui-form-item">
                            <c:forEach var="item" items="${items}">
                                <c:if test="${group.id == item.groupId}">
                                    <div class="layui-input-inline">
                                        <c:choose>
                                            <c:when test="${group.type == 0}">
                                                <input type="radio" name="voteItem_${group.id}" value="${item.value}" title="${item.name}" ${item.state != null && item.state == 1 ? 'checked' : ''} >
                                            </c:when>
                                            <c:otherwise>
                                                <input type="checkbox" name="voteItem_${group.id}" value="${item.value}" title="${item.name}" ${item.state != null && item.state == 1 ? 'checked' : ''} >
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                </c:forEach>

                <div class="layui-card">
                    <div class="layui-card-body">
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <c:choose>
                                    <c:when test="${record != null}">
                                        <button class="layui-btn layui-btn-disabled"><spring:message code="vote.btn.voting"/></button>
                                    </c:when>
                                    <c:otherwise>
                                        <button class="layui-btn" lay-submit lay-filter="votingForm"><spring:message code="vote.btn.voting"/></button>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>

    var form,element;
    layui.use(['element','laydate','form'],function(){
        element = layui.element;
        laydate = layui.laydate;
        form = layui.form;

        element.render('breadcrumb');

        form.render();

        form.on('submit(votingForm)',function(data){
            console.log(data.field);
            var groupMap = new DataMap();
            $("input[name*='voteItem_']").each(function(){
                var itemName = $(this).attr("name");
                var names = itemName.split("_");
                if(!groupMap.containsKey(names[1])){
                    groupMap.put(names[1],'');
                }
            });

            var params = {
                voteId: '${vote.id}',
                groups: [],
            };

            var keys = groupMap.keys();
            var groups = [];
            for(var i=0;i<keys.length;i++){
                var group = {};
                var index = keys[i];

                group.id = index;

                var values = '';
                $("input[name='voteItem_"+index+"']:checked").each(function(){
                    var _val = $(this).val();
                    values += values == '' ? _val : ',' + _val;
                });
                group.values = values;
                groups.push(group);
            }
            params.groups = groups;
            console.log(params);

            $.ajax({
                url: APP_ENV + '/game/vote/doVoting',
                data: {
                    voteInfo: JSON.stringify(params)
                },
                dataType: 'json',
                success: function(result){
                    if(result.code){

                    }else{
                        Frame.info(result.msg);
                    }
                }
            });

            return false;
        });
    });

    function goBack(){
        Frame.modMainTab({
            id:'${menuId}',
            url:APP_ENV + '/game/vote/toMain',
            params:{
                menuId:'${menuId}'
            }
        });
    }


</script>