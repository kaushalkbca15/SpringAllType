<%@page import="java.util.ArrayList"%>
<%@ page isELIgnored="false" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@page import="java.util.*" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center">Employee details</h1>
	<hr><br>
	<%-- ${TotalPage} --%>
	
	<table align="center" style="width:800px;" border="1">
		<tr>
			<td colspan="6">${responseMessage}</td>
		</tr>
	 	<td>Sno.</td><td>Emp Id</td><td>Emp Name</td><td>Sal</td><td>Address</td>
	 	
	 	<td>Mobile</td>
	 	<th colspan="2">Action</th>
	 	<%int count=1;%>
	 		<c:forEach  var="list" items="${response}">
	 			<tr>
	 			
	 				<td><%=count++%></td>
	 				<td>${list.eid}</td>
	 				<td>${list.name}</td>
	 				<td>${list.sal}</td>
	 				<td>${list.adds}</td>
	 				<td>${list.mobile}</td>
	 				<td><a href="edit?data=${list}">Edit</a></td>
	 				<td><a href="delete?eid=${list.eid}">Delete</a></td>
	 			</tr>
	 		</c:forEach>
	</table>
	
	<br><br>
	<table align="center">
		<tr>
			<td>
				<c:if test="${CPN>1}">
					<a href="view_all?id=${CPN-1}">Previous</a>
				</c:if>
			</td>
				<c:forEach var="i" begin="1" end="${TotalPage}" step="1">
					<c:choose>
						<c:when test="${CPN==i}">
							<td>${i}</td>
						</c:when>
						<c:otherwise>
							<td><a href="view_all?id=${i}">${i}</a></td>
						</c:otherwise>
					</c:choose>
					
				</c:forEach>
			<td>
				<c:if test="${CPN!=TotalPage}">
					<a href="view_all?id=${CPN+1}">Next</a>
				</c:if>
			</td>
		</tr>
	</table>
</body>
</html>