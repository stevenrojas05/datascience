<%@ page import='org.software.entities.User'%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	User usuario = (User) session.getAttribute("usuario");

	if (usuario == null) {

		response.sendRedirect("index.jsp");

	}
%>
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

<link href="css/cart.css" rel="stylesheet">
<link rel="stylesheet" href="css/styles.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.css">
</head>

<body>
	<!-- Navigation -->
	<jsp:include page="componentes-reactivos/navbar.jsp"></jsp:include>

	<!-- Page Content -->


	<div class="container">
		<div class="card shopping-cart">
			<div class="card-header bg-dark text-light p-3">
				<i class="fa fa-shopping-cart px-1" aria-hidden="true"></i> Shopping
				cart <a href="http://localhost:8080/FoodTimeEcommerce"
					class="btn btn-outline-info btn-sm float-right">Continue
					shopping</a>
				<div class="clearfix"></div>
			</div>
			<div id="div_items" class="card-body">
				<div class="row">
					<div class="col-md-6 m-auto text-center">
						<a href="http://localhost:8080/ecommerce"
							class="btn btn-outline-secondary"> Update shopping cart </a>
					</div>
				</div>

			</div>
			<div class="card-footer text-center">

				<div class="row">
					<div class="col-4 m-auto">
						Total price: <b id="total">$0.00</b>
					</div>
					<div class="col-4 m-auto">
						<a href="javascript:checkout();" id="checkout"
							class="btn btn-success float-right">Checkout</a>
					</div>
				</div>

			</div>
		</div>
	</div>


	<!-- Footer -->
	<jsp:include page="componentes-reactivos/footer.jsp"></jsp:include>

	<!-- Bootstrap core JavaScript -->

	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	<script src="js/cart.js"></script>

	<script>
		$(document).ready(function() {
			getItems();
			updateItemsCount();
		});
	</script>

</body>
</html>