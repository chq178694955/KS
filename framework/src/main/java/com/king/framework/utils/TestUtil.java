package com.king.framework.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/11/24
 * @描述
 */
public class TestUtil {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");list.add("a1");list.add("a2");
//        list.add("ab");list.add("af");list.add("a3");
//        list.add("ac");list.add("ag");list.add("a4");
//        list.add("ad");list.add("as");list.add("a5");
        list.stream().limit(10).forEach(v ->{
            System.out.println(v);
        });
    }
}
