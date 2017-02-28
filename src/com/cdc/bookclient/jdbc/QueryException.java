package com.cdc.bookclient.jdbc;
/**
 * 查询异常类
 * @author aaron
 *
 */
public class QueryException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4584627661258422417L;

	public QueryException(String message){
		super(message);
	}

}
