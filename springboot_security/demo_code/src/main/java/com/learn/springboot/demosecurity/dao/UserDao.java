package com.learn.springboot.demosecurity.dao;

import com.learn.springboot.demosecurity.entity.User;

public interface UserDao {
	User findByUserName(String username);
}
