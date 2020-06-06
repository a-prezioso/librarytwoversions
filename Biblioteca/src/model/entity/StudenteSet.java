package model.entity;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import util.UDate;

public class StudenteSet {

	//Sezione Attributi
	public int IDStudente;
	public String Cognome;
	public String Nome;
	public String Matricola;
	public Date DataNascita;
	public String Indirizzo;
	public String Comune;
	public String Provincia;
	public String Nazione;
	public String Telefono;

	//Sezione COSTRUTTORI
	public StudenteSet() {

	}

	public StudenteSet(int chiave) throws ParseException {
		this.estrai(chiave);
	}

	//Sezione Metodi di Istanza
	private void estrai(int chiave) throws ParseException {
		String sql = "SELECT * FROM Studente WHERE IDStudente = ?";

		Connessione oConnessione = new Connessione();

		try {
			oConnessione.apri();
			PreparedStatement pstStudente = oConnessione.connetti.prepareStatement(sql);
			pstStudente.setInt(1, chiave);
			ResultSet rsStudente = pstStudente.executeQuery();
			rsStudente.next();
			this.IDStudente = chiave;
			this.Cognome = rsStudente.getString("Cognome");
			this.Nome = rsStudente.getString("Nome");
			this.Matricola = rsStudente.getString("Matricola");
			this.DataNascita = rsStudente.getDate("DataNascita");
			this.Indirizzo = rsStudente.getString("Indirizzo");
			this.Comune = rsStudente.getString("Comune");
			this.Provincia = rsStudente.getString("Provincia");
			this.Nazione = rsStudente.getString("Nazione");
			this.Telefono = rsStudente.getString("Telefono");
			oConnessione.chiudi();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (oConnessione.connetti != null)
				oConnessione.chiudi();
		}

	}

	public void inserisci() {

		String sql = "INSERT INTO Studente (IDStudente, Cognome, Nome, Matricola, DataNascita, Indirizzo, Comune, Provincia, Nazione, Telefono) "
				+ "values (STUDENTE_IDSTUDENTE.nextval, INITCAP(?), INITCAP(?), INITCAP(?), ?, INITCAP(?), INITCAP(?), INITCAP(?), INITCAP(?), ?)";

		Connessione oConnessione = new Connessione();

		try {
			oConnessione.apri();
			PreparedStatement pstInserisci = oConnessione.connetti.prepareStatement(sql);
			pstInserisci.setString(1, this.Cognome);
			pstInserisci.setString(2, this.Nome);
			pstInserisci.setString(3, this.Matricola);
			pstInserisci.setDate(4, this.DataNascita);
			pstInserisci.setString(5, this.Indirizzo);
			pstInserisci.setString(6, this.Comune);
			pstInserisci.setString(7, this.Provincia);
			pstInserisci.setString(8, this.Nazione);
			pstInserisci.setString(9, this.Telefono);
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

		String sql = "UPDATE Studente SET Cognome = INITCAP(?), Nome = INITCAP(?), Matricola = INITCAP(?), DataNascita = ?, "
				+ "Indirizzo = INITCAP(?), Comune = INITCAP(?), Provincia = INITCAP(?), Nazione = INITCAP(?)"
				+ "Telefono = ?, WHERE IDStudente = ?";

		Connessione oConnessione = new Connessione();

		try {
			oConnessione.apri();

			PreparedStatement pstAggiorna = oConnessione.connetti.prepareStatement(sql);
			pstAggiorna.setString(1, this.Cognome);
			pstAggiorna.setString(2, this.Nome);
			pstAggiorna.setString(3, this.Matricola);
			pstAggiorna.setDate(4, this.DataNascita);
			pstAggiorna.setString(5, this.Indirizzo);
			pstAggiorna.setString(6, this.Comune);
			pstAggiorna.setString(7, this.Provincia);
			pstAggiorna.setString(8, this.Nazione);
			pstAggiorna.setString(9, this.Telefono);
			pstAggiorna.setInt(10, this.IDStudente);
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

		String sql = "DELETE FROM Studente WHERE IDStudente = ?";

		Connessione oConnessione = new Connessione();

		try {
			oConnessione.apri();
			PreparedStatement pstElimina = oConnessione.connetti.prepareStatement(sql);
			pstElimina.setInt(1, this.IDStudente);
			pstElimina.executeUpdate();
			oConnessione.chiudi();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (oConnessione.connetti != null)
				oConnessione.chiudi();
		}
	}

	//Sezione METODI DI CLASSE
	
	public static ArrayList<Integer> listachiavi() {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		
		String sql = "SELECT IDStudente FROM Studente";
		
		Connessione oConnessione = new Connessione();
		
		try {
			oConnessione.apri();
			PreparedStatement pstElenco = oConnessione.connetti.prepareStatement(sql);
			ResultSet rsElenco = pstElenco.executeQuery();
						
			while(rsElenco.next())
				lista.add(rsElenco.getInt("IDStudente"));
			
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
