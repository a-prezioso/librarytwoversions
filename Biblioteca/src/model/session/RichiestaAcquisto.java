package model.session;

import java.util.ArrayList;

import model.entity.RichiestaAcquistoSet;

public class RichiestaAcquisto {
	// Sezione attributi
	private int chiave;
	private String stato;
	private RichiestaAcquistoSet oggettoSet;

	// Sezione attributi disconnessi

	private int chiaveProfessore;
	private String professore;
	private int chiaveLibro;
	private String libroRichiesta;
	private int chiaveAutore;
	private String autore;
	private int chiaveGenere;
	private String genere;
	private String titolo;

	// Sezione get and set
	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public int getChiave() {
		return chiave;
	}
	// Sezione get e set disconnessa

	public int getChiaveProfessore() {
		return chiaveProfessore;
	}

	public String getProfessore() {
		return professore;
	}

	public void setProfessore(int chiave) {
		Professore oProfessore = new Professore(chiave);
		this.chiaveProfessore = chiave;
		this.professore = oProfessore.getCognome() + " " + oProfessore.getNome() + " " + oProfessore.getMatricola();
	}

	public int getChiaveLibro() {
		return chiaveLibro;
	}

	public String getLibro() {
		return libroRichiesta;
	}

	public void setLibroRichiesta(int chiave) {
		Libro oLibro = new Libro(chiave);
		this.chiaveLibro = chiave;
		this.titolo = oLibro.getTitolo();
		this.libroRichiesta = oLibro.getAutore() + " " + oLibro.getGenere() + " " + oLibro.getCasaeditrice() + " "
				+ oLibro.getTitolo();
	}

	public int getChiaveAutore() {
		return chiaveAutore;
	}

	public String getAutore() {
		return autore;
	}

	public void setAutore(int chiave) {
		Autore oAutore = new Autore(chiave);
		this.chiaveAutore = chiave;
		this.autore = oAutore.getCognome() + " " + oAutore.getNome();
	}

	public int getChiaveGenere() {
		return chiaveGenere;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(int chiave) {
		Genere oGenere = new Genere(chiave);
		this.chiaveGenere = chiave;
		this.genere = oGenere.getNome();
	}

	public String getTitolo() {
		return titolo;
	}

	// Sezione costruttori

	public RichiestaAcquisto() {
		this.inizializza();
	}

	private void inizializza() {
		this.chiave = 0;
		this.chiaveProfessore = 0;
		this.professore = "";
		this.chiaveLibro = 0;
		this.libroRichiesta = "";
		this.chiaveAutore = 0;
		this.autore = "";
		this.chiaveGenere = 0;
		this.genere = "";
		this.titolo = "";
		this.stato = "N";
		this.oggettoSet = new RichiestaAcquistoSet();
	}

	public RichiestaAcquisto(int chiave) {
		this.inizializza(chiave);
	}

	private void inizializza(int chiave) {
		this.oggettoSet = new RichiestaAcquistoSet(chiave);
		this.chiave = chiave;
		this.setProfessore(this.oggettoSet.IDProfessore);
		this.setLibroRichiesta(this.oggettoSet.IDLibro);
		this.setAutore(this.oggettoSet.IDAutore);
		this.setGenere(this.oggettoSet.IDGenere);
		this.setStato(this.oggettoSet.Stato);

	}

	// Sezione metodi di istanza
	public void salva() {
		this.oggettoSet.IDProfessore = this.getChiaveProfessore();
		this.oggettoSet.IDLibro = this.getChiaveLibro();
		this.oggettoSet.IDAutore = this.getChiaveAutore();
		this.oggettoSet.IDGenere = this.getChiaveGenere();
		this.oggettoSet.Titolo = this.getTitolo();
		this.oggettoSet.Stato = this.getStato();
		if (this.chiave == 0)
			this.oggettoSet.inserisci();
		else
			this.oggettoSet.aggiorna();
	}

	public void elimina() {
		this.oggettoSet.elimina();
	}

	// Sezione metodi di classe
	public static ArrayList<RichiestaAcquisto> elenco() {
		ArrayList<RichiestaAcquisto> elenco = new ArrayList<RichiestaAcquisto>();
		ArrayList<Integer> chiavi = RichiestaAcquistoSet.listachiavirichieste();
		for (int i = 0; i < chiavi.size(); i++)
			elenco.add(new RichiestaAcquisto(chiavi.get(i)));
		return elenco;
	}
}
