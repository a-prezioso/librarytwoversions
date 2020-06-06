package controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.session.Studente;
import util.UDate;

/**
 * Servlet implementation class CtrlArchivioStudente
 */
@WebServlet("/CtrlArchivioStudente")
public class CtrlArchivioStudente extends HttpServlet {
	private static final long serialVersionUID = 1L;


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
		((Studente) request.getSession().getAttribute("beanStudente")).elimina();

	}

	private void eliminaStudente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			try {
				request.getSession().setAttribute("beanStudente", new Studente(Integer.parseInt(request.getParameter("rdoIDStudente"))));
			} catch (NumberFormatException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			getServletContext().getRequestDispatcher("/ArchivioStudente/PgsArchivioStudenteElimina.jsp").forward(request, response);
		}

	

	private void modificaStudente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			request.getSession().setAttribute("beanStudente",
					new Studente(Integer.parseInt(request.getParameter("rdoIDStudente"))));
		} catch (NumberFormatException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher("/ArchivioStudente/PgsArchivioStudenteNuovoModifica.jsp")
				.forward(request, response);

	}

	private void salvaStudente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		((Studente) request.getSession().getAttribute("beanStudente")).setCognome(request.getParameter("txtCognome"));
		((Studente) request.getSession().getAttribute("beanStudente")).setNome(request.getParameter("txtNome"));
		((Studente) request.getSession().getAttribute("beanStudente")).setMatricola(request.getParameter("txtMatricola"));
		try {
			((Studente) request.getSession().getAttribute("beanStudente")).setDataNascita(UDate.ctrlData(request.getParameter("txtDataNascita")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		((Studente) request.getSession().getAttribute("beanStudente")).setIndirizzo(request.getParameter("txtIndirizzo"));
		((Studente) request.getSession().getAttribute("beanStudente")).setComune(request.getParameter("txtComune"));
		((Studente) request.getSession().getAttribute("beanStudente")).setProvincia(request.getParameter("txtProvincia"));
		((Studente) request.getSession().getAttribute("beanStudente")).setNazione(request.getParameter("txtNazione"));
		((Studente) request.getSession().getAttribute("beanStudente")).setTelefono(request.getParameter("txtTelefono"));
		((Studente) request.getSession().getAttribute("beanStudente")).salva();

	}

	private void nuovoStudente(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Studente oStudente;
		try {
			oStudente = new Studente();
		HttpSession oSessione = request.getSession();
		oSessione.setAttribute("beanStudente", oStudente);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher("/ArchivioStudente/PgsArchivioStudenteNuovoModifica.jsp")
				.forward(request, response);
	}

	private void visualizzaElenco(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Studente> elenco;
		try {
			elenco = Studente.elenco();
		request.setAttribute("elencoStudente", elenco);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		// definizione di un'oggetto della classe servlet context
		ServletContext oContesto = getServletContext();
		// definizione di un oggetto per il dispatching o pubblicazione della pgs
		RequestDispatcher oDispatcher = oContesto.getRequestDispatcher("" + "/ArchivioStudente/PgsArchivioStudente.jsp");
		oDispatcher.forward(request, response);
	}

}
