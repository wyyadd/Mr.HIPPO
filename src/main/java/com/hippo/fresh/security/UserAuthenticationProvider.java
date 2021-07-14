package com.hippo.fresh.security;

import com.hippo.fresh.security.entity.SysUserDetails;
import com.hippo.fresh.security.service.SysUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 用户登录验证处理类
 */
@Component
@Slf4j
public class UserAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private SysUserDetailsService userDetailsService;

	/**
	 * 身份验证
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		String username = (String) authentication.getPrincipal(); // 获取用户名
		String password = (String) authentication.getCredentials(); // 获取密码
		SysUserDetails sysUserDetails = (SysUserDetails) userDetailsService.loadUserByUsername(username);

		if (sysUserDetails == null) {
			throw new BadCredentialsException("用户不存在");
		}
		if (!new BCryptPasswordEncoder().matches(password, sysUserDetails.getPassword())) {
			throw new BadCredentialsException("用户名或密码错误");
		}
//		if (sysUserDetails.getStatus().equals("2")) {
//			throw new LockedException("用户已禁用");
//		}
		return new UsernamePasswordAuthenticationToken(sysUserDetails, password, sysUserDetails.getAuthorities());
	}

	/**
	 * 支持指定的身份验证
	 */
	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}

}
