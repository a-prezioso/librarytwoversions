<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>PgsArchivioStudenteNuovoModifica.jsp</title>
</head>
<body>
<jsp:useBean id="beanStudente" scope="session"
	class="model.session.Studente">
	<jsp:setProperty name="beanStudente" property="*" />
</jsp:useBean>

<P align="center"><FONT size="+2" color="green">Archivio Studente</FONT></P>

<FORM method="post" action="/Biblioteca/CtrlArchivioStudente">

<TABLE  align="center" >
<tr>

	<td>
	 	Cognome:	 
	</td>
	
	<td>
		<INPUT type="text" name="txtCognome" value="<%= beanStudente.getCognome()%>" size="20" maxlength="50">
	</td>
	
</tr>	

<tr>

	<td>
	&nbsp;
	</td>
	
</tr>

<tr>

	<td>
		Nome: 
	</td>
	
	<td>
		 
	<INPUT type="text" name="txtNome" value="<%= beanStudente.getNome()%>" size="20" maxlength="50">
	
	</td>
	
</tr>

<tr>

	<td>
		Matricola: 
	</td>
	
	<td>
		 
	<INPUT type="text" name="txtMatricola" value="<%= beanStudente.getMatricola()%>" size="20" maxlength="50">
	
	</td>
	
</tr>

<tr>

	<td>
		Data Nascita: 
	</td>
	
	<td>
		 
	<INPUT type="text" name="txtDataNascita" value="<%= beanStudente.getDataNascita()%>" size="20" maxlength="50">
	
	</td>
	
</tr>


<tr>

	<td>
		Indirizzo: 
	</td>
	
	<td>
		 
	<INPUT type="text" name="txtIndirizzo" value="<%= beanStudente.getIndirizzo()%>" size="20" maxlength="50">
	
	</td>
	
</tr>


<tr>

	<td>
		Comune: 
	</td>
	
	<td>
		 
	<INPUT type="text" name="txtComune" value="<%= beanStudente.getComune()%>" size="20" maxlength="50">
	
	</td>
	
</tr>

<tr>

	<td>
		Provincia: 
	</td>
	
	<td>
		 
	<INPUT type="text" name="txtProvincia" value="<%= beanStudente.getProvincia()%>" size="20" maxlength="50">
	
	</td>
	
</tr>

<tr>

	<td>
		Nazione: 
	</td>
	
	<td>
		 
	<INPUT type="text" name="txtNazione" value="<%= beanStudente.getNazione()%>" size="20" maxlength="50">
	
	</td>
	
</tr>

<tr>

	<td>
		Telefono: 
	</td>
	
	<td>
		 
	<INPUT type="text" name="txtTelefono" value="<%= beanStudente.getTelefono()%>" size="20" maxlength="50">
	
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