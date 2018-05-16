package com.cyy.web;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyy.services.ManagerService;

/**
 * Servlet implementation class ShowAllManager
 */
@WebServlet("/showAllManager")
public class ShowAllManagerServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManagerService managerService = new ManagerService();
		List<Map<String, String>> list = null;
		try {
			list = managerService.showAllManager();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(list!=null && list.size()!=0) {
			request.setAttribute("info", list);
			request.setAttribute("msg", "success");
		}else {
			request.setAttribute("msg", "error");
			
		}
		request.getRequestDispatcher("jsp/showAllManager.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
