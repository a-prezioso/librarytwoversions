<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="model.session.Professore"%>
<HTML>
<HEAD>
<%@ page 
language="java"
contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"
import="java.util.List"
%>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<TITLE>pgsArchivioProfessore.jsp</TITLE>
</HEAD>
<BODY>

<FORM method="post" action="/BibliotecaWebHb/CtrlArchivioProfessore" name="ArchivioProfessore">
<P>&nbsp;&nbsp; <input type="submit" name="cmdAzione" value="Torna alla Home">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<font size="+3">Archivio Professore </font>
</P>
<% List pgsElenco= (List) request.getAttribute("elencoProfessore");
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
</TR>
	<TR>
	<TD>
	<input type="radio" name="rdoIDProfessore" value="<%=((Professore)pgsElenco.get(0)).getId() %>" checked="checked"> </TD> 
	<TD> <%= ((Professore)pgsElenco.get(0)).getCognome() %></TD>
	<TD> <%= ((Professore)pgsElenco.get(0)).getNome() %></TD>
		<TD> <%= ((Professore)pgsElenco.get(0)).getMatricola() %></TD>	
	</TR>

<%while(i< numeroRighe){
	%>
	<TR>
	<TD>
	<input type="radio" name="rdoIDProfessore" value="<%= ((Professore)pgsElenco.get(i)).getId() %>" > 
	</TD>
	<TD> <%= ((Professore)pgsElenco.get(i)).getCognome() %></TD>
	<TD> <%= ((Professore)pgsElenco.get(i)).getNome() %></TD>	
	<TD> <%= ((Professore)pgsElenco.get(i)).getMatricola() %></TD>
	</TR>
	<% i++;
	} %>	
</TBODY>
</TABLE>
<P> </P>
<% } else{%> Non ci sono Professori<% } %> <BR>


<input type="submit" name="cmdAzione" value="Nuovo">
&nbsp;&nbsp;&nbsp;<input type="submit" name="cmdAzione" value="Modifica">
&nbsp;&nbsp;&nbsp;<input type="submit" name="cmdAzione" value="Elimina">

<br>
</Form>

</BODY>
</HTML>
