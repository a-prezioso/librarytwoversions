<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PgsArchivioStudenteElimina.jsp</title>
</head>
<body>
<jsp:useBean id="beanLibro" scope="session" class="model.session.Libro">
	<jsp:setProperty name="beanLibro" property="*" />
</jsp:useBean>

<P align="center"><FONT size="+2" color="black">Sei sicuro di voler eliminare il libro? </FONT></P>

<FORM method="post" action="/BibliotecaWebHb/CtrlGestioneLibro">

<TABLE  align="center" >

<tr>

	<td>
		<input type=submit name=cmdAzione value="Elimina Libro">
	</td>
	<td>
		<input type=submit name=cmdAzione value="Annulla">
	</td>
	
</tr>

</TABLE>

</FORM>

</body>
</html>