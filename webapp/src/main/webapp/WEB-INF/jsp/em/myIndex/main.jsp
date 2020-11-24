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
        <a href="javascript:;">伺服电机</a>
        <a><cite>我的指标</cite></a>
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
                                <div class="layui-input-inline">
                                    <input id="searchKey_${menuId}" type="text" name="searchKey"  placeholder="请输入自定义指标名称" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <button id="query_${menuId}" type="button" class="layui-btn layui-btn-normal" title="<spring:message code="com.btn.query"/>">
                                    <i class="layui-icon layui-icon-search"></i>
                                </button>
                                <button id="add_${menuId}" type="button" class="layui-btn layui-btn-warm" title="<spring:message code="com.btn.add"/>">
                                    <i class="layui-icon layui-icon-add-1"></i>
                                </button>
                                <button id="modify_${menuId}" type="button" class="layui-btn layui-btn-normal" title="<spring:message code="com.btn.edit"/>">
                                    <i class="layui-icon layui-icon-edit"></i>
                                </button>
                                <button id="del_${menuId}" type="button" class="layui-btn layui-btn-danger" title="<spring:message code="com.btn.del"/>">
                                    <i class="layui-icon layui-icon-delete"></i>
                                </button>
                                <button id="excel_${menuId}" type="button" class="layui-btn" title="<spring:message code="com.btn.excel"/>">
                                    <i class="iconfont layui-icon-excel"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="layui-card-body ">
                    <table class="layui-table" lay-filter="dataList_${menuId}" lay-data="{
                        url:'${ctx}/em/myIndex/find',
                        id:'dataList_${menuId}',
                        page:true,
                        }">
                        <thead>
                        <tr>
                            <th lay-data="{checkbox:true}"></th>
                            <th lay-data="{field:'id',width:75,align:'center',hide:true}">ID</th>
                            <th lay-data="{field:'groupName',width:160,align:'center'}">分组名称</th>
                            <th lay-data="{field:'val',width:160,align:'center'}">我的指标</th>
                            <th lay-data="{field:'minVal',width:160,align:'center'}">最小指标</th>
                            <th lay-data="{field:'maxVal',width:160,align:'center'}">最大指标</th>
                            <th lay-data="{field:'weight',width:160,align:'center'}">指标权重</th>
                            <th lay-data="{field:'unit',width:120,align:'center'}">单位</th>
                            <th lay-data="{field:'categoryName',width:160,align:'center'}">所属分类</th>
                            <%--<th lay-data="{field:'oper',fixed:'right',width:180,align:'center',toolbar:'#operBar_${menuId}'}"><spring:message code="com.btn.oper"/></th>--%>
                        </thead>
                    </table>

                    <%--<script type="text/html" id="operBar_${menuId}">--%>
                        <%--<a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit" title="<spring:message code="com.btn.edit"/>">--%>
                            <%--<i class="layui-icon layui-icon-edit"></i>--%>
                            <%--<cite><spring:message code="com.btn.edit"/></cite>--%>
                        <%--</a>--%>
                        <%--<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" title="<spring:message code="com.btn.del"/>">--%>
                            <%--<i class="layui-icon layui-icon-delete"></i>--%>
                            <%--<cite><spring:message code="com.btn.del"/></cite>--%>
                        <%--</a>--%>
                    <%--</script>--%>
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
            url: '${ctx}/em/myIndex/find',
            page: true
        });

        element.render('breadcrumb');

        $('#query_${menuId}').bind('click',table,query);
        $('#add_${menuId}').bind('click',toAdd);
        $('#modify_${menuId}').bind('click',toUpdate);
        $('#del_${menuId}').bind('click',doDel);
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
                            groupId: data.groupId
                        }
                    });

                    //Frame.loadPage('${menuId}','game/vote/toVoting?menuId=${menuId}',data,WebUtils.getMessage('com.btn.modify'),400);
                    break;
            };
        });

        function toUpdate(){
            var checkStatus = table.checkStatus('dataList_${menuId}');
            console.log(checkStatus.data) //获取选中行的数据
            console.log(checkStatus.data.length) //获取选中行数量，可作为是否有选中行的条件
            console.log(checkStatus.isAll ) //表格是否全选
            if(checkStatus.data.length == 0){
                Frame.warn("请选择需要修改的指标");
                return ;
            }
            if(checkStatus.data.length > 1){
                Frame.warn("只能选择一行数据进行操作");
                return ;
            }

            Frame.modMainTab({
                id:'${menuId}',
                url:APP_ENV + '/em/myIndex/toUpdate',
                params:{
                    menuId:'${menuId}',
                    groupId: checkStatus.data[0].groupId
                }
            });
            //Frame.loadPage('${menuId}','game/vote/toAdd?menuId=${menuId}',{},WebUtils.getMessage('com.btn.add'),400);
        }

        function doDel(){
            var checkStatus = table.checkStatus('dataList_${menuId}');
            console.log(checkStatus.data) //获取选中行的数据
            console.log(checkStatus.data.length) //获取选中行数量，可作为是否有选中行的条件
            console.log(checkStatus.isAll ) //表格是否全选
            if(checkStatus.data.length == 0){
                Frame.warn("请选择需要删除的指标");
                return ;
            }
            if(checkStatus.data.length > 1){
                Frame.warn("只能选择一行数据进行操作");
                return ;
            }
            Frame.confirm("自定义指标是一组一组的删除，确定删除吗？",function(){
                $.ajax({
                    url: APP_ENV + '/em/myIndex/delete',
                    data:{groupId: checkStatus.data[0].groupId},
                    dataType:'json',
                    success:function (result) {
                        if(result.code == 0){
                            $('#query_${menuId}').click();
                        }
                        Frame.alert(result.msg);
                    }
                });
            });
        }
    });

    function query(event){
        var table = event.data;
        table.reload('dataList_${menuId}',{
            where:{
                searchKey: $('#searchKey_${menuId}').val()
            },
            page:{
                curr:1
            }
        });
    }

    function toAdd(){
        Frame.modMainTab({
            id:'${menuId}',
            url:APP_ENV + '/em/myIndex/toAdd',
            params:{
                menuId:'${menuId}'
            }
        });
        //Frame.loadPage('${menuId}','game/vote/toAdd?menuId=${menuId}',{},WebUtils.getMessage('com.btn.add'),400);
    }

    function exportExcel(){
        var layId = 'dataList_${menuId}';
        Frame.exportExcel(layId,'${ctx}/em/myIndex/find',{
            searchKey: $('#searchKey_${menuId}').val()
        },'我的指标列表');
    }

</script>