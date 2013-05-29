package com.zqdy.dao.po;

import java.util.Date;
import java.util.Date;

/**
 * TScheduleModel entity. @author MyEclipse Persistence Tools
 */

public class TScheduleModel implements java.io.Serializable {

	// Fields

	private Integer id;
	private String scheduleName;
	private String runStrategy;
	private String startTime;
	private String stopTime;
	private String runFormatStr;
	private Integer runPeriod;
	private Integer tvType;
	private Integer scheduleType;
	private TSource source;
	private String linkUrl;
	private String linkRegex;
	private String embedRegex;
	private String detailregex;
	private String charset;
	private Integer isActive;
	private Date addTime;
	private String runWeek;
	private String runMonth;
	private String runMonthDay;
	private String autoRunTime;
	private Integer runPeriodHour;
	private Integer runPeriodMinute;
	private Integer snatchSize;

	// Constructors

	/** default constructor */
	public TScheduleModel() {
	}

	/** full constructor */
	public TScheduleModel(String scheduleName, String runStrategy,
			String startTime, String stopTime, String runFormatStr,
			Integer runPeriod, Integer tvType, Integer scheduleType,
			TSource source, String linkUrl, String linkRegex,
			String embedRegex, String detailregex, String charset,
			Integer isActive, Date addTime, String runWeek,
			String runMonth, String runMonthDay, String autoRunTime,
			Integer runPeriodHour, Integer runPeriodMinute, Integer snatchSize) {
		this.scheduleName = scheduleName;
		this.runStrategy = runStrategy;
		this.startTime = startTime;
		this.stopTime = stopTime;
		this.runFormatStr = runFormatStr;
		this.runPeriod = runPeriod;
		this.tvType = tvType;
		this.scheduleType = scheduleType;
		this.source = source;
		this.linkUrl = linkUrl;
		this.linkRegex = linkRegex;
		this.embedRegex = embedRegex;
		this.detailregex = detailregex;
		this.charset = charset;
		this.isActive = isActive;
		this.addTime = addTime;
		this.runWeek = runWeek;
		this.runMonth = runMonth;
		this.runMonthDay = runMonthDay;
		this.autoRunTime = autoRunTime;
		this.runPeriodHour = runPeriodHour;
		this.runPeriodMinute = runPeriodMinute;
		this.snatchSize = snatchSize;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getScheduleName() {
		return this.scheduleName;
	}

	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}

	public String getRunStrategy() {
		return this.runStrategy;
	}

	public void setRunStrategy(String runStrategy) {
		this.runStrategy = runStrategy;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStopTime() {
		return this.stopTime;
	}

	public void setStopTime(String stopTime) {
		this.stopTime = stopTime;
	}

	public String getRunFormatStr() {
		return this.runFormatStr;
	}

	public void setRunFormatStr(String runFormatStr) {
		this.runFormatStr = runFormatStr;
	}

	public Integer getRunPeriod() {
		return this.runPeriod;
	}

	public void setRunPeriod(Integer runPeriod) {
		this.runPeriod = runPeriod;
	}

	public Integer getTvType() {
		return this.tvType;
	}

	public void setTvType(Integer tvType) {
		this.tvType = tvType;
	}

	public Integer getScheduleType() {
		return this.scheduleType;
	}

	public void setScheduleType(Integer scheduleType) {
		this.scheduleType = scheduleType;
	}

	public TSource getSource() {
		return this.source;
	}

	public void setSource(TSource source) {
		this.source = source;
	}

	public String getLinkUrl() {
		return this.linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getLinkRegex() {
		return this.linkRegex;
	}

	public void setLinkRegex(String linkRegex) {
		this.linkRegex = linkRegex;
	}

	public String getEmbedRegex() {
		return this.embedRegex;
	}

	public void setEmbedRegex(String embedRegex) {
		this.embedRegex = embedRegex;
	}

	public String getDetailregex() {
		return this.detailregex;
	}

	public void setDetailregex(String detailregex) {
		this.detailregex = detailregex;
	}

	public String getCharset() {
		return this.charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public Integer getIsActive() {
		return this.isActive;
	}

	public void setIsActive(Integer isActive) {
		this.isActive = isActive;
	}

	public Date getAddTime() {
		return this.addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getRunWeek() {
		return this.runWeek;
	}

	public void setRunWeek(String runWeek) {
		this.runWeek = runWeek;
	}

	public String getRunMonth() {
		return this.runMonth;
	}

	public void setRunMonth(String runMonth) {
		this.runMonth = runMonth;
	}

	public String getRunMonthDay() {
		return this.runMonthDay;
	}

	public void setRunMonthDay(String runMonthDay) {
		this.runMonthDay = runMonthDay;
	}

	public String getAutoRunTime() {
		return this.autoRunTime;
	}

	public void setAutoRunTime(String autoRunTime) {
		this.autoRunTime = autoRunTime;
	}

	public Integer getRunPeriodHour() {
		return this.runPeriodHour;
	}

	public void setRunPeriodHour(Integer runPeriodHour) {
		this.runPeriodHour = runPeriodHour;
	}

	public Integer getRunPeriodMinute() {
		return this.runPeriodMinute;
	}

	public void setRunPeriodMinute(Integer runPeriodMinute) {
		this.runPeriodMinute = runPeriodMinute;
	}

	public Integer getSnatchSize() {
		return this.snatchSize;
	}

	public void setSnatchSize(Integer snatchSize) {
		this.snatchSize = snatchSize;
	}

}