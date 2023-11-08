package com.iefihz.service;

import com.iefihz.entity.SysMenu;
import com.iefihz.entity.SysRole;
import com.iefihz.entity.SysUser;
import com.iefihz.vo.RolesAndMenus;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface SysUserService extends UserDetailsService {

    /**
     * 用户名获取用户
     * @param username
     * @return
     */
    SysUser getByUsername(String username);

    /**
     * 获取用户的角色
     * @param id
     * @return
     */
    List<SysRole> getRolesByUserId(Long id);

    /**
     * 获取用户的菜单权限
     * @param id
     * @return
     */
    List<SysMenu> getMenusByUserId(Long id);

    /**
     * 获取比level低级别的角色
     * @param level
     * @return
     */
    List<SysRole>  getLowerLevelRoles(int level);

    /**
     * 获取比level低级别的菜单
     * @param level
     * @return
     */
    List<SysMenu> getLowerLevelMenus(int level);

    /**
     * 获取用户实际上的角色和菜单权限
     * @param userId
     * @return
     */
    RolesAndMenus getRealRolesAndMenus(Long userId);
}
