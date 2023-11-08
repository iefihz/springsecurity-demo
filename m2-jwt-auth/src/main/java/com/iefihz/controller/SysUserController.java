package com.iefihz.controller;

import com.iefihz.entity.SecurityUser;
import com.iefihz.plugin.exception.annotation.GlobalException;
import com.iefihz.plugin.exception.entity.R;
import com.iefihz.security.properties.JwtRsaProperties;
import com.iefihz.service.SysUserService;
import com.iefihz.tools.jwt.JwtTools;
import com.iefihz.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@GlobalException
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private JwtRsaProperties jwtRsaProps;

    /**
     * 刷新access_token、refresh_token
     * @param request
     * @param response
     * @return
     */
    @GetMapping("/refreshToken")
    public R refreshToken(HttpServletRequest request, HttpServletResponse response) {
        // 已经通过Jwt认证过滤器校验，这里直接生成两个access_token、refresh_token即可
        String token = request.getHeader("Authorization").replaceAll("^(Bearer|bearer)", "").trim();

        // 获取jwt中的用户名
        UserVo vo = JwtTools.parser(jwtRsaProps.getPublicKey()).parseJwt4Data(token, UserVo.class);

        // 组成UserVo并返回
        SecurityUser securityUser = (SecurityUser) sysUserService.loadUserByUsername(vo.getUsername());
        UserVo userVo = securityUser.getUserVo();

        return R.success().data(userVo).message("token刷新成功，请替换本地access_token、refresh_token、权限角色等");
    }
}
