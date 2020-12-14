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
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:;">评估系统</a>
        <a><cite>性能评估</cite></a>
    </span>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <form class="layui-form layui-form-pane" lay-filter="experimentForm" action="${ctx}/em/analysis/startEstimate">
                <div class="layui-card">
                    <div class="layui-card-body">
                        <!-- 1.实验数据开始 -->
                        <fieldset class="layui-elem-field">
                            <legend style="font-weight: bold">实验数据</legend>
                            <div class="layui-field-box">
                                <div class="layui-form-item">
                                    <div class="layui-inline">
                                        <label class="layui-form-label" style="width: 180px;">实验数据</label>
                                        <div class="layui-input-inline">
                                            <select name="productId" id="productList_${menuId}" lay-filter="productSelectFilter" lay-verify="" lay-search>
                                                <c:forEach var="product" items="${productList}">
                                                    <option value="${product.id}">${product.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <div class="layui-inline">
                                        <label class="layui-form-label" style="width: 180px;">空载转速（r/min）</label>
                                        <div class="layui-input-inline">
                                            <input type="text" name="speedEmpty" id="speedEmpty_${menuId}" value="${experimentDataObj.speedEmpty}" class="layui-input" autocomplete="false" disabled/>
                                        </div>
                                    </div>
                                    <div class="layui-inline">
                                        <label class="layui-form-label" style="width: 180px;">额定负载转速（r/min）</label>
                                        <div class="layui-input-inline">
                                            <input type="text" name="speedFixedLoad" id="speedFixedLoad_${menuId}" value="${experimentDataObj.speedFixedLoad}" class="layui-input" autocomplete="false" disabled/>
                                        </div>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <div class="layui-inline">
                                        <label class="layui-form-label" style="width: 180px;">最大转速（r/min）</label>
                                        <div class="layui-input-inline">
                                            <input type="text" name="speedMax" id="speedMax_${menuId}" value="${experimentDataObj.speedMax}" class="layui-input" autocomplete="false" disabled/>
                                        </div>
                                    </div>
                                    <div class="layui-inline">
                                        <label class="layui-form-label" style="width: 180px;">最小转速（r/min）</label>
                                        <div class="layui-input-inline">
                                            <input type="text" name="speedMin" id="speedMin_${menuId}" value="${experimentDataObj.speedMin}" class="layui-input" autocomplete="false" disabled/>
                                        </div>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <div class="layui-inline">
                                        <label class="layui-form-label" style="width: 180px;">转速设定值（r/min）</label>
                                        <div class="layui-input-inline">
                                            <input type="text" name="speedSetter" id="speedSetter_${menuId}" value="${experimentDataObj.speedSetter}" class="layui-input" autocomplete="false" disabled/>
                                        </div>
                                    </div>
                                    <div class="layui-inline">
                                        <label class="layui-form-label" style="width: 180px;">负载转矩（T·m）</label>
                                        <div class="layui-input-inline">
                                            <input type="text" name="torqueOverload" id="torqueOverload_${menuId}" value="${experimentDataObj.torqueOverload}" class="layui-input" autocomplete="false" disabled/>
                                        </div>
                                    </div>
                                </div>

                                <div class="layui-row">
                                    <div class="layui-col-md3">
                                        <div class="layui-card">
                                            <div class="layui-card-header" style="font-weight: bold">阶跃响应数据</div>
                                            <div class="layui-card-body">
                                                <table lay-filter="stepDataList_${menuId}" id="stepDataList_${menuId}" class="layui-table"></table>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="layui-col-md3">
                                        <div class="layui-card">
                                            <div class="layui-card-header" style="font-weight: bold">正弦响应数据</div>
                                            <div class="layui-card-body">
                                                <table lay-filter="sinDataList_${menuId}" id="sinDataList_${menuId}" class="layui-table"></table>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="layui-col-md3">
                                        <div class="layui-card">
                                            <div class="layui-card-header" style="font-weight: bold">空载实验数据</div>
                                            <div class="layui-card-body">
                                                <table lay-filter="emptyloadDataList_${menuId}" id="emptyloadDataList_${menuId}" class="layui-table"></table>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="layui-col-md3">
                                        <div class="layui-card">
                                            <div class="layui-card-header" style="font-weight: bold" class="layui-table">恒定负载扰动数据</div>
                                            <div class="layui-card-body">
                                                <table lay-filter="constantloadDataList_${menuId}" id="constantloadDataList_${menuId}" class="layui-table"></table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </fieldset>
                        <!-- 1.实验数据结束 -->

                        <!-- 2.系统参数开始 -->
                        <fieldset class="layui-elem-field">
                            <legend style="font-weight: bold">基本参数</legend>
                            <div class="layui-field-box">
                                <div class="layui-form-item">
                                    <div class="layui-input-block">
                                        <input type="radio" name="radioParams" lay-filter="radioParams" value="1" title="系统参数" checked/>
                                        <input type="radio" name="radioParams" lay-filter="radioParams" value="0" title="用户自定义"/>
                                    </div>
                                </div>

                                <div class="layui-form-item">
                                    <div class="layui-inline">
                                        <label class="layui-form-label" style="width: 180px;">额定电流（A）</label>
                                        <div class="layui-input-inline">
                                            <input type="text" name="fixedCurrent" id="fixedCurrent_${menuId}" lay-verify="required|number" class="layui-input"/>
                                        </div>
                                    </div>
                                    <div class="layui-inline">
                                        <label class="layui-form-label" style="width: 180px;">额定电压（V）</label>
                                        <div class="layui-input-inline">
                                            <input type="text" name="fixedVoltage" id="fixedVoltage_${menuId}" lay-verify="required|number" class="layui-input"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <div class="layui-inline">
                                        <label class="layui-form-label" style="width: 180px;">额定转速（r/min）</label>
                                        <div class="layui-input-inline">
                                            <input type="text" name="fixedSpeed" id="fixedSpeed_${menuId}" lay-verify="required|number" class="layui-input"/>
                                        </div>
                                    </div>
                                    <div class="layui-inline">
                                        <label class="layui-form-label" style="width: 180px;">额定转矩（N·m）</label>
                                        <div class="layui-input-inline">
                                            <input type="text" name="fixedTorque" id="fixedTorque_${menuId}" lay-verify="required|number" class="layui-input"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <div class="layui-inline">
                                        <label class="layui-form-label" style="width: 180px;">电机过载能力（倍）</label>
                                        <div class="layui-input-inline">
                                            <input type="text" name="overloadCapacity" id="overloadCapacity_${menuId}" lay-verify="required|number" class="layui-input"/>
                                        </div>
                                    </div>
                                    <div class="layui-inline">
                                        <label class="layui-form-label" style="width: 180px;">电机机身长度（m）</label>
                                        <div class="layui-input-inline">
                                            <input type="text" name="machineLength" id="machineLength_${menuId}" lay-verify="required|number" class="layui-input"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <div class="layui-inline">
                                        <label class="layui-form-label" style="width: 180px;">横截面高度（m）</label>
                                        <div class="layui-input-inline">
                                            <input type="text" name="machineHeight" id="machineHeight_${menuId}" lay-verify="required|number" class="layui-input"/>
                                        </div>
                                    </div>
                                    <div class="layui-inline">
                                        <label class="layui-form-label" style="width: 180px;">横截面宽度（m）</label>
                                        <div class="layui-input-inline">
                                            <input type="text" name="machineWidth" id="machineWidth_${menuId}" lay-verify="required|number" class="layui-input"/>
                                        </div>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <div class="layui-inline">
                                        <label class="layui-form-label" style="width: 180px;">转子轴前端长度（m）</label>
                                        <div class="layui-input-inline">
                                            <input type="text" name="rotorLength" id="rotorLength_${menuId}" lay-verify="required|number" class="layui-input"/>
                                        </div>
                                    </div>
                                    <div class="layui-inline">
                                        <label class="layui-form-label" style="width: 180px;">电机质量（kg）</label>
                                        <div class="layui-input-inline">
                                            <input type="text" name="machineWeight" id="machineWeight_${menuId}" lay-verify="required|number" class="layui-input"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </fieldset>
                        <!-- 2.系统参数结束 -->

                        <!-- 3.系统指标开始 -->
                        <fieldset class="layui-elem-field">
                            <legend style="font-weight: bold">系统指标</legend>
                            <div class="layui-field-box">
                                <div class="layui-form-item">
                                    <div class="layui-input-block">
                                        <input type="radio" name="radioIndex" lay-filter="radioIndex" value="1" title="系统指标" checked/>
                                        <input type="radio" name="radioIndex" lay-filter="radioIndex" value="0" title="用户自定义"/>
                                    </div>
                                </div>

                                <c:forEach var="category" items="${categories}">
                                    <fieldset style="border-color: #01AAED;border-width: 1px;">
                                        <legend style="font-weight: bold">${category.name}</legend>
                                        <div class="layui-field-box">
                                            <c:forEach var="template" items="${templates}">
                                                <c:if test="${category.id == template.categoryId}">
                                                    <blockquote class="layui-elem-quote">
                                                        <span style="color: #FF5722;font-weight: bold;">${template.name}</span>
                                                        <span style="color: #FF5722;font-weight: bold;">（${template.unit}）</span>
                                                        <%--<span style="color: #FF5722;font-weight: bold;">【最大值：${template.maxVal}</span>--%>
                                                        <%--<span style="color: #FF5722;font-weight: bold;">、最小值：${template.maxVal}】</span>--%>
                                                    </blockquote>
                                                    <div class="layui-form-item">
                                                        <div class="layui-inline">
                                                            <label class="layui-form-label" style="width: 170px;">指标值</label>
                                                            <div class="layui-input-inline">
                                                                <!-- 表单数据名称采用 "val_" 加 原始指标id的方式，后台按照此规则进行解析 -->
                                                                <input type="text" name="val_${template.id}" id="val_${template.id}_${menuId}" lay-verify="required|number"  placeholder="请输入指标值" autocomplete="off" class="layui-input">
                                                            </div>
                                                        </div>

                                                        <div class="layui-inline">
                                                            <label class="layui-form-label" style="width: 170px;">权重值</label>
                                                            <div class="layui-input-inline">
                                                                <!-- 表单数据名称采用 "weight_" 加 原始指标id的方式，后台按照此规则进行解析 -->
                                                                <input type="text" name="weight_${template.id}" id="weight_${template.id}_${menuId}" lay-verify="required|number"  placeholder="请输入权重值" autocomplete="off" class="layui-input">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                    </fieldset>
                                </c:forEach>
                            </div>
                        </fieldset>
                        <!-- 3.系统指标结束 -->

                        <!-- 4.执行分析按钮开始 -->
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn layui-btn-radius layui-btn-normal" lay-submit="" lay-filter="experimentForm">
                                    <i class="layui-icon">&#xe62c;</i>开始评估<i class="layui-icon">&#xe62c;</i>
                                </button>
                            </div>
                        </div>
                        <!-- 4.执行分析按钮结束 -->
                    </div>
                </div>
            </form>
        </div>
    </div>

    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">
                    <span>评估结果数据</span>
                    <a id="downloadExperiment_${menuId}" class="layui-btn layui-btn-sm layui-btn-primary" style="line-height:2.5em;margin-top:4px;float:right;" href="javascript:void(0)" title="评估结果下载">
                        <i class="iconfont layui-icon-excel" style="color: #FF5722;"></i>评估结果下载
                    </a>
                </div>
                <div class="layui-card-body">
                    <form class="layui-form layui-form-pane" lay-filter="experimentResultForm">

                        <fieldset class="layui-elem-field" style="border-color: #5FB878;border-width: 1px;">
                            <legend style="font-weight: bold">等级评估</legend>
                            <div class="layui-field-box">
                                <div class="layui-form-item">
                                    <div class="layui-inline">
                                        <label class="layui-form-label" style="width: 180px;">综合性能评级</label>
                                        <div class="layui-input-inline">
                                            <input type="text" name="evaluation" id="evaluation_${menuId}" class="layui-input" disabled/>
                                        </div>
                                    </div>
                                </div>
                                <div class="layui-form-item">
                                    <c:forEach var="category" items="${categories}">
                                        <label class="layui-form-label" style="width: 180px;">${category.name}</label>
                                        <div class="layui-input-inline">
                                            <input type="text" name="evaluation_${category.id}" id="evaluation_${category.id}_${menuId}" class="layui-input" disabled/>
                                        </div>
                                    </c:forEach>
                                </div>
                            </div>
                        </fieldset>

                        <!-- 显示没有做归一化的指标结果 -->
                        <c:forEach var="category" items="${categories}">
                            <fieldset class="layui-elem-field" style="border-color: #5FB878;border-width: 1px;">
                                <legend style="font-weight: bold">${category.name}</legend>
                                <div class="layui-field-box">
                                    <div class="layui-form-item">
                                        <div class="layui-row">
                                            <c:forEach var="template" items="${templates}">
                                                <c:if test="${category.id == template.categoryId}">
                                                    <div class="layui-col-md4">
                                                        <label class="layui-form-label" style="width: 50%;">${template.name}（${template.unit}）</label>
                                                        <div class="layui-input-inline">
                                                            <input type="text" name="calc_${template.id}" id="calc_${template.id}_${menuId}" class="layui-input" disabled/>
                                                        </div>
                                                    </div>
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </fieldset>
                        </c:forEach>

                        <!-- 综合性能差异显示（折线图） -->
                        <fieldset class="layui-elem-field" style="border-color: #5FB878;border-width: 1px;">
                            <legend style="font-weight: bold">综合性能差异显示</legend>
                            <div class="layui-field-box">
                                <div id="experimentResultChart" style="height: 300px;">

                                </div>
                            </div>
                        </fieldset>

                    </form>
                </div>
            </div>
        </div>
    </div>

</div>

<script>
    var xData = [];//全局用于存放生成echarts图表的数据，为了导出excel使用不再进行计算
    var yData = [];//全局用于存放生成echarts图表的数据，为了导出excel使用不再进行计算
    layui.use(['form','element','table','laydate'], function() {
        var element = layui.element;
        var form = layui.form;
        var table = layui.table;
        element.render('breadcrumb');
        form.render();

        table.render({
            elem: '#stepDataList_${menuId}',
            title:'阶跃响应数据',
            height:300,
            cols:[
                [
                    {field:'dataTime',width:120,title:'时间（ms）',align:'center'},
                    {field:'speed',width:120,title:'转速（r/min）',align:'center'}
                ]
            ],
            data: [],
            limit:Number.MAX_VALUE
        });
        table.render({
            elem: '#sinDataList_${menuId}',
            title:'正弦响应数据',
            height:300,
            cols:[
                [
                    {field:'speed10',width:120,title:'转速（r/min）',align:'center'}
                ]
            ],
            data: [],
            limit:Number.MAX_VALUE
        });
        table.render({
            elem: '#emptyloadDataList_${menuId}',
            title:'空载实验数据',
            height:300,
            cols:[
                [
                    {field:'speedForward',width:150,title:'正向转速（r/min）',align:'center'},
                    {field:'speedReverse',width:150,title:'反向转速（r/min）',align:'center'}
                ]
            ],
            data: [],
            limit:Number.MAX_VALUE
        });
        table.render({
            elem: '#constantloadDataList_${menuId}',
            title:'恒定负载扰动实验数据',
            height:300,
            cols:[
                [
                    {field:'speed100',width:120,title:'转速（r/min）',align:'center'},
                    {field:'torque100',width:120,title:'转矩（N·m）',align:'center'}
                ]
            ],
            data: [],
            limit:Number.MAX_VALUE
        });

        var hasDataFlag = ${experimentDataObj.hasDataFlag};
        if(hasDataFlag){
            var stepList = ${experimentDataObj.stepList};
            table.reload('stepDataList_${menuId}',{
                data: stepList
            });

            var sinList = ${experimentDataObj.sinList};
            table.reload('sinDataList_${menuId}',{
                data: sinList
            });

            var emptyloadList = ${experimentDataObj.emptyloadList};
            table.reload('emptyloadDataList_${menuId}',{
                data: emptyloadList
            });

            var constantloadList = ${experimentDataObj.constantloadList};
            table.reload('constantloadDataList_${menuId}',{
                data: constantloadList
            });
        }else{
            Frame.info('${experimentDataObj.msg}',2);
        }

        //实验项目下拉选中事件
        form.on('select(productSelectFilter)', function(data){
            // console.log(data.elem); //得到select原始DOM对象
            // console.log(data.value); //得到被选中的值
            // console.log(data.othis); //得到美化后的DOM对象
            $.ajax({
                url: APP_ENV + '/em/analysis/reloadDatas',
                data:{
                    productId: data.value
                },
                dataType: 'json',
                success: function(res){
                    if(res && res.hasDataFlag){
                        $('#speedEmpty_${menuId}').val(res.speedEmpty);
                        $('#speedFixedLoad_${menuId}').val(res.speedFixedLoad);
                        $('#speedMax_${menuId}').val(res.speedMax);
                        $('#speedMin_${menuId}').val(res.speedMin);
                        $('#speedSetter_${menuId}').val(res.speedSetter);
                        $('#torqueOverload_${menuId}').val(res.torqueOverload);
                        table.reload('stepDataList_${menuId}', {
                            data: res.stepList
                        });

                        table.reload('sinDataList_${menuId}', {
                            data: res.sinList
                        });

                        table.reload('emptyloadDataList_${menuId}', {
                            data: res.emptyloadList
                        });

                        table.reload('constantloadDataList_${menuId}', {
                            data: res.constantloadList
                        });
                    }else{
                        Frame.info(res.msg,2);
                        table.render({
                            elem: '#stepDataList_${menuId}',
                            title:'阶跃响应数据',
                            height:300,
                            cols:[
                                [
                                    {field:'dataTime',width:120,title:'时间（ms）',align:'center'},
                                    {field:'speed',width:120,title:'转速（r/min）',align:'center'}
                                ]
                            ],
                            data: [],
                            limit:Number.MAX_VALUE
                        });
                        table.render({
                            elem: '#sinDataList_${menuId}',
                            title:'正弦响应数据',
                            height:300,
                            cols:[
                                [
                                    {field:'speed10',width:120,title:'转速（r/min）',align:'center'}
                                ]
                            ],
                            data: [],
                            limit:Number.MAX_VALUE
                        });
                        table.render({
                            elem: '#emptyloadDataList_${menuId}',
                            title:'空载实验数据',
                            height:300,
                            cols:[
                                [
                                    {field:'speedForward',width:150,title:'正向转速（r/min）',align:'center'},
                                    {field:'speedReverse',width:150,title:'反向转速（r/min）',align:'center'}
                                ]
                            ],
                            data: [],
                            limit:Number.MAX_VALUE
                        });
                        table.render({
                            elem: '#constantloadDataList_${menuId}',
                            title:'恒定负载扰动实验数据',
                            height:300,
                            cols:[
                                [
                                    {field:'speed100',width:120,title:'转速（r/min）',align:'center'},
                                    {field:'torque100',width:120,title:'转矩（N·m）',align:'center'}
                                ]
                            ],
                            data: [],
                            limit:Number.MAX_VALUE
                        });
                    }
                }
            });
        });

        //参数
        loadDefaultParams();
        function loadDefaultParams(){
            $.ajax({
                url: APP_ENV + '/em/analysis/findDefaultParams',
                dataType: 'json',
                success: function(res){
                    loadParams(res);
                }
            });
        }
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

        form.on('radio(radioParams)',function(data){
            console.log(data.value);// 0-用户自定义 1-系统预设
            if(data.value == 1){
                loadDefaultParams();
            }else{
                Frame.loadPage('${menuId}','em/analysis/toCustomParams?menuId=${menuId}',{},'用户自定义参数',600,450);
            }
        });

        form.on('radio(radioIndex)',function(data){
            console.log(data.value);// 0-用户自定义 1-系统预设
            if(data.value == 1){
                loadDefaultIndex();
            }else{
                Frame.loadPage('${menuId}','em/analysis/toCustomIndex?menuId=${menuId}',{},'用户自定义指标',600,450);
            }
        });

        //指标
        loadDefaultIndex();
        function loadDefaultIndex(){
            var templates = ${templates};
            for(var i=0;i<templates.length;i++){
                var template = templates[i];
                $('#val_' + template.id + '_${menuId}').val(template.bestVal);
                $('#weight_' + template.id + '_${menuId}').val(template.weight);
            }
        }


        //开始评估
        form.on('submit(experimentForm)',function(data){
            console.log(data.elem);
            console.log(data.form);
            console.log(data.field);

            $.ajax({
                url: data.form.action,
                data: data.field,
                dataType: 'json',
                beforeSend: function(){
                    Frame.load();
                },
                success: function(result){
                    if(result.code == 0){
                        //成功则显示分析结果
                        if(result.data){
                            xData = [];
                            yData = [];
                            if(result.data.categories){
                                $('#evaluation_${menuId}').val(result.data.evaluation);
                                for(var i=0;i<result.data.categories.length;i++){
                                    var category = result.data.categories[i];
                                    $('#evaluation_' + category.id + '_${menuId}').val(result.data['evaluation_' + category.id]);
                                }
                            }
                            if(result.data.templates){
                                for(var i=0;i<result.data.templates.length;i++){
                                    var template = result.data.templates[i];
                                    $('#calc_' + template.id + '_${menuId}').val(template.calcVal);
                                    xData.push(template.name);
                                    yData.push(template.normalVal);
                                }
                                //显示折线图
                                var chartOption = {
                                    title:{
                                        text:'归一化后性能差异'
                                    },
                                    tooltip:{
                                        trigger:'axis'
                                    },
                                    legend:{
                                        data:['性能指标']
                                    },
                                    xAxis:[{
                                        name:'性能指标',
                                        type:'category',
                                        boundaryGap:false,
                                        axisLabel:{
                                            interval:0,
                                            rotate:40
                                        },
                                        data:xData
                                    }],
                                    yAxis:[{
                                        name:'归一化结果',
                                        type:'value',
                                        max:1,
                                        min:0
                                    }],
                                    series:[
                                        {
                                            name:'性能指标',
                                            type:'line',
                                            data:yData
                                        }
                                    ]
                                };
                                WebUtils.Chart.createChart('experimentResultChart',chartOption);
                            }
                        }
                    }
                    if(result.msg && result.msg != undefined && result.msg != ''){
                        Frame.alert(result.msg);
                    }
                },
                complete: function(XMLHttpRequest, status){
                    Frame.closeLayer()
                }
            })
            return false;
        });

        //结果tab展示
        element.on('tab(analysisResult)',function(data){
            console.log(data);
        });

        $('#downloadExperiment_${menuId}').bind('click',downloadExperiment);
    });
    function downloadExperiment(){
        //此种方式可行
        //window.location.href=APP_ENV+"/em/analysis/downloadResult";
        //改为模拟表单方式下载文件，方便传参
        var url = APP_ENV+"/em/analysis/downloadResult";
        var downloadForm = $("<form></form>").attr("action",url).attr("method","post");
        var evaluation = $("#evaluation_${menuId}").val();
        if(WebUtils.isEmpty(evaluation)){
            Frame.alert("暂无结果分析，请开始评估");
            return ;
        }
        downloadForm.append($("<input/>").attr("type","hidden").attr("name","evaluation").attr("value",evaluation));
        <c:forEach var="category" items="${categories}">
            var evaluationItem = $("#evaluation_${category.id}_${menuId}").val();
            downloadForm.append($("<input/>").attr("type","hidden").attr("name","evaluation_${category.id}").attr("value",evaluationItem));
        </c:forEach>

        <c:forEach var="template" items="${templates}">
            var calcItem = $("#calc_${template.id}_${menuId}").val();
            downloadForm.append($("<input/>").attr("type","hidden").attr("name","calc_${template.id}").attr("value",calcItem));
        </c:forEach>

        downloadForm.append($("<input/>").attr("type","hidden").attr("name","xData").attr("value",xData));
        downloadForm.append($("<input/>").attr("type","hidden").attr("name","yData").attr("value",yData));
        var imgUrl = baseUrlFromEcharts();
        downloadForm.append($("<input/>").attr("type","hidden").attr("name","imgUrl").attr("value",imgUrl));
        downloadForm.appendTo("body").submit().remove();
    }

    /**
     * 将echarts图表转成图片
     * @returns {*|*|*|string}
     */
    function baseUrlFromEcharts(){
        var baseCanvas = $('#experimentResultChart').find("canvas").first()[0];
        if(!baseCanvas){
            return ;
        }
        var width = baseCanvas.width;
        var height = baseCanvas.height;
        var ctx = baseCanvas.getContext('2d');
        $('#experimentResultChart').find('canvas').each(function(i,canvasObj){
            if(i > 0){
                var canvasTmp = $(canvasObj)[0];
                ctx.drawImage(canvasTmp,0,0,width,height);
            }
        });
        return baseCanvas.toDataURL();
    }
</script>