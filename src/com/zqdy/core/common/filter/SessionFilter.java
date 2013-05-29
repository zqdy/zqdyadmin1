package com.zqdy.core.common.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionFilter extends HttpServlet implements javax.servlet.Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest servletRequest = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		HttpSession session = servletRequest.getSession();
		// 获取当前页面文件�?
		String url = servletRequest.getRequestURI();
		String postfix = "";
		url = url.substring(url.lastIndexOf("/") + 1, url.length());
		if (url.indexOf(".") != -1) {
			postfix = url.substring(url.lastIndexOf(".") + 1, url.length());
		} else {
			postfix = url;
		}
		String accessPath = servletRequest.getContextPath();
		String excludeFile = "goLogin.action,login.action,loginOut.action,doLogin.action,loginOut.action,top.jsp,menuAction.action,my_destop.htm,bottom.html,sessionOut.jsp,action";
		String excludePostfix = "css,js,gif,xml,jpg";
		if (excludeFile.indexOf(url) == -1
				&& excludePostfix.indexOf(postfix) == -1) {

			String userCode = (String) session.getAttribute("userCode");

			if (userCode == null) {

				// request.getRequestDispatcher(accessPath+"/sessionOut.jsp").forward(request,response);
				servletResponse.sendRedirect(accessPath + "/sessionOut.jsp");
			} else {

				chain.doFilter(request, response);

			}

		} else {		
				chain.doFilter(request, response);
			
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
