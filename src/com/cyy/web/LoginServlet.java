package com.cyy.web;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cyy.services.ManagerService;
import com.cyy.services.StuService;
import com.cyy.tools.Tools;

/**
 * Servlet implementation class LoginServlet
 * 统一的登录处理servlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//打印前台传递的参数
		/*System.out.println(Tools.createdto(request));*/
		HttpSession session = request.getSession();
		if(session.getAttribute("stu_info")!=null) {
			session.removeAttribute("stu_info");
		}if(session.getAttribute("manager_info")!=null) {
			session.removeAttribute("manager_info");
		}
		String role = request.getParameter("role");
		if(role.equals("1")) {
			ManagerService managerService = new ManagerService(Tools.createdto(request));
			Map<String, String> map = null;
			try {
				map = managerService.getOne();
				
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(map != null) {
				request.getSession().setAttribute("manager_info", map);
				response.getWriter().write("{\"msg\":\"success\"}");
			}else {
				response.setContentType("application/json; charset=utf-8");  
				response.getWriter().write("{\"msg\":\"用户名或者密码错误\"}");
			}
		}else {
			StuService stuService = new StuService(Tools.createdto(request));
			Map<String, String> map = null;
			try {
				map = stuService.getOne();
				
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(map != null) {
				request.getSession().setAttribute("stu_info", map);
				response.getWriter().write("{\"msg\":\"success\"}");
			}else {
				response.setContentType("application/json; charset=utf-8");  
				response.getWriter().write("{\"msg\":\"用户名或者密码错误\"}");
			}
			
		}
			
	}

	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
