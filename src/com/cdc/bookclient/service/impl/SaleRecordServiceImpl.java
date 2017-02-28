package com.cdc.bookclient.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import com.cdc.bookclient.bean.Book;
import com.cdc.bookclient.bean.BookSaleRecord;
import com.cdc.bookclient.bean.SaleRecord;
import com.cdc.bookclient.commons.BusinessException;
import com.cdc.bookclient.commons.DateUtil;
import com.cdc.bookclient.dao.BookDao;
import com.cdc.bookclient.dao.BookSaleRecordDao;
import com.cdc.bookclient.dao.SaleRecordDao;
import com.cdc.bookclient.service.SaleRecordService;

/**
 * 销售记录业务实现类
 * @author aaron
 *
 */
public class SaleRecordServiceImpl implements SaleRecordService {
	
	private SaleRecordDao saleRecordDao;
	
	private BookSaleRecordDao bookSaleRecordDao;
	
	private BookDao bookDao;
	
	public SaleRecordServiceImpl(SaleRecordDao saleRecordDao, 
			BookSaleRecordDao bookSaleRecordDao, BookDao bookDao) {
		this.saleRecordDao = saleRecordDao;
		this.bookSaleRecordDao = bookSaleRecordDao;
		this.bookDao = bookDao;
	}
	
	@Override
	public SaleRecord get(String id) {
		SaleRecord r = saleRecordDao.findById(id);
		return processDatas(r);
	}

	@Override
	//实现接口方法
	public Collection<SaleRecord> getAll(Date date) {
		//得到下一天
		Date nextDate = DateUtil.getNextDate(date);
		//得到今天的日期, 格式为yyyy-MM-dd
		String today = DateUtil.getDateString(date);
		//得到明天的日期, 格式为yyyy-MM-dd
		String tomorrow = DateUtil.getDateString(nextDate);
		Collection<SaleRecord> records = saleRecordDao.findByDate(today, tomorrow);
		for (SaleRecord r : records) {
			processDatas(r);
		}
		return records;
	}
	
	//处理一个SaleRecord, 设置它的书本销售记录属性和书本名字属性
	private SaleRecord processDatas(SaleRecord r) {
		//查找该记录所对应的书的销售记录
		Collection<BookSaleRecord> brs = bookSaleRecordDao.findBySaleRecord(r.getID());
		//设置结果集中的每一个book属性
		setBook(brs);
		//设置SaleRecord对象中的书的销售记录集合
		r.setBookSaleRecords((Vector<BookSaleRecord>)brs);
		//设置SaleRecord的书名集合
		r.setBookNames(getBookNames(brs));
		//设置数量与总价
		r.setAmount(getAmount(brs));
		r.setTotalPrice(getTotalPrice(brs));
		return r;
	}
	
	//获取一次交易中涉及的总价
	private double getTotalPrice(Collection<BookSaleRecord> brs) {
		double result = 0;
		for (BookSaleRecord br : brs) {
			//书本的交易量
			int tradeSum = Integer.valueOf(br.getTRADE_SUM());
			//书的单价
			double bookPrice = Double.valueOf(br.getBook().getBOOK_PRICE());
			result += (bookPrice * tradeSum);
		}
		return result;
	}
	
	//获取一次交易中所有书本的交易量
	private int getAmount(Collection<BookSaleRecord> brs) {
		int result = 0;
		//遍历书的交易记录，计算总价
		for (BookSaleRecord br : brs) {
			result += Integer.valueOf(br.getTRADE_SUM());
		}
		return result;
	}
	
	//设置参数中的每一个BookSaleRecord的book属性
	private void setBook(Collection<BookSaleRecord> brs) {
		for (BookSaleRecord br : brs) {
			//调书本的数据访问层接口
			Book book = bookDao.find(br.getBOOK_ID_FK());
			br.setBook(book);
		}
	}
	
	//获取一次交易中所有书本的名字, 以逗号隔开
	private String getBookNames(Collection<BookSaleRecord> brs) {
		if (brs.size() == 0) return ""; 
		StringBuffer result = new StringBuffer();
		for (BookSaleRecord br : brs) {
			Book book = br.getBook();
			result.append(book.getBOOK_NAME() + ", ");
		}
		//去掉最后的逗号并返回
		return result.substring(0, result.lastIndexOf(","));
	}
	

	@Override
	public void saveRecord(SaleRecord record) {
		//遍历判断书的库存是否不够
		for (BookSaleRecord r : record.getBookSaleRecords()) {
			String bookId = r.getBook().getID();
			Book b = bookDao.find(bookId);
			//当存库不够时,抛出异常
			if (Integer.valueOf(r.getTRADE_SUM()) > 
				Integer.valueOf(b.getREPERTORY_SIZE())) {
				throw new BusinessException(b.getBOOK_NAME() + " 的库存不够");
			}
		}
		//先保存交易记录
		String id = saleRecordDao.save(record);
		//再保存书的交易记录
		for (BookSaleRecord r : record.getBookSaleRecords()) {
			//设置销售记录id
			r.setT_SALE_RECORD_ID_FK(id);
			bookSaleRecordDao.saveBookSaleRecord(r);
			//修改书的库存
			String bookId = r.getBook().getID();
			Book b = bookDao.find(bookId);
			//计算剩余的库存
			int leave = Integer.valueOf(b.getREPERTORY_SIZE()) - 
				Integer.valueOf(r.getTRADE_SUM());
			//设置库存并将库存数保存到数据库
			b.setREPERTORY_SIZE(String.valueOf(leave));
			bookDao.updateRepertory(b);
		}
	}

}
