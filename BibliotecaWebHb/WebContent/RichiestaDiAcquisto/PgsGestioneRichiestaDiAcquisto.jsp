<%@ page language="java" 
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "java.util.List"
    import = "model.session.RichiestaAcquisto"
    import = "model.session.Libro" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>pgsGestioneRichiestaDiAcquisto.jsp</title>
</head>
<body>
	<jsp:useBean id = "beanRichiesta" scope = "session" class = "model.session.RichiestaAcquisto">
		<jsp:setProperty name = "beanRichiesta" property = "*" />
	</jsp:useBean>
	
	<form method = "post" action = "/Biblioteca/CtrlGestioneRichiestaDiAcquisto" name = "GestioneRichiestaDiAcquisto">
		<p>&nbsp;&nbsp; <input type="submit" name="cmdAzione" value="Torna alla Home">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</p>
		<font size="+3">Gestione Richiesta Di Acquisto</font>
		<br><br>
		<%  List<RichiestaAcquisto> pgsElenco = (List) request.getAttribute("elencoRichieste");
			int i = 0;	
			
			if (pgsElenco.size() > 0)
			{ %>
				<table border = "1">
					<tr>
						<td></td>
						<td>Stato</td>
						<td>Titolo</td>
						<td>Autore</td>
						<td>Professore</td>
						<td>Genere</td>
					</tr>
				<%	while(i < pgsElenco.size())
					{ %>
						<tr>
							<td>
								<input type="radio" name="rdoIDPrestito" value="<%= ((RichiestaAcquisto) pgsElenco.get(i)).getId() %>" checked="checked">
							</td>
							<td> <%= ((RichiestaAcquisto) pgsElenco.get(i)).getStato() %></td>
							<td> <%= ((RichiestaAcquisto) pgsElenco.get(i)).getoLibro().getTitolo() %></td>
							<td> <%= ((RichiestaAcquisto) pgsElenco.get(i)).getoLibro().getoAutore().getCognome() 
							+ " " + ((RichiestaAcquisto) pgsElenco.get(i)).getoLibro().getoAutore().getNome() %></td>
							<td> <%= ((RichiestaAcquisto) pgsElenco.get(i)).getoProfessore().getCognome() 
							+ " " + ((RichiestaAcquisto) pgsElenco.get(i)).getoProfessore().getNome() %> </td>
							<td> <%= ((RichiestaAcquisto) pgsElenco.get(i)).getoLibro().getoGenere().getNome() %> </td>
						</tr>
				<%		i++;
					} %>
				</table>
		<%	}
			else
			{ %>
				Non ci sono Richieste Di Acquisto!
		<%	} %>		
			<br><br>
		<% 	if (Integer.parseInt((String) request.getAttribute("sceltaRichiesta")) == 0)
			{ %>
				<input type = "submit" name = "cmdAzione" value = "Nuovo">
				<input type = "submit" name = "cmdAzione" value = "Modifica">
				<input type = "submit" name = "cmdAzione" value = "Elimina">
				<input type = "submit" name = "cmdAzione" value = "Annulla">
		<%	}
			else
			{ %>
				<input type = "submit" name = "cmdAzione" value = "Chiusura Con Acquisto">
				<input type = "submit" name = "cmdAzione" value = "Chiusura Senza Acquisto">
				<input type = "submit" name = "cmdAzione" value = "Annulla">	
		<%	} %>
	</form>
</body>
</html>