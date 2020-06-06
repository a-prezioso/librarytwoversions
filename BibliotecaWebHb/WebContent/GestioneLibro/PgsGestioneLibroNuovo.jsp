<%@ page language="java" 
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "model.session.Libro"
    import = "model.session.Autore"
    import = "model.session.Genere"
    import = "model.session.CasaEditrice"
    import = "java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>pgsGestioneLibroNuovo.jsp</title>
</head>
<body>
	<jsp:useBean id="beanLibro" scope="session" class="model.session.Libro">
		<jsp:setProperty name="beanLibro" property="*" />
	</jsp:useBean>
	
	<h1 align = "center"> Nuovo Libro</h1>
	<br>
	<div align = "center" >
	<form method = "post" action = "/BibliotecaWebHb/CtrlGestioneLibro">
		<table>
			<tr>
				<td>Titolo: </td>
				<td><input type = "text" name = "txtTitolo" value = "<%= beanLibro.getTitolo() %>"> <!-- value = "<%= beanLibro.getTitolo() %>" -->
			</tr>
			<tr>
				<td>Autore: </td>
				<td>
					<select name = "cboAutore" style = "width:175px">
					<% 	List pgsElencoAutore = (List) request.getAttribute("elencoAutori");
					
						if (pgsElencoAutore.size() > 0)
						{
							int i = 0; %>
							<option value = "0" selected="selected"></option>
						<% 	while (i < pgsElencoAutore.size())
							{ %>
								<option value = "<%= ((Autore) pgsElencoAutore.get(i)).getId() %>">	
									<%= ((Autore) pgsElencoAutore.get(i)).getCognome() + " " + ((Autore) pgsElencoAutore.get(i)).getNome() %>
								</option>
						<%		i++;
							}
						}
						else
						{ %>
							<option>Non ci sono Autori!</option>
					<%  } %>
 						
					</select>
				</td>
			</tr>
			<tr>
				<td>Genere: </td>
				<td>
					<select name = "cboGenere" style = "width:175px">
					<% 	List pgsElencoGenere = (List) request.getAttribute("elencoGeneri");
						if (pgsElencoGenere.size() > 0)
						{
							int i = 0; %>
							<option value = "0" selected="selected"></option>
						<% 	while (i < pgsElencoGenere.size())
							{ %>
								<option value = "<%= ((Genere) pgsElencoGenere.get(i)).getId() %>">	
									<%= ((Genere) pgsElencoGenere.get(i)).getNome() %>
								</option>
						<%		i++;
							}
						}
						else
						{ %>
							<option>Non ci sono Generi!</option>
					<%  } %>
 						
					</select>
				</td>
			</tr>
			<tr>
				<td>Casa Editrice: </td>
				<td>
					<select name = "cboCasaEditrice" style = "width:175px">
					<% 	List pgsElencoCasaEditrice = (List) request.getAttribute("elencoCaseEditrici");
						if (pgsElencoCasaEditrice.size() > 0)
						{
							int i = 0; %>
							<option value = "0" selected="selected"></option>
						<% 	while (i < pgsElencoCasaEditrice.size())
							{ %>
								<option value = "<%= ((CasaEditrice) pgsElencoCasaEditrice.get(i)).getId() %>">	
									<%= ((CasaEditrice) pgsElencoCasaEditrice.get(i)).getNome() %>
								</option>
						<%		i++;
							}
						}
						else
						{ %>
							<option>Non ci sono Case Editrici!</option>
					<%  } %>
 						
					</select>
				</td>
			</tr> 
			<tr>
				<td>Data di Acquisto: </td>
				<td><input type = "text" name = "txtDataAcquisto" disabled="disabled"></td>
			</tr>
			<tr>
				<td>Codice Seriale: </td>
				<td><input type = "text" name = "txtSeriale" disabled="disabled"></td>
			</tr>
		</table>
		<br><br>
		<input type = "submit" name = "cmdAzione" value = "Registra">&nbsp;&nbsp;
		<input type = "submit" name = "cmdAzione" value = "Annulla">
	</form>
	</div>
</body>
</html>