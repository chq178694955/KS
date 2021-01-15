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
        <a><cite>投票统计</cite></a>
    </span>
</div>
<div class="layui-fluid">
    <div class="layui-row">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">
                    <form action="" class="layui-form">
                        <div class="layui-form-item" style="padding-top: 2px;">
                            <div class="layui-inline">
                                <label class="layui-form-label">投票</label>
                                <div class="layui-input-inline">
                                    <select name="voteId" id="voteId_${menuId}" lay-search>
                                        <c:forEach var="vote" items="${voteInfoList}">
                                            <option value="${vote.id}">${vote.title}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="layui-inline">
                                <label class="layui-form-label">规则</label>
                                <div class="layui-input-inline">
                                    <select name="ruleId" id="ruleId_${menuId}" lay-search>
                                        <option value="0">票权统计</option>
                                        <option value="1">票权面积统计</option>
                                        <option value="2">票权从多统计</option>
                                        <option value="3">票权面积从多统计</option>
                                    </select>
                                </div>
                            </div>
                            <div class="layui-inline">
                                <button id="query_${menuId}" type="button" class="layui-btn layui-btn-normal" title="<spring:message code="com.btn.query"/>">
                                    <i class="layui-icon layui-icon-search"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="layui-card-body">
                    <fieldset class="layui-elem-field layui-field-title">
                        <legend id="staticTitle_${menuId}">统计结果</legend>
                        <div class="layui-field-box" id="statisResult_${menuId}">

                        </div>
                    </fieldset>
                </div>
            </div>
        </div>
    </div>
</div>

<style>
    .layui-field-box h2{
        color: #393D49;
        text-align: center;
        padding-bottom: 2px;
    }
    .statis-result-item{
        display: flex;
    }
    .statis-result-item span{
        display: inline-block;
        flex: 1;
        height: 32px;
        line-height: 32px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        text-align: center;
        padding: 0 3px;
    }
    .statis-result-item span:first-child{
        color: #01AAED;
        font-weight: bold;
    }
</style>

<script>
    layui.use(['element','table','form'],function(){
        var element = layui.element;
        var table = layui.table;
        var form = layui.form;

        element.render('breadcrumb');
        form.render();

        $('#query_${menuId}').bind('click',voteStatis);

        function voteStatis(){
            var ruleId = $('#ruleId_${menuId}').val();
            var voteId = $('#voteId_${menuId}').val();
            $.ajax({
                url: APP_ENV + '/keke/voteStatis/statis',
                data:{
                    ruleId: ruleId,
                    voteId: voteId
                },
                dataType:'json',
                success: function(res){
                    try{
                        $('#statisResult_${menuId}').empty();
                        $('#staticTitle_${menuId}').empty().append("【"+res.title+"】统计结果");
                        var $optionContext = $("<div>");
                        res.options.forEach((o,index)=>{
                            var $subTitle = $("<h2>").append((index + 1) + "、" + o.name);
                            $subTitle.appendTo($optionContext);
                            o.items.forEach(item=>{
                                var $itemContext = $("<div>").addClass("statis-result-item");
                                //name部分放在最前面特殊处理
                                var $value2 = $("<span>").append(item.name);
                                $value2.appendTo($itemContext);

                                for(let p in item){
                                    if(p == 'name')continue;//跳过，上面已特殊处理
                                    let dispKey = '';
                                    if(p == 'ticket'){
                                        dispKey = '票权数：';
                                    }else if(p == 'rate'){
                                        dispKey = '票权比例：';
                                    }else if(p == 'area'){
                                        dispKey = '面积比例：';
                                    }
                                    //var $key = $("<span>").append(dispKey);
                                    var $value = $("<span>").append(dispKey + item[p]);
                                    //$key.appendTo($itemContext);
                                    $value.appendTo($itemContext);
                                }
                                $itemContext.appendTo($optionContext);
                                $("<hr>").addClass('layui-bg-cyan').appendTo($optionContext);
                            });
                        });
                        $optionContext.appendTo($('#statisResult_${menuId}'));
                    }catch(err){
                        console.log(err);
                        Frame.err('统计异常');
                    }
                }
            });
        }
    });
</script>