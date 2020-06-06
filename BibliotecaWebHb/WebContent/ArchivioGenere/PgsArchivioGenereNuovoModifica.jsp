<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PgsArchivioGenereNuovoModifica</title>
</head>
<body>
<jsp:useBean id="beanGenere" scope="session"
	class="model.session.Genere">
	<jsp:setProperty name="beanGenere" property="*" />
</jsp:useBean>

<P align="center"><FONT size="+2" color="green">Archivio Genere</FONT></P>

<FORM method="post" action="/BibliotecaWebHb/CtrlArchivioGenere">

<TABLE  align="center" >


<tr>

	<td>
		Nome: 
	</td>
	
	<td>
		 
	<INPUT type="text" name="txtNome" value="<%= beanGenere.getNome()%>" size="20" maxlength="50">
	
	</td>
	
</tr>
</TABLE >
<BR/> 
<BR/>
<DIV align="center">

<INPUT type="submit" name="cmdAzione" value="Registra"> &nbsp;&nbsp;
<INPUT type="submit" name="cmdAzione" value="Annulla"> <BR>

</DIV></FORM>


</body>
</html>