package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.PrestitoService;
import model.dao.RichiestaAcquistoService;

/**
 * Servlet implementation class CtrlGestioneRichiestaDiAcquisto
 */
@WebServlet("/CtrlGestioneRichiestaDiAcquisto")
public class CtrlGestioneRichiestaDiAcquisto extends HttpServlet 
{
	private static RichiestaAcquistoService oRichiesta = new RichiestaAcquistoService();
	
	private String paginaCorrente;
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CtrlGestioneRichiestaDiAcquisto() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		String azione = request.getParameter("cmdAzione");

		if (azione == null)
			visualizzaElenco(request, response);

		else if (azione.equals("Torna alla Home"))
			getServletContext().getRequestDispatcher("/CtrlMain").forward(request, response);
		
		else if (azione.equals("Home"))
			getServletContext().getRequestDispatcher("/CtrlMain").forward(request, response);
		
		else if (azione.equals("Avanti"))
			visualizzaElenco(request, response);
	}
	
	private void visualizzaElenco(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		request.setAttribute("elencoRichieste", oRichiesta.findAll());
		
		String sceltaRichiesta = (request.getSession().getAttribute("sceltaRichiesta").toString());
		request.setAttribute("sceltaRichiesta", sceltaRichiesta);
		
		this.paginaCorrente = "/GestioneRichiestaDiAcquisto/PgsGestioneRichiestaDiAcquisto.jsp";
		pubblicaPagina(request, response);
	}

	private void pubblicaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		getServletContext().getRequestDispatcher(this.paginaCorrente).forward(request, response);
	}
}
