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
        <a><cite>居民管理</cite></a>
    </span>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">
                    <form class="layui-form" lay-filter="searchFormResident" lay-filter="searchFormResident">
                        <div class="layui-form-item">
                            <label class="layui-form-label">查找方式</label>
                            <div class="layui-inline">
                                <div class="layui-input-inline">
                                    <select id="searchType_${menuId}" name="searchType">
                                        <option value="0">户主名</option>
                                        <option value="1">手机号</option>
                                        <option value="2">房号</option>
                                        <option value="3">车牌号</option>
                                        <option value="4">身份证号</option>
                                    </select>
                                </div>
                            </div>
                            <div class="layui-inline">
                                <div class="layui-input-inline">
                                    <input type="text" id="searchKey_${menuId}" name="searchKey" class="layui-input" placeholder="请输入你要查找的内容">
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
                            <th lay-data="{field:'id',width:100,align:'center',hidden:true}">ID</th>
                            <th lay-data="{field:'houseHolder',width:150,align:'center'}">户主名</th>
                            <th lay-data="{field:'phone',width:100,align:'center'}">联系方式</th>
                            <th lay-data="{field:'carNo',width:200,align:'center'}">车牌</th>
                            <th lay-data="{field:'building',width:200,align:'center'}">楼栋</th>
                            <th lay-data="{field:'roomNo',width:200,align:'center'}">房号</th>
                            <th lay-data="{field:'idCardNo',width:200,align:'center'}">身份证</th>
                            <th lay-data="{field:'area',width:200,align:'center'}">建筑面积</th>
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
    layui.use(['element','table','form'],function(){
        var element = layui.element;
        var talbe = layui.table;
        var form = layui.form;

        form.render('select');

        element.render('breadcrumb');
        table.init();
    });
</script>