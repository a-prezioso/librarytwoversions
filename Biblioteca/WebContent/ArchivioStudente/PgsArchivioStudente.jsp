<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="model.session.Studente"%>
<HTML>
<HEAD>
<%@ page 
language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
import="java.util.ArrayList"
%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<TITLE>pgsArchivioStudente.jsp</TITLE>
</HEAD>
<BODY>

<FORM method="post" action="/Biblioteca/CtrlArchivioStudente" name="ArchivioStudente">
<P>&nbsp;&nbsp; <input type="submit" name="cmdAzione" value="Torna alla Home">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<font size="+3">Archivio Studente </font>
</P>
<% ArrayList pgsElenco= (ArrayList) request.getAttribute("elencoStudente");
   int numeroRighe = pgsElenco.size();
   if(numeroRighe > 0){
   		int i = 1;
   
%>
<TABLE border="1" >
<TBODY>
<TR>
<TD> </TD>
<TD>Cognome</TD>   		
<TD>Nome</TD>  
<TD>Matricola</TD>
<TD>Data Nascita</TD>
<TD>Indirizzo</TD>
<TD>Comune</TD>
<TD>Provincia</TD>
<TD>Nazione</TD>
<TD>Telefono</TD>
</TR>
	<TR>
	<TD>
	<input type="radio" name="rdoIDStudente" value="<%=((Studente)pgsElenco.get(0)).getChiave() %>" checked="checked"> </TD> 
	<TD> <%= ((Studente)pgsElenco.get(0)).getCognome() %></TD>
	<TD> <%= ((Studente)pgsElenco.get(0)).getNome() %></TD>
	<TD> <%= ((Studente)pgsElenco.get(0)).getMatricola() %></TD>
	<TD> <%= ((Studente)pgsElenco.get(0)).getDataNascita() %></TD>	
	<TD> <%= ((Studente)pgsElenco.get(0)).getIndirizzo() %></TD>
	<TD> <%= ((Studente)pgsElenco.get(0)).getComune() %></TD>
	<TD> <%= ((Studente)pgsElenco.get(0)).getProvincia() %></TD>
	<TD> <%= ((Studente)pgsElenco.get(0)).getNazione() %></TD>
	<TD> <%= ((Studente)pgsElenco.get(0)).getTelefono() %></TD>
	</TR>

<%while(i< numeroRighe){
	%>
	<TR>
	<TD>
	<input type="radio" name="rdoIDStudente" value="<%= ((Studente)pgsElenco.get(i)).getChiave() %>" > 
	</TD>
	<TD> <%= ((Studente)pgsElenco.get(i)).getCognome() %></TD>
	<TD> <%= ((Studente)pgsElenco.get(i)).getNome() %></TD>	
	<TD> <%= ((Studente)pgsElenco.get(i)).getMatricola() %></TD>
	<TD> <%= ((Studente)pgsElenco.get(0)).getDataNascita() %></TD>	
	<TD> <%= ((Studente)pgsElenco.get(0)).getIndirizzo() %></TD>
	<TD> <%= ((Studente)pgsElenco.get(0)).getComune() %></TD>
	<TD> <%= ((Studente)pgsElenco.get(0)).getProvincia() %></TD>
	<TD> <%= ((Studente)pgsElenco.get(0)).getNazione() %></TD>
	<TD> <%= ((Studente)pgsElenco.get(0)).getTelefono() %></TD>
	</TR>
	<% i++;
	} %>	
</TBODY>
</TABLE>
<P> </P>
<% } else{%> Non ci sono Studenti<% } %> <BR>


<input type="submit" name="cmdAzione" value="Nuovo">
&nbsp;&nbsp;&nbsp;<input type="submit" name="cmdAzione" value="Modifica">
&nbsp;&nbsp;&nbsp;<input type="submit" name="cmdAzione" value="Elimina">

<br>
</Form>

</BODY>
</HTML>
