<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PgsArchivioGenereElimina.jsp</title>
</head>
<body>
<jsp:useBean id="beanCorso" scope="session" class="model.session.Genere">
	<jsp:setProperty name="beanCorso" property="*" />
</jsp:useBean>

<P align="center"><FONT size="+2" color="black">Sei sicuro di voler eliminare il genere? </FONT></P>

<FORM method="post" action="/BibliotecaWebHb/CtrlArchivioGenere">

<TABLE  align="center" >

<tr>

	<td>
		<input type=submit name=cmdAzione value="Elimina Genere">
	</td>
	<td>
		<input type=submit name=cmdAzione value="Annulla">
	</td>
	
</tr>

</TABLE>

</FORM>

</body>
</html>