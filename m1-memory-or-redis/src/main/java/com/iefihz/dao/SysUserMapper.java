package com.iefihz.dao;

import com.iefihz.entity.SysMenu;
import com.iefihz.entity.SysRole;
import com.iefihz.entity.SysUser;

import java.util.List;

public interface SysUserMapper {
    SysUser getByUsername(String username);

    List<SysRole> getRolesByUserId(Long id);

    List<SysMenu> getMenusByUserId(Long id);

    List<SysRole> getLowerLevelRoles(int level);

    List<SysMenu> getLowerLevelMenus(int level);
}
