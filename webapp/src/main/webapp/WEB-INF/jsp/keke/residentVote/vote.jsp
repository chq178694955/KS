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
        <a><cite>我的投票</cite></a>
    </span>
</div>
<div class="layui-fluid">
    <div class="layui-row">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">
                    房号：${resident.houseNo}、户主：${resident.houseHolder}
                </div>
                <div class="layui-card-body">
                    <form id="voteForm_${menuId}" action="${ctx}/keke/residentVote/vote" class="layui-form">
                        <input type="hidden" name="residentId" value="${resident.id}" />
                        <input type="hidden" name="voteId" value="${vote.id}" />
                        <h2 style="text-align: center;">${vote.title}</h2>
                        <hr class="layui-bg-cyan">
                        <c:forEach items="${options}" var="option">
                            <fieldset class="layui-elem-field">
                                <legend>${option.name}<span style="color: #2D93CA">[${option.typeDesc}]</span></legend>
                                <div class="layui-field-box">
                                    <div class="layui-form-item">
                                        <div class="layui-input-block">
                                            <c:forEach items="${option.items}" var="item">
                                                <c:if test="${option.type == 0}">
                                                    <input type="radio" name="myOption_${option.id}" value="${item.id}" title="${item.name}" <c:if test="${item.checked == true}">checked</c:if> >
                                                </c:if>
                                                <c:if test="${option.type == 1}">
                                                    <input type="checkbox" name="myOption_${option.id}" value="${item.id}" title="${item.name}" <c:if test="${item.checked == true}">checked</c:if> >
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                    </div>
                                </div>
                            </fieldset>
                        </c:forEach>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit lay-filter="voteForm">投票</button>
                            </div>
                        </div>
                    </form>
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
        form.render();

        form.on('submit(voteForm)',function(data){
            console.log(data.elem);
            console.log(data.form);
            console.log(data.field);
            var names = new DataMap();
            $('#voteForm_${menuId}').find('input[name^="myOption_"]').each(function(){
                if(!names.containsKey($(this).attr('name'))){
                    names.put($(this).attr('name'),'');
                }
            });
            names.elements.forEach((o)=>{
                var itemIds = [];
                $('#voteForm_${menuId}').find('input[name="'+o.key+'"]:checked').each(function(){
                    itemIds.push($(this).val());
                });
                o.value = itemIds.join(",");
            })
            console.log(names);
            if(names.size() == 0){
                Frame.err('请选择投票信息');
                return ;
            }
            $.ajax({
                url: data.form.action,
                data: {
                    residentId: data.field.residentId,
                    voteId: data.field.voteId,
                    options: JSON.stringify(names.elements)
                },
                dataType: 'json',
                success: function(result){
                    if(result.code == 0){
                        $('#query_${menuId}').click();
                        Frame.closeAllLayer();
                    }
                    Frame.alert(result.msg);
                }
            })
            return false;
        });
    });
</script>