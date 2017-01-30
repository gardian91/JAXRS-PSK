package com.psk.bank.repository;

import java.util.List;

public interface Repository<T, ID> {
	void save(T object);
	T findOne(ID id);
	List<T> findAll();
}
