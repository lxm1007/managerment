package com.cyy.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyy.services.StuService;
import com.cyy.tools.Tools;

/**
 * Servlet implementation class SearchOneStuDetail
 */
@WebServlet("/searchOneStuDetail")
public class SearchOneStuDetailServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		StuService stuService = new StuService(Tools.createdto(request));
		Map<String, String> map = null;
		try {
			map = stuService.searchOneStuDetail();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(map != null) {
			map.put("msg", "success");
			request.setAttribute("info", map);
			request.getRequestDispatcher("jsp/showDetail.jsp").forward(request, response);;
		
		}else {
			map.put("msg", "error");
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
