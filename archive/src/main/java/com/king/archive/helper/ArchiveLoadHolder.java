package com.king.archive.helper;

/**
 * 档案懒加载管理器.
 * @see       [相关类/方法]
 * @since     2.0.0
 */
public class ArchiveLoadHolder {

    private static final ThreadLocal<Boolean> holder = new ThreadLocal<Boolean>();
    
    /**
     * 设置当前懒加载状态.
     * 
     * @param lazyload
     */
    public static void set(Boolean lazyload) {
        holder.set(lazyload);
    }
    
    /**
     * 获取当前懒加载状态.
     * 
     * @return
     */
    public static Boolean isLazyload() {
        return holder.get();
    }

    /**
     * 获取当前懒加载状态.
     * <p>
     * 如果未设置状态，则使用默认状态.
     * </p>
     * @param defaultLazyload 默认懒加载状态
     * @return
     */
    public static Boolean isLazyload(Boolean defaultLazyload) {
       Boolean lazyload = holder.get();
       return lazyload == null ? defaultLazyload 
               : lazyload;
    }
    
    public static void clear() {
        holder.remove();
    }
    
}
