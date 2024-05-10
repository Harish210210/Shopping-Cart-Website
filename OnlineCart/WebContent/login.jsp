<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="style.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<div class="container p-5">
		<div class="row text-center"> 
			<div class="col-md-4"></div>
			<div class="col-md-4 login p-4">
				<form id="login">
				<h3>Login</h3>
				<label id="invalid" style="color:red;display:none">Enter Valid Details</label>
				<input type="email" id="email" class="input form-control" placeholder="Email" required/>
				<br/>
				<input type="password" id="password" class="input form-control" placeholder="Password" required/>
				<br/>
				<p>Didn't have an account? <a href="signup.jsp">signup</a></p>
				<input type="submit" value="Login" class="input bg-primary form-control text-white" />
				</form>
			</div>
			<div class="col-md-4"></div>
		</div>
	</div>
	<script>
		$("#login").submit(function(e){
			e.preventDefault();
			$.ajax({
	      		url:'Login',
	      		method:'post',
	      		data:{ 
	      			email:$("#email").val(),
	      			password:$("#password").val()
	      		},
	      		success:function(res){
		      		if(res.includes("Success")){
		      			window.location.href="store.jsp"
		      		}
		      		else{
		      			$("#email").css('border','1px solid red');
		      			$("#password").css('border','1px solid red');
		      			$("#invalid").css('display','block');
		      		}
	      		}
	      	})
		})
	</script>
</body>
</html> 