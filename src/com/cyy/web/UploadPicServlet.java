package com.cyy.web;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.cyy.services.StuService;


/**
 * Servlet implementation class UploadPicServlet
 */
@WebServlet("/uploadPic")
public class UploadPicServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String savePath = this.getServletContext().getRealPath("/WEB-INF/upload");
		// 创建一个DiskFileItemFactory工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		String filename = "";
		String id = "";
		// 创建一个文件上传解析器
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 判断提交上来的数据是否是上传表单的数据
		if (!ServletFileUpload.isMultipartContent(request)) {
		// 按照传统方式获取数据
		return;
		}
		// 使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入
		List<FileItem> list = null;
		try {
			list = upload.parseRequest(request);
		} catch (FileUploadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (FileItem item : list) {
		// 如果fileitem中封装的是普通输入项的数据
		if (item.isFormField()) {
		String name = item.getFieldName();
		// 转码
		id = item.getString("UTF-8");
		id = new String(id.getBytes("iso-8859-1"), "UTF-8");
		System.out.println(name + "=" + id);
		} else {
		// 如果fileitem中封装的是上传文件
		//得到文件名
		filename = item.getName();
		System.out.println("filename=" + filename);
		if (filename == null || filename.trim().equals("")) {
		continue;
		}
		}
		}
		//处理获取到的上传文件的文件名的路径部分，只保留文件名部分
		filename = filename.substring(filename.lastIndexOf("\\") + 1);
		//tomcat下的目录
		//String path_name = "E:\\apache-tomcat-8.0.51\\webapps\\managerment\\static\\img\\"+filename;
		//eclipse下的目录
		String path_name = "G:\\project\\managerment\\WebContent\\static\\img\\"+filename;
		//获取item中的上传文件的输入流
		InputStream in = list.get(0).getInputStream();
		//创建缓冲区
		byte buffer[] = new byte[1024];
		//创建输出流对象，用于将缓冲区的数据读出到保存路径
		System.out.println("save_path=="+savePath+ "\\" + filename);
		FileOutputStream output = new FileOutputStream(path_name);
		//判断输入流中的数据是否已经读完
		int len = 0;
		//循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示输入流中还有数据
		while ((len = in.read(buffer)) > 0) {
		//使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
		output.write(buffer, 0, len);
		}
		
		output.flush();
		output.close();
		response.setContentType("image/png");

		String path = savePath+ "\\" + filename;
		Map<String,Object> map = new HashMap<>();
		map.put("imgPath", path_name);
		map.put("id", id);
		StuService stuService = new StuService(map);
		try {
			stuService.addPicPath();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedImage bi = ImageIO.read(new File(path_name));

		ImageIO.write(bi, "png", response.getOutputStream());
		request.setAttribute("picPath", path);

		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
