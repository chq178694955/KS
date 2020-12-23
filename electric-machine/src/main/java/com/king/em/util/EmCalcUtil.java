package com.king.em.util;

import com.king.em.entity.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @创建人 chq
 * @创建时间 2020/11/26
 * @描述
 */
public class EmCalcUtil {

    /**
     * 求出稳态转速
     * 用户提供的最后一个转速
     * @param stepList
     * @return
     */
    public static BigDecimal getSteadySpeed(List<Experiment> stepList){
        Experiment lastStep = stepList.stream().reduce((first,second)->second).orElse(null);
        return lastStep != null ? ((EmDataStep)lastStep).getSpeed() : null;
    }

    /**
     * 求出调整时间
     * 1.list倒序
     * 2.循环list
     * 3.循环索引对象的转速 >= 稳态转速 * 1.02 || 转速 <= 稳态转速 * 0.98
     * 4.所返回对应的时间为 调整时间
     * @param stepList
     * @param steadySpeed
     * @return
     */
    public static BigDecimal getAdjustTime(List<Experiment> stepList,BigDecimal steadySpeed){
        Collections.reverse(stepList);
        Optional<Experiment> findFirst = stepList.stream().filter(e -> ( ((EmDataStep)e).getSpeed().compareTo(steadySpeed.multiply(new BigDecimal(1.02))) > -1 || ((EmDataStep)e).getSpeed().compareTo(steadySpeed.multiply(new BigDecimal(0.98))) < 1 )).findFirst();
        return findFirst.get() == null ? null : ((EmDataStep)findFirst.get()).getDataTime().setScale(BigDecimal.ROUND_HALF_UP,2);
    }

    /**
     * 获取峰值对象
     * 最大转速对应的时间即为峰值时间
     * @param stepList
     * @return
     */
    public static EmDataStep getPeakTime(List<Experiment> stepList){
        Experiment e = stepList.stream().reduce((first,second)->( ((EmDataStep)first).getSpeed().compareTo(((EmDataStep)second).getSpeed()) > 0 ? first : second )).orElse(null);
        return e == null ? null : ((EmDataStep)e);
    }

    /**
     * 获取超调量
     * （最大转速-稳态转速）/稳态转速*100%
     * @param maxSpeed
     * @param steadySpeed
     * @return
     */
    public static BigDecimal getOvershoot(BigDecimal maxSpeed,BigDecimal steadySpeed){
        return maxSpeed.subtract(steadySpeed).divide(steadySpeed,BigDecimal.ROUND_HALF_UP,4).multiply(new BigDecimal(100));
    }

    /**
     * 转速调整率
     * 1.将正弦实验转速求和的平均值，该值已经处理了，只取前10条记录
     * 2.求和后的值 - 额定转速 的 绝对值
     * 3.再除以额定转速
     * @param sinList2
     * @param fixedSpeed
     * @return
     */
    public static BigDecimal getSpeedAdjustRatio(List<Experiment> sinList2, BigDecimal fixedSpeed) {
        List<EmDataSin> sins = sinList2.stream().map(e->(EmDataSin)e).collect(Collectors.toList());
        BigDecimal total = sins.stream().map(EmDataSin::getSpeed10).reduce(BigDecimal.ZERO,BigDecimal::add);
        return total.divide(new BigDecimal(sins.size())).subtract(fixedSpeed).abs().divide(fixedSpeed,BigDecimal.ROUND_HALF_UP,4).multiply(new BigDecimal(100));
    }

    /**
     * 获取转速变化率
     * （空载-额定负载）/空载
     * @param overload
     * @return
     */
    public static BigDecimal getSpeedChangeRatio(EmDataOverload overload){
        return overload.getSpeedEmpty().subtract(overload.getSpeedFixedLoad()).divide(overload.getSpeedEmpty(),BigDecimal.ROUND_HALF_UP,4).multiply(new BigDecimal(100));
    }

    /**
     * 调速范围
     * max/min
     * @param maxSpeed
     * @param minSpeed
     * @return
     */
    public static BigDecimal getSpeedRange(BigDecimal maxSpeed,BigDecimal minSpeed){
        return maxSpeed.divide(minSpeed,BigDecimal.ROUND_HALF_UP,2);
    }

    /**
     * 正反转速差率
     * 1.对正、反向转速求和
     * 2.正-反的绝对值 除以 正+反 * 100%
     * @param emptyloadList
     * @return
     */
    public static BigDecimal getForwardReverseSpeedDiff(List<Experiment> emptyloadList) {
        List<EmDataEmptyload> emptyloads = emptyloadList.stream().map(e-> (EmDataEmptyload)e).collect(Collectors.toList());
        BigDecimal totalForward = emptyloads.stream().map(EmDataEmptyload::getSpeedForward).reduce(BigDecimal.ZERO,BigDecimal::add);
        BigDecimal totalReverse = emptyloads.stream().map(EmDataEmptyload::getSpeedReverse).reduce(BigDecimal.ZERO,BigDecimal::add);
        return totalForward.subtract(totalReverse).abs().divide(totalForward.add(totalReverse),BigDecimal.ROUND_HALF_UP,4).multiply(new BigDecimal(100));
    }


    /**
     * 转速平均误差
     * 1.循环恒定负载数据
     * 2.每个转速值 - 转速设定值 的 绝对值 再进行求和
     * 3.求和的值再除以100
     * @param constantloadList2
     * @param speedSetter
     * @return
     */
    public static BigDecimal getSpeedAvgError(List<Experiment> constantloadList2, BigDecimal speedSetter) {
        List<EmDataConstantload> constantloads = constantloadList2.stream().map(e->(EmDataConstantload)e).collect(Collectors.toList());
        BigDecimal total = constantloads.stream().map(e-> e.getSpeed100().subtract(speedSetter).abs()).reduce(BigDecimal.ZERO,BigDecimal::add);
        return total.divide(new BigDecimal(100),BigDecimal.ROUND_HALF_UP,4);
    }

