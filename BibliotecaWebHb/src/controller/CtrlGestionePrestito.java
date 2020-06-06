package controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CopiaService;
import model.dao.LibroService;
import model.dao.PrestitoService;
import model.dao.ProfessoreService;
import model.dao.StudenteService;
import model.session.Copia;
import model.session.Libro;
import model.session.Prestito;
import model.session.Professore;
import model.session.Studente;
import util.UDate;

/**
 * Servlet implementation class CtrlGestionePrestito
 */
@WebServlet("/CtrlGestionePrestito")
public class CtrlGestionePrestito extends HttpServlet 
{
	private static PrestitoService oPres = new PrestitoService();
	private static StudenteService oStud = new StudenteService();
	private static ProfessoreService oProf = new ProfessoreService();
	private static LibroService oLib = new LibroService();
	private static CopiaService oCop = new CopiaService();
	private String paginaCorrente;
	private static final long serialVersionUID = 1L;

	public CtrlGestionePrestito() 
	{
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		String azione = request.getParameter("cmdAzione");

		if (azione == null)
			visualizzaElenco(request, response);

		else if (azione.equals("Torna alla Home"))
			getServletContext().getRequestDispatcher("/CtrlMain").forward(request, response);

		else if (azione.equals("Avanti")) 
			visualizzaElenco(request, response);

		else if (azione.equals("Annulla")) 
			visualizzaElenco(request, response);
		
		else if (azione.equals("Nuovo"))
			nuovoPrestito(request, response);

		else if (azione.equals("Procedi"))
			pagRegistraPrestito(request, response);

		else if (azione.equals("Indietro"))
			indietroNuovo(request, response);

		else if (azione.equals("Registra"))
		{
			salvaPrestito(request, response);
			visualizzaElenco(request, response);
		}
		
		else if (azione.equals("Prolunga Prestito"))
			prolungaPrestito(request, response);
		
		else if (azione.equals("Registra Data"))
		{
			registraData(request, response);
			visualizzaElenco(request, response);
		}
		
		else if (azione.equals("Restituzione Copia"))
			restituzioneCopia(request, response);
		
		else if (azione.equals("Registra Restituzione"))
		{
			salvaRestituzione(request, response);
			visualizzaElenco(request, response);
		}
	}

	private void salvaRestituzione(HttpServletRequest request, HttpServletResponse response) 
	{
		try 
		{
			((Prestito) request.getSession().getAttribute("beanPrestito")).setDataRestituzione(UDate.ctrlData(request.getParameter("txtNewDataRestituzione")));
		}
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		((Copia) request.getSession().getAttribute("beanCopia")).setStato("D");
		oPres.update(((Prestito) request.getSession().getAttribute("beanPrestito")));
		oCop.update(((Copia) request.getSession().getAttribute("beanCopia")));
	}

	private void restituzioneCopia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.getSession().setAttribute("beanPrestito", oPres.findById(Integer.parseInt(request.getParameter("rdoIDPrestito"))));
		request.getSession().setAttribute("beanCopia", oCop.findById(Integer.parseInt(request.getParameter("rdoIDCopia"))));
		this.paginaCorrente = "/GestionePrestito/PgsGestionePrestitoRestituzione.jsp";
		pubblicaPagina(request, response);
	}

	private void registraData(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		try 
		{
			((Prestito) request.getSession().getAttribute("beanPrestito")).setDataFine(UDate.ctrlData(request.getParameter("newTxtDataFine")));
		}
		catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		oPres.update(((Prestito) request.getSession().getAttribute("beanPrestito")));
		
//		this.paginaCorrente = "/GestionePrestito/PgsGestionePrestito.jsp";
//		pubblicaPagina(request, response);
	} 

	private void prolungaPrestito(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		request.getSession().setAttribute("beanPrestito", oPres.findById(Integer.parseInt(request.getParameter("rdoIDPrestito"))));
		
		this.paginaCorrente = "/GestionePrestito/PgsGestionePrestitoProlunga.jsp";
		pubblicaPagina(request, response);
	}

	private void indietroNuovo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		this.paginaCorrente = "/GestionePrestito/PgsGestionePrestitoNuovo.jsp";
		pubblicaPagina(request, response);
	}

	private void pagRegistraPrestito(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		String TitoloLibro = request.getParameter("cboTitoloLibro");
		request.setAttribute("TitoloLibro", TitoloLibro);
		
		List<Copia> elencoCopie = oCop.disponibiliCopie((oLib.findByTitolo(TitoloLibro)));
		request.setAttribute("elencoCopie", elencoCopie);

		List<Professore> elencoProfessori = oProf.findAll();
		request.setAttribute("elencoProfessori", elencoProfessori);

		List<Studente> elencoStudenti = oStud.findAll();
		request.setAttribute("elencoStudenti", elencoStudenti);

		this.paginaCorrente = "/GestionePrestito/PgsGestionePrestitoNuovo1.jsp";
		pubblicaPagina(request, response);
	}

	private void salvaPrestito(HttpServletRequest request, HttpServletResponse response) 
	{
		((Prestito) request.getSession().getAttribute("beanPrestito")).setIddestinatario(Integer.parseInt(request.getParameter("cboDestinatario")));
		((Prestito) request.getSession().getAttribute("beanPrestito")).setDestinatario(request.getParameter("txtDest"));
		
		try 
		{
			((Prestito) request.getSession().getAttribute("beanPrestito")).setDataInizio(UDate.ctrlData(request.getParameter("txtDataInizio")));
			((Prestito) request.getSession().getAttribute("beanPrestito")).setDataFine(UDate.ctrlData(request.getParameter("txtDataFine")));
			((Prestito) request.getSession().getAttribute("beanPrestito")).setDataRestituzione(null);
		} 
		catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		((Prestito) request.getSession().getAttribute("beanPrestito")).setoCopia(oCop.findById(Integer.parseInt(request.getParameter("cboCopia"))));
		
		Copia oCopia = oCop.findById(Integer.parseInt(request.getParameter("cboCopia")));
		oCopia.setStato("P");
		
		if (((Prestito) request.getSession().getAttribute("beanPrestito")).getId() == 0) 
		{
			oPres.persist(((Prestito) request.getSession().getAttribute("beanPrestito")));
			oCop.update(oCopia);
		}		
	}

	private void nuovoPrestito(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		List<Libro> elencoLibri = oLib.avBo();
		request.setAttribute("elencoLibri", elencoLibri);

		request.getSession().setAttribute("beanPrestito", new Prestito());

		this.paginaCorrente = "/GestionePrestito/PgsGestionePrestitoNuovo.jsp";
		pubblicaPagina(request, response);
	}

	private void visualizzaElenco(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setAttribute("elencoProfessori", oProf.findAll());
		request.setAttribute("elencoStudenti", oStud.findAll());
		
		List<Prestito> elenco = null;
		String scelta = (request.getSession().getAttribute("scelta").toString());
		
		if (Integer.parseInt(scelta) == 0)
			elenco = oPres.findAll();
		
		else if (Integer.parseInt(scelta) == 1)
		{
			elenco = oPres.elencoPrestitiChiusi();
		} 
		else if (Integer.parseInt(scelta) == 2)
		{
			elenco = oPres.elencoPrestitiAttivi();
		}
		
		request.setAttribute("scelta", scelta);
		request.setAttribute("elencoPrestiti", elenco);
		getServletContext().getRequestDispatcher("/GestionePrestito/PgsGestionePrestito.jsp").forward(request, response);
	}

	private void pubblicaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		getServletContext().getRequestDispatcher(this.paginaCorrente).forward(request, response);
	}
}