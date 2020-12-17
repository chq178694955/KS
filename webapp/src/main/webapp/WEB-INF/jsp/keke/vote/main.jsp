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
        <a><cite>投票管理</cite></a>
    </span>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">
                    <form class="layui-form" lay-filter="searchForm_${menuId}">
                        <div class="layui-form-item">
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
<%--                                <button id="excel_${menuId}" type="button" class="layui-btn" title="<spring:message code="com.btn.excel"/>">--%>
<%--                                    <i class="iconfont layui-icon-excel"></i>--%>
<%--                                </button>--%>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="layui-card-body ">
                    <table class="layui-table" lay-filter="voteInfoList_${menuId}" lay-data="{
                        url:'${ctx}/keke/vote/findInfo',
                        id:'voteInfoList_${menuId}',
                        page:true,
                        }">
                        <thead>
                        <tr>
                            <th lay-data="{type:'radio'}"></th>
<%--                            <th lay-data="{checkbox:true}"></th>--%>
<%--                            <th lay-data="{field:'id',width:100,align:'center',hidden:true}">ID</th>--%>
                            <th lay-data="{field:'title',width:450,align:'left'}">标题</th>
                            <th lay-data="{field:'createTimeDesc',width:160,align:'center'}">创建时间</th>
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

                    <script type="text/html" id="operBar2_${menuId}">
                        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del" title="<spring:message code="com.btn.del"/>">
                            <i class="layui-icon layui-icon-delete"></i>
                            <cite><spring:message code="com.btn.del"/></cite>
                        </a>
                    </script>

                    <div class="layui-row">
                        <div class="layui-col-md6" style="padding: 0 3px 0 0;">
                            <fieldset class="layui-elem-field" style="height: 450px;">
                                <legend>议题</legend>
                                <div class="layui-field-box">
                                    <form class="layui-form" lay-filter="voteOptionForm_${menuId}">
                                        <div class="layui-form-item">
                                            <div class="layui-inline">
                                                <div class="layui-input-inline" style="width: 130px;">
                                                    <input type="text" class="layui-input" placeholder="请输入一个议题"/>
                                                </div>
                                            </div>
                                            <div class="layui-inline">
                                                <div class="layui-input-inline" style="width: 90px;">
                                                    <select class="layui-input-xs" lay-filter="optionType_${menuId}">
                                                        <option value="0">单选</option>
                                                        <option value="1">多选</option>
                                                    </select>
                                                </div>
                                            </div>
                                            <div class="layui-inline">
                                                <a id="addOption_${menuId}" class="layui-btn layui-btn-warm" title="<spring:message code="com.btn.add"/>">
                                                    <i class="layui-icon layui-icon-add-1"></i>
                                                    <cite><spring:message code="com.btn.add"/></cite>
                                                </a>
                                            </div>
                                        </div>
                                    </form>
                                    <table class="layui-table" id="voteOptionList_${menuId}" lay-filter="voteOptionList_${menuId}">
                                        <thead>
                                        <tr>
                                            <th lay-data="{type:'radio'}"></th>
                                            <th lay-data="{field:'name',width:160,align:'left'}">议题</th>
                                            <th lay-data="{field:'oper',fixed:'right',width:180,align:'center',toolbar:'#operBar2_${menuId}'}"><spring:message code="com.btn.oper"/></th>
                                        </thead>
                                    </table>
                                </div>
                            </fieldset>
                        </div>
                        <div class="layui-col-md6" style="padding: 0 0 0 3px;">
                            <fieldset class="layui-elem-field" style="height: 450px;">
                                <legend>议题选项</legend>
                                <div class="layui-field-box">
                                    <form class="layui-form" lay-filter="voteOptionItemForm_${menuId}">
                                        <div class="layui-form-item">
                                            <div class="layui-inline">
                                                <div class="layui-input-inline" style="width: 150px;">
                                                    <input type="text" class="layui-input" placeholder="请输入一个选项"/>
                                                </div>
                                            </div>
                                            <div class="layui-inline">
                                                <a id="addOptionItem_${menuId}" class="layui-btn layui-btn-warm" title="<spring:message code="com.btn.add"/>">
                                                    <i class="layui-icon layui-icon-add-1"></i>
                                                    <cite><spring:message code="com.btn.add"/></cite>
                                                </a>
                                            </div>
                                        </div>
                                    </form>
                                    <table class="layui-table" id="voteOptionItemList_${menuId}" lay-filter="voteOptionItemList_${menuId}">
                                        <thead>
                                        <tr>
                                            <th lay-data="{field:'name',width:160,align:'left'}">选项</th>
                                            <th lay-data="{field:'oper',fixed:'right',width:180,align:'center',toolbar:'#operBar2_${menuId}'}"><spring:message code="com.btn.oper"/></th>
                                        </thead>
                                    </table>
                                </div>
                            </fieldset>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    layui.use(['element','table','form'],function(){
        var element = layui.element;
        var table = layui.table;
        var form = layui.form;

        element.render('breadcrumb');
        form.render('select', 'optionType_${menuId}');

        table.render({
            elem: 'voteInfoList_${menuId}',
            url: '${ctx}/keke/vote/findInfo',
            done:function(res,cur,count){
                $(".layui-table-view[lay-id='voteInfoList_${menuId}'] .layui-table-body tr[data-index = '0' ] .layui-form-radio").click();
            },
            page: true
        });

        table.render({
            elem: 'voteOptionList_${menuId}',
            url: '${ctx}/keke/vote/findOption',
            done:function(res,cur,count){
                $(".layui-table-view[lay-id='voteOptionList_${menuId}'] .layui-table-body tr[data-index = '0' ] .layui-form-radio").click();
            },
            data:[],
            limit:Number.MAX_VALUE
        });

        table.render({
            elem: 'voteOptionItemList_${menuId}',
            url: '${ctx}/keke/vote/findOptionItem',
            data:[],
            limit:Number.MAX_VALUE
        })


        $('#query_${menuId}').bind('click',loadVoteInfo);
        function loadVoteInfo(){
            table.reload('voteInfoList_${menuId}',{
                where:{
                    searchKey: $('#searchKey_${menuId}').val()
                },
                page:{
                    curr:1
                }
            })
        }

        /**
         * 监听表格中radio事件
         */
        table.on('radio(voteInfoList_${menuId})', function(obj){
            loadOptions(obj.data.id);
        });
        function loadOptions(voteId){
            $.ajax({
                url: APP_ENV + '/keke/vote/findOption',
                data:{voteId: voteId},
                dataType:'json',
                success:function (options) {
                    if(options && options.length > 0){
                        table.reload('voteOptionList_${menuId}',{data:options});
                    }else{
                        table.reload('voteOptionList_${menuId}',{data:[]});
                    }
                }
            });
        }
        /**
         * 监听表格中radio事件
         */
        table.on('radio(voteOptionList_${menuId})', function(obj){
            loadOptionItems(obj.data.id);
        });
        function loadOptionItems(voteOptionId){
            $.ajax({
                url: APP_ENV + '/keke/vote/findOptionItem',
                data:{voteOptionId: voteOptionId},
                dataType:'json',
                success:function (items) {
                    if(items && items.length > 0){
                        table.reload('voteOptionItemList_${menuId}',{data:items});
                    }else{
                        table.reload('voteOptionItemList_${menuId}',{data:[]});
                    }
                }
            });
        }

        $('#add_${menuId}').bind('click',toAdd);
        function toAdd(){
            Frame.modMainTab({
                id:'${menuId}',
                url:APP_ENV + '/keke/vote/toAdd',
                params:{
                    menuId:'${menuId}'
                }
            });
        }

        $('#addOption_${menuId}').bind('click',addOption);
        function addOption(){
            var checkStatus = table.checkStatus('voteInfoList_${menuId}');
        }

        table.on('tool(voteInfoList_${menuId})',function(obj){
            var data = obj.data;
            data.menuId = '${menuId}';
            switch(obj.event){
                case 'del':
                    Frame.confirm('确定删除该户吗？',function(){
                        $.ajax({
                            url: APP_ENV + '/keke/resident/delete',
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
                case 'edit':
                    Frame.modMainTab({
                        id:'${menuId}',
                        url:APP_ENV + '/keke/resident/toUpdate',
                        params:{
                            menuId:'${menuId}',
                            id: data.id
                        }
                    });

                    //Frame.loadPage('${menuId}','game/vote/toVoting?menuId=${menuId}',data,WebUtils.getMessage('com.btn.modify'),400);
                    break;
            };
        });
    });
</script>