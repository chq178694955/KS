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
        <a><cite>指标管理</cite></a>
    </span>
</div>
<div class="layui-fluid">
    <fieldset class="layui-elem-field layui-field-title site-title">
        <legend><a name="color-design">指标分类</a></legend>
    </fieldset>

    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">
                    <form class="layui-form" lay-filter="searchForm">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <button id="query_${menuId}" type="button" class="layui-btn layui-btn-normal" title="<spring:message code="com.btn.query"/>">
                                    <i class="layui-icon layui-icon-search"></i>
                                </button>
                                <%--<button id="add_${menuId}" type="button" class="layui-btn layui-btn-warm" title="<spring:message code="com.btn.add"/>">--%>
                                    <%--<i class="layui-icon layui-icon-add-1"></i>--%>
                                <%--</button>--%>
                                <button id="excel_${menuId}" type="button" class="layui-btn" title="<spring:message code="com.btn.excel"/>">
                                    <i class="iconfont layui-icon-excel"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="layui-card-body ">
                    <table class="layui-table" lay-filter="dataList_${menuId}" lay-data="{
                        url:'${ctx}/em/indexMgr/findCategory',
                        id:'dataList_${menuId}',
                        page:true,
                        }">
                        <thead>
                        <tr>
                            <th lay-data="{checkbox:true}"></th>
                            <th lay-data="{field:'id',width:75,align:'center',hide:true}">ID</th>
                            <th lay-data="{field:'name',width:160,align:'center'}">分类名称</th>
                            <th lay-data="{field:'oper',fixed:'right',width:180,align:'center',toolbar:'#operBar_${menuId}'}"><spring:message code="com.btn.oper"/></th>
                        </thead>
                    </table>

                    <script type="text/html" id="operBar_${menuId}">
                        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit" title="<spring:message code="com.btn.edit"/>">
                            <i class="layui-icon layui-icon-edit"></i>
                            <cite><spring:message code="com.btn.edit"/></cite>
                        </a>
                        <%--<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" title="<spring:message code="com.btn.del"/>">--%>
                            <%--<i class="layui-icon layui-icon-delete"></i>--%>
                            <%--<cite><spring:message code="com.btn.del"/></cite>--%>
                        <%--</a>--%>
                    </script>
                </div>
            </div>
        </div>
    </div>

    <fieldset class="layui-elem-field layui-field-title site-title">
        <legend><a name="color-design">系统指标</a></legend>
    </fieldset>

    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">
                    <form class="layui-form" lay-filter="searchFormTemplate">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">指标分类</label>
                                <div class="layui-input-inline">
                                    <select id="indexCategory_${menuId}" name="state" lay-verify="">

                                    </select>
                                </div>
                            </div>
                            <div class="layui-inline">
                                <button id="queryTemplate_${menuId}" type="button" class="layui-btn layui-btn-normal" title="<spring:message code="com.btn.query"/>">
                                    <i class="layui-icon layui-icon-search"></i>
                                </button>
                                <%--<button id="addTemplate_${menuId}" type="button" class="layui-btn layui-btn-warm" title="<spring:message code="com.btn.add"/>">--%>
                                    <%--<i class="layui-icon layui-icon-add-1"></i>--%>
                                <%--</button>--%>
                                <button id="excelTemplate_${menuId}" type="button" class="layui-btn" title="<spring:message code="com.btn.excel"/>">
                                    <i class="iconfont layui-icon-excel"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="layui-card-body ">
                    <table class="layui-table" lay-filter="dataListTemplate_${menuId}" lay-data="{
                        url:'${ctx}/em/indexMgr/findTemplate',
                        id:'dataListTemplate_${menuId}',
                        page:true,
                        limit:16
                        }">
                        <thead>
                        <tr>
                            <th lay-data="{checkbox:true}"></th>
                            <th lay-data="{field:'id',width:75,align:'center',hide:true}">ID</th>
                            <th lay-data="{field:'name',width:160,align:'center'}">指标名称</th>
                            <th lay-data="{field:'bestVal',width:160,align:'center'}">最优指标</th>
                            <%--<th lay-data="{field:'minVal',width:160,align:'center'}">最小指标</th>--%>
                            <%--<th lay-data="{field:'maxVal',width:160,align:'center'}">最大指标</th>--%>
                            <th lay-data="{field:'weight',width:160,align:'center'}">指标权重</th>
                            <th lay-data="{field:'unit',width:120,align:'center'}">单位</th>
                            <th lay-data="{field:'categoryName',width:160,align:'center'}">所属分类</th>
                            <th lay-data="{field:'oper',fixed:'right',width:180,align:'center',toolbar:'#operBarTemplate_${menuId}'}"><spring:message code="com.btn.oper"/></th>
                        </thead>
                    </table>

                    <script type="text/html" id="operBarTemplate_${menuId}">
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

        form.render('select','searchFormTemplate');

        table.init('dataList_${menuId}',{
            id: 'dataList_${menuId}',
            url: '${ctx}/em/indexMgr/findCategory',
            page: true
        });

        element.render('breadcrumb');

        $('#query_${menuId}').bind('click',table,query);
        $('#add_${menuId}').bind('click',toAdd);
        $('#excel_${menuId}').bind('click',exportExcel);

        $('#queryTemplate_${menuId}').bind('click',table,queryTemplate);
        $('#addTemplate_${menuId}').bind('click',toAddTemplate);
        $('#excelTemplate_${menuId}').bind('click',exportExcelTemplate);

        table.on('tool(dataList_${menuId})',function(obj){
            var data = obj.data;
            data.menuId = '${menuId}';
            switch(obj.event){
                case 'del':
                    Frame.confirm('确定删除该分类吗？',function(){
                        $.ajax({
                            url: APP_ENV + '/em/indexMgr/deleteCategory',
                            data:{id: data.id},
                            dataType:'json',
                            success:function (result) {
                                if(result.code == 0){
                                    $('#query_${menuId}').click();
                                    loadCategories();
                                }
                                Frame.alert(result.msg);
                            }
                        });
                    });
                    break;
                case 'edit':
                    Frame.modMainTab({
                        id:'${menuId}',
                        url:APP_ENV + '/em/indexMgr/toUpdateCategory',
                        params:{
                            menuId:'${menuId}',
                            id: data.id
                        }
                    });

                    //Frame.loadPage('${menuId}','game/vote/toVoting?menuId=${menuId}',data,WebUtils.getMessage('com.btn.modify'),400);
                    break;
            };
        });

        table.on('tool(dataListTemplate_${menuId})',function(obj){
            var data = obj.data;
            data.menuId = '${menuId}';
            switch(obj.event){
                case 'del':
                    Frame.confirm('确定删除该指标吗？',function(){
                        $.ajax({
                            url: APP_ENV + '/em/indexMgr/deleteTemplate',
                            data:{id: data.id},
                            dataType:'json',
                            success:function (result) {
                                if(result.code == 0){
                                    $('#queryTemplate_${menuId}').click();
                                    loadCategories();
                                }
                                Frame.alert(result.msg);
                            }
                        });
                    });
                    break;
                case 'edit':
                    Frame.modMainTab({
                        id:'${menuId}',
                        url:APP_ENV + '/em/indexMgr/toUpdateTemplate',
                        params:{
                            menuId:'${menuId}',
                            id: data.id
                        }
                    });

                    //Frame.loadPage('${menuId}','game/vote/toVoting?menuId=${menuId}',data,WebUtils.getMessage('com.btn.modify'),400);
                    break;
            };
        });

        loadCategories(function(){
            table.init('dataListTemplate_${menuId}',{
                id: 'dataListTemplate_${menuId}',
                url: '${ctx}/em/indexMgr/findTemplate',
                where: {
                    categoryId: $('#indexCategory_${menuId}').val()
                },
                page: true
            });
        });
        function loadCategories(callback){
            $.ajax({
                url: APP_ENV + '/em/indexMgr/listCategory',
                dataType: 'json',
                success: function(categories){
                    if(categories){
                        $('#indexCategory_${menuId}').empty();
                        $('#indexCategory_${menuId}').append('<option value="">全部</option>')
                        for(var i=0;i<categories.length;i++){
                            var cate = categories[i];
                            $('#indexCategory_${menuId}').append('<option value="'+cate.id+'">'+cate.name+'</option>')
                        }
                        form.render('select','searchFormTemplate');
                    }
                    if(typeof callback == 'function'){
                        callback();
                    }
                }
            })
        }
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

    function queryTemplate(event){
        var table = event.data;
        table.reload('dataListTemplate_${menuId}',{
            where:{
                categoryId : $('#indexCategory_${menuId}').val()
            },
            page:{
                curr:1
            }
        });
    }

    function toAdd(){
        Frame.modMainTab({
            id:'${menuId}',
            url:APP_ENV + '/em/indexMgr/toAddCategory',
            params:{
                menuId:'${menuId}'
            }
        });
        //Frame.loadPage('${menuId}','game/vote/toAdd?menuId=${menuId}',{},WebUtils.getMessage('com.btn.add'),400);
    }

    function toAddTemplate(){
        Frame.modMainTab({
            id:'${menuId}',
            url:APP_ENV + '/em/indexMgr/toAddTemplate',
            params:{
                menuId:'${menuId}'
            }
        });
        //Frame.loadPage('${menuId}','game/vote/toAdd?menuId=${menuId}',{},WebUtils.getMessage('com.btn.add'),400);
    }

    function exportExcel(){
        var layId = 'dataList_${menuId}';
        Frame.exportExcel(layId,'${ctx}/em/indexMgr/findCategory',{},'指标分类列表');
    }

    function exportExcelTemplate(){
        var layId = 'dataListTemplate_${menuId}';
        Frame.exportExcel(layId,'${ctx}/em/indexMgr/findTemplate',{categoryId:$('#indexCategory_${menuId}').val()},'指标列表');
    }

</script>