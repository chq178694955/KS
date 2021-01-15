package com.king.keke.statis;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.king.keke.entity.*;
import com.king.keke.service.IResidentInfoService;
import com.king.keke.service.IResidentVoteService;
import com.king.keke.service.IVoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

/**
 * 票权面积从多统计
 */
@Component("voteWeightAreaManyStrategy")
public class VoteWeightAreaManyStrategy implements VoteStrategy{

    private final static Logger logger = LoggerFactory.getLogger(VoteWeightAreaManyStrategy.class);

    @Autowired
    private IVoteService voteService;
    @Autowired
    private IResidentVoteService residentVoteService;
    @Autowired
    private IResidentInfoService residentInfoService;

    private Integer total = 560;//总票数
    private BigDecimal totalArea = new BigDecimal(49918.26);//总面积

    @Override
    public JSONObject vote(Long voteId) {
        logger.info("票权面积从多统计");
        JSONObject result = new JSONObject();//用于存放计算结果，用于前端展示

        VoteInfo vote = voteService.findVoteById(voteId);
        List<VoteOption> options = voteService.findOption(voteId);
        options.forEach(o->o.setItems(voteService.findOptionItem(o.getId())));
        //List<ResidentInfo> repeatResidents = residentInfoService.findRepeat();
        List<ResidentInfo> residents = residentInfoService.findAll();
        //List<ResidentInfo> buildings = residentInfoService.findBuildings();
        List<ResidentVote> votes = residentVoteService.findByVote(voteId);

        logger.info("====="+vote.getTitle()+"=====");
        result.put("title",vote.getTitle());
        JSONArray datas = new JSONArray();
        //汇总
        options.forEach((o)->{
            logger.info("***"+o.getName()+"***");
            JSONObject jsonOption = new JSONObject();
            jsonOption.put("name",o.getName());
            JSONArray jsonItems = new JSONArray();

            List<VoteOptionItem> items = o.getItems();
            //初始化每个选项0票
            Map<String,Integer> ticketMap = items.stream().collect(Collectors.toMap(
                    k-> voteId + "_" + o.getId() + "_" + k.getId()
                    ,v->0));
            Map<String,BigDecimal> areaMap = items.stream().collect(Collectors.toMap(
                    k-> voteId + "_" + o.getId() + "_" + k.getId()
                    ,v->new BigDecimal(0)));

            votes.forEach(v->{
                String keyPrefix = v.getVoteId() + "_" + v.getVoteOptionId();
                String[] aryIds = v.getItemIds().split(",");
                for(String s : aryIds){
                    String key = keyPrefix + "_" + s;
                    if(ticketMap.containsKey(key)){
                        Integer count = ticketMap.get(key);
                        count = count + 1;
                        ticketMap.put(key,count);
                    }
                    if(areaMap.containsKey(key)){
                        Optional<ResidentInfo> ri = residents.stream().filter(r->r.getId().equals(v.getResidentId())).findFirst();
                        BigDecimal curArea = ri.get().getArea();
                        BigDecimal area = areaMap.get(key);
                        areaMap.put(key,area.add(curArea));
                    }
                }
            });
            //采用未投从多，先计算出已投总票数
            AtomicInteger hasCount = new AtomicInteger(0);
            ticketMap.forEach((k,v)->{
                hasCount.set(hasCount.get() + v);
            });
            //采用未投从多，先计算出已投总面积
            AtomicReference<BigDecimal> hasArea = new AtomicReference(new BigDecimal(0));
            areaMap.forEach((k,v)->{
                hasArea.set(hasArea.get().add(v));
            });
            ticketMap.forEach((k,v)->{
                Optional<VoteOptionItem> param = items.stream().filter(item->(k.equals(voteId + "_" + o.getId() + "_" + item.getId()))).findFirst();
                if(param.get() != null){
                    VoteOptionItem item = (VoteOptionItem)param.get();
                    String rate = new BigDecimal(v + (total - hasCount.get())).divide(new BigDecimal(total),BigDecimal.ROUND_HALF_UP,4).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP).toString() + "%";
                    logger.info("###"+item.getName()+"###，票数：" + v + "，票数比例：" + rate);

                    //面积
                    BigDecimal _area = areaMap.get(k);
                    _area = _area.add(totalArea.subtract(hasArea.get()));
                    String area = _area.divide(totalArea,BigDecimal.ROUND_HALF_UP,4).multiply(new BigDecimal(100)).setScale(2,BigDecimal.ROUND_HALF_UP).toString() + "%";
                    logger.info("###"+item.getName()+"###，面积比例：" + area);

                    JSONObject jsonItem = new JSONObject();
                    jsonItem.put("name",item.getName());
                    jsonItem.put("ticket",v);
                    jsonItem.put("rate",rate);
                    jsonItem.put("area",area);
                    jsonItems.add(jsonItem);
                }
            });
            jsonOption.put("items",jsonItems);
            datas.add(jsonOption);
        });
        result.put("options",datas);
        return result;
    }
}
