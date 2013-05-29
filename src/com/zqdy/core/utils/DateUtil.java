package com.zqdy.core.utils;

import java.util.Date;
 


public class DateUtil {

	/**
	 * ���ص�ǰ��ݿ�ʱ��
	 * 
	 * @return
	 * @throws Exception 
	 */
	public static Date getCurrDate() throws Exception {
		Date date = null;
		 
		return date;
	}

	/**
	 * ���ص�ǰ��ݿ�ʱ���ַ�,��ʽΪ:yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 * @throws Exception 
	 */
	public static String getCurrDateStr() throws Exception {
		String dateStr = "";
//		String sql = "select sysdate from dual";
//		RecordSet rs = OperateDbService.getRecordSet(sql);
//		if (rs.next()) {
//			dateStr = rs.getTimestamp(1);
//		}
		return dateStr;
	}

	/**
	 * ��ָ����ʽ���ص�ǰ��ݿ�ʱ���ַ�
	 * 
	 * @param format
	 * @return
	 * @throws Exception 
	 */
	public static String getCurrDateStr(String format) throws Exception {
		String dateStr = "";
//		String sql = "select sysdate from dual";
//		RecordSet rs = OperateDbService.getRecordSet(sql);
//		if (rs.next()) {
//			dateStr = sundun.framework.util.DateUtil.getDateFormat(rs.getTimestamp(1),
//					"yyyy-MM-dd HH:mm:ss", format);
//		}
		return dateStr;
	}

}
