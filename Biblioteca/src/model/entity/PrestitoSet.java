package model.entity;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import util.UDate;

public class PrestitoSet {
	// sezione attributi
	public int IDPrestito;
	public int IDDestinatario;
	public int IDLibro;
	public String Destinatario;
	public String DataInizio;
	public String DataFine;
	public String DataRestituzione;

	// sezione costruttori
	public PrestitoSet() {

	}

	public PrestitoSet(int chiave) {
		this.estrai(chiave);
	}

	// sezione metodi di istanza
	private void estrai(int chiave) {
		String sql = "Select * FROM Prestito WHERE IDPrestito = ?";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstPrestito = oConnessione.connetti.prepareStatement(sql);
			pstPrestito.setInt(1, chiave);
			ResultSet rsPrestito = pstPrestito.executeQuery();
			rsPrestito.next();
			this.IDPrestito = rsPrestito.getInt("IDPrestito");
			this.IDDestinatario = rsPrestito.getInt("IDDestinatario");
			this.IDLibro = rsPrestito.getInt("IDLibro");
			this.Destinatario = rsPrestito.getString("Destinatario");
			this.DataInizio = UDate.inserisciStringa(rsPrestito.getDate("DataInizio"));
			this.DataFine = UDate.inserisciStringa(rsPrestito.getDate("DataFine"));
			if(rsPrestito.getDate("DataRestituzione") == null)
				this.DataRestituzione = "Non Restituito";
			else
				this.DataRestituzione = UDate.inserisciStringa(rsPrestito.getDate("DataRestituzione"));
			oConnessione.chiudi();
		} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}


	public void inserisci() throws ParseException {
		String sql = "INSERT INTO Prestito (IDPrestito, IDDestinatario, IDLibro, Destinatario, DataInizio, DataFine)" 
				+ "VALUES (PRESTITO_IDPRESTITO.nextval, ?, ?, UPPER(?), ?, ?)";
		Connessione oConnessione = new Connessione();
		try {oConnessione.apri();
			PreparedStatement pstInserisci = oConnessione.connetti.prepareStatement(sql);
			pstInserisci.setInt(1, this.IDDestinatario);
			pstInserisci.setInt(2, this.IDLibro);
			pstInserisci.setString(3, this.Destinatario);
			pstInserisci.setDate(4, new Date(UDate.ctrlData(this.DataInizio).getTime()));
			pstInserisci.setDate(5, new Date(UDate.ctrlData(this.DataFine).getTime()));
			pstInserisci.executeUpdate();
			oConnessione.chiudi();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (oConnessione.connetti != null)
				oConnessione.chiudi();
		}
	}

	public void aggiorna() throws ParseException {
		String sql = "UPDATE Prestito SET DataFine = ? WHERE IDPrestito = ?";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstAggiorna = oConnessione.connetti.prepareStatement(sql);

			pstAggiorna.setDate(1, new Date(UDate.ctrlData(this.DataFine).getTime()));
			pstAggiorna.setInt(2, this.IDPrestito);
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
		String sql = "DELETE FROM Prestito WHERE IDPrestito = ?";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstElimina = oConnessione.connetti.prepareStatement(sql);
			pstElimina.setInt(1, this.IDPrestito);
			pstElimina.executeUpdate();
			oConnessione.chiudi();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (oConnessione.connetti != null)
				oConnessione.chiudi();
		}
	}
	
	
	// sezione metodi di classe
	public static ArrayList<Integer> listachiavi() {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		String sql = "Select IDPrestito FROM Prestito";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstElenco = oConnessione.connetti.prepareStatement(sql);
			ResultSet rsElenco = pstElenco.executeQuery();
			while (rsElenco.next())
				lista.add(rsElenco.getInt("IDPrestito"));
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

