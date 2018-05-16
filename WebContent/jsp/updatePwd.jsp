<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <% request.setAttribute("path", request.getContextPath()); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="${path }/static/bootstrap-3.3.7-dist/jquery-3.2.1.js"></script>
<link rel="stylesheet" href="${path}/static/bootstrap-3.3.7-dist/css/bootstrap.css" type="text/css" media="screen"/>
<script type="text/javascript" src="${path}/static/bootstrap-3.3.7-dist/js/bootstrap.js""></script>
</head>
<body>

<div class="col-md-10 col-md-offset-2" style="text-align: center;padding-top: 50px;">
<div class="col-md-12" >
<span id="msg" style="padding-right: 400px;text-align:center "></span>
</div>
<form class="form-horizontal">
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">旧密码</label>
    <div class="col-sm-4">
      <input type="email" class="form-control" id="oldPwd" placeholder="输入旧密码">
    </div>
  </div>
  <div class="form-group" >
    <label for="inputPassword3" class="col-sm-2 control-label">新密码</label>
    <div class="col-sm-4">
      <input type="password" class="form-control" id="pwd1" disabled placeholder="新密码">
    </div>
  </div>
   <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">确认密码</label>
    <div class="col-sm-4">
      <input type="password" class="form-control" id="pwd2" disabled placeholder="新密码要一致">
    </div>
  </div>

  <div class="form-group">
    <div class="col-sm-offset-2 col-md-4">
      <button id="btn" type="button" class="btn btn-danger" disabled>确认重置</button>
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
//检验当前密码是否正确
$("#oldPwd").blur(function(){
	var id="${manager_info.manager_id}";
	var pwd = $(this).val();
	$.ajax({
		url:"${path}/checkPwd?id="+id+"&&pwd="+pwd,
		type:"GET",
		success:function(data){
			var data1 =  JSON.parse(data);
			if(data1.msg=="success"){
				$("#msg").text("密码正确").css("color","green").css("font-size","18px");
				$("#pwd1").removeAttr("disabled");
				$("#pwd2").removeAttr("disabled");
				$("#oldPwd").attr("disabled","disabled");
				$("#btn").removeAttr("disabled");
			}else {
				$("#msg").text("密码错误，请重试").css("color","red").css("font-size","18px");
				}
			
		}
		
	});
});

//提价前校验
$("#pwd1").blur(function(){
	$("#btn").removeAttr("disabled");
});
$("#pwd2").blur(function(){
	var pwd1 = $("#pwd1").val();
	var pwd2 = $("#pwd2").val();
	if(pwd1!=pwd2||pwd1==""||pwd2==""){
		$("#msg").text("请检查你的密码").css("color","red").css("font-size","18px");
		$("#btn").attr("disabled","disabled");
	}else{
		$("#btn").removeAttr("disabled");
	}
});

$("#btn").click(function(){
	var f = false;
	var val1 = $("#pwd1").val();
	var val2 = $("#pwd2").val();
	var id="${manager_info.manager_id}";
	if(val2!=val1||val2==""||val1==""){
		$("#msg").text("请检查你的密码").css("color","red").css("font-size","18px");
	}else{
		f = true;
	}
	if(f){
		$.ajax({
			url:"${path}/updatePwd?id="+id+"&&pwd="+val2,
			type:"POST",
			success:function(data){
				var res =  JSON.parse(data);
				if(res.msg=="success"){
					$("#modal1").modal(function(){
						backdrop:'static'
					});
					$("#msg2").text("修改成功").css("color","green").css("font-size","20px");
				}else{
					$("#msg2").text("网络错误").css("color","red").css("font-size","20px");
				}
			}
		});
	}
});
</script>
</html>