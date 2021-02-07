<%@ include file="/WEB-INF/jsp/common/tags.jsp" %>
<%--
  Created by IntelliJ IDEA.
  User: 17869
  Date: 2020/4/2
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<style>
    .welcome-page{
        background-color: #040d32;
    }
    .box-container{
        color: #FFF;
        font-size: 14px;
        /*text-align: center;*/
    }
    .box-container li{
        display: inline-block;
        width: calc(33% - 10px);/*因为margin不受box-sizing的影响，所以动态减去它的宽度*/
        height: 160px;
        background: rgba(1,19,67,0.8);
        border: 2px solid #00a1ff;
        border-radius: 6px;
        margin: 5px 5px 5px 5px;
        position: relative;
        box-sizing: border-box;
    }
    .box-container li::before{
        position: absolute;
        top: -2px;
        bottom: -2px;
        left: 30px;
        width: calc(100% - 60px);
        content: "";
        border-top: 2px solid #016886;
        border-bottom: 2px solid #016886;
        z-index: 0;
    }
    .box-container li::after{
        position: absolute;
        top: 30px;
        right: -2px;
        left: -2px;
        height: calc(100% - 60px);
        content: "";
        border-right: 2px solid #016886;
        border-left: 2px solid #016886;
        z-index: 0;
    }

    .box-container-item{
        position: absolute;
        left:30px;
        width: calc(100% - 60px);
        height: 100%;
    }
    .box-container-item-info{
        width: 100%;
        height: 100px;
        display: flex;
    }
    .box-container-item-info .box-container-item-info-name{
        flex: 2;
        height: 100%;
    }
    .box-container-item-info .box-container-item-info-name p{
        height: 50px;
        line-height: 50px;
    }
    .box-container-item-info .box-container-item-info-img{
        flex: 1;
        padding: 3px;
    }
    .box-container-item-info .box-container-item-info-img img{
        border-radius: 3px;
        width: 100%;
        /*黑白滤镜
        -webkit-filter: grayscale(100%);
        -moz-filter: grayscale(100%);
        -ms-filter: grayscale(100%);
        -o-filter: grayscale(100%);
        filter: grayscale(100%);
        filter: gray;
         */
    }
    .box-container-item-desc{
        width: 100%;
        height: 60px;
    }
</style>

<div class="layui-fluid">
    <div class="layui-row">
        <div class="layui-col-md12 welcome-page">
            <h1 style="text-align: center;color: #FFF;">可可馨园（可可小城）吃委会形象墙</h1>
            <ul class="box-container">
                <li>
                    <div class="box-container-item">
                        <div class="box-container-item-info">
                            <div class="box-container-item-info-name">
                                <p>职位：主任</p>
                                <p>姓名：周泽强</p>
                            </div>
                            <div class="box-container-item-info-img">
                                <img src="${ctx}/static/images/touxiang2.jpg"/>
                            </div>
                        </div>
                        <div class="box-container-item-desc">特长：喝酒、吹牛逼</div>
                    </div>
                </li>
                <li>
                    <div class="box-container-item">
                        <div class="box-container-item-info">
                            <div class="box-container-item-info-name">
                                <p>职位：副主任</p>
                                <p>姓名：谢正娟</p>
                            </div>
                            <div class="box-container-item-info-img">
                                <img src="${ctx}/static/images/touxiang1.jpg"/>
                            </div>
                        </div>
                        <div class="box-container-item-desc">特长：天杀的！</div>
                    </div>
                </li>
                <li>
                    <div class="box-container-item">
                        <div class="box-container-item-info">
                            <div class="box-container-item-info-name">
                                <p>职位：秘书</p>
                                <p>姓名：向友明</p>
                            </div>
                            <div class="box-container-item-info-img">
                                <img src="${ctx}/static/images/touxiang2.jpg"/>
                            </div>
                        </div>
                        <div class="box-container-item-desc">特长：去西站</div>
                    </div>
                </li>
                <li>
                    <div class="box-container-item">
                        <div class="box-container-item-info">
                            <div class="box-container-item-info-name">
                                <p>职位：委员</p>
                                <p>姓名：左洁怡</p>
                            </div>
                            <div class="box-container-item-info-img">
                                <img src="${ctx}/static/images/touxiang1.jpg"/>
                            </div>
                        </div>
                        <div class="box-container-item-desc">特长：伟哥，我们一起去蹭饭（关键蹭不到！）</div>
                    </div>
                </li>
                <li>
                    <div class="box-container-item">
                        <div class="box-container-item-info">
                            <div class="box-container-item-info-name">
                                <p>职位：委员</p>
                                <p>姓名：曹浩权</p>
                            </div>
                            <div class="box-container-item-info-img">
                                <img src="${ctx}/static/images/touxiang3.jpg"/>
                            </div>
                        </div>
                        <div class="box-container-item-desc">特长：静静看你们装逼</div>
                    </div>
                </li>
                <li>
                    <div class="box-container-item">
                        <div class="box-container-item-info">
                            <div class="box-container-item-info-name">
                                <p>职位：委员</p>
                                <p>姓名：李学伟</p>
                            </div>
                            <div class="box-container-item-info-img">
                                <img src="${ctx}/static/images/touxiang2.jpg"/>
                            </div>
                        </div>
                        <div class="box-container-item-desc">特长：蹭饭小王子</div>
                    </div>
                </li>
            </ul>
        </div>
    </div>
<%--    <fieldset class="layui-elem-field layui-field-title site-title">--%>
<%--        <legend>系统说明</legend>--%>
<%--        <div class="layui-card">--%>
<%--            <div class="layui-card-body">--%>
<%--                <blockquote class="layui-elem-quote">--%>
<%--                    <p style="color: #1E9FFF;font-weight: bold;">--%>
<%--                        <span class="layui-badge-dot"></span>--%>
<%--                        <span class="layui-badge-dot layui-bg-orange"></span>--%>
<%--                        <span class="layui-badge-dot layui-bg-green"></span>--%>
<%--                        <span class="layui-badge-dot layui-bg-cyan"></span>--%>
<%--                        <span class="layui-badge-dot layui-bg-blue"></span>--%>
<%--                        <span class="layui-badge-dot layui-bg-black"></span>--%>
<%--                        <span class="layui-badge-dot layui-bg-gray"></span>--%>
<%--                        <span class="layui-badge layui-bg-cyan">开始体验吧！</span>--%>
<%--                        <span class="layui-badge-dot layui-bg-gray"></span>--%>
<%--                        <span class="layui-badge-dot layui-bg-black"></span>--%>
<%--                        <span class="layui-badge-dot layui-bg-blue"></span>--%>
<%--                        <span class="layui-badge-dot layui-bg-cyan"></span>--%>
<%--                        <span class="layui-badge-dot layui-bg-green"></span>--%>
<%--                        <span class="layui-badge-dot layui-bg-orange"></span>--%>
<%--                        <span class="layui-badge-dot"></span>--%>
<%--                    </p>--%>
<%--                </blockquote>--%>
<%--            </div>--%>
<%--        </div>--%>
<%--    </fieldset>--%>
</div>

<script>
    layui.use(['element','layer'],function(){
        let element = layui.element;
        layer.open({
            title:'正在加载...',
            content:`<div class="layui-progress layui-progress-big" lay-showPercent="yes" lay-filter="demoProgress">
              <div class="layui-progress-bar layui-bg-blue" lay-percent="0%"></div>
            </div>`,
            success: function(layero,index){
                let initVal = 0;
                let testInterval = setInterval(function(){
                    initVal = initVal + 10;
                    if(initVal > 100){
                        window.clearInterval(testInterval);
                        Frame.closeLayer(index);
                    }
                    element.progress('demoProgress',initVal + '%');
                },300);
            },
            closeBtn:0,
            btn:[]
        });
    });
</script>

