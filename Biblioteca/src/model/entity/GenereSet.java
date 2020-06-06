package model.entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GenereSet {

	// attributi immagine db
	public int IDGenere;
	public String Nome;

	// costruttore
	public GenereSet() {

	}

	public GenereSet(int chiave) {
		this.estrai(chiave);
	}

	// sezione metodi di istanza
	private void estrai(int chiave) {
		String sql = "Select * From Genere WHERE IDGenere = ?";
		Connessione oConnessione = new Connessione();

		try {
			oConnessione.apri();
			PreparedStatement pstGenere = oConnessione.connetti.prepareStatement(sql);
			pstGenere.setInt(1, chiave);
			ResultSet rsGenere = pstGenere.executeQuery();
			rsGenere.next();
			this.IDGenere = chiave;
			this.Nome = rsGenere.getString("Nome");
			oConnessione.chiudi();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (oConnessione.connetti != null)
				oConnessione.chiudi();
		}

	}

	public void elimina() {
		String sql = "delete from genere where IDGenere = ?";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstElimina = oConnessione.connetti.prepareStatement(sql);
			pstElimina.setInt(1, this.IDGenere);
			pstElimina.executeUpdate();
			oConnessione.chiudi();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (oConnessione.connetti != null)
				oConnessione.chiudi();
		}
	}

	public void aggiorna() {
		String sql = "UPDATE Genere SET Nome = INITCAP(?) WHERE IDGenere = ?";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstAggiorna = oConnessione.connetti.prepareStatement(sql);
			pstAggiorna.setString(1, this.Nome);
			pstAggiorna.setInt(2, this.IDGenere);
			pstAggiorna.executeUpdate();
			oConnessione.chiudi();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (oConnessione.connetti != null)
				oConnessione.chiudi();
		}
	}

	public void inserisci() {
		String sql = "INSERT INTO Genere values (Genere_IDGenere.nextval, INITCAP(?))";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstInserisci = oConnessione.connetti.prepareStatement(sql);
			pstInserisci.setString(1, this.Nome);
			pstInserisci.executeUpdate();
			oConnessione.chiudi();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (oConnessione.connetti != null)
				oConnessione.chiudi();
		}
	}
	
	
	public boolean checkLibroGenere() {
		String sql = "SELECT COUNT(*) AS Record FROM Libro WHERE IDGenere = ?";
		Connessione oConnessione = new Connessione();
		boolean chk = false;
		try {
			oConnessione.apri();
			PreparedStatement pstCheck = oConnessione.connetti.prepareStatement(sql);
			pstCheck.setInt(1, this.IDGenere);
			ResultSet rsCheck = pstCheck.executeQuery();
			rsCheck.next();
			int record = rsCheck.getInt("Record");
			if (record == 0)
				chk = false;
			else
				chk = true;
			oConnessione.chiudi();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return chk;
	}
	

	// sezione metodi di classe
	public static ArrayList<Integer> listachiavi() {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		String sql = "select IDGenere from genere";
		Connessione oConnessione = new Connessione();
		oConnessione.apri();
		try {
			PreparedStatement pstElenco = oConnessione.connetti.prepareStatement(sql);
			ResultSet rsElenco = pstElenco.executeQuery();
			while (rsElenco.next())
				lista.add(rsElenco.getInt("IDGenere"));
			oConnessione.chiudi();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (oConnessione.connetti != null)
				oConnessione.chiudi();
		}
		return lista;
	}

}