    /**
     * 转速平均误差
     * 1.循环恒定负载数据
     * 2.每个转矩值 - 负载转矩 的 绝对值 再进行求和
     * 3.求和的值再除以100
     * @param constantloadList2
     * @param torqueOverload
     * @return
     */
    public static BigDecimal getTorqueAvgError(List<Experiment> constantloadList2, BigDecimal torqueOverload) {
        List<EmDataConstantload> constantloads = constantloadList2.stream().map(e->(EmDataConstantload)e).collect(Collectors.toList());
        BigDecimal total = constantloads.stream().map(e-> e.getTorque100().subtract(torqueOverload).abs()).reduce(BigDecimal.ZERO,BigDecimal::add);
        return total.divide(new BigDecimal(100),BigDecimal.ROUND_HALF_UP,4);
    }

    /**
     * 转矩波动系数
     * （最大 - 最小）/（最大 + 最小）
     * @param constantloadList2
     * @return
     */
    public static BigDecimal getTorqueCoeff(List<Experiment> constantloadList2) {
        List<EmDataConstantload> constantloads = constantloadList2.stream().map(e->(EmDataConstantload)e).collect(Collectors.toList());
        EmDataConstantload maxDto = constantloads.stream().reduce((first,second)->( first.getTorque100().compareTo(second.getTorque100()) > 0 ? first : second )).orElse(null);
        EmDataConstantload minDto = constantloads.stream().reduce((first,second)->( first.getTorque100().compareTo(second.getTorque100()) < 0 ? first : second )).orElse(null);
        BigDecimal max = maxDto.getTorque100();
        BigDecimal min = minDto.getTorque100();
        return max.subtract(min).divide(max.add(min),BigDecimal.ROUND_HALF_UP,4).multiply(new BigDecimal(100));
    }

    /**
     * 转速波动系数
     * （最大 - 最小）/（最大 + 最小）
     * @param constantloadList2
     * @return
     */
    public static BigDecimal getSpeedCoeff(List<Experiment> constantloadList2) {
        List<EmDataConstantload> constantloads = constantloadList2.stream().map(e->(EmDataConstantload)e).collect(Collectors.toList());
        EmDataConstantload maxDto = constantloads.stream().reduce((first,second)->( first.getSpeed100().compareTo(second.getSpeed100()) > 0 ? first : second )).orElse(null);
        EmDataConstantload minDto = constantloads.stream().reduce((first,second)->( first.getSpeed100().compareTo(second.getSpeed100()) < 0 ? first : second )).orElse(null);
        BigDecimal max = maxDto.getSpeed100();
        BigDecimal min = minDto.getSpeed100();
        return max.subtract(min).divide(max.add(min),BigDecimal.ROUND_HALF_UP,4).multiply(new BigDecimal(100));
    }

    /**
     * 功率密度
     * 1.	1 /9550*额定转速*额定转矩 = 功率
     * 2.   功率 / 电机质量 = 功率密度
     * @param fixedSpeed
     * @param fixedTorque
     * @param machineWeight
     * @return
     */
    public static BigDecimal getPowerDensity(BigDecimal fixedSpeed, BigDecimal fixedTorque, BigDecimal machineWeight) {
        BigDecimal power = new BigDecimal(1).divide(new BigDecimal(9550),BigDecimal.ROUND_HALF_UP,4).multiply(fixedSpeed).multiply(fixedTorque);
        return power.divide(machineWeight,BigDecimal.ROUND_HALF_UP,4);
    }

    /**
     * 综合评级
     * I >= 0.8 好
     * 0.8 > I >= 0.7 较好
     * 0.7 > I >= 0.6 中
     * 0.6 > I >= 0.45 较差
     * 0.45 > I 差
     * @param degree
     * @return
     */
    public static String getComprehensiveEvaluation(BigDecimal degree) {
        if(degree.compareTo(new BigDecimal(0.8)) >= 0){
            return "好";
        }else if(degree.compareTo(new BigDecimal(0.8)) < 0 && degree.compareTo(new BigDecimal(0.7)) >= 0){
            return "较好";
        }else if(degree.compareTo(new BigDecimal(0.7)) < 0 && degree.compareTo(new BigDecimal(0.6)) >= 0){
            return "中";
        }else if(degree.compareTo(new BigDecimal(0.6)) < 0 && degree.compareTo(new BigDecimal(0.45)) >= 0){
            return "较差";
        }else {
            return "差";
        }
    }

    /**
     * 分类评级
     * I >= 0.3 好
     * 0.3 > I >= 0.25 较好
     * 0.25 > I >= 0.2 中
     * 0.2 > I >= 0.15 较差
     * 0.15 > I 差
     * @param degree
     * @return
     */
    public static String getCategoryEvaluation(BigDecimal degree) {
        if(degree.compareTo(new BigDecimal(0.3)) >= 0){
            return "好";
        }else if(degree.compareTo(new BigDecimal(0.3)) < 0 && degree.compareTo(new BigDecimal(0.25)) >= 0){
            return "较好";
        }else if(degree.compareTo(new BigDecimal(0.25)) < 0 && degree.compareTo(new BigDecimal(0.2)) >= 0){
            return "中";
        }else if(degree.compareTo(new BigDecimal(0.2)) < 0 && degree.compareTo(new BigDecimal(0.15)) >= 0){
            return "较差";
        }else {
            return "差";
        }
    }
}
