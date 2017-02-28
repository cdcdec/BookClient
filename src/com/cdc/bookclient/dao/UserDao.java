package com.cdc.bookclient.dao;

import com.cdc.bookclient.bean.User;
/**
 * 用户DAO接口
 * @author aaron
 *
 */
public interface UserDao {
	User getUser(String name, String password);

}
