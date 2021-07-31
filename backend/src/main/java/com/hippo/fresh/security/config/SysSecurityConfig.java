package com.hippo.fresh.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.hippo.fresh.security.UserAuthenticationProvider;
import com.hippo.fresh.security.filter.JWTAuthenticationFilter;
import com.hippo.fresh.handler.UserAccessDeniedHandler;
import com.hippo.fresh.handler.UserLoginFailureHandler;
import com.hippo.fresh.handler.UserLoginSuccessHandler;
import com.hippo.fresh.handler.UserLogoutSuccessHandler;
import com.hippo.fresh.handler.UserNotLoginHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * 系统安全核心配置
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 开启方法权限注解
public class SysSecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * 无权限处理类
	 */
	@Autowired
	private UserAccessDeniedHandler userAccessDeniedHandler;

	/**
	 * 用户未登录处理类
	 */
	@Autowired
	private UserNotLoginHandler userNotLoginHandler;

	/**
	 * 用户登录成功处理类
	 */
	@Autowired
	private UserLoginSuccessHandler userLoginSuccessHandler;

	/**
	 * 用户登录失败处理类
	 */
	@Autowired
	private UserLoginFailureHandler userLoginFailureHandler;

	/**
	 * 用户登出成功处理类
	 */
	@Autowired
	private UserLogoutSuccessHandler userLogoutSuccessHandler;

	/**
	 * 用户登录验证
	 */
	@Autowired
	private UserAuthenticationProvider userAuthenticationProvider;

	@Autowired
	private  CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

//	/**
//	 * 用户权限注解
//	 */
//	@Autowired
//	private UserPermissionEvaluator userPermissionEvaluator;

	/**
	 * 加密方式
	 * 
	 * @return
	 */
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
//
//	/**
//	 * 注入自定义PermissionEvaluator
//	 *
//	 * @return
//	 */
////	@Bean
////	public DefaultWebSecurityExpressionHandler userSecurityExpressionHandler() {
////		DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
////		handler.setPermissionEvaluator(userPermissionEvaluator);
////		return handler;
////	}

	/**
	 * 用户登录验证
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(userAuthenticationProvider);
	}

	/**
	 * 安全权限配置
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests() // 权限配置
				.antMatchers(JWTConfig.antMatchers.split(",")).permitAll()// 获取白名单（不进行权限验证）
				.anyRequest().authenticated() // 其他的需要登陆后才能访问
				.and().httpBasic().authenticationEntryPoint(userNotLoginHandler) // 配置未登录处理类
				.and().formLogin().loginProcessingUrl("/api/user/login")
				.successHandler(userLoginSuccessHandler) // 配置登录成功处理类
				.failureHandler(userLoginFailureHandler) // 配置登录失败处理类
				.and().logout()// 配置登出地址
				.logoutSuccessHandler(userLogoutSuccessHandler) // 配置用户登出处理类
				.and().exceptionHandling().accessDeniedHandler(userAccessDeniedHandler)// 配置没有权限处理类
				.and().cors() // 开启跨域
				.and().csrf().disable(); // 禁用跨站请求伪造防护
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 禁用session（使用Token认证）
		http.headers().cacheControl(); // 禁用缓存
		http.addFilter(new JWTAuthenticationFilter(authenticationManager())); //// 添加JWT过滤器
		http.exceptionHandling()
				.authenticationEntryPoint(customAuthenticationEntryPoint);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedOrigin("*");
		configuration.addAllowedMethod("*");
		configuration.addAllowedHeader("*");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}


}