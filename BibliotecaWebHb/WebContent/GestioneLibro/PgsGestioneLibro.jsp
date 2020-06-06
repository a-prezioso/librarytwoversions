<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<%@ page 
	language="java"
	contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.List"
	import="model.session.Libro" %>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="GENERATOR" content="IBM WebSphere Studio">
	<title>pgsGestioneLibro.jsp</title>
</head>
<body>
	<form method = "post" action = "/BibliotecaWebHb/CtrlGestioneLibro" name = "GestioneLibro">
		<p>&nbsp;&nbsp; <input type="submit" name="cmdAzione" value="Torna alla Home">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<font size="+3">Gestione Libro</font>
		</p>
		<% 	List pgsElenco = (List) request.getAttribute("elencoLibri");
			int i = 0;
			
			if (pgsElenco.size() > 0)
			{ %>
				<table border = "1">
					<tr>
						<td></td>
						<td>Titolo</td>
						<td>Autore</td>
						<td>Genere</td>
						<td>Casa Editrice</td>
						<td>N Copie</td>
					</tr>
				<%	while (i < pgsElenco.size())
					{ %>
						<tr>
							<td><input type = "radio" name = "rdoIDLibro" value = "<%= ((Libro) pgsElenco.get(i)).getId() %>" checked="checked">
							<td> <%= ((Libro) pgsElenco.get(i)).getTitolo() %></td>
							<td> <%= ((Libro) pgsElenco.get(i)).getoAutore().getCognome() + " " + ((Libro) pgsElenco.get(i)).getoAutore().getNome() %></td>
							<td> <%= ((Libro) pgsElenco.get(i)).getoGenere().getNome() %> </td>
							<td> <%= ((Libro) pgsElenco.get(i)).getoCasaEditrice().getNome() %></td>
							<td> <%= ((Libro) pgsElenco.get(i)).getListaCopie().size()%></td> 					
						</tr>
				<%		i++;
					} %>
				</table>
		<%	}
			else
			{ %>
				<font>Non ci sono Libri! </font>
		<% 	} %>	
			<br><br>
			<input type = "submit" name = "cmdAzione" value = "Nuovo Libro"> &nbsp;&nbsp;
			<input type = "submit" name = "cmdAzione" value = "Nuova Copia"><br><br>
			<input type = "submit" name = "cmdAzione" value = "Modifica"> &nbsp;
			<input type = "submit" name = "cmdAzione" value = "Elimina">
	</form>
</body>
</html>