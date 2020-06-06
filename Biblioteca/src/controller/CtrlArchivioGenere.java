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

import model.session.Genere;

/**
 * Servlet implementation class CtrlArchivioGenere
 */
@WebServlet("/CtrlArchivioGenere")
public class CtrlArchivioGenere extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CtrlArchivioGenere() {
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
			nuovoGenere(request, response);
		} else if (azione.equals("Annulla")) {
			visualizzaElenco(request, response);
		} else if (azione.equals("Registra")) {
			salvaGenere(request, response);
			visualizzaElenco(request, response);
		} else if (azione.equals("Modifica")) {
			modificaGenere(request, response);
		} else if (azione.equals("Elimina")) {
			eliminaGenere(request, response);
		} else if (azione.equals("Elimina Genere")) {
			cancellaGenere(request, response);
			visualizzaElenco(request, response);
		}
	}
	
	
	
	private void cancellaGenere(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		((Genere) request.getSession().getAttribute("beanGenere")).elimina();

	}
	
	
	
	private void eliminaGenere(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			request.getSession().setAttribute("beanGenere", new Genere(Integer.parseInt(request.getParameter("rdoIDGenere"))));
			getServletContext().getRequestDispatcher("/ArchivioGenere/PgsArchivioGenereElimina.jsp").forward(request, response);
		}
	
	

	private void modificaGenere(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getSession().setAttribute("beanGenere",
				new Genere(Integer.parseInt(request.getParameter("rdoIDGenere"))));
		getServletContext().getRequestDispatcher("/ArchivioGenere/PgsArchivioGenereNuovoModifica.jsp").forward(request,
				response);

	}

	private void salvaGenere(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		((Genere) request.getSession().getAttribute("beanGenere")).setNome(request.getParameter("txtNome"));
		((Genere) request.getSession().getAttribute("beanGenere")).salva();

	}

	private void nuovoGenere(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Genere oGenere = new Genere();
		HttpSession oSessione = request.getSession();
		oSessione.setAttribute("beanGenere", oGenere);
		getServletContext().getRequestDispatcher("/ArchivioGenere/PgsArchivioGenereNuovoModifica.jsp").forward(request,
				response);
	}

	private void visualizzaElenco(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Genere> elenco = Genere.elenco();
		request.setAttribute("elencoGenere", elenco);
		// definizione di un'oggetto della classe servlet context
		ServletContext oContesto = getServletContext();
		// definizione di un oggetto per il dispatching o pubblicazione della pgs
		RequestDispatcher oDispatcher = oContesto.getRequestDispatcher("" + "/ArchivioGenere/PgsArchivioGenere.jsp");
		oDispatcher.forward(request, response);
	}
}
