package model.entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RichiestaAcquistoSet {
	// Sezione attributi
	public int IDRichiestaDiAcquisto;
	public int IDProfessore;
	public int IDLibro;
	public int IDAutore;
	public int IDGenere;
	public String Titolo;
	public String Stato;

	// Sezione costruttori
	public RichiestaAcquistoSet() {

	}

	public RichiestaAcquistoSet(int chiave) {
		this.estrai(chiave);
	}

	// Sezioni metodi di istanza
	private void estrai(int chiave) {
		String sql = "Select * FROM RichiestaDiAcquisto WHERE IDRichiestaDiAcquisto = ?";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstRichiesta = oConnessione.connetti.prepareStatement(sql);
			pstRichiesta.setInt(1, chiave);
			ResultSet rsRichiesta = pstRichiesta.executeQuery();
			rsRichiesta.next();
			this.IDRichiestaDiAcquisto = chiave;
			this.IDProfessore = rsRichiesta.getInt("IDProfessore");
			this.IDLibro = rsRichiesta.getInt("IDLibro");
			this.IDAutore = rsRichiesta.getInt("IDAutore");
			this.IDGenere = rsRichiesta.getInt("IDGenere");
			this.Titolo = rsRichiesta.getString("Titolo");
			this.Stato = rsRichiesta.getString("Stato");
			oConnessione.chiudi();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (oConnessione.connetti != null)
				oConnessione.chiudi();
		}
	}

	public void inserisci() {
		String sql = "Insert into RichiestaDiAcquisto(IDRichiestaDiAcquisto, IDProfessore, IDLibro, IDAutore, IDGenere, Titolo, Stato)"
				+ "values (SEQCHIAVERICHIESTA.nextval, ?, ?, ?, ?, ?, ?)";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstInserisci = oConnessione.connetti.prepareStatement(sql);
			pstInserisci.setInt(1, this.IDProfessore);
			pstInserisci.setInt(2, this.IDLibro);
			pstInserisci.setInt(3, this.IDAutore);
			pstInserisci.setInt(4, this.IDGenere);
			pstInserisci.setString(5, this.Titolo);
			pstInserisci.setString(6, this.Stato);
			pstInserisci.executeUpdate();
			oConnessione.chiudi();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (oConnessione.connetti != null)
				oConnessione.chiudi();
		}

	}

	public void aggiorna() {
		String sql = "Update RichiestaDiAcquisto SET IDProfessore = ?, IDLibro = ?, IDAutore = ?, IDGenere = ?, Titolo = ?, Stato = ? WHERE IDRichiestaDiAcquisto = ?";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstAggiorna = oConnessione.connetti.prepareStatement(sql);
			pstAggiorna.setInt(1, this.IDProfessore);
			pstAggiorna.setInt(2, this.IDLibro);
			pstAggiorna.setInt(3, this.IDAutore);
			pstAggiorna.setInt(4, this.IDGenere);
			pstAggiorna.setString(5, this.Titolo);
			pstAggiorna.setString(6, this.Stato);
			pstAggiorna.setInt(7, this.IDRichiestaDiAcquisto);
			pstAggiorna.executeUpdate();
			oConnessione.chiudi();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (oConnessione.connetti != null)
				oConnessione.chiudi();
		}

	}

	public void elimina() {
		String sql = "DELETE FROM RichiestaDiAcquisto WHERE IDRichiestaDiAcquisto = ?";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstElimina = oConnessione.connetti.prepareStatement(sql);
			pstElimina.setInt(1, this.IDRichiestaDiAcquisto);
			pstElimina.executeUpdate();
			oConnessione.chiudi();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (oConnessione.connetti != null)
				oConnessione.chiudi();
		}

	}

	// Sezione metodi di classe
	public static ArrayList<Integer> listachiavirichieste() {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		String sql = "Select IDRichiestaDiAcquisto FROM RichiestaDiAcquisto";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstElenco = oConnessione.connetti.prepareStatement(sql);
			ResultSet rsElenco = pstElenco.executeQuery();
			while (rsElenco.next())
				lista.add(rsElenco.getInt("IDRichiestaDiAcquisto"));
			oConnessione.chiudi();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (oConnessione.connetti != null)
				oConnessione.chiudi();
		}
		return lista;

	}
}
