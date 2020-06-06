package model.dao;

import model.session.CasaEditrice;

public class CasaEditriceDAO extends AbstractDAO<CasaEditrice> {

	@Override
	public Class<CasaEditrice> getPersistentClass() {
		return CasaEditrice.class;
	}

}

