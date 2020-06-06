package model.dao;

import model.session.Libro;

public class LibroDAO extends AbstractDAO<Libro> {

	@Override
	public Class<Libro> getPersistentClass() {
		return Libro.class;
	}

}

