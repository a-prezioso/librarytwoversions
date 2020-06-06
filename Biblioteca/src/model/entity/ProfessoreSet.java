package model.entity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProfessoreSet {

	// sezione attributi
	public int IDProfessore;
	public String Nome;
	public String Cognome;
	public String Matricola;

	// sezione costruttori
	public ProfessoreSet(int chiave) {
		this.estrai(chiave);
	}
	
	public ProfessoreSet()
	{
		
	}

	// sezione metodi di istanza
	private void estrai(int chiave) {
		String sql = "Select * From Professore WHERE IDProfessore = ?";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstProfessore = oConnessione.connetti.prepareStatement(sql);
			pstProfessore.setInt(1, chiave);
			ResultSet rsProfessore = pstProfessore.executeQuery();
			rsProfessore.next();
			this.IDProfessore = chiave;
			this.Nome = rsProfessore.getString("Nome");
			this.Cognome = rsProfessore.getString("Cognome");
			this.Matricola = rsProfessore.getString("Matricola");
			oConnessione.chiudi();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (oConnessione.connetti != null)
				oConnessione.chiudi();
		}

	}

	public void elimina()
	{
		String sql = " DELETE FROM professore WHERE IDProfessore = ?";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstElimina = oConnessione.connetti.prepareStatement(sql);
			pstElimina.setInt(1, this.IDProfessore);
			pstElimina.executeUpdate();
			oConnessione.chiudi();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(oConnessione != null)
				oConnessione.chiudi();
		}
		
	}
	
	public void aggiorna() {
		String sql = "UPDATE professore SET nome = INITCAP(?), cognome = INITCAP(?), matricola = ?";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstAggiorna = oConnessione.connetti.prepareStatement(sql);
			pstAggiorna.setString(1, this.Nome);
			pstAggiorna.setString(2, this.Cognome);
			pstAggiorna.setString(3, this.Matricola);
			pstAggiorna.setInt(4, this.IDProfessore);
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
		String sql = "INSERT INTO professore  values (Professore_IDProfessore.nextval, INITCAP(?), INITCAP(?), ?)";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstInserisci = oConnessione.connetti.prepareStatement(sql);
			pstInserisci.setString(1, this.Nome);
			pstInserisci.setString(2, this.Cognome);
			pstInserisci.setString(3, this.Matricola);
			pstInserisci.executeUpdate();
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
		String sql = ("SELECT IDProfessore FROM Professore");
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstElenco = oConnessione.connetti.prepareStatement(sql);
			ResultSet rsElenco = pstElenco.executeQuery();
			while (rsElenco.next())
				lista.add(rsElenco.getInt("IDProfessore"));
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
