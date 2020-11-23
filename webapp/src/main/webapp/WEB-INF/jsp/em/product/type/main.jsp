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
        <a href="javascript:;"><spring:message code="em.product.mgr.title"/></a>
        <a><cite><spring:message code="em.attr.type.title"/></cite></a>
    </span>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">
                    <form class="layui-form" lay-filter="searchForm_${menuId}">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label"><spring:message code="em.attr.type.text"/></label>
                                <div class="layui-input-inline">
                                    <input id="searchKey_${menuId}" type="text" name="searchKey"  placeholder="<spring:message code="em.attr.type.text"/>" autocomplete="off" class="layui-input">
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
                    <table class="layui-table" lay-filter="dataList_${menuId}" lay-data="{
                        url:'${ctx}/em/attr/type/find',
                        id:'dataList_${menuId}',
                        page:true,
                        }">
                        <thead>
                        <tr>
                            <th lay-data="{checkbox:true}"></th>
                            <th lay-data="{field:'id',width:75,align:'center'}"><spring:message code="em.attr.type.id"/></th>
                            <th lay-data="{field:'text',width:250,align:'center'}"><spring:message code="em.attr.type.text"/></th>
                            <th lay-data="{field:'value',width:120,align:'center'}"><spring:message code="em.attr.type.value"/></th>
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
    layui.use(['form','element','table','laydate'], function(){
        var form = layui.form;
        var element = layui.element;
        var table = layui.table;

        form.render('select','searchForm_${menuId}');

        table.init('dataList_${menuId}',{
            id: 'dataList_${menuId}',
            url: '${ctx}/em/attr/type/find',
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
                    Frame.confirm(WebUtils.getMessage('vote.tip.delSure'),function(){
                        $.ajax({
                            url: APP_ENV + '/em/attr/type/delete',
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
                    <%--$.ajax({--%>
                        <%--url: APP_ENV + '/em/attr/type/toUpdate',--%>
                        <%--data:{voteId: data.id},--%>
                        <%--success: function(result){--%>
                            <%--if(result.code == 0){--%>
                                <%--Frame.modMainTab({--%>
                                    <%--id:'${menuId}',--%>
                                    <%--url:APP_ENV + '/game/vote/voting',--%>
                                    <%--params:{--%>
                                        <%--menuId:'${menuId}',--%>
                                        <%--voteId: data.id--%>
                                    <%--}--%>
                                <%--});--%>
                            <%--}else{--%>
                                <%--Frame.info(result.msg);--%>
                            <%--}--%>
                        <%--}--%>
                    <%--});--%>

                    Frame.loadPage('${menuId}','em/attr/type/toUpdate?menuId=${menuId}',data,WebUtils.getMessage('com.btn.modify'),400);
                    break;
            };
        });
    });

    function query(event){
        var table = event.data;
        table.reload('dataList_${menuId}',{
            where:{
                searchKey: WebUtils.fmtStr($('#searchKey_${menuId}').val())
            },
            page:{
                curr:1
            }
        });
    }

    function toAdd(){
        <%--Frame.modMainTab({--%>
            <%--id:'${menuId}',--%>
            <%--url:APP_ENV + '/em/attr/type/toAdd',--%>
            <%--params:{--%>
                <%--menuId:'${menuId}'--%>
            <%--}--%>
        <%--});--%>
        Frame.loadPage('${menuId}','em/attr/type/toAdd?menuId=${menuId}',{},WebUtils.getMessage('com.btn.add'),400);
    }

    function exportExcel(){
        var layId = 'dataList_${menuId}';
        Frame.exportExcel(layId,'${ctx}/em/attr/type/find',{
            searchKey: WebUtils.fmtStr($('#searchKey_${menuId}').val())
        },'产品属性类型列表');
    }

</script>