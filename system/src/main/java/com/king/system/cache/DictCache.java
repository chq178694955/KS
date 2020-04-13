package com.king.system.cache;

import com.king.framework.cache.SystemCache;
import com.king.system.entity.SysDict;
import com.king.system.service.ISysDictService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 字典缓存工具类
 * @创建人 chq
 * @创建时间 2020/4/9
 * @描述
 */
@Component
public class DictCache extends SystemCache {

    private static final ConcurrentHashMap<String, ConcurrentHashMap<String, SysDict>> DICT_CACHE = new ConcurrentHashMap<String, ConcurrentHashMap<String, SysDict>>();

    @Autowired
    private ISysDictService sysDictService;

    public static SysDict getDict(String classNo, String dictNo) {

        if (DICT_CACHE.get(classNo) == null) {
            return null;
        }
        ConcurrentHashMap<String, SysDict> cache = DICT_CACHE.get(classNo);
        SysDict dict = null;
        for (Map.Entry<String, SysDict> entry : cache.entrySet()) {
            dict = entry.getValue();
            if (dictNo != null && dictNo.equals(String.valueOf(dict.getDictNo()))) {
                return dict;
            }
        }
        return null;
    }

    public static SysDict getDict(String classNo, String parentSn, String dictNo) {

        if (DICT_CACHE.get(classNo) == null) {
            return null;
        }

        ConcurrentHashMap<String, SysDict> cache = DICT_CACHE.get(classNo);

        SysDict dict = null;
        for (Map.Entry<String, SysDict> entry : cache.entrySet()) {
            dict = entry.getValue();
            if (parentSn != null && parentSn.equals(dict.getParentSn()) && dictNo != null
                    && dictNo.equals(String.valueOf(dict.getDictNo()))) {
                return dict;
            }
        }
        return null;
    }

    public static List<SysDict> getDict(String classNo) {

        List<SysDict> retDicts = new ArrayList<SysDict>();
        Set<Map.Entry<String, SysDict>> set = DICT_CACHE.get(classNo) == null ? null
                : DICT_CACHE.get(classNo).entrySet();
        SysDict retDict = null;
        if (set != null) {
            for (Map.Entry<String, SysDict> entry : set) {
                retDict = new SysDict();
                BeanUtils.copyProperties(entry.getValue(), retDict);
                retDicts.add(retDict);
            }
        }
        return retDicts;
    }

    public static Map<String, Map<String, SysDict>> getDicts() {

        Map<String, Map<String, SysDict>> map = new HashMap<String, Map<String, SysDict>>();
        map.putAll(DICT_CACHE);
        return map;
    }

    @Override
    protected void init() {
//        ISysDictService sysDictService = null;
//        try {
//            sysDictService = (ISysDictService) BeanLoader.getBean("sysDictService");
//        } catch (Exception e) {
//            sysDictService = null;
//        }
//        if (sysDictService != null) {
//            List<SysDict> dicts = sysDictService.findAll();
//            initCache(dicts);
//        }
        List<SysDict> dicts = sysDictService.findAll();
        initCache(dicts);
    }

    private void initCache(List<SysDict> dicts) {

        DICT_CACHE.clear();
        Integer classNo = null;
        Integer dictNo = null;
        Long dictSn = null;
        for (SysDict dict : dicts) {
            classNo = dict.getClassNo();
            dictNo = dict.getDictNo();
            dictSn = dict.getDictSn();
            if (dictNo == null || classNo == null) {
                continue;
            }
            if(classNo == null){
                continue;
            }
            if (!DICT_CACHE.containsKey(classNo)) {
                DICT_CACHE.put(classNo+"", new ConcurrentHashMap<String, SysDict>());
            }
            DICT_CACHE.get(classNo+"").put(dictSn+"", dict);
        }
    }
}
