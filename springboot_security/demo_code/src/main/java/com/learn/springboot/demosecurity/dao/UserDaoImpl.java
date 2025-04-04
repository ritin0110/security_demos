package com.learn.springboot.demosecurity.dao;

import org.springframework.stereotype.Repository;

import com.learn.springboot.demosecurity.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class UserDaoImpl implements UserDao {
	private EntityManager entityManager;

	public UserDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public User findByUserName(String username) {
		TypedQuery<User> query = entityManager.createQuery("from User where name=:username and active=true",
				User.class);
		query.setParameter("username", username);
		return query.getSingleResult();
	}
}
