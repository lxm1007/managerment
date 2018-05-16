package com.cyy.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyy.services.ManagerService;
import com.cyy.tools.Tools;

/**
 * Servlet implementation class CheckAccount
 * ��ѯ�˺��Ƿ��ظ�
 */
@WebServlet("/checkAccount")
public class CheckAccount extends HttpServlet {
	

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ManagerService managerService = new ManagerService(Tools.createdto(request));
		boolean f = false;
		try {
			f = managerService.checkAccount();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(f) {
			response.getWriter().write("{\"msg\":\"success\"}");
		}else {
			response.getWriter().write("{\"msg\":\"error\"}");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
