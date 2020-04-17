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
        <a href="javascript:;"><spring:message code="game.menu.gameMgr"/></a>
        <a><cite><spring:message code="game.menu.voteItemMgr"/></cite></a>
    </span>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">
                    <form class="layui-form" lay-filter="searchFormVoteItem">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <div class="layui-input-inline">
                                    <select id="groupId_${menuId}" name="groupId" lay-verify=""></select>
                                </div>
                            </div>
                            <div class="layui-inline">
                                <button id="query_${menuId}" type="button" class="layui-btn layui-btn-normal" title="<spring:message code="com.btn.query"/>">
                                    <i class="layui-icon layui-icon-search"></i>
                                </button>
                                <button id="add_${menuId}" type="button" class="layui-btn layui-btn-warm" title="<spring:message code="com.btn.add"/>">
                                    <i class="layui-icon layui-icon-add-1"></i>
                                </button>
                                <button id="excel_${menuId}" type="button" class="layui-btn" title="<spring:message code="com.btn.excel"/>">
                                    <i class="iconfont layui-icon-excel"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="layui-card-body ">
                    <table class="layui-table" lay-filter="voteItemList_${menuId}" lay-data="{
                        url:'${ctx}/game/voteItem/find',
                        id:'voteItemList_${menuId}',
                        page:true,
                        }">
                        <thead>
                        <tr>
                            <th lay-data="{checkbox:true}"></th>
                            <th lay-data="{field:'id',width:100,align:'center'}"><spring:message code="vote.item.field.id"/></th>
                            <th lay-data="{field:'name',width:150,align:'center'}"><spring:message code="vote.item.field.name"/></th>
                            <th lay-data="{field:'value',width:100,align:'center'}"><spring:message code="vote.item.field.value"/></th>
                            <th lay-data="{field:'description',width:200,align:'center'}"><spring:message code="vote.item.field.description"/></th>
                            <th lay-data="{field:'groupName',width:200,align:'center'}"><spring:message code="vote.item.field.groupName"/></th>
                            <th lay-data="{field:'oper',fixed:'right',width:180,align:'center',toolbar:'#operBar_${menuId}'}"><spring:message code="com.btn.oper"/></th>
                        </thead>
                    </table>

                    <script type="text/html" id="operBar_${menuId}">
                        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit" title="<spring:message code="com.btn.edit"/>">
                            <i class="layui-icon layui-icon-edit"></i>
                            <cite><spring:message code="com.btn.edit"/></cite>
                        </a>
                        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" title="<spring:message code="com.btn.del"/>">
                            <i class="layui-icon layui-icon-delete"></i>
                            <cite><spring:message code="com.btn.del"/></cite>
                        </a>
                    </script>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    layui.use(['form','element','table'], function(){
        var form = layui.form;
        var element = layui.element;
        var table = layui.table;

        list(function(){
            form.render('select','searchFormVoteItem');
        });

        table.init('voteItemList_${menuId}',{
            id: 'voteItemList_${menuId}',
            url: '${ctx}/game/voteItem/find',
            page: true
        });

        element.render('breadcrumb');

        $('#query_${menuId}').bind('click',table,query);
        $('#add_${menuId}').bind('click',toAdd);
        $('#excel_${menuId}').bind('click',exportExcel);

        table.on('tool(voteItemList_${menuId})',function(obj){
            var data = obj.data;
            data.menuId = '${menuId}';
            switch(obj.event){
                case 'del':
                    Frame.confirm(WebUtils.getMessage('vote.item.tip.delSure'),function(){
                        $.ajax({
                            url: APP_ENV + '/game/voteItem/delete',
                            data:{id: data.id},
                            dataType:'json',
                            success:function (result) {
                                if(result.code == 0){
                                    $('#query_${menuId}').click();
                                }
                                Frame.alert(result.msg);
                            }
                        });
                    });
                    break;
                case 'edit':
                    Frame.loadPage('${menuId}','game/voteItem/toUpdate?menuId=${menuId}',data,WebUtils.getMessage('com.btn.modify'),400);
                    break;
            };
        });
    });

    function query(event){
        var table = event.data;
        table.reload('voteItemList_${menuId}',{
            where:{
                groupId: WebUtils.fmtStr($('#groupId_${menuId}').val())
            },
            page:{
                curr:1
            }
        });
    }

    function toAdd(){
        Frame.loadPage('${menuId}','game/voteItem/toAdd?menuId=${menuId}',{},WebUtils.getMessage('com.btn.add'),400);
    }

    function exportExcel(){
        var layId = 'voteItemList_${menuId}';
        Frame.exportExcel(layId,'${ctx}/game/voteItem/find',{
            name: WebUtils.fmtStr($('#name_${menuId}').val())
        },'投票项列表');
    }

    function list(callback){
        $.ajax({
            url: APP_ENV + '/game/voteItemGroup/list?flag=1',
            dataType: 'json',
            success: function(datas){
                if(datas){
                    for(var i=0;i<datas.length;i++){
                        var group = datas[i];
                        $('#groupId_${menuId}').append('<option value="'+group.id+'">'+group.name+'</option>')
                    }
                    if(typeof callback == 'function'){
                        callback();
                    }
                }
            }
        });
    }

</script>