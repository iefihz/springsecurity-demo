package com.iefihz.security.handler;

import com.iefihz.plugin.exception.entity.R;
import com.iefihz.plugin.exception.enums.RCode;
import com.iefihz.security.exception.AuthException;
import com.iefihz.tools.HttpTools;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理器
 */
@Component
public class AuthFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        R r = null;
        if (e instanceof AuthException) {
            AuthException ae = (AuthException) e;
            r = R.fail().code(ae.getCode()).message(ae.getMessage());
        } else if (e.getCause() instanceof AuthException) {
            AuthException ae = (AuthException) e.getCause();
            r = R.fail().code(ae.getCode()).message(ae.getMessage());
        } else if (e instanceof BadCredentialsException) {
            r = R.fail().rCode(RCode.INCORRECT_CREDENTIAL);
        } else {
            r = R.fail().message(e.getMessage());
        }
        HttpTools.writeJson(response, r);
    }
}
