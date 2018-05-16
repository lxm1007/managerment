package com.cyy.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyy.services.StuService;
import com.cyy.tools.Tools;

/**
 * Servlet implementation class UpdateStuMajorServlet
 */
@WebServlet("/updateStuMajor")
public class UpdateStuMajorServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StuService stuService = new  StuService(Tools.createdto(request));
		boolean f=false;
		try {
			f = stuService.updateStuMajor();
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
