function getProductById(item_id, user_id) {

	$.getJSON("http://localhost:8080/FoodTimeEcommerce/ws/products/ListRecommended/"+ item_id,
			function(result) {
				data = result.data;
					for (var row = 0; row < data.length; row = row + 1) {
						var id = data[row].id;
						var name = data[row].name;
						var published = data[row].published;
						var icon = data[row].icon;
						var pricing = data[row].pricing;
						var media = data[row].media;
						var short_description = data[row].short_description;
						var long_description = data[row].long_description;
						var item = '<div class="col-md-6 my-5 mx-auto text-center" >';
						item += "<div class='card efect-elevation'>";
						
						item += "<div class='card-header p-0'> ";
						item += "<img src='images/"+ icon+ "' style='width:100%; height:400px; object-fit:cover;' />"
						item += "</div>";
						
						item += "<div class='card-body p-4' id='ratingSuccess'>";
						item += "<h3 class='my-2'>" + name + "</h3> <hr />"
						item += "<h4 class='my-2'> Precio: </h4> <p class='badge badge-success' style='font-size:1.2em'> $" +pricing +" </p><hr />";
						item += "<h4 class='my-2'> Calificación : </h4> <p class='badge badge-info' style='font-size:1.2em'>" +Math.round(media) +" de 5 Estrellas</p> <hr />";
						item += "<h4 class='my-2'> Descripción : </h4> <p class='badge badge-dark' style='font-size:1.2em'>" +short_description +"</p> <hr />";
						item += "<h4 class='my-2'> Ingredientes : </h4> <p class='badge badge-dark' style='font-size:1.2em'>" +long_description +"</p>  <hr />";
						
						if(user_id != null){
							
							item += "<h4 class='pt-5'> Calificar </h4>";
							
							item+= "<form class='mt-5'>";
							
							item+=  "<div class='form-group row align-items-center'>";
							item+=    "<label for='rating' class='col-sm-3 m-auto col-form-label'>Rating</label>";
							item+=    "<div class='col-sm-5 m-auto'>";
							item+=      "<input type='number' min='1' max='5' step='any' class='form-control' id='rating'>";
							item+=    "</div>";
							item+=  "</div>";

							item+=  "<div class='form-group row align-items-center'>";
							item+=    "<label for='comment' class='col-sm-3 m-auto col-form-label'>Comment</label>";
							item+=    "<div class='col-sm-5 m-auto'>";
							item+=      "<textarea class='form-control' id='comment' required></textarea>";
							item+=    "</div>";
							item+=  "</div>";
							
							item+= "</form>";					
							item+= "</div>";
							item +="<div id='ratingMsg'></div>"
								
							item += "<button type='button' onclick='addReview("+ user_id +","+id+")' class='btn btn-success mx-auto mb-5 p-2'>Calificar</button>"
							
							

						}
												
						item += "</div>";			
						item += "</div>";
						item += "</div>";
						$("#product_show").append(item);
					}
			});

}


	

function addReview(user_id,item_id){
	var rating = document.getElementById("rating").value;
	var comment = document.getElementById("comment").value;
	
	if(rating>=1 && rating <=5 ){
		
		$.ajax({
			'url':'http://localhost:8080/FoodTimeEcommerce/ws/review/add',
			'method':'POST',
			'dataType': 'json',
			'contentType': 'application/json',
			'data':JSON.stringify({
				"user_id":user_id,
				"item_id":item_id, 
				"rating":rating, 
				"comment":comment
			}),
			'success': function(data){
				console.log(data)
				document.getElementById("rating").value = 0.0;
	        	document.getElementById("comment").value = "";
	        	
	        	$('#ratingMsg').empty();
	        	var at = '<div class="alert alert-success" role="alert">'; 
	        	at += '<button type="button" class="close" data-dismiss="alert" aria-label="Close">';
				at += '<span aria-hidden="true">&times;</span></button>';
				at += '<strong>Excelente!</strong> ' + data.message + '!';
				at += '</div>';
				$('#ratingMsg').append(at);

				window.setTimeout(function() {
			    	$(".alert").fadeTo(500, 0).slideUp(500, function(){
			        	$(this).remove(); 
			      	});
			  	}, 4000);
			},
	        'failure': function(errorMsg) {
	        	console.log(errorMsg);
				document.getElementById("rating").value = 0.0;
	        	document.getElementById("comment").value = "";
	        	
	        	$('#ratingMsg').empty();
	        	var at = '<div class="alert alert-danger" role="alert">'; 
	        	at += '<button type="button" class="close" data-dismiss="alert" aria-label="Close">';
				at += '<span aria-hidden="true">&times;</span></button>';
				at += '<strong>Error!</strong> ' + errorMsg.message + '!';
				at += '</div>';
				$('#ratingMsg').append(at);
				
				window.setTimeout(function() {
			    	$(".alert").fadeTo(500, 0).slideUp(500, function(){
			        	$(this).remove(); 
			        	
			      	});
			  	}, 3000);
	        }
		});
		
	}else{
		
		
		
		$('#ratingMsg').empty();
    	var at = '<div class="alert alert-warning" role="alert">'; 
    	at += '<button type="button" class="close" data-dismiss="alert" aria-label="Close">';
		at += '<span aria-hidden="true">&times;</span></button>';
		at += '<strong>Advertencia!</strong> El rating debe ser calificado entre 1 y 5!';
		at += '</div>';
		$('#ratingMsg').append(at);

		window.setTimeout(function() {
	    	$(".alert").fadeTo(500, 0).slideUp(500, function(){
	        	$(this).remove(); 
	      	});
	  	}, 4000);
		
	}
	
	
}


