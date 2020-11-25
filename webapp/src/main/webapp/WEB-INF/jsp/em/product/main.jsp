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
        <a href="javascript:;">伺服电机</a>
        <a><cite>实验数据</cite></a>
    </span>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">
                    <form class="layui-form" lay-filter="searchForm">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label"><spring:message code="vote.field.title"/></label>
                                <div class="layui-input-inline">
                                    <input id="searchKey_${menuId}" type="text" name="searchKey"  placeholder="请输入试验项目名称" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <button id="query_${menuId}" type="button" class="layui-btn layui-btn-normal" title="<spring:message code="com.btn.query"/>">
                                    <i class="layui-icon layui-icon-search"></i>
                                </button>
                                <%--<button id="add_${menuId}" type="button" class="layui-btn layui-btn-warm" title="<spring:message code="com.btn.add"/>">--%>
                                    <%--<i class="layui-icon layui-icon-add-1"></i>--%>
                                <%--</button>--%>
                                <button id="excel_${menuId}" type="button" class="layui-btn" title="<spring:message code="com.btn.excel"/>">
                                    <i class="iconfont layui-icon-excel"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="layui-card-body ">
                    <table class="layui-table" lay-filter="dataList_${menuId}" lay-data="{
                        url:'${ctx}/em/product/find',
                        id:'dataList_${menuId}',
                        page:true,
                        }">
                        <thead>
                        <tr>
                            <th lay-data="{checkbox:true}"></th>
                            <th lay-data="{field:'id',width:75,align:'center'}">ID</th>
                            <th lay-data="{field:'name',width:250,align:'center'}">实验项目名</th>
                            <%--<th lay-data="{field:'creator',width:120,align:'center'}">创建人</th>--%>
                            <th lay-data="{field:'createTimeDesc',width:180,align:'center'}">创建时间</th>
                            <th lay-data="{field:'oper',fixed:'right',width:160,align:'center',toolbar:'#operBar_${menuId}'}"><spring:message code="com.btn.oper"/></th>
                        </thead>
                    </table>

                    <script type="text/html" id="operBar_${menuId}">
                        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="detail" title="查看实验数据">
                            <i class="layui-icon layui-icon-search"></i>
                            <cite>查看</cite>
                        </a>
                        <%--<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" title="<spring:message code="com.btn.del"/>">--%>
                            <%--<i class="layui-icon layui-icon-delete"></i>--%>
                            <%--<cite><spring:message code="com.btn.del"/></cite>--%>
                        <%--</a>--%>
                    </script>
                </div>
            </div>
        </div>
    </div>

    <div class="layui-row layui-col-space15">
        <div class="layui-col-md4">
            <div class="layui-card">
                <div class="layui-card-header">阶跃响应试验数据</div>
                <div class="layui-card-body">
                    <table class="layui-table" lay-filter="dataList1_${menuId}" lay-data="{
                        id:'dataList1_${menuId}',
                        page:true,
                        }">
                        <thead>
                        <tr>
                            <th lay-data="{field:'id',width:75,align:'center',hide:true}">ID</th>
                            <th lay-data="{field:'dataTime',width:150,align:'center'}">时间（ms）</th>
                            <th lay-data="{field:'speed',width:150,align:'center'}">转速（r/min）</th>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
        <div class="layui-col-md4">
            <div class="layui-card">
                <div class="layui-card-header">正弦响应试验数据</div>
                <div class="layui-card-body">
                    <table class="layui-table" lay-filter="dataList2_${menuId}" lay-data="{
                        id:'dataList2_${menuId}',
                        page:true,
                        }">
                        <thead>
                        <tr>
                            <th lay-data="{field:'id',width:75,align:'center',hide:true}">ID</th>
                            <th lay-data="{field:'speed10',width:150,align:'center'}">转速（r/min）</th>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
        <div class="layui-col-md4">
            <div class="layui-card">
                <div class="layui-card-header">负载扰动试验数据</div>
                <div class="layui-card-body">
                    <table class="layui-table" lay-filter="dataList3_${menuId}" lay-data="{
                        id:'dataList3_${menuId}',
                        page:true,
                        }">
                        <thead>
                        <tr>
                            <th lay-data="{field:'id',width:75,align:'center',hide:true}">ID</th>
                            <th lay-data="{field:'speedEmpty',width:150,align:'center'}">空载转速（r/min）</th>
                            <th lay-data="{field:'speedFixedLoad',width:180,align:'center'}">额定负载转速（r/min）</th>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <div class="layui-row layui-col-space15">
        <div class="layui-col-md4">
            <div class="layui-card">
                <div class="layui-card-header">空载试验数据</div>
                <div class="layui-card-body">
                    <table class="layui-table" lay-filter="dataList4_${menuId}" lay-data="{
                        id:'dataList4_${menuId}',
                        page:true,
                        }">
                        <thead>
                        <tr>
                            <th lay-data="{field:'id',width:75,align:'center',hide:true}">ID</th>
                            <th lay-data="{field:'speedForward',width:150,align:'center'}">正向转速（r/min）</th>
                            <th lay-data="{field:'speedReverse',width:150,align:'center'}">反向转速（r/min）</th>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
        <div class="layui-col-md4">
            <div class="layui-card">
                <div class="layui-card-header">调速范围测定试验数据</div>
                <div class="layui-card-body">
                    <table class="layui-table" lay-filter="dataList5_${menuId}" lay-data="{
                        id:'dataList5_${menuId}',
                        page:true,
                        }">
                        <thead>
                        <tr>
                            <th lay-data="{field:'id',width:75,align:'center',hide:true}">ID</th>
                            <th lay-data="{field:'speedMax',width:150,align:'center'}">最大转速（r/min）</th>
                            <th lay-data="{field:'speedMin',width:150,align:'center'}">最小转速（r/min）</th>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
        <div class="layui-col-md4">
            <div class="layui-card">
                <div class="layui-card-header">恒定负载扰动试验数据</div>
                <div class="layui-card-body">
                    <table class="layui-table" lay-filter="dataList6_${menuId}" lay-data="{
                        id:'dataList6_${menuId}',
                        page:true,
                        }">
                        <thead>
                        <tr>
                            <th lay-data="{field:'id',width:75,align:'center',hide:true}">ID</th>
                            <th lay-data="{field:'speed100',width:150,align:'center'}">转速（r/min）</th>
                            <th lay-data="{field:'torque100',width:150,align:'center'}">转矩（N·m）</th>
                            <th lay-data="{field:'speedSetter',width:150,align:'center'}">转速设定值（r/min）</th>
                            <th lay-data="{field:'torqueOverload',width:150,align:'center'}">负载转矩（T·m）</th>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    layui.use(['form','element','table','laydate'], function(){
        var form = layui.form;
        var element = layui.element;
        var table = layui.table;
        var laydate = layui.laydate;

        form.render('select','searchForm');

        table.init('dataList_${menuId}',{
            id: 'dataList_${menuId}',
            url: '${ctx}/em/product/find',
            page: true
        });

        element.render('breadcrumb');

        $('#query_${menuId}').bind('click',table,query);
        $('#add_${menuId}').bind('click',toAdd);
        $('#excel_${menuId}').bind('click',exportExcel);

        table.on('tool(dataList_${menuId})',function(obj){
            var data = obj.data;
            data.menuId = '${menuId}';
            switch(obj.event){
                case 'del':
                    Frame.confirm('确定删除该实验项目？',function(){
                        $.ajax({
                            url: APP_ENV + '/em/product/delete',
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
                case 'detail':
                    queryDetail(data.id);

                    //Frame.loadPage('${menuId}','game/vote/toVoting?menuId=${menuId}',data,WebUtils.getMessage('com.btn.modify'),400);
                    break;
            };
        });

        function queryDetail(productId){
            table.init('dataList1_${menuId}',{
                id: 'dataList1_${menuId}',
                url: '${ctx}/em/product/data/find1',
                where:{productId: productId},
                page:true
            });
            table.init('dataList2_${menuId}',{
                id: 'dataList2_${menuId}',
                url: '${ctx}/em/product/data/find2',
                where:{productId: productId},
                page:true
            });
            table.init('dataList3_${menuId}',{
                id: 'dataList3_${menuId}',
                url: '${ctx}/em/product/data/find3',
                where:{productId: productId},
                page:true
            });
            table.init('dataList4_${menuId}',{
                id: 'dataList4_${menuId}',
                url: '${ctx}/em/product/data/find4',
                where:{productId: productId},
                page:true
            });
            table.init('dataList5_${menuId}',{
                id: 'dataList5_${menuId}',
                url: '${ctx}/em/product/data/find5',
                where:{productId: productId},
                page:true
            });
            table.init('dataList6_${menuId}',{
                id: 'dataList6_${menuId}',
                url: '${ctx}/em/product/data/find6',
                where:{productId: productId},
                page:true
            });
        }
    });

    function query(event){
        var table = event.data;
        table.reload('dataList_${menuId}',{
            where:{
                searchKey: WebUtils.fmtStr($('#searchKey_${menuId}').val())
            },
            page:{
                curr:1
            }
        });
    }

    function toAdd(){
        Frame.modMainTab({
            id:'${menuId}',
            url:APP_ENV + '/game/vote/toAdd',
            params:{
                menuId:'${menuId}'
            }
        });
        //Frame.loadPage('${menuId}','game/vote/toAdd?menuId=${menuId}',{},WebUtils.getMessage('com.btn.add'),400);
    }

    function exportExcel(){
        var layId = 'dataList_${menuId}';
        Frame.exportExcel(layId,'${ctx}/em/product/find',{
            searchKey: WebUtils.fmtStr($('#searchKey_${menuId}').val())
        },'实验项目列表');
    }



</script>