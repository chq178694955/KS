package com.king.system.conf;

import com.king.system.shiro.CustomerShiroRealm;
import com.king.system.shiro.RetryLimitHashedCredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置
 * @创建人 chq
 * @创建时间 2020/3/29
 * @描述
 */
@Configuration
public class ShiroConfig {

    private static final String MD5 = "md5";

    @Value("${spring.redis.host}")
    public String redisHost;
    @Value("${spring.redis.port}")
    public Integer redisPort;

    @Bean(name="hashedCredMatcher")
    public HashedCredentialsMatcher createHashCredentialsMatcher() {
        RetryLimitHashedCredentialsMatcher hcm = new RetryLimitHashedCredentialsMatcher();
        hcm.setHashAlgorithmName(MD5);
        hcm.setStoredCredentialsHexEncoded(true);
        return hcm;
    }

    @Bean
    public CustomerShiroRealm realm(){
        CustomerShiroRealm myrealm = new CustomerShiroRealm();
        myrealm.setCredentialsMatcher(createHashCredentialsMatcher());
        return myrealm;
    }

    @Bean
    public RedisManager redisManager(){
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(redisHost);
        redisManager.setPort(redisPort);
        redisManager.setExpire(1800);// 配置缓存过期时间
        redisManager.setTimeout(0);
        //redisManager.setPassword();//为空可以不配置
        return redisManager;
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    @Bean
    public CacheManager cacheManager(){
        //内存缓存管理器
        //return new MemoryConstrainedCacheManager();

        //redis缓存管理器
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    @Bean
    public SessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setGlobalSessionTimeout(1800000);
        sessionManager.setDeleteInvalidSessions(true);
        sessionManager.setCacheManager(cacheManager());
        sessionManager.setSessionDAO(redisSessionDAO());
        sessionManager.setSessionIdCookieEnabled(true);
//        sessionManager.setSessionIdCookie(rememberMeCookie());
        sessionManager.setSessionIdUrlRewritingEnabled(false);//隐藏jsessionid
        return sessionManager;
    }

    /**
     * rememberMe cookie 效果是重开浏览器后无需重新登录
     * @return SimpleCookie
     */
    private SimpleCookie rememberMeCookie() {
        // 设置 cookie 名称，对应 login.html 页面的 <input type="checkbox" name="rememberMe"/>
        SimpleCookie cookie = new SimpleCookie("rememberMe");
        // cookie.setSecure(true); // 只在 https中有效 注释掉 正常
        // 设置 cookie 的过期时间，单位为秒，这里为一天
        cookie.setMaxAge(2000);
        return cookie;
    }

    /**
     * cookie管理对象
     *
     * @return CookieRememberMeManager
     */
    private CookieRememberMeManager rememberMeManager() {
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        // rememberMe cookie 加密的密钥
        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
        return cookieRememberMeManager;
    }

    @Bean(name="securityManager")
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm());
        securityManager.setRememberMeManager(rememberMeManager());
        securityManager.setCacheManager(cacheManager());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    @Bean(name="shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设置SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        //设置登录url
        shiroFilterFactoryBean.setLoginUrl("/login");
        //设置登录成功跳转url
        shiroFilterFactoryBean.setSuccessUrl("/index");
        //设置未授权403页面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        LinkedHashMap<String,String> filterChianMap = new LinkedHashMap<>();
        //设置免验证路径
        filterChianMap.put("/static/**","anon");
        filterChianMap.put("/api/**","anon");//http接口放行
        filterChianMap.put("/403","anon");
        filterChianMap.put("/404","anon");
        filterChianMap.put("/500","anon");
        //出以上url外，其余url必须通过认证才可以访问
        filterChianMap.put("/**","user");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChianMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 配置shiro的生命周期
     * 使用springboot整合shiro时，@value注解无法读取application.yml中的配置
     * 解决方法:将LifecycleBeanPostProcessor的配置方法改成静态的就可以了
     * @return
     */
    @Bean
    public static LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 配置shiro注解是否生效
     * 启动Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator和AuthorizationAttributeSourceAdvisor)即可实现此功能
     * @return
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor sourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        sourceAdvisor.setSecurityManager(securityManager);
        return sourceAdvisor;
    }

}
