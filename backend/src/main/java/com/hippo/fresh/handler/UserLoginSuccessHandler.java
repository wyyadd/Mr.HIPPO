package com.hippo.fresh.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.hippo.fresh.security.utils.JWTTokenUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.hippo.fresh.security.entity.SysUserDetails;
import com.hippo.fresh.utils.ResponseUtils;

/**
 * 登录成功处理类
 */
@Component
public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) {
		SysUserDetails sysUserDetails = (SysUserDetails) authentication.getPrincipal();
		String token = JWTTokenUtil.createAccessToken(sysUserDetails);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("id",sysUserDetails.getId());
		jsonObject.put("username",sysUserDetails.getUsername());
		jsonObject.put("token", token);
		ResponseUtils.responseJson(response, ResponseUtils.response(200, "登录成功", jsonObject));
	}
}
