<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员登录</title>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script src="js/jquery.validate.min.js" type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="css/style.css" type="text/css" />

<style>
body {
	margin-top: 20px;
	margin: 0 auto;
}

.carousel-inner .item img {
	width: 100%;
	height: 300px;
}

.container .row div {
	/* position:relative;
				 float:left; */
	
}
.error{
	color: red;

}

font {
	color: #666;
	font-size: 22px;
	font-weight: normal;
	padding-right: 17px;
}

</style>
<script type="text/javascript">
	function changeCheckImg(obj) {
		$(obj).attr("src","${pageContext.request.contextPath }/checkImg?date="+new Date());
	}
	$(function(){
		//自定义校验规则------验证码是否正确
		$.validator.addMethod("isRight",function(value,element,params){
			var varify = false;
			$.ajax({
				//设置为同步校验
				async:false,
				dataType:"json",
				type:"POST",
				data:{"checkCode":value},
				url:"${pageContext.request.contextPath }/user?method=checkCodeVarify",
				success:function(data){
					varify = data.isRight;
				}
			});
			return varify;
		});
		$("#loginForm").validate({
			rules:{
				username:{
					required:true
				},
				password:{
					required:true
				},
				checkCode:{
					required:true,
					isRight:true
				}
				
			},
			messages:{
				username:{
					required:"请输入用户名"
				},
				password:{
					required:"请输入密码"
				},
				checkCode:{
					required:"请输入验证码",
					isRight:"验证码不正确"
				}
				
			}
		});
	});



</script>
</head>
<body>

	<!-- 引入header.jsp -->
	<jsp:include page="/header.jsp"></jsp:include>


	<div class="container"
		style="width: 100%; height: 460px; background: #FF2C4C url('images/loginbg.jpg') no-repeat;">
		<div class="row">
			<div class="col-md-7">
				<!--<img src="./image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">-->
			</div>

			<div class="col-md-5">
				<div
					style="width: 440px; border: 1px solid #E7E7E7; padding: 20px 0 20px 30px; border-radius: 5px; margin-top: 60px; background: #fff;">
					<font>会员登录</font>USER LOGIN
					<form class="form-horizontal" id="loginForm" method="post" action="${pageContext.request.contextPath }/user?method=userLogin">
						<div class="form-group">
							<label for="username" class="col-sm-2 control-label">用户名</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="username" name="username"
									placeholder="请输入用户名">
							</div>
							<label id="username-error" class="error" for="username"> </label>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">密码</label>
							<div class="col-sm-6">
								<input type="password" class="form-control" id="password" name="password"
									placeholder="请输入密码">
							</div>
							<label id="password-error" class="error" for="password"> </label>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">验证码</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" id="checkCode" name="checkCode"
									placeholder="请输入验证码">
							</div>
							<div class="col-sm-3">
								<img src="${pageContext.request.contextPath }/checkImg" onclick="changeCheckImg(this)" />
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<div class="checkbox">
									<label> <input type="checkbox"> 自动登录
									</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <label> <input
										type="checkbox"> 记住用户名
									</label>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<input type="submit" width="100" value="登录" name="submit"
									style="background: url('./images/login.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0); height: 35px; width: 100px; color: white;">
							</div>
						</div>
						<div id="loginInfo" style="padding-left: 50px; color: red;">
							<c:if test="${loginInfo=='false' }">
								用户名或密码输入错误
							</c:if>
							<c:if test="${loginInfo=='notActive' }">
								用户尚未激活
							</c:if>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	<!-- 引入footer.jsp -->
	<jsp:include page="/footer.jsp"></jsp:include>

</body>
</html>