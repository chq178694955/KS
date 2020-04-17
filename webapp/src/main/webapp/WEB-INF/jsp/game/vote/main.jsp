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
        <a><cite><spring:message code="game.menu.voteMgr"/></cite></a>
    </span>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">
                    <form class="layui-form" lay-filter="searchFormVote">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label"><spring:message code="vote.field.title"/></label>
                                <div class="layui-input-inline">
                                    <input id="searchKey_${menuId}" type="text" name="searchKey"  placeholder="<spring:message code="vote.title.tip"/>" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label"><spring:message code="com.time"/></label>
                                <div class="layui-input-inline">
                                    <input id="timeRange_${menuId}" type="text" name="timeRange"  placeholder="" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label"><spring:message code="vote.field.status"/></label>
                                <div class="layui-input-inline">
                                    <select name="status" id="status_${menuId}">
                                        <option value=""><spring:message code="com.select.all"/></option>
                                        <option value="0"><spring:message code="vote.field.status.ready"/></option>
                                        <option value="1"><spring:message code="vote.field.status.start"/></option>
                                        <option value="2"><spring:message code="vote.field.status.pause"/></option>
                                        <option value="3"><spring:message code="vote.field.status.finish"/></option>
                                        <option value="4"><spring:message code="vote.field.status.discard"/></option>
                                    </select>
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
                    <table class="layui-table" lay-filter="voteList_${menuId}" lay-data="{
                        url:'${ctx}/game/vote/find',
                        id:'voteList_${menuId}',
                        page:true,
                        }">
                        <thead>
                        <tr>
                            <th lay-data="{checkbox:true}"></th>
                            <th lay-data="{field:'id',width:75,align:'center'}"><spring:message code="vote.field.id"/></th>
                            <th lay-data="{field:'title',width:250,align:'center'}"><spring:message code="vote.field.title"/></th>
                            <th lay-data="{field:'startTimeDesc',width:120,align:'center'}"><spring:message code="vote.field.startTime"/></th>
                            <th lay-data="{field:'endTimeDesc',width:120,align:'center'}"><spring:message code="vote.field.endTime"/></th>
                            <th lay-data="{field:'statusDesc',width:80,align:'center'}"><spring:message code="vote.field.status"/></th>
                            <th lay-data="{field:'templateName',width:150,align:'center'}"><spring:message code="vote.field.templateName"/></th>
                            <th lay-data="{field:'creator',width:100,align:'center'}"><spring:message code="vote.field.creator"/></th>
                            <th lay-data="{field:'createTimeDesc',width:120,align:'center'}"><spring:message code="vote.field.createTime"/></th>
                            <th lay-data="{field:'createId',width:20,align:'center',hide:true}"></th>
                            <th lay-data="{field:'subTitle',width:200,align:'center',hide:true}"><spring:message code="vote.field.subTitle"/></th>
                            <th lay-data="{field:'oper',fixed:'right',width:180,align:'center',toolbar:'#operBar_${menuId}'}"><spring:message code="com.btn.oper"/></th>
                        </thead>
                    </table>

                    <script type="text/html" id="operBar_${menuId}">
                        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="vote" title="<spring:message code="vote.btn.voting"/>">
                            <i class="iconfont">&#xe635;</i>
                            <cite><spring:message code="vote.btn.voting"/></cite>
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
    layui.use(['form','element','table','laydate'], function(){
        var form = layui.form;
        var element = layui.element;
        var table = layui.table;
        var laydate = layui.laydate;

        form.render('select','searchFormVote');

        laydate.render({
            elem: '#timeRange_${menuId}',
            range: '~'
        });

        table.init('voteList_${menuId}',{
            id: 'voteList_${menuId}',
            url: '${ctx}/game/vote/find',
            page: true
        });

        element.render('breadcrumb');

        $('#query_${menuId}').bind('click',table,query);
        $('#add_${menuId}').bind('click',toAdd);
        $('#excel_${menuId}').bind('click',exportExcel);

        table.on('tool(voteList_${menuId})',function(obj){
            var data = obj.data;
            data.menuId = '${menuId}';
            switch(obj.event){
                case 'del':
                    Frame.confirm(WebUtils.getMessage('vote.tip.delSure'),function(){
                        $.ajax({
                            url: APP_ENV + '/game/vote/delete',
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
                case 'vote':
                    $.ajax({
                        url: APP_ENV + '/game/vote/validVote',
                        data:{voteId: data.id},
                        success: function(result){
                            if(result.code == 0){
                                Frame.modMainTab({
                                    id:'${menuId}',
                                    url:APP_ENV + '/game/vote/voting',
                                    params:{
                                        menuId:'${menuId}',
                                        voteId: data.id
                                    }
                                });
                            }else{
                                Frame.info(result.msg);
                            }
                        }
                    });

                    //Frame.loadPage('${menuId}','game/vote/toVoting?menuId=${menuId}',data,WebUtils.getMessage('com.btn.modify'),400);
                    break;
            };
        });
    });

    function query(event){
        var table = event.data;
        table.reload('voteList_${menuId}',{
            where:{
                searchKey: WebUtils.fmtStr($('#searchKey_${menuId}').val()),
                status: WebUtils.fmtStr($('#status_${menuId}').val()),
                timeRange: WebUtils.fmtStr($('#timeRange_${menuId}').val())
            },
            page:{
                curr:1
            }
        });
    }

    function toAdd(){
        Frame.modMainTab({
            id:'${menuId}',
            url:APP_ENV + '/game/vote/toAdd',
            params:{
                menuId:'${menuId}'
            }
        });
        //Frame.loadPage('${menuId}','game/vote/toAdd?menuId=${menuId}',{},WebUtils.getMessage('com.btn.add'),400);
    }

    function exportExcel(){
        var layId = 'voteList_${menuId}';
        Frame.exportExcel(layId,'${ctx}/game/vote/find',{
            searchKey: WebUtils.fmtStr($('#searchKey_${menuId}').val()),
            status: WebUtils.fmtStr($('#status_${menuId}').val()),
            timeRange: WebUtils.fmtStr($('#timeRange_${menuId}').val())
        },'投票列表');
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