package model.dao;


import model.session.Professore;


public class ProfessoreService extends AbstractService<ProfessoreDAO, Professore> {

	@Override
	public ProfessoreDAO createDAO() {
		return new ProfessoreDAO();
	}
}