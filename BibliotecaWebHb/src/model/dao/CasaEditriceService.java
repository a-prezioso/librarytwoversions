package model.dao;


import model.session.CasaEditrice;


public class CasaEditriceService extends AbstractService<CasaEditriceDAO, CasaEditrice> {

	@Override
	public CasaEditriceDAO createDAO() {
		return new CasaEditriceDAO();
	}
}