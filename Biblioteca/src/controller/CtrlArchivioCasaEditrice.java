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

import model.session.CasaEditrice;

/**
 * Servlet implementation class CtrlArchivioCasaEditrice
 */
@WebServlet("/CtrlArchivioCasaEditrice")
public class CtrlArchivioCasaEditrice extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CtrlArchivioCasaEditrice() {
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
			nuovoCasaEditrice(request, response);
		} else if (azione.equals("Annulla")) {
			visualizzaElenco(request, response);
		} else if (azione.equals("Registra")) {
			salvaCasaEditrice(request, response);
			visualizzaElenco(request, response);
		} else if (azione.equals("Modifica")) {
			modificaCasaEditrice(request, response);
		} else if (azione.equals("Elimina")) {
			eliminaCasaEditrice(request, response);
		} else if (azione.equals("Elimina CasaEditrice")) {
			cancellaCasaEditrice(request, response);
			visualizzaElenco(request, response);
		}
	}

	private void cancellaCasaEditrice(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		((CasaEditrice) request.getSession().getAttribute("beanCasaEditrice")).elimina();

	}

	private void eliminaCasaEditrice(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getSession().setAttribute("beanCasaEditrice",
				new CasaEditrice(Integer.parseInt(request.getParameter("rdoIDCasaEditrice"))));
		getServletContext().getRequestDispatcher("/ArchivioCasaEditrice/PgsArchivioCasaEditriceElimina.jsp").forward(request,
				response);
	}

	private void modificaCasaEditrice(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getSession().setAttribute("beanCasaEditrice",
				new CasaEditrice(Integer.parseInt(request.getParameter("rdoIDCasaEditrice"))));
		getServletContext().getRequestDispatcher("/ArchivioCasaEditrice/PgsArchivioCasaEditriceNuovoModifica.jsp").forward(request,
				response);

	}

	private void salvaCasaEditrice(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		((CasaEditrice) request.getSession().getAttribute("beanCasaEditrice")).setNome(request.getParameter("txtNome"));
		((CasaEditrice) request.getSession().getAttribute("beanCasaEditrice")).salva();

	}

	private void nuovoCasaEditrice(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		CasaEditrice oCasaEditrice = new CasaEditrice();
		HttpSession oSessione = request.getSession();
		oSessione.setAttribute("beanCasaEditrice", oCasaEditrice);
		getServletContext().getRequestDispatcher("/ArchivioCasaEditrice/PgsArchivioCasaEditriceNuovoModifica.jsp").forward(request,
				response);
	}

	private void visualizzaElenco(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<CasaEditrice> elenco = CasaEditrice.elenco();
		request.setAttribute("elencoCasaEditrice", elenco);
		// definizione di un'oggetto della classe servlet context
		ServletContext oContesto = getServletContext();
		// definizione di un oggetto per il dispatching o pubblicazione della pgs
		RequestDispatcher oDispatcher = oContesto.getRequestDispatcher("" + "/ArchivioCasaEditrice/PgsArchivioCasaEditrice.jsp");
		oDispatcher.forward(request, response);
	}
}
