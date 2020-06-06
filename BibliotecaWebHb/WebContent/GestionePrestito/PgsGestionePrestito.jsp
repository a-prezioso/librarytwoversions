<%@page import="util.UDate"%>
<%@ page language="java" 
	contentType="text/html; charset=ISO-8859-1"	
    pageEncoding="ISO-8859-1"
    import="java.util.List"
    import = "model.session.Prestito"
    import = "model.session.Professore"
    import = "model.session.Studente" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>pgsGestionePrestito.jsp</title>
</head>
<body>
	<jsp:useBean id = "beanPrestito" scope = "session" class = "model.session.Prestito">
		<jsp:setProperty name = "beanPrestito" property = "*" />
	</jsp:useBean>
	
	<form method = "post" action = "/BibliotecaWebHb/CtrlGestionePrestito" name = "GestionePrestito">
		<p>&nbsp;&nbsp; <input type="submit" name="cmdAzione" value="Torna alla Home">
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</p>
		<font size="+3">Gestione Prestiti</font>
		<br><br>
		<%  List<Prestito> pgsElenco = (List) request.getAttribute("elencoPrestiti");
			int i = 0;	
			
			if (pgsElenco.size() > 0)
			{ %> 
				<table border = "1">
					<tr>
						<td></td>
						<td>Titolo</td>
						<td>Destinatario</td>
						<td>Nome</td>
						<td>Data Inizio</td>
						<td>Data Fine</td>
						<td>Data Restituzione</td>
						<td>Seriale</td>
					</tr>
		<%	while (i < pgsElenco.size())
			{ %>
				<tr>
					<td>
						<input type="radio" name="rdoIDPrestito" value="<%=((Prestito) pgsElenco.get(i)).getId() %>" checked="checked"> 
						<input type="hidden" name="rdoIDCopia" value="<%= ((Prestito) pgsElenco.get(i)).getoCopia().getIdcopia() %>">
				
					</td>
					<td> <%= ((Prestito) pgsElenco.get(i)).getoCopia().getoCopiaLibro().getTitolo() %> </td>
					<td> <%= ((Prestito) pgsElenco.get(i)).getDestinatario() %> </td>
						<% if(((Prestito) pgsElenco.get(i)).getDestinatario().equals("Professore")) 
								 	{
        								List<Professore> pgsElencoProf = (List) request.getAttribute("elencoProfessori");
        								int j = 0;
       
       								while(j < pgsElencoProf.size() && ((Prestito) pgsElenco.get(i)).getIddestinatario() != ((Professore) pgsElencoProf.get(j)).getId()) 
       								{ 
        									j++; 
        								} 
      								
        								if(((Prestito) pgsElenco.get(i)).getIddestinatario() == ((Professore) pgsElencoProf.get(j)).getId()) 
					          	{ %>
					        		<td><%= ((Professore) pgsElencoProf.get(j)).getCognome() + " " + ((Professore) pgsElencoProf.get(j)).getNome()%> </td>
					      	<%  }
					        }               
					        else 
					       	{
					        	if(((Prestito) pgsElenco.get(i)).getDestinatario().equals("Studente")) 
 								 	{
         								List<Studente> pgsElencoStud = (List) request.getAttribute("elencoStudenti");
         								int j = 0;
        
        								while(j < pgsElencoStud.size() && ((Prestito) pgsElenco.get(i)).getIddestinatario() != ((Studente) pgsElencoStud.get(j)).getId()) 
        								{ 
         									j++; 
         								} 
       								
         								if(((Prestito) pgsElenco.get(i)).getIddestinatario() == ((Studente) pgsElencoStud.get(j)).getId()) 
						          	{ %>
						        		<td><%= ((Studente) pgsElencoStud.get(j)).getCognome() + " " + ((Studente) pgsElencoStud.get(j)).getNome()%> </td>
						      	<%  }
						        }
					       	} %> 
					<td> <%= UDate.inserisciStringa(((Prestito) pgsElenco.get(i)).getDataInizio()) %> </td>
					<td> <%= UDate.inserisciStringa(((Prestito) pgsElenco.get(i)).getDataFine()) %> </td>
					<td> <%= UDate.inserisciStringa(((Prestito) pgsElenco.get(i)).getDataRestituzione()) %></td>
						
					<td> <%= ((Prestito) pgsElenco.get(i)).getoCopia().getSeriale() %> </td> <!-- Seriale Copia ==> getListaCopie().get(i).getSeriale() -->
				</tr>
		<%		i++;	
			} %>
			</table>
		<% 	} 
			else
			{ %>
				Non sono presenti prestiti! 
		<% 	}  %>
		<br><br>
		<input type = "submit" name = "cmdAzione" value = "Nuovo">
<%  if (Integer.parseInt((String) request.getAttribute("scelta")) == 2)
   { %>
    &nbsp;&nbsp;&nbsp;<input type = "submit" name = "cmdAzione" value = "Prolunga Prestito">
    &nbsp;&nbsp;&nbsp;<input type = "submit" name = "cmdAzione" value = "Restituzione Copia">
  <% } %>
		<br>
	</form>
</body>
</html>