package com.king.archive.factory;

import com.king.archive.dto.ArchiveDto;
import com.king.archive.dto.ArchiveType;
import com.king.archive.service.IArchiveMgrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 档案服务工厂类
 * @创建人 chq
 * @创建时间 2020/3/20
 * @描述
 */
@Component
public class ArchiveServiceFactory implements ApplicationListener<ContextRefreshedEvent> {

    private static Map<Integer, IArchiveMgrService<ArchiveDto>> serviceMap = new ConcurrentHashMap<>();

    private static ArchiveServiceFactory instance;

    private ArchiveServiceFactory(){}

    public static ArchiveServiceFactory getInstance(){
        if(null == instance){
            synchronized (ArchiveServiceFactory.class){
                if(null == instance){
                    instance = new ArchiveServiceFactory();
                }
            }
        }
        return instance;
    }

    @Autowired
    private List<IArchiveMgrService<ArchiveDto>> services;

    public IArchiveMgrService<ArchiveDto> getService(int archiveType){
        return serviceMap.get(archiveType);
    }

    public IArchiveMgrService<ArchiveDto> getService(ArchiveType archiveType){
        return archiveType == null ? null : getService(archiveType.getValue());
    }

    public List<IArchiveMgrService<ArchiveDto>> getServices(){
        List<IArchiveMgrService<ArchiveDto>> services = new ArrayList<>();
        for(Map.Entry<Integer,IArchiveMgrService<ArchiveDto>> entry : serviceMap.entrySet()){
            services.add(entry.getValue());
        }
        return services;
    }

    public void setServices(List<IArchiveMgrService<ArchiveDto>> services){
        this.services = services;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(contextRefreshedEvent.getApplicationContext().getParent() == null){
            if(services != null){
                for(IArchiveMgrService<ArchiveDto> service : services){
                    if(service.getArchiveType() != null){
                        if(service.getArchiveType().getValue() != ArchiveType.UNKONW.getValue()){
                            serviceMap.put(service.getArchiveType().getValue(),service);
                        }
                    }
                }
            }
        }
    }
}
