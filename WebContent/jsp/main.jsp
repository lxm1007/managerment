<%@ page language="java" pageEncoding="GBK"%>
<% request.setAttribute("stu_info", request.getAttribute("stu_info")); %>
<% request.setAttribute("manager_info", request.getAttribute("manager_info")); %>
<html>
<head>
<title>管理首页</title>
<% request.setAttribute("path", request.getContextPath()); %>
<link href="css/style.css" type="text/css">
<link href="${path }/static/bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
<script type="text/javascript" src="${path }/static/bootstrap-3.3.7-dist/jquery-3.2.1.js"></script>
</head>
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>主页</title>
	
	<link rel="stylesheet" type="text/css" href="${path }/static/bootstrap-3.3.7-dist/css/htmleaf-demo.css">
	<link rel="stylesheet" type="text/css" href="${path }/static/bootstrap-3.3.7-dist/css/sidebar-menu.css">
	
	<script type="text/javascript" src="${path }/static/bootstrap-3.3.7-dist/js/sidebar-menu.js"></script>
	
	<style type="text/css">
	.main-sidebar{
	    position: absolute;
	    top: 0;
	    left: 0;
	    height: 100%;
	    min-height: 100%;
	    width: 230px;
	    z-index: 810;
	    background-color: #222d32;
	 }
	</style>
</head>
<body>
	<aside class="main-sidebar">
	<section  class="sidebar">
	    <ul class="sidebar-menu">
	      <li class="header">全部菜单</li>
	      <li class="treeview" id="pli1">
	        <a href="#">
	          <i class="fa fa-dashboard"></i> <span>用户管理</span> <i class="fa fa-angle-left pull-right"></i>
	        </a>
	        <ul class="treeview-menu">
	          <li id="li1"><a href="updatePwd.jsp" target="main"><i class="fa fa-circle-o"></i>修改密码</a></li>
	          <li id="li2"><a href="assignManager.jsp" target="main"><i class="fa fa-circle-o"></i>分配管理员</a></li>
	          <li id="li3"><a href="${path}/showAllManager" target="main"><i class="fa fa-circle-o"></i>查询用户</a></li>
	        </ul>
	      </li>
	      <li class="treeview" id="pli2">
	        <a href="#">
	          <i class="fa fa-files-o"></i>
	          <span>院系信息</span>
	          <span class="label label-primary pull-right"></span>
	        </a>
	        <ul class="treeview-menu" style="display: none;">
	          <li><a href="${path }/serachByGrade" target="main"><i class="fa fa-circle-o"></i>年级信息</a></li>
	          <li><a href="${path }/serachByAcademy" target="main"><i class="fa fa-circle-o"></i>学院信息</a></li>
	          <li><a href="${path }/serachByMajor" target="main"><i class="fa fa-circle-o"></i>专业信息</a></li>
	          <li><a href="${path }/serachByClass" target="main"><i class="fa fa-circle-o"></i>班级信息</a></li>
	        </ul>
	      </li>
	      <li class="treeview" id="pli3">
	        <a href="#">
	          <i class="fa fa-pie-chart"></i>
	          <span>学生信息</span>
	          <i class="fa fa-angle-left pull-right"></i>
	        </a>
	        <ul class="treeview-menu">
	          <li><a href="${path }/showGeneralInfo" target="main"><i class="fa fa-circle-o"></i>基本信息</a></li>
	          <li><a href="showStuHonor.jsp" target="main"><i class="fa fa-circle-o"></i>获奖信息</a></li>
	          <li><a href="showPunish.jsp" target="main"><i class="fa fa-circle-o"></i>处分信息</a></li>
	        </ul>
	      </li>
	      <li class="treeview" id="pli4">
	        <a href="#">
	          <i class="fa fa-laptop"></i>
	          <span>个人中心</span>
	          <i class="fa fa-angle-left pull-right"></i>
	        </a>
	        <ul class="treeview-menu">
	          <li><a id="a1" target="main"><i class="fa fa-circle-o"></i>信息完善</a></li>
	          <li><a id="a2" target="main"><i class="fa fa-circle-o"></i>奖励信息</a></li>
	          <li><a id="a3" target="main"><i class="fa fa-circle-o"></i>处分信息</a></li>
	        </ul>
	      </li>
	    </ul>
	  </section>
	 </aside>
	<div class="htmleaf-container">
	</div>
	<div style="margin-left: 18%;width: 81%;height:1%"><img src="${path}/static/img/banner.png" style="height: 88px"></div>
	<iframe name="main" style="margin-left: 18%;margin-top: 7%;width: 82%;height: 84%">
	
	</iframe>
	
	<script type="text/javascript">
		$(function(){
			var stu_info = "${stu_info}";
			var manager_info = "${manager_info}";
			if(stu_info==""||stu_info==null){
				$("#pli4").remove();
				var type="${manager_info.manager_type}"
				if(type=="2"){
					$("#li2").remove();
				}
			}else if(manager_info==""||manager_info==null){
				var id = "${stu_info.stu_id}";
				var study_id = "${stu_info.stu_study_id}";
				$("#a1").attr("href","${path}/searchOneStuDetail?id="+id)
				$("#a2").attr("href","${path}/searchOneStuHonor?id="+study_id)
				$("#a3").attr("href","${path}/searchOneStuPunish?id="+study_id)
				$("#pli1").remove();
				$("#pli2").remove();
				$("#pli3").remove();
			}
		})
	</script>

	  <script>
	    $.sidebarMenu($('.sidebar-menu'))
	  </script>
</body>
</html>