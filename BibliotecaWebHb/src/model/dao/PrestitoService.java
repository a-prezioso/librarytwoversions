package model.dao;

import java.util.List;

import model.session.Prestito;

public class PrestitoService extends AbstractService<PrestitoDAO, Prestito> {

	@Override
	public PrestitoDAO createDAO() {
		return new PrestitoDAO();
	}

	private static PrestitoService oPres = new PrestitoService();

	public List<Prestito> elencoPrestitiChiusi() {
		List<Prestito> prestitiChiusi = oPres.findAll();
		int i = 0;
		while (i < prestitiChiusi.size()) {
			if (prestitiChiusi.get(i).getDataRestituzione() == null) {
				prestitiChiusi.remove(i);
			}
			i++;
		}
		return prestitiChiusi;
	}
	

	public List<Prestito> elencoPrestitiAttivi() {
		List<Prestito> prestitiAttivi = oPres.findAll();
		int i = 0;
		while (i < prestitiAttivi.size()) {
			if (prestitiAttivi.get(i).getDataRestituzione() != null) {
				prestitiAttivi.remove(i);
			}
			i++;
		}
		return prestitiAttivi;
	}

}