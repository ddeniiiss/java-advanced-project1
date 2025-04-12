$( document ).ready(function() {
     $('.leftmenutrigger').on('click', function(e) {
     $('.side-nav').toggleClass("open");
     e.preventDefault();
    });
});

$("a.product-logout").click(function(){
	$.get("logout", function(data){
		if(data == "Success"){
			window.location.href = 'login.jsp'; 
		}
	})
	
});

$(document).ready(function(){
	$.get("user-role", function(data) {
		if (data !== '') {
			userRole = data;
		}
	}).done(function() {
		if(userRole === 'ADMINISTRATOR'){
			$('li.bucket-option').hide();
			$('button.buy-single-product').hide();
		}else if(userRole === 'USER'){
			$('li.create-product-option').hide();
		}
		
		
	});
	
	
});
