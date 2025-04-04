package com.learn.springboot.demosecurity.dao;

import java.util.List;

public interface RoleDao {
	List<String> getUserRoles(int userId);
}
