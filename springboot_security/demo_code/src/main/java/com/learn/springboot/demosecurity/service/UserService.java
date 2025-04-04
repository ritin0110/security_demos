package com.learn.springboot.demosecurity.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.learn.springboot.demosecurity.entity.User;

public interface UserService extends UserDetailsService {
	public User findByUserName(String username);
}
