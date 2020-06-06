<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="model.session.Autore"%>
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
<TITLE>pgsArchivioAutore.jsp</TITLE>
</HEAD>
<BODY>

<FORM method="post" action="/BibliotecaWebHb/CtrlArchivioAutore" name="ArchivioAutore">
<P>&nbsp;&nbsp; <input type="submit" name="cmdAzione" value="Torna alla Home">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<font size="+3">Archivio Autore </font>
</P>
<% ArrayList pgsElenco= (ArrayList) request.getAttribute("elencoAutore");
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
</TR>
	<TR>
	<TD>
	<input type="radio" name="rdoIDAutore" value="<%=((Autore)pgsElenco.get(0)).getId() %>" checked="checked"> </TD> 
	<TD> <%= ((Autore)pgsElenco.get(0)).getCognome() %></TD>
	<TD> <%= ((Autore)pgsElenco.get(0)).getNome() %></TD>	
	</TR>

<%while(i< numeroRighe){
	%>
	<TR>
	<TD>
	<input type="radio" name="rdoIDAutore" value="<%= ((Autore)pgsElenco.get(i)).getId() %>" > 
	</TD>
	<TD> <%= ((Autore)pgsElenco.get(i)).getCognome() %></TD>
	<TD> <%= ((Autore)pgsElenco.get(i)).getNome() %></TD>	
	</TR>
	<% i++;
	} %>	
</TBODY>
</TABLE>
<P> </P>
<% } else{%> Non ci sono Autori<% } %> <BR>


<input type="submit" name="cmdAzione" value="Nuovo">
&nbsp;&nbsp;&nbsp;<input type="submit" name="cmdAzione" value="Modifica">
&nbsp;&nbsp;&nbsp;<input type="submit" name="cmdAzione" value="Elimina">

<br>
</Form>

</BODY>
</HTML>
