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

    modMainTab: function(opts){
        this.Tabs.updateTab(opts)
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
    info: function(content,iconIndex){
        this.Layer.info(content,iconIndex);
    },
    confirm: function(title,doFunc){
      this.Layer.confirm(title,doFunc);
    },
    load: function(){
        return this.Layer.load();
    },
    closeLayer: function(index){
        layer.close(index);
    },
    closeAllLayer: function(){
        layer.closeAll();
    },
    tips: function(title,id){
        this.Layer.tips(title,id);
    },
    loadPage: function(id,url,params,title,width,height){
        this.Layer.loadPage(id,url,params,title,width,height);
    },

    exportExcel: function(layId,url,queryParams,title){
        this.DataGrid.exportExcel(layId,url,queryParams,title)
    },

    goBack: function(id,url){
        this.modMainTab({
            id:id,
            url:APP_ENV + url,
            params:{
                menuId:id
            }
        })
    }

}

/**
 * 主框架Tabs选项卡操作
 * @type {{}}
 */
Frame.Tabs = {

    mainId: 'mainFrameTab',
    prefixMainId: 'mainFrameMenu_',
    mainContentId: 'mainFrameContent_',

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

    updateTab: function(opts){
        if(Frame.Tabs.existTab(opts.id)){
            var that = this;
            that.layerIndex = null;
            $.ajax({
                url: opts.url,
                data: opts.params,
                beforeSend: function(){
                    that.layerIndex = Frame.load();
                },
                success: function(html){
                    $('#' + that.mainContentId + opts.id).html(html);
                },
                error: function(xhr,status,errorMsg){

                },
                complete: function(XMLHttpRequest,status){
                    if(that.layerIndex != null){
                        Frame.closeLayer(that.layerIndex);
                    }
                }
            });
        }
    },

    addTab: function(opts){
        opts = typeof(opts) == "undefined" ? {} : opts;
        if(Frame.Tabs.existTab(opts.id)){
            Frame.Tabs.tabChange(opts.id)
        }else{
            if(!WebUtils.isEmpty(opts.url)){
                if(opts.url.indexOf('/#') >= 0){
                    Frame.info(WebUtils.getMessage("ks.global.exception.404"),2)
                    return ;
                }
                var that = this;
                that.layerIndex = null;
                $.ajax({
                    url: opts.url,
                    async: true,
                    beforeSend: function(){
                        that.layerIndex = Frame.load();
                    },
                    success: function(html){
                        if(typeof html == 'string'){
                            opts.content = "<div style='height: 100%;overflow-y: auto' id='"+that.mainContentId + opts.id+"'>"+html+"</div>";
                            var newTitle = Frame.Tabs.createTitle(opts.title);
                            // opts.content = $("<div>").css({height:'100%','overflow-y':'auto'}).append(html).toString();
                            element.tabAdd(Frame.Tabs.mainId,{
                                //title:Frame.Tabs.createTitle(opts.title),
                                // title:'<i class="layui-icon">'+opts.title.icon+'</i>' + opts.title.name,
                                title:newTitle,
                                content:opts.content,
                                id:Frame.Tabs.prefixMainId + opts.id
                            });
                            Frame.Tabs.tabChange(opts.id);
                        }else{
                            Frame.info(html.msg,2);
                        }
                    },
                    error: function(xhr,status,errorMsg){
                        if(that.layerIndex != null){
                            Frame.closeLayer(that.layerIndex);
                        }
                        if(xhr.status == 404){
                            Frame.info(WebUtils.getMessage("ks.global.exception.404"),2)
                        }else if(xhr.status == 500){
                            Frame.info(WebUtils.getMessage("ks.global.exception.500"),2)
                        }else{
                            Frame.info(WebUtils.getMessage("ks.global.exception.other"),2)
                        }
                    },
                    complete: function(){
                        if(that.layerIndex != null){
                            Frame.closeLayer(that.layerIndex);
                        }
                    }
                });
            }else if(!WebUtils.isEmpty(opts.content)){
                element.tabAdd(Frame.Tabs.mainId,{
                    title:opts.title,
                    content:opts.content,
                    id:Frame.Tabs.prefixMainId + opts.id
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

    defaultIcon: '&#xe717;',

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

    alert: function(content){
        return layer.open({
            title: WebUtils.getMessage('com.layer.title1'),
            content: WebUtils.fmtStr(content)
        })
    },
    warn: function(content){
        return layer.open({
            title: WebUtils.getMessage('com.layer.title2'),
            content: WebUtils.fmtStr(content)
        })
    },
    err: function(content){
        return layer.open({
            title: WebUtils.getMessage('com.layer.title3'),
            content: WebUtils.fmtStr(content)
        })
    },
    info: function(content,iconIndex){
        layer.closeAll();
        layer.alert(content,{
            shade:0,
            icon:iconIndex != undefined ? iconIndex : 1,
            offset:'rb',
            time:3000,
            anim:2
        },function(index){
            layer.close(index);
        });
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
        return layer.load();
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
                    layer.open({
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

/**
 * layUI表格相关操作
 * @type {{}}
 */
Frame.DataGrid = {

    defColspan:"1",
    defRowspan:"1",

    exportExcel: function(layId,url,queryParams,title){
        var headers = this.getHeaders(layId);

        var pageNo = 1;
        var pageSize = -1;

        // 获取导出表单
        var form = $('<form></form>');
        form.append($('<input type="text" name="exportTitle" value="' + title + '"/>'))
            .append($('<input type="text" name="page" value="' + pageNo + '"/>'))
            .append($('<input type="text" name="limit" value="' + pageSize + '"/>'))
            .append($('<input type="text" value="1" name="isDownload"/>'));
        for (var key in queryParams) {
            if (queryParams[key] == undefined || queryParams[key] == 'undefined') {
                queryParams[key] = '';
            }
            form.append($('<input type="text" name="' + key + '" value="' + queryParams[key] + '"/>'));
        }

        // 设置特殊参数
        var params = form.serialize();
        if (params) {
            params += '&exportHeaders=' + encodeURIComponent(JSON.stringify(headers)); //json参数特殊处理
        }
        $.ajax({
            type: 'post',
            url: url,
            data: params,
            dataType: 'json',
            beforeSend: function(){
                Frame.load();
            },
            success: function (data) {
                if (data.guid) {
                    Frame.DataGrid.defFilterCols = 0;
                    window.self.location = APP_ENV + '/global/datagrid/download?guid='+data.guid;
                }
            },
            complete: function(XMLHttpRequest,status){
                Frame.closeLayer();
            }
        });
        // 清理导出表单
        form.remove();
        form = null;
    },

    /**
     * 获取表头
     */
    getHeaders: function(layId){
        //需要过滤的表头列
        var filterFieldMap = new DataMap();
        filterFieldMap.put('oper','');//手动添加的按钮处理列，约定

        var columns = [];//表头属性，二维数组

        $('div[lay-id="'+layId+'"] table thead tr').each(function(){
            var cols = [];
            $(this).find('th').each(function(){
                var name = $(this).attr('data-field');
                if(name == '')return true;//过滤
                if(WebUtils.isNum(name))return true;//过滤，数字默认是layui自动生成的属性
                if(filterFieldMap.containsKey(name)){
                   return true;//跳出当前循环，执行下一次循环
                }
                var text = $(this).find('span:eq(0)').html();
                var attrAlign = $($(this)[0]).find('div:eq(0)').attr('align');
                var align = attrAlign != null ? attrAlign : 'left';
                var rowspan = $(this).attr('rowspan') != null ? $(this).attr('rowspan') : Frame.DataGrid.defRowspan;
                var colspan = $(this).attr('colspan') != null ? $(this).attr('colspan') : Frame.DataGrid.defColspan;
                var field = {
                    name : name,
                    text: text,
                    align : align,
                    rowspan : rowspan,
                    colspan : colspan
                }
                cols.push(field);
            });
            if(cols.length > 0){
                columns.push(cols);
            }
        });
        return columns;
    }

}