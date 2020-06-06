package controller;

import java.io.IOException;
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

	public CtrlMain() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		visualizzaMenu(request, response);
	}

	private void visualizzaMenu(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		getServletContext().getRequestDispatcher("/Applicazione/PgsMain.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String valoreSceltoPrestito = request.getParameter("rdoSceltaPrestito");
		if (valoreSceltoPrestito != null) {
			menuPrestito(request, response);
		} 
		else {
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
					menuPrestito(request, response);
					break;

				default:
					visualizzaMenu(request, response);
					break;
				}
				if (Integer.parseInt(valoreScelto) != 6)
					getServletContext().getRequestDispatcher(controller).forward(request, response);
			}
				else
					visualizzaMenu(request, response);
			}
		}
	

	private void menuPrestito(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int scelta = 0;
		String valoreSceltoPrestito = request.getParameter("rdoSceltaPrestito");

		if (valoreSceltoPrestito != null) {
			int valore = Integer.parseInt(valoreSceltoPrestito);

			switch (valore) {
			case 0:
				scelta = 0;
				break;
			case 1:
				scelta = 1;
				break;
			case 2:
				scelta = 2;
				break;
			}
			request.getSession().setAttribute("scelta", scelta);
			getServletContext().getRequestDispatcher("/CtrlGestionePrestito").forward(request, response);
		} else
			visualizzaMenuPrestito(request, response);

	}

	private void visualizzaMenuPrestito(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/Applicazione/PgsMenuPrestito.jsp").forward(request, response);
	}
}
