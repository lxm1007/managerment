<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <% request.setAttribute("path", request.getContextPath()); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="${path }/static/bootstrap-3.3.7-dist/jquery-3.2.1.js"></script>
<link rel="stylesheet" href="${path}/static/bootstrap-3.3.7-dist/css/bootstrap.css" type="text/css" media="screen"/>
<script type="text/javascript" src="${path}/static/bootstrap-3.3.7-dist/js/bootstrap.js"></script>
</head>
<body>
<div class="col-md-10 col-md-offset-2" style="text-align: center;padding-top: 50px;">
<div class="col-md-12" >
<span id="msg" style="padding-right: 400px;text-align:center "></span>
</div>
<form class="form-horizontal">
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">账号</label>
    <div class="col-sm-4">
      <input type="email" class="form-control" id="account" placeholder="输入账号">
    </div>
  </div>
  <div class="form-group" >
    <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
    <div class="col-sm-4">
      <input type="password" class="form-control" id="pwd1"  placeholder="密码">
    </div>
  </div>
   <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">确认密码</label>
    <div class="col-sm-4">
      <input type="password" class="form-control" id="pwd2"  placeholder="确认密码">
    </div>
  </div>
<input type="hidden" id="flag" value="0">
  <div class="form-group">
    <div class="col-sm-offset-2 col-md-4">
      <button id="btn" type="button" class="btn btn-danger" >确认分配</button>
    </div>
  </div>
</form>

</div>
<!-- 信息提示框 -->
<div class="modal fade bs-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" id="modal1">
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

$("#btn").click(function(){
	var f = true;
	var f2 = $("#flag").val();
	var pwd1 = $("#pwd1").val();
	var pwd2 = $("#pwd2").val();
	var account = $("#account").val();
	if(pwd1!=pwd2||pwd1==""||pwd2==""){
		$("#msg").text("密码不相同或者为空").css("color","red").css("font-size","18px");
		f = false;
	}
	if(f&&(f2==1)){
		$.ajax({
			url:"${path}/addOneManager?account="+account+"&&pwd="+pwd2,
			type:"GET",
			success:function(data){
			var res = JSON.parse(data);
			if(res.msg=="success"){
				$("#msg2").text("添加成功").css("color","green").css("font-size","18px");
			}else{
				$("#msg2").text("添加失败").css("color","red").css("font-size","18px");
			}
			$("#modal1").modal(function(){
				backdrop:'static'
			});
			}
		});
	}
});

$("#account").blur(function(){
	var account = $(this).val();
	$.ajax({
		url:"${path}/checkAccount?account="+account,
		type:"POST",
		success:function(data){
			var res = JSON.parse(data);
			if(res.msg=="success"){
				$("#flag").val(1);
				$("#msg").text("用户名可用").css("color","green").css("font-size","18px");
			}else{
				$("#flag").val(0);
				$("#msg").text("用户名不可用").css("color","red").css("font-size","18px");
			}
		}
	});
});
</script>
</html>