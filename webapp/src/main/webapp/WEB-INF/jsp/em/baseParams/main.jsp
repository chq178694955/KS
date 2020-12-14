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
        <a><cite>电机基本参数列表</cite></a>
    </span>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">
                    <form class="layui-form" lay-filter="searchForm">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <button id="query_${menuId}" type="button" class="layui-btn layui-btn-normal" title="<spring:message code="com.btn.query"/>">
                                    <i class="layui-icon layui-icon-search"></i><spring:message code="com.btn.query"/>
                                </button>
                                <button id="add_${menuId}" type="button" class="layui-btn layui-btn-warm" title="<spring:message code="com.btn.add"/>">
                                    <i class="layui-icon layui-icon-add-1"></i><spring:message code="com.btn.add"/>
                                </button>
                                <button id="excel_${menuId}" type="button" class="layui-btn" title="<spring:message code="com.btn.excel"/>">
                                    <i class="iconfont layui-icon-excel"></i><spring:message code="com.btn.excel"/>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="layui-card-body ">
                    <table class="layui-table" lay-filter="dataList_${menuId}" lay-data="{
                        url:'${ctx}/em/baseParams/find',
                        id:'dataList_${menuId}',
                        page:true,
                        }">
                        <thead>
                        <tr>
                            <th lay-data="{checkbox:true}"></th>
                            <th lay-data="{field:'id',width:75,align:'center',hide:true}">ID</th>
                            <th lay-data="{field:'fixedCurrent',width:160,align:'center'}">额定电流（A）</th>
                            <th lay-data="{field:'fixedVoltage',width:160,align:'center'}">额定电压（V）</th>
                            <th lay-data="{field:'fixedSpeed',width:160,align:'center'}">额定转速（r/min）</th>
                            <th lay-data="{field:'fixedTorque',width:160,align:'center'}">额定转矩（N·m）</th>
                            <th lay-data="{field:'overloadCapacity',width:160,align:'center'}">过载能力（倍）</th>
                            <th lay-data="{field:'machineLength',width:160,align:'center'}">机身长度（m）</th>
                            <th lay-data="{field:'machineHeight',width:160,align:'center'}">电机高度（m）</th>
                            <th lay-data="{field:'machineWidth',width:160,align:'center'}">电机宽度（m）</th>
                            <th lay-data="{field:'rotorLength',width:160,align:'center'}">转子轴长度（m）</th>
                            <th lay-data="{field:'machineWeight',width:160,align:'center'}">电机质量（kg）</th>
                            <th lay-data="{field:'isDefault',width:160,align:'center',templet:'#isDefaultTpl_${menuId}'}">参数类型</th>
                            <th lay-data="{field:'oper',fixed:'right',width:180,align:'center',toolbar:'#operBar_${menuId}'}"><spring:message code="com.btn.oper"/></th>
                        </thead>
                    </table>

                    <script type="text/html" id="isDefaultTpl_${menuId}">
                        <input type = "checkbox" name = "isDefault" value = "{{d.isDefault}}" lay-skin = "switch" lay-text = "默认|自定义" {{ d.isDefault == 1 ? 'checked': ''}} >
                    </script>

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
    layui.use(['form','element','table','laydate'], function(){
        var form = layui.form;
        var element = layui.element;
        var table = layui.table;
        var laydate = layui.laydate;

        form.render('select','searchForm');

        table.init('dataList_${menuId}',{
            id: 'dataList_${menuId}',
            url: '${ctx}/em/baseParams/find',
            page: true
        });

        element.render('breadcrumb');

        $('#query_${menuId}').bind('click',table,query);
        $('#add_${menuId}').bind('click',toAdd);
        $('#excel_${menuId}').bind('click',exportExcel);

        table.on('tool(dataList_${menuId})',function(obj){
            var data = obj.data;
            data.menuId = '${menuId}';
            switch(obj.event){
                case 'del':
                    Frame.confirm('确定删除该参数吗？',function(){
                        $.ajax({
                            url: APP_ENV + '/em/baseParams/delete',
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
                    Frame.modMainTab({
                        id:'${menuId}',
                        url:APP_ENV + '/em/baseParams/toUpdate',
                        params:{
                            menuId:'${menuId}',
                            id: data.id
                        }
                    });

                    //Frame.loadPage('${menuId}','game/vote/toVoting?menuId=${menuId}',data,WebUtils.getMessage('com.btn.modify'),400);
                    break;
            };
        });
    });

    function query(event){
        var table = event.data;
        table.reload('dataList_${menuId}',{
            where:{

            },
            page:{
                curr:1
            }
        });
    }

    function toAdd(){
        Frame.modMainTab({
            id:'${menuId}',
            url:APP_ENV + '/em/baseParams/toAdd',
            params:{
                menuId:'${menuId}'
            }
        });
        //Frame.loadPage('${menuId}','game/vote/toAdd?menuId=${menuId}',{},WebUtils.getMessage('com.btn.add'),400);
    }

    function exportExcel(){
        var layId = 'dataList_${menuId}';
        Frame.exportExcel(layId,'${ctx}/em/baseParams/find',{},'基本参数列表');
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