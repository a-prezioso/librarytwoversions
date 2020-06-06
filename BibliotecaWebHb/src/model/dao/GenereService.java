package model.dao;


import model.session.Genere;


public class GenereService extends AbstractService<GenereDAO, Genere> {

	@Override
	public GenereDAO createDAO() {
		return new GenereDAO();
	}
}