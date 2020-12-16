package com.king.framework.utils;

import com.king.framework.model.UserInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @创建人 chq
 * @创建时间 2020/4/17
 * @描述
 */
public class AuthUtils {

    public AuthUtils(){

    }

    public static UserInfo getUserInfo(){
        UserInfo userInfo = null;
        Subject subject = SecurityUtils.getSubject();
        if(subject != null){
            Object obj = subject.getPrincipal();
            userInfo = obj2Bean(obj);
        }
        return userInfo;
    }

    public static UserInfo obj2Bean(Object obj){
        Class<?> cls = obj.getClass();
        Field[] fields = cls.getDeclaredFields();
        Map<String,String> fieldMap = new HashMap<>();
        for(Field f : fields){
            fieldMap.put(f.getName(),"");
        }

        UserInfo user = new UserInfo();
        Field[] userFields = user.getClass().getDeclaredFields();

        try{
            for(Field f : userFields){
                String name = f.getName();
                if(fieldMap.containsKey(name)){
                    String methodName = "get" + name.substring(0,1).toUpperCase() + name.substring(1);
                    Method method = cls.getDeclaredMethod(methodName);
                    Object value = method.invoke(obj);
                    f.setAccessible(true);
                    f.set(user,value);
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return user;
    }

}
