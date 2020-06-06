<%@ page language="java" 
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "java.util.List"
    import = "model.session.Libro"
    import = "model.session.Prestito"
    import = "model.session.Copia" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>pgsGestionePrestitoNuovo.jsp</title>
</head>
<body>
	<jsp:useBean id="beanPrestito" scope="session" class="model.session.Prestito">
		<jsp:setProperty name="beanPrestito" property="*" />
	</jsp:useBean>
	
	<jsp:useBean id="beanLibro" scope="session" class="model.session.Libro">
		<jsp:setProperty name="beanLibro" property="*" />
	</jsp:useBean>
	
	<h1 align = "center"> Nuovo Prestito</h1>
	<br>
	<form method = "post" action = "/BibliotecaWebHb/CtrlGestionePrestito">
		<table align = "center">
			<tr>
				<td>Titolo:</td>
				<td>
					<select name = "cboTitoloLibro" style = "width: 176px;">
						<% 	List pgselencoLibro = (List) request.getAttribute("elencoLibri"); 
						if (pgselencoLibro.size() > 0)
						{ 
							int i = 0; %>
							<option value = "0" selected="selected"></option>
						<% 	while (i < pgselencoLibro.size())
							{ %>
								<option>
									<%= ((Libro) pgselencoLibro.get(i)).getTitolo() %>
								</option>
							<%	i++;
							} 
					 	}
						else
						{ %> 
							<option>Non ci sono Libri! </option>
					<%	} %>
					</select>
				</td>
			</tr>
			<tr>
				<td>Data Inizio: </td>
				<td><input type = "text" name = "txtDataInizio" > <!-- value = "<%= beanPrestito.getDataInizio() %>" -->
			</tr>
			<tr>
				<td>Data Fine: </td>
				<td><input type = "text" name = "txtDataFine" ><!-- value = "<%= beanPrestito.getDataFine() %>" -->
			</tr>
		</table>
		<br><br>
		<table border = "1" align = "center" width = "300px" height = "150px">
			<tr>
				<td align = "center">Destinatario</td>
			</tr>
			<tr>
				<td align = "center">
					<input type = "radio" name = "rdoDestinatario" value = "Professore">Professore &nbsp; 
					<input type = "radio" name = "rdoDestinatario" value = "Studente">Studente
				</td>
			</tr>
		</table>
		<br><br>
		<div align = "center">
			<input type = "submit" name = "cmdAzione" value = "Procedi">&nbsp;&nbsp;
			<input type = "submit" name = "cmdAzione" value = "Annulla">
		</div>
	</form>
</body>
</html>