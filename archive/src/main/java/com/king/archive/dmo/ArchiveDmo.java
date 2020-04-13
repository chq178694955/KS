package com.king.archive.dmo;

import com.king.archive.dto.ArchiveType;

import java.io.Serializable;

/**
 * 档案内存对象的基类.
 * @see       [相关类/方法]
 * @since     2.0.0
 */
public abstract class ArchiveDmo implements Serializable, Cloneable {

    private static final long serialVersionUID = -1374091644194240560L;

    // 档案类型
	private ArchiveType archiveType;
	
	// 档案唯一标识
	private long id;
	
	// 档案名称
	private String name;
	
	public ArchiveDmo(ArchiveType type) {

		this.archiveType = type;
	}
	
    public long getId() {

		return id;
	}

	public void setId(long id) {

		this.id = id;
	}

	public ArchiveType getArchiveType() {

		return archiveType;
	}
    
    public String getName() {
    
        return name;
    }

    
    public void setName(String name) {
    
        this.name = name;
    }

    
    public void setArchiveType(ArchiveType archiveType) {
    
        this.archiveType = archiveType;
    }
    
    public Object clone() throws CloneNotSupportedException {
        
        return super.clone();
    }
    
}
