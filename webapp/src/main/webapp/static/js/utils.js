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