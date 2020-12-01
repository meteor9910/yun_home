<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>云+ 房屋登录</title>
<%--<link rel="stylesheet" href="${pageContext.request.contextPath}/index/lib/layer/css/layui.css">--%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/index/lib/layer/css/login1.css"/>
<!-- Bootstrap -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath}/js/jquery-3.4.1.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<%--<script src="${pageContext.request.contextPath}/css/login.css"/>--%>
</head>
<body>
	<div class="qiqiu1 qiqiu">
    	<img src="${pageContext.request.contextPath}/index/lib/layer/login_img/q2.png"/>
        <div class="text">love</div>
    </div>
    	<div class="qiqiu2 qiqiu">
    	<img src="${pageContext.request.contextPath}/index/lib/layer/login_img/q3.png"/>
        <div class="text">love</div>
    </div>
    	<div class="qiqiu3 qiqiu">
    	<img src="${pageContext.request.contextPath}/index/lib/layer/login_img/q4.png"/>
        <div class="text">love</div>
    </div>
    	<div class="qiqiu4 qiqiu">
    	<img src="${pageContext.request.contextPath}/index/lib/layer/login_img/q5.png"/>
        <div class="text">love</div>
    </div>
    	<div class="qiqiu5 qiqiu">
    	<img src="${pageContext.request.contextPath}/index/lib/layer/login_img/q6.png"/>
        <div class="text">love</div>
    </div>

<div class="login">
	 <h1>用户登录</h1>
	<div class="error_msg" style="margin-left: 90px;color:red;}"><label class="msgColor">${loginError_msg}</label></div>
		<form class="form-horizontal" action="${pageContext.request.contextPath}/user/loginCustomer" method="post">
			<div class="form-group">
				<label for="username" class="col-sm-3 control-label">用户名</label>
				<div class="col-sm-9">
					<input type="username" name="username" class="form-control" id="username" placeholder="Username">
				</div>
			</div>
			<div class="form-group">

				<label for="password" class="col-sm-3 control-label">密码</label>
				<div class="col-sm-9">
					<input type="password" name="password" class="form-control" id="password" placeholder="Password">
				</div>

			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<div class="checkbox">
						<label type="checkbox">
							<input type="checkbox"  name="auto_login" id="auto_login" onclick="autoLogin()" /> 记住密码
						</label>
					</div>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-12" style="text-align: center">
					<button type="submit" class="btn btn-success">登录</button>
				</div>
			</div>
		</form>
	</div>



</body>


<script>
	//选中记住密码触发事件，如果选中就赋值为1 ，否则赋值为0
	function autoLogin (){
		var remFlag = $("input:checkbox").is(':checked');
		if(remFlag){
			//cookie存用户名和密码,回显的是真实的用户名和密码,存在安全问题.
			var conFlag = confirm("记录密码功能不宜在公共场所使用,以防密码泄露.您确定要使用此功能吗?");
			if(conFlag){
				//确认标志
				$("#auto_login").val("1");
			}else{
				$("input:checkbox").removeAttr('checked');
				$("#auto_login").val("0");
			}
		}else{
			//如果没选中设置remFlag为""
			$("#auto_login").val("0");
		}
	}

	$(function(){
		//cookie数据保存格式是key=value;key=value;形式，loginInfo为保存在cookie中的key值，具体看controller代码
		var str = getCookie("auto_login");
		str = str.slice(0,9);
		var username = str.split("+")[0];

		var str2 = getCookie("auto_login");

		str2 = str2.slice(10,16);
		var password = str2.split("+")[0];
		//自动填充用户名和密码
		$("#username").val(username);
		$("#password").val(password);
	});


	//获取cookie
	function getCookie(cname) {
		var name = cname + "=";
		var ca = document.cookie.split(';');
		for(var i=0; i<ca.length; i++) {
			var c = ca[i];
			while (c.charAt(0)==' ') c = c.substring(1);
			if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
		}
		return "";
	}

	$("auto_login").{

	}


</script>

</html>
