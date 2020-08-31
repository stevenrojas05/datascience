	
function validate(errorMsg) {

	
	if(errorMsg !== 'null'){
		
		$("#errorMsg").addClass("show").empty();
		
		$("#errorMsg").append("<strong>"+errorMsg+"</strong>");
		
	}

}



















