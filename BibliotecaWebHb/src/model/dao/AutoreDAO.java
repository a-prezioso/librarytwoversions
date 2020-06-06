package model.dao;

import model.session.Autore;

public class AutoreDAO extends AbstractDAO<Autore> {

	@Override
	public Class<Autore> getPersistentClass() {
		return Autore.class;
	}

}

