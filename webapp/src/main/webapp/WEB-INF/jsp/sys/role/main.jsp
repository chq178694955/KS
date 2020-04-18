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
        <a href="javascript:;"><spring:message code="sys.menu.sysMgr"/></a>
        <a><cite><spring:message code="sys.menu.roleMgr"/></cite></a>
    </span>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md6">
            <div class="layui-card">
                <div class="layui-card-header">
                    <form class="layui-form" lay-filter="searchForm">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <div class="layui-input-inline">
                                    <input id="searchKey_${menuId}" type="text" name="searchKey"  placeholder="<spring:message code="sys.user.label.accountOrNameTip"/>" autocomplete="off" class="layui-input">
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
                    <table class="layui-table" lay-filter="sysRoleList_${menuId}">
                        <thead>
                        <tr>
                            <th lay-data="{checkbox:true}"></th>
                            <th lay-data="{field:'id',width:100,align:'center'}"><spring:message code="sys.role.field.id"/></th>
                            <th lay-data="{field:'name',width:200,align:'center'}"><spring:message code="sys.role.field.name"/></th>
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
        <div class="layui-col-md6">
            <div class="layui-card">
                <div class="layui-card-header">
                    权限设置
                </div>
                <div class="layui-card-body">
                    <div id="treeId"></div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    layui.use(['form','element','table','tree'], function(){
        var form = layui.form;
        var element = layui.element;
        var table = layui.table;
        var tree = layui.tree;
        //渲染
        var inst1 = tree.render({
            elem: '#treeId'  //绑定元素
            ,data: [{
                title: '江西' //一级菜单
                ,children: [{
                    title: '南昌' //二级菜单
                    ,children: [{
                        title: '高新区' //三级菜单
                        //…… //以此类推，可无限层级
                    }]
                }]
            },{
                title: '陕西' //一级菜单
                ,children: [{
                    title: '西安' //二级菜单
                }]
            }]
        });

        table.init('sysRoleList_${menuId}',{
            id: 'sysRoleList_${menuId}',
            url: '${ctx}/sys/role/find',
            page: true
        });

        element.render('breadcrumb');

        $('#query_${menuId}').bind('click',table,query);
        $('#add_${menuId}').bind('click',toAdd);
        $('#excel_${menuId}').bind('click',exportExcel);

        table.on('tool(sysRoleList_${menuId})',function(obj){
            var data = obj.data;
            data.menuId = '${menuId}';
            switch(obj.event){
                case 'del':
                    Frame.confirm(WebUtils.getMessage('sys.role.tip.delRole'),function(){
                        $.ajax({
                            url: APP_ENV + '/sys/role/delete',
                            data:{userId: data.id},
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
                    var title = WebUtils.getMessage('com.btn.update');
                    Frame.loadPage('${menuId}','sys/role/toUpdate?menuId=${menuId}',data,WebUtils.getMessage('com.btn.modify'),400);
                    break;
            };
        });

    });

    function query(event){
        var table = event.data;
        table.reload('sysRoleList_${menuId}',{
            where:{
                searchKey: WebUtils.fmtStr($('#searchKey_${menuId}').val())
            },
            page:{
                curr:1
            }
        });
    }

    function toAdd(){
        Frame.loadPage('${menuId}','sys/role/toAdd?menuId=${menuId}',{},WebUtils.getMessage('com.btn.add'),400);
    }

    function exportExcel(){
        var layId = 'sysRoleList_${menuId}';
        Frame.exportExcel(layId,'${ctx}/sys/role/find',{
            searchKey: WebUtils.fmtStr($('#searchKey_${menuId}').val())
        },'用户列表');
    }

</script>