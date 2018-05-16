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
</head>
<body>
<div class="col-md-8 col-md-offset-2">
<table class="table table-striped">
<tr>
<td>序号</td>
<td>姓名</td>
<td>账号</td>
<td>手机号</td>
</tr>
<c:forEach var="list" items="${info}" varStatus="status">
<tr>
<input type="hidden" class="hide" value="${list.manager_id}">
<td>${status.index+1}</td>
<td>${list.manager_name}</td>
<td>${list.manager_login_name}</td>
<td>${list.manager_tel}</td>
</tr>

</c:forEach>
</table>

<!-- Modal -->
<div class="modal fade"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" id="modal1">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">信息更改</h4>
      </div>
      <div class="modal-body">
      <form class="form-horizontal">
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label"></label>
    <div class="col-sm-10">
      <input type="email" class="form-control" id="inputEmail3" placeholder="Email">
    </div>
  </div>
  <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
    <div class="col-sm-10">
      <input type="password" class="form-control" id="inputPassword3" placeholder="Password">
    </div>
  </div>

</form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>

</div>
<!-- 信息提示框 -->
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

</html>