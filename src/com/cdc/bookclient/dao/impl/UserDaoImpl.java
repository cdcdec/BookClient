package com.cdc.bookclient.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.cdc.bookclient.bean.User;
import com.cdc.bookclient.bean.ValueObject;
import com.cdc.bookclient.dao.UserDao;

/**
 * 用户DAO实现类
 * @author aaron
 *
 */
public class UserDaoImpl extends CommonDaoImpl implements UserDao{
	@Override
	public User getUser(String name, String password) {
		String sql = "SELECT * FROM T_USER user WHERE user.USER_NAME='" + name + "' and user.USER_PASSWORD='" + 
		password + "'";
		@SuppressWarnings("unchecked")
		List<User> datas = (List<User>)getDatas(sql, new ArrayList<ValueObject>(), User.class);
		return (datas.size() == 1) ? datas.get(0) : null;
	}
}
