<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <% request.setAttribute("path", request.getContextPath()); %>
    <% request.setAttribute("picPath", request.getAttribute("picPath")); %>
     <% request.setAttribute("info", request.getAttribute("info")); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="${path }/static/bootstrap-3.3.7-dist/jquery-3.2.1.js"></script>
<link rel="stylesheet" href="${path}/static/bootstrap-3.3.7-dist/css/bootstrap.css" type="text/css" media="screen"/>
<script type="text/javascript" src="${path}/static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
</head>
<body>
<span style="color: red">图片为155*135,请裁剪后上传</span>
<form id="form" enctype="multipart/form-data" action="${path }/uploadPic" method="post" target="pic">
<div class="col-md-1" ><iframe id="frame" style="width: 160px;height: 140px" name="pic">
</iframe><input  type="file" name="file"><input type="hidden" name="id" value="${info.stu_id }"><button class="btn btn-primary btn-sm">上传</button></div>
</form>
<form class="form-inline" method="post" action="${path }/updateFirstInfo">
<div class="col-md-10 col-md-offset-1" style="padding-top: 0px;float: left">

  <div class="form-group">
    <label for="exampleInputName2">学&nbsp;&nbsp;&nbsp;&nbsp;号</label>
    <input type="text" class="form-control" name="stu_study_id" value="${info.stu_study_id }" placeholder="必填" required="required">
  </div>
  <div class="form-group">
    <label for="exampleInputName2">姓&nbsp;&nbsp;&nbsp;&nbsp;名</label>
    <input type="text" class="form-control" name="stu_name" value="${info.stu_name }" placeholder="必填" required="required">
  </div>
  <div class="form-group">
    <label for="exampleInputEmail2">曾用名</label>
    <input type="text" class="form-control" name="stu_former_name" value="${info.stu_former_name }" placeholder="非必填">
  </div>
  <div class="col-md-12"><br></div>
   <div class="form-group">
    <label for="exampleInputEmail2">性&nbsp;&nbsp;&nbsp;&nbsp;别</label>
    <input type="text" class="form-control" name="stu_sex" value="${info.stu_sex }"placeholder="必填" required="required">
  </div>
    <div class="form-group">
    <label for="exampleInputEmail2">民&nbsp;&nbsp;&nbsp;&nbsp;族</label>
    <input type="text" class="form-control" name="stu_nation" value="${info.stu_nation }" placeholder="必填" required="required">
  </div>
     <div class="form-group">
    <label for="exampleInputEmail2">籍&nbsp;&nbsp;&nbsp;&nbsp;贯</label>
    <input type="text" class="form-control" name="stu_native" value="${info.stu_native }" placeholder="必填" required="required">
  </div>
  <div class="col-md-12"><br></div>
     <div class="form-group">
    <label for="exampleInputEmail2">学&nbsp;&nbsp;&nbsp;&nbsp;院</label>
    <input type="text" class="form-control" name="stu_academy" value="${info.stu_academy }" placeholder="必填" required="required">
  </div>
    <div class="form-group">
    <label for="exampleInputEmail2">专&nbsp;&nbsp;&nbsp;&nbsp;业</label>
    <input type="text" class="form-control" name="stu_major" value="${info.stu_major }" placeholder="必填" required="required">
  </div>
     <div class="form-group">
    <label for="exampleInputEmail2">班&nbsp;&nbsp;&nbsp;&nbsp;级</label>
    <input type="text" class="form-control"  name="stu_class" value="${info.stu_class }" placeholder="必填" required="required">
  </div>
    <div class="col-md-12"><br><br><br></div>
    </div>
    <div class="col-md-11 col-md-offset-1" >
         <div class="form-group">
    <label for="exampleInputEmail2">电&nbsp;&nbsp;&nbsp;&nbsp;话</label>
    <input type="text" name="stu_tel" class="form-control" value="${info.stu_tel }" placeholder="必填" required="required">
  </div>
    <div class="form-group">
    <label for="exampleInputEmail2">固定电话</label>
    <input type="text" name="stu_home_tel" class="form-control" value="${info.stu_home_tel }" placeholder="非必填">
  </div>
     <div class="form-group">
    <label for="exampleInputEmail2">住&nbsp;&nbsp;&nbsp;&nbsp;址</label>
    <input type="text" name="stu_address" class="form-control" value="${info.stu_address }" placeholder="必填" required="required">
  </div>

    <div class="col-md-12"><br></div>
 
   
    <div class="form-group">
    <label for="exampleInputEmail2">出生日期</label>
    <input type="text" class="form-control" name="stu_birth" value="${info.stu_birth }" placeholder="必填" required="required">
  </div>
    <div class="form-group">
    <label for="exampleInputEmail2">层&nbsp;&nbsp;&nbsp;&nbsp;次</label>
    <input type="text" class="form-control" name="stu_type" value="${info.stu_type }" placeholder="必填" required="required">
  </div>
     <div class="form-group">
    <label for="exampleInputEmail2">学&nbsp;&nbsp;&nbsp;&nbsp;制</label>
    <input type="text" class="form-control"  name="stu_year" value="${info.stu_year }" placeholder="必填" required="required">
  </div>
   
  <div class="col-md-12"><br></div>
     <div class="col-md-4">
    <div class="form-group">
    <label for="exampleInputEmail2">政治面貌</label>
    <select name="stu_poltic" class="form-control" id="select1">
    <option value="共青团员">共青团员</option>
    <option value="预备党员">预备党员</option>
    <option value="中共党员">中共党员</option>
    <option value="群众">群众</option>
    </select>
  </div>
  </div>
  <div class="col-md-4">
    <div class="form-group">
    <label for="exampleInputEmail2">婚姻情况</label>
 	<select name="stu_marry" class="form-control" id="select2">
    <option value="已婚">已婚</option>
    <option value="未婚">未婚</option>
    </select>
  </div>
  </div>
  <div class="col-md-4">
     <div class="form-group">
    <label for="exampleInputEmail2">健康情况</label>
<select name="stu_health" class="form-control" id="select3">
    <option value="健康">健康</option>
    <option value="良好">良好</option>
    <option value="一般">一般</option>
    </select>
  </div>
  </div>
 
  <input type="hidden" name="stu_id" id="stu_id">
    <div class="col-md-12"><br></div>
    <div class="col-md-offset-5"><button type="submit" class="btn btn-danger">提交</button></div>
  </div>
</form>
</body>
<script type="text/javascript">
$(function(){
	var path = "${info.imgpath}";
	var real = "${path}/static/img/"+path;
	if(path!=null && path!=""){
		$("#frame").attr("src",real);
	}
	$("#stu_id").val("${info.stu_id}");
	var val1 = "${info.stu_poltic}";
	var val2 = "${info.stu_health}";
	var val3 = "${info.stu_marry}";
	$("#select1").val(val1);
	$("#select2").val(val3);
	$("#select3").val(val2);
	
	
});

</script>
</html>