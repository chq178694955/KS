package com.king.app.webapp.init;

import com.king.system.cache.DictCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @创建人 chq
 * @创建时间 2020/4/9
 * @描述
 */
@Component
public class InitApplicationRun implements ApplicationRunner {

    private final Logger logger = LoggerFactory.getLogger(InitApplicationRun.class);

    @Autowired
    private DictCache dictCache;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("King System already started ...");
        dictCache.refresh();
        logger.info("Initialization data complete ...");
    }

}
