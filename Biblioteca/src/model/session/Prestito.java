package model.session;

import java.text.ParseException;
import java.util.ArrayList;

import model.entity.PrestitoSet;

public class Prestito {
	// sezione attributi
	private int chiave;
	private int chiaveDestinatario;
	private int chiaveLibro;
	private String destinatario;
	private String dataInizio;
	private String dataFine;
	private String dataRestituzione;
	private String libro;
	private String dest;
	private String utente;
	private int libroelimina;
	private PrestitoSet oggettoSet;

	// sezione get e set
	public int getLibroelimina() {
		return libroelimina;
	}
	public String getDestinatario() {
		return destinatario;
	}
	public String getDataInizio() {
		return dataInizio;
	}
	public void setDataInizio(String dataInizio) {
		this.dataInizio = dataInizio;
	}
	public String getDataFine() {
		return dataFine;
	}
	public void setDataFine(String dataFine) {
		this.dataFine = dataFine;
	}
	public String getDataRestituzione() {
		return dataRestituzione;
	}
	public void setDataRestituzione(String dataRestituzione) {
		this.dataRestituzione = dataRestituzione;
	}
	public int getChiave() {
		return chiave;
	}
	public int getChiaveDestinatario() {
		return chiaveDestinatario;
	}
	public int getChiaveLibro() {
		return chiaveLibro;
	}
	public void setLibro(int chiavecopia) {
		Libro oLibroc = new Libro(0, chiavecopia, true);
		// chiaveLibro anche se il campo in questa tabella si chiama IDLibro è in realtà
		// la chiave della COPIA
		this.chiaveLibro = oLibroc.getChiavecopia();
		Libro oLibro = new Libro(oLibroc.getLibrochiave2());
		//assegno ad un attributo creato appositamente la chiave del libro da eliminare, che mi servirà per l'ELIMINA
		this.libroelimina = oLibroc.getLibrochiave2();
		this.libro = oLibro.getTitolo() + " " + oLibro.getAutore() + " " + oLibro.getGenere() + " "
				+ oLibro.getCasaeditrice();
	}
	public void setDestinatario(String destinatario, int chiave) throws ParseException {
		this.destinatario = dest;
		if (dest == "P") {
			Professore oProfessore = new Professore(chiave);
			this.chiaveDestinatario = chiave;
			this.utente = oProfessore.getCognome() + " " + oProfessore.getNome() + " " + oProfessore.getMatricola();
		} else if (dest == "S") {
			Studente oStudente = new Studente(chiave);
			this.utente = oStudente.getCognome() + " " + oStudente.getNome() + " " + oStudente.getMatricola();
		} else {
			this.chiaveDestinatario = chiave;
			this.utente = "utente";
		}
	}
	public String getLibro() {
		return libro;
	}
	public String getDest() {
		return dest;
	}
	public String getUtente() {
		return utente;
	}

	// sezione costruttori
	public Prestito() {
		this.inizializza();
	}

	public Prestito(int chiave) throws ParseException {
		this.inizializza(chiave);
	}

	// sezione metodi di istanza
	private void inizializza() {
		this.chiave = 0;
		this.chiaveDestinatario = 0;
		this.chiaveLibro = 0;
		this.destinatario = "" ;
		this.dataInizio = "";
		this.dataFine = "";
		this.dataRestituzione = "";
		this.oggettoSet = new PrestitoSet();
	}

	private void inizializza(int chiave) throws ParseException {
		this.oggettoSet = new PrestitoSet(chiave);
		this.chiave = chiave;
		this.setDestinatario(this.oggettoSet.Destinatario, this.oggettoSet.IDDestinatario);
		this.setLibro(this.oggettoSet.IDLibro);
		this.setDataInizio(this.oggettoSet.DataInizio);
		this.setDataFine(this.oggettoSet.DataFine);
		this.setDataRestituzione(this.oggettoSet.DataRestituzione);
	}

	public void salva() throws ParseException {
		this.oggettoSet.IDPrestito = this.getChiave();
		this.oggettoSet.IDLibro = this.getChiaveLibro();
		this.oggettoSet.IDDestinatario = this.getChiaveDestinatario();
		this.oggettoSet.DataInizio = this.getDataInizio();
		this.oggettoSet.DataFine = this.getDataFine();
		this.oggettoSet.DataRestituzione = this.getDataRestituzione();
		if (chiave == 0)
			this.oggettoSet.inserisci();
		else
			this.oggettoSet.aggiorna();
	}

	public void elimina() {
		this.oggettoSet.elimina();
	}

	// sezione metodi di classe
	public static ArrayList<Prestito> elenco() throws ParseException {
		ArrayList<Prestito> elenco = new ArrayList<Prestito>();
		ArrayList<Integer> chiavi = PrestitoSet.listachiavi();
		for (int i = 0; i < chiavi.size(); i++)
			elenco.add(new Prestito(chiavi.get(i)));
		return elenco;
	}
}


