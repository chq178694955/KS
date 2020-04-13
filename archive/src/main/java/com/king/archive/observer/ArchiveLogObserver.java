package com.king.archive.observer;

import com.king.archive.dto.ArchiveDto;
import com.king.archive.dto.ArchiveEventDto;
import com.king.archive.dto.ArchiveType;
import com.king.archive.model.ArchiveDeleteNotifier;
import com.king.archive.model.ArchiveInsertNotifier;
import com.king.archive.model.ArchiveNotifier;
import com.king.archive.model.ArchiveUpdateNotifier;
import com.king.archive.service.IArchiveMgrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 档案日志观察者
 * @创建人 chq
 * @创建时间 2020/3/20
 * @描述
 */
@Component
public class ArchiveLogObserver implements ArchiveObserver {

    private Logger logger = LoggerFactory.getLogger(ArchiveLogObserver.class);

    @Autowired
    private IArchiveMgrService<ArchiveDto> archiveEventMgrService;

    /**
     * 可操作的档案范围
     */
    private ArchiveType[] ranges = {
        ArchiveType.METER,
        ArchiveType.TERMINAL
    };

    @Override
    public boolean pass(Object arg) {
        if(arg instanceof ArchiveNotifier){
            ArchiveNotifier notifier = (ArchiveNotifier)arg;
            ArchiveDto dto = notifier.getDto();
            ArchiveType archiveType = dto.getArchiveType();
            Set<ArchiveType> sets = new HashSet<ArchiveType>(Arrays.asList(ranges));
            if(!sets.contains(archiveType)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(!pass(arg)){
            ArchiveEventDto event = null;
            if(arg instanceof ArchiveInsertNotifier){
                ArchiveInsertNotifier notifier = (ArchiveInsertNotifier)arg;
                Long userId = notifier.getUserId();
                event = createEvent(1,userId,notifier.getDto(),null);
            }
            if (arg instanceof ArchiveUpdateNotifier) {
                ArchiveUpdateNotifier notifier = (ArchiveUpdateNotifier) arg;
                Long userId = notifier.getUserId();
                event = createEvent(2, userId, notifier.getDto(), notifier.getAfter());
            }
            if (arg instanceof ArchiveDeleteNotifier) {
                ArchiveDeleteNotifier notifier = (ArchiveDeleteNotifier) arg;
                Long userId = notifier.getUserId();
                event = createEvent(3, userId, notifier.getDto(), null);
            }
            if (event != null) {
                archiveEventMgrService.insert(event.getOperator(), event);
            }
        }
    }

    private ArchiveEventDto createEvent(Integer operatorType,Long userId,ArchiveDto dto,ArchiveDto after){
        ArchiveEventDto event  = null;
        logger.info("这里执行档案日志操作");
        return event;
    }

    /*
    //这里可以根据档案类型将档案信息存入数据库，然后写入日志的时候可以获取，日志更加明白
    private Map<String, String> getCompareFields(ArchiveType archType) {
        Map<String,String> map = new HashMap<String,String>();
        List<SysWebFieldInfo> fields = WebTableCache.getWebFieldInfo(Long.valueOf(archType.getValue()));
        if (fields != null) {
            String webFieldname = null;
            for(SysWebFieldInfo field : fields){
                webFieldname = ArchiveProfileHelper.underlineToCamel(field.getFieldname());
                if (field.getUseFlag() == 1) {
                    map.put(webFieldname, field.getFielddesc());
                }
            }
        }
        return map;
    }

    private String getOperateTypeDesc(ArchiveType archType, Integer operateType) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(getOperateTypeName(operateType));
        buffer.append(" ");
        SysWebTableInfo tableInfo = WebTableCache.getWebTableInfo(Long.valueOf(archType.getValue()));
        if (tableInfo != null) {
            buffer.append(tableInfo.getTabledesc());
        }
        return buffer.toString();
    }

    private String getOperateTypeName(Integer operateType) {
        SysDict dict = DictCache.getDict(String.valueOf(DictConst.DIC_ARCH_OPERATE),
                String.valueOf(operateType));
        return dict == null ? ""
                : dict.getDictDesc();
    }
    */
}
