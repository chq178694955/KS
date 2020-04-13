var element;
layui.use(['element','layer'],function(){
    element = layui.element;
});

/**
 * 主框架
 * @type {*|{}}
 */
window.Frame = window.Frame || {

    init: function(){
        this.Tabs.changeTab();
    },

    getTabId: function(){
        return this.Tabs.getTabId();
    },

    changeTab: function(index){
        this.Tabs.tabChange(index);
    },

    addMainTab: function(opts){
        this.Tabs.addTab(opts);
    },

    delMainTab: function(layId){
        this.Tabs.delTab(layId);
    },

    alert: function(content){
        this.Layer.alert(content);
    },
    warn: function(content){
        this.Layer.warn(content);
    },
    err: function(content){
        this.Layer.err(content);
    },
    confirm: function(title,doFunc){
      this.Layer.confirm(title,doFunc);
    },
    load: function(){
        this.Layer.load();
    },
    closeLayer: function(){
        if(this.Layer.layerIndex != null){
            layer.close(this.Layer.layerIndex);
        }
    },
    tips: function(title,id){
        this.Layer.tips(title,id);
    },
    loadPage: function(id,url,params,title,width,height){
        this.Layer.loadPage(id,url,params,title,width,height);
    }

}

/**
 * 主框架Tabs选项卡操作
 * @type {{}}
 */
Frame.Tabs = {

    mainId: 'mainFrameTab',
    prefixMainId: 'mainFrameMenu_',
    mainContentId: 'mainFrameContent',

    createTitle: function(opts){
        opts = opts || {};
        if(opts instanceof String){
            opts = {title: opts}
        }
        if(WebUtils.isEmpty(opts.icon)){
            return WebUtils.isEmpty(opts.name) ? '' : opts.name;
        }else{
            //return "<i class='layui-icon'>"+opts.icon+"</i>" + "<span>&nbsp;"+(WebUtils.isEmpty(opts.name) ? '' : opts.name)+"</span>";
            return "<i class='iconfont'>"+opts.icon+"</i>" + "<span>&nbsp;"+(WebUtils.isEmpty(opts.name) ? '' : opts.name)+"</span>";
        }
    },

    /**
     * 判断Tab是否存在
     * @param id
     * @returns {boolean}
     */
    existTab: function(id){
        var instance = this;
        var flag = false;
        $('#' +this.mainId).find(".layui-tab-title").find("li").each(function(){
            if($(this).attr('lay-id') == (instance.prefixMainId + id)){
                flag = true;
                return false;//结束循环
            }
        });
        return flag;
    },

    getTabId: function(){
        return $('#' +this.mainId).find(".layui-tab-title .layui-this").attr("lay-id");
    },

    tabChange: function(index){
        element.tabChange(this.mainId,this.prefixMainId + index);
    },

    changeTab: function(){
        element.on('tab('+this.mainId+')',function(data){
            console.log(this);
            console.log(data.index);
            console.log(data.elem);
            // alert(Frame.getTabId());
        });
    },

    addTab: function(opts){
        opts = typeof(opts) == "undefined" ? {} : opts;
        if(Frame.Tabs.existTab(opts.id)){
            Frame.Tabs.tabChange(opts.id)
        }else{
            if(!WebUtils.isEmpty(opts.url)){
                $.ajax({
                    url: opts.url,
                    async: true,
                    success: function(html){
                        opts.content = "<div style='height: 100%;overflow-y: auto'>"+html+"</div>";
                        var newTitle = Frame.Tabs.createTitle(opts.title);
                        // opts.content = $("<div>").css({height:'100%','overflow-y':'auto'}).append(html).toString();
                        element.tabAdd(Frame.Tabs.mainId,{
                            //title:Frame.Tabs.createTitle(opts.title),
                            // title:'<i class="layui-icon">'+opts.title.icon+'</i>' + opts.title.name,
                            title:newTitle,
                            content:opts.content,
                            id:'mainFrameMenu_' + opts.id
                        });
                        Frame.Tabs.tabChange(opts.id);
                    }
                });
            }else if(!WebUtils.isEmpty(opts.content)){
                element.tabAdd(Frame.Tabs.mainId,{
                    title:opts.title,
                    content:opts.content,
                    id:'mainFrameMenu_' + opts.id
                })
            }
        }
    },

    delTab: function(layid){
        element.tabDelete(Frame.Tabs.mainId,layid);
    }

}

