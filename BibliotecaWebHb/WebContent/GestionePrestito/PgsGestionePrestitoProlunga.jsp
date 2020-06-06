<%@page import="util.UDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Prolunga Prestito</title>
</head>
<body>
	<jsp:useBean id = "beanPrestito" scope = "session" class = "model.session.Prestito">
		<jsp:setProperty name = "beanPrestito" property = "*" />
	</jsp:useBean>
	<form method = "post" action = "/BibliotecaWebHb/CtrlGestionePrestito">
		<div align = "center">
			<font size="+3">Prestito</font>
		</div>
		<br>
		<table align = "center">
			<tr>
				<td>Data Fine Attuale: </td>
				<td><input type = "text" value = "<%= UDate.inserisciStringa(beanPrestito.getDataFine()) %>" disabled="disabled">
			</tr>
			<tr><td></td></tr>
			<tr>
				<td>Prolungare fino a: </td>
				<td><input type = "text" name = "newTxtDataFine"></td>
			</tr>
		</table>
		
		<br>
		<div align = "center">
			<input type = "submit" name = "cmdAzione" value = "Registra Data">&nbsp;&nbsp;
			<input type = "submit" name = "cmdAzione" value = "Annulla">
		</div>
	</form>
</body>
</html>