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
        <a href="javascript:;"><spring:message code="sys.menu.sysMgr"/></a>
        <a><cite><spring:message code="sys.menu.dictMgr"/></cite></a>
    </span>
</div>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">
                    <form class="layui-form" lay-filter="sysDictSearchForm">
                        <div class="layui-form-item">
                            <div class="layui-inline">
                                <label class="layui-form-label"><spring:message code="sys.dict.label.dictType"/></label>
                                <div class="layui-input-inline">
                                    <select id="classNo_${menuId}" name="classNo" lay-verify=""></select>
                                </div>
                            </div>
                            <div class="layui-inline">
                                <div class="layui-input-inline">
                                    <input id="dictDesc_${menuId}" type="text" name="dictDesc"  placeholder="<spring:message code="sys.dict.label.dictDescTip"/>" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-inline">
                                <button id="query_${menuId}" type="button" class="layui-btn layui-btn-normal" title="<spring:message code="com.btn.query"/>">
                                    <i class="layui-icon layui-icon-search"></i>
                                </button>
                                <button id="add_${menuId}" type="button" class="layui-btn layui-btn-warm" title="<spring:message code="com.btn.add"/>">
                                    <i class="layui-icon layui-icon-add-1"></i>
                                </button>
                                <button type="button" class="layui-btn" title="<spring:message code="com.btn.excel"/>">
                                    <i class="iconfont layui-icon-excel"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="layui-card-body ">
                    <table class="layui-table" lay-filter="sysDictList_${menuId}" lay-data="{url:'${ctx}/sys/dict/find',
                        id:'sysDictList_${menuId}',
                        page:true,
                        }">
                        <thead>
                        <tr>
                            <th lay-data="{checkbox:true}">ID</th>
                            <th lay-data="{field:'dictSn',width:100,align:'center'}"><spring:message code="sys.dict.field.sn"/></th>
                            <th lay-data="{field:'classNo',width:100,align:'center'}"><spring:message code="sys.dict.field.classNo"/></th>
                            <th lay-data="{field:'dictNo',width:100,align:'center'}"><spring:message code="sys.dict.field.dictNo"/></th>
                            <th lay-data="{field:'dictDesc',width:300,align:'center'}"><spring:message code="sys.dict.field.dictDesc"/></th>
                            <th lay-data="{field:'dispOrder',width:100,align:'center'}"><spring:message code="sys.dict.field.dispOrder"/></th>
                            <th lay-data="{field:'parentSn',width:100,align:'center'}"><spring:message code="sys.dict.field.parentSn"/></th>
                            <th lay-data="{field:'useFlag',width:100,align:'center',templet:'#usedFlagSwitchTpl_${menuId}'}"><spring:message code="sys.dict.field.useFlag"/></th>
                            <th lay-data="{field:'oper',fixed:'right',width:180,align:'center',toolbar:'#operBar_${menuId}'}"><spring:message code="com.btn.oper"/></th>
                        </thead>
                        <tbody>
                            <tr>
                                <td>ID</td>
                                <td>1</td>
                                <td>1</td>
                                <td>1</td>
                                <td>1</td>
                                <td>1</td>
                                <td>1</td>
                                <td>1</td>
                                <td>1</td>
                                <td></td>
                            </tr>
                        </tbody>
                    </table>

                    <script type="text/html" id="usedFlagSwitchTpl_${menuId}">
                        <input type = "checkbox" name = "useFlag" value = "{{d.useFlag}}" lay-skin = "switch" lay-text = "<spring:message code="sys.dict.field.useFlag.enable"/>|<spring:message code="sys.dict.field.useFlag.disable"/>" lay-filter = "usedFlagFilter" {{ d.useFlag == 1 ? 'checked': ''}} >
                    </script>

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
    layui.use(['form','element','table'], function(){
        var form = layui.form;
        var element = layui.element;
        var table = layui.table;

        //var tableIns = table.render();

        element.render('breadcrumb');
        loadDictTypes(function(){
            form.render('select','sysDictSearchForm');
        });

        $('#query_${menuId}').bind('click',table,query);
        $('#add_${menuId}').bind('click',toAdd);

        form.on('switch(usedFlagFilter)',function(obj){
            var useFlagObj = obj.othis;
            var parentTr = useFlagObj.parents("tr");
            var parentTrIndex = parentTr.attr("data-index");
            var data = table.cache['sysDictList_${menuId}'];
            var row = {};
            for(var i=0;i<data.length;i++){
                if(data[i].LAY_TABLE_INDEX == parentTrIndex){
                    row = data[i];
                    break;
                }
            }
            layer.tips(this.value + ' ' + this.name + '：'+ obj.elem.checked ? "关" : "开", obj.othis);
        });

        table.on('tool(sysDictList_${menuId})',function(obj){
            var data = obj.data;
            data.menuId = '${menuId}';
            switch(obj.event){
                case 'del':
                    Frame.confirm(WebUtils.getMessage('sys.dict.tip.delDictSure'),function(){
                        $.ajax({
                            url: APP_ENV + '/sys/dict/delete',
                            data:{dictSn: data.dictSn},
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
                    var title = WebUtils.getMessage('com.btn.update');
                    Frame.loadPage('${menuId}','sys/dict/toUpdate?menuId=${menuId}',data,WebUtils.getMessage('com.btn.modify'),400);
                    break;
            };
        });
    });

    function query(event){
        var table = event.data;
        table.reload('sysDictList_${menuId}',{
            where:{
                classNo: WebUtils.fmtStr($('#classNo_${menuId}').val()),
                dictDesc: WebUtils.fmtStr($('#dictDesc_${menuId}').val())
            },
            page:{
                curr:1
            }
        });
    }

    function loadDictTypes(callback){
        $.ajax({
            url: APP_ENV + '/sys/dict/loadDictTypes',
            data:{
                flag:"1"
            },
            dataType: 'json',
            success: function(dictTypes){
                if(dictTypes){
                    for(var i=0;i<dictTypes.length;i++){
                        var dict = dictTypes[i];
                        $('#classNo_${menuId}').append('<option value="'+dict.classNo+'">'+dict.dictDesc+'</option>')
                    }
                }
                if(typeof callback == 'function'){
                    callback();
                }
            }
        })
    }

    function toAdd(){
        Frame.loadPage('${menuId}','sys/dict/toAdd?menuId=${menuId}',{},WebUtils.getMessage('com.btn.add'),400);
        <%--Frame.tips('只是测试一下','dictDesc_${menuId}')--%>
        // Frame.load();
        // setTimeout(function(){
        //     Frame.closeLoad();
        // },5000);
        // Frame.confirm('确认执行吗？',function(){
        //     Frame.alert('收到，准备执行');
        // })
        //Frame.loadPage('${menuId}','sys/dict/toAdd','新增字典',400,600);
        // Frame.alert('在线调试');
        // Frame.warn('在线调试');
        // Frame.err('在线调试');
        //Frame.addMainTab({id:'${menuId}_01',title:{icon:'&#xe68e;',name:'新增字典'},url:APP_ENV + '/sys/dict/toAdd'});
    }

</script>