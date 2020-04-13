package com.king.archive.service.impl;

import com.king.archive.dmo.ArchiveDmo;
import com.king.archive.dto.ArchiveDto;
import com.king.archive.dto.ArchiveType;
import com.king.archive.entity.ArchiveEvent;
import com.king.archive.service.IArchiveLazyLoader;
import org.springframework.stereotype.Service;

/**
 * @创建人 chq
 * @创建时间 2020/3/20
 * @描述
 */
@Service("archiveEventMgrService")
public class ArchiveEventMgrServiceImpl extends ArchiveMgrServiceImpl<ArchiveEvent>{

    @Override
    public ArchiveType getArchiveType() {
        return ArchiveType.UNKONW;
    }

    @Override
    protected ArchiveDto entityToDto(ArchiveEvent entity, boolean isLazy) {
        return null;
    }

    @Override
    protected ArchiveEvent dtoToEntity(ArchiveDto dto) {
        return null;
    }

    @Override
    protected ArchiveDmo dtoToDmo(ArchiveDto dto) {
        return null;
    }

    @Override
    protected void validate(ArchiveDto dto) {

    }

    @Override
    protected void loadDtoProperties(ArchiveDto dto) {

    }

    @Override
    public IArchiveLazyLoader<ArchiveDto> getLazyLoader() {
        return super.getLazyLoader();
    }
}
