package model.dao;

import java.util.ArrayList;
import java.util.List;

import model.session.Copia;
import model.session.Libro;

public class LibroService extends AbstractService<LibroDAO, Libro> {

	@Override
	public LibroDAO createDAO() {
		return new LibroDAO();
	}

	public List<Libro> avBo() {
		List<Libro> elencoLibri = findAll();
		List<Libro> elencoLibriDisponibili = new ArrayList<Libro>();
		for (int i = 0; i < elencoLibri.size(); i++) {
			boolean DISP = false;
			for (int j = 0; j < elencoLibri.get(i).getListaCopie().size() && !DISP; j++) {
				if (elencoLibri.get(i).getListaCopie().get(j).getStato().equalsIgnoreCase("D")) {
					DISP = true;
					elencoLibriDisponibili.add(elencoLibri.get(i));
				}
			}
		}
		return elencoLibriDisponibili;
	}

	public Libro findByTitolo(String Titolo) {
		Libro oLibro = new Libro();
		List<Libro> elenco = this.findAll();
		int i = 0;
		while(i<elenco.size()) {
		if(elenco.get(i).getTitolo().equals(Titolo)) {
			oLibro = this.findById(elenco.get(i).getId());
		}
		i++;
	}
		return oLibro;
}

//	public void removeCopie(Libro lib, Copia cop) 
//	 {
//	     int i = 0;
//	     boolean removed = false;
//	     
//	     while (i < lib.getListaCopie().size() && !removed) 
//	     {
//	      if (lib.getListaCopie().get(i).getIdcopia() == cop.getIdcopia()) 
//	      {
//	       lib.getListaCopie().remove(i);
//	       removed = true;
//	      }
//	      
//	      i++;
//	     }
//	  }
	//
//	 public void removeCopie(Libro lib, int cop) 
//	 {
//	  Copia oCopia = new CopiaService().findById(cop);
//	  removeCopie(lib, oCopia);
//	 }

}