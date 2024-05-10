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
				<form id="signup">
				<h3>Signup</h3>
				<input type="email" id="email" class="input form-control" placeholder="Email" required />
				<br/>
				<input type="number" id="mobile" class="input form-control" placeholder="Mobile" required/>
				<br/>
				<input type="password" id="password" class="input form-control" placeholder="Password" required/>
				<br/>
				<textarea id="address" class="input form-control" placeholder="Address..."></textarea>
				<br/>
				<input type="submit" value="Signup" class="input bg-primary form-control text-white" />
				</form>
			</div>
			<div class="col-md-4"></div>
		</div>
	</div>
	<script>
		$("#signup").submit(function(e){
			e.preventDefault();
			$.ajax({
	      		url:'Signup',
	      		method:'post',
	      		data:{ 
	      			email:$("#email").val(),
	      			mobile:$("#mobile").val(),
	      			password:$("#password").val(),
	      			address:$("#address").val()
	      		},
	      		success:function(res){
		      		window.location.href="login.jsp"
	      		}
	      	})
		})
	</script>
</body>
</html>