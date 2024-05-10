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
<script src="https://checkout.razorpay.com/v1/checkout.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<div class="container-fluid bg-info p-2">
		<div class="row">
			<div class="col-md-2 text-center"><h2 id="back" style="cursor:pointer"><i class="fa fa-caret-left" aria-hidden="true"></i>Back</h2></div>
			<div class="col-md-8 text-center"><h2>Cart Page</h2></div>
			<div class="col-md-2 text-center"></div>
		</div>
	</div>
	<div class="container-fluid p-5">
		<div class="container p-5">
			<center><div id="items" style="display:flex;flex-wrap:wrap">
				
			</div></center>
		</div>
		<div class="modal fade" id="checkout">
		  <div class="modal-dialog modal-xl">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h4 class="modal-title">Checkout Page</h4>
		        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
		      </div>
		      <div class="modal-body">
		        <div class="container ps-5 pe-5">
		        	<h5>Order Details</h5>
		        	<table class="table table-striped" id="ckd">
		        	</table>
		        	<br/>
		        	<div id="total">
							        	
		        	</div>
		        	<div class="row">
						<div class="col-md-3"></div>
						<div class="col-md-6 p-3">
							<button id="order" class="form-control bg-info" >Place Order</button>
						</div>
					</div>
		        </div>
		      </div>
		
		    </div>
		  </div>
		</div>
		<div class="row">
			<div class="col-md-5"></div>
			<div class="col-md-2">
				<button id="btnspace" data-bs-toggle="modal" data-bs-target="#checkout" class="bg-info form-control" style="display:none">Order All</button>
			</div>
		</div>
	</div>
	<script>	
	
		$("#back").click(function(){
			window.location.href="store.jsp"
		})
		jQuery(document).ready(function($){
			cartDetails();	
			
	  	});
		function remove(i){
			$.ajax({
	      		url:'RemoveCartItem',
	      		method:'post',
	      		data:{ 
	      			pid:""+i+""
	      		},
	      		success:function(res){
		      		 cartDetails();
	      		}
	      	})
		}
		let currentDate = new Date();
		let year = currentDate.getFullYear();
		let month = currentDate.getMonth() + 1; 
		let day = currentDate.getDate();
		var curdate = year + '-' + month + '-' + day;
		$("#order").click(function(){
			$.ajax({
	      		url:'Order',
	      		method:'post',
	      		data:{
	      			total:total,
					date:curdate
	      		},
	      		success:function(res){
	      			cartDetails();
	      		}
	      	})
	      	
	      	var amount = total*100;	 
	        fetch('razorpay', {
	            method: 'POST',
	            headers: {
	                'Content-Type': 'application/json'
	            },
	            body: JSON.stringify({ amount: amount })
	        })
	        .then(function(response) {
	            return response.json();
	        })
	        .then(function(data) {
	            var options = {
	                "key": "rzp_test_qz0DkggQja3OTl",
	                "amount": amount,
	                "currency": "INR",
	                "name": "Pennant Shopping Cart",
	                "description": "Test Payment",
	                "order_id": data.id,
	                "handler": function(response) {
	                    // Handle successful payment
	                    console.log(response);
	                    alert('Payment successful: ' + response.razorpay_payment_id);
	                },
	                "prefill": {
	                    "name": "Test User",
	                    "email": "harishmaddala@gmail.com"
	                },
	                "theme": {
	                    "color": "#3399cc"
	                }
	            };
	            var rzp = new Razorpay(options);
	            rzp.open();
	        })
	        .catch(function(error) {
	            console.error('Error:', error);
	        });
		})
		var total = 0;
		$("#btnspace").click(function(){
			$.ajax({
	      		url:'Checkout',
	      		method:'post',
	      		success:function(res){
	      			console.log(res);
	      			$("#ckd").empty();
	      			$("#total").empty();
	      			var tt = 0;
	      			$("#ckd").append("<tr><th>Name</th><th>Quantity</th><th>Price</th><th>GST</th><th>Shipping Charges</th><th>Total</th></tr>");
	      			res.map((ele)=>{
	      				var row = "<tr><td>"+ele.pname+"</td><td>"+ele.quantity+"</td><td>"+ele.price+"\\-</td><td>"+ele.gst+"%</td><td>"+(5*ele.pid)+"\\-</td><td>"
	      				+Math.round((Number(ele.price)*Number(ele.quantity))+((Number(ele.price)*Number(ele.quantity)*Number(ele.gst))/100)+(5*ele.pid))+"\\-</td></tr>";
	      				$("#ckd").append(row);
	      				tt += Math.round((Number(ele.price)*Number(ele.quantity))+((Number(ele.price)*Number(ele.quantity)*Number(ele.gst))/100)+(5*ele.pid));
	      			})
	      			$("#total").append("<h5>Total Price : "+tt+"\\-</h5>");
	      			total = tt;
	      		}
	      	})
		})
		function cartDetails(){
			$.ajax({
	      		url:'CartDetailsList',
	      		method:'post',
	      		success:function(res){
	      			$("#items").empty();
		      		 res.map((ele)=>{
		      			 var item = '<div class="text-center m-4"><img class="img rounded" height="200px" src="'+ele.image+'" /><h4>'
					     	+ele.price+'/-</h4><h5>Quantity : '
					     	+ele.quantity+'</h5><button data-bs-toggle="modal" data-bs-target="#addcart" onclick="remove('
					     	+ele.pid+')" class="m-2 form-control bg-info">Remove</button></div>';
		      			$("#items").append(item);
		      		 })
		      		 if(res.length!=0){
		      			$("#btnspace").css('display','block'); 
		      		 }
		      		 else{
		      			$("#btnspace").css('display','none'); 
		      		 }
		      		
	      		}
	      	})
		}
	</script>
</body>
</html>