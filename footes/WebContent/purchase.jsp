
<%@ page import="org.software.entities.User" %>



<% User usuario = (User)session.getAttribute("usuario");

	if(usuario == null){
		
		response.sendRedirect("index.jsp");	
		
	}
%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Ecommerce - FoodTime</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<link rel="stylesheet" href="css/styles.css">
</head>

<body>
	<jsp:include page="componentes-reactivos/navbar.jsp"></jsp:include>

	<div class="container" style="margin-top: 150px">
		<div class="row align-items-center">

			<div class="col-md-3">
				<h1 >COMPRAS</h1>
				<div class="list-group" id="div_purchases"></div>
			</div>
			<!-- /.col-lg-3 -->

			<div class="col-md-9">
				<div class="card shopping-cart">
					<div class="card-header bg-dark text-light">
						<i class="fa fa-shopping-cart" aria-hidden="true"></i> Shopping
						cart
					</div>
					<div id="div_items" class="card-body"></div>
					<div class="card-footer p-3 text-center">

						<p>
							Total price: <b id="total">$0.00</b>
						</p>

					</div>
				</div>

			</div>

		</div>

	</div>

	<jsp:include page="componentes-reactivos/footer.jsp"></jsp:include>


	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	<script src="js/cart.js"></script>
	<script src="js/purchase.js"></script>

	<script>
		$(document).ready(function() {
			getPurchases();
			getItems(0);
			updateItemsCount();
		});
	</script>

</body>
</html>