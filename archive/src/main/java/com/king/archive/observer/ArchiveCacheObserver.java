package com.king.archive.observer;

import com.king.archive.cache.ArchiveCache;
import com.king.archive.dmo.ArchiveDmo;
import com.king.archive.dto.ArchiveDto;
import com.king.archive.factory.ArchiveServiceFactory;
import com.king.archive.model.ArchiveDeleteNotifier;
import com.king.archive.model.ArchiveInsertNotifier;
import com.king.archive.model.ArchiveUpdateNotifier;
import com.king.archive.service.IArchiveMgrService;
import org.springframework.stereotype.Component;

import java.util.Observable;

/**
 * @创建人 chq
 * @创建时间 2020/3/20
 * @描述
 */
@Component
public class ArchiveCacheObserver implements ArchiveObserver {

    @Override
    public boolean pass(Object arg) {
        return false;
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof ArchiveInsertNotifier) {
            ArchiveInsertNotifier notifier = (ArchiveInsertNotifier) arg;
            ArchiveDto dto = notifier.getDto();
            if (dto != null) {
                if (ArchiveCache.isCacheable(dto)) {
                    cache(dto);
                }
            }
        }
        if (arg instanceof ArchiveUpdateNotifier) {
            ArchiveUpdateNotifier notifier = (ArchiveUpdateNotifier) arg;
            ArchiveDto dto = notifier.getAfter();
            if (dto != null) {
                if (ArchiveCache.isCacheable(dto)) {
                    ArchiveCache.remove(dto.getArchiveType(), dto.getId());
                    cache(dto);
                }
            }
        }
        if (arg instanceof ArchiveDeleteNotifier) {
            ArchiveDeleteNotifier notifier = (ArchiveDeleteNotifier) arg;
            ArchiveDto dto = notifier.getDto();
            if (dto != null) {
                if (ArchiveCache.isCacheable(dto)) {
                    ArchiveCache.remove(dto.getArchiveType(), dto.getId());
                }
            }
        }
    }

    private void cache(ArchiveDto dto) {
        IArchiveMgrService<ArchiveDto> service = ArchiveServiceFactory.getInstance().getService(dto.getArchiveType());
        if (service != null) {
            ArchiveDmo dmo = service.getLazyLoader().getDmo(dto);
            if (dmo != null) {
                ArchiveCache.put(dmo);
            }
        }
    }

}
