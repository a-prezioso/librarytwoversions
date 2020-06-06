package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.StudenteService;
import model.session.Studente;

/**
 * Servlet implementation class CtrlArchivioStudente
 */
@WebServlet("/CtrlArchivioStudente")
public class CtrlArchivioStudente extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static StudenteService oStu = new StudenteService();

	public CtrlArchivioStudente() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String azione = request.getParameter("cmdAzione");
		if (azione == null) {
			visualizzaElenco(request, response);
		} else if (azione.equals("Torna alla Home")) {
			getServletContext().getRequestDispatcher("/CtrlMain").forward(request, response);
		} else if (azione.equals("Nuovo")) {
			nuovoStudente(request, response);
		} else if (azione.equals("Annulla")) {
			visualizzaElenco(request, response);
		} else if (azione.equals("Registra")) {
			salvaStudente(request, response);
			visualizzaElenco(request, response);
		} else if (azione.equals("Modifica")) {
			modificaStudente(request, response);
		} else if (azione.equals("Elimina")) {
			eliminaStudente(request, response);
		} else if (azione.equals("Elimina Studente")) {
			cancellaStudente(request, response);
			visualizzaElenco(request, response);
		}

	}

	private void cancellaStudente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		oStu.delete(((Studente) request.getSession().getAttribute("beanStudente")).getId());
	}

	private void eliminaStudente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Studente oStudente = oStu.findById(Integer.parseInt(request.getParameter("rdoIDStudente")));
		request.getSession().setAttribute("beanStudente", oStudente);
		getServletContext().getRequestDispatcher("/ArchivioStudente/PgsArchivioStudenteElimina.jsp").forward(request, response);
		}

	

	private void modificaStudente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Studente oStudente = oStu.findById(Integer.parseInt(request.getParameter("rdoIDStudente")));
		request.getSession().setAttribute("beanStudente", oStudente);
		getServletContext().getRequestDispatcher("/ArchivioStudente/PgsArchivioStudenteNuovoModifica.jsp")
				.forward(request, response);

	}

	private void salvaStudente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		((Studente) request.getSession().getAttribute("beanStudente")).setCognome(request.getParameter("txtCognome"));
		((Studente) request.getSession().getAttribute("beanStudente")).setNome(request.getParameter("txtNome"));
		((Studente) request.getSession().getAttribute("beanStudente")).setMatricola(request.getParameter("txtMatricola"));
		((Studente) request.getSession().getAttribute("beanStudente")).setDatanascita(request.getParameter("txtDataNascita"));
		((Studente) request.getSession().getAttribute("beanStudente")).setIndirizzo(request.getParameter("txtIndirizzo"));
		((Studente) request.getSession().getAttribute("beanStudente")).setComune(request.getParameter("txtComune"));
		((Studente) request.getSession().getAttribute("beanStudente")).setProvincia(request.getParameter("txtProvincia"));
		((Studente) request.getSession().getAttribute("beanStudente")).setNazione(request.getParameter("txtNazione"));
		((Studente) request.getSession().getAttribute("beanStudente")).setTelefono(request.getParameter("txtTelefono"));

		if(((Studente) request.getSession().getAttribute("beanStudente")).getId() == 0)
			oStu.persist(((Studente) request.getSession().getAttribute("beanStudente"))); 
		else
			oStu.update(((Studente) request.getSession().getAttribute("beanStudente")));
	
	}

	private void nuovoStudente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Studente oStudente = new Studente();
//		HttpSession oSessione = ;
		request.getSession().setAttribute("beanStudente", oStudente);
		getServletContext().getRequestDispatcher("/ArchivioStudente/PgsArchivioStudenteNuovoModifica.jsp")
				.forward(request, response);
	}

	private void visualizzaElenco(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Studente> elenco = oStu.findAll();
		request.setAttribute("elencoStudente", elenco);
		// definizione di un'oggetto della classe servlet context
		ServletContext oContesto = getServletContext();
		// definizione di un oggetto per il dispatching o pubblicazione della pgs
		RequestDispatcher oDispatcher = oContesto.getRequestDispatcher("" + "/ArchivioStudente/PgsArchivioStudente.jsp");
		oDispatcher.forward(request, response);
	}

}
