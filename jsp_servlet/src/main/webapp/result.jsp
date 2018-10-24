<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<body>
	<center>
		<h1>Available Brands</h1>
		<%
			//if (request.getParameter("Type")== null) {
				//response.sendRedirect("index.html");
			//}
				List result = (List) request.getAttribute("brands");
				Iterator it = result.iterator();

				out.println("<br>We have <br><br>");
				while (it.hasNext()) {
					out.println(it.next() + "<br>");
				}
			
		%>
		<a href="index.html"> <input type="submit" value="devolver"
			id="button-2" />
		</a>
</body>
</html>