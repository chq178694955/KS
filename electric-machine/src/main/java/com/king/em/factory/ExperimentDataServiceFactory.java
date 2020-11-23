package com.king.em.factory;

import com.king.em.dto.ExperimentType;
import com.king.em.entity.Experiment;
import com.king.em.service.IExperimentDataMgrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @创建人 chq
 * @创建时间 2020/11/21
 * @描述
 */
@Component
public class ExperimentDataServiceFactory implements ApplicationListener<ContextRefreshedEvent> {

    private static Map<Integer, IExperimentDataMgrService<Experiment>> serviceMap = new ConcurrentHashMap<>();

    private static ExperimentDataServiceFactory instance;

    private ExperimentDataServiceFactory(){}

    public static ExperimentDataServiceFactory getInstance(){
        if(null == instance){
            synchronized (ExperimentDataServiceFactory.class){
                if(null == instance){
                    instance = new ExperimentDataServiceFactory();
                }
            }
        }
        return instance;
    }

    @Autowired
    private List<IExperimentDataMgrService<Experiment>> services;

    public IExperimentDataMgrService<Experiment> getService(int experimentType){
        return serviceMap.get(experimentType);
    }

    public IExperimentDataMgrService<Experiment> getService(ExperimentType type){
        return type == null ? null : getService(type.getVal());
    }

    public List<IExperimentDataMgrService<Experiment>> getServices(){
        List<IExperimentDataMgrService<Experiment>> services = new ArrayList<>();
        for(Map.Entry<Integer,IExperimentDataMgrService<Experiment>> entry : serviceMap.entrySet()){
            services.add(entry.getValue());
        }
        return services;
    }

    public void setServices(List<IExperimentDataMgrService<Experiment>> services){
        this.services = services;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(contextRefreshedEvent.getApplicationContext().getParent() == null){
            if(services != null){
                for(IExperimentDataMgrService<Experiment> service : services){
                    if(service.getExperimentType() != null){
                        if(service.getExperimentType().getVal() != ExperimentType.UNKNOW.getVal()){
                            serviceMap.put(service.getExperimentType().getVal(),service);
                        }
                    }
                }
            }
        }
    }
}
