package model.dao;


import model.session.Autore;


public class AutoreService extends AbstractService<AutoreDAO, Autore> {

	@Override
	public AutoreDAO createDAO() {
		return new AutoreDAO();
	}
}