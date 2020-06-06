package model.dao;


import model.session.Prenotazione;


public class PrenotazioneService extends AbstractService<PrenotazioneDAO, Prenotazione> {

	@Override
	public PrenotazioneDAO createDAO() {
		return new PrenotazioneDAO();
	}
}