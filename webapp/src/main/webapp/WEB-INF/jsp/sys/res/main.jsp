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
        <a><cite><spring:message code="sys.menu.resMgr"/></cite></a>
    </span>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md5">
            <div class="layui-card">
                <div class="layui-card-header">
                    <span><spring:message code="sys.role.tip.permissionSet"/></span>
                </div>
                <div class="layui-card-body">
                    <div id="resTree_${menuId}"></div>
                </div>
            </div>
        </div>
        <div class="layui-col-md7">
            <div class="layui-card">
                <div class="layui-card-header">
                    <span><spring:message code="com.btn.oper"/></span>
                </div>
                <div class="layui-card-body">
                    <div class="layui-form layui-form-pane">
                        <input type="hidden" name="id" id="treeId_${menuId}"/>
                        <input type="hidden" name="pid" id="treeParentId_${menuId}"/>
                        <div class="layui-form-item">
                            <label class="layui-form-label"><spring:message code="sys.resource.field.parentName"/></label>
                            <div class="layui-input-block">
                                <input type="text" class="layui-input" id="parentName_${menuId}" name="parentName" lay-verify="" lay-verType="tips" disabled />
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label"><spring:message code="sys.resource.field.name"/></label>
                            <div class="layui-input-block">
                                <input type="text" class="layui-input" id="name_${menuId}" name="name" lay-verify="required" lay-verType="tips">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label"><spring:message code="sys.resource.field.url"/></label>
                            <div class="layui-input-block">
                                <input type="text" class="layui-input" id="url_${menuId}" name="url" lay-verify="required" lay-verType="tips">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label"><spring:message code="sys.resource.field.type"/></label>
                            <div class="layui-input-inline">
                                <select name="type" id="type_${menuId}">
                                    <option value="0" selected><spring:message code="sys.resource.field.type.menu"/></option>
                                    <option value="1"><spring:message code="sys.resource.field.type.tab"/></option>
                                    <option value="2"><spring:message code="sys.resource.field.type.btn"/></option>
                                </select>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label"><spring:message code="sys.resource.field.icon"/></label>
                            <div class="layui-input-inline">
                                <input type="text" class="layui-input" id="icon_${menuId}" name="icon">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button type="button" id="add_${menuId}" class="layui-btn layui-btn-normal">
                                    <i class="layui-icon layui-icon-add-1"></i>
                                    <cite><spring:message code="com.btn.add"/></cite>
                                </button>
                                <button type="button" id="del_${menuId}" class="layui-btn layui-btn-danger">
                                    <i class="layui-icon layui-icon-delete"></i>
                                    <cite><spring:message code="com.btn.del"/></cite>
                                </button>
                                <button id="save_${menuId}" class="layui-btn layui-btn-warm" lay-submit="" lay-filter="saveForm">
                                    <i class="layui-icon layui-icon-set"></i>
                                    <cite><spring:message code="com.btn.save"/></cite>
                                </button>
                            </div>
                        </div>
                    </div>
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
            renderTree(treeData);
        });

        element.render('breadcrumb');
        form.render('select');

        form.on('submit(saveForm)',function(data){
            var fields = data.field;
            $.ajax({
                url: APP_ENV + '/sys/resource/save',
                data: fields,
                dataType: 'json',
                success: function(result){
                    if(result.code == 0){
                        clearForm();

                        //渲染树
                        loadTree(function(treeData){
                            renderTree(treeData);
                        });
                    }
                    Frame.info(result.msg);
                }
            });
            return false;
        });

        $('#add_${menuId}').bind('click',addResource);
        $('#del_${menuId}').bind('click',delResource);
    });

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

    function renderTree(treeData){
        tree.render({
            id: 'resourcesTree_${menuId}'
            ,elem: '#resTree_${menuId}'  //绑定元素
            ,showCheckbox: false   //是否显示复选框
            ,data: treeData
            ,click: function(obj){
                //console.log(obj.data); //得到当前点击的节点数据
                //console.log(obj.state); //得到当前节点的展开状态：open、close、normal
                //console.log(obj.elem); //得到当前节点元素
                //console.log(obj.data.children); //当前节点下是否有子节点
                var curTreeId = obj.data.id;
                var prevTreeId = $("#treeId_${menuId}").val();

                if(curTreeId+"" !== prevTreeId){
                    $('div [data-id="'+curTreeId+'"] div').eq(1).last().css('background-color','#87eaa3');
                    $('div [data-id="'+prevTreeId+'"] div').eq(1).last().css('background-color','');
                    loadNode(obj.data.id,function(res){
                        setFrom(res);
                    });
                }else{
                    $('div [data-id="'+curTreeId+'"] div').eq(1).last().css('background-color','');
                    clearForm();
                }
            }
        });
    }

    function loadNode(resId,callback){
        $.ajax({
            url: APP_ENV + '/sys/resource/get/' + resId,
            dataType:'json',
            success: function(node){
                if(node && typeof callback == 'function'){
                    callback(node);
                }
            }
        });
    }

    function setFrom(res){
        $('#parentName_${menuId}').val(res.parentName);
        $('#name_${menuId}').val(res.name);
        $('#treeParentId_${menuId}').val(res.pid);
        $('#treeId_${menuId}').val(res.id);
        $('#url_${menuId}').val(res.url);
        $('#type_${menuId}').val(res.type);
        $('#icon_${menuId}').val(res.icon);
        form.render('select');
    }

    function clearForm(){
        $('#parentName_${menuId}').val('');
        $('#name_${menuId}').val('');
        $('#treeParentId_${menuId}').val('');
        $('#treeId_${menuId}').val('');
        $('#url_${menuId}').val('');
        $('#type_${menuId}').val('0');
        $('#icon_${menuId}').val('');
        form.render('select');
    }

    function addResource(){
        var treeId = $('#treeId_${menuId}').val();
        if(treeId !== ''){
            $('#parentName_${menuId}').val($('#name_${menuId}').val());
            $('#name_${menuId}').val('');
            $('#treeParentId_${menuId}').val($('#treeId_${menuId}').val());
            $('#treeId_${menuId}').val('');
            $('#url_${menuId}').val('');
            $('#type_${menuId}').val('0');
            $('#icon_${menuId}').val('');
        }else{
            $('#parentName_${menuId}').val('');
            $('#name_${menuId}').val('');
            $('#treeParentId_${menuId}').val('-1');
            $('#treeId_${menuId}').val('');
            $('#url_${menuId}').val('');
            $('#type_${menuId}').val('0');
            $('#icon_${menuId}').val('');
        }
        form.render('select');

    }

    function delResource(){
        var resId = $('#treeId_${menuId}').val();
        if(resId != ''){
            $.ajax({
                url: APP_ENV + '/sys/resource/del/' + resId,
                dataType: 'json',
                success: function(result){
                    if(result.code == 0){
                        clearForm();

                        //渲染树
                        loadTree(function(treeData){
                            renderTree(treeData);
                        });
                    }
                    Frame.info(result.msg);
                }
            });
        }else{
            Frame.info(WebUtils.getMessage('sys.resource.tip.selectResource'));
            return ;
        }
    }

</script>