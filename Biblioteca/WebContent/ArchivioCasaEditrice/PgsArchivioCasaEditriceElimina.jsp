<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PgsArchivioCasaEditriceElimina.jsp</title>
</head>
<body>
<jsp:useBean id="beanCorso" scope="session" class="model.session.CasaEditrice">
	<jsp:setProperty name="beanCorso" property="*" />
</jsp:useBean>

<P align="center"><FONT size="+2" color="black">Sei sicuro di voler eliminare il CasaEditrice? </FONT></P>

<FORM method="post" action="/Biblioteca/CtrlArchivioCasaEditrice">

<TABLE  align="center" >

<tr>

	<td>
		<input type=submit name=cmdAzione value="Elimina CasaEditrice">
	</td>
	<td>
		<input type=submit name=cmdAzione value="Annulla">
	</td>
	
</tr>

</TABLE>

</FORM>

</body>
</html>