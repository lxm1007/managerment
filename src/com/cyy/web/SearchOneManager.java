package com.cyy.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cyy.services.ManagerService;
import com.cyy.tools.Tools;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class SearchOneManager
 * ÐÞ¸ÄÇ°²éÑ¯
 */
@WebServlet("/searchOneManager")
public class SearchOneManager extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ManagerService managerService = new ManagerService(Tools.createdto(request));
		Map<String, String> map =  null;
		try {
			
			map = managerService.searchOneManager();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(map != null) {
			map.put("msg", "success");
			response.getWriter().write(new GsonBuilder().enableComplexMapKeySerialization().create().toJson(map));
			
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
