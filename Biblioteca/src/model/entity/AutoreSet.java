package model.entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AutoreSet {
	// sezione attributi immagine db
	public int IDAutore;
	public String Cognome;
	public String Nome;

	// sezione costruttori
	public AutoreSet(int chiave) {
		this.estrai(chiave);
	}

	public AutoreSet() {

	}

	// sezione metodi di istanza

	private void estrai(int chiave) {
		String sql = "Select * From Autore WHERE IDAutore = ?";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstAutore = oConnessione.connetti.prepareStatement(sql);
			pstAutore.setInt(1, chiave);
			ResultSet rsAutore = pstAutore.executeQuery();
			rsAutore.next();
			this.IDAutore = chiave;
			this.Nome = rsAutore.getString("Nome");
			this.Cognome = rsAutore.getString("Cognome");
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
		String sql = "delete from autore where IDAutore = ?";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstElimina = oConnessione.connetti.prepareStatement(sql);
			pstElimina.setInt(1, this.IDAutore);
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

	public void inserisci() {
		String sql = "INSERT INTO autore values (Autore_IDAutore.nextval, INITCAP(?), INITCAP(?))";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstInserisci = oConnessione.connetti.prepareStatement(sql);
			pstInserisci.setString(1, this.Nome);
			pstInserisci.setString(2, this.Cognome);
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

	public void aggiorna() {
		String sql = "UPDATE autore SET nome = INITCAP(?), cognome = INITCAP(?) WHERE IDAutore = ?";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstAggiorna = oConnessione.connetti.prepareStatement(sql);
			pstAggiorna.setString(1, this.Nome);
			pstAggiorna.setString(2, this.Cognome);
			pstAggiorna.setInt(3, this.IDAutore);
			pstAggiorna.executeUpdate();
			oConnessione.chiudi();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (oConnessione.connetti != null)
				oConnessione.chiudi();
		}
	} 

	public boolean checkLibroAutore()
	{
		String sql = "SELECT COUNT (*) AS Record FROM Libro WHERE IDAutore = ?";
		Connessione oConnessione = new Connessione();
		boolean chk = false;
		try {
			oConnessione.apri();
			PreparedStatement pstCheck = oConnessione.connetti.prepareStatement(sql);
			pstCheck.setInt(1, this.IDAutore);
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
	
	
	
	// sezioni metodi di classe

	public static ArrayList<Integer> listachiavi() {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		String sql = "SELECT IDAutore FROM autore";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstElenco = oConnessione.connetti.prepareStatement(sql);
			ResultSet rsElenco = pstElenco.executeQuery();
			while (rsElenco.next())
				lista.add(rsElenco.getInt("IDAutore"));
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
