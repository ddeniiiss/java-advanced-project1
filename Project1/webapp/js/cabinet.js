var products = null;

$.get("products", function(data){
	if(data !== ''){
		products = data;
	}
}).done(function(){
	
	var cardsContent = "";
	jQuery.each(products, function(i, value) {

		cardsContent+="<div class='col'>" +
					  "<div class='card'>" +
					  "<div class='card-body'>" +
					  "<h5 class='card-title'>" + value.name + "</h5>"+
					  "<h6 class='card-subtitle mb-2 text-muted'>" + value.description + "</h6>"+
					  "<p class='card-text'>" + value.price + "</p>"+
					  "<a href='product?id=" + value.id + "' class='card-link'>More</a>"+
					  "</div>" +
					  "</div>" +
					  "</div>" +
					  "</div>"
	});

	  $('#productCards').html(cardsContent);
	
});