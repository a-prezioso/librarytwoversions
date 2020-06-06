package controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.AutoreService;
import model.dao.CasaEditriceService;
import model.dao.CopiaService;
import model.dao.GenereService;
import model.dao.LibroService;
import model.session.Autore;
import model.session.CasaEditrice;
import model.session.Copia;
import model.session.Genere;
import model.session.Libro;
import model.session.Studente;
import util.UDate;

/**
 * Servlet implementation class CtrlGestioneLibro
 */
@WebServlet("/CtrlGestioneLibro")
public class CtrlGestioneLibro extends HttpServlet {
	private static LibroService Lib = new LibroService();
	private static CopiaService Cop = new CopiaService();
	private static AutoreService Aut = new AutoreService();
	private static GenereService Gen = new GenereService();
	private static CasaEditriceService CasaEd = new CasaEditriceService();

	private String paginaCorrente;

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CtrlGestioneLibro() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String azione = request.getParameter("cmdAzione");

		if (azione == null)
			visualizzaMenu(request, response);

		else if (azione.equals("Torna alla Home"))
			getServletContext().getRequestDispatcher("/CtrlMain").forward(request, response);

		else if (azione.equals("Nuovo Libro"))
			nuovoLibro(request, response);

		else if (azione.equals("Annulla"))
			visualizzaMenu(request, response);

		else if (azione.equals("Registra")) {
			salvaLibro(request, response);
			visualizzaMenu(request, response);
		}

		else if (azione.equals("Nuova Copia"))
			nuovomodificaCopia(request, response);

		else if (azione.equals("Registra Copia")) {
			salvaCopia(request, response);
			visualizzaMenu(request, response);
		}

		else if (azione.equals("Modifica"))
			modificaLibro(request, response);

		else if (azione.equals("Modifica Copia"))
			nuovomodificaCopia(request, response);
		else if (azione.equals("Elimina"))
			eliminaLibro(request, response);
		else if (azione.equals("Elimina Libro")) {
			cancellaLibro(request, response);
			visualizzaMenu(request, response);
		}
		else if(azione.equals("Elimina Copia")) {
			eliminaCopia(request, response);
		}

	}

	private void eliminaCopia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		  Cop.delete(Cop.findById(Integer.parseInt(request.getParameter("rdoIDCopiaLibro"))));
		  
		  request.getSession().setAttribute("beanLibro", Lib.findById(((Libro) request.getSession().getAttribute("beanLibro")).getId()));
		  
		    List<Autore> elencoAutori = Aut.findAll();
			request.setAttribute("elencoAutori", elencoAutori);

			List<Genere> elencoGeneri = Gen.findAll();
			request.setAttribute("elencoGeneri", elencoGeneri);

			List<CasaEditrice> elencoCaseEditrici = CasaEd.findAll();
			request.setAttribute("elencoCaseEditrici", elencoCaseEditrici);

			List<Copia> elencoCopie = ((Libro) request.getSession().getAttribute("beanLibro")).getListaCopie();
			request.setAttribute("elencoCopie", elencoCopie);
			


		  pubblicaPagina(request, response);
	}

	private void cancellaLibro(HttpServletRequest request, HttpServletResponse response) {
		Lib.delete(((Libro) request.getSession().getAttribute("beanLibro")).getId());
	}

	private void eliminaLibro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Libro oLibro = Lib.findById(Integer.parseInt(request.getParameter("rdoIDLibro")));
		request.getSession().setAttribute("beanLibro", oLibro);
		getServletContext().getRequestDispatcher("/GestioneLibro/PgsGestioneLibroElimina.jsp").forward(request,
				response);
	}

	private void modificaLibro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getSession().setAttribute("beanLibro",
				Lib.findById(Integer.parseInt(request.getParameter("rdoIDLibro"))));

		List<Autore> elencoAutori = Aut.findAll();
		request.setAttribute("elencoAutori", elencoAutori);

		List<Genere> elencoGeneri = Gen.findAll();
		request.setAttribute("elencoGeneri", elencoGeneri);

		List<CasaEditrice> elencoCaseEditrici = CasaEd.findAll();
		request.setAttribute("elencoCaseEditrici", elencoCaseEditrici);

		List<Copia> elencoCopie = ((Libro) request.getSession().getAttribute("beanLibro")).getListaCopie();
		request.setAttribute("elencoCopie", elencoCopie);
		
