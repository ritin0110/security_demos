package com.learn.springboot.demosecurity.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.learn.springboot.demosecurity.dao.RoleDao;
import com.learn.springboot.demosecurity.dao.UserDao;
import com.learn.springboot.demosecurity.entity.User;

@Service
public class UserServiceImpl implements UserService {
	private UserDao userDao;
	private RoleDao roleDao;

	@Autowired
	public UserServiceImpl(UserDao userDao, RoleDao roleDao) {
		this.userDao = userDao;
		this.roleDao = roleDao;
	}

	@Override
	public User findByUserName(String username) {
		return userDao.findByUserName(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userDao.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found in DB");
		}
		return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
				mapRoleToAuthorities(roleDao.getUserRoles(user.getId())));
	}

	private Collection<? extends GrantedAuthority> mapRoleToAuthorities(List<String> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
	}
}
