<%--
  Created by IntelliJ IDEA.
  User: 17869
  Date: 2020/4/20
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/common/tags.jsp" %>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:;">可可小城</a>
        <a><cite>业主信息</cite></a>
    </span>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">业主信息导入</div>
                <div class="layui-card-body">
                    <form class="layui-form">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">业主信息</label>
                                <div class="layui-input-inline">
                                    <input type="text" class="layui-input" id="filePath_${menuId}" disabled/>
                                </div>
                                <div class="layui-input-inline">
                                    <button type="button" class="layui-btn layui-btn-normal" id="residentImportBtn_${menuId}">
                                        <i class="layui-icon">&#xe67c;</i>选择文件
                                    </button>
                                </div>
                                <div class="layui-input-inline">
                                    <button class="layui-btn" lay-submit="" lay-filter="residentUpload" id="residentUploadBtn_${menuId}" onclick="return false">
                                        立即上传
                                    </button>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="layui-card">
                <div class="layui-card-header">
                    <form class="layui-form" lay-filter="searchFormResident" lay-filter="searchFormResident">
                        <div class="layui-form-item">
                            <label class="layui-form-label">查找方式</label>
                            <div class="layui-inline">
                                <div class="layui-input-inline">
                                    <select id="searchType_${menuId}" name="searchType" lay-filter="searchType_${menuId}">
                                        <option value="">全部</option>
                                        <option value="0">楼栋</option>
                                        <option value="1">房号</option>
                                        <option value="2">户主名称</option>
                                        <option value="3">身份证</option>
                                        <option value="4">手机号</option>
                                    </select>
                                </div>
                            </div>
                            <div class="layui-inline">
                                <div class="layui-input-inline">
                                    <input type="text" id="searchKey_${menuId}" name="searchKey" class="layui-input" placeholder="请输入你要查找的内容" disabled>
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
                    <table class="layui-table" lay-filter="residentList_${menuId}" lay-data="{
                        url:'${ctx}/keke/resident/find',
                        id:'residentList_${menuId}',
                        page:true,
                        }">
                        <thead>
                        <tr>
                            <th lay-data="{checkbox:true}"></th>
<%--                            <th lay-data="{field:'id',width:100,align:'center',hidden:true}">ID</th>--%>
                            <th lay-data="{field:'building',width:80,align:'center'}">楼栋</th>
                            <th lay-data="{field:'houseNo',width:80,align:'center'}">房号</th>
                            <th lay-data="{field:'houseHolder',width:150,align:'center'}">户主名</th>
                            <th lay-data="{field:'phone',width:150,align:'center'}">联系方式</th>
                            <th lay-data="{field:'idCardNo',width:200,align:'center'}">身份证</th>
                            <th lay-data="{field:'area',width:120,align:'center'}">建筑面积</th>
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
    layui.use(['element','table','form','upload'],function(){
        var element = layui.element;
        var table = layui.table;
        var form = layui.form;
        var upload = layui.upload;

        upload.render({
            elem: '#residentImportBtn_${menuId}'
            ,url: APP_ENV + '/keke/resident/uploadExcelData'
            ,auto:false
            ,bindAction:'#residentUploadBtn_${menuId}'
            ,before:function(){
                // this.data = {
                //     name: $('input[name="name"]').val()
                // }
            }
            ,choose: function(obj){
                obj.preview(function(index,file,result){
                    $('#filePath_${menuId}').val(file.name)
                });
            }
            ,done: function(res, index, upload){ //上传后的回调
                console.log(res);
                if(res.code == 0){
                    Frame.alert('上传成功');
                }else{
                    Frame.alert('上传失败，' + res.msg);
                }

            }
            ,accept: 'file' //允许上传的文件类型
            ,size: 3 * 1024 //最大允许上传的文件大小,KB
            ,exts:'xls|xlsx'
        });

        form.render('select');
        element.render('breadcrumb');

        form.on('select(searchType_${menuId})', function(data){
            // console.log(data.elem); //得到select原始DOM对象
            // console.log(data.value); //得到被选中的值
            // console.log(data.othis); //得到美化后的DOM对象
            if(data.value == ''){
                $('#searchKey_${menuId}').attr('disabled',true)
            }else{
                $('#searchKey_${menuId}').attr('disabled',false)
            }
        });

        table.init('residentList_${menuId}',{
            id: 'residentList_${menuId}',
            url: '${ctx}/keke/resident/find',
            page: true
        });


        $('#query_${menuId}').bind('click',residentReload);
        function residentReload(){
            table.reload('residentList_${menuId}',{
                where:{
                    searchType: $('#searchType_${menuId}').val(),
                    searchKey: $('#searchKey_${menuId}').val()
                },
                page:{
                    curr:1
                }
            })
        }

        $('#add_${menuId}').bind('click',toAdd);
        function toAdd(){
            Frame.modMainTab({
                id:'${menuId}',
                url:APP_ENV + '/keke/resident/toAdd',
                params:{
                    menuId:'${menuId}'
                }
            });
        }

        table.on('tool(residentList_${menuId})',function(obj){
            var data = obj.data;
            data.menuId = '${menuId}';
            switch(obj.event){
                case 'del':
                    Frame.confirm('确定删除该户吗？',function(){
                        $.ajax({
                            url: APP_ENV + '/keke/resident/delete',
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
                        url:APP_ENV + '/keke/resident/toUpdate',
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
</script>