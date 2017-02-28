package com.cdc.bookclient.commons;
/**
 * 业务异常
 * @author aaron
 *
 */
public class BusinessException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257969801456443325L;

	public BusinessException(String m) {
		super(m);
	}

}
