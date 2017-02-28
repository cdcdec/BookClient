package com.cdc.bookclient.service.impl;

import com.cdc.bookclient.bean.User;
import com.cdc.bookclient.commons.BusinessException;
import com.cdc.bookclient.dao.UserDao;
import com.cdc.bookclient.service.UserService;
/**
 * 用户业务实现类
 * @author aaron
 *
 */
public class UserServiceImpl implements UserService {

private UserDao userDao;
	
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public void login(String name, String password) {
		User user = userDao.getUser(name, password);
		if (user == null) {
			throw new BusinessException("用户名密码错误");
		}
	}

}
