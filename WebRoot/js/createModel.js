function scheduleTypeChange(typeObj){
	if(typeObj.value==1){
		serverTr.style.display='block';
		linkTr.style.display='block';
		movieTr.style.display='none';
		charsetTr.style.display='none';
	}else if(typeObj.value==2){
		serverTr.style.display='block';
		linkTr.style.display='none';
		movieTr.style.display='block';
		charsetTr.style.display='block';
	}else if(typeObj.value==3){
		linkTr.style.display='none';
		movieTr.style.display='none';
		charsetTr.style.display='none';
		serverTr.style.display='none';
		 
	}
}

//隐藏时间策略
function hideAllAutotaskRun(){
	autoRun_time.style.display='none';
	autoRun_dtStart.style.display='none';
	autoRun_stopTime.style.display='none';
	//autoRun_incTime.style.display='none';
	//autoRun_day.style.display='none';
	//autoRun_week1.style.display='none';
	autoRun_week2.style.display='none';
	autoRun_month1.style.display='none';
	autoRun_month2.style.display='none';
	autoRun_HM.style.display='none';
//	document.all.submitBtn2.style.visibility='visible';
}
	
//显示每天
function showEveryDateRun(){
	autoRun_time.style.display='block';
	autoRun_dtStart.style.display='block';
	autoRun_stopTime.style.display='block';
	//autoRun_incTime.style.display='block';
	//autoRun_day.style.display='block';
	//autoRun_week1.style.display='none';
	autoRun_week2.style.display='none';
	autoRun_month1.style.display='none';
	autoRun_month2.style.display='none';
	autoRun_HM.style.display='none';
//	document.all.submitBtn2.style.visibility='hidden';
}
	
//显示每星期
function showEveryWeekRun(){
	autoRun_time.style.display='block';
	autoRun_dtStart.style.display='block';
	autoRun_stopTime.style.display='block';
	//autoRun_incTime.style.display='block';
	//autoRun_day.style.display='none';
	//autoRun_week1.style.display='block';
	autoRun_week2.style.display='block';
	autoRun_month1.style.display='none';
	autoRun_month2.style.display='none';
	autoRun_HM.style.display='none';
//	document.all.submitBtn2.style.visibility='hidden';
}
	
//显示每月
function showEveryMonthRun(){
	autoRun_time.style.display='block';
	autoRun_dtStart.style.display='block';
	autoRun_stopTime.style.display='block';
	//autoRun_incTime.style.display='block';
	//autoRun_day.style.display='none';
	//autoRun_week1.style.display='none';
	autoRun_week2.style.display='none';
	autoRun_month1.style.display='block';
	autoRun_month2.style.display='block';
	autoRun_HM.style.display='none';
//	document.all.submitBtn2.style.visibility='hidden';
}

//显示时分
function showEveryTime(){
	autoRun_time.style.display='none';
	autoRun_dtStart.style.display='block';
	autoRun_stopTime.style.display='block';
	//autoRun_incTime.style.display='block';
	//autoRun_day.style.display='none';
	//autoRun_week1.style.display='none';
	autoRun_week2.style.display='none';
	autoRun_month1.style.display='none';
	autoRun_month2.style.display='none';
	autoRun_HM.style.display='block';
//	document.all.submitBtn2.style.visibility='hidden';
}