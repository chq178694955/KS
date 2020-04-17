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
        <a href="javascript:;"><spring:message code="game.menu.gameMgr"/></a>
        <a><cite><spring:message code="game.menu.voteMgr"/></cite></a>
    </span>
    <a class="layui-btn layui-btn-sm layui-btn-primary" style="line-height:2.5em;margin-top:4px;float:right;" onclick="goBack()" title="<spring:message code="com.goBack"/>">
        <i class="iconfont">&#xe650;</i>
    </a>
</div>

<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body">
                    <blockquote class="layui-elem-quote"><spring:message code="vote.tip.createVoteThemeTip"/></blockquote>
                </div>
            </div>

            <div class="layui-form layui-form-pane" lay-filter="voteForm">
                <div class="layui-card">
                    <div class="layui-card-header"><spring:message code="vote.tip.voteTheme"/></div>
                    <div class="layui-card-body">
                        <div class="layui-form-item">
                            <label class="layui-form-label"><spring:message code="vote.field.title"/></label>
                            <div class="layui-input-block">
                                <input type="text" name="title" required  lay-verify="required" placeholder="<spring:message code="vote.title.tip"/>" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item layui-form-text">
                            <label class="layui-form-label"><spring:message code="vote.field.subTitle"/></label>
                            <div class="layui-input-block">
                                <textarea name="subTitle" placeholder="<spring:message code="vote.subTitle.tip"/>" class="layui-textarea"></textarea>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label"><spring:message code="com.time"/></label>
                            <div class="layui-input-inline">
                                <input id="timeRange_${menuId}" type="text" name="timeRange" lay-verify="required"  placeholder="" autocomplete="off" class="layui-input">
                            </div>
                            <label class="layui-form-label"><spring:message code="vote.field.status"/></label>

                            <div class="layui-input-inline">
                                <select name="status" id="status_${menuId}">
                                    <option value="0" selected><spring:message code="vote.field.status.ready"/></option>
                                    <option value="1"><spring:message code="vote.field.status.start"/></option>
                                    <option value="2"><spring:message code="vote.field.status.pause"/></option>
                                    <option value="3"><spring:message code="vote.field.status.finish"/></option>
                                    <option value="4"><spring:message code="vote.field.status.discard"/></option>
                                </select>
                            </div>
                        </div>

                    </div>
                </div>

                <div class="layui-card">
                    <div class="layui-card-header"><spring:message code="vote.tip.voteContent"/></div>
                    <div class="layui-card-body">
                        <div class="layui-form-item">
                            <label class="layui-form-label"><spring:message code="vote.tip.voteMode"/></label>
                            <div class="layui-input-inline">
                                <input type="radio" name="optionMode" value="0" title="<spring:message code="vote.itemGroup.field.type.single"/>" checked>
                                <input type="radio" name="optionMode" value="1" title="<spring:message code="vote.itemGroup.field.type.multiple"/>">
                            </div>
                            <div class="layui-input-inline">
                                <input type="text" id="groupName_${menuId}" name="groupName" placeholder="<spring:message code="vote.itemGroup.name.tip"/>" class="layui-input"/>
                            </div>
                            <div class="layui-input-inline">
                                <button type="button" class="layui-btn layui-btn-normal" onclick="addGroup(this)">
                                    <i class="layui-icon layui-icon-add-1"></i><spring:message code="com.btn.add"/>
                                </button>
                            </div>
                        </div>

                        <%--<hr class="layui-bg-blue">--%>

                        <%--<div class="layui-form-item" vote-group="voteGroup_0">--%>
                            <%--<label class="layui-form-label">投票分组1：</label>--%>
                            <%--<div class="layui-form-mid layui-word-aux" style="margin-left: 15px;">物业满意度调查？</div>--%>
                            <%--<button class="layui-btn layui-btn-danger" title="删除分组">--%>
                                <%--<i class="layui-icon layui-icon-delete"></i>--%>
                            <%--</button>--%>
                        <%--</div>--%>
                        <%--<div class="layui-form-item" vote-item="voteItem_0">--%>
                            <%--<label class="layui-form-label">选项1：</label>--%>
                            <%--<div class="layui-input-inline">--%>
                                <%--<input type="text" class="layui-input">--%>
                            <%--</div>--%>
                            <%--<button class="layui-btn layui-btn-normal" title="添加项">--%>
                                <%--<i class="layui-icon layui-icon-add-1"></i>--%>
                            <%--</button>--%>
                            <%--<button class="layui-btn layui-btn-normal" title="移除项">--%>
                                <%--<i class="layui-icon layui-icon-delete"></i>--%>
                            <%--</button>--%>
                        <%--</div>--%>
                        <%--<div class="layui-form-item" vote-item="voteItem_0">--%>
                            <%--<label class="layui-form-label">选项2：</label>--%>
                            <%--<div class="layui-input-inline">--%>
                                <%--<input type="text" class="layui-input">--%>
                            <%--</div>--%>
                            <%--<button class="layui-btn layui-btn-normal" title="添加项">--%>
                                <%--<i class="layui-icon layui-icon-add-1"></i>--%>
                            <%--</button>--%>
                            <%--<button class="layui-btn layui-btn-normal" title="移除项">--%>
                                <%--<i class="layui-icon layui-icon-delete"></i>--%>
                            <%--</button>--%>
                        <%--</div>--%>
                    </div>
                </div>

                <div class="layui-card">
                    <div class="layui-card-body">
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit lay-filter="addVoteForm"><spring:message code="vote.btn.launch"/></button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    /**
     * 投票对象定义
     * @type {{groupName: string, optionMode: number}}
     */
    var VoteObject = {

        optionMode: 0,//0-单选，1-多选，默认0

        groupIndex: 0,//默认分组索引

        cacheGroupKey: new DataMap(),//缓存投票组的key

        sort: function(){
            var keys = this.cacheGroupKey.keys();
            for(var i=0;i<keys.length-1;i++){
                for(var j=0;j<keys.length-1;j++){
                    if(keys[j] > keys[j+1]){
                        var temp = keys[j];
                        keys[j] = keys[j+1];
                        keys[j+1] = temp;
                    }
                }
            }
            this.cacheGroupKey.clear();
            for(var i=0;i<keys.length;i++){
                this.cacheGroupKey.put(keys[i],'');
            }
        },

        getMaxKey: function(){
            this.sort();

            var keys = this.cacheGroupKey.keys();
            var maxKey = this.groupIndex;
            for(var i=0;i<keys.length;i++){
                if(keys[i] > maxKey){
                    maxKey = keys[i];
                }
            }
            return maxKey + 1;
        }

    };

    var form,element,laydate;
    layui.use(['element','laydate','form'],function(){
        element = layui.element;
        laydate = layui.laydate;
        form = layui.form;

        element.render('breadcrumb');
        laydate.render({
            elem: '#timeRange_${menuId}',
            range: '~',
            trigger:'click',
            min: 0
        });

        form.render();

        form.on('submit(addVoteForm)',function(data){
            var params = {
                title: data.field.title,
                subTitle: data.field.subTitle,
                timeRange: data.field.timeRange,
                status: data.field.status
            };

            var groups = [];
            $("div[vote-group*='voteGroup_']").each(function(){
                var group = {};
                var voteGroup = $(this).attr("vote-group");
                var strs = voteGroup.split("_");
                var groupId = strs[1];
                var optionMode = $(this).find("input[name='optionMode_"+groupId+"']:eq(0)").val();
                var groupName = $(this).find("input[name='groupName_"+groupId+"']:eq(0)").val();
                group.name = groupName;
                group.type = optionMode;

                var items = [];
                $("div[vote-item*='voteItem_"+groupId+"']").each(function(){
                    var itemName = $(this).find("input[name='itemOption']:eq(0)").val();
                    if(itemName != ''){
                        items.push(itemName);
                    }
                });
                group.items = items;
                groups.push(group);
            });
            params.groups = groups;
            console.log(params)

            $.ajax({
                type: 'post',
                url: APP_ENV + '/game/vote/add',
                data: "voteInfo=" + encodeURIComponent(JSON.stringify(params)),
                dataType: 'json',
                beforeSend: function(){
                    Frame.load();
                },
                success: function (result) {
                    Frame.closeLayer();
                    if(result.code == 0){
                        goBack();
                    }
                    Frame.info(result.msg);
                }
            });
            return false;
        });
    });

    function goBack(){
        Frame.modMainTab({
            id:'${menuId}',
            url:APP_ENV + '/game/vote/toMain',
            params:{
                menuId:'${menuId}'
            }
        });
    }

    function addGroup(curObj){
        var parent = $(curObj).parent().parent().parent();
        var optionMode = parent.find("input[name='optionMode']:checked").val();//获取组类型 0-单选 1-多选
        var groupName = parent.find("input[name='groupName']").val();
        var groupId = VoteObject.getMaxKey();

        if(groupName == ''){
            Frame.tips(WebUtils.getMessage('vote.itemGroup.tip.nameEmpty'),'groupName_${menuId}');
            return ;
        }

        //分割线
        $("<hr>").addClass("layui-bg-blue").appendTo(parent);

        //组
        var group = $("<div>").addClass("layui-form-item").attr("vote-group","voteGroup_" + groupId);
        var label = $("<label>").addClass("layui-form-label").html(WebUtils.getMessage('vote.tip.voteGroup'));
        var groupDesc = $("<div>").addClass("layui-form-mid layui-word-aux").css("margin-left","15px").html(groupName);
        var groupNameInput = $("<input>").attr("name","groupName_" + groupId).attr("type","hidden").val(groupName);
        var optionModeInput = $("<input>").attr("name","optionMode_" + groupId).attr("type","hidden").val(optionMode);
        var delGroupBtn = $("<button>").addClass("layui-btn layui-btn-danger").attr("title",WebUtils.getMessage('vote.btn.label.delGroup')).html("<i class='layui-icon layui-icon-delete'></i>").bind('click',delGroup);

        label.appendTo(group);
        groupDesc.appendTo(group);
        groupNameInput.appendTo(group);
        optionModeInput.appendTo(group);
        delGroupBtn.appendTo(group);
        group.appendTo(parent)

        //默认添加一个选项
        var itemBlock = $("<div>").addClass("layui-form-item").attr("vote-item","voteItem_" + groupId);
        var itemLabel =$("<label>").addClass("layui-form-label").html(WebUtils.getMessage('vote.tip.voteOption'));
        var itemInline = $("<div>").addClass("layui-input-inline");
        var item = $("<input>").addClass("layui-input").attr("lay-verify","required").attr("name","itemOption");
        var addBtn = $("<button>").addClass("layui-btn layui-btn-normal").attr("title",WebUtils.getMessage('vote.btn.label.addItem')).html("<i class='layui-icon layui-icon-add-1'></i>").bind('click',addOption);
        itemLabel.appendTo(itemBlock);
        item.appendTo(itemInline);
        itemInline.appendTo(itemBlock);
        addBtn.appendTo(itemBlock);
        itemBlock.appendTo(parent)

        VoteObject.cacheGroupKey.put(groupId,'');
    }

    function delGroup(event){
        var parent = event.currentTarget.parentNode;//上级
        var pp = parent.parentNode;//上上级
        var voteGroup = $(parent).attr("vote-group");
        var strs = voteGroup.split("_");
        var index = strs[1];//获得下标
        //删除分割线
        $(parent).prev().remove();
        //删除项
        $(pp).find("div[vote-item='voteItem_"+index+"']").each(function(){
            $(this).remove();
        });
        $(parent).remove();//删除组

        VoteObject.cacheGroupKey.removeByKey(index);
    }

    function addOption(event){
        var curObj = event.currentTarget;
        var parent = curObj.parentNode;//上级节点
        var pp = parent.parentNode;//上上级节点
        var attrVoteItem = $(parent).attr('vote-item');
        var strs = attrVoteItem.split("_");
        var groupId = strs[1];

        //添加一个选项
        var itemBlock = $("<div>").addClass("layui-form-item").attr('vote-item',attrVoteItem);
        var itemLabel =$("<label>").addClass("layui-form-label").html(WebUtils.getMessage('vote.tip.voteOption'));
        var itemInline = $("<div>").addClass("layui-input-inline");
        var item = $("<input>").addClass("layui-input").attr("lay-verify","required").attr("name","itemOption");
        var addBtn = $("<button>").addClass("layui-btn layui-btn-normal").attr("title",WebUtils.getMessage('vote.btn.label.addItem')).html("<i class='layui-icon layui-icon-add-1'></i>").bind('click',addOption);
        var delBtn = $("<button>").addClass("layui-btn layui-btn-danger").attr("title",WebUtils.getMessage('vote.btn.label.delItem')).html("<i class='layui-icon layui-icon-delete'></i>").bind('click',delOption);
        itemLabel.appendTo(itemBlock);
        item.appendTo(itemInline);
        itemInline.appendTo(itemBlock);
        addBtn.appendTo(itemBlock);
        delBtn.appendTo(itemBlock);
        itemBlock.appendTo($(pp));

        //移除自身“添加按钮”
        $(curObj).remove();
    }

    function delOption(event){
        var curObj = event.currentTarget;
        var parent = curObj.parentNode;//上级节点
        var pp = parent.parentNode;//上上级节点
        var attrVoteItem = $(parent).attr('vote-item');
        var strs = attrVoteItem.split("_");
        var groupId = strs[1];

        var items = $(pp).find("div[vote-item='"+attrVoteItem+"']");
        var len = items.length;

        var prevItem = $(parent).prev();
        var prevAttrVoteItem = prevItem.attr('vote-item');
        if(prevAttrVoteItem != null && prevAttrVoteItem != ''){
            //当前元素后是否存在其他项
            var flag = false;
            $(parent).nextAll().each(function(){
                if($(this).attr('vote-item') == attrVoteItem){
                    flag = true;
                    return false;
                }
            });

            if(!flag){
                var addBtn = $("<button>").addClass("layui-btn layui-btn-normal").attr("title",WebUtils.getMessage('vote.btn.label.addItem')).html("<i class='layui-icon layui-icon-add-1'></i>").bind('click',addOption);
                var delBtn = $("<button>").addClass("layui-btn layui-btn-danger").attr("title",WebUtils.getMessage('vote.btn.label.delItem')).html("<i class='layui-icon layui-icon-delete'></i>").bind('click',delOption);
                prevItem.find("button").each(function(){
                    $(this).remove();
                });
                addBtn.appendTo(prevItem);
                if(len != 2){
                    delBtn.appendTo(prevItem);
                }
            }

            $(parent).remove();
        }

    }
</script>