$("button.createProduct").click(function(){
	var name = $("form.create-product-form input.productName").val();
	var description = $("form.create-product-form input.productDescription").val();
	var price = $("form.create-product-form input.productPrice").val();
	
	var product = {
		name: name,
		description: description,
		price: price
	};
	
	$.post("product", product, function(data){
		if(data == "Success"){
			alert("Success");
		}
	});
	
});

$("button.buy-product").click(function(){
	var productId = $(this).attr("product-id");
	
	$.post("bucket", {'productId': productId}, function(data){
		if(data == 'Success'){
			$("[data-dismiss=modal]").trigger({type:"click"});
		}
		
	});
	
	
});