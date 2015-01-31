<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Property Fields</title>
</head>
<body>

<table>
	<thead>
	<td>Column Name</td>
	<td>Column Type</td>
	<td>Mandatory</td>
	<td>Unique Inclusion</td>
	</thead>
	<c:forEach items="${fields}" var="field">
		<tr>
			<td>${field.columnName}</td>
			<td>${field.columnType}</td>
			<td>${field.mandatory}</td>
			<td>${field.uniqueInclusion}</td>
		</tr>
	</c:forEach>
</table>


</body>
</html>
