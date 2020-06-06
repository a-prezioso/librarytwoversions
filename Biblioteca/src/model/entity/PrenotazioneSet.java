package model.entity;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import util.UDate;

public class PrenotazioneSet {
	//sezione attributi
	
	public int IDPrenotazione;
	public int IDLibro;
	public int IDRichiedente;
	public String Richiedente;
	public String Data;
	public String Evasa;
	
	//sezione costruttori
	public PrenotazioneSet () {
		
	}
	
	public PrenotazioneSet(int chiave) {
		this.estrai(chiave);
	}
	
	//sezione metodi di istanza
	private void estrai(int chiave) {
		String sql = "Select * FROM Prenotazione WHERE IDPrenotazione = ?";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstPrenotazione = oConnessione.connetti.prepareStatement(sql);
			pstPrenotazione.setInt(1, chiave);
			ResultSet rsPrenotazione = pstPrenotazione.executeQuery();
			rsPrenotazione.next();
			this.IDPrenotazione = chiave;
			this.IDLibro = rsPrenotazione.getInt("IDLibro");
			this.IDRichiedente = rsPrenotazione.getInt("IDRichiedente");
			this.Richiedente = rsPrenotazione.getString("Richiedente");
			this.Data = UDate.inserisciStringa(rsPrenotazione.getDate("Data"));
			this.Evasa = rsPrenotazione.getString("Evasa");
			oConnessione.chiudi();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (oConnessione.connetti != null)
				oConnessione.chiudi();
		} 
	}
	
	public void aggiorna() throws ParseException 
	{
		// TODO Auto-generated method stub
		String sql = "UPDATE Prenotazione SET Data = ?, Evasa = UPPER(?) "
				+ " WHERE IDPrenotazione = ?";
		Connessione oConnessione = new Connessione();
		
		try 
		{
			oConnessione.apri();
			
			PreparedStatement pstAggiorna = oConnessione.connetti.prepareStatement(sql);
			pstAggiorna.setDate(1, new Date(UDate.ctrlData(this.Data).getTime()));
			pstAggiorna.setString(2, this.Evasa);
			pstAggiorna.setInt(3, this.IDPrenotazione);
			pstAggiorna.executeUpdate();
			
			oConnessione.chiudi();
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally 
		{
			if(oConnessione.connetti != null)
				oConnessione.chiudi();
		}
	}
	
	public void elimina() throws ParseException{
		String sql = "DELETE FROM Prenotazione WHERE IDPrenotazione = ?";
		Connessione oConnessione = new Connessione();
		
		try {
			oConnessione.apri();
			PreparedStatement pstElimina = oConnessione.connetti.prepareStatement(sql);
			pstElimina.setInt(1, this.IDPrenotazione);
			pstElimina.executeUpdate();
			oConnessione.chiudi();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	public void inserisci() throws ParseException {
		String sql = "INSERT INTO Prenotazione (IDPrenotazione, IDLibro, IDRichiedente, Richiedente, Data, Evasa)" 
				+ "VALUES (PRENOTAZIONE_IDPRENOTAZIONE.nextval, ?, ?, UPPER(?), ?, UPPER(?))";
		Connessione oConnessione = new Connessione();
		try {oConnessione.apri();
			PreparedStatement pstInserisci = oConnessione.connetti.prepareStatement(sql);
			pstInserisci.setInt(1, this.IDLibro);
			pstInserisci.setInt(2, this.IDRichiedente);
			pstInserisci.setString(3, this.Richiedente);
			pstInserisci.setDate(4, new Date(UDate.ctrlData(this.Data).getTime()));
			pstInserisci.setString(5, this.Evasa);
			pstInserisci.executeUpdate();
			oConnessione.chiudi();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (oConnessione.connetti != null)
				oConnessione.chiudi();
		}
	}

	
	//sezione  metodi di classe
	public static ArrayList<Integer> listachiavi() {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		String sql = "Select IDPrenotazione FROM Prenotazione";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstElenco = oConnessione.connetti.prepareStatement(sql);
			ResultSet rsElenco = pstElenco.executeQuery();
			lista.add(rsElenco.getInt("IDPrenotazione"));
			while (rsElenco.next())
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
