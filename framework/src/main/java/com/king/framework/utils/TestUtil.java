package com.king.framework.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @创建人 chq
 * @创建时间 2020/11/24
 * @描述
 */
public class TestUtil {
    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        list.add("a");list.add("a1");list.add("a2");
//        list.add("ab");list.add("af");list.add("a3");
//        list.add("ac");list.add("ag");list.add("a4");
//        list.add("ad");list.add("as");list.add("a5");
//        list.stream().limit(10).forEach(v ->{
//            System.out.println(v);
//        });

//        List<String> list = Arrays.asList("node", "java", "c++", "react", "javascript");
//
//        String result = list.stream().reduce((first, second) -> second).orElse("no last element");
//
//        System.out.println(result);

        String pwd = "aaaaa";
        String letterRuler = ".*[0-9]+.*$";

        System.out.println(Pattern.matches(letterRuler,pwd));

//        String hashAlgorithmName = "MD5";//加密方式
//        String oriPassword = "Admin@123";//密码原值
//        ByteSource salt = ByteSource.Util.bytes("admin");//以账号作为盐值
//        int hashIterations = 1024;//加密1024次
//        Object hashedCredentials = new SimpleHash(hashAlgorithmName, oriPassword, salt, hashIterations);
//        System.out.println(hashedCredentials.toString());
    }
}
