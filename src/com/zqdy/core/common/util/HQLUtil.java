package com.zqdy.core.common.util;

public final class HQLUtil {
	public static String ignoreSpecialChar(String input) {
		
		if (input.indexOf("'") != -1) {
			input = input.replace("'", "''");
		}
		if (input.indexOf("[") != -1) {
			input = input.replace("[", "[[]");
		}
		if (input.indexOf("%") != -1) {
			input = input.replace("%", "[%]");
		}
		if (input.indexOf("_") != -1) {
			input = input.replace("_", "[_]");
		}
		
	
		
		return input;
	}
	/**
	 * Convert SQL String Parameter
	 * @param sqlPara
	 * @param paraType <br>
	 * 		* <b>'S'/'s'</b>: String, <font color="red">'</font>(<font color="blue">single quotes</font>) will be replaced by <font color="red">''</font>(<font color="red">double single quotes</font>)<br>
	 * 		* <b>'I'/'i'</b>: Integer<br>
	 * 		* <b>'F'/'f'</b>: Float/Double/Decimal/percentage<br>
	 * 		* <b>'D'/'d'</b>: Date/DateTime<br>
	 * 		* <b>'L'/'l'</b>: String(<font color="blue">the like condition the SQL string</font>),<font color="red">',[,%,_</font> will be replaced by <font color="red">'',[[],[%],[_]</font><br>
	 * 		* <b>'X'/'x'</b>: Fixed String, <font color="red"><b>Make sure your Parameter is security</b></font><br>
	 * @return 
	 * @author Peng yingchun
	 * @return 
	 */
	public static String convertSqlPara(String sqlPara,char paraType){
		String strPara = "";
		if(sqlPara != null){
			strPara = sqlPara.trim();
		}
		String strReturn = strPara;
		switch(paraType){
		case 'S':
		case 's':
			strReturn = strPara.replace("'", "''");
			break;
		case 'I':
		case 'i':
			if(strPara.equals("")){strPara="''";}
			strReturn = strPara;
			break;
		case 'F':
		case 'f':
			if(strPara.equals("")){strPara="''";}
			strReturn = strPara;
			break;
		case 'D':
		case 'd':
			if(strPara.equals("")){strPara="''";}
			strReturn = strPara;
			break;
		case 'L':
		case 'l':
			strReturn = strPara.replace("'", "''").replace("[", "[[]").replace("%", "[%]").replace("_", "[_]");
			break;
		case 'X':
		case 'x':
			strReturn = strPara;
			break;			
		default:
			break;
		}
		return strReturn;
	}

	

	
}