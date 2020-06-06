package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.dao.AutoreService;
import model.session.Autore;

/**
 * Servlet implementation class CtrlArchivioAutore
 */
@WebServlet("/CtrlArchivioAutore")
public class CtrlArchivioAutore extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static AutoreService oAut = new AutoreService();

	public CtrlArchivioAutore() {
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
			nuovoAutore(request, response);
		} else if (azione.equals("Annulla")) {
			visualizzaElenco(request, response);
		} else if (azione.equals("Registra")) {
			salvaAutore(request, response);
			visualizzaElenco(request, response);
		} else if (azione.equals("Modifica")) {
			modificaAutore(request, response);
		} else if (azione.equals("Elimina")) {
			eliminaAutore(request, response);
		} else if (azione.equals("Elimina Autore")) {
			cancellaAutore(request, response);
			visualizzaElenco(request, response);
		}

	}

	private void cancellaAutore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		oAut.delete(((Autore) request.getSession().getAttribute("beanAutore")).getId());
	}

	private void eliminaAutore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Autore oAutore = oAut.findById(Integer.parseInt(request.getParameter("rdoIDAutore")));
		request.getSession().setAttribute("beanAutore", oAutore);
		getServletContext().getRequestDispatcher("/ArchivioAutore/PgsArchivioAutoreElimina.jsp").forward(request, response);
		}

	

	private void modificaAutore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Autore oAutore = oAut.findById(Integer.parseInt(request.getParameter("rdoIDAutore")));
		request.getSession().setAttribute("beanAutore", oAutore);
		getServletContext().getRequestDispatcher("/ArchivioAutore/PgsArchivioAutoreNuovoModifica.jsp")
				.forward(request, response);

	}

	private void salvaAutore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		((Autore) request.getSession().getAttribute("beanAutore")).setCognome(request.getParameter("txtCognome"));
		((Autore) request.getSession().getAttribute("beanAutore")).setNome(request.getParameter("txtNome"));
		if(((Autore) request.getSession().getAttribute("beanAutore")).getId() == 0)
			oAut.persist(((Autore) request.getSession().getAttribute("beanAutore"))); 
		else
			oAut.update(((Autore) request.getSession().getAttribute("beanAutore")));
	
	}

	private void nuovoAutore(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Autore oAutore = new Autore();
		HttpSession oSessione = request.getSession();
		oSessione.setAttribute("beanAutore", oAutore);
		getServletContext().getRequestDispatcher("/ArchivioAutore/PgsArchivioAutoreNuovoModifica.jsp")
				.forward(request, response);
	}

	private void visualizzaElenco(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Autore> elenco = oAut.findAll();
		request.setAttribute("elencoAutore", elenco);
		// definizione di un'oggetto della classe servlet context
		ServletContext oContesto = getServletContext();
		// definizione di un oggetto per il dispatching o pubblicazione della pgs
		RequestDispatcher oDispatcher = oContesto.getRequestDispatcher("" + "/ArchivioAutore/PgsArchivioAutore.jsp");
		oDispatcher.forward(request, response);
	}

}
