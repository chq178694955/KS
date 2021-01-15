package com.king.keke.statis;

import com.king.framework.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class VoteContext {

    /**
     * 利用spring发现机制注入bean
     */
    @Autowired
    private final Map<String,VoteStrategy> strategies = new ConcurrentHashMap<>();

    public VoteContext(Map<String,VoteStrategy> strategiesMap){
        this.strategies.clear();
        strategiesMap.forEach((k,v)->this.strategies.put(k,v));
    }

    public VoteStrategy getStrategy(Integer ruleId){
        VoteStrategy voteStrategy = this.strategies.get(VoteStrategyEnum.fromRule(ruleId));
        if(voteStrategy == null){
            throw new BusinessException("暂无统计策略");
        }
        return voteStrategy;
    }

}
