package com.hippo.fresh.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hippo.fresh.security.config.JWTConfig;
import com.hippo.fresh.security.entity.SysUserDetails;
import com.hippo.fresh.security.utils.JWTTokenUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * JWT权限过滤器，用于验证Token是否合法
 */
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		// 取出Token
		String token = request.getHeader(JWTConfig.tokenHeader);
		if (token != null && token.startsWith(JWTConfig.tokenPrefix)) {
			SysUserDetails sysUserDetails = JWTTokenUtil.parseAccessToken(token);

			if (sysUserDetails != null) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						sysUserDetails, sysUserDetails.getId(), sysUserDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		filterChain.doFilter(request, response);
	}

}
