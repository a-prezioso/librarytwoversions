<%@page import="util.UDate"%>
<%@ page language="java" 
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "model.session.Copia" 
    import = "model.session.Libro"
    import = "model.session.Autore"
    import = "model.session.CasaEditrice"
    import = "model.session.Genere" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>pgsGestioneLibroNuovoModificaCopia.jsp</title>
</head>
<body>
	<jsp:useBean id="beanCopia" scope="session" class="model.session.Copia">
		<jsp:setProperty name="beanCopia" property="*" />
	</jsp:useBean>
	
	<jsp:useBean id="beanLibro" scope="session" class="model.session.Libro">
		<jsp:setProperty name="beanLibro" property="*" />
	</jsp:useBean>
	<h1 align = "center"> Nuova Copia</h1>
	<br>
	<form method = "post" action = "/BibliotecaWebHb/CtrlGestioneLibro" name = "GestioneLibro">
		<input type = "hidden" name = "txtIDLibro" value = "<%= beanLibro.getId()%>">
		<table align = "center">
			<tr>
				<td>Titolo: </td>
				<td> <input type = "text" name = "txtTitoloCopia" value = "<%= beanLibro.getTitolo() %>">
			</tr>
			<tr>
				<td>Autore: </td>
				<td> 
					<input type="text" name="txtAutoreCopia" value="<%= beanLibro.getoAutore().getCognome() + " " + beanLibro.getoAutore().getNome()%>">
				</td>	
			</tr>
			<tr>
				<td>Genere: </td>
				<td> <input type = "text" name = "txtGenereCopia" value = "<%= beanLibro.getoGenere().getNome() %>">
			</tr>
			<tr>
				<td>Casa Editrice: </td>
				<td> <input type = "text" name = "txtCasaEditriceCopia" value = "<%= beanLibro.getoCasaEditrice().getNome()%>">
			</tr>
			<tr>
				<td>Data Acquisto: </td>
				<td><input type = "text" name = "txtDataAcquistoCopia" value = "<%= UDate.inserisciStringa(beanCopia.getDataAcquisto())%>"></td>
			</tr>
			<tr>
				<td>Seriale: </td>
				<td><input type = "text" name = "txtSerialeCopia" value = "<%= beanCopia.getSeriale()%>"></td>
			</tr>	
		</table>
		<br><br>
		<div align = "center">
			<input type = "submit" name = "cmdAzione" value = "Registra Copia">&nbsp;&nbsp;
			<input type = "submit" name = "cmdAzione" value = "Annulla">
		</div>
	</form>
</body>
</html>