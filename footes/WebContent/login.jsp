<%@ page import='org.software.entities.User'%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	User usuario = (User) session.getAttribute("usuario");

	if (usuario != null) {

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

<title>Login</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="css/styles.css">
</head>

<body>
	<!-- Navigation -->
	<jsp:include page="componentes-reactivos/navbar.jsp"></jsp:include>

	<!-- Page Content -->
	<div class="container py-5" style="margin-top: 100px">

		<div class="row">
			<div class="col-md-6 mx-auto">

				<!-- form card login -->
				<div class="card efect-elevation" style="border-radius: 50px">
					<div class="card-body p-5 text-center">
					 <img src="images/login.png" class="card-img rounded-circle efect-elevation-normal" alt="login.png" style="width: 250px">
						<form action="Login" class="form" role="form" autocomplete="off"
							id="formLogin" method="POST">
							<div class="form-group pt-5 text-left">
								<label for="uname1">Email</label> <input type="text"
									class="form-control form-control-lg rounded-0" name="username"
									id="username">
							</div>
							<div class="form-group pt-3 text-left">
								<label>Password</label> <input type="password"
									class="form-control form-control-lg rounded-0" id="password"
									name="password">
							</div>
							<div class="alert alert-danger alert-dismissible fade" id="errorMsg" role="alert"></div>
							<button type="submit" class="btn btn-success btn-lg"
								id="btnLogin">Login</button>
						</form>
					</div>
					<!--/card-block-->
				</div>
				<!-- /form card login -->

			</div>
		</div>
		<!--/row-->

	</div>
	<!-- /.container -->

	<!-- Footer -->
	<jsp:include page="componentes-reactivos/footer.jsp"></jsp:include>

	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

	<script type="text/javascript" src="js/login.js"></script>
	<script type="text/javascript" src="js/cart.js"></script>

	<script type="text/javascript">
	updateItemsCount();
	<%String errorMsg = (String) request.getAttribute("errorMsg");%>
	var errorMsg = "<%=errorMsg%>"
	validate(errorMsg)
	</script>


</body>
</html>









