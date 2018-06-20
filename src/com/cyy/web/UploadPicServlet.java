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
		// ����һ��DiskFileItemFactory����
		DiskFileItemFactory factory = new DiskFileItemFactory();
		String filename = "";
		String id = "";
		// ����һ���ļ��ϴ�������
		ServletFileUpload upload = new ServletFileUpload(factory);
		// �ж��ύ�����������Ƿ����ϴ���������
		if (!ServletFileUpload.isMultipartContent(request)) {
		// ���մ�ͳ��ʽ��ȡ����
		return;
		}
		// ʹ��ServletFileUpload�����������ϴ����ݣ�����������ص���һ��List<FileItem>���ϣ�ÿһ��FileItem��Ӧһ��Form��������
		List<FileItem> list = null;
		try {
			list = upload.parseRequest(request);
		} catch (FileUploadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		for (FileItem item : list) {
		// ���fileitem�з�װ������ͨ�����������
		if (item.isFormField()) {
		String name = item.getFieldName();
		// ת��
		id = item.getString("UTF-8");
		id = new String(id.getBytes("iso-8859-1"), "UTF-8");
		System.out.println(name + "=" + id);
		} else {
		// ���fileitem�з�װ�����ϴ��ļ�
		//�õ��ļ���
		filename = item.getName();
		System.out.println("filename=" + filename);
		if (filename == null || filename.trim().equals("")) {
		continue;
		}
		}
		}
		//�����ȡ�����ϴ��ļ����ļ�����·�����֣�ֻ�����ļ�������
		filename = filename.substring(filename.lastIndexOf("\\") + 1);
		//tomcat�µ�Ŀ¼
		//String path_name = "E:\\apache-tomcat-8.0.51\\webapps\\managerment\\static\\img\\"+filename;
		//eclipse�µ�Ŀ¼
		String path_name = "G:\\project\\managerment\\WebContent\\static\\img\\"+filename;
		//��ȡitem�е��ϴ��ļ���������
		InputStream in = list.get(0).getInputStream();
		//����������
		byte buffer[] = new byte[1024];
		//����������������ڽ������������ݶ���������·��
		System.out.println("save_path=="+savePath+ "\\" + filename);
		FileOutputStream output = new FileOutputStream(path_name);
		//�ж��������е������Ƿ��Ѿ�����
		int len = 0;
		//ѭ�������������뵽���������У�(len=in.read(buffer))>0�ͱ�ʾ�������л�������
		while ((len = in.read(buffer)) > 0) {
		//ʹ��FileOutputStream�������������������д�뵽ָ����Ŀ¼(savePath + "\\" + filename)����
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
