<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Top Level Properties</title>
</head>
<body>

	<table>
		<c:forEach items="${properties}" var="property">
	        <tr>
				<td>${property.name}</td>
	        </tr>

		</c:forEach>
	</table>

</body>
</html>
