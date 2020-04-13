<%--
  Created by IntelliJ IDEA.
  User: 17869
  Date: 2020/4/11
  Time: 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<div class="x-nav">
    <span class="layui-breadcrumb">
        <a href="javascript:;"><spring:message code="sys.menu.sysMgr"/></a>
        <a><cite><spring:message code="sys.menu.dictMgr"/></cite></a>
    </span>
</div>

<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body">
                    <form class="layui-form" action="${ctx}/sys/dict/update">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label"><spring:message code="sys.dict.field.parentSn"/></label>
                                <div class="layui-input-inline">
                                    <select id="parentSn" name="parentSn"></select>
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label"><spring:message code="sys.dict.field.sn"/></label>
                                <div class="layui-input-inline">
                                    <input type="text" name="dictSn" class="layui-input" lay-verify="required|number" lay-verType="tips" value="${dict.dictSn}" disabled>
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label"><spring:message code="sys.dict.field.classNo"/></label>
                                <div class="layui-input-inline">
                                    <input type="text" name="classNo" class="layui-input" lay-verify="required|number" lay-verType="tips" value="${dict.classNo}">
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label"><spring:message code="sys.dict.field.dictNo"/></label>
                                <div class="layui-input-inline">
                                    <input type="text" name="dictNo" class="layui-input" lay-verify="required|number" lay-verType="tips" value="${dict.dictNo}">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label"><spring:message code="sys.dict.field.dictDesc"/></label>
                                <div class="layui-input-inline">
                                    <input type="text" name="dictDesc" class="layui-input" lay-verify="required" lay-verType="tips" value="${dict.dictDesc}">
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label"><spring:message code="sys.dict.field.dispOrder"/></label>
                                <div class="layui-input-inline">
                                    <input type="text" name="dispOrder" class="layui-input" lay-verify="required|number" lay-verType="tips" value="${dict.dispOrder}">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label"><spring:message code="sys.dict.field.useFlag"/></label>
                                <div class="layui-input-inline">
                                    <c:if test="${dict.useFlag == '0'}">
                                        <input type="radio" lay-filter="useFlagFilter" name="useFlag" value="0" title="<spring:message code="sys.dict.field.useFlag.disable"/>" checked>
                                        <input type="radio" lay-filter="useFlagFilter" name="useFlag" value="1" title="<spring:message code="sys.dict.field.useFlag.enable"/>" >
                                    </c:if>
                                    <c:if test="${dict.useFlag == '1'}">
                                        <input type="radio" lay-filter="useFlagFilter" name="useFlag" value="0" title="<spring:message code="sys.dict.field.useFlag.disable"/>" >
                                        <input type="radio" lay-filter="useFlagFilter" name="useFlag" value="1" title="<spring:message code="sys.dict.field.useFlag.enable"/>" checked>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit="" lay-filter="updateForm"><spring:message code="com.btn.modify"/></button>
                                <button class="layui-btn layui-btn-primary" type="reset"><spring:message code="com.btn.reset"/></button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    layui.use(['element','form'],function(){
        var element = layui.element;
        var form = layui.form;
        element.render('breadcrumb');

        loadDictTypes(function(){
            form.render('select');
        });

        form.on('radio(useFlagFilter)',function(data){
            console.log(data.elem);
            //Frame.err(data.value);
        });
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
                        layer.close(Frame.Layer.layerIndex);
                    }
                    Frame.alert(result.msg);
                }
            })
            return false;
        });
        form.render();
    });

    function loadDictTypes(callback){
        $.ajax({
            url: APP_ENV + '/sys/dict/loadDictTypes',
            data:{
                flag:"2"
            },
            dataType: 'json',
            success: function(dictTypes){
                if(dictTypes){
                    var classNo = '${dict.classNo}';
                    for(var i=0;i<dictTypes.length;i++){
                        var dict = dictTypes[i];
                        if(classNo == dict.classNo){
                            $('#parentSn').append('<option value="'+dict.classNo+'" selected>'+dict.dictDesc+'</option>')
                        }else{
                            $('#parentSn').append('<option value="'+dict.classNo+'">'+dict.dictDesc+'</option>')
                        }
                    }
                }
                if(typeof callback == 'function'){
                    callback();
                }
            }
        })
    }
</script>