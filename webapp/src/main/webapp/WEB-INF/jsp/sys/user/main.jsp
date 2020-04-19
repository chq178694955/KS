<%--
  Created by IntelliJ IDEA.
  User: 17869
  Date: 2020/4/10
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:;"><spring:message code="sys.menu.sysMgr"/></a>
        <a><cite><spring:message code="sys.menu.userMgr"/></cite></a>
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
                                <label class="layui-form-label"><spring:message code="sys.user.field.state"/></label>
                                <div class="layui-input-inline">
                                    <select id="state_${menuId}" name="state" lay-verify="">
                                        <option value="" selected><spring:message code="com.select.all"/></option>
                                        <option value="0"><spring:message code="sys.user.field.state.normal"/></option>
                                        <option value="1"><spring:message code="sys.user.field.state.locked"/></option>
                                    </select>
                                </div>
                            </div>
                            <div class="layui-inline">
                                <div class="layui-input-inline">
                                    <input id="searchKey_${menuId}" type="text" name="searchKey"  placeholder="<spring:message code="sys.user.label.accountOrNameTip"/>" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <button id="query_${menuId}" type="button" class="layui-btn layui-btn-normal" title="<spring:message code="com.btn.query"/>">
                                    <i class="layui-icon layui-icon-search"></i>
                                </button>
                                <shiro:hasPermission name="sys_user_add">
                                    <button id="add_${menuId}" type="button" class="layui-btn layui-btn-warm" title="<spring:message code="com.btn.add"/>">
                                        <i class="layui-icon layui-icon-add-1"></i>
                                    </button>
                                </shiro:hasPermission>
                                <button id="excel_${menuId}" type="button" class="layui-btn" title="<spring:message code="com.btn.excel"/>">
                                    <i class="iconfont layui-icon-excel"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="layui-card-body ">
                    <table class="layui-table" lay-filter="sysUserList_${menuId}">
                        <thead>
                        <tr>
                            <th lay-data="{checkbox:true}"></th>
                            <th lay-data="{field:'name',width:200,align:'center'}"><spring:message code="sys.user.field.name"/></th>
                            <th lay-data="{field:'username',width:200,align:'center'}"><spring:message code="sys.user.field.username"/></th>
                            <th lay-data="{field:'roleName',width:120,align:'center'}"><spring:message code="sys.user.file.roleName"/></th>
                            <th lay-data="{field:'stateDesc',width:120,align:'center'}"><spring:message code="sys.user.field.state"/></th>
                            <th lay-data="{field:'idCardNum',width:300,align:'center'}"><spring:message code="sys.user.field.idCardNo"/></th>
                            <th lay-data="{field:'oper',fixed:'right',width:180,align:'center',toolbar:'#operBar_${menuId}'}"><spring:message code="com.btn.oper"/></th>
                        </thead>
                    </table>

                    <script type="text/html" id="operBar_${menuId}">
                        <shiro:hasPermission name="sys_user_update">
                            <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit" title="<spring:message code="com.btn.edit"/>">
                                <i class="layui-icon layui-icon-edit"></i>
                                <cite><spring:message code="com.btn.edit"/></cite>
                            </a>
                        </shiro:hasPermission>
                        <shiro:hasPermission name="sys_user_delete">
                            <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" title="<spring:message code="com.btn.del"/>">
                                <i class="layui-icon layui-icon-delete"></i>
                                <cite><spring:message code="com.btn.del"/></cite>
                            </a>
                        </shiro:hasPermission>
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

        table.init('sysUserList_${menuId}',{
            id: 'sysUserList_${menuId}',
            url: '${ctx}/sys/user/find',
            page: true
        });

        element.render('breadcrumb');

        $('#query_${menuId}').bind('click',table,query);
        $('#add_${menuId}').bind('click',toAdd);
        $('#excel_${menuId}').bind('click',exportExcel);

        table.on('tool(sysUserList_${menuId})',function(obj){
            var data = obj.data;
            data.menuId = '${menuId}';
            switch(obj.event){
                case 'del':
                    Frame.confirm(WebUtils.getMessage('sys.user.tip.delUser'),function(){
                        $.ajax({
                            url: APP_ENV + '/sys/user/delete',
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
                    Frame.loadPage('${menuId}','sys/user/toUpdate?menuId=${menuId}',data,WebUtils.getMessage('com.btn.modify'),400);
                    break;
            };
        });
    });

    function query(event){
        var table = event.data;
        table.reload('sysUserList_${menuId}',{
            where:{
                state: WebUtils.fmtStr($('#state_${menuId}').val()),
                searchKey: WebUtils.fmtStr($('#searchKey_${menuId}').val())
            },
            page:{
                curr:1
            }
        });
    }

    function toAdd(){
        Frame.loadPage('${menuId}','sys/user/toAdd?menuId=${menuId}',{},WebUtils.getMessage('com.btn.add'),400);
    }

    function exportExcel(){
        var layId = 'sysUserList_${menuId}';
        Frame.exportExcel(layId,'${ctx}/sys/user/find',{
            state: WebUtils.fmtStr($('#state_${menuId}').val()),
            searchKey: WebUtils.fmtStr($('#searchKey_${menuId}').val())
        },'用户列表');
    }

</script>