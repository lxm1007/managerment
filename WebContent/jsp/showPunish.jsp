<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <% request.setAttribute("path", request.getContextPath()); %>
     <% request.setAttribute("info", request.getAttribute("info")); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="${path }/static/bootstrap-3.3.7-dist/jquery-3.2.1.js"></script>
<link rel="stylesheet" href="${path}/static/bootstrap-3.3.7-dist/css/bootstrap.css" type="text/css" media="screen"/>
<script type="text/javascript" src="${path}/static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
<style type="text/css">

.inp{
width: 100px;
}

</style>

</head>
<body>
<div class="col-md-8 col-md-offset-3" style="padding-top: 20px">
<form class="form-inline" action="${path}/searchPunishjByStudyAndName">
  <div class="form-group">
    <label class="sr-only" for="exampleInputEmail3">学号</label>
    <input type="text" class="form-control" name="study_id" placeholder="学号">
  </div>
<div class="form-group">
    <label class="sr-only" for="exampleInputEmail3">姓名</label>
    <input type="text" class="form-control" name="stu_name" placeholder="姓名">
  </div>
  <button type="submit" class="btn btn-success">查找</button>
  <button type="button" id="add" class="btn btn-primary">添加</button>
</form>

</div>
<div class="col-md-8 col-md-offset-2">
<table class="table table-striped">
<tr>
<td>序号</td>
<td>姓名</td>
<td>学号</td>
<td>处分时间</td>
<td>处分缘由</td>
<td>处分级别</td>
<td>操作</td>
</tr>
<c:forEach var="list" items="${info}" varStatus="status">
<tr>
<input type="hidden" class="hide" value="${list.punish_id}">
<td>${status.index+1}</td>
<td>${list.stu_name}</td>
<td>${list.stu_study_id}</td>
<td>${list.punish_time}</td>
<td>${list.punish_cause}</td>
<td>${list.punish_level}</td>

<td><button type="button" class="btn btn-danger update">修改</button></td>
</tr>

</c:forEach>
</table>
</div>
<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" id="modal2">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
     <br><br>
     <span id="msg2" style="text-align: center;padding-left: 100px;"></span>
     <br><br>
    </div>
  </div>
</div>

<div class="modal fade" id="modal1" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">信息修改</h4>
      </div>
      <div class="modal-body">
      <form class="form-inline" action="${path }/updatePunishInfo" method="post">
      <input type="hidden" name="punish_id" id="punish_id">
 
  <div class="form-group">
    <label for="stu_study">时间</label>
    <input type="date" name="punish_time" class="form-control" id="punish_time">
  </div>
  <div class="col-md-12"><br></div>
   <div class="form-group">
    <label for="stu_sex">缘由</label>
    <input type="text" class="form-control" name="punish_cause" id="punish_cause">
  </div>
   <div class="form-group">
    <label for="stu_nation">等级</label>
    <input type="text" class="form-control" name="punish_level" id="punish_level">
  </div>
   <div class="col-md-12"><br></div>
     
      <div class="modal-footer">
        <button type="submit" class="btn btn-danger">确认修改</button>
        </div>
        </form>
         </div>
      </div>
    </div>
  </div>

  
  <!-- 添加 -->

<div class="modal fade" id="modal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">信息添加</h4>
      </div>
      <div class="modal-body">
      <form class="form-inline" action="${path }/addPunishInfo" method="post">
      <input type="hidden" name="punish_id" id="punish_id">
 <div class="form-group">
    <label for="stu_study">学号</label>
    <input type="text" name="stu_study_id" class="form-control" >
  </div>
  <div class="form-group">
    <label for="stu_study">时间</label>
    <input type="date" name="punish_time" class="form-control">
  </div>
  <div class="col-md-12"><br></div>
   <div class="form-group">
    <label for="stu_sex">缘由</label>
    <input type="text" class="form-control" name="punish_cause">
  </div>
   <div class="form-group">
    <label for="stu_nation">等级</label>
    <input type="text" class="form-control" name="punish_level">
  </div>
   <div class="col-md-12"><br></div>
     
      <div class="modal-footer">
        <button type="submit" class="btn btn-danger">确认添加</button>
        </div>
        </form>
         </div>
      </div>
    </div>
  </div>
  
</body>
<script type="text/javascript">
$(".update").click(function(){
	var id = $(this).parent().parent("tr").find("input[class='hide']").val();
	$.ajax({
		url:"${path}/searchPunishById?id="+id,
		type:"POST",
		success:function(data){
			var res = JSON.parse(data);
			if(res.msg=="success"){
				$("#punish_id").val(res.punish_id);
				$("#punish_time").val(res.punish_time);
				$("#punish_cause").val(res.punish_cause);
				$("#punish_level").val(res.punish_level);
				$("#modal1").modal(function(){
					backdrop:'static'
				});
			}
		}
	});


});

$("#add").click(function(){
	$("#modal3").modal(function(){
		backdrop:'static'
	});
});
</script>
</html>