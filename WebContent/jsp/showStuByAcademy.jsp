<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <% request.setAttribute("path", request.getContextPath()); %>
    <% request.setAttribute("msg", request.getAttribute("msg")); %>
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
<form class="form-inline" action="${path}/serachByAcademy">
  <div class="form-group">
    <label class="sr-only" for="exampleInputEmail3">学院</label>
    <input type="text" class="form-control" name="academy" placeholder="学院">
  </div>

  <button type="submit" class="btn btn-success">查找</button>
</form>
</div>
<div class="col-md-8 col-md-offset-2">
<table class="table table-striped">
<tr>
<td>序号</td>
<td>姓名</td>
<td>学号</td>
<td>学院</td>
<td>操作</td>
</tr>
<c:forEach var="list" items="${info}" varStatus="status">
<tr>
<input type="hidden" class="hide" value="${list.stu_id}">
<td>${status.index+1}</td>
<td>${list.stu_name}</td>
<td>${list.stu_study_id}</td>
<td><input class="form-control inp" value="${list.stu_academy}"></td>
<td><button type="button" class="btn btn-danger update">确认修改</button></td>
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
</body>
<script type="text/javascript">

$(".update").click(function(){
	var val1 = $(this).parent().parent("tr").find("input[class='hide']").val();
	var val2 = $(this).parent().parent("tr").find("input[class='form-control inp']").val();
	$.ajax({
		url:"${path}/updateStuAcademy?id="+val1+"&&academy="+val2,
		type:"POST",
		success:function(data){
			var res = JSON.parse(data);
			if(res.msg=="success"){
				$("#modal2").modal(function(){
					backdrop:'static'
				});
				$("#msg2").text("操作成功").css("color","green").css("font-size","18px");
				$("#con").click(function(){
					$("#modal2").modal('hide');
				});
			}
		}
	});
});
</script>
</html>