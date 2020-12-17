/* 全局资源类*/
try{
    window.i18nKeys = window.i18nKeys 	|| window.top.i18nKeys 	|| new DataMap();
    window.permisstionDataMap = window.permisstionDataMap	|| window.top.permisstionDataMap	|| new DataMap();
}catch(e){
    //跨域的情况下，firefox不能够使用parent或者top。
    window.i18nKeys = window.i18nKeys 	|| new DataMap();
    window.permisstionDataMap = window.permisstionDataMap	|| new DataMap();
    //console.info("跨域了,当前域为="+window.location.host);
}

var APP_ENV = null;

window.WebUtils = window.WebUtils || {

    /**
     * 包含字符串数字
     * 是否数字，true：是 false：否
     * @param value
     * @returns {boolean}
     */
    isNum: function(value){
        return !isNaN(value);
        //return typeof value === 'number' && !isNaN(value);
    },

    /**
     * 格式化字符串
     * @param content
     * @returns {*}
     */
    fmtStr: function(content){
        if(typeof content == undefined){
            return '';
        }
        if(content == "undefined"){
            return '';
        }
        if(content == null){
            return '';
        }
        if(content == ""){
            return '';
        }
        return content;
    },

    /**
     * 判断空
     */
    isEmpty: function(content){
        if(typeof content == undefined){
            return true;
        }
        if(content == "undefined"){
            return true;
        }
        if(content == null){
            return true;
        }
        if(content == ""){
            return true;
        }
        return false;
    },

    /**
     * 获取根路径
     */
    getEnv: function(){
        return this.Context.getEnv();
    },

    getMessage: function(key,params){
        return this.Lang.getMessage(key,params);
    },

    hasPermission: function(key){
        return this.Permission.hasPermission(key)
    }

}

WebUtils.Context = {

    //获取WEB环境
    getEnv: function(){
        return APP_ENV;
    },

    init: function(env){
        APP_ENV = env;
    }

}

WebUtils.GolbalAjax = {
    init: function(){
        $.ajaxSetup({
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                if(XMLHttpRequest.status==403) {
                    Frame.info('暂无权限',2)
                    return false;
                }
            },
            complete:function(XMLHttpRequest,textStatus) {
                switch (XMLHttpRequest.status) {
                    case 400:{
                        Frame.info('请求参数错误',2)
                        break;
                    }
                    case 405:{
                        Frame.info('不支持当前请求方法',2)
                        break;
                    }
                    case 415:{
                        Frame.info('不支持当前媒体类型',2)
                        break;
                    }
                    case 500:{
                        Frame.info('服务器异常',2)
                        break;
                    }
                    case 10001:{
                        Frame.info('业务异常',2)
                        break;
                    }
                    case 10002:{
                        Frame.info('缓存异常',2)
                        break;
                    }
                    case 10003:{
                        Frame.info('数据异常',2)
                        break;
                    }
                    case 10004:{
                        Frame.info('参数异常',2)
                        break;
                    }
                    default:{
                        break;
                    }
                }
            }
        });
    }
}

WebUtils.Lang = {
    init: function(result){
        var _result = JSON.parse(result);
        if(_result && _result.length > 0){
            for (var i = 0; i < _result.length; i++) {
                var i18nKey = _result[i].key;
                var i18nVal = _result[i].val;
                if (i18nKeys.containsKey(i18nKey)) {
                    continue;
                }
                i18nKeys.put(i18nKey, i18nVal);
            }
        }
    },
    getMessage: function(key,params){
        if (typeof(i18nKeys.get(key)) != 'undefined'){
            var val =  i18nKeys.get(key);
            params = params || [];
            params = (params instanceof Array ) ? params
                : [params];
            for (var i = 0; i < params.length; i++) {
                val = val.replace("{"+i+"}", params[i]);
            }
            return val;
        } else {
            return key;
        }
    }
}

WebUtils.Permission = {

    init: function(permissions){
        if (permissions) {
            permisstionDataMap.clear();
            var permissionsAry = permissions.split(",");
            for(var i=0;i<permissionsAry.length;i++){
                permisstionDataMap.put(permissionsAry[i],'');
            }
        }
    },

    hasPermission: function(key){
        if(permisstionDataMap.containsKey(key)){
            return true;
        }else{
            return false;
        }
    }
}

WebUtils.Chart = {

    theme:['macarons','infographic','blue','green','red','helianthus'],

    createChart: function(divId,option){
        var myChart = echarts.init(document.getElementById(divId),this.theme[5]);
        myChart.showLoading();
        myChart.setOption(option);
        myChart.hideLoading();
    },

    createRemoteChart: function(divId,url,params){
        var instance = this;
        var myChart = echarts.init(document.getElementById(divId),instance.theme[5]);
        myChart.showLoading();
        $.ajax({
            url: url,
            data: params,
            dataType: 'json',
            success: function(option){
                myChart.hideLoading();
                myChart.setOption(option);
            },
            error: function(){
                myChart.hideLoading();
                Frame.info("create chart error!")
            }
        });
    }

}