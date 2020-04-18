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
        <div class="layui-col-md7">
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
                            <th lay-data="{field:'id',width:80,align:'center'}"><spring:message code="sys.role.field.id"/></th>
                            <th lay-data="{field:'name',width:180,align:'center'}"><spring:message code="sys.role.field.name"/></th>
                            <th lay-data="{field:'oper',fixed:'right',width:250,align:'center',toolbar:'#operBar_${menuId}'}"><spring:message code="com.btn.oper"/></th>
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
                        <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="auth" title="<spring:message code="com.btn.auth"/>">
                            <i class="iconfont">&#xe612;</i>
                            <cite><spring:message code="com.btn.auth"/></cite>
                        </a>
                    </script>
                </div>
            </div>

            <div class="layui-card">
                <div class="layui-card-header"><spring:message code="com.btn.oper"/></div>
                <div class="layui-card-body">
                    <form class="layui-form">
                        <input type="hidden" id="roleId_${menuId}" name="id" value=""/>
                        <div class="layui-form-item">
                            <label class="layui-form-label"><spring:message code="sys.role.field.name"/></label>
                            <div class="layui-input-inline">
                                <input type="text" id="roleName_${menuId}" name="name" class="layui-input" lay-verify="required" lay-verType="tips">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit="" lay-filter="sysRoleForm"><spring:message code="com.btn.save"/></button>
                                <button class="layui-btn layui-btn-primary" type="reset"><spring:message code="com.btn.reset"/></button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div class="layui-col-md5">
            <div class="layui-card">
                <div class="layui-card-header">
                    <span><spring:message code="sys.role.tip.permissionSet"/></span>
                    <span id="disAuthName_${menuId}" role-id="" style="font-weight: bold;"></span>
                    <a class="layui-btn layui-btn-sm layui-btn-warm" style="line-height:2.5em;margin-top:6px;float:right;" onclick="saveAuth()" title="<spring:message code="com.btn.save"/>">
                        <i class="iconfont">&#xe758;</i>
                    </a>
                </div>
                <div class="layui-card-body">
                    <div id="treeId" ></div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    var form,element,table,tree;
    layui.use(['form','element','table','tree'], function(){
        form = layui.form;
        element = layui.element;
        table = layui.table;
        tree = layui.tree;
        //渲染树
        loadTree(function(treeData){
            tree.render({
                id: 'resourcesTree_${menuId}'
                ,elem: '#treeId'  //绑定元素
                ,showCheckbox: true   //是否显示复选框
                ,data: treeData
            });
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
                            data:{roleId: data.id},
                            dataType:'json',
                            success:function (result) {
                                if(result.code == 0){
                                    $('#query_${menuId}').click();
                                    return ;
                                }
                                Frame.info(result.msg);
                            }
                        });
                    });
                    break;
                case 'edit':
                    $("#roleId_${menuId}").val(data.id);
                    $("#roleName_${menuId}").val(data.name);
                    //var title = WebUtils.getMessage('com.btn.update');
                    //Frame.loadPage('${menuId}','sys/role/toUpdate?menuId=${menuId}',data,WebUtils.getMessage('com.btn.modify'),400);
                    break;
                case 'auth':
                    $.ajax({
                        url: APP_ENV + '/sys/resource/owner/' + data.id,
                        dataType: 'json',
                        success: function(result){
                            $('#disAuthName_${menuId}').html("[" + data.name + "]").attr("role-id",data.id);
                            tree.reload('resourcesTree_${menuId}',{});
                            if(result && result.length > 0){
                                var checkedIds = [];
                                for(var i=0;i<result.length;i++){
                                    var resNode = result[i];
                                    checkedIds.push(resNode.id);
                                }
                                tree.setChecked('resourcesTree_${menuId}',checkedIds);
                            }
                        }
                    });
                    break;
            };
        });

        form.on('submit(sysRoleForm)',function(data){
            $.ajax({
                url: APP_ENV + '/sys/role/save',
                data: data.field,
                dataType: 'json',
                success: function(result){
                    if(result.code == 0){
                        $("#roleId_${menuId}").val("");
                        $("#roleName_${menuId}").val("");

                        $('#query_${menuId}').click();
                    }else{
                        Frame.info(result.msg);
                    }
                }
            });
            return false;
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
        $("#roleId_${menuId}").val("");
        $("#roleName_${menuId}").val("");
        //Frame.loadPage('${menuId}','sys/role/toAdd?menuId=${menuId}',{},WebUtils.getMessage('com.btn.add'),400);
    }

    function exportExcel(){
        var layId = 'sysRoleList_${menuId}';
        Frame.exportExcel(layId,'${ctx}/sys/role/find',{
            searchKey: WebUtils.fmtStr($('#searchKey_${menuId}').val())
        },'用户列表');
    }

    function loadTree(callback){
        $.ajax({
            url: APP_ENV + '/sys/resource/list',
            dataType: 'json',
            success: function(treeData){
                if(treeData && typeof(callback) == 'function'){
                    callback(treeData);
                }
            }
        });
    }

    function saveAuth(){
        //获得选中的节点
        var checkData = tree.getChecked('resourcesTree_${menuId}');
        var roleId = $('#disAuthName_${menuId}').attr('role-id');
        if(WebUtils.isEmpty(roleId)){
            Frame.info(WebUtils.getMessage('sys.role.tip.selectRole'));
            return ;
        }
        if(checkData.length == 0){
            Frame.info(WebUtils.getMessage('sys.role.tip.selectPermissions'));
            return ;
        }

        var ids = "";
        for(var i=0;i<checkData.length;i++){
            var item = checkData[i];
            ids += item.id + ','
            if(item.children instanceof Array){
                ids = getChildIds(item.children, ids);
            }
        }
        if(ids.length > 0){
            ids = ids.substring(0,ids.length - 1);
        }

        $.ajax({
            url: APP_ENV + '/sys/role/authorization',
            data:{
                roleId: roleId,
                ids: ids
            },
            dataType:'json',
            success: function(result){
                if(result.code == 0){
                    $('#disAuthName_${menuId}').html("").attr("role-id","");
                }
                Frame.info(result.msg);
            }
        });
    }

    function getChildIds(ary, ids){
        for(var i=0;i<ary.length;i++){
            var item = ary[i];
            ids += item.id + ',';
            if(item.children instanceof Array){
                ids = getChildIds(item.children, ids);
            }
        }
        return ids;
    }

</script>