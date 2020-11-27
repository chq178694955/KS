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
        <a><cite>系统指标</cite></a>
    </span>
    <a class="layui-btn layui-btn-sm layui-btn-primary" style="line-height:2.5em;margin-top:4px;float:right;" onclick="goBack()" title="<spring:message code="com.goBack"/>">
        <i class="iconfont">&#xe650;</i>
    </a>
</div>

<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <form class="layui-form layui-form-pane" lay-filter="addForm" action="${ctx}/em/indexMgr/addTemplate">
                <div class="layui-card">
                    <div class="layui-card-header">新增系统指标</div>
                    <div class="layui-card-body">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label" style="width: 170px;">指标名称</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="name" lay-verify="required" lay-verType="tips" placeholder="请输入指标名称" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label" style="width: 170px;">指标单位</label>
                                <div class="layui-input-inline">
                                    <input name="unit" lay-verify="required" lay-verType="tips" placeholder="请输入指标单位" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label" style="width: 170px;">最大值</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="maxVal" lay-verify="required|number" lay-verType="tips" placeholder="请输入指标最大值" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label" style="width: 170px;">最小值</label>
                                <div class="layui-input-inline">
                                    <input name="minVal" lay-verify="required|number" lay-verType="tips" placeholder="请输入指标最小值" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label" style="width: 170px;">最优指标</label>
                                <div class="layui-input-inline">
                                    <input type="text" name="bestVal" lay-verify="required|number" lay-verType="tips" placeholder="请输入最优指标" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label" style="width: 170px;">指标权重</label>
                                <div class="layui-input-inline">
                                    <input name="weight" lay-verify="required|number" lay-verType="tips" placeholder="请输入指标权重" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label" style="width: 170px;">所属分类</label>
                                <div class="layui-input-inline">
                                    <select id="categoryId_${menuId}" name="categoryId" lay-verify="">

                                    </select>
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label" style="width: 170px;">归一化公式</label>
                                <div class="layui-input-inline">
                                    <select type="text" name="formulaId">
                                        <c:forEach var="formula" items="${furmulas}">
                                            <option value="${formula.id}">${formula.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit="" lay-filter="addForm"><spring:message code="com.btn.save"/></button>
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

        form.on('submit(addForm)',function(data){
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

        loadCategories();
        function loadCategories(callback){
            $.ajax({
                url: APP_ENV + '/em/indexMgr/listCategory',
                dataType: 'json',
                success: function(categories){
                    if(categories){
                        $('#categoryId_${menuId}').empty();
                        var strSelected = '';
                        for(var i=0;i<categories.length;i++){
                            var cate = categories[i];
                            if(i == 0)strSelected = 'selected';
                            $('#categoryId_${menuId}').append('<option value="'+cate.id+'" '+strSelected+'>'+cate.name+'</option>');
                            strSelected = '';
                        }
                        form.render('select','addForm');
                    }
                    if(typeof callback == 'function'){
                        callback();
                    }
                }
            })
        }
    });

    function goBack(){
        Frame.modMainTab({
            id:'${menuId}',
            url:APP_ENV + '/em/indexMgr/toMain',
            params:{
                menuId:'${menuId}'
            }
        });
    }
</script>