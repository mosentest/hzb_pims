package com.hzb.dao;

import java.util.List;

public interface BaseDao<T> {
	public int save(T entity);

	public int update(T entity);

	public List<T> findAll();
	
	public T findOne(int id);
}
