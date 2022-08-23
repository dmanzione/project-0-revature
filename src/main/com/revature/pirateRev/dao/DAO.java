package com.revature.pirateRev.dao;

import com.revature.pirateRev.collections.ArrayList;
import com.revature.pirateRev.models.LineItem;

public interface DAO<T> {
	public void create(T obj);

	public T readBySomeColumnValue(String columnVal);

	public void update(T obj);

	public ArrayList<T> readAll();

	void delete(T obj);

}
