package com.cdc.bookclient.dao;

import java.util.Collection;
import com.cdc.bookclient.bean.SaleRecord;

/**
 * 销售记录DAO接口
 * @author aaron
 *
 */
public interface SaleRecordDao {

	/**
	 * 根据两个日期, 查找两个日期之间的交易记录
	 * @param begin 开始日期的字符串, 格式为yyyy-MM-dd
	 * @param end 结束日期的字符串, 格式为yyyy-MM-dd
	 * @return
	 */
	Collection<SaleRecord> findByDate(String begin, String end);
	
	/**
	 * 根据id查找销售记录
	 * @param id
	 * @return
	 */
	SaleRecord findById(String id);
	
	/**
	 * 保存一条销售记录
	 * @param record
	 * @return
	 */
	String save(SaleRecord record);
}
