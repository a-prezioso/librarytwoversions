package model.dao;

import model.session.Professore;

public class ProfessoreDAO extends AbstractDAO<Professore> {

	@Override
	public Class<Professore> getPersistentClass() {
		return Professore.class;
	}

}

