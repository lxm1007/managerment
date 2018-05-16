<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <% request.setAttribute("path", request.getContextPath()); %>
    <% request.setAttribute("msg", request.getAttribute("msg")); %>
    <% request.setAttribute("msg1", request.getAttribute("msg1")); %>
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
<form class="form-inline" action="${path}/showGeneralInfo">
  <div class="form-group">
    <label class="sr-only" for="exampleInputEmail3">学号</label>
    <input type="text" class="form-control" name="study_id" placeholder="学号">
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
<td>性别</td>
<td>民族</td>
<td>籍贯</td>
<td>政治面貌</td>
<td>健康情况</td>
<td>婚姻情况</td>
<td>操作</td>
</tr>
<c:forEach var="list" items="${info}" varStatus="status">
<tr>
<input type="hidden" class="hide" value="${list.stu_id}">
<td>${status.index+1}</td>
<td>${list.stu_name}</td>
<td>${list.stu_study_id}</td>
<td>${list.stu_sex}</td>
<td>${list.stu_nation}</td>
<td>${list.stu_native}</td>
<td>${list.stu_poltic}</td>
<td>${list.stu_health}</td>
<td>${list.stu_marry}</td>

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
      <form class="form-inline" action="${path }/updateStuGeneral" method="post">
      <input type="hidden" name="stu_id" id="stu_id">
  <div class="form-group">
    <label for="stu_name">姓名</label>
    <input type="text" name="stu_name" class="form-control" id="stu_name">
  </div>
  <div class="form-group">
    <label for="stu_study">学号</label>
    <input type="text" name="stu_study_id" class="form-control" id="stu_study">
  </div>
  <div class="col-md-12"><br></div>
   <div class="form-group">
    <label for="stu_sex">性别</label>
    <input type="text" class="form-control" name="stu_sex" id="stu_sex">
  </div>
   <div class="form-group">
    <label for="stu_nation">民族</label>
    <input type="text" class="form-control" name="stu_nation" id="stu_nation">
  </div>
   <div class="col-md-12"><br></div>
  <div class="form-group">
    <label for="exampleInputEmail2">政治面貌</label>
     <select name="stu_poltic" class="form-control" id="select1">
   <option value="共青团员">共青团员</option>
   <option value="预备党员">预备党员</option>
   <option value="党员">党员</option>
   <option value="群众">群众</option>
   </select>
  </div>
   <div class="form-group">
    <label for="exampleInputEmail2">健康状况</label>
   <select name="stu_health" class="form-control" id="select2">
   <option value="健康">健康</option>
   <option value="良好">良好</option>
   <option value="一般">一般</option>
   </select>
  </div>
   <div class="form-group">
    <label for="exampleInputEmail2">婚姻状况</label>
 <select name="stu_marry" class="form-control" id="select3">
   <option value="已婚">已婚</option>
   <option value="未婚">未婚</option>
   </select>
  </div>
     
      <div class="modal-footer">
        <button type="submit" class="btn btn-danger">确认修改</button>
        </div>
        </form>
         </div>
      </div>
    </div>
  </div>



</body>
<script type="text/javascript">
console.log("${msg1}");
$(".update").click(function(){
	var id = $(this).parent().parent("tr").find("input[class='hide']").val();
	$.ajax({
		url:"${path}/searchStuById?id="+id,
		type:"POST",
		success:function(data){
			var res = JSON.parse(data);
			if(res.msg=="success"){
				$("#stu_id").val(res.stu_id);
				$("#stu_name").val(res.stu_name);
				$("#stu_study").val(res.stu_study_id);
				$("#stu_sex").val(res.stu_sex);
				$("#stu_nation").val(res.stu_nation);
				$("#select1").val(res.stu_poltic);
				$("#select2").val(res.stu_health);
				$("#select3").val(res.stu_marry);
				$("#modal1").modal(function(){
					backdrop:'static'
				});
			}
		}
	});


});
</script>
</html>