package cn.phorcys.oauth.controller;

import cn.phorcys.framework.commons.utility.object.LocalDateUtil;
import cn.phorcys.framework.commons.utility.object.StringUtil;
import cn.phorcys.framework.soa.annotation.AuthenticateSkip;
import cn.phorcys.framework.soa.authentication.Authorizer;
import cn.phorcys.framework.soa.commons.SessionInfo;
import cn.phorcys.oauth.db.entity.SysUser;
import cn.phorcys.oauth.dto.LoginRequestType;
import cn.phorcys.oauth.dto.LoginResponseType;
import cn.phorcys.oauth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * @Author: Wonder
 * @Date: Created on 2020/5/14 4:40 下午
 */
@RestController
public class OAuthController {
    @Autowired
    UserService userService;
    @Autowired
    Authorizer authorizer;

    @AuthenticateSkip
    @PostMapping(value = "/login")
    public LoginResponseType login(@RequestBody(required = false) LoginRequestType request) {
        String username = request.getUsername();
        String password = request.getPassword();
        LoginResponseType response = new LoginResponseType();
        if (StringUtil.isNullOrEmptyOrWhitespace(username)) {
            response.setSuccessful(false);
            response.setMessage("用户名或邮箱不能为空");
            return response;
        }
        if (StringUtil.isNullOrEmptyOrWhitespace(password)) {
            response.setSuccessful(false);
            response.setMessage("密码不能为空");
            return response;
        }

        SysUser sysUser = userService.checkUserPassword(username, password);
        if (sysUser == null) {
            response.setSuccessful(false);
            response.setMessage("用户名或密码错误");
            return response;
        } else {
            LocalDate endDate = sysUser.getEndDate();
            if (endDate != null && endDate.isBefore(LocalDate.now())) {
                response.setSuccessful(false);
                response.setMessage("该用户已于" + LocalDateUtil.toDateString(endDate) + "到期");
                return response;
            }
            response.setSuccessful(true);
            response.setMessage("登录成功");
            SessionInfo current = SessionInfo.current();
            String clientIP = current.getUserRealIP();
            Long sessionId = authorizer.login(sysUser.getTenantId(), sysUser.getUserId(), sysUser.getUserName(), clientIP);
            response.setSessionId(sessionId.toString());
            return response;
        }
    }
}
