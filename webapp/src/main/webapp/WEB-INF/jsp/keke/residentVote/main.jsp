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
        <a><cite>业主投票</cite></a>
    </span>
</div>
<div class="layui-fluid">
    <div class="layui-row">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">
                    <form class="layui-form" lay-filter="searchForm_${menuId}">
                        <div class="layui-form-item" style="padding-top: 2px;">
                            <div class="layui-inline">
                                <label class="layui-form-label">楼栋</label>
                                <div class="layui-input-inline">
                                    <select name="building" id="building_${menuId}">
                                        <c:forEach var="resident" items="${residentInfoList}">
                                            <option value="${resident.building}">${resident.building}栋</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
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
                                <button id="query_${menuId}" type="button" class="layui-btn layui-btn-normal" title="<spring:message code="com.btn.query"/>">
                                    <i class="layui-icon layui-icon-search"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="layui-card-body">
                    <ul class="x-king-resident-vote" id="buildingVoteInfo_${menuId}">
<%--                        <li style="background-color: #2F4056">--%>
<%--                            <div class="x-king-resident-vote-item">--%>
<%--                                <div class="x-king-resident-vote-item-left"><i class="iconfont">&#xe6e6;</i>&nbsp;房号</div>--%>
<%--                                <div class="x-king-resident-vote-item-right">1503</div>--%>
<%--                            </div>--%>
<%--                            <div class="x-king-resident-vote-item">--%>
<%--                                <div class="x-king-resident-vote-item-left"><i class="iconfont">&#xe631;</i>&nbsp;姓名</div>--%>
<%--                                <div class="x-king-resident-vote-item-right">曹浩权</div>--%>
<%--                            </div>--%>
<%--                            <div class="x-king-resident-vote-item">--%>
<%--                                <div class="x-king-resident-vote-item-left"><i class="iconfont">&#xe664;</i>&nbsp;手机</div>--%>
<%--                                <div class="x-king-resident-vote-item-right">1503</div>--%>
<%--                            </div>--%>
<%--                            <div class="x-king-resident-vote-item">--%>
<%--                                <div class="x-king-resident-vote-item-left"><i class="iconfont">&#xe621;</i>&nbsp;身份证</div>--%>
<%--                                <div class="x-king-resident-vote-item-right">43052119880510003X</div>--%>
<%--                            </div>--%>
<%--                            <div class="x-king-resident-vote-item">--%>
<%--                                <div class="x-king-resident-vote-item-left"><i class="iconfont">&#xe60e;</i>&nbsp;状态</div>--%>
<%--                                <div class="x-king-resident-vote-item-right"><a href="javascript:void(0);" class="layui-btn layui-btn-xs layui-btn-danger" title="未投">未投</a></div>--%>
<%--                            </div>--%>
<%--                        </li>--%>
<%--                        <li style="background-color: #01AAED">--%>
<%--                            <div class="x-king-resident-vote-item">--%>
<%--                                <div class="x-king-resident-vote-item-left"><i class="iconfont">&#xe6e6;</i>&nbsp;房号</div>--%>
<%--                                <div class="x-king-resident-vote-item-right">1503</div>--%>
<%--                            </div>--%>
<%--                            <div class="x-king-resident-vote-item">--%>
<%--                                <div class="x-king-resident-vote-item-left"><i class="iconfont">&#xe631;</i>&nbsp;姓名</div>--%>
<%--                                <div class="x-king-resident-vote-item-right">曹浩权</div>--%>
<%--                            </div>--%>
<%--                            <div class="x-king-resident-vote-item">--%>
<%--                                <div class="x-king-resident-vote-item-left"><i class="iconfont">&#xe664;</i>&nbsp;手机</div>--%>
<%--                                <div class="x-king-resident-vote-item-right">1503</div>--%>
<%--                            </div>--%>
<%--                            <div class="x-king-resident-vote-item">--%>
<%--                                <div class="x-king-resident-vote-item-left"><i class="iconfont">&#xe621;</i>&nbsp;身份证</div>--%>
<%--                                <div class="x-king-resident-vote-item-right">43052119880510003X</div>--%>
<%--                            </div>--%>
<%--                            <div class="x-king-resident-vote-item">--%>
<%--                                <div class="x-king-resident-vote-item-left"><i class="iconfont">&#xe60e;</i>&nbsp;状态</div>--%>
<%--                                <div class="x-king-resident-vote-item-right">已投</div>--%>
<%--                            </div>--%>
<%--                        </li>--%>
                    </ul>
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

        $('#query_${menuId}').bind('click',queryResidentVoteList);

        function queryResidentVoteList(){
            var building = $('#building_${menuId}').val();
            var voteId = $('#voteId_${menuId}').val();
            $.ajax({
                url: APP_ENV + '/keke/residentVote/building',
                data:{
                    building: building,
                    voteId: voteId
                },
                dataType:'json',
                success: function(res){
                    $('#buildingVoteInfo_${menuId}').find('li').remove();
                    for(var i=0;i<res.length;i++){
                        var voteStatus = res[i].voteStatus;
                        var bgColor = voteStatus == 1 ? '#01AAED' : '#2F4056';
                        var $li = $('<li>').css('background-color',bgColor);
                        var $voteBtn = voteStatus == 1 ?
                            $('<button>').addClass('layui-btn').addClass('layui-btn-xs').addClass('layui-btn-warm').attr('title','已投').attr('data-resident',res[i].residentId).attr('data-vote',voteId).append('已投').bind('click',voteEvent)
                            :
                            $('<button>').addClass('layui-btn').addClass('layui-btn-xs').addClass('layui-btn-danger').attr('title','未投').attr('data-resident',res[i].residentId).attr('data-vote',voteId).append('未投').bind('click',voteEvent)
                        ;

                        //房号
                        var $houseNo1 = $('<div>').addClass('x-king-resident-vote-item-left').append("<i class='iconfont'>&#xe6e6;</i>&nbsp;房号");
                        var $houseNo2 = $('<div>').addClass('x-king-resident-vote-item-right').append(res[i].houseNo);
                        var $houseNo = $('<div>').addClass('x-king-resident-vote-item');
                        $houseNo1.appendTo($houseNo);
                        $houseNo2.appendTo($houseNo);
                        $houseNo.appendTo($li);

                        //姓名
                        var $houseHolder1 = $('<div>').addClass('x-king-resident-vote-item-left').append("<i class='iconfont'>&#xe631;</i>&nbsp;姓名");
                        var $houseHolder2 = $('<div>').addClass('x-king-resident-vote-item-right').append(res[i].houseHolder);
                        var $houseHolder = $('<div>').addClass('x-king-resident-vote-item');
                        $houseHolder1.appendTo($houseHolder);
                        $houseHolder2.appendTo($houseHolder);
                        $houseHolder.appendTo($li);

                        //手机
                        var $phone1 = $('<div>').addClass('x-king-resident-vote-item-left').append("<i class='iconfont'>&#xe664;</i>&nbsp;手机");
                        var $phone2 = $('<div>').addClass('x-king-resident-vote-item-right').append(res[i].phone);
                        var $phone = $('<div>').addClass('x-king-resident-vote-item');
                        $phone1.appendTo($phone);
                        $phone2.appendTo($phone);
                        $phone.appendTo($li);

                        //身份证
                        var $idCardNo1 = $('<div>').addClass('x-king-resident-vote-item-left').append("<i class='iconfont'>&#xe621;</i>&nbsp;身份证");
                        var $idCardNo2 = $('<div>').addClass('x-king-resident-vote-item-right').append(res[i].idCardNo);
                        var $idCardNo = $('<div>').addClass('x-king-resident-vote-item');
                        $idCardNo1.appendTo($idCardNo);
                        $idCardNo2.appendTo($idCardNo);
                        $idCardNo.appendTo($li);

                        //状态
                        var $voteStatus1 = $('<div>').addClass('x-king-resident-vote-item-left').append("<i class='iconfont'>&#xe60e;</i>&nbsp;状态");
                        var $voteStatus2 = $('<div>').addClass('x-king-resident-vote-item-right').append($voteBtn);
                        var $voteStatus = $('<div>').addClass('x-king-resident-vote-item');
                        $voteStatus1.appendTo($voteStatus);
                        $voteStatus2.appendTo($voteStatus);
                        $voteStatus.appendTo($li);

                        $li.appendTo($('#buildingVoteInfo_${menuId}'));
                    }

                }
            });
        }

        function voteEvent(){
            var residentId = $(this).attr('data-resident');
            var voteId = $(this).attr('data-vote');
            Frame.loadPage('${menuId}','keke/residentVote/toVote?menuId=${menuId}',{residentId:residentId,voteId:voteId},'我的投票',600,450);
        }
    });
</script>