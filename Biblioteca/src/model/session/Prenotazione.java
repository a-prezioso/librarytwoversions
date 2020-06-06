package model.session;

import java.text.ParseException;
import java.util.ArrayList;

import model.entity.PrenotazioneSet;

public class Prenotazione {
	// sezione attributi
	private int chiave;
	private int chiaveLibro;
	private int libro;
	private int chiaveRichiedente;
	private String richiedente;
	private String richied;
	private String utente;
	private String data;
	private String evasa;
	private PrenotazioneSet oggettoSet;

	// sezioni get e set
	public void setData(String data) {
		this.data = data;
	}

	public String getEvasa() {
		return evasa;
	}

	public void setEvasa(String evasa) {
		this.evasa = evasa;
	}

	public int getChiave() {
		return chiave;
	}

	public int getChiaveLibro() {
		return chiaveLibro;
	}

	public int getLibro() {
		return libro;
	}

	public int getChiaveRichiedente() {
		return chiaveRichiedente;
	}

	public String getRichiedente() {
		return richiedente;
	}
	
	public String getUtente() {
		return utente;
	}


	public void setLibro(int chiaveLibro) {
		this.chiaveLibro = chiaveLibro;
	}
	
	public void setRichiedente(String richiedente, int chiaveRichiedente) throws ParseException {
		if(richied == "P")
		{
			Professore oProfessore = new Professore(chiave);
			this.chiaveRichiedente = chiave;
			this.utente = oProfessore.getCognome() + " " + oProfessore.getNome() 
				  + " " + oProfessore.getMatricola();
		}
		else if (richied == "S")
		{
			Studente oStudente = new Studente(chiave);
			this.chiaveRichiedente = chiave;
			this.utente = oStudente.getCognome() + " " + oStudente.getNome() 
			  + " " + oStudente.getMatricola();
		}
		else
		{
			this.chiaveRichiedente = chiave;
			this.utente = "Utente";
		}
	}
	
	
	// sezione costruttori
	public Prenotazione() {
		this.inizializza();
	}

	public Prenotazione(int chiave) throws ParseException {
		this.inizializza(chiave);
	}

	// sezione metodi di istanza
	private void inizializza() {
		this.chiave = 0;
		this.chiaveLibro = 0;
		this.chiaveRichiedente = 0;
		this.richiedente = "";
		this.data = "";
		this.evasa = "NO";
		this.oggettoSet = new PrenotazioneSet();
	}

	private void inizializza(int chiave) throws ParseException {
		this.oggettoSet = new PrenotazioneSet(chiave);
		this.chiave = chiave;
		this.setLibro(this.oggettoSet.IDLibro);
		this.setRichiedente(this.oggettoSet.Richiedente, this.oggettoSet.IDRichiedente);
		this.setData(this.oggettoSet.Data);
		this.setEvasa(this.oggettoSet.Evasa);
	}


	public void salva() throws ParseException
		{
			this.oggettoSet.IDLibro = this.getChiaveLibro();
			this.oggettoSet.IDRichiedente = this.getChiaveRichiedente();
			this.oggettoSet.Richiedente = this.getRichiedente();
			this.oggettoSet.Data = this.getData();
			this.oggettoSet.Evasa = this.getEvasa();
			
			if(this.chiave == 0)
				this.oggettoSet.inserisci();
			else
				this.oggettoSet.aggiorna();
		}
	

	public void elimina() throws ParseException {
		this.oggettoSet.elimina();

	}

	
	// sezione metodi di classe
	public static ArrayList<Prenotazione> elenco() throws ParseException {
		ArrayList<Prenotazione> elenco = new ArrayList<Prenotazione>();
		ArrayList<Integer> chiavi = PrenotazioneSet.listachiavi();
		for (int i = 0; i < chiavi.size(); i++)
			elenco.add(new Prenotazione(chiavi.get(i)));
		return elenco;

	}

	public String getData() {
		return data;
	}






}
