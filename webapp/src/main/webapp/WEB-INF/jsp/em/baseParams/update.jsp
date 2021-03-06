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
        <a href="javascript:;">评估系统</a>
        <a><cite>电机参数</cite></a>
    </span>
    <a class="layui-btn layui-btn-sm layui-btn-primary" style="line-height:2.5em;margin-top:4px;float:right;" onclick="Frame.goBack('${menuId}','/em/baseParams/toMain');" title="<spring:message code="com.goBack"/>">
        <i class="iconfont">&#xe650;</i><spring:message code="com.goBack"/>
    </a>
</div>

<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <form class="layui-form layui-form-pane" lay-filter="updateForm" action="${ctx}/em/baseParams/update">
                <input type="hidden" name="id" value="${params.id}"/>
                <div class="layui-card">
                    <div class="layui-card-header">修改电机基本参数</div>
                    <div class="layui-card-body">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label" style="width: 170px;">额定电流（A）</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="fixedCurrent" value="${params.fixedCurrent}" lay-verify="required|number" lay-verType="tips" placeholder="请输入额定电流" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label" style="width: 170px;">电机机身长度（m）</label>
                                <div class="layui-input-inline">
                                    <input name="machineLength" value="${params.machineLength}" lay-verify="required|number" lay-verType="tips" placeholder="请输入电机机身长度" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label" style="width: 170px;">额定电压（V）</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="fixedVoltage" value="${params.fixedVoltage}" lay-verify="required|number" lay-verType="tips" placeholder="请输入额定电压" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label" style="width: 170px;">横截面高度（m）</label>
                                <div class="layui-input-inline">
                                    <input name="machineHeight" value="${params.machineHeight}" lay-verify="required|number" lay-verType="tips" placeholder="请输入横截面高度" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label" style="width: 170px;">额定转速（r/min）</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="fixedSpeed" value="${params.fixedSpeed}" lay-verify="required|number" lay-verType="tips" placeholder="请输入额定转速" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label" style="width: 170px;">横截面宽度（m）</label>
                                <div class="layui-input-inline">
                                    <input name="machineWidth" value="${params.machineWidth}" lay-verify="required|number" lay-verType="tips" placeholder="请输入横截面宽度" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label" style="width: 170px;">额定转矩（N·m）</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="fixedTorque" value="${params.fixedTorque}" lay-verify="required|number" lay-verType="tips" placeholder="请输入额定转矩" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label" style="width: 170px;">转子轴前端长度（m）</label>
                                <div class="layui-input-inline">
                                    <input name="rotorLength" value="${params.rotorLength}" lay-verify="required|number" lay-verType="tips" placeholder="请输入转子轴前端长度" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label" style="width: 170px;">电机过载能力（倍）</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="overloadCapacity" value="${params.overloadCapacity}" lay-verify="required|number" lay-verType="tips" placeholder="请输入电机过载能力" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label" style="width: 170px;">电机质量（kg）</label>
                                <div class="layui-input-inline">
                                    <input name="machineWeight" value="${params.machineWeight}" lay-verify="required|number" lay-verType="tips" placeholder="请输入电机质量" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit="" lay-filter="updateForm"><spring:message code="com.btn.save"/></button>
                                <button class="layui-btn layui-btn-primary" type="reset"><spring:message code="com.btn.reset"/></button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    layui.use(['element','form'],function(){
        var element = layui.element;
        var form = layui.form;
        element.render('breadcrumb');

        form.on('submit(updateForm)',function(data){
            console.log(data.elem);
            console.log(data.form);
            console.log(data.field);
            $.ajax({
                url: data.form.action,
                data: data.field,
                dataType: 'json',
                success: function(result){
                    if(result.code == 0){
                        $('#query_${menuId}').click();
                        Frame.goBack('${menuId}','/em/baseParams/toMain');
                    }
                    Frame.alert(result.msg);
                }
            })
            return false;
        });
        form.render();
    });
</script>