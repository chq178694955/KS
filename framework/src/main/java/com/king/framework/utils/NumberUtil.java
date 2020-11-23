package com.king.framework.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @创建人 chq
 * @创建时间 2020/5/8
 * @描述
 */
public class NumberUtil {

    public static void main(String[] args) {

        double d = 0.0007;
//        double d = 0.00012;
        NumberFormat format = new DecimalFormat("0.#####");
        DecimalFormat format0 = new DecimalFormat("0");
        DecimalFormat format2 = new DecimalFormat("0.##");
        DecimalFormat format3 = new DecimalFormat("0.###");
        System.out.println(format.format(d));
        System.out.println("" + d);
        System.out.println(String.valueOf(d));
        System.out.println((double)d);
        BigDecimal b = new BigDecimal(0.0002);
        System.out.println(b.setScale(4,BigDecimal.ROUND_HALF_UP));
    }

}
