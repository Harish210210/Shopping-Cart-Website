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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<div class="container-fluid bg-info p-2">
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8 text-center"><h2>Online Store</h2></div>
			<div class="col-md-2 text-center"><h2 id="cart" style="cursor:pointer">Cart<i class="fa fa-shopping-cart" aria-hidden="true"></i></h2></div>
		</div>
	</div>
	<div class="container-fluid p-5">
		<div class="container p-5">
			<div class="row">
				<div class="col-md-3">
					<select id="cate" class="form-control">
						<option>Select Category</option>
					</select>
				</div>
				<div class="col-md-3">
					<select id="sort" class="form-control">
						<option value="">Price Sorting</option>
						<option value="asc">Lowest to Highest</option>
						<option value="desc">Highest to Lowest</option>
					</select>
				</div>
			</div>	
			<br/><br/>	
			<center><div id="items" style="display:flex;flex-wrap:wrap">
				
			</div></center>
		</div>
		<div class="modal fade" id="addcart">
			<div class="modal-dialog">
			    <div class="modal-content">
			    <div class="modal-header">
			    	<h3>Add to Cart</h3>
			        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
			      </div>
			      <div class="modal-body">
			        <label>Enter Pin Code</label>
			        <input id="pin" type="number" class="input form-control" />
					<br/>
					<button type="button" id="addc" class="form-control bg-info">Submit</button>
			      </div>
			    </div>
			  </div>
			</div>
	</div>
	<script>
		var products = [];
		var category = [];
		$("#cart").click(function(){
			window.location.href="cart.jsp"
		})
		jQuery(document).ready(function($){
			$.ajax({
	      		url:'Products',
	      		method:'post',
	      		success:function(res){
		      		 res.map((ele)=>{
		      			 var product = {pid:ele.pid, pname:ele.pname, price:ele.price, image:ele.image, pcatid:ele.pcatid};
		      			 products.push(product);
		      		 })
		      		 updateStore(products);
	      		}
	      	})
	      	$.ajax({
	      		url:'Category',
	      		method:'post',
	      		success:function(res){
	      			res.map((ele)=>{
	      				var cat = {pcatid:ele.pcatid, pcatname:ele.pcatname};
	      				category.push(cat);
	      			})
	      			updateCategory(category)
	      		}
	      	})
	  	});
		function updateStore(products){
			$("#items").empty();
			for(let i=0;i<products.length;i++){
				var item = '<div class="text-center m-4"><img class="img rounded" height="200px" src="'+products[i].image+'" /><h4>'
		     	+products[i].price+'/-</h4><h4>'
		     	+products[i].pname+'</h4><input type="number" id="cnt'
		     	+products[i].pid+'" value=1 min=1 max=10 class= "m-2 input form-control" /><button data-bs-toggle="modal" data-bs-target="#addcart" onclick="adc('
		     	+products[i].pid+')" class="m-2 form-control bg-info">Add to Cart</button></div>';
				$("#items").append(item);
			}			
		}
		function updateCategory(category){
			$("#cate").empty();
			var opt = $("<option></option>").attr("value", "All").text("All");
			    $("#cate").append(opt);
  			category.map((ele)=>{     				
  				var newOption = $("<option></option>").attr("value", ele.pcatname).text(ele.pcatname);
  			    $("#cate").append(newOption);
      		 })			
		}
		var c=0;
		$("#cate").click(function(){
			var catProducts = [];
			var cat = $("#cate").val();
			var pcatid = "";
			category.map((ele)=>{
				if(ele.pcatname === cat){
					pcatid = ele.pcatid;
				}
			})
			console.log(pcatid);
			if(cat==="All"){
				updateStore(products);
			}
			else{
				products.map((ele)=>{
					if(ele.pcatid === pcatid){
						catProducts.push(ele);
					}
				})
				updateStore(catProducts);
			}
		})
		$("#sort").click(function(){
			var catProducts = products.slice();
			var sort = $("#sort").val();
			if(sort === "asc"){
				catProducts.sort((a, b) => {
				    return Number(a.price) - Number(b.price);
				});
			}
			if(sort === "desc"){
				catProducts.sort((a, b) => {
				    return Number(b.price) - Number(a.price);
				});
			}
			updateStore(catProducts);
		})
		var prdid=0;
		$("#addc").click(function(){
			$.ajax({
	      		url:'ToCart',
	      		method:'post',
	      		data:{ 
	      			pid:""+prdid+"",
	      			qt:$("#cnt"+prdid+"").val(),
	      			region:$("#pin").val()
	      		},
	      		success:function(res){
		      		 if(res==="Success"){
		      			 alert("Added to Cart");
		      		 }
		      		 else{
		      			 alert("This Region is Non Serviceable")
		      		 }
	      		}
	      	})
		})
		function adc(i){
			prdid=i;			
		}
	</script>
</body>
</html>