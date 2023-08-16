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
     * @param sysMenu
     */
    void addMenu(SysMenu sysMenu);

    /**
     * 查询菜单列表
     * @param queryVo
     * @return
     */
    List<SysMenu> getMenuList(MenuQueryVo queryVo);
}
