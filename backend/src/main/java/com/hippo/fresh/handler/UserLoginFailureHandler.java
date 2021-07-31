package com.hippo.fresh.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hippo.fresh.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * 登录失败处理类
 */
@Component
@Slf4j
public class UserLoginFailureHandler implements AuthenticationFailureHandler {

	@Override

	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) {
		log.info(exception.getMessage());//TODO(wyyadd):此可以加上日志功能
		HashMap<String,String> dataMap = new HashMap<String,String>();
		dataMap.put("username",exception.getMessage());
		ResponseUtils.responseJson(response, ResponseUtils.response(401, "用户名或密码错误", dataMap));
	}
}
