package com.cdc.bookclient;

import com.cdc.bookclient.dao.UserDao;
import com.cdc.bookclient.dao.impl.UserDaoImpl;
import com.cdc.bookclient.service.UserService;
import com.cdc.bookclient.service.impl.UserServiceImpl;
import com.cdc.bookclient.ui.LoginFrame;
/**
 * 程序入口类
 * @author aaron
 *
 */
public class Main {
	public static void main(String[] args) {
		UserDao userDao = new UserDaoImpl();
		UserService userService = new UserServiceImpl(userDao);
		new LoginFrame(userService);
	}

}
