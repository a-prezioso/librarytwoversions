package model.dao;

import model.session.Genere;

public class GenereDAO extends AbstractDAO<Genere> {

	@Override
	public Class<Genere> getPersistentClass() {
		return Genere.class;
	}

}

