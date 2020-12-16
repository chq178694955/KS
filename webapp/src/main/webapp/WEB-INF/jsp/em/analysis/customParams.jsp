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
                <div class="layui-card-header">请选择一条自定义参数</div>
                <div class="layui-card-body">
                    <table id="customParamsDataList_${menuId}" lay-filter="customParamsDataList_${menuId}"></table>
                    <div class="layui-form-item">
                        <div class="layui-input-block">
                            <button class="layui-btn" id="selCustomParams_${menuId}">选择</button>
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
            elem: '#customParamsDataList_${menuId}',
            url: '${ctx}/em/analysis/findCustomParams',
            width: 540,
            cols:[
                [
                    {checkbox:true},
                    {width:75,field:'id',title:'ID',align:'center'},
                    {width:150,field:'fixedCurrent',title:'额定电流（A）',align:'center'},
                    {width:150,field:'fixedVoltage',title:'额定电压（V）',align:'center'},
                    {width:150,field:'fixedSpeed',title:'额定转速（r/min）',align:'center'},
                    {width:150,field:'fixedTorque',title:'额定转矩（N·m）',align:'center'},
                    {width:150,field:'overloadCapacity',title:'过载能力（倍）',align:'center'},
                    {width:150,field:'machineLength',title:'机身长度（m）',align:'center'},
                    {width:150,field:'machineHeight',title:'机身高度（m）',align:'center'},
                    {width:150,field:'machineWidth',title:'机身宽度（m）',align:'center'},
                    {width:150,field:'rotorLength',title:'转子轴长度（m）',align:'center'},
                    {width:150,field:'machineWeight',title:'电机质量（kg）',align:'center'}
                ]
            ],
            page: true
        });

        table.on('checkbox(customDataList_${menuId})', function(obj){
            console.log(obj.checked); //当前是否选中状态
            console.log(obj.data); //选中行的相关数据
            console.log(obj.type); //如果触发的是全选，则为：all，如果触发的是单选，则为：one
        });

        $('#selCustomParams_${menuId}').bind('click',function(){
            var checkStatus = table.checkStatus('customParamsDataList_${menuId}');
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
            loadParams(checkStatus.data[0]);

        });

        function loadParams(res){
            $('#fixedCurrent_${menuId}').val(res.fixedCurrent);
            $('#fixedVoltage_${menuId}').val(res.fixedVoltage);
            $('#fixedSpeed_${menuId}').val(res.fixedSpeed);
            $('#fixedTorque_${menuId}').val(res.fixedTorque);
            $('#overloadCapacity_${menuId}').val(res.overloadCapacity);
            $('#machineLength_${menuId}').val(res.machineLength);
            $('#machineHeight_${menuId}').val(res.machineHeight);
            $('#machineWidth_${menuId}').val(res.machineWidth);
            $('#rotorLength_${menuId}').val(res.rotorLength);
            $('#machineWeight_${menuId}').val(res.machineWeight);
        }

    });
</script>