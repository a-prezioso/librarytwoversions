package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.session.Professore;

/**
 * Servlet implementation class CtrlArchivioProfessore
 */
@WebServlet("/CtrlArchivioProfessore")
public class CtrlArchivioProfessore extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public CtrlArchivioProfessore() {
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
			nuovoProfessore(request, response);
		} else if (azione.equals("Annulla")) {
			visualizzaElenco(request, response);
		} else if (azione.equals("Registra")) {
			salvaProfessore(request, response);
			visualizzaElenco(request, response);
		} else if (azione.equals("Modifica")) {
			modificaProfessore(request, response);
		} else if (azione.equals("Elimina")) {
			eliminaProfessore(request, response);
		} else if (azione.equals("Elimina Professore")) {
			cancellaProfessore(request, response);
			visualizzaElenco(request, response);
		}

	}

	private void cancellaProfessore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		((Professore) request.getSession().getAttribute("beanProfessore")).elimina();

	}

	private void eliminaProfessore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.getSession().setAttribute("beanProfessore", new Professore(Integer.parseInt(request.getParameter("rdoIDProfessore"))));
			getServletContext().getRequestDispatcher("/ArchivioProfessore/PgsArchivioProfessoreElimina.jsp").forward(request, response);
		}

	

	private void modificaProfessore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getSession().setAttribute("beanProfessore",
				new Professore(Integer.parseInt(request.getParameter("rdoIDProfessore"))));
		getServletContext().getRequestDispatcher("/ArchivioProfessore/PgsArchivioProfessoreNuovoModifica.jsp")
				.forward(request, response);

	}

	private void salvaProfessore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		((Professore) request.getSession().getAttribute("beanProfessore")).setCognome(request.getParameter("txtCognome"));
		((Professore) request.getSession().getAttribute("beanProfessore")).setNome(request.getParameter("txtNome"));
		((Professore) request.getSession().getAttribute("beanProfessore")).setMatricola(request.getParameter("txtMatricola"));
		((Professore) request.getSession().getAttribute("beanProfessore")).salva();

	}

	private void nuovoProfessore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Professore oProfessore = new Professore();
		HttpSession oSessione = request.getSession();
		oSessione.setAttribute("beanProfessore", oProfessore);
		getServletContext().getRequestDispatcher("/ArchivioProfessore/PgsArchivioProfessoreNuovoModifica.jsp")
				.forward(request, response);
	}

	private void visualizzaElenco(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Professore> elenco = Professore.elenco();
		request.setAttribute("elencoProfessore", elenco);
		// definizione di un'oggetto della classe servlet context
		ServletContext oContesto = getServletContext();
		// definizione di un oggetto per il dispatching o pubblicazione della pgs
		RequestDispatcher oDispatcher = oContesto.getRequestDispatcher("" + "/ArchivioProfessore/PgsArchivioProfessore.jsp");
		oDispatcher.forward(request, response);
	}

}
