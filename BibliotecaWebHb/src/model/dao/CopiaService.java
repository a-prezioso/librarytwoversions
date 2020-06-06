package model.dao;


import java.util.ArrayList;
import java.util.List;

import model.session.Copia;
import model.session.Libro;


public class CopiaService extends AbstractService<CopiaDAO, Copia> {

	@Override
	public CopiaDAO createDAO() {
		return new CopiaDAO();
	}
	
	public List<Copia> disponibiliCopie(Libro oLibro) {
//		LibroService oLib = new LibroService();
//		oLib.findByTitolo(Titolo);
	    List<Copia> elencoCopie = oLibro.getListaCopie();
	    List<Copia> elencoCopieDisponibili = new ArrayList<Copia>();
	    for (int i = 0; i < elencoCopie.size(); i++) {
	    	boolean DISP = false;
	      for (int j = 0; j < elencoCopie.size() && !DISP; j++) {
	        if (elencoCopie.get(i).getStato().equalsIgnoreCase("D")) {
	          DISP = true;
	          elencoCopieDisponibili.add(elencoCopie.get(i));
	        }
	      }
	    }
	    return elencoCopieDisponibili;
	  }
	
	

}