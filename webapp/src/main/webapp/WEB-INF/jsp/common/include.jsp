<%--
  Created by IntelliJ IDEA.
  User: 17869
  Date: 2020/3/27
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" href="${ctx}/static/fonts/ledfont.css">
<link rel="stylesheet" href="${ctx}/static/X-admin/css/font.css">
<link rel="stylesheet" href="${ctx}/static/fonts/iconfont.css">
<link rel="stylesheet" href="${ctx}/static/X-admin/css/login.css">
<link rel="stylesheet" href="${ctx}/static/X-admin/css/xadmin.css">
<script src="${ctx}/static/X-admin/js/jquery.min.js" charset="utf-8"></script>
<script src="${ctx}/static/X-admin/lib/layui/layui.js" charset="utf-8"></script>
<script src="${ctx}/static/echarts-2.2.7/build/dist/echarts-all.js"></script>
<script type="text/javascript" src="${ctx}/static/X-admin/js/xadmin.js"></script>
<script type="text/javascript" src="${ctx}/static/js/DataMap.js"></script>
<script type="text/javascript" src="${ctx}/static/js/DateUtils.js"></script>
<script type="text/javascript" src="${ctx}/static/js/utils.js"></script>
<script type="text/javascript" src="${ctx}/static/js/frame.js"></script>

<script>
    /**
     * 这里处理一些系统初始化需要加载的数据
     */
    WebUtils.Context.init('${ctx}');
    WebUtils.Lang.init('${empty i18nKeys ? '[]' : i18nKeys}');
    //add permission files
    WebUtils.Permission.init('${sessionScope.permissions}');
</script>

