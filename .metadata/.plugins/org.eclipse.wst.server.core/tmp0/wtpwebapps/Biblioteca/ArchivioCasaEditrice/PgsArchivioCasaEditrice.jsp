<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@page import="model.session.CasaEditrice"%>
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
<TITLE>pgsArchivioCasaEditrice.jsp</TITLE>
</HEAD>
<body>
<FORM method="post" action="/Biblioteca/CtrlArchivioCasaEditrice" name="ArchivioCasaEditrice">
<P>&nbsp;&nbsp; <input type="submit" name="cmdAzione" value="Torna alla Home">
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<font size="+3">Archivio CasaEditrice </font>
</P>
<% ArrayList pgsElenco= (ArrayList) request.getAttribute("elencoCasaEditrice");
   int numeroRighe = pgsElenco.size();
   if(numeroRighe > 0){
   		int i = 1;
   
%>
<TABLE border="1" >
<TBODY>
<TR>
<TD> </TD>  		
<TD>Nome</TD>  
</TR>
	<TR>
	<TD>
	<input type="radio" name="rdoIDCasaEditrice" value="<%=((CasaEditrice)pgsElenco.get(0)).getChiave() %>" checked="checked"> </TD> 
	<TD> <%= ((CasaEditrice)pgsElenco.get(0)).getNome() %></TD>	
	</TR>

<%while(i< numeroRighe){
	%>
	<TR>
	<TD>
	<input type="radio" name="rdoIDCasaEditrice" value="<%= ((CasaEditrice)pgsElenco.get(i)).getChiave() %>" > 
	</TD>
	<TD> <%= ((CasaEditrice)pgsElenco.get(i)).getNome() %></TD>	
	</TR>
	<% i++;
	} %>	
</TBODY>
</TABLE>
<P> </P>
<% } else{%> Non ci sono Case Editrici<% } %> <BR>


<input type="submit" name="cmdAzione" value="Nuovo">
&nbsp;&nbsp;&nbsp;<input type="submit" name="cmdAzione" value="Modifica">
&nbsp;&nbsp;&nbsp;<input type="submit" name="cmdAzione" value="Elimina">

<br>
</Form>

</body>
</html>