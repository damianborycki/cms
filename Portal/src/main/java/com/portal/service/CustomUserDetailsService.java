package com.portal.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portal.dao.interfaces.UserDAOI;

@Service
@Transactional(readOnly=true)
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserDAOI userDAO;

	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException {
		
		com.portal.entity.User domainUser = userDAO.getUser(login);
		
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		return new User(
				domainUser.getLogin(), 
				domainUser.getPassword(), 
				enabled, 
				accountNonExpired, 
				credentialsNonExpired, 
				accountNonLocked,
				getAuthorities(domainUser.getGroup().getId())
		);
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities(Long role) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
		return authList;
	}
	
	public List<String> getRoles(Long group_id) {

		List<String> roles = new ArrayList<String>();

		if (group_id.intValue() == 1) {
			roles.add("ROLE_ADMIN");
			roles.add("ROLE_MODERATOR");
			roles.add("ROLE_USER");
		} else if (group_id.intValue() == 2) {
			roles.add("ROLE_MODERATOR");
			roles.add("ROLE_USER");
		} else if (group_id.intValue() == 3) {
			roles.add("ROLE_USER");
		}
		
		return roles;
	}
	
	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}

}
