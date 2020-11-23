package com.king.archive.service.impl;

import com.king.archive.dmo.ArchiveDmo;
import com.king.archive.dmo.MeterDmo;
import com.king.archive.dto.ArchiveDto;
import com.king.archive.dto.ArchiveType;
import com.king.archive.dto.MeterDto;
import com.king.archive.entity.Meter;
import org.springframework.stereotype.Service;

/**
 * @创建人 chq
 * @创建时间 2020/3/20
 * @描述
 */
@Service("meterMgrService")
public class MeterMgrServiceImpl extends ArchiveMgrServiceImpl<Meter> {

    private void testInsert(){
        super.insert(1L, new ArchiveDto(ArchiveType.METER), new Callback() {
            @Override
            public void execute(Object... args) {
                System.out.println("插入成功");
            }
        });
    }

    @Override
    public ArchiveType getArchiveType() {
        return ArchiveType.METER;
    }

    @Override
    protected ArchiveDto entityToDto(Meter entity, boolean isLazy) {
        MeterDto dto = null;
        if(entity != null){
            dto = new MeterDto();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
        }
        if(!isLazy){
            loadDtoProperties(dto);
        }
        return dto;
    }

    @Override
    protected Meter dtoToEntity(ArchiveDto dto) {
        Meter entity = null;
        if(dto != null){
            entity = new Meter();
            MeterDto meterDto = (MeterDto) dto;
            entity.setId(meterDto.getId());
            entity.setName(meterDto.getName());
        }
        return entity;
    }

    @Override
    protected ArchiveDmo dtoToDmo(ArchiveDto dto) {
        MeterDmo dmo = null;
        if(dto != null){
            dmo = new MeterDmo();
            dmo.setId(dto.getId());
            dmo.setName(dto.getName());
        }
        return dmo;
    }

    @Override
    protected void validate(ArchiveDto dto) {

    }

    @Override
    protected void loadDtoProperties(ArchiveDto dto) {

    }
}
