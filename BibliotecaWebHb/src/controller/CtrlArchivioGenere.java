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

import model.dao.GenereService;
import model.session.Genere;

/**
 * Servlet implementation class CtrlArchivioGenere
 */
@WebServlet("/CtrlArchivioGenere")
public class CtrlArchivioGenere extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static GenereService oGen = new GenereService();

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
		oGen.delete(((Genere) request.getSession().getAttribute("beanGenere")).getId());
	}

	private void eliminaGenere(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Genere oGenere = oGen.findById(Integer.parseInt(request.getParameter("rdoIDGenere")));
		request.getSession().setAttribute("beanGenere", oGenere);
		getServletContext().getRequestDispatcher("/ArchivioGenere/PgsArchivioGenereElimina.jsp").forward(request, response);
		}

	

	private void modificaGenere(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Genere oGenere = oGen.findById(Integer.parseInt(request.getParameter("rdoIDGenere")));
		request.getSession().setAttribute("beanGenere", oGenere);
		getServletContext().getRequestDispatcher("/ArchivioGenere/PgsArchivioGenereNuovoModifica.jsp")
				.forward(request, response);

	}

	private void salvaGenere(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		((Genere) request.getSession().getAttribute("beanGenere")).setNome(request.getParameter("txtNome"));
		if(((Genere) request.getSession().getAttribute("beanGenere")).getId() == 0)
			oGen.persist(((Genere) request.getSession().getAttribute("beanGenere"))); 
		else
			oGen.update(((Genere) request.getSession().getAttribute("beanGenere")));
	}

	private void nuovoGenere(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Genere oGenere = new Genere();
		HttpSession oSessione = request.getSession();
		oSessione.setAttribute("beanGenere", oGenere);
		getServletContext().getRequestDispatcher("/ArchivioGenere/PgsArchivioGenereNuovoModifica.jsp")
				.forward(request, response);
	}

	private void visualizzaElenco(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Genere> elenco = oGen.findAll();
		request.setAttribute("elencoGenere", elenco);
		getServletContext().getRequestDispatcher("/ArchivioGenere/PgsArchivioGenere.jsp").forward(request, response);
	}

}
