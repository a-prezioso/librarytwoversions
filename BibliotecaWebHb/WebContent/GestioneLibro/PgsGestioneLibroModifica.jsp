<%@page import="util.UDate"%>
<%@ page language="java" 
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "model.session.Libro"
    import = "model.session.Copia"
    import = "model.session.Autore"
    import = "model.session.Genere"
    import = "model.session.CasaEditrice"
    import = "java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>pgsGestioneLibroNuovoModifica.jsp</title>
</head>
<body>
	<jsp:useBean id="beanCopia" scope="session" class="model.session.Copia">
		<jsp:setProperty name="beanCopia" property="*" />
	</jsp:useBean>
	
	<jsp:useBean id="beanLibro" scope="session" class="model.session.Libro">
		<jsp:setProperty name="beanLibro" property="*" />
	</jsp:useBean>
	
	<h1 align = "center"> Modifica Libro</h1>
	<br>
	<form method = "post" action = "/BibliotecaWebHb/CtrlGestioneLibro">
		<table>
			<tr>
				<td>Titolo: </td>
				<td><input type = "text" name = "txtTitolo" value = "<%= beanLibro.getTitolo() %>">
			</tr>
			<tr><td></td></tr><tr><td></td></tr>
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
							{ 
								if (beanLibro.getoAutore().getId() == ((Autore) pgsElencoAutore.get(i)).getId()) 
								{ %>
									<option value = "<%= ((Autore) pgsElencoAutore.get(i)).getId() %>" selected="selected">	
										<%= ((Autore) pgsElencoAutore.get(i)).getCognome() + " " + ((Autore) pgsElencoAutore.get(i)).getNome() %>
									</option>
							<%	}
								else
								{ %>
									<option value = "<%= ((Autore) pgsElencoAutore.get(i)).getId() %>">	
										<%= ((Autore) pgsElencoAutore.get(i)).getCognome() + " " + ((Autore) pgsElencoAutore.get(i)).getNome() %>
									</option>
							<%	}
								
								i++;
							}
						}
						else
						{ %>
							<option>Non ci sono Autori!</option>
					<%  } %>
 						
					</select>
				</td>
			</tr>
			<tr><td></td></tr><tr><td></td></tr>
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
							{ 
								if (beanLibro.getoGenere().getId() == ((Genere) pgsElencoGenere.get(i)).getId()) 
								{ %>
									<option value = "<%= ((Genere) pgsElencoGenere.get(i)).getId() %>" selected="selected">	
										<%= ((Genere) pgsElencoGenere.get(i)).getNome() %>
									</option>
							<%	}
								else
								{ %>
									<option value = "<%= ((Genere) pgsElencoGenere.get(i)).getId() %>">	
										<%= ((Genere) pgsElencoGenere.get(i)).getNome() %>
									</option>
							<%	}
								
								i++;
							}
						}
						else
						{ %>
							<option>Non ci sono Generi!</option>
					<%  } %>
 						
					</select>
				</td>
			</tr>
			<tr><td></td></tr><tr><td></td></tr>
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
							{ 
								if (beanLibro.getoCasaEditrice().getId() == ((CasaEditrice) pgsElencoCasaEditrice.get(i)).getId()) 
								{ %>
									<option value = "<%= ((CasaEditrice) pgsElencoCasaEditrice.get(i)).getId() %>" selected="selected">	
										<%= ((CasaEditrice) pgsElencoCasaEditrice.get(i)).getNome() %>
									</option>
							<%	}
								else
								{ %>
									<option value = "<%= ((CasaEditrice) pgsElencoCasaEditrice.get(i)).getId() %>">	
										<%= ((CasaEditrice) pgsElencoCasaEditrice.get(i)).getNome() %>
									</option>
							<%	}
								
								i++;
							}
						}
						else
						{ %>
							<option>Non ci sono Case Editrici!</option>
					<%  } %>
 						
					</select>
				</td>
			</tr> 
		</table>
		<br><br><br>
		<table border = "1" >
			<tr>
				<td></td>
				<td>Data Acquisto</td>
				<td>Seriale</td>
			</tr>
			
			<%  List pgsElencoCopie = (List) request.getAttribute("elencoCopie"); 
			
				int i = 0;
				
				if (pgsElencoCopie.size() > 0)
				{ 
					while (i < pgsElencoCopie.size())
					{ %>
						<tr>
							<td>
								<input type = "radio" name = "rdoIDCopiaLibro" value = "<%= ((Copia) pgsElencoCopie.get(i)).getIdcopia()%>" >
							</td>
							<td>
								<%= UDate.inserisciStringa(((Copia) pgsElencoCopie.get(i)).getDataAcquisto()) %>				
							</td>
							<td>
								<%= ((Copia) pgsElencoCopie.get(i)).getSeriale() %>				
							</td>
						</tr>
					<%	i++; 
					} 	
				}
				else
				{ %>
					<tr><td colspan = "3">Non ci sono Copie!</td></tr>
			<%	} %>
		</table>
		<br><br><br>
		<input type = "submit" name = "cmdAzione" value = "Nuova Copia">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type = "submit" name = "cmdAzione" value = "Modifica Copia">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type = "submit" name = "cmdAzione" value = "Elimina Copia">
		<br><br>
		<input type = "submit" name = "cmdAzione" value = "Registra">&nbsp;&nbsp;&nbsp;
		<input type = "submit" name = "cmdAzione" value = "Annulla">
	</form>
</body>
</html>