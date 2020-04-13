package com.king.system.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 实现密码次数重试限制
 * */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    @Autowired
    private CacheManager cacheManager;

    private int maxRetryCount = 5;

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String username = (String) token.getPrincipal();
        //集群中可能会导致出现验证多过5次的现象，因为AtomicInteger只能保证单节点并发
        Cache<String, AtomicInteger> loginRetryCache = cacheManager.getCache("passwordRetryCache");
        //retry count + 1
        AtomicInteger retryCount = loginRetryCache.get(username);
        if (null == retryCount) {
            retryCount = new AtomicInteger(0);
            loginRetryCache.put(username, retryCount);
        }
        if (retryCount.incrementAndGet() > maxRetryCount) {
            throw new ExcessiveAttemptsException("username: " + username + " tried to login more than 5 times in period");
        }

        boolean matches = super.doCredentialsMatch(token, info);
        if (matches) {
            loginRetryCache.remove(username);
        }
        return matches;
    }
}
