package com.zqdy.core.common;

public class Constants {

	public static final String SESSION_USER = "user";
	public static final String IS_LOGIN = "isLogin";
	
	public static final String SHOPPING_CART = "cart";
	public static final String SHOPPING_CART_ITEMS = "orderItems";
	public static final String SHOPPING_CART_ORDER = "order";
	public static final String SHOPPING_CART_GOODS_NUMBER = "goodsNumber";

	public static final String RESULT_SUCCESS = "1";
	public static final String RESULT_FAILURE = "0";
	
	/***
	 * 调度设置
	 */
	public static final String JOB_DATAMAP_SCHEMAID = "JOB_DATAMAP_SCHEMAID";
	public static final String  JOB_TRIGGER_SIMPLETRIGGER = "JOB_TRIGGER_SIMPLETRIGGER";
	public static final String  JOB_TRIGGER_CRONIGGER = "JOB_TRIGGER_CRONIGGER";
	public static final String JOB_GROUP = "JOB_GROUP";
	
	public static final String RUNSTRATEGY_ONCE = "1";
	public static final String RUNSTRATEGY_EVERY_DAY = "2";
	public static final String RUNSTRATEGY_EVERY_WEEK = "3";
	public static final String RUNSTRATEGY_EVERY_MONTH = "4";
	public static final String RUNSTRATEGY_EVERY_TIME = "5";
 
	
	/***
	 * 演员类型
	 */
	
	public static final int ACTOR = 1;     //演员
	public static final int WRITERPLAY = 2;  //编剧
	public static final int PRODUCER = 3;    //监制
	public static final int WRITER = 4;    //原著
	
	public static final int MOVIE = 0;    //电影
	public static final int TV = 1;    //电视剧
}
