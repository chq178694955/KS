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
        <a href="javascript:;">业主信息</a>
        <a><cite>修改业主信息</cite></a>
    </span>
    <a class="layui-btn layui-btn-sm layui-btn-primary" style="line-height:2.5em;margin-top:4px;float:right;" onclick="Frame.goBack('${menuId}','/keke/resident/toMain');" title="<spring:message code="com.goBack"/>">
        <i class="iconfont">&#xe650;</i><spring:message code="com.goBack"/>
    </a>
</div>

<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <form class="layui-form layui-form-pane" lay-filter="modForm" action="${ctx}/keke/resident/update">
                <input type="hidden" name="id" value="${resident.id}"/>
                <div class="layui-card">
                    <div class="layui-card-header">修改业主信息</div>
                    <div class="layui-card-body">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">楼栋</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="building" lay-verify="required|number"  placeholder="请输入楼栋" value="${resident.building}" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">房号</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="houseNo" lay-verify="required|number"  placeholder="请输入房号" value="${resident.houseNo}" class="layui-input">
                                </div>
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">面积</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="area" lay-verify="required|number"  placeholder="请输入面积" value="${resident.area}" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">户主</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="houseHolder" lay-verify=""  placeholder="请输入户主名称" value="${resident.houseHolder}" class="layui-input">
                                </div>
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label">手机</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="phone" lay-verify=""  placeholder="请输入手机" value="${resident.phone}" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">身份证</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="idCardNo" lay-verify=""  placeholder="请输入身份证" value="${resident.idCardNo}" class="layui-input">
                                </div>
                            </div>
                        </div>

                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label">备注</label>
                            <textarea name="remarks" placeholder="请输入内容" class="layui-textarea">${resident.remarks}</textarea>
                        </div>

                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit="" lay-filter="modForm"><spring:message code="com.btn.save"/></button>
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

        form.render();

        form.on('submit(modForm)',function(data){
            $.ajax({
                url: data.form.action,
                data: data.field,
                dataType: 'json',
                success: function(result){
                    if(result.code == 0){
                        $('#query_${menuId}').click();
                        Frame.goBack('${menuId}','/keke/resident/toMain');
                    }
                    Frame.alert(result.msg);
                }
            })
            return false;
        });
        form.render();
    });

</script>