package com.king.archive.dmo;


import com.king.archive.dto.ArchiveType;

/**
 * 
 * 空对象.
 * <p>
 *  用于缓存不存在的档案
 * </p>
 * @author    c00031
 * @see       [相关类/方法]
 * @since     2.0.0
 */
public class NullDmo extends ArchiveDmo {

    private static final long serialVersionUID = 5417982542728393490L;

    public NullDmo() {
        super(ArchiveType.UNKONW);
    }
    
    public NullDmo(ArchiveType type) {

        super(type);
    }
    
}