/**
 * 权限菜单
 * @type {{}}
 */
Frame.Menu = {

    defaultIcon: '&#xe666;',

    init: function(){
        $.ajax({
            url: APP_ENV + '/loadMenus',
            dataType:'json',
            success: function(roots){
                if(roots){
                    for(var i=0;i<roots.length;i++){
                        var node = roots[i];//当前节点
                        var children = roots[i].children;//子节点

                        var $parentItem = $("<li>");
                        var $one = $("<i>").addClass("iconfont left-nav-li").attr("lay-tips",node.text).html(node.icon == "" ? Frame.Menu.defaultIcon : node.icon);
                        var $two = $("<cite>").html(node.text);
                        var $three = $("<i>").addClass("iconfont nav_right").html("&#xe697;");
                        var $parentLink = $("<a>").attr("href","javascript:;");
                        $one.appendTo($parentLink);
                        $two.appendTo($parentLink);
                        $three.appendTo($parentLink);
                        $parentLink.appendTo($parentItem);
                        if(children){
                            var $subMenu = $("<ul>").addClass("sub-menu");
                            for(var j=0;j<children.length;j++){
                                var subNode = children[j];
                                var $subIcon = $("<i>").addClass("iconfont").html(subNode.icon == "" ? Frame.Menu.defaultIcon : subNode.icon);
                                var $subText = $("<cite>").html(subNode.text);
                                var $childLink = $("<a>").attr("href","javascript:;");
                                //绑定lick事件
                                $childLink.unbind('click').bind('click',subNode,Frame.Menu.itemClick);
                                var $subItem = $("<li>");
                                $subIcon.appendTo($childLink);
                                $subText.appendTo($childLink);
                                $childLink.appendTo($subItem);
                                $subItem.appendTo($subMenu);
                            }
                            $subMenu.appendTo($parentItem);
                        }
                        $('.left-nav #nav').append($parentItem);
                    }
                }
            }
        });
    },

    /**
     * 菜单点击事件
     * @param event
     */
    itemClick: function(event){
        var node = event.data;
        if(node){
            Frame.addMainTab({
                id:node.id,
                title:{
                    icon:node.icon == '' ? Frame.Menu.defaultIcon : node.icon,
                    name:node.text
                },
                url:APP_ENV + '/' + node.url + '?menuId=' +node.id
            });
        }
    }

}

/**
 * 基于layUI封装
 * type：0（信息框，默认）1（页面层）2（iframe层）3（加载层）4（tips层）
 * @type {{alert: Frame.Layer.alert}}
 */
Frame.Layer = {

    layerIndex: null,

    alert: function(content){
        layer.open({
            title: WebUtils.getMessage('com.layer.title1'),
            content: WebUtils.fmtStr(content)
        })
    },
    warn: function(content){
        layer.open({
            title: WebUtils.getMessage('com.layer.title2'),
            content: WebUtils.fmtStr(content)
        })
    },
    err: function(content){
        layer.open({
            title: WebUtils.getMessage('com.layer.title3'),
            content: WebUtils.fmtStr(content)
        })
    },
    confirm: function(title,doFunc){
        layer.confirm(title,{icon:3,title:WebUtils.getMessage('com.layer.title1')},function(index){
            if(typeof doFunc == 'function'){
                doFunc();
            }
            layer.close(index);
        });
    },
    load: function(){
        this.layerIndex = layer.load();
    },
    tips: function(title,id){
        layer.tips(title,'#' + id)
    },
    loadPage: function(id,url,params,title,width,height){
        var area = 'auto';
        if(width && !height){
            area = {'area': width + 'px'}
        }
        if(width && height){
            area = [width + 'px',height + 'px']
        }
        $.ajax({
            url: APP_ENV + '/' + url,
            data: params,
            success: function(html){
                if(html){
                    Frame.Layer.layerIndex = layer.open({
                        id:'loadPage_' + id,
                        type:1,
                        title: title == null ? '' : title,
                        content: html,
                        area: area
                    })
                }
            }
        })
    }
}