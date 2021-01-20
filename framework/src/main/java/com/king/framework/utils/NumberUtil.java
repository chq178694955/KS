package com.king.framework.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @创建人 chq
 * @创建时间 2020/5/8
 * @描述
 */
public class NumberUtil {

    // 大写数字
    private static final String[] NUMBERS = {"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
    // 整数部分的单位
    private static final String[] IUNIT = {"元","拾","佰","仟","万","拾","佰","仟","亿","拾","佰","仟","万","拾","佰","仟"};
    // 小数部分的单位
    private static final String[] DUNIT = {"角","分","厘"};


    /**
     *  转换为大写的中文金额
     * @param str 字符串类型的 金额数字
     * @return
     */
    public static String toChinese(String str) {
        // 判断输入的金额字符串是否符合要求
        if (str == null || str.equals("") || !str.matches("(-)?[\\d]*(.)?[\\d]*")) {
            return "抱歉，请输入数字！";
        }

        if("0".equals(str) || "0.00".equals(str) || "0.0".equals(str)) {
            return "零元";
        }

        // 判断金额数字中是否存在负号"-"
        boolean flag = false;
        if(str.startsWith("-")){
            // 标志位，标志此金额数字为负数
            flag = true;
            str = str.replaceAll("-", "");
        }

        // 去掉金额数字中的逗号","
        str = str.replaceAll(",", "");
        String integerStr;//整数部分数字
        String decimalStr;//小数部分数字

        // 初始化：分离整数部分和小数部分
        if(str.indexOf(".")>0) {
            integerStr = str.substring(0,str.indexOf("."));
            decimalStr = str.substring(str.indexOf(".") + 1);
        }else if(str.indexOf(".")==0) {
            integerStr = "";
            decimalStr = str.substring(1);
        }else {
            integerStr = str;
            decimalStr = "";
        }

        // beyond超出计算能力，直接返回
        if(integerStr.length()>IUNIT.length) {
            return "超出计算能力！";
        }

        // 整数部分数字
        int[] integers = toIntArray(integerStr);
        // 判断整数部分是否存在输入012的情况
        if (integers.length>1 && integers[0] == 0) {
            return "抱歉，输入数字不符合要求！";
        }
        // 设置万单位
        boolean isWan = isWan5(integerStr);
        // 小数部分数字
        int[] decimals = toIntArray(decimalStr);
        // 返回最终的大写金额
        String result = getChineseInteger(integers, isWan) + getChineseDecimal(decimals);
        if(flag){
            // 如果是负数，加上"负"
            return "负" + result;
        }else{
            return result;
        }
    }

    /**
     *  将字符串转为int数组
     * @param number  数字
     * @return
     */
    private static int[] toIntArray(String number) {
        int[] array = new int[number.length()];
        for(int i = 0;i<number.length();i++) {
            array[i] = Integer.parseInt(number.substring(i,i+1));
        }
        return array;
    }

    /**
     *  将整数部分转为大写的金额
     * @param integers 整数部分数字
     * @param isWan  整数部分是否已经是达到【万】
     * @return
     */
    public static String getChineseInteger(int[] integers,boolean isWan) {
        StringBuffer chineseInteger = new StringBuffer("");
        int length = integers.length;
        if (length == 1 && integers[0] == 0) {
            return "";
        }
        for(int i=0; i<length; i++) {
            String key = "";
            if(integers[i] == 0) {
                if((length - i) == 13)//万（亿）
                    key = IUNIT[4];
                else if((length - i) == 9) {//亿
                    key = IUNIT[8];
                }else if((length - i) == 5 && isWan) {//万
                    key = IUNIT[4];
                }else if((length - i) == 1) {//元
                    key = IUNIT[0];
                }
                if((length - i)>1 && integers[i+1]!=0) {
                    key += NUMBERS[0];
                }
            }
            chineseInteger.append(integers[i]==0?key:(NUMBERS[integers[i]]+IUNIT[length - i -1]));
        }
        return chineseInteger.toString();
    }

    /**
     *  将小数部分转为大写的金额
     * @param decimals 小数部分的数字
     * @return
     */
    private static String getChineseDecimal(int[] decimals) {
        StringBuffer chineseDecimal = new StringBuffer("");
        for(int i = 0;i<decimals.length;i++) {
            if(i == 3) {
                break;
            }
            chineseDecimal.append(decimals[i]==0?"":(NUMBERS[decimals[i]]+DUNIT[i]));
        }
        return chineseDecimal.toString();
    }

    /**
     *  判断当前整数部分是否已经是达到【万】
     * @param integerStr  整数部分数字
     * @return
     */
    private static boolean isWan5(String integerStr) {
        int length = integerStr.length();
        if(length > 4) {
            String subInteger = "";
            if(length > 8) {
                subInteger = integerStr.substring(length- 8,length -4);
            }else {
                subInteger = integerStr.substring(0,length - 4);
            }
            return Integer.parseInt(subInteger) > 0;
        }else {
            return false;
        }
    }

    public static List<String> getCharList(String str){
        if(str == null || str.length() == 0)return new ArrayList<>();
        List<String> list = new ArrayList<>();
        for(int i=0;i<str.length();i++){
            char ch = str.charAt(i);
            list.add(new Character(ch).toString());
        }
        return list;
    }

    public static void main(String[] args) {
        String number = "12.56";
        System.out.println(number+": "+NumberUtil.toChinese(number));

        number = "1234567890563886.123";
        System.out.println(number+": "+NumberUtil.toChinese(number));

        number = "1600";
        System.out.println(number+": "+NumberUtil.toChinese(number));

        number = "156,0";
        System.out.println(number+": "+NumberUtil.toChinese(number));

        number = "-156,0";
        System.out.println(number+": "+NumberUtil.toChinese(number));

        number = "0.12";
        System.out.println(number+": "+NumberUtil.toChinese(number));

        number = "0.0";
        System.out.println(number+": "+NumberUtil.toChinese(number));

        number = "01.12";
        System.out.println(number+": "+NumberUtil.toChinese(number));

        number = "0125";
        System.out.println(number+": "+NumberUtil.toChinese(number));

        number = "-0125";
        System.out.println(number+": "+NumberUtil.toChinese(number));

        number = "sdw5655";
        System.out.println(number+": "+NumberUtil.toChinese(number));

        System.out.println(null+": "+NumberUtil.toChinese(null));
//
//        double d = 0.0007;
////        double d = 0.00012;
//        NumberFormat format = new DecimalFormat("0.#####");
//        DecimalFormat format0 = new DecimalFormat("0");
//        DecimalFormat format2 = new DecimalFormat("0.##");
//        DecimalFormat format3 = new DecimalFormat("0.###");
//        System.out.println(format.format(d));
//        System.out.println("" + d);
//        System.out.println(String.valueOf(d));
//        System.out.println((double)d);
//        BigDecimal b = new BigDecimal(0.0002);
//        System.out.println(b.setScale(4,BigDecimal.ROUND_HALF_UP));
    }

}
