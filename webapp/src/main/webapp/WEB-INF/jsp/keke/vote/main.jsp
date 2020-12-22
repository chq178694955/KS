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
<%--                        <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="edit" title="<spring:message code="com.btn.edit"/>">--%>
<%--                            <i class="layui-icon layui-icon-edit"></i>--%>
<%--                            <cite><spring:message code="com.btn.edit"/></cite>--%>
<%--                        </a>--%>
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
                </div>
            </div>
        </div>
    </div>

    <div class="layui-row layui-col-space15">
        <div class="layui-col-md7">
            <div class="layui-card">
                <div class="layui-card-header">议题</div>
                <div class="layui-card-body">
                    <form class="layui-form" lay-filter="voteOptionForm_${menuId}">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <div class="layui-input-inline" style="width: 130px;">
                                    <input type="text" id="optionName_${menuId}" class="layui-input" placeholder="请输入一个议题"/>
                                </div>
                            </div>
                            <div class="layui-inline">
                                <div class="layui-input-inline" style="width: 90px;">
                                    <select class="layui-input-xs" id="optionType_${menuId}" lay-filter="optionType_${menuId}">
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
                            <th lay-data="{field:'name',width:300,align:'left'}">议题</th>
                            <th lay-data="{field:'typeDesc',width:80,align:'center'}">类型</th>
                            <th lay-data="{field:'oper',fixed:'right',width:90,align:'center',toolbar:'#operBar2_${menuId}'}"><spring:message code="com.btn.oper"/></th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
        <div class="layui-col-md5">
            <div class="layui-card">
                <div class="layui-card-header">议题选项</div>
                <div class="layui-card-body">
                    <form class="layui-form" lay-filter="voteOptionItemForm_${menuId}">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <div class="layui-input-inline" style="width: 150px;">
                                    <input type="text" id="optionItemName_${menuId}" class="layui-input" placeholder="请输入一个选项"/>
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
                            <th lay-data="{field:'name',width:220,align:'left'}">选项</th>
                            <th lay-data="{field:'oper',fixed:'right',width:90,align:'center',toolbar:'#operBar2_${menuId}'}"><spring:message code="com.btn.oper"/></th>
                        </tr>
                        </thead>
                    </table>
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

        /**
         * 监听表格中radio事件
         */
        table.on('radio(voteInfoList_${menuId})', function(obj){
            loadOptions(obj.data.id);
        });
        function loadOptions(voteId){
            table.reload('voteOptionList_${menuId}',{
                url:APP_ENV + '/keke/vote/findOption',
                where:{voteId:voteId},
                page:{curr:1}
            })
        }

        /**
         * 监听表格中radio事件
         */
        table.on('radio(voteOptionList_${menuId})', function(obj){
            loadOptionItems(obj.data.id);
        });
        function loadOptionItems(voteOptionId){
            table.reload('voteOptionItemList_${menuId}',{
                url:APP_ENV + '/keke/vote/findOptionItem',
                where:{
                    voteOptionId:voteOptionId
                },
                page:{curr:1}
            });
        }

        table.init('voteInfoList_${menuId}',{
            //elem: '#voteInfoList_${menuId}',
            id: 'voteInfoList_${menuId}',
            url: '${ctx}/keke/vote/findInfo',
            done:function(res,cur,count){
                if(res && res.data.length > 0){
                    $(".layui-table-view[lay-id='voteInfoList_${menuId}'] .layui-table-body tr[data-index = '0' ] .layui-form-radio").click();
                }
            },
            page: true
        });

        table.init('voteOptionList_${menuId}',{
            //elem:'#voteOptionList_${menuId}',
            id:'voteOptionList_${menuId}',
            done:function(res2,cur2,count2){
                if(res2 && res2.data.length > 0){
                    //loadOptionItems(res2.data[0].id);
                    $(".layui-table-view[lay-id='voteOptionList_${menuId}'] .layui-table-body tr[data-index = '0' ] .layui-form-radio").click();
                }
            },
            height:300,
            limit:Number.MAX_VALUE
        });

        table.init('voteOptionItemList_${menuId}',{
            //elem:'#voteOptionList_${menuId}',
            id:'voteOptionItemList_${menuId}',
            height:300,
            limit:Number.MAX_VALUE
        });

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

        $('#add_${menuId}').bind('click',toAdd);
        function toAdd(){
            Frame.loadPage('${menuId}','keke/vote/toAddVote?menuId=${menuId}',{},WebUtils.getMessage('com.btn.add'),400);
        }

        $('#addOption_${menuId}').bind('click',addOption);
        function addOption(){
            var checkStatus = table.checkStatus('voteInfoList_${menuId}');
            console.log(checkStatus)
            if(checkStatus.data.length == 0){
                Frame.err('请选择投票');
                return ;
            }
            var optionName = $('#optionName_${menuId}').val();
            var optionType = $('#optionType_${menuId}').val();
            if(optionName == ''){
                Frame.err('请输入议题');
                return ;
            }
            $.ajax({
                url: APP_ENV + '/keke/vote/addOption',
                data:{
                    voteId:checkStatus.data[0].id,
                    optionName: optionName,
                    optionType: optionType
                },
                dataType:'json',
                success: function(res){
                    //成功重新加载list，失败给出提示
                    if(res.code == 0){
                        table.reload('voteOptionList_${menuId}',{
                            data:res.data,
                        })
                        Frame.info(res.msg);
                    }else{
                        Frame.info(res.msg,2);
                    }
                }
            })
        }

        $('#addOptionItem_${menuId}').bind('click',addOptionItem);
        function addOptionItem(){
            var checkStatus = table.checkStatus('voteOptionList_${menuId}');
            if(checkStatus.data.length == 0){
                Frame.err('请选择议题');
                return ;
            }
            var itemName = $('#optionItemName_${menuId}').val();
            if(itemName == ''){
                Frame.err('请输入议题选项');
                return ;
            }
            $.ajax({
                url: APP_ENV + '/keke/vote/addOptionItem',
                data:{
                    voteOptionId:checkStatus.data[0].id,
                    itemName: itemName
                },
                dataType:'json',
                success: function(res){
                    //成功重新加载list，失败给出提示
                    if(res.code == 0){
                        table.reload('voteOptionItemList_${menuId}',{
                            data:res.data,
                        })
                        Frame.info(res.msg);
                    }else{
                        Frame.info(res.msg,2);
                    }
                }
            })
        }

        table.on('tool(voteInfoList_${menuId})',function(obj){
            var data = obj.data;
            data.menuId = '${menuId}';
            switch(obj.event){
                case 'del':
                    Frame.confirm('确定删除该投票吗？',function(){
                        $.ajax({
                            url: APP_ENV + '/keke/vote/delete',
                            data:{voteId: data.id},
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
                <%--Frame.modMainTab({--%>
                <%--    id:'${menuId}',--%>
                <%--    url:APP_ENV + '/keke/resident/toUpdate',--%>
                <%--    params:{--%>
                <%--        menuId:'${menuId}',--%>
                <%--        id: data.id--%>
                <%--    }--%>
                <%--});--%>

                    //Frame.loadPage('${menuId}','game/vote/toVoting?menuId=${menuId}',data,WebUtils.getMessage('com.btn.modify'),400);
                    break;
            };
        });

        table.on('tool(voteOptionList_${menuId})',function(obj){
            var data = obj.data;
            data.menuId = '${menuId}';
            switch(obj.event){
                case 'del':
                    Frame.confirm('确定删除该议题吗？',function(){
                        $.ajax({
                            url: APP_ENV + '/keke/vote/deleteOption',
                            data:{id: data.id},
                            dataType:'json',
                            success:function (res) {
                                //成功重新加载list，失败给出提示
                                if(res.code == 0){
                                    table.reload('voteOptionList_${menuId}',{
                                        data:res.data,
                                    })
                                    Frame.info(res.msg);
                                }else{
                                    Frame.info(res.msg,2);
                                }
                            }
                        });
                    });
                    break;
            };
        });

        table.on('tool(voteOptionItemList_${menuId})',function(obj){
            var data = obj.data;
            data.menuId = '${menuId}';
            switch(obj.event){
                case 'del':
                    Frame.confirm('确定删除该选项吗？',function(){
                        $.ajax({
                            url: APP_ENV + '/keke/vote/deleteItem',
                            data:{id: data.id},
                            dataType:'json',
                            success:function (res) {
                                //成功重新加载list，失败给出提示
                                if(res.code == 0){
                                    table.reload('voteOptionItemList_${menuId}',{
                                        data:res.data,
                                    })
                                    Frame.info(res.msg);
                                }else{
                                    Frame.info(res.msg,2);
                                }
                            }
                        });
                    });
                    break;
            };
        });
    });
</script>