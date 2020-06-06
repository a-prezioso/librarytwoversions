package model.dao;


import model.session.RichiestaAcquisto;


public class RichiestaAcquistoService extends AbstractService<RichiestaAcquistoDAO, RichiestaAcquisto> {

	@Override
	public RichiestaAcquistoDAO createDAO() {
		return new RichiestaAcquistoDAO();
	}
}