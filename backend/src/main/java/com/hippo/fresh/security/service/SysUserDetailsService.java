package com.hippo.fresh.security.service;

import com.hippo.fresh.entity.User;
import com.hippo.fresh.security.entity.SysUserDetails;
import com.hippo.fresh.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * 用户登录Service
 */
@Service
public class SysUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;

	/**
	 * 根据用户名查用户信息
	 * 
	 * @param username 用户名
	 * @return 用户详细信息
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userService.findByUsername(username);
		if (user.isPresent()) {
			SysUserDetails sysUserDetails = new SysUserDetails();
			BeanUtils.copyProperties(user.get(), sysUserDetails);

//			Set<GrantedAuthority> authorities = new HashSet<>(); // 角色集合
//
//			List<SysRole> roleList = sysUserService.findRoleByUserId(sysUserDetails.getId());
//			roleList.forEach(role -> {
//				authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
//			});
//
//			sysUserDetails.setAuthorities(authorities);

			return sysUserDetails;
		}
		return null;
	}
}
