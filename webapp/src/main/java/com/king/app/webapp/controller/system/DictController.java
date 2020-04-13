package com.king.app.webapp.controller.system;

import com.github.pagehelper.PageInfo;
import com.king.app.webapp.dto.ResultResp;
import com.king.framework.base.BaseController;
import com.king.framework.model.Criteria;
import com.king.framework.utils.I18nUtils;
import com.king.system.entity.SysDict;
import com.king.system.service.ISysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/4/10
 * @描述
 */
@Controller
@RequestMapping("sys/dict")
public class DictController extends BaseController {

    @Autowired
    private ISysDictService sysDictService;

    @RequestMapping("toMain")
    public ModelAndView toMain(){
        ModelAndView mv = new ModelAndView("sys/dict/main");
        return mv;
    }

    @RequestMapping("loadDictTypes")
    @ResponseBody
    public Object loadDictTypes(HttpServletRequest request){
        String flag = super.getParam("flag");
        List<SysDict> dictTypes = sysDictService.findTopDicts();
        if(dictTypes == null)dictTypes = new ArrayList<>();
        if(flag.equals("1")){
            SysDict dict = new SysDict();
            dict.setClassNo(0);
            dict.setDictDesc(I18nUtils.get("com.select.all"));
            dictTypes.add(0,dict);
        }else if(flag.equals("2")){
            SysDict dict = new SysDict();
            dict.setClassNo(0);
            dict.setDictDesc(I18nUtils.get("sys.dict.field.classNo"));
            dictTypes.add(0,dict);
        }
        return dictTypes;
    }

    @ResponseBody
    @RequestMapping("find")
    public Object find(HttpServletRequest request){
        PageInfo<SysDict> page = super.getPage(request);
        Criteria criteria = new Criteria();
        criteria.put("classNo",super.getIntegerParam("classNo"));
        criteria.put("dictDesc",super.getParam("dictDesc"));
        PageInfo<SysDict> pageInfo = sysDictService.find(page,criteria,isDownloadReq());
        return getGridData(pageInfo);
    }

    @RequestMapping("toAdd")
    public ModelAndView toAdd(){
        ModelAndView mv = new ModelAndView("sys/dict/add");
        return mv;
    }

    @RequestMapping("add")
    @ResponseBody
    public Object add(HttpServletRequest request,SysDict dict){
        if(sysDictService.isExistsDict(dict.getDictSn())){
            return new ResultResp(I18nUtils.get("sys.dict.tip.existsDict"));
        }else{
            if(sysDictService.addDict(dict) > 0){
                return ResultResp.ok();
            }else{
                return ResultResp.fail();
            }
        }
    }

    @RequestMapping("toUpdate")
    public ModelAndView toUpdate(HttpServletRequest request,SysDict dict){
        ModelAndView mv = new ModelAndView("sys/dict/update");
        mv.addObject("dict",dict);
        return mv;
    }

    @RequestMapping("update")
    @ResponseBody
    public Object update(HttpServletRequest request,SysDict dict){
        if(sysDictService.updateDict(dict) > 0){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public Object delete(HttpServletRequest request,Long dictSn){
        if(sysDictService.deleteDict(dictSn) > 0){
            return ResultResp.ok();
        }else{
            return ResultResp.fail();
        }
    }

}
