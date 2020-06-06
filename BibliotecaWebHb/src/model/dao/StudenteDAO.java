package model.dao;

import model.session.Studente;

public class StudenteDAO extends AbstractDAO<Studente> {

	@Override
	public Class<Studente> getPersistentClass() {
		return Studente.class;
	}

}

