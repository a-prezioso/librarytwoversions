<%@page import="util.UDate"%>
<%@ page language="java" 
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "model.session.Prestito" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Restituzione Copia</title>
</head>
<body>
	<jsp:useBean id = "beanPrestito" scope = "session" class = "model.session.Prestito">
		<jsp:setProperty name = "beanPrestito" property = "*" />
	</jsp:useBean>
	
		<jsp:useBean id = "beanCopia" scope = "session" class = "model.session.Copia">
		<jsp:setProperty name = "beanCopia" property = "*" />
	</jsp:useBean>
	
	<form method = "post" action = "/BibliotecaWebHb/CtrlGestionePrestito">
		<div align = "center">
			<font size="+3">Prestito</font>
		</div>
		<br>
		<table align = "center">
			<tr>
				<td>Data Restituzione: </td>
				<td><input type = "text" name = "txtNewDataRestituzione"></td>
			</tr>
		</table>
		
		<br>
		<div align = "center">
			<input type = "submit" name = "cmdAzione" value = "Registra Restituzione">&nbsp;&nbsp;
			<input type = "submit" name = "cmdAzione" value = "Annulla">
		</div>
	</form>
</body>
</html>