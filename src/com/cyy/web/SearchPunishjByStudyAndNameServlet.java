package com.cyy.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyy.services.StuService;
import com.cyy.tools.Tools;

/**
 * Servlet implementation class SearchPunishjByStudyAndNameServlet
 */
@WebServlet("/searchPunishjByStudyAndName")
public class SearchPunishjByStudyAndNameServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		StuService stuService = new StuService(Tools.createdto(request));
		List<Map<String, String>> list=null;
		try {
			list = stuService.searchPunishjByStudyAndName();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(list != null) {
			request.setAttribute("info", list);
			request.setAttribute("msg", "success");
			request.getRequestDispatcher("jsp/showPunish.jsp").forward(request, response);;
		}else {
			
			request.setAttribute("msg", "error");
			request.getRequestDispatcher("jsp/showStuHonor.jsp").forward(request, response);
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
