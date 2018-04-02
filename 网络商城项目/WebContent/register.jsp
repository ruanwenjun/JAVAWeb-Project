<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html>
<head></head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员注册</title>
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

font {
	color: #3164af;
	font-size: 18px;
	font-weight: normal;
	padding: 0 10px;
}

.error{
color: red;
}
</style>

<!-- 使用validate进行表单校验 -->

<script type="text/javascript">
		
		$(function() {
			//自定义校验规则------用户名是否存在
			$.validator.addMethod("ifExist",function(value,element,params){
				//value是校验的表单的值
				//element是一个对象
				//params是true
				var ifExist = false;
				$.ajax({
					//设置为同步校验
					async:false,
					dataType:"json",
					type:"POST",
					data:{"username":value},
					url:"${pageContext.request.contextPath }/user?method=isUsernameExist",
					success:function(data){
						ifExist = data.isExist;
					}
				});
				return !ifExist;      //返回的true和false即为校验是否通过
			});
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
			
			$("#registerForm").validate({
				rules:{
					username:{
						required:true,
						ifExist:true
					},
					password:{
						required:true,
						rangelength:"[6,16]"
					},
					confirmpwd:{
						equalTo:"[name='password']"
					},
					email:{
						required:true,
						email:true
					},
					sex:{
						required:true
					},
					birthday:{
						required:true
					},
					varifyCode:{
						required:true,
						isRight:true
					}
				},
				messages:{
					username:{
						required:"用户名不能为空",
						ifExist:"用户名已经存在"
					},
					password:{
						required:"密码不能为空",
						rangelength:"密码长度必须为6-16位"
					},
					confirmpwd:{
						equalTo:"两次密码输入不一样"
					},
					email:{
						required:"email不能为空",
						email:"请输入正确的email格式"
					},
					sex:{
						required:"性别不能为空"
					},
					birthday:{
						required:"出生日期不能为空"
					},
					varifyCode:{
						required:"验证码不能为空",
						isRight:"验证码不正确"
					}
					
				}
			});
			
		})
		
		function changeVarifyCode(obj) {
			$(obj).attr("src","${pageContext.request.contextPath }/checkImg?a="+Date());
		}
	


</script>
</head>
<body>

	<!-- 引入header.jsp -->
	<jsp:include page="/header.jsp"></jsp:include>

	<div class="container"
		style="width: 100%; background: url('image/regist_bg.jpg');">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8"
				style="background: #fff; padding: 40px 80px; margin: 30px; border: 7px solid #ccc;">
				<font>会员注册</font>USER REGISTER
				
				<form id="registerForm"  class="form-horizontal" style="margin-top: 5px;" action="${pageContext.request.contextPath }/user?method=userRegister">
					<div class="form-group">
						<label for="username" class="col-sm-2 control-label">用户名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="username" name="username"
								placeholder="请输入用户名">
						</div>
						<label id="username-error" class="error" for="username" style="font-size: 15px;padding-top: 8px " ></label>
					</div>
					<div class="form-group">
						<label for="inputPassword3" class="col-sm-2 control-label">密码</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="password" name="password"
								placeholder="请输入密码">
						</div>
						<label id="password-error" class="error" for="password" style="font-size: 15px;padding-top: 8px " ></label>
					</div>
					<div class="form-group">
						<label for="confirmpwd" class="col-sm-2 control-label" >确认密码</label>
						<div class="col-sm-6">
							<input type="password" class="form-control" id="confirmpwd" name="confirmpwd"
								placeholder="请输入确认密码">
						</div>
						<label id="confirmpwd-error" class="error" for="confirmpwd" style="font-size: 15px;padding-top: 8px " ></label>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">Email</label>
						<div class="col-sm-6">
							<input type="email" class="form-control" id="email" name="email"
								placeholder="Email">
						</div>
						<label id="email-error" class="error" for="email" style="font-size: 15px;padding-top: 8px " ></label>
					</div>
					<div class="form-group">
						<label for="usercaption" class="col-sm-2 control-label">姓名</label>
						<div class="col-sm-6">
							<input type="text" class="form-control" id="name" name="name"
								placeholder="请输入姓名">
						</div>
						<label id="name-error" class="error" for="name" style="font-size: 15px;padding-top: 8px " ></label>
					</div>
					<div class="form-group opt">
						<label for="inlineRadio1" class="col-sm-2 control-label">性别</label>
						<div class="col-sm-6">
							<label class="radio-inline"> <input type="radio"
								name="sex" id="sex" value="mail">
								男
							</label> <label class="radio-inline"> <input type="radio"
								name="sex" id="sex" value="femail">
								女
							</label>
							<label id="sex-error" class="error" for="sex" style="font-size: 15px;padding-top: 8px"></label>
						</div>
					</div>
					<div class="form-group">
						<label for="date" class="col-sm-2 control-label">出生日期</label>
						<div class="col-sm-6">
							<input type="date" class="form-control" name="birthday">
						</div>
						<label id="birthday-error" class="error" for="birthday" style="font-size: 15px;padding-top: 8px " ></label>
					</div>

					<div class="form-group">
						<label for="date" class="col-sm-2 control-label">验证码</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="varifyCode" id="varifyCode">

						</div>
						<div class="col-sm-2">
							<img src="${pageContext.request.contextPath }/checkImg" onclick="changeVarifyCode(this)"/>
						</div>
						<label id="varifyCode-error" class="error" for="varifyCode" style="font-size: 15px;padding-top: 8px " ></label>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<input type="submit" width="100" value="注册" name="submit"
								style="background: url('./images/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0); height: 35px; width: 100px; color: white;">
						</div>
					</div>
				</form>
			</div>

			<div class="col-md-2"></div>

		</div>
	</div>

	<!-- 引入footer.jsp -->
	<jsp:include page="/footer.jsp"></jsp:include>

</body>
</html>




