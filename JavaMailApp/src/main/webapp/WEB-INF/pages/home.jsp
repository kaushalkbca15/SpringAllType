<%@ page isELIgnored="false" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2 align="center">Java Mail Apps</h2>
	<hr><br>
	<form method="post">
		<table align="center">
		<tr>
			<td colspan="2">${responce}</td>
		</tr>
		<tr>
			<td>Enter to email id</td>
			<td><input type="text" name="tomail"> </td>
		</tr>
		<tr>
			<td>Enter sub</td>
			<td><input type="text" name="sub"> </td>
		</tr>
		<tr>
			<td>Enter message</td>
			<td><textarea rows="5" cols="20" name="message"></textarea> </td>
		</tr>
		<tr>
			<td align="center"><input type="submit" value="send" > </td>
		</tr>
	</table>
	</form>
</body>
</html>