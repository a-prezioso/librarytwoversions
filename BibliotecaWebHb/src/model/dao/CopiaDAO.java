package model.dao;

import model.session.Copia;

public class CopiaDAO extends AbstractDAO<Copia> {

	@Override
	public Class<Copia> getPersistentClass() {
		return Copia.class;
	}

}

