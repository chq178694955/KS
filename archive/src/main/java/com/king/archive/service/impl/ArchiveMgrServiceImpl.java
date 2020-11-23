package com.king.archive.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.king.archive.dmo.ArchiveDmo;
import com.king.archive.dto.ArchiveDto;
import com.king.archive.entity.Archive;
import com.king.archive.helper.ArchiveLoadHolder;
import com.king.archive.model.ArchiveDeleteNotifier;
import com.king.archive.model.ArchiveInsertNotifier;
import com.king.archive.model.ArchiveNotifier;
import com.king.archive.model.ArchiveUpdateNotifier;
import com.king.archive.observer.ArchiveObservable;
import com.king.archive.service.IArchiveLazyLoader;
import com.king.archive.service.IArchiveMgrService;
import com.king.framework.base.IBaseDao;
import com.king.framework.model.Criteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 档案查询基类 档案通用方法可以用它来处理
 * @创建人 chq
 * @创建时间 2020/3/20
 * @描述
 */
public abstract class ArchiveMgrServiceImpl<T extends Archive> implements IArchiveMgrService<ArchiveDto> {

    private Logger logger = LoggerFactory.getLogger(ArchiveMgrServiceImpl.class);

    public static final String FIND_BY_IDS = "findByIds";
    public static final String FIND_BY_PAGE = "findByPage";

    @Autowired
    private ArchiveObservable observable;

    //@Autowired
    private IBaseDao<T> baseDao;

    @Override
    public boolean insert(Long userId, ArchiveDto archiveDto) {
        return insert(userId, archiveDto, null);
    }

    protected boolean insert(Long userId, ArchiveDto dto, Callback callback){
        T entity = dtoToEntity(dto);
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        int i = baseDao.insert(entity);
        if(callback != null){
            callback.execute();
        }
        this.notifyObservers(new ArchiveInsertNotifier(userId, dto));
        return i > 0;
    }

    @Override
    public boolean delete(Long userId, ArchiveDto dto) {
        return delete(userId, dto);
    }

    @Override
    public boolean delete(Long userId, List<ArchiveDto> list) {
        if (list != null && list.size() > 0) {
            for (ArchiveDto dto : list) {
                delete(userId, dto);
            }
        }
        return true;
    }

    protected boolean delete(Long userId, ArchiveDto dto, Callback callback){
        validate(dto);
        int i = baseDao.delete(dto.getId());
        if(callback != null){
            callback.execute();
        }
        this.notifyObservers(new ArchiveDeleteNotifier(userId, dto));
        return i > 0;
    }

    @Override
    public boolean update(Long userId, ArchiveDto archiveDto) {
        return false;
    }

    protected boolean update(Long userId, ArchiveDto dto, Callback callback){
        validate(dto);
        ArchiveDto oldDto = get(dto.getId());
        if (oldDto == null) {
            logger.warn("Attempt to update the archive[type:" + dto.getArchiveType().getValue()+", id:"+dto.getId()+"] that do not exist!");
            return insert(userId, dto, callback);
        }
        T oldEntity = baseDao.selectByPrimaryKey(dto.getId());
        T newEntity = this.dtoToEntity(dto);
        BeanUtils.copyProperties(newEntity, oldEntity);
        int i = baseDao.update(oldEntity);
        if (callback != null) {
            callback.execute();
        }
        this.notifyObservers(new ArchiveUpdateNotifier(userId, oldDto, dto));
        return i > 0;

    }

    @Override
    public ArchiveDto get(Long id) {
        T entity = baseDao.selectByPrimaryKey(id);
        Boolean isLazyload = ArchiveLoadHolder.isLazyload(true);
        return entityToDto(entity,isLazyload);
    }

    @Override
    public List<ArchiveDto> list(Criteria params) {
        List<T> entities = baseDao.find(params);
        List<ArchiveDto> dtos = new ArrayList<>();
        ArchiveDto dto = null;
        Boolean isLazyload = ArchiveLoadHolder.isLazyload(false);
        for (T entity : entities) {
            dto = entityToDto(entity, isLazyload);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<ArchiveDto> list(Long[] ids) {
        List<T> entities = baseDao.find(FIND_BY_IDS, ids);
        List<ArchiveDto> dtos = new ArrayList<ArchiveDto>();
        ArchiveDto dto = null;
        Boolean isLazyload = ArchiveLoadHolder.isLazyload(true);
        for (T entity : entities) {
            dto = entityToDto(entity, isLazyload);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<ArchiveDto> listAll() {
        List<T> entities = baseDao.findAll();
        List<ArchiveDto> dtos = new ArrayList<ArchiveDto>();
        ArchiveDto dto = null;
        Boolean isLazyload = ArchiveLoadHolder.isLazyload(true);
        for (T entity : entities) {
            dto = entityToDto(entity, isLazyload);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public PageInfo<ArchiveDto> findByPage(PageInfo<ArchiveDto> page, Criteria criteria) {
        if (page.getPageSize() != -1) {
            PageHelper.startPage(page.getPageNum(), page.getPageSize());
        }
        List<T> entities = baseDao.find(FIND_BY_PAGE, criteria);
        List<ArchiveDto> dtos = new ArrayList<ArchiveDto>();
        ArchiveDto dto = null;
        Boolean isLazyload = ArchiveLoadHolder.isLazyload(false);
        for (T entity : entities) {
            dto = entityToDto(entity, isLazyload);
            dtos.add(dto);
        }
        PageInfo<T> pageInfo = new PageInfo<>(entities);
        page.setList(dtos);
        page.setTotal(pageInfo.getTotal());
        return page;
    }

    /**
     * 通知观察者.
     * @param notifier
     */
    protected void notifyObservers(ArchiveNotifier notifier) {

        observable.notifyObservers(notifier);
    }

    /**
     * 实体转模型.
     * @param entity 实体类
     * @param isLazy 是否懒加载关联属性
     * @return
     */
    protected abstract ArchiveDto entityToDto(T entity, boolean isLazy);

    /**
     * 模型转实体.
     * @param dto
     * @return
     */
    protected abstract T dtoToEntity(ArchiveDto dto);

    /**
     * Dto转Dmo.
     * @param dto
     */
    protected abstract ArchiveDmo dtoToDmo(ArchiveDto dto);

    /**
     * 数据校验.
     * @param dto
     */
    protected abstract void validate(ArchiveDto dto);

    /**
     * 加载Dto属性.
     * @param dto
     */
    protected abstract void loadDtoProperties(ArchiveDto dto);

    /**
     * 档案操作回调接口
     */
    public interface Callback {
        public void execute(Object... args);
    }

    @Override
    public IArchiveLazyLoader<ArchiveDto> getLazyLoader() {
        return new IArchiveLazyLoader<ArchiveDto>() {
            @Override
            public void load(ArchiveDto dto) {
                loadDtoProperties(dto);
            }

            @Override
            public ArchiveDmo getDmo(ArchiveDto dto) {
                return dtoToDmo(dto);
            }
        };
    }
}
