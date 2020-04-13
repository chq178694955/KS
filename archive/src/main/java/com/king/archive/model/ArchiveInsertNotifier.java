package com.king.archive.model;

import com.king.archive.dto.ArchiveDto;

/**
 * 档案新增通知类
 * @创建人 chq
 * @创建时间 2020/3/20
 * @描述
 */
public class ArchiveInsertNotifier extends ArchiveNotifier {

    public ArchiveInsertNotifier(Long userId, ArchiveDto dto){
        super(userId,dto);
    }

}
