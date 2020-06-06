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
		<FONT size="+2" color="ff0000#">----&nbsp;Biblioteca&nbsp;----</FONT><BR>
	<FORM method="post" action="/Biblioteca/CtrlMain">
		<DIV align="center">
			<TABLE>
				<TBODY>
					<TR>
						<TD><INPUT type="radio" name="rdoScelta" value="0"
							checked="checked"></TD>
						<TD>Archivio&nbsp;Genere</TD>
					</TR>

					<TR>
						<TD><INPUT type="radio" name="rdoScelta" value="1"></TD>
						<TD>Archivio&nbsp;Casa&nbsp;Editrice</TD>
					</TR>
					<TR>
						<TD><INPUT type="radio" name="rdoScelta" value="2"></TD>
						<TD>Archivio&nbsp;Autore</TD>
					</TR>
					<TR>
						<TD><INPUT type="radio" name="rdoScelta" value="3"></TD>
						<TD>Archivio&nbsp;Studente</TD>
					</TR>
					<TR>
						<TD><INPUT type="radio" name="rdoScelta" value="4"></TD>
						<TD>Archivio&nbsp;Professore</TD>
					</TR>
					<TR>
						<TD><INPUT type="radio" name="rdoScelta" value="4"></TD>
						<TD>Gestione&nbsp;Libro</TD>
					</TR>
					<TR>
						<TD><INPUT type="radio" name="rdoScelta" value="4"></TD>
						<TD>Gestione&nbsp;Prestito</TD>
					</TR>
					<TR>
						<TD><INPUT type="radio" name="rdoScelta" value="4"></TD>
						<TD>Prenotazione</TD>
					</TR>
					<TR>
						<TD><INPUT type="radio" name="rdoScelta" value="4"></TD>
						<TD>Richiesta&nbsp;di&nbsp;Acquisto</TD>
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
