package model.dao;

import model.session.Prestito;

public class PrestitoDAO extends AbstractDAO<Prestito> {

	@Override
	public Class<Prestito> getPersistentClass() {
		return Prestito.class;
	}

}

