package com.xiaobai.fast.quarkus.system.service;

import com.xiaobai.fast.quarkus.system.domain.SysMenu;
import com.xiaobai.fast.quarkus.system.domain.vo.MenuQueryVo;

import java.util.List;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
public interface SysMenuService {

    /**
     * 添加一个菜单
     * @param sysMenu 新增菜单参数
     */
    void addMenu(SysMenu sysMenu);

    /**
     * 查询菜单列表
     * @param queryVo 查询参数
     * @return 菜单列白哦
     */
    List<SysMenu> getMenuList(MenuQueryVo queryVo);

    /**
     * 查询菜单详情
     * @param menuId 菜单Id
     * @return 菜单详情
     */
    SysMenu getById(Long menuId);

    /**
     * 更新菜单信息
     * @param sysMenu 更新菜单的信息
     */
    void updateById(SysMenu sysMenu);

    /**
     * 删除菜单
     * @param ids 菜单Id 使用,逗号分割
     */
    void deleteByIds( List<Long> ids);
}
