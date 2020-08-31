<%@ page import='org.software.entities.User' %>
<%

	User usuario = (User)session.getAttribute("usuario");
	if (usuario == null){
		usuario = null;
	}
%>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-light efect-elevation-normal p-3 bg-white fixed-top">
	<div class="container">
		<a class="navbar-brand" href="http://localhost:8080/FoodTimeEcommerce">Ecommerce - FoodTime</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
			<% if(usuario != null) {%>
				<li class="nav-item"><a class="nav-link" href="cart.jsp">
						<button type="button" class="btn btn-success">
							Shoping CART <i class="fa fa-shopping-cart px-1" style="font-size: 1.3em" aria-hidden="true"></i> <span id="shopping_cart" class="badge badge-light">0</span>
						</button>
				</a></li>
			<% }%>
				<% if(usuario == null){ %>
				<li class="nav-item"><a class="nav-link" href="login.jsp">
						<button type="button" class="btn btn-success">Login</button>
				</a></li>
				<% } %>
				<% if(usuario != null){ %>
				<li class="nav-item">
					<div class="btn-group nav-link" role="group">
						<button id="btnGroupDrop1" type="button"
							class="btn btn-success dropdown-toggle" data-toggle="dropdown"
							aria-haspopup="true" aria-expanded="false">
							<%= usuario.getUsername() %>
						</button>
						<div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
							<a class="dropdown-item" href="purchase.jsp">Purchases</a> <a
								class="dropdown-item" href="Logout">Logout</a>
						</div>
					</div>
				</li>
				<% } %>

			</ul>
		</div>
	</div>
</nav>