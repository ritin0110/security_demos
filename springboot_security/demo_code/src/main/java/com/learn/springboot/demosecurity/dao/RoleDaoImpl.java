package com.learn.springboot.demosecurity.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class RoleDaoImpl implements RoleDao {
	private EntityManager entityManager;

	public RoleDaoImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<String> getUserRoles(int userId) {
		TypedQuery<String> query = entityManager.createQuery("select r.name from Role r, UserRole ur "
				+ "where ur.userId=:userId and ur.roleId=r.id",
				String.class);
		query.setParameter("userId", userId);
		return query.getResultList();
	}
}
