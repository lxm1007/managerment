<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setAttribute("path", request.getContextPath()); %>
<% request.setAttribute("msg", request.getAttribute("msg")); %>
<html>
	<head>
		<meta charset="utf-8">
	 	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />  
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no"/>
        
		<link rel="stylesheet" href="${path}/static/bootstrap-3.3.7-dist/css/bootstrap.css" type="text/css" media="screen"/>
		<link rel="stylesheet" type="text/css" href="${path}/static/bootstrap-3.3.7-dist/css/bootstrap-switch.css">
		<link rel="stylesheet" type="text/css" href="${path}/static/bootstrap-3.3.7-dist/css/normalize.css" />
		<link rel="stylesheet" type="text/css" href="${path}/static/bootstrap-3.3.7-dist/css/default.css">
		<link rel="stylesheet" type="text/css" href="${path}/static/bootstrap-3.3.7-dist/css/styles.css">
		
		<script type="text/javascript" src="${path }/static/bootstrap-3.3.7-dist/jquery-3.2.1.js"></script>
		<script type="text/javascript" src="${path}/static/bootstrap-3.3.7-dist/js/bootstrap.js""></script>
				<script type="text/javascript" src="${path}/static/bootstrap-3.3.7-dist/js/bootstrap-switch.js"></script>
		<title>欢迎登录</title>
	</head> 
	
	<style type="text/css">   
	
	.text1{
	 font-style: inherit;
	 text-align: center;
	 line-height:420%;
	 font-size: 10px;
	}
	
	.login-html{
	text-align: center;
	
	padding-top: 10px;
	}
	@media (min-width: 650px) {
			.text1 {
			 font-size:20px;
			 font-style: inherit;
			 text-align: center;
			 line-height:420%;"
			}
			.login-html{
			text-align: center;
			min-height: 650px;
			max-height: 800px;
			}
				}
		@media (min-width: 992px) {
		.text1 {
			font-size:30px;
		 	font-style: inherit;
		 	text-align: center;
	 		line-height:420%;"
		}
		.login-html{
		text-align: center;
		min-height: 650px;
		max-height: 800px;
		}
			}
    .linear{   
        width:100%;   
        height:600px;   
        FILTER: progid:DXImageTransform.Microsoft.Gradient(gradientType=0,startColorStr=#ACD6FF,endColorStr=#ECF5FF); /*IE 6 7 8*/   
        background: -ms-linear-gradient(top, #ACD6FF,  #ECF5FF);        /* IE 10 */  
        background:-moz-linear-gradient(top,#ACD6FF,#ECF5FF);/*火狐*/   
        background:-webkit-gradient(linear, 0% 0%, 0% 100%,from(#ACD6FF), to(#ECF5FF));/*谷歌*/   
        background: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#ACD6FF), to(#ECF5FF));      /* Safari 4-5, Chrome 1-9*/  
        background: -webkit-linear-gradient(top, #ACD6FF, #ECF5FF);   /*Safari5.1 Chrome 10+*/  
        background: -o-linear-gradient(top, #ACD6FF, #ECF5FF);  /*Opera 11.10+*/  
    }   

	
	
}
 		input::-webkit-input-placeholder{
            color:white;
        }
        input::-moz-placeholder{   /* Mozilla Firefox 19+ */
             color:white;
        }
        input:-moz-placeholder{    /* Mozilla Firefox 4 to 18 */
            color:white;
        }
        input:-ms-input-placeholder{  /* Internet Explorer 10-11 */ 
        	 color:white;
        }
         
        ::-webkit-input-placeholder{ 
        	color:white;			/*Google*/
        } 
   
	</style>   
	
	<body class="linear">
	<div class="container">
	<div class="row">
		<div class="col-md-5  col-md-offset-2 col-xs-8" style="height:85px;"><img class="img-responsive" src="${path}/static/img/banner.png"></div>
		<div class="col-md-3  col-xs-4 text1">学生档案管理系统</div>
	</div>
	<div class="row">
		<div class="login-wrap" style="margin-top: 10px;text-align: center;">
			<div class="login-html col-md-8 col-xs-10 col-md-offset-2 col-xs-offset-1" style="height: 500px;">
				<span id="showmsg" style="font-size: 18px"></span>
				<br><br><br>
				<input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">&nbsp;&nbsp;登录</label>
				<input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab"></label>
				<div class="login-form">
					<div class="sign-in-htm">
						<form id="form1">
							<div class="group">
								<label for="user" class="label">用户名</label>
								<input id="user" type="text" name="stuAccount" class="input" required="required">
							</div>
							<div class="group">
								<label for="pass" class="label">密码</label>
								<input id="pass" type="password" name="stuPwd" class="input" data-type="password" >
							</div>
							<div class="group">
								<input id="loginbtn" type="button" class="button"  value="登录">
							</div>
							<span style="font-size: 15px" id="msg1"></span>
							<br>
							<input type="hidden"  id="role" value="2">
						</form>
						<br>
						<div id="switch" class="switch" data-on-label="管理员" data-off-label="学生">
    							<input type="checkbox"  checked />
						</div>
						<br>
						<br>
						<br>
						<div class="foot-lnk">
							<a href="${path}/forgot">忘记密码？</a>
						</div>
						<div class="hr"></div>
					</div>
					<div class="sign-up-htm">
					<input type="hidden" id="hide1" value="0">
					<input type="hidden" id="hide2" value="0">
					<input type="hidden" id="hide3" value="0">
						<form id="fomr2">
						<div class="group">
							<label for="candidate" class="label">学号</label>
							<input id="candidate" type="text" name="stuCandidate" class="input">
						</div>
						<div class="group">
							<label for="stuAccount" class="label">登录名</label>
							<input id="stuAccount" type="text" name="stuAccount" class="input" disabled="disabled" placeholder="登录账号,数字字母组合">
						</div>
						<div class="group">
							<label for="stuPwd" class="label">密码</label>
							<input id="stuPwd" type="password" name="stuPwd" class="input" data-type="password" disabled="disabled" placeholder="数字和密码组合不少于8位">
						</div>
						<div class="group">
							<label for="repwd" class="label">确认密码</label>
							<input id="repwd" type="password" class="input"  data-type="password" disabled="disabled">
						</div>
						<div class="group">
							<input type="button" id="regBtn" class="button" disabled="disabled" value="注册">
						</div>
						</form>
						<div class="hr"></div>
						
							<label>请登录后完善个人信息</label>
						
					</div>
				</div>
			</div>
	</div>
</div>
	<div class="col-xs-12" style="text-align: center;margin-top:5px;"><br>版权所有 青岛农业大学<sup>&copy;</sup></div>
	
	</body>
	
	<script type="text/javascript">

	$(function(){
		$("#switch input").bootstrapSwitch({  
		      onText:"学生",  
		      offText:"管理员",  
		      onColor:"success",  
		      offColor:"info",  
		      size:"normal",  
		    onSwitchChange:function(event,state){  
		      if(state==true){  
		       $("#role").attr("value","2");
		      }else{  
		       $("#role").attr("value","1");
		      }  
		    }  
		 
		       });
	});
	
	$("#tab-2").click(function(){
		$(".login-html").css("min-height","720px");
	});
	$("#tab-1").click(function(){
		$(".login-html").css("min-height","555px");
	});
	
	$("#loginbtn").click(function(){
		var f = true;
		var user = $("#user").val();
		var pass = $("#pass").val();
		var role =$("#role").val();
		if(user==""||pass==""){
			f=false;
		}
		var str = {"user":user,"pass":pass,"role":role}
		if(f){
	
		$.ajax({
			url:"${path}/login",
			type:"POST",
			data:str,
			success:function(data){
				if(data.msg != undefined){
					$("#msg1").text(data.msg).css("color","red");
				}else {
				window.location.href="jsp/main.jsp";
				}
				
			}
			
		});
		}else{
			$("#msg1").css("color","red").addClass("glyphicon glyphicon-remove").text("请输入用户名和密码")
		}
	});
	
	$("#user").change(function(){
		$("#msg1").text("").removeClass("glyphicon glyphicon-remove");
		
	});
	$("#pass").change(function(){
		$("#msg1").text("").removeClass("glyphicon glyphicon-remove");
		
	});
	</script>
	
	<!-- 注册过程中的js验证 -->
	
	<script type="text/javascript">
	
		$("#tel").blur(function(){
			checkTel($(this).val());
		});
		function checkTel(obj){
			var reg = /^1[3|4|5|8|7|9][0-9]\d{8}$/;
			var flag = reg.test(obj);
			if(flag==false){
				$("#showmsg").removeClass("glyphicon glyphicon-ok").addClass("glyphicon glyphicon-remove").css("color","red").text("手机号格式不正确");
			}else{
				$("#showmsg").removeClass("glyphicon glyphicon-remove").addClass("glyphicon glyphicon-ok").css("color","green").text("手机号可用");
				$("#stuAccount").removeAttr("disabled");
			}
			return flag;
		}

		
		$("#stuPwd").keyup(function(){
			checkPwd($(this).val());
		});
		function checkPwd(obj){
			var f = true;
			var reg = /[a-zA-Z0-9]{8,}/;
			
			if(obj==""){
				$("#showmsg").removeClass("glyphicon glyphicon-ok").addClass("glyphicon glyphicon-remove").css("color","red").text("密码不能为空");
				f = false;
			}
			if(reg.test(obj)==false){
				$("#showmsg").removeClass("glyphicon glyphicon-ok").addClass("glyphicon glyphicon-remove").css("color","red").text("密码格式不正确");
				f = false;
			}
			else{
				$("#showmsg").removeClass("glyphicon glyphicon-ok").removeClass("glyphicon glyphicon-remove").css("color","red").text("请确保密码相同");
				$("#repwd").removeAttr("disabled");
			}
			return f;
			
		}
		$("#repwd").keyup(function(){
			var f = checkRePwd($(this).val());
		
		});
		function checkRePwd(obj){
			var f = true;
			var pwd = $("#stuPwd").val();
			if(pwd!=obj||obj==""){
				f = false;
				$("#showmsg").addClass("glyphicon glyphicon-remove").css("color","red").text("请检查您的密码");
			}
			else{
				$("#showmsg").removeClass("glyphicon glyphicon-remove").addClass("glyphicon glyphicon-ok").css("color","green").text("密码相同");
			
			}
			return f;
		}
		
		
		$("#regBtn").click(function(){
			var f1 = checkTel($("#tel").val());
			var f2 = checkPwd($("#pwd").val());
			var f3 = checkRePwd($("#repwd").val());
			/*学号  */
			var f4 = $("#hide1").val();
			/* 账户名 */
			var f5 = $("#hide2").val();
			/* 验证码 */
			var f6 = $("#hide3").val();
			if(f1&&f2&&f3&&(f4==1)&&(f5==1)&&(f6==1)){
				$.ajax({
					url:"${path}/register",
					data:$("#fomr2").serialize(),
					type:"POST",
					success:function(data){
						if(data.msg=="success"){
						$("#msg1").removeClass("glyphicon glyphicon-remove").addClass("glyphicon glyphicon-ok").text("账户已激活");
						window.location.href="${path}/index.jsp";
						}else{
						$("#msg1").removeClass("glyphicon glyphicon-ok").addClass("glyphicon glyphicon-remove").text("网络故障请重试");	
						}
						
					}
				});
			}
			
		});
		
		$("#stuAccount").keyup(function(){
			var stuAccount = $(this).val();
			var f =checkUser(stuAccount);
			if(f){
				$.ajax({
					url:"${path}/searchAccount?stuAccount="+stuAccount,
					type:"GET",
					success:function(data){
						var msg =data.msg;
						if(msg=="success"){
							$("#showmsg").removeClass("glyphicon glyphicon-remove").addClass("glyphicon glyphicon-ok").css("color","green").text("用户名可用");
							$("#hide1").val(1);
							$("#stuPwd").removeAttr("disabled");
						}else{
							$("#showmsg").removeClass("glyphicon glyphicon-ok").addClass("glyphicon glyphicon-remove").css("color","red").text("用户名不可用");
							$("#hide1").val(0);
						}
					}
				});
			}
		});
		function checkUser(obj){
			var reg =/^[0-9a-zA-Z_]{1,}$/;
			var f = reg.test(obj);
			if(f==false){
				$("#showmsg").addClass("glyphicon glyphicon-remove").css("color","red").text("您输入的用户名不合法")
			}
			return f;
		}
		
		$("#candidate").blur(function(){
			var val = $(this).val();
			reg = /^[0-9]{15}$/
			var f = reg.test(val);
			if(f==false){
				$("#showmsg").removeClass("glyphicon glyphicon-ok").addClass("glyphicon glyphicon-remove").css("color","red").text("考号格式不正确");
			}
			if(val!=""&&f){
				$.ajax({
					url:"${path}/searchCandidate?stuCandidate="+val,
					type:"GET",
					success:function(data){
						var msg = data.msg;
						if(msg=="success"){
							$("#showmsg").removeClass("glyphicon glyphicon-remove").addClass("glyphicon glyphicon-ok").css("color","green").text("考号验证成功");
							$("#hide2").val(1);
							$("#tel").removeAttr("disabled");
						}else{
							$("#showmsg").removeClass("glyphicon glyphicon-ok").addClass("glyphicon glyphicon-remove").css("color","red").text("考号不存在");
							$("#hide2").val(0);
						}
					}
					
				});
			}
		});
		
	</script>
	<script type="text/javascript">

	$("#yzm").keyup(function(){
		var code = $(this).val();
		var len = code.length;
		if(len>=5){
			
		
		$.ajax({
			url:"${path}/checkYzm?code="+code.trim(),
			success:function(data){
				var msg= data.msg;
				if(msg=="success"){
					$("#msg1").removeClass("glyphicon glyphicon-remove").addClass("glyphicon glyphicon-ok").css("color","white").css("font-size","18px").text("验证码正确");
					$("#loginbtn").removeAttr("disabled");
				}else{
					$("#msg1").removeClass("glyphicon glyphicon-ok").addClass("glyphicon glyphicon-reomove").css("color","red").text("验证码错误");
					$("#loginbtn").attr("disabled","disabled");
				}
			}
		});
		}
	});
	$("#img").click(function(){
		$("#img").attr("src","${path}/kcaptcha");
	});
	
	$("#yzm2").keyup(function(){
		var code = $(this).val();
		var len = code.length;
		if(len>=5){
		$.ajax({
			url:"${path}/checkYzm?code="+code.trim(),
			success:function(data){
				var msg= data.msg;
				if(msg=="success"){
					$("#showmsg").removeClass("glyphicon glyphicon-remove").addClass("glyphicon glyphicon-ok").css("color","green").text("验证码正确");
					$("#regBtn").removeAttr("disabled");
					$("#hide3").val(1);
					}
				else{
					$("#showmsg").removeClass("glyphicon glyphicon-ok").addClass("glyphicon glyphicon-reomove").css("color","red").text("验证码错误");
					$("#regBtn").attr("disabled","disabled");
				}
			}
		});
		}
	});
	$("#img2").click(function(){
		$("#img2").attr("src","${path}/kcaptcha");
	});
	</script>
	
	
</html>
