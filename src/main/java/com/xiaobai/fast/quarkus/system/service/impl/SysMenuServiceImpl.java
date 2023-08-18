package com.xiaobai.fast.quarkus.system.service.impl;

import com.xiaobai.fast.quarkus.config.Constants;
import com.xiaobai.fast.quarkus.core.exception.ServiceException;
import com.xiaobai.fast.quarkus.config.ienum.ServiceCodeEnum;
import com.xiaobai.fast.quarkus.system.domain.vo.MenuQueryVo;
import com.xiaobai.fast.quarkus.system.repository.SysMenuRepository;
import com.xiaobai.fast.quarkus.system.service.SysMenuService;
import com.xiaobai.fast.quarkus.system.domain.SysMenu;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.runtime.util.StringUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
@ApplicationScoped
public class SysMenuServiceImpl implements SysMenuService {
    @Inject
    SysMenuRepository sysMenuRepository;

    /**
     * 添加一个菜单
     *
     * @param sysMenu 菜单实体
     */
    @Override
    @Transactional(rollbackOn = Exception.class)
    public void addMenu(SysMenu sysMenu) {
        // 保存
        sysMenuRepository.persist(sysMenu);
    }

    /**
     * 查询菜单列表
     *
     * @param queryVo 查询条件
     * @return 菜单列表
     */
    @Override
    public List<SysMenu> getMenuList(MenuQueryVo queryVo) {
        StringBuilder hql = new StringBuilder();
        hql.append("FROM SysMenu where isDel=0 ");

        Map<String,Object> paramterMap = new HashMap<>();
        if(!StringUtil.isNullOrEmpty(queryVo.getMenuName())){
            hql.append("AND menuName like :menuName");
            paramterMap.put("menuName", Constants.likeFormat(queryVo.getMenuName()));
        }
        if(queryVo.getStatus() !=null){
            hql.append("AND status=:status");
            paramterMap.put("status",queryVo.getStatus());
        }
        if(queryVo.getParentId() !=null){
            hql.append("AND parentId=:parentId");
            paramterMap.put("parentId",queryVo.getParentId());
        }
        PanacheQuery<SysMenu> panacheQuery = sysMenuRepository
                .find(hql.toString(),paramterMap);
        PanacheQuery<SysMenu> page = panacheQuery.page(queryVo.getPageNum(), queryVo.getPageSize());
        return page.list();
    }

    /**
     * 查询菜单详情
     *
     * @param menuId 菜单Id
     * @return 菜单详情
     */
    @Override
    public SysMenu getById(Long menuId) {
       return sysMenuRepository.find("FROM SysMenu where isDel=0 AND menu_id=:menuId", menuId).firstResult();
    }

    /**
     * 更新菜单信息
     *
     * @param sysMenu 更新菜单的信息
     */
    @Override
    @Transactional
    public void updateById(SysMenu sysMenu) {
        SysMenu exits = sysMenuRepository.findById(sysMenu.getMenuId());
        if(exits == null){
            throw new ServiceException(ServiceCodeEnum.PARAMETER_ERROR,ServiceCodeEnum.PARAMETER_ERROR);
        }
        exits.setMenuId(sysMenu.getMenuId());
        exits.setParentId(sysMenu.getParentId());
        exits.setMenuName(sysMenu.getMenuName());
        exits.setSortBy(sysMenu.getSortBy());
        exits.setStatus(sysMenu.getStatus());
        exits.setUrl(sysMenu.getUrl());
        exits.setTarget(sysMenu.getTarget());
        exits.setMenuType(sysMenu.getMenuType());
        exits.setVisible(sysMenu.getVisible());
        exits.setIsRefresh(sysMenu.getIsRefresh());
        exits.setPermissionString(sysMenu.getPermissionString());
        exits.setIcon(sysMenu.getIcon());
        exits.setCreateName(sysMenu.getCreateName());
        exits.setUpdateName(sysMenu.getUpdateName());
        exits.setCreateTime(sysMenu.getCreateTime());
        exits.setUpdateTime(sysMenu.getUpdateTime());
        exits.setIsDel(sysMenu.getIsDel());

    }

    /**
     * 删除菜单
     *
     * @param ids 菜单Id 使用,逗号分割
     */
    @Override
    @Transactional
    public void deleteByIds( List<Long> ids) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("ids",ids);
        sysMenuRepository.update(" isDel=1 where menuId in (:ids)",paramMap);
    }
}
