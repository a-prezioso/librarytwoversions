<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<HTML>
<HEAD>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META name="GENERATOR" content="IBM WebSphere Studio">
<TITLE>pgsMain.jsp</TITLE>
</HEAD>
<BODY>

	<P align="center">
		<FONT size="+2" color="ff0000#">----&nbsp;Elenco&nbsp;Prestiti&nbsp;----</FONT><BR>
	<FORM method="post" action="/BibliotecaWebHb/CtrlMain">
		<DIV align="center">
			<TABLE>
				<TBODY>
					<TR>
						<TD><INPUT type="radio" name="rdoSceltaPrestito" value="0"
							checked="checked"></TD>
						<TD>Tutti</TD>
					</TR>

					<TR>
						<TD><INPUT type="radio" name="rdoSceltaPrestito" value="1"></TD>
						<TD>Chiusi</TD>
					</TR>
					<TR>
						<TD><INPUT type="radio" name="rdoSceltaPrestito" value="2"></TD>
						<TD>Attivi</TD>
					</TR>

				</TBODY>

			</TABLE>
			<BR> <INPUT type="submit" name="cmdInvio" value="Avanti">
		</DIV>
	</form>
	<DIV ALIGN="center">
		<br>
	</div>

</BODY>
</HTML>
