package com.king.archive.model;

import com.king.archive.dto.ArchiveDto;

/**
 * @创建人 chq
 * @创建时间 2020/3/20
 * @描述
 */
public class ArchiveNotifier {

    protected Long userId;

    protected ArchiveDto dto;

    public ArchiveNotifier(Long userId, ArchiveDto dto) {
        this.userId = userId;
        this.dto = dto;
    }

    public Long getUserId() {

        return userId;
    }

    public void setUserId(Long userId) {

        this.userId = userId;
    }

    public ArchiveDto getDto() {

        return dto;
    }

    public void setDto(ArchiveDto dto) {

        this.dto = dto;
    }
}
