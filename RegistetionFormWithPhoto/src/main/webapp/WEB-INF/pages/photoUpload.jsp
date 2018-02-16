<%@ page isELIgnored="false" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2 align="center"> File uploading</h2>
	<hr><br>
	<form method="post" action="registerWPhoto" enctype="multipart/form-data">
	<h5><a href="home">Home</a> </h5>
		<table align="center">
				<tr>
					<td colspan="2" align="center" style="color: green">${responseMessage}</td>
				</tr>
				<tr>
					<td>Employee Name:</td>
					<td><input type="text" name="ename"></td>
				</tr>
				<tr>
					<td>Sal:</td>
					<td><input type="text" name="sal"></td>
				</tr>
				<tr>
					<td>Address:</td>
					<td><input type="text" name="adds"></td>
				</tr>
				<tr>
					<td>Mobile number:</td>
					<td><input type="text" name="mobile"></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="file" name="fileUpload" size="80">
					</td>
				</tr>
				<tr>
					<td align="center"><input type="submit" value="Register"></td>
					<td><input type="hidden" name="cToken" value="${sToken}"> </td>
				</tr>
		</table>
		
	</form>
</body>
</html>