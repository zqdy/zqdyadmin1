//屏蔽页的删除按钮
 function KeyDown(){   
	 if(event.srcElement.type!='text'&&event.srcElement.type!='textarea')   
	      if   (event.keyCode==8)  {     
            event.keyCode=0;   
            event.returnValue=false;   
      }   
 }   
 document.onkeydown=KeyDown;   
 
 function formatFloat(src, pos){
    return Math.round(src*Math.pow(10, pos))/Math.pow(10, pos);
}

function findObj(theObj, theDoc){
	var p, i, foundObj;
	if(!theDoc) theDoc = document;
	if( (p = theObj.indexOf("?")) > 0 && parent.frames.length)
	{
	    theDoc = parent.frames[theObj.substring(p+1)].document;
	    theObj = theObj.substring(0,p);
	}
	if(!(foundObj = theDoc[theObj]) && theDoc.all) foundObj = theDoc.all[theObj];
	for (i=0; !foundObj && i < theDoc.forms.length; i++)
	    foundObj = theDoc.forms[i][theObj];
	for(i=0; !foundObj && theDoc.layers && i < theDoc.layers.length; i++)
	    foundObj = findObj(theObj,theDoc.layers[i].document);
	if(!foundObj && document.getElementById) foundObj = document.getElementById(theObj);
	return foundObj;
}

function selectAll(obj){
	var selectValue = obj.checked;
	var code_Values = document.all['mId']; 
	if(code_Values.length){ 
		for(var i=0;i<code_Values.length;i++) 
		{ 
			if(selectValue){
				code_Values[i].checked = true; 
			}else{
				code_Values[i].checked = false; 
			}
		} 
		}else{ 
		code_Values.checked = true; 
	}
}

function goTo(url){
	window.location.href=url;
}
function moreSubTv(divId){
	$("#moreSubTv"+divId).show();	
	$("#lessSubTvBtn"+divId).show();
	$("#moreSubTvA"+divId).hide();	
	$("#moreSubTvBtn"+divId).hide();	
}
function lessSubTv(divId){
	$("#moreSubTv"+divId).hide();	
	$("#lessSubTvBtn"+divId).hide();
	$("#moreSubTvA"+divId).show();	
	$("#moreSubTvBtn"+divId).show();	
}
			