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

<script type="text/javascript" src="${path }/static/jquery.table2excel.js"></script>
</head>
<body>
<div class="col-md-10 col-md-offset-1">
<table class="table table-striped table-hover" id="tab2">
<tr>
<td>序号</td>
<td>姓名</td>
<td>学号</td>
<td>时间</td>
<td>缘由</td>
<td>荣誉</td>
</tr>
<c:forEach var="list" items="${info }" varStatus="status" >
<tr>
<td>${status.index+1}</td>
<td>${list.stu_name}</td>
<td>${list.stu_study_id}</td>
<td>${list.honor_time }</td>
<td>${list.honor_cause }</td>
<td>${list.honor_prove }</td>
</tr>
</c:forEach>
</table>
<div class="col-md-3 col-md-offset-6" >
<button class="btn btn-primary" type="button" id="dowmload">导出</button>
</div>
</div>
</body>

<script type="text/javascript">

$("#dowmload").click(function(){

	$("#tab2").table2excel({
	    exclude  : ".noExl", //过滤位置的 css 类名
	    filename : "学生获奖信息表" + ".xls" //文件名称
	});
});


</script>
</html>