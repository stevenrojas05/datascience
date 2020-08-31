<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="org.software.entities.User" %>

<% 
	User usuario = (User)session.getAttribute("usuario");
	String item_id = request.getParameter("id");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ecommerse - FoodTime</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<link rel="stylesheet" href="css/styles.css">

</head>
<body>


	<jsp:include page="componentes-reactivos/navbar.jsp"></jsp:include>


	<div class="container-fluid " style="margin-top:150px">
		<div class="row" id="product_show"></div>
	</div>



	<jsp:include page="componentes-reactivos/footer.jsp"></jsp:include>





	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

	<script type="text/javascript" src="js/item.js"></script>
	<script type="text/javascript" src="js/cart.js"></script>

	<script type="text/javascript">
	updateItemsCount();
		<%if(usuario == null){%>
			getProductById(<%=item_id%>,null)
		<%}%>
		<%if(usuario != null){%>
		getProductById(<%=item_id%>,<%=usuario.getId()%>)
		<%}%>
	</script>


</body>
</html>