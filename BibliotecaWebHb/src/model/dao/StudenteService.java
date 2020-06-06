package model.dao;


import model.session.Studente;


public class StudenteService extends AbstractService<StudenteDAO, Studente> {

	@Override
	public StudenteDAO createDAO() {
		return new StudenteDAO();
	}
}