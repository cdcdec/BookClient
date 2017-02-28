package com.cdc.bookclient.service.impl;

import java.util.Collection;

import com.cdc.bookclient.bean.Type;
import com.cdc.bookclient.dao.TypeDao;
import com.cdc.bookclient.service.TypeService;

/**
 * 书本种类业务实现类
 * @author aaron
 *
 */
public class TypeServiceImpl implements TypeService {

	private TypeDao dao;
	
	public TypeServiceImpl(TypeDao dao) {
		this.dao = dao;
	}
	
	public Collection<Type> query(String name) {
		return dao.findByName(name);
	}

	public Collection<Type> getAll() {
		return dao.find();
	}

	public Type add(Type type) {
		String id = dao.add(type);
		return get(id);
	}

	public Type update(Type type) {
		// TODO Auto-generated method stub
		String id = dao.update(type);
		return get(id);
	}
	
	public Type get(String id) {
		return dao.find(id);
	}

}

