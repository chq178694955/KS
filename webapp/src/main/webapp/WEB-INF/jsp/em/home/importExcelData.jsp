<%--
  Created by IntelliJ IDEA.
  User: 17869
  Date: 2020/4/11
  Time: 12:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="layui-fluid">
    <div class="layui-row">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body">
                    <form class="layui-form">
                        <%--<div class="layui-form-item">--%>
                            <%--<label class="layui-form-label">数据名称</label>--%>
                            <%--<div class="layui-input-block">--%>
                                <%--<input type="text" name="name" lay-verify="required" lay-verType="tips" placeholder="请输入数据名称" class="layui-input">--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button type="button" class="layui-btn layui-btn-normal" id="excelDataUploadBtn">
                                    <i class="layui-icon">&#xe67c;</i>选择文件
                                </button>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit="" lay-filter="addForm" id="commit" onclick="return false">立即上传</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    layui.use(['element','form','upload'],function(){
        var upload = layui.upload;
        var form = layui.form;
        upload.render({
            elem: '#excelDataUploadBtn'
            ,url: APP_ENV + '/em/home/uploadExcelData'
            ,auto:false
            ,bindAction:'#commit'
            ,before:function(){
                // this.data = {
                //     name: $('input[name="name"]').val()
                // }
            }
            ,done: function(res, index, upload){ //上传后的回调
                console.log(res);
                if(res.code == 0){
                    Frame.alert('上传成功');
                }else{
                    Frame.alert('上传失败，' + res.msg);
                }

            }
            ,accept: 'file' //允许上传的文件类型
            //,size: 50 //最大允许上传的文件大小
            ,exts:'xls|xlsx'
        });

        form.on('submit(addForm)',function(data){
            return false;
        });
    });
</script>