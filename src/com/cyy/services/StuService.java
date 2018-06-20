package com.cyy.services;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cyy.tools.Tools;



/**
 * 学生信息service
 * @author cyy
 *
 */
public class StuService extends JdbcServicesSupport{

	public StuService(Map<String, Object> dto) {
		super(dto);
	}

	public StuService() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Map<String,String> getOne() throws NoSuchAlgorithmException{
		String sql = "select * from STU_INFO where STU_LOGIN_NAME=? and STU_PWD=?";
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

	public List<Map<String, String>> serachByGrade() throws Exception {
		
		String sql = (dto.get("grade")==null||dto.get("grade").toString().equals(""))?"select * from stu_info":"select * from  stu_info where stu_grade like ? ";
		Object[]param = {"%"+dto.get("grade")+"%"};
		return (dto.get("grade")==null||dto.get("grade").toString().equals(""))?this.queryForList(sql):this.queryForList(sql,param);

		
	}

	public boolean updateStuGrade() throws Exception {
		String sql = "update stu_info set stu_grade = ? where stu_id = ?";
		Object[] param = {dto.get("grade"),dto.get("id")};
		return this.executeUpdate(sql, param);
	}

	public List<Map<String, String>> serachByAcademy() throws Exception {
		String sql = (dto.get("academy")==null||dto.get("academy").toString().equals(""))?"select * from stu_info":"select * from  stu_info where stu_academy like ?";
		Object[]param = {"%"+dto.get("academy")+"%"};
		return (dto.get("academy")==null||dto.get("academy").toString().equals(""))?this.queryForList(sql):this.queryForList(sql,param);

	}

	public boolean updateStuAcademy() throws Exception {
		String sql = "update stu_info set stu_academy = ? where stu_id = ?";
		Object[] param = {dto.get("academy"),dto.get("id")};
		return this.executeUpdate(sql, param);
	}

	public List<Map<String, String>> serachByMajor() throws Exception {
		String sql = (dto.get("major")==null||dto.get("major").toString().equals(""))?"select * from stu_info":"select * from  stu_info where stu_major like ?";
		Object[]param = {"%"+dto.get("major")+"%"};
		return (dto.get("major")==null||dto.get("major").toString().equals(""))?this.queryForList(sql):this.queryForList(sql,param);
	}

	public boolean updateStuMajor() throws Exception {
		String sql = "update stu_info set stu_major = ? where stu_id = ?";
		Object[] param = {dto.get("major"),dto.get("id")};
		return this.executeUpdate(sql, param);
	}

	public List<Map<String, String>> serachByClass() throws Exception {
		String sql = (dto.get("class")==null||dto.get("class").toString().equals(""))?"select * from stu_info":"select * from  stu_info where stu_class like ?";
		Object[]param = {"%"+dto.get("class")+"%"};
		return (dto.get("class")==null||dto.get("class").toString().equals(""))?this.queryForList(sql):this.queryForList(sql,param);
	}

	public boolean updateStuClass() throws Exception {
		String sql = "update stu_info set stu_class = ? where stu_id = ?";
		Object[] param = {dto.get("class"),dto.get("id")};
		return this.executeUpdate(sql, param);
	}

	public List<Map<String, String>> showGeneralInfo() throws Exception {
		String sql = (dto.get("study_id")==null||dto.get("study_id").toString().equals(""))?"select * from stu_info":"select * from  stu_info where stu_study_id = ?";
		Object[]param = {dto.get("study_id")};
		return (dto.get("study_id")==null||dto.get("study_id").toString().equals(""))?this.queryForList(sql):this.queryForList(sql,param);
	}

	public boolean updateStuGeneral() throws Exception {
		String sql = "update stu_info set stu_sex=?,stu_name= ?,stu_study_id=?,stu_nation=?,stu_poltic=?,stu_health=?,stu_marry=? where stu_id=?";
		Object[] param = {dto.get("stu_sex"),dto.get("stu_name"),dto.get("stu_study_id"),dto.get("stu_nation"),dto.get("stu_poltic"),dto.get("stu_health"),dto.get("stu_marry"),dto.get("stu_id")};
		return this.executeUpdate(sql, param);
	}

	public Map<String, String> searchStuById() throws Exception {
		String sql = "select * from stu_info where stu_id=?";
		Object[] param = {dto.get("id")};
		return this.queryForMap(sql, param);
 	}

	public List<Map<String, String>> searchStuByStudyAndName() throws Exception {
	System.out.println(dto);
	String sql = "select a.stu_name,b.* from  stu_info  a  left join stu_honor b on a.stu_study_id = b.stu_study_id and a.stu_study_id =? and a.stu_name like ? ";
	//Object[] param = {dto.get("stu_study_id"),"%"+dto.get("stu_name").toString()+"%"};
	String study_id= dto.get("study_id")==null?"":dto.get("study_id").toString();
	String stu_name = dto.get("stu_name")==null?"":dto.get("stu_name").toString();
	if(study_id.equals("")&&!stu_name.equals("")) {
		sql = "select a.stu_name,b.* from  stu_info  a  right join stu_honor b on a.stu_study_id = b.stu_study_id where  a.stu_name like ? ";
		Object[] param = {"%"+stu_name+"%"};
		return this.queryForList(sql, param);
	}else if(!study_id.equals("")&&stu_name.equals("")) {
		sql = "select a.stu_name,b.* from  stu_info  a  right join stu_honor b on a.stu_study_id = b.stu_study_id where  a.stu_study_id = ? ";
		Object[] param = {study_id};
		return this.queryForList(sql, param);
	}else if(!study_id.equals("")&&!stu_name.equals("")) {
		sql = "select a.stu_name,b.* from  stu_info  a  right join stu_honor b on a.stu_study_id = b.stu_study_id where  a.stu_study_id = ? and a.stu_name like ?";
		Object[] param = {study_id,"%"+stu_name+"%"};
		return this.queryForList(sql, param);
	}else {
		sql = "select a.stu_name,b.* from  stu_info  a  left join stu_honor b on a.stu_study_id = b.stu_study_id";
		return this.queryForList(sql);
	}
	
	
	}

	public Map<String, String> searchHonorById() throws Exception {
	String sql="select * from stu_honor where honor_id =?";
	Object[] param = {dto.get("id")};
	return this.queryForMap(sql, param);
	}

	public boolean updateHonorInfo() throws Exception {
		String sql="update stu_honor set honor_time=?,honor_cause=?,honor_prove=? where honor_id=?";
		Object[] param = {dto.get("honor_time"),dto.get("honor_cause"),dto.get("honor_prove"),dto.get("honor_id")};
		return this.executeUpdate(sql, param);
	}

	public boolean addHonorInfo() throws Exception {
		String sql = "insert into stu_honor(stu_study_id,honor_time,honor_cause,honor_prove) values(?,?,?,?) ";
		Object[] param = {dto.get("stu_study_id"),dto.get("hobor_time"),dto.get("honor_cause"),dto.get("honor_prove")};
		return this.executeUpdate(sql, param);
	}

	public List<Map<String, String>> searchPunishjByStudyAndName() throws Exception {
		String stu_study_id= dto.get("study_id")==null?"":dto.get("study_id").toString();
		String stu_name = dto.get("stu_name")==null?"":dto.get("stu_name").toString();
		if(stu_study_id.equals("")&&!stu_name.equals("")) {
			String sql = "select b.stu_name,a.* from  stu_punish  a  left join stu_info b on a.stu_study_id = b.stu_study_id where  b.stu_name like ? ";
			Object[] param = {"%"+stu_name+"%"};
			return this.queryForList(sql, param);
		}else if(!stu_study_id.equals("")&&stu_name.equals("")) {
			String sql = "select b.stu_name,a.* from  stu_punish  a  left join stu_info b on a.stu_study_id = b.stu_study_id where  b.stu_study_id = ? ";
			Object[] param = {stu_study_id};
			return this.queryForList(sql, param);
		}else if(!stu_study_id.equals("")&&!stu_name.equals("")) {
			String sql = "select b.stu_name,a.* from  stu_punish  a  left join stu_info b on a.stu_study_id = b.stu_study_id  where  b.stu_study_id = ? and b.stu_name like ?";
			Object[] param = {stu_study_id,"%"+stu_name+"%"};
			return this.queryForList(sql, param);
		}else {
			String sql = "select b.stu_name,a.* from  stu_punish  a  left join stu_info b on a.stu_study_id = b.stu_study_id ";
			return this.queryForList(sql);
		}
	}

	public boolean updatePunishInfo() throws Exception {
		String sql="update stu_punish set punish_time=?,punish_cause=?,punish_level=? where punish_id=?";
		Object[] param = {dto.get("punish_time"),dto.get("punish_cause"),dto.get("punish_level"),dto.get("punish_id")};
		return this.executeUpdate(sql, param);
	}

	public Map<String, String> searchPunishById() throws Exception {
		String sql="select * from stu_punish where punish_id =?";
		Object[] param = {dto.get("id")};
		return this.queryForMap(sql, param);
	}

	public boolean addPunishInfo() throws Exception {
		String sql = "insert into stu_punish(stu_study_id,punish_time,punish_cause,punish_level) values(?,?,?,?) ";
		Object[] param = {dto.get("stu_study_id"),dto.get("punish_time"),dto.get("punish_cause"),dto.get("punish_level")};
		return this.executeUpdate(sql, param);
	}

	public Map<String, String> searchOneStuDetail() throws Exception {
		String sql =  "select  * from  stu_info where stu_id=?";
		Object[] param = {dto.get("id")};
		return this.queryForMap(sql, param);
	}

	public boolean updateFirstInfo() throws Exception {
		String sql = "update STU_INFO set stu_name = ?,stu_former_name=?,stu_study_id=?,stu_sex=?,stu_nation=?,stu_native=?,stu_academy=?,stu_major=?,stu_class=?,stu_tel=?,"
				+ "stu_home_tel=?,stu_address=?,stu_birth=?,stu_type=?,stu_year=?,stu_poltic=?,stu_marry=?,stu_health=? where stu_id=?";
		Object[] param = {dto.get("stu_name"),dto.get("stu_former_name"),dto.get("stu_study_id"),dto.get("stu_sex"),dto.get("stu_nation"),dto.get("stu_native"),dto.get("stu_academy"),dto.get("stu_major"),dto.get("stu_class"),dto.get("stu_tel"),
				dto.get("stu_home_tel"),dto.get("stu_address"),dto.get("stu_birth"),dto.get("stu_type"),dto.get("stu_year"),dto.get("stu_poltic"),dto.get("stu_marry"),dto.get("stu_health"),dto.get("stu_id")
				
		};
		return this.executeUpdate(sql, param);
	}

	public List<Map<String, String>> searchOneStuHonor() throws Exception {
		String sql = "select a.stu_name,b.* from  stu_info  a  right join stu_honor b on a.stu_study_id = b.stu_study_id where  a.stu_study_id = ? ";
		Object[] param  = {dto.get("id")};
		return this.queryForList(sql, param);
		
		
 	}

	public List<Map<String, String>> searchOneStuPunish() throws Exception {
		String sql = "select b.stu_name,a.* from  stu_punish  a  right join stu_info b on a.stu_study_id = b.stu_study_id where  b.stu_study_id = ? ";
		Object[] param = {dto.get("id")};
		return this.queryForList(sql, param);
	}

	public boolean addPicPath() throws Exception {
		String sql="update stu_info set imgpath =? where stu_id=?";
		Object[] param= {dto.get("imgPath").toString().substring(dto.get("imgPath").toString().lastIndexOf("\\")+1),dto.get("id")};
		return this.executeUpdate(sql, param);
	}
	
	
	
	
}
