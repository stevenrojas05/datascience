

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	
<%@ page import="org.software.entities.User"%>
<%
	User usuario = (User) session.getAttribute("usuario");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ecommerse - FoodTime</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<link rel="stylesheet" href="css/styles.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.css">

</head>

<body>


	<jsp:include page="componentes-reactivos/navbar.jsp"></jsp:include>


	<!-- CAROUSEL  -->

	<div id="carouselExampleCaptions" class="carousel slide"
		data-ride="carousel">
		<ol class="carousel-indicators py-3">
			<li data-target="#carouselExampleCaptions" data-slide-to="0"
				class="active"></li>
			<li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
			<li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
		</ol>
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="images/carousel1.jpg" class="d-block w-100"  style="object-fit:cover; height:100vh" alt="carousel1">
				<div class="carousel-caption d-none d-md-block py-4" style="background-color: rgba(0,0,0,0.5); border-radius: 50px;">
					<h3>ALIMENTOS FRESCOS</h3>
					<p style="font-size: 1.2em">La mejor selección de frutas y verduras para realizar las recetas de FoodTime</p>
				</div>
			</div>
			<div class="carousel-item">
				<img src="images/carousel2.jpg" class="d-block w-100" style="object-fit:cover; height:100vh" alt="carousel2">
				<div class="carousel-caption d-none d-md-block py-4" style="background-color: rgba(0,0,0,0.5); border-radius: 50px;">
					<h3>PPROFESIONALES EXCEPCIONALES</h3>
					<p style="font-size: 1.2em">Tenemos los mejores cocineros que realizan su trabajo con mucha pasión</p>
				</div>
			</div>
			<div class="carousel-item">
				<img src="images/carousel3.jpg" class="d-block w-100" style="object-fit:cover; height:100vh" alt="carousel3">
				<div class="carousel-caption d-none d-md-block py-4" style="background-color: rgba(255,255,255,0.3); border-radius: 50px;">
					<h3>SITIO AGRADABLE</h3>
					<p style="font-size: 1.2em">Instalaciones amobladas y diseñadas para el bienestar y comodidad del cliente</p>
				</div>
			</div>
		</div>
		<a class="carousel-control-prev" href="#carouselExampleCaptions"
			role="button" data-slide="prev"> <span
			class="carousel-control-prev-icon" aria-hidden="true"></span> <span
			class="sr-only">Previous</span>
		</a> <a class="carousel-control-next" href="#carouselExampleCaptions"
			role="button" data-slide="next"> <span
			class="carousel-control-next-icon" aria-hidden="true"></span> <span
			class="sr-only">Next</span>
		</a>
	</div>
	
	<!-- FIN CAROUSEL  -->


	<!-- DECORACIÓN  -->
	<div class="container-fluid text-center efect-elevation-normal my-5">
		
		<div class="row">
			<div class="col-md-6 m-auto p-5 my-5">
				<h1><span class="badge badge-info p-5"> CATEGORÍAS Y PRODUCTOS </span></h1>
			</div>
		</div>

	</div>	
	<!-- FIN DECORACIÓN  -->


	<!-- CATEGORÍAS Y PRODUCTOS  -->
	<div class="container-fluid bg-light text-center p-5"> 
		<div class="row align-items-center">
			<div class="col-md-2 m-auto">
				<jsp:include page="componentes-reactivos/category.jsp"></jsp:include>
			</div>
			<div class="col-md-8 m-auto">
				<jsp:include page="componentes-reactivos/products.jsp"></jsp:include>
			</div>

		</div>
	</div>	
	<!-- FIN CATEGORÍAS Y PRODUCTOS  -->
	
	
		<!-- DECORACIÓN  -->
	<div class="container-fluid text-center efect-elevation-normal my-5">
		
		<div class="row">
			<div class="col-md-6 m-auto p-5 my-5">
				<h1><span class="badge badge-info p-5"> RECOMENDACIONES</span></h1>
			</div>
		</div>

	</div>
	
	
	<!-- FIN DECORACIÓN  -->

	<!-- RECOMENDACIONES  -->

	<jsp:include page="componentes-reactivos/products_recommended.jsp"></jsp:include>

	<!-- FIN RECOMENDACIONES  -->




	<jsp:include page="componentes-reactivos/footer.jsp"></jsp:include>





	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
	<script src="js/recommend.js"></script>
	<script src="js/cart.js"></script>
	<script type="text/javascript">
		updateItemsCount();
		getProductsRecommended();
	<%if (usuario == null) {%>
		getCategories(1, null);
		getProductsAndRecommended(1);
	<%}%>
		
	<%if (usuario != null) {%>
		getCategories(1,<%=usuario.getId()%>);
		getProductsAndRecommendedByUser(1,<%=usuario.getId()%>);
	<%}%>
		
	</script>

</body>
</html>