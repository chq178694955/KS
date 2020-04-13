package com.king.archive.model;

import com.king.archive.dto.ArchiveDto;

/**
 * 档案删除通知类
 * @创建人 chq
 * @创建时间 2020/3/20
 * @描述
 */
public class ArchiveDeleteNotifier extends ArchiveNotifier {

    public ArchiveDeleteNotifier(Long userId, ArchiveDto dto){
        super(userId,dto);
    }

}
