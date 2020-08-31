<%@ page import="org.software.entities.User"%>

<%
	User usuario = (User) session.getAttribute("usuario");
%>

<div class="container-fluid text-center p-5 bg-light">
	<div class="row align-items-center justify-content-center">
		<div class="col-md-12 p-5">
			<%
				if (usuario == null) {
			%>
			<h1><span class="badge badge-info">RECOMENDACIONES POR RATING</span></h1>
			<%
				}
			%>
			<%
				if (usuario != null) {
			%>

			<h1>
				<span class="badge badge-info"> RECOMENDACIONES PARA <%=usuario.getUsername().toUpperCase()%></span>
			</h1>
			<%
				}
			%>

		</div>
	</div>
	<div class="row justify-content-center p-5 align-items-center" id="recommendedCategory"></div>
</div>


		<!-- DECORACIÓN  -->
	<div class="container-fluid text-center efect-elevation-normal my-5">
		
		<div class="row">
			<div class="col-md-6 m-auto p-5 my-5">
				<h1><span class="badge badge-info p-5"> RECOMENDACIONES PARA LOS CLIENTES</span></h1>
			</div>
		</div>

	</div>
	<!-- FIN DECORACIÓN  -->
	
		<div class="container-fluid text-center my-5 bg-light">
		
			<div class="row justify-content-center p-5 align-items-center" id="recommended"></div>

		</div>




