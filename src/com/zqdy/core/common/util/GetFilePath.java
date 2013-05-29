package com.zqdy.core.common.util;

public class GetFilePath {

	/**
	 * 
	 * @param c  类所在的路径 
	 * @param filename   �?��文件夹的名字 例如:WEB-INF
	 * @return
	 */
	public static String getAddress(Class c, String folder,String tempfolder,String filename) {
		Class theClass = c;
		java.net.URL u = theClass.getResource("");
		// str会得到这个函数所在类的路�?
		String str = u.toString();
		// 截去�?��前面6个无用的字符
		str = str.substring(6, str.length());
		// �?20换成空格（如果文件夹的名称带有空格的话，会在取得的字符串上变�?20�?
		str = str.replaceAll("%20", " ");
		// 查找“WEB-INF”在该字符串的位�?
		int num = str.indexOf(folder);
		// 截取即可
		str = str.substring(0, num + folder.length()); 
		if(tempfolder!=null&&tempfolder!="")
		str=str+"/"+tempfolder;
		return str+"/"+filename;
	}
}
