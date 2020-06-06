package model.dao;

import model.session.Prenotazione;

public class PrenotazioneDAO extends AbstractDAO<Prenotazione> {

	@Override
	public Class<Prenotazione> getPersistentClass() {
		return Prenotazione.class;
	}

}

