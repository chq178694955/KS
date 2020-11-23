package com.king.framework.utils;

import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @创建人 chq
 * @创建时间 2020/5/3
 * @描述
 */
public class ShaCoder {

    /**
     * sha1加密
     * @param data
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String sha1(String data) throws NoSuchAlgorithmException {
        //加盐   更安全一些
        data += "King";
        //信息摘要器                                算法名称
        MessageDigest md = MessageDigest.getInstance("SHA1");
        //把字符串转为字节数组
        byte[] b = data.getBytes();
        //使用指定的字节来更新我们的摘要
        md.update(b);
        //获取密文  （完成摘要计算）
        byte[] b2 = md.digest();
        //获取计算的长度
        int len = b2.length;
        //16进制字符串
        String str = "0123456789abcdef";
        //把字符串转为字符串数组
        char[] ch = str.toCharArray();

        //创建一个40位长度的字节数组
        char[] chs = new char[len*2];
        //循环20次
        for(int i=0,k=0;i<len;i++) {
            byte b3 = b2[i];//获取摘要计算后的字节数组中的每个字节
            // >>>:无符号右移
            // &:按位与
            //0xf:0-15的数字
            chs[k++] = ch[b3 >>> 4 & 0xf];
            chs[k++] = ch[b3 & 0xf];
        }

        //字符数组转为字符串
        return new String(chs);
    }

    public static void main(String[] args) throws Exception{
        String username = "caohaoquan";//用户名
        int rand = (int)((Math.random() * 9 + 1) * 1000);//随机数
        long curTime = System.currentTimeMillis();//当前时间
        StringBuffer buff = new StringBuffer();
        buff.append(username).append(rand).append(curTime);
        System.out.println(buff.toString());
        String coder = ShaCoder.sha1(buff.toString());
        System.out.println(coder);

        Map<String, Object> keyMap = RSACoder.initKey();

        //公钥
        byte[] publicKey = RSACoder.getPublicKey(keyMap);

        //私钥
        byte[] privateKey = RSACoder.getPrivateKey(keyMap);

        byte[] coder2 = RSACoder.encryptByPublicKey(coder.getBytes(),publicKey);
        System.out.println("appSecret：" + Base64.encodeBase64String(coder2));

        byte[] coder3 = RSACoder.decryptByPrivateKey(coder2,privateKey);
        System.out.println("appKey：" + new String(coder3));
    }

}
