package model.entity;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import util.UDate;

public class CopiaSet {

	// sezione attributi immagine db
	public int IDCopia;
	public int IDLibro;
	public String Stato;
	public String DataAcquisto;
	public String Seriale;
	public boolean pippo;
	public boolean chiavecopia;
	public ArrayList<String[]> ListaCopie;

	// sezione costruttori
	public CopiaSet() {

	}

	public CopiaSet (int chiave, int chiavecopia) {
		if (chiavecopia == 0) {
			this.estraiCopieLibro(chiave);
		} else {
			this.estraiCopie(chiavecopia);
		}
	}

	// sezione metodi di istanza
	private void estraiCopie(int chiavecopia) {
		this.ListaCopie = new ArrayList<String[]>();
		String sql = "SELECT * FROM Copia WHERE IDCopia = ?";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstCopia = oConnessione.connetti.prepareStatement(sql);
			pstCopia.setInt(1, chiavecopia);
			ResultSet rsElencoCopie = pstCopia.executeQuery();
			rsElencoCopie.next();
			{
				this.IDCopia = chiavecopia;
				
				String[] vet = new String[5];
				vet[0] = rsElencoCopie.getString("IDCopia");
				vet[1] = rsElencoCopie.getString("IDLibro");
				vet[2] = rsElencoCopie.getString("Stato");
				vet[3] = UDate.inserisciStringa(rsElencoCopie.getDate("DataAcquisto"));
				vet[4] = rsElencoCopie.getString("Seriale");
				this.ListaCopie.add(vet);
			}
			this.DataAcquisto = UDate.inserisciStringa(rsElencoCopie.getDate("DataAcquisto"));
			this.Seriale = rsElencoCopie.getString("Seriale");
			this.IDLibro = rsElencoCopie.getInt("IDLibro");
			oConnessione.chiudi();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (oConnessione.connetti != null)
				oConnessione.chiudi();
		}
	}

	private void estraiCopieLibro(int chiave) {
		this.ListaCopie = new ArrayList<String[]>();
		String sql = "SELECT * FROM Copia WHERE IDLibro = ?";
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
		} finally {
			if (oConnessione.connetti != null)
				oConnessione.chiudi();
		}

	}

	public void elimina() {
		String sql = "delete from Copia where IDCopia = ?";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstElimina = oConnessione.connetti.prepareStatement(sql);
			pstElimina.setInt(1, this.IDCopia);
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

	public void inserisciCopie() throws ParseException {
		String sql = "INSERT INTO Copia (idcopia, idlibro, stato, dataAcquisto, seriale) VALUES (copia_idcopia.nextval, "
				+ "?, UPPER(?), ?, ?)";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstInserisciCopie = oConnessione.connetti.prepareStatement(sql);
			for (int i = 0; i < this.ListaCopie.size(); i++) {
				pstInserisciCopie.setString(1, this.ListaCopie.get(i)[1]);
				pstInserisciCopie.setString(2, this.ListaCopie.get(i)[2]);
				pstInserisciCopie.setDate(3, new Date(UDate.ctrlData(this.ListaCopie.get(i)[3]).getTime()));
				pstInserisciCopie.setString(4, this.ListaCopie.get(i)[4]);
				pstInserisciCopie.executeUpdate();
			}
			oConnessione.chiudi();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (oConnessione.connetti != null)
				oConnessione.chiudi();
		}

	}

	public void aggiorna() throws ParseException {
		String sql = "UPDATE Copia SET IDLibro = ?, Stato = UPPER(?), DataAcquisto = ?, Seriale = ? WHERE IDCopia = ?";
		Connessione oConnessione = new Connessione();
		try {
			oConnessione.apri();
			PreparedStatement pstAggiorna = oConnessione.connetti.prepareStatement(sql);
			pstAggiorna.setInt(1, this.IDLibro);
			pstAggiorna.setString(2, this.Stato);
			pstAggiorna.setDate(3, new Date(UDate.ctrlData(this.DataAcquisto).getTime()));
			pstAggiorna.setString(4, this.Seriale);
			pstAggiorna.setInt(5, this.IDCopia);
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

	// sezione metodi di classe

}
