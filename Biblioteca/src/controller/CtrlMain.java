package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CtrlMain
 */
@WebServlet("/CtrlMain")
public class CtrlMain extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public CtrlMain() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		visualizzaMenu(request, response);
	}

	private void visualizzaMenu(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/Applicazione/PgsMain.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		String controller = "";
		String valoreScelto = request.getParameter("rdoScelta");
		if (valoreScelto != null) {
			int valore = Integer.parseInt(valoreScelto);
			switch (valore) {
			case 0:
				controller = "/CtrlArchivioGenere";
				break;
			case 1:
				controller = "/CtrlArchivioCasaEditrice";
				break;
			case 2: 
				controller = "/CtrlArchivioAutore";
				break;
			case 3:
				controller = "/CtrlArchivioStudente";
				break;
			case 4: 
				controller = "/CtrlArchivioProfessore";
				break;
			case 5:
				controller = "/CtrlGestioneLibro";
				break;
			case 6:
				controller = "/CtrlGestionePrestito";
				break;
			case 7: 
				controller = "/CtrlPrenotazione";
				break;
			case 8:
				controller = "/CtrlRichiestaDiAcquisto";
				break;				
			default:
				visualizzaMenu(request, response);
				break; }
			getServletContext().getRequestDispatcher(controller).forward(request, response);
				// definizione di un'oggetto della classe servlet context
				//ServletContext oContesto = getServletContext();
				// definizione di un oggetto per il dispatching o pubblicazione della pgs
				//RequestDispatcher oDispatcher = oContesto.getRequestDispatcher("/CtrlArchivioDocente");
				//oDispatcher.forward(req, resp);
		}
		else
			visualizzaMenu(request, response);

	}

}
