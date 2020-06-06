package model.entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CasaEditriceSet {

	// sezione attributi immagine db
	public int IDCasaEditrice;
	public String Nome;

	// sezione costruttori
	
	public CasaEditriceSet ()
	{
		
	}
	public CasaEditriceSet(int chiave)
	{
		this.estrai(chiave);
	}
	
	
	// sezione metodi di istanza
	private void estrai (int chiave)
	{
		String sql = "SELECT * FROM CasaEditrice WHERE IDCasaEditrice = ?";
		Connessione oConnessione = new Connessione ();
		try {
			oConnessione.apri();
			PreparedStatement pstCasaEditrice = oConnessione.connetti.prepareStatement(sql);
			pstCasaEditrice.setInt(1, chiave);
			ResultSet rsCasaEditrice = pstCasaEditrice.executeQuery();
			rsCasaEditrice.next();
			this.IDCasaEditrice = chiave;
			this.Nome = rsCasaEditrice.getString("Nome");
			oConnessione.chiudi();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} if (oConnessione.connetti != null)
			oConnessione.chiudi();
		
	}

	public void elimina() 
	{
		String sql = "delete from casaeditrice where IDCasaEditrice = ?";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstElimina = oConnessione.connetti.prepareStatement(sql);
			pstElimina.setInt(1, this.IDCasaEditrice);
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
	
	public void aggiorna() 
	{
		String sql = "UPDATE CasaEditrice SET Nome = INITCAP(?) WHERE IDCasaEditrice = ?";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstAggiorna = oConnessione.connetti.prepareStatement(sql);
			pstAggiorna.setString(1, this.Nome);
			pstAggiorna.setInt(2, this.IDCasaEditrice);
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
	
	public void inserisci ()
	{
		String sql = "INSERT INTO casaeditrice values (CasaEditrice_IDCasaEditrice.nextval, INITCAP(?))";
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
	
	
	
	public boolean checkLibroCasaEditrice() {
		String sql = "SELECT COUNT(*) AS Record FROM Libro WHERE IDCasaEditrice = ?";
		Connessione oConnessione = new Connessione();
		boolean chk = false;
		try {
			oConnessione.apri();
			PreparedStatement pstCheck = oConnessione.connetti.prepareStatement(sql);
			pstCheck.setInt(1, this.IDCasaEditrice);
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
		String sql = ("SELECT IDCasaEditrice FROM casaeditrice");
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstElenco = oConnessione.connetti.prepareStatement(sql);
			ResultSet rsElenco = pstElenco.executeQuery();
			while (rsElenco.next())
				lista.add(rsElenco.getInt("IDCasaEditrice"));
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
