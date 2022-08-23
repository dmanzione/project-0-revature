package com.revature.pirateRev.data;

import com.revature.pirateRev.models.LineItem;
import com.revature.pirateRev.utils.ArrayList;

public interface DAO<T> {
	public void create(T obj);

	public T readBySomeColumnValue(String columnVal);

	public void update(T obj);

	public ArrayList<T> readAll();

	void delete(T obj);

}
