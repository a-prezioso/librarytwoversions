package model.entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import util.UDate;

public class LibroSet {

	// sezione attributi immagine db
	public int IDLibro;
	public int IDAutore;
	public int IDGenere;
	public int IDCasaEditrice;
	public String Titolo;
	public ArrayList<String[]> ListaCopie;

	// sezione costruttori

	public LibroSet() {

	}

	public LibroSet(int chiave) {
		this.estrai(chiave);
	}

	// sezione metodi di istanza
	public void estrai(int chiave) {
		String sql = "Select * FROM Libro WHERE IDLibro = ?";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstLibro = oConnessione.connetti.prepareStatement(sql);
			pstLibro.setInt(1, chiave);
			ResultSet rsLibro = pstLibro.executeQuery();
			rsLibro.next();
			this.IDLibro = chiave;
			this.IDAutore = rsLibro.getInt("IDAutore");
			this.IDGenere = rsLibro.getInt("IDGenere");
			this.IDCasaEditrice = rsLibro.getInt("IDCasaEditrice");
			this.Titolo = rsLibro.getString("Titolo");
			oConnessione.chiudi();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (oConnessione.connetti != null)
				oConnessione.chiudi();
		}
	}

	private void estraiCopie(int chiave) {
		this.ListaCopie = new ArrayList<String[]>();
		String sql = "SELECT * FROM Copia WHERE IDCopia = ?";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstCopia = oConnessione.connetti.prepareStatement(sql);
			pstCopia.setInt(1, chiave);
			ResultSet rsElencoCopie = pstCopia.executeQuery();
			while (rsElencoCopie.next()) {
				String[] vet = new String[5];
				vet[0] = rsElencoCopie.getString("IDCopia");
				vet[1] = rsElencoCopie.getString("IDLibro");
				vet[2] = rsElencoCopie.getString("Stato");
				vet[3] = UDate.inserisciStringa(rsElencoCopie.getDate("DataAcquisto"));
				vet[4] = rsElencoCopie.getString("Seriale");
				this.ListaCopie.add(vet);
			}
			oConnessione.chiudi();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void inserisci() throws ParseException {
		String sql = "INSERT INTO Libro values (libro_idlibro.nextval, ?, ?, ?, INITCAP(?))";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstInserisci = oConnessione.connetti.prepareStatement(sql);
			pstInserisci.setInt(1, this.IDAutore);
			pstInserisci.setInt(2, this.IDGenere);
			pstInserisci.setInt(3, this.IDCasaEditrice);
			pstInserisci.setString(4, this.Titolo);
			pstInserisci.executeUpdate();
			oConnessione.chiudi();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (oConnessione.connetti != null)
				oConnessione.chiudi();
		}

	}

	/*
	 * private void inserisciCopie() throws ParseException { String sql =
	 * "INSERT INTO Copia (idcopia, idlibro, stato, dataAcquisto, seriale) VALUES (copia_idcopia.nextval, "
	 * + "libro_idlibro.currval, UPPER(?), ?, ?)"; Connessione oConnessione = new
	 * Connessione(); try { oConnessione.apri(); PreparedStatement pstInserisciCopie
	 * = oConnessione.connetti.prepareStatement(sql); for (int i = 0; i <
	 * this.ListaCopie.size(); i++) { pstInserisciCopie.setString(1,
	 * this.ListaCopie.get(i)[2]); pstInserisciCopie.setDate(2, new
	 * Date(UDate.ctrlData(this.ListaCopie.get(i)[3]).getTime()));
	 * pstInserisciCopie.setString(3, this.ListaCopie.get(i)[4]);
	 * pstInserisciCopie.executeUpdate(); } oConnessione.chiudi(); } catch
	 * (SQLException e) { e.printStackTrace(); } finally { if (oConnessione.connetti
	 * != null) oConnessione.chiudi(); }
	 * 
	 * }
	 */

	public void aggiorna() {
		String sql = "UPDATE Libro SET IDAutore = ?, IDGenere = ?, IDCasaEditrice = ?, Titolo = INITCAP(?) WHERE IDLibro = ?";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstAggiorna = oConnessione.connetti.prepareStatement(sql);
			pstAggiorna.setInt(1, this.IDAutore);
			pstAggiorna.setInt(2, this.IDGenere);
			pstAggiorna.setInt(3, this.IDCasaEditrice);
			pstAggiorna.setString(4, this.Titolo);
			pstAggiorna.setInt(5, this.IDLibro);
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

	public void elimina() {
		this.eliminaTutteCopie();
		String sql = "delete from Libro where IDLibro = ?";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstElimina = oConnessione.connetti.prepareStatement(sql);
			pstElimina.setInt(1, this.IDLibro);
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

	private void eliminaTutteCopie() {
		String sql = "DELETE FROM copia WHERE IDLibro = ?";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstEliminaTutteCopie = oConnessione.connetti.prepareStatement(sql);
			pstEliminaTutteCopie.setInt(1, this.IDLibro);
			pstEliminaTutteCopie.executeUpdate();
			oConnessione.chiudi();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (oConnessione.connetti != null)
				oConnessione.chiudi();
		}

	}

	// sezione metodi di classe
	public static ArrayList<Integer> listachiavi() {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		String sql = "SELECT IDLibro FROM libro";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstElenco = oConnessione.connetti.prepareStatement(sql);
			ResultSet rsElenco = pstElenco.executeQuery();
			while (rsElenco.next())
				lista.add(rsElenco.getInt("IDLibro"));
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
