function getProductsAndRecommended(category_id) {
	$('.list-group-item').removeClass('active').addClass('');
	$("#category_" + category_id).addClass('active');
	
	$.getJSON("http://localhost:8080/FoodTimeEcommerce/ws/products/ByCategory/" + category_id,
			function(result) {
				data = result.data;
				$("#div_products").empty();
				for (var row = 0; row < data.length; row = row + 1) {
					var id = data[row].id;
					var name = data[row].name;
					var published = data[row].published;
					var icon = data[row].icon;
					var pricing = data[row].pricing;
					var short_description = data[row].short_description;
					var url = 'item.jsp?id='+id;
					var item = '<div class="col-md-4 mt-3 mb-3">';
					item += "<div class='card efect-elevation'>";
					item += "<div class='card-header p-0'> ";
					item += "<img src='images/"+ icon + "' style='width:100%; height:300px; object-fit:cover;' />"
					item += "</div>";
					item += "<div class='card-body p-4'>";
					item += "<h3>"+ name +"</h3> <hr />"
					item += "<h4> $ "+ pricing +"</h4> <hr />"
					item += "<p>" + short_description + "</p> <hr />"
					item += "<div class='row'>";
					item += "<div class='col-6 m-auto'>";
					item += "<a href='" + url + "' class='btn btn-info text-white'>Visualizar</a>"				
					item += "</div>";
					item += "</div>";
					item += "</div>";
					item += "</div>";
					item += "</div>";
				
					$("#div_products").append(item);
				}
			});
	
	
	
		$.getJSON("http://localhost:5000/users", function(data) {
		
		jsonResult = data.recommendedProducts.toString();
		productos = jsonResult.split(",");

		
		$("#recommendedCategory").empty();
		for (var indice = 0; indice < productos.length; indice++) {
			
			var product_id = productos[indice];
			
			$.getJSON("http://localhost:8080/FoodTimeEcommerce/ws/products/RecommendCategory/" + category_id + "/" + product_id,
					function(result) {
						data = result.data;
						for (var row = 0; row < data.length; row = row + 1) {
							var id = data[row].id;
							var name = data[row].name;
							var published = data[row].published;
							var icon = data[row].icon;
							var pricing = data[row].pricing;
							var short_description = data[row].short_description;
							var url = 'item.jsp?id='+id;
							var item = '<div class="col-md-3 mt-3 mb-3">';
							item += "<div class='card efect-elevation'>";
							item += "<div class='card-header p-0'> ";
							item += "<img src='images/"+ icon + "' style='width:100%; height:300px; object-fit:cover;' />"
							item += "</div>";
							item += "<div class='card-body p-4'>";
							item += "<h3>"+ name +"</h3> <hr />"
							item += "<h4> $ "+ pricing +"</h4> <hr />"
							item += "<p>" + short_description + "</p> <hr />"
							item += "<div class='row'>";
							item += "<div class='col-6 m-auto'>";
							item += "<a href='" + url + "' class='btn btn-info text-white'>Visualizar</a>"				
							item += "</div>";
							item += "</div>";
							item += "</div>";
							item += "</div>";
							item += "</div>";
							$("#recommendedCategory").append(item);
						}
					});
			
		}
	});
	
	
}





function getProductsAndRecommendedByUser(category_id, user_id) {
	$('.list-group-item').removeClass('active').addClass('');
	$("#category_" + category_id).addClass('active');
	
	$.getJSON("http://localhost:8080/FoodTimeEcommerce/ws/products/ByCategory/" + category_id,
			function(result) {
				data = result.data;
				$("#div_products").empty();
				for (var row = 0; row < data.length; row = row + 1) {
					var id = data[row].id;
					var name = data[row].name;
					var published = data[row].published;
					var icon = data[row].icon;
					var pricing = data[row].pricing;
					var short_description = data[row].short_description;
					var url = 'item.jsp?id='+id;
					var item = '<div class="col-md-4 mt-3 mb-3">';
					item += "<div class='card efect-elevation'>";
					item += "<div class='card-header p-0'> ";
					item += "<img src='images/"+ icon + "' style='width:100%; height:300px; object-fit:cover;' />"
					item += "</div>";
					item += "<div class='card-body p-4'>";
					item += "<h3>"+ name +"</h3> <hr />"
					item += "<h4> $ "+ pricing +"</h4> <hr />"
					item += "<p>" + short_description + "</p> <hr />"
					item += "<div class='row'>";
					item += "<div class='col-6 m-auto'>";
					item += "<a href='" + url + "' class='btn btn-info text-white'>Visualizar</a>"				
					item += "</div>";
					item += "<div class='col-6'>";
					item += "<a href=javascript:addToCart('" + id + "') class='btn btn-success text-white'>Añadir</a>"	
					item += "</div>";
					item += "</div>";
					item += "</div>";
					item += "</div>";
					item += "</div>";
				
					$("#div_products").append(item);
				}
			});
	
	
	
		$.getJSON("http://localhost:5000/user/"+user_id, function(data) {
		
		jsonResult = data.recommendedProducts.toString();
		productos = jsonResult.split(",");

		
		$("#recommendedCategory").empty();
		for (var indice = 0; indice < productos.length; indice++) {
			
			var product_id = productos[indice];
			
			$.getJSON("http://localhost:8080/FoodTimeEcommerce/ws/products/RecommendCategory/" + category_id + "/" + product_id,
					function(result) {
						data = result.data;
						for (var row = 0; row < data.length; row = row + 1) {
							var id = data[row].id;
							var name = data[row].name;
							var published = data[row].published;
							var icon = data[row].icon;
							var pricing = data[row].pricing;
							var short_description = data[row].short_description;
							var url = 'item.jsp?id='+id;
							var item = '<div class="col-md-3 mt-3 mb-3">';
							item += "<div class='card efect-elevation'>";
							item += "<div class='card-header p-0'> ";
							item += "<img src='images/"+ icon + "' style='width:100%; height:300px; object-fit:cover;' />"
							item += "</div>";
							item += "<div class='card-body p-4'>";
							item += "<h3>"+ name +"</h3> <hr />"
							item += "<h4> $ "+ pricing +"</h4> <hr />"
							item += "<p>" + short_description + "</p> <hr />"
							item += "<div class='row'>";
							item += "<div class='col-6 m-auto'>";
							item += "<a href='" + url + "' class='btn btn-info text-white'>Visualizar</a>"				
							item += "</div>";
							item += "</div>";
							item += "</div>";
							item += "</div>";
							item += "</div>";
							$("#recommendedCategory").append(item);
						}
					});
			
		}
	});
	
	
}


function getCategories(category_id, user_id) {
	$.getJSON("http://localhost:8080/FoodTimeEcommerce/ws/category", function(result) {
		data = result.data;
		$("#div_categories").empty();
		for (var row = 0; row < data.length; row = row + 1) {
			var id = data[row].id;
			var name = data[row].name;
			var item_class = "list-group-item";
			if (id == category_id) {
				item_class = "list-group-item active";
			}
			
			if(user_id == null){
				
				$("#div_categories").append(
						"<a href='javascript:getProductsAndRecommended(" + id
								+ ");' id='category_" + id + "' class='"
								+ item_class + " p-3 categories-efect'>" + name + "</a>");
				
			}else{

				$("#div_categories").append(
						"<a href='javascript:getProductsAndRecommendedByUser(" + id
								+ ","+ user_id + ");' id='category_" + id + "' class='"
								+ item_class + " p-3 categories-efect'>" + name + "</a>");
				
			}
			
		}
	});
}


function getProductsRecommended() {

	$.getJSON("http://localhost:5000/users", function(data) {
		
		jsonResult = data.recommendedProducts.toString();
		productos = jsonResult.split(",");
		console.log(productos.length)
		
		for (var indice = 0; indice < productos.length; indice++) {
			
			var product_id = productos[indice];
			
			$.getJSON("http://localhost:8080/FoodTimeEcommerce/ws/products/ListRecommended/" + product_id,
					function(result) {
						data = result.data;
					console.log(data)
						for (var row = 0; row < data.length; row = row + 1) {
							var id = data[row].id;
							var name = data[row].name;
							var published = data[row].published;
							var icon = data[row].icon;
							var pricing = data[row].pricing;
							var short_description = data[row].short_description;
							var url = 'item.jsp?id='+id;
							var item = '<div class="col-md-3 mt-3 mb-3">';
							item += "<div class='card efect-elevation'>";
							item += "<div class='card-header p-0'> ";
							item += "<img src='images/"+ icon + "' style='width:100%; height:300px; object-fit:cover;' />"
							item += "</div>";
							item += "<div class='card-body p-4'>";
							item += "<h3>"+ name +"</h3> <hr />"
							item += "<h4> $ "+ pricing +"</h4> <hr />"
							item += "<p>" + short_description + "</p> <hr />"
							item += "<div class='row'>";
							item += "<div class='col-6 m-auto'>";
							item += "<a href='" + url + "' class='btn btn-info text-white'>Visualizar</a>"				
							item += "</div>";
							item += "</div>";
							item += "</div>";
							item += "</div>";
							item += "</div>";
							$("#recommended").append(item);
						}
					});
			
		}
	});
}






	
	
	