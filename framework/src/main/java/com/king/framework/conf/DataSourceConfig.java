package com.king.framework.conf;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import com.king.framework.ds.*;
import com.king.framework.utils.I18nUtils;
import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.scripting.xmltags.DynamicContext;
import org.apache.ibatis.scripting.xmltags.DynamicSqlSource;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.CollectionUtils;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * 数据源配置
 * 1.druid数据源
 * 2.事务
 * 3.分页
 */
@Configuration
public class DataSourceConfig {

    //@Value("${spring.datasource.url}")
    private String dbUrl;
    //@Value("${spring.datasource.username}")
    private String username;
    //@Value("${spring.datasource.password}")
    private String password;
    //@Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
    @Value("${spring.datasource.initialSize}")
    private int initialSize;
    @Value("${spring.datasource.minIdle}")
    private int minIdle;
    @Value("${spring.datasource.maxActive}")
    private int maxActive;
    @Value("${spring.datasource.maxWait}")
    private int maxWait;
    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;
    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;
    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;
    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${spring.datasource.testOnReturn}")
    private boolean testOnReturn;
    @Value("${spring.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;
    @Value("${spring.datasource.filters}")
    private String filters;
    @Value("${spring.datasource.connectionProperties}")
    private String connectionProperties;
    @Value("${spring.datasource.useGlobalDataSourceStat}")
    private boolean useGlobalDataSourceStat;

    @Value(("${mybatis.mapper-locations}"))
    private String mapperLocations;

    @Autowired
    private DataSourceProps dataSourceProps;

    @Bean(name = "dynamicDataSource")
    @Primary
    public DataSource dynamicDataSource(){
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        //配置多数据源
        Map<Object,Object> dataSourceMap = new HashMap<>();
        if(!CollectionUtils.isEmpty(dataSourceProps.getAttrs())){
            for(DataSourceAttr attr : dataSourceProps.getAttrs()){
                DataSource ds = initilDataSourceAttr(attr);
                DataSourceType type = DataSourceType.getType(attr.getName());
                dataSourceMap.put(type,ds);
            }
        }
        //设置默认数据源
        //dynamicDataSource.setDefaultTargetDataSource(dataSourceMap.get(DataSourceContextHolder.getDataSource()));
        dynamicDataSource.setTargetDataSources(dataSourceMap);
        return dynamicDataSource;
    }
    private DataSource initilDataSourceAttr(DataSourceAttr attr){
        DruidDataSource ds = new DruidDataSource();
        ds.setName(attr.getName());
        ds.setDriverClassName(attr.getDriverClassName());
        ds.setUrl(attr.getUrl());
        ds.setUsername(attr.getUsername());
        ds.setPassword(attr.getPassword());
        //其他配置项
        if(attr.getInitialSize() > 0){
            ds.setInitialSize(attr.getInitialSize());
        }else{
            ds.setInitialSize(initialSize);
        }
        if(attr.getMaxActive() > 0){
            ds.setMaxActive(attr.getMaxActive());
        }else{
            ds.setMaxActive(maxActive);
        }
        if(attr.getMinIdle() > 0){
            ds.setMinIdle(attr.getMinIdle());
        }else{
            ds.setMinIdle(minIdle);
        }

        ds.setMaxWait(maxWait);
        ds.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        ds.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        ds.setValidationQuery(validationQuery);
        ds.setTestWhileIdle(testWhileIdle);
        ds.setTestOnBorrow(testOnBorrow);
        ds.setTestOnReturn(testOnReturn);
        ds.setPoolPreparedStatements(poolPreparedStatements);
        ds.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        ds.setUseGlobalDataSourceStat(useGlobalDataSourceStat);
        try {
            ds.setFilters(filters);
        } catch (SQLException e) {
            System.err.println("druid configuration initialization filter: "+ e);
        }
        ds.setConnectionProperties(connectionProperties);
        return ds;
    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager transactionManager(@Autowired @Qualifier("dynamicDataSource")DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper = new PageHelper();
        Properties props = new Properties();
        props.setProperty("offsetAsPageNum","true");
        props.setProperty("rowBoundsWithCount","true");
        props.setProperty("reasonable","true");
        props.setProperty("dialect","mysql");
        pageHelper.setProperties(props);
        return pageHelper;
    }

    class KsSqlSource extends DynamicSqlSource{
        private org.apache.ibatis.session.Configuration configuration;
        private SqlNode rootSqlNode;

        public KsSqlSource(org.apache.ibatis.session.Configuration configuration, SqlNode rootSqlNode) {
            super(configuration, rootSqlNode);
            this.configuration = configuration;
            this.rootSqlNode = rootSqlNode;
        }

        public BoundSql getBoundSql(Object parameterObject) {
            DynamicContext context = new DynamicContext(this.configuration, parameterObject);
            context.getBindings().put("locale", I18nUtils.getLocale().toString());
            this.rootSqlNode.apply(context);
            SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(this.configuration);
            Class<?> parameterType = parameterObject == null ? Object.class : parameterObject.getClass();
            SqlSource sqlSource = sqlSourceParser.parse(context.getSql(), parameterType, context.getBindings());
            BoundSql boundSql = sqlSource.getBoundSql(parameterObject);
            Iterator var7 = context.getBindings().entrySet().iterator();

            while(var7.hasNext()) {
                Map.Entry<String, Object> entry = (Map.Entry)var7.next();
                boundSql.setAdditionalParameter((String)entry.getKey(), entry.getValue());
            }

            return boundSql;
        }
    }

    @Intercepts({@Signature(
            type = Executor.class,
            method = "query",
            args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
    ), @Signature(
            type = Executor.class,
            method = "query",
            args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}
    )})
    class MyBatisParamsInterceptor implements Interceptor{
        public Object intercept(Invocation invocation) throws Throwable {
            try {
                Object[] args = invocation.getArgs();
                MappedStatement ms = (MappedStatement)args[0];
                SqlSource sqlSource = ms.getSqlSource();
                if (sqlSource instanceof DynamicSqlSource) {
                    KsSqlSource ksSqlSource = new KsSqlSource(ms.getConfiguration(), this.getSqlNode((DynamicSqlSource)sqlSource));
                    this.setSource(ms, ksSqlSource);
                }
            } catch (Exception var6) {
                var6.printStackTrace();
            }

            return invocation.proceed();
        }

        public SqlNode getSqlNode(DynamicSqlSource sqlSource) {
            try {
                Field field = DynamicSqlSource.class.getDeclaredField("rootSqlNode");
                field.setAccessible(true);
                SqlNode sqlnode = (SqlNode)field.get(sqlSource);
                return sqlnode;
            } catch (Exception var4) {
                var4.printStackTrace();
                return null;
            }
        }

        public void setSource(MappedStatement ms, SqlSource sqlSource) {
            try {
                Field field = MappedStatement.class.getDeclaredField("sqlSource");
                field.setAccessible(true);
                field.set(ms, sqlSource);
            } catch (SecurityException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException var4) {
                var4.printStackTrace();
            }

        }

        public Object plugin(Object target) {
            return Plugin.wrap(target, this);
        }

        public void setProperties(Properties properties) {
        }
    }

    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Autowired @Qualifier("dynamicDataSource")DataSource dataSource)
        throws Exception{
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPlugins(new Interceptor[]{pageHelper(),new MyBatisParamsInterceptor()});
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(mapperLocations));
        return sessionFactory.getObject();
    }

}
