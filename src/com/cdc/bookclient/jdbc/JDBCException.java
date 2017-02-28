package com.cdc.bookclient.jdbc;
/**
 * jdbc异常类
 * @author aaron
 *
 */
public class JDBCException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7645236315313796390L;

	public  JDBCException(String message){
		super(message);
	}

}
