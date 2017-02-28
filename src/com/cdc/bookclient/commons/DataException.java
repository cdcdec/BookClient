package com.cdc.bookclient.commons;
/**
 * 数据异常
 * @author aaron
 *
 */
public class DataException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7216846084584065832L;

	public DataException(String message) {
		super(message);
	}

}
