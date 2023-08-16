package com.xiaobai.fast.quarkus.system.service.impl;

import com.xiaobai.fast.quarkus.system.domain.vo.MenuQueryVo;
import com.xiaobai.fast.quarkus.system.repository.SysMenuRepository;
import com.xiaobai.fast.quarkus.system.service.SysMenuService;
import com.xiaobai.fast.quarkus.system.domain.SysMenu;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

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

        PanacheQuery<SysMenu> panacheQuery = sysMenuRepository
                .find("parentId",queryVo.getParentId());
        panacheQuery.page(queryVo.getPageNum(),queryVo.getPageSize());
        return panacheQuery.list();
    }
}