//		List<Copia> copieDaEliminare = ((Copia) request.getSession().getAttribute("beanCopia")).getCopiedaeliminare();
//		request.setAttribute("copiedaeliminare", copieDaEliminare);

		if(request.getParameter("cmdAzione").equals("Elimina Copia")) {
			
		}
		else {
		this.paginaCorrente = "/GestioneLibro/PgsGestioneLibroModifica.jsp";
		pubblicaPagina(request, response); }
	}

	private void salvaCopia(HttpServletRequest request, HttpServletResponse response) {
		try {
			((Copia) request.getSession().getAttribute("beanCopia"))
					.setDataAcquisto(UDate.ctrlData(request.getParameter("txtDataAcquistoCopia")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		((Copia) request.getSession().getAttribute("beanCopia"))
				.setoCopiaLibro(Lib.findById(Integer.parseInt(request.getParameter("txtIDLibro"))));
		((Copia) request.getSession().getAttribute("beanCopia")).setSeriale(request.getParameter("txtSerialeCopia"));

		((Copia) request.getSession().getAttribute("beanCopia"))
		.setStato("D");
		
		if (((Copia) request.getSession().getAttribute("beanCopia")).getIdcopia() == 0)
			Cop.persist(((Copia) request.getSession().getAttribute("beanCopia")));
		else
			Cop.update(((Copia) request.getSession().getAttribute("beanCopia")));
	}

	private void nuovomodificaCopia(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Copia oCopia;
		if (request.getParameter("rdoIDLibro") == null) {
			if (request.getParameter("rdoIDCopiaLibro") == null) {
				oCopia = new Copia((Libro) request.getSession().getAttribute("beanLibro"));
				request.getSession().setAttribute("beanCopia", oCopia);
			} else {
				oCopia = new Copia((Libro) request.getSession().getAttribute("beanLibro"));
				request.getSession().setAttribute("beanCopia",
						Cop.findById(Integer.parseInt(request.getParameter("rdoIDCopiaLibro"))));
			}
		}
		else {
			request.getSession().setAttribute("beanLibro",
					Lib.findById(Integer.parseInt(request.getParameter("rdoIDLibro"))));
			oCopia = new Copia(((Libro) request.getSession().getAttribute("beanLibro")));
			request.getSession().setAttribute("beanCopia", oCopia);
		}

		this.paginaCorrente = "/GestioneLibro/PgsGestioneLibroNuovoModificaCopia.jsp";
		pubblicaPagina(request, response);
	}

	private void salvaLibro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		((Libro) request.getSession().getAttribute("beanLibro"))
				.setTitolo(request.getParameter("txtTitolo"));
		((Libro) request.getSession().getAttribute("beanLibro"))
				.setoAutore(Aut.findById(Integer.parseInt((request.getParameter("cboAutore")))));
		((Libro) request.getSession().getAttribute("beanLibro"))
				.setoGenere(Gen.findById(Integer.parseInt((request.getParameter("cboGenere")))));
		((Libro) request.getSession().getAttribute("beanLibro"))
				.setoCasaEditrice(CasaEd.findById(Integer.parseInt((request.getParameter("cboCasaEditrice")))));
		
//		List<Copia> elencoCopie =  ((Copia) request.getSession().getAttribute("beanCopia")).getCopiedaeliminare();
//		int i = 0;
//		while(i < elencoCopie.size()) {
//			Cop.delete(elencoCopie.get(i));
//			i++;
//		}
//		((Libro) request.getSession().getAttribute("beanLibro")).setListaCopie(elencoCopie);
		
		if (((Libro) request.getSession().getAttribute("beanLibro")).getId() == 0)
			Lib.persist(((Libro) request.getSession().getAttribute("beanLibro")));
		else
			Lib.update(((Libro) request.getSession().getAttribute("beanLibro")));
	}

	private void nuovoLibro(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Libro oLibro = new Libro();
		request.getSession().setAttribute("beanLibro", oLibro);

		List<Autore> elencoAutori = Aut.findAll();
		request.setAttribute("elencoAutori", elencoAutori);

		List<Genere> elencoGeneri = Gen.findAll();
		request.setAttribute("elencoGeneri", elencoGeneri);

		List<CasaEditrice> elencoCaseEditrici = CasaEd.findAll();
		request.setAttribute("elencoCaseEditrici", elencoCaseEditrici);



		this.paginaCorrente = "/GestioneLibro/PgsGestioneLibroNuovo.jsp";
		pubblicaPagina(request, response);
	}

	private void visualizzaMenu(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Libro> elenco = Lib.findAll();
		request.setAttribute("elencoLibri", elenco);

		this.paginaCorrente = "/GestioneLibro/PgsGestioneLibro.jsp";
		pubblicaPagina(request, response);
	}

	private void pubblicaPagina(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		getServletContext().getRequestDispatcher(this.paginaCorrente).forward(request, response);
	}

}
