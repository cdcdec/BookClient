package com.cdc.bookclient.service;

import java.util.Collection;

import com.cdc.bookclient.bean.Concern;

/**
 * 出版社业务接口
 * @author aaron
 *
 */
public interface ConcernService {

	/**
	 * 获取全部的出版社
	 * @return
	 */
	Collection<Concern> getAll();
	
	/**
	 * 根据id查找一个出版社
	 * @param id 出版社id
	 * @return
	 */
	Concern find(String id);
	
	/**
	 * 添加一个出版社
	 * @param c
	 * @return
	 */
	Concern add(Concern c);
	
	/**
	 * 修改一个出版社
	 * @param c
	 * @return
	 */
	Concern update(Concern c);
	
	/**
	 * 根据出版社名字模糊查找
	 * @param name
	 * @return
	 */
	Collection<Concern> query(String name);
}

