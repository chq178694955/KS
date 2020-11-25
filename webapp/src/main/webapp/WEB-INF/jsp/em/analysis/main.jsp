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
        <a href="javascript:;">伺服电机</a>
        <a><cite>性能评估</cite></a>
    </span>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <blockquote class="layui-elem-quote">
                <p style="color: #1E9FFF;font-weight: bold;">
                    1.性能评估必须有可以试验的数据作为支撑，数据可以在<span class="x-red">首页</span>的Excel导入按钮将数据导入到系统当中。<br/>
                    2.导入数据的Excel<span class="x-red">文件名</span>会作为本次数据的名称保存到系统当中，请取自己理解的文件名。<span class="x-red">相同文件名导入的数据会对系统已存在的数据进行覆盖。</span><br/>
                    3.本系统预设了基本参数值，<span class="x-red">非专业人士要请勿修改</span>。如果对于基本参数不满意可以自行添加自己需要的即可。<br/>
                    4.本系统预设了最优指标以及默认指标权重，<span class="x-red">非专业人士要请勿修改</span>。如果对于预设指标不满意可以自行添加自己需要的即可（每个用户都可以配置属于自己的指标，但系统预设指标属于共用）。<br/>
                    <span class="layui-badge-dot"></span>
                    <span class="layui-badge-dot layui-bg-orange"></span>
                    <span class="layui-badge-dot layui-bg-green"></span>
                    <span class="layui-badge-dot layui-bg-cyan"></span>
                    <span class="layui-badge-dot layui-bg-blue"></span>
                    <span class="layui-badge-dot layui-bg-black"></span>
                    <span class="layui-badge-dot layui-bg-gray"></span>
                    <span class="layui-badge layui-bg-cyan">开始实验吧！</span>
                    <span class="layui-badge-dot layui-bg-gray"></span>
                    <span class="layui-badge-dot layui-bg-black"></span>
                    <span class="layui-badge-dot layui-bg-blue"></span>
                    <span class="layui-badge-dot layui-bg-cyan"></span>
                    <span class="layui-badge-dot layui-bg-green"></span>
                    <span class="layui-badge-dot layui-bg-orange"></span>
                    <span class="layui-badge-dot"></span>
                </p>
            </blockquote>

            <form class="layui-form layui-form-pane" lay-filter="experimentForm" action="${ctx}/em/myIndex/add">
                <div class="layui-card">
                    <div class="layui-card-body">
                        <!-- 1.实验数据开始 -->
                        <fieldset class="layui-elem-field">
                            <legend>实验数据</legend>
                            <div class="layui-field-box">
                                <div class="layui-form-item">
                                    <div class="layui-inline">
                                        <label class="layui-form-label" style="width: 180px;">实验数据</label>
                                        <div class="layui-input-inline">
                                            <select name="productId" id="productList_${menuId}" lay-filter="productSelectFilter" lay-verify="">
                                                <c:forEach var="product" items="${productList}">
                                                    <option value="${product.id}">${product.name}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <c:if test="${experimentDataObj.hasDataFlag}">
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
                                                <div class="layui-card-header">阶跃响应数据</div>
                                                <div class="layui-card-body">
                                                    <div id="stepDataList_${menuId}"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="layui-col-md3">
                                            <div class="layui-card">
                                                <div class="layui-card-header">正弦响应数据</div>
                                                <div class="layui-card-body">
                                                    <div id="sinDataList_${menuId}"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="layui-col-md3">
                                            <div class="layui-card">
                                                <div class="layui-card-header">空载实验数据</div>
                                                <div class="layui-card-body">
                                                    <div id="emptyloadDataList_${menuId}"></div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="layui-col-md3">
                                            <div class="layui-card">
                                                <div class="layui-card-header">恒定负载扰动数据</div>
                                                <div class="layui-card-body">
                                                    <div id="constantloadDataList_${menuId}"></div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                                <c:if test="${!experimentDataObj.hasDataFlag}">
                                    <p class="x-red" style="font-weight: bold;">${experimentDataObj.msg}</p>
                                </c:if>
                            </div>
                        </fieldset>
                        <!-- 1.实验数据结束 -->

                        <!-- 2.系统参数开始 -->
                        <fieldset class="layui-elem-field">
                            <legend>基本参数</legend>
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
                            <legend>系统指标</legend>
                            <div class="layui-field-box">
                                <div class="layui-form-item">
                                    <div class="layui-input-block">
                                        <input type="radio" name="radioIndex" value="1" title="系统指标" checked/>
                                        <input type="radio" name="radioIndex" value="0" title="用户自定义"/>
                                    </div>
                                </div>

                                <fieldset style="border-color: #01AAED;border-width: 1px;">
                                    <legend>动态控制性能</legend>
                                    <div class="layui-field-box">
                                        <div class="layui-form-item">
                                            <div class="layui-inline">
                                                <label class="layui-form-label" style="width: 180px;">调整时间（ms）</label>
                                                <div class="layui-input-inline">
                                                    <input type="text" name="radioParams" lay-verify="required|number" class="layui-input"/>
                                                </div>
                                            </div>
                                            <div class="layui-inline">
                                                <label class="layui-form-label" style="width: 180px;">峰值时间（ms）</label>
                                                <div class="layui-input-inline">
                                                    <input type="text" name="radioParams" lay-verify="required|number" class="layui-input"/>
                                                </div>
                                            </div>
                                            <div class="layui-inline">
                                                <label class="layui-form-label" style="width: 180px;">超调量（%）</label>
                                                <div class="layui-input-inline">
                                                    <input type="text" name="radioParams" lay-verify="required|number" class="layui-input"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="layui-form-item">
                                            <div class="layui-inline">
                                                <label class="layui-form-label" style="width: 180px;">转速调整率（%）</label>
                                                <div class="layui-input-inline">
                                                    <input type="text" name="radioParams" lay-verify="required|number" class="layui-input"/>
                                                </div>
                                            </div>
                                            <div class="layui-inline">
                                                <label class="layui-form-label" style="width: 180px;">转速变化率（%）</label>
                                                <div class="layui-input-inline">
                                                    <input type="text" name="radioParams" lay-verify="required|number" class="layui-input"/>
                                                </div>
                                            </div>
                                            <div class="layui-inline">
                                                <label class="layui-form-label" style="width: 180px;">正反转速差率（%）</label>
                                                <div class="layui-input-inline">
                                                    <input type="text" name="radioParams" lay-verify="required|number" class="layui-input"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </fieldset>

                                <fieldset style="border-color: #01AAED;border-width: 1px;">
                                    <legend>稳态控制性能</legend>
                                    <div class="layui-field-box">
                                        <div class="layui-form-item">
                                            <div class="layui-inline">
                                                <label class="layui-form-label" style="width: 180px;">调速范围（倍）</label>
                                                <div class="layui-input-inline">
                                                    <input type="text" name="radioParams" lay-verify="required|number" class="layui-input"/>
                                                </div>
                                            </div>
                                            <div class="layui-inline">
                                                <label class="layui-form-label" style="width: 180px;">过载能力（倍）</label>
                                                <div class="layui-input-inline">
                                                    <input type="text" name="radioParams" lay-verify="required|number" class="layui-input"/>
                                                </div>
                                            </div>
                                            <div class="layui-inline">
                                                <label class="layui-form-label" style="width: 180px;">转速平均误差（r/min）</label>
                                                <div class="layui-input-inline">
                                                    <input type="text" name="radioParams" lay-verify="required|number" class="layui-input"/>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="layui-form-item">
                                            <div class="layui-inline">
                                                <label class="layui-form-label" style="width: 180px;">转矩平均误差（N·m）</label>
                                                <div class="layui-input-inline">
                                                    <input type="text" name="radioParams" lay-verify="required|number" class="layui-input"/>
                                                </div>
                                            </div>
                                            <div class="layui-inline">
                                                <label class="layui-form-label" style="width: 180px;">转速波动系数（%）</label>
                                                <div class="layui-input-inline">
                                                    <input type="text" name="radioParams" lay-verify="required|number" class="layui-input"/>
                                                </div>
                                            </div>
                                            <div class="layui-inline">
                                                <label class="layui-form-label" style="width: 180px;">转矩波动系数（%）</label>
                                                <div class="layui-input-inline">
                                                    <input type="text" name="radioParams" lay-verify="required|number" class="layui-input"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </fieldset>

                                <fieldset style="border-color: #01AAED;border-width: 1px;">
                                    <legend>伺服电机本体设计</legend>
                                    <div class="layui-field-box">
                                        <div class="layui-form-item">
                                            <div class="layui-inline">
                                                <label class="layui-form-label" style="width: 180px;">功率密度（kw/kg）</label>
                                                <div class="layui-input-inline">
                                                    <input type="text" name="radioParams" lay-verify="required|number" class="layui-input"/>
                                                </div>
                                            </div>
                                            <div class="layui-inline">
                                                <label class="layui-form-label" style="width: 180px;">电机体积（m<sup>3</sup>）</label>
                                                <div class="layui-input-inline">
                                                    <input type="text" name="radioParams" lay-verify="required|number" class="layui-input"/>
                                                </div>
                                            </div>
                                            <div class="layui-inline">
                                                <label class="layui-form-label" style="width: 180px;">电机质量（kg）</label>
                                                <div class="layui-input-inline">
                                                    <input type="text" name="radioParams" lay-verify="required|number" class="layui-input"/>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="layui-form-item">
                                            <div class="layui-inline">
                                                <label class="layui-form-label" style="width: 180px;">电机轴向尺寸（m）</label>
                                                <div class="layui-input-inline">
                                                    <input type="text" name="radioParams" lay-verify="required|number" class="layui-input"/>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </fieldset>
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
                <div class="layui-card-body">
                    <!-- 5.分析结果开始 -->
                    <fieldset class="layui-elem-field">
                        <legend>结果展示</legend>
                        <div class="layui-field-box">
                            <!-- tab控件别放入form当中，会导致表单元素和列表元素部分失效 -->
                            <div class="layui-tab layui-tab-brief">
                                <ul class="layui-tab-title">
                                    <li class="layui-this">评估结果</li>
                                    <li>动态控制性能</li>
                                    <li>稳态控制性能</li>
                                    <li>伺服电机本体设计</li>
                                    <li>综合性能差异显示</li>
                                </ul>
                                <div class="layui-tab-content"></div>
                            </div>
                        </div>
                    </fieldset>
                    <!-- 5.分析结果结束 -->
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    layui.use(['form','element','table','laydate'], function() {
        var element = layui.element;
        var form = layui.form;
        var table = layui.table;
        element.render('breadcrumb');
        form.render();

        var hasDataFlag = ${experimentDataObj.hasDataFlag};
        if(hasDataFlag){
            var stepList = ${experimentDataObj.stepList};
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
                data: stepList
            });

            var sinList = ${experimentDataObj.sinList};
            table.render({
                elem: '#sinDataList_${menuId}',
                title:'正弦响应数据',
                height:300,
                cols:[
                    [
                        {field:'speed10',width:120,title:'转速（r/min）',align:'center'}
                    ]
                ],
                data: sinList
            });

            var emptyloadList = ${experimentDataObj.emptyloadList};
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
                data: emptyloadList
            });

            var constantloadList = ${experimentDataObj.constantloadList};
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
                data: constantloadList
            });
        }

        //实验项目下拉选中事件
        form.on('select(productSelectFilter)', function(data){
            console.log(data.elem); //得到select原始DOM对象
            console.log(data.value); //得到被选中的值
            console.log(data.othis); //得到美化后的DOM对象
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

        //开始评估
        form.on('submit(experimentForm)',function(data){
            console.log(data.elem);
            console.log(data.form);
            console.log(data.field);
            /*
            $.ajax({
                url: data.form.action,
                data: data.field,
                dataType: 'json',
                success: function(result){
                    if(result.code == 0){
                        $('#query_${menuId}').click();
                        layer.close(Frame.Layer.layerIndex);
                    }
                    Frame.alert(result.msg);
                }
            })
            */
            return false;
        });

    });
</script>