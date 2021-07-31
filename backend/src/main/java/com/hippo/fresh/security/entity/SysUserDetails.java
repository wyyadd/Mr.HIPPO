package com.hippo.fresh.security.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.hippo.fresh.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.stereotype.Component;

/**
 * 系统用户详情
 */
@Data
@Component
@EqualsAndHashCode(callSuper = false)
public class SysUserDetails extends User implements UserDetails, Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 账号是否过期
	 */
	private boolean isAccountNonExpired = false;

	/**
	 * 账号是否锁定
	 */
	private boolean isAccountNonLocked = false;

	/**
	 * 证书是否过期
	 */
	private boolean isCredentialsNonExpired = false;

	/**
	 * 账号是否有效
	 */
	private boolean isEnabled = true;

	/**
	 * 获得用户权限
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<SimpleGrantedAuthority>  authorities = new HashSet<>();
		authorities.add(new SimpleGrantedAuthority("customer"));
		return authorities;
	}

	/**
	 * 判断账号是否过期
	 */
	@Override
	public boolean isAccountNonExpired() {
		return isAccountNonExpired;
	}

	/**
	 * 判断账号是否锁定
	 */
	@Override
	public boolean isAccountNonLocked() {
		return isAccountNonLocked;
	}

	/**
	 * 判断证书是否过期
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return isCredentialsNonExpired;
	}

	/**
	 * 判断账号是否有效
	 */
	@Override
	public boolean isEnabled() {
		return isEnabled;
	}

}
