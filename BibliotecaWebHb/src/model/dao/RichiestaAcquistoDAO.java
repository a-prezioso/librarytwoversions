package model.dao;

import model.session.RichiestaAcquisto;

public class RichiestaAcquistoDAO extends AbstractDAO<RichiestaAcquisto> {

	@Override
	public Class<RichiestaAcquisto> getPersistentClass() {
		return RichiestaAcquisto.class;
	}

}

