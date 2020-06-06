<%@ page language="java" 
	contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    import = "java.util.List"
    import = "model.session.Prestito"
    import = "model.session.Professore"
    import = "model.session.Studente"
    import = "model.session.Libro"
    import = "model.session.Copia" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PgsGestionePrestitoNuovo1.jsp</title>
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
				<td>Titolo</td>
				<td >
					<input type = "text" name = "txtTitolo" value = "<%= request.getAttribute("TitoloLibro") %>">
				</td>
			</tr>
			<tr>
				<td>Copia</td>
				<td> 
					<select name = "cboCopia">
						<% 	List pgsElencoCopie = (List) request.getAttribute("elencoCopie");
						
							if (pgsElencoCopie.size() > 0)
							{
								int i = 0; %>
								<option selected="selected"></option>
							<%	while (i < pgsElencoCopie.size())
								{ %>
									<option value = "<%=((Copia) pgsElencoCopie.get(i)).getIdcopia()%>">
										<%= ((Copia) pgsElencoCopie.get(i)).getSeriale() %>
									</option>
								<%	i++;
								} 
							}
							else
							{ %>
								<option> Non ci sono Copie!</option>
						<%	} %>
					</select>
				</td>
			</tr>
			<!-- -------------------------------- -->
			<tr>
				<td>Destinatario</td>
				<td>
					<select name = "cboDestinatario">
					<%	//String destinatario = ((Prestito) request.getSession().getAttribute("beanPrestito")).getDestinatario();
						String destinatario = request.getParameter("rdoDestinatario");
						
						if (destinatario.equals("Professore"))
						{ 
							List pgsElencoProfessori = (List) request.getAttribute("elencoProfessori");
							int i = 0;
							
							while(i < pgsElencoProfessori.size())
							{ %>
								<option value = "<%= ((Professore) pgsElencoProfessori.get(i)).getId() %>">
									<%= ((Professore) pgsElencoProfessori.get(i)).getCognome() + " " + ((Professore) pgsElencoProfessori.get(i)).getNome() %>
								</option>
							<%	i++;
							}
						}
						else
						{
							List pgsElencoStudenti = (List) request.getAttribute("elencoStudenti");
							int i = 0;
							
							while(i < pgsElencoStudenti.size())
							{ %>
								<option value = "<%= ((Studente) pgsElencoStudenti.get(i)).getId() %>">
									<%= ((Studente) pgsElencoStudenti.get(i)).getCognome() + " " + ((Studente) pgsElencoStudenti.get(i)).getNome() %>
								</option>
							<%	i++;
							} 
						} %>
					</select>
				</td>
			</tr>
			<tr>
				<td>Data Inizio: </td>
				<td><input type = "text" name = "txtDataInizio" value = "<%= request.getParameter("txtDataInizio") %>"> 
				<input type = "hidden" name = "txtDest" value = "<%= request.getParameter("rdoDestinatario") %>" >
			</tr>
			<tr>
				<td>Data Fine: </td>
				<td><input type = "text" name = "txtDataFine" value = "<%= request.getParameter("txtDataFine") %>" >
			</tr>	
		</table>
		<br>
		<div align = "center">
			<input type = "submit" name = "cmdAzione" value = "Registra">&nbsp;&nbsp;
			<input type = "submit" name = "cmdAzione" value = "Indietro">
		</div>
	</form>
</body>
</html>