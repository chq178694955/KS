<%--
  Created by IntelliJ IDEA.
  User: 17869
  Date: 2020/4/10
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">请选择一条自定义指标</div>
                <div class="layui-card-body">
                    <table id="customIndexDataList_${menuId}" lay-filter="customIndexDataList_${menuId}"></table>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button class="layui-btn" id="selCustomIndex_${menuId}">选择</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    layui.use(['element','form','table'],function(){
        var element = layui.element;
        var table = layui.table;

        table.render({
            elem: '#customIndexDataList_${menuId}',
            url: '${ctx}/em/analysis/findIndexGroup',
            width: 540,
            cols:[
                [
                    {checkbox:true},
                    {width:75,field:'id',title:'ID',align:'center'},
                    {width:150,field:'name',title:'分组名称',align:'center'}
                ]
            ],
            page: true
        });

        $('#selCustomIndex_${menuId}').bind('click',function(){
            var checkStatus = table.checkStatus('customIndexDataList_${menuId}');
            console.log(checkStatus.data) //获取选中行的数据
            console.log(checkStatus.data.length) //获取选中行数量，可作为是否有选中行的条件
            console.log(checkStatus.isAll ) //表格是否全选
            if(checkStatus.data.length == 0){
                Frame.info('请选择需要的数据',2);
                return ;
            }
            if(checkStatus.data.length > 1){
                Frame.info('只能选择一条数据',2);
                return ;
            }
            loadIndex(checkStatus.data[0],function(){
                Frame.closeLayer();//手动关闭弹出框
            });

        });

        function loadIndex(res,callback){
            $.ajax({
                url: APP_ENV + '/em/analysis/findCustomIndex',
                data:{
                    groupId: res.id
                },
                dataType:'json',
                success: function(datas){
                    if(datas){
                        for(var i=0;i<datas.length;i++){
                            var data = datas[i];
                            var indexId = data.indexId;
                            var valId = '#val_' + indexId + '_${menuId}';
                            var weightId = '#weight_' + indexId + '_${menuId}';
                            $(valId).val(data.val);
                            $(weightId).val(data.weight);
                        }
                        if(typeof(callback) == 'function'){
                            callback();
                        }
                    }else{
                        Frame.info('选择自定义指标失败，请重新选择！',2);
                    }
                }
            });
        }

    });
</script>