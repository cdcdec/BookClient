package com.cdc.bookclient.commons;
/**
 * 上传异常
 * @author aaron
 *
 */
public class UploadException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 190798859702036010L;

	public UploadException(String m) {
		super(m);
	}
}
