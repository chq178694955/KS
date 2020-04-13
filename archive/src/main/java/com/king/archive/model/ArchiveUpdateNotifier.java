package com.king.archive.model;

import com.king.archive.dto.ArchiveDto;

/**
 * 档案修改通知类
 * @创建人 chq
 * @创建时间 2020/3/20
 * @描述
 */
public class ArchiveUpdateNotifier extends ArchiveNotifier {

    /**
     * 修改后的dto
     */
    private ArchiveDto after;

    public ArchiveUpdateNotifier(Long userId, ArchiveDto dto){
        super(userId,dto);
    }

    public ArchiveUpdateNotifier(Long userId, ArchiveDto dto, ArchiveDto after){
        this(userId, dto);
        this.after = after;
    }

    public ArchiveDto getAfter() {
        return after;
    }

    public void setAfter(ArchiveDto after) {
        this.after = after;
    }
}
