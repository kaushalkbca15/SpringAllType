<%@ page isELIgnored="false" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1 align="center">Update Employee Details</h1>
		<hr><br>
		<form action="update" method="post">
			<table align="center" border="2">
					<tr>
						<td colspan="2" align="center">${updateReponse}</td>
					</tr>
					<tr>
						<td>
							<input type="hidden" value="${responseData.eid}" name="eid">
						</td>
					</tr>
				<tr>
					<td>Emp Id:</td>
					<td><input type="text" value="${responseData.eid}"  disabled="disabled"> </td>
					
				</tr>
				<tr>
					<td>Emp Name</td>
					<td><input type="text" value="${responseData.name}" name="ename"> </td>
				</tr>
				<tr>
					<td>Emp sal:</td>
					<td><input type="text" value="${responseData.sal}" name="sal"> </td>
				</tr>
				<tr>
					<td>Emp adds:</td>
					<td><input type="text" value="${responseData.adds}" name="adds"> </td>
				</tr>
				<tr>
					<td>Emp mobile:</td>
					<td><input type="text" value="${responseData.mobile}" name="mobile"> </td>
				</tr>
				<tr>
					<td align="center" colspan="2"><input type="submit" value="Update">  </td>
				</tr>
				<tr>
					<td><input type="hidden" name="cToken" value="${sToken}"> </td>
				</tr>
			</table>
			
		</form>
</body>
</html>