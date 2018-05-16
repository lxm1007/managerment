package com.cyy.services;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cyy.tools.Tools;
/**
 * 管理员Service
 * @author cyy
 *
 */
public class ManagerService extends JdbcServicesSupport{

	public ManagerService(Map<String, Object> dto) {
		super(dto);
	}

	public ManagerService() {
		// TODO Auto-generated constructor stub
	}
	
	//登录
	public Map<String,String> getOne() throws NoSuchAlgorithmException{
		
		String sql = "select * from MANAGER_INFO where MANAGER_LOGIN_NAME=? and MANAGER_PWD=?";
		//md5加密密码
		Object[] obj = {dto.get("user"),Tools.getMD5String(dto.get("pass").toString())};
		Map<String,String> map =new HashMap<>();
		try {
			 map = this.queryForMap(sql, obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return map;
		
	}

	//检验密码
	public Boolean checkPwd() throws Exception {
		String sql = "select * from  manager_info where manager_id =? and manager_pwd = ? ";
		Object []param = {dto.get("id"),Tools.getMD5String(dto.get("pwd").toString())};
		for(Object temp:param) {
			System.out.println(temp);
		}
		Map<String,String> map=null;
		map = this.queryForMap(sql, param);
		if(map==null) {
			return false;
		}else {
			return true;
		}
	}

	//更新密码
	public Boolean updatePwd() throws Exception {
		
		String sql = "update manager_info set manager_pwd = ? where manager_id = ?";
		Object[] param = {Tools.getMD5String(dto.get("pwd").toString()),dto.get("id")};
		return  this.executeUpdate(sql, param);
		
	}

	//添加账号
	public boolean addOneManager() throws Exception {

	String sql = "insert into manager_info(manager_login_name,manager_pwd,manager_type) values(?,?,2)";
	Object[] param = {dto.get("account"),Tools.getMD5String(dto.get("pwd").toString())};
	return this.executeUpdate(sql, param);
	}

	//检查账号是否可用
	public boolean checkAccount() throws Exception {
		// TODO Auto-generated method stub
		String sql = "select * from manager_info  where manager_login_name = ?";
		Object[] param = {dto.get("account")};
		Map<String,String> map = this.queryForMap(sql, param);
		if(map!=null) {
			return false;
		}else {
			return true;
		}
	}

	public List<Map<String, String>> showAllManager() throws Exception {
		String sql = "select * from  manager_info";
		return  this.queryForList(sql);
	}

	public Map<String, String> searchOneManager() throws Exception {
		String sql = "select * from  manager_info  where  manager_id = ?";
		Object[] param = {dto.get("id")};
		return this.queryForMap(sql, param);
	}


	
}
