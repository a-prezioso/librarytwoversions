package model.session;

import java.text.ParseException;
import java.util.ArrayList;

import model.entity.CopiaSet;
import model.entity.LibroSet;

public class Libro {

	// sezione attributi
	private int chiave;
	private int chiaveautore;
	private String autore;
	private int chiavegenere;
	private String genere;
	private int chiavecasaeditrice;
	private String casaeditrice;
	private String titolo;
	private LibroSet oggettoSet;
	private boolean pippo;
	private ArrayList<String[]> copie;
	private boolean trovato;

	// sezione attributi classe copia
	private int chiavecopia;
	private String stato;
	private String dataacquisto;
	private String seriale;
	private CopiaSet oggettoSetcopia;
	private String librochiave;
	private int librochiave2;

	// sezione attributi metodo di istanza disconnessa

	// get e set di COPIA

	public void setLibrochiave2(int librochiave2) {
		this.librochiave2 = librochiave2;
	}

	public int getLibrochiave2() {
		return librochiave2;
	}

	public String getLibrochiave() {
		return librochiave;
	}

	public void setLibrochiave(String librochiave) {
		this.librochiave = librochiave;
	}

	public boolean isPippo() {
		return pippo;
	}

	public void setPippo(boolean pippo) {
		this.pippo = pippo;
	}

	public int getChiaveautore() {
		return this.chiaveautore;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getDataacquisto() {
		return dataacquisto;
	}

	public void setDataacquisto(String dataacquisto) {
		this.dataacquisto = dataacquisto;
	}

	public String getSeriale() {
		return seriale;
	}

	public void setSeriale(String seriale) {
		this.seriale = seriale;
	}

	public int getChiavecopia() {
		return chiavecopia;
	}

	// get e set di LIBRO
	public int getChiavegenere() {
		return this.chiavegenere;
	}

	public int getChiavecasaeditrice() {
		return this.chiavecasaeditrice;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public int getChiave() {
		return chiave;
	}

	public String getAutore() {
		return this.autore;
	}

	public void setAutore(int chiave) {
		Autore oAutore = new Autore(chiave);
		this.chiaveautore = chiave;
		this.autore = oAutore.getCognome() + " " + oAutore.getNome();
	}

	public String getGenere() {
		return this.genere;
	}

	public void setGenere(int chiave) {
		Genere oGenere = new Genere(chiave);
		this.chiavegenere = chiave;
		this.genere = oGenere.getNome();
	}

	public String getCasaeditrice() {
		return this.casaeditrice;
	}

	public void setCasaeditrice(int chiave) {
		CasaEditrice oCasaEditrice = new CasaEditrice(chiave);
		this.chiavecasaeditrice = chiave;
		this.casaeditrice = oCasaEditrice.getNome();
	}

	public ArrayList<String[]> getCopie() {
		return copie;
	}

	public boolean isTrovato() {
		return trovato;
	}

	public void setTrovato(boolean trovato) {
		this.trovato = trovato;
	}

	// sezione costruttori
	public Libro() {

		this.inizializza();
	}

	private void inizializza() {
		this.chiave = 0;
		this.chiaveautore = 0;
		this.chiavecasaeditrice = 0;
		this.chiavegenere = 0;
		this.setTitolo("");
		this.copie = new ArrayList<String[]>();
		this.oggettoSet = new LibroSet();
		this.chiavecopia = 0;
		this.librochiave = "";
		this.stato = "";
		this.dataacquisto = "";
		this.seriale = "";
		this.oggettoSetcopia = new CopiaSet();
	}

	public Libro(int chiave, int chiavecopia, boolean pippo) {
		this.pippo = pippo;
		this.chiavecopia = chiavecopia;
		this.inizializza(chiave);
	}

	public Libro(int chiave) {
		this.inizializza(chiave);
	}

	private void inizializza(int chiave) {
		if (!this.pippo) {

			this.oggettoSet = new LibroSet(chiave);
			this.chiave = chiave;
			this.setAutore(this.oggettoSet.IDAutore);
			this.setGenere(this.oggettoSet.IDGenere);
			this.setCasaeditrice(this.oggettoSet.IDCasaEditrice);
			this.setTitolo(this.oggettoSet.Titolo);
			if (pippo) {
				this.copie = new ArrayList<String[]>();
				for (int i = 0; i < this.oggettoSetcopia.ListaCopie.size(); i++) {
					this.copie.add(this.oggettoSetcopia.ListaCopie.get(i));
				}
			}
		} else {
			this.copie = new ArrayList<String[]>();
			this.oggettoSetcopia = new CopiaSet(chiave, this.chiavecopia);
			this.setLibrochiave2(this.oggettoSetcopia.IDLibro);
			this.setDataacquisto(this.oggettoSetcopia.DataAcquisto);
			this.setSeriale(this.oggettoSetcopia.Seriale);
			if (chiave != 0) {
				this.chiave = chiave;
			} else {
				this.chiavecopia = this.oggettoSetcopia.IDCopia;
			}
			for (int i = 0; i < this.oggettoSetcopia.ListaCopie.size(); i++) {
				this.copie.add(this.oggettoSetcopia.ListaCopie.get(i));
			}
			this.setLibrochiave2(this.oggettoSetcopia.IDLibro);
		}
	}

	// provare a fare debug nuobo

	// sezione metodi di istanza

	public void aggiungiCopia() {
		String[] libro = new String[5];
		libro[0] = ""; // idcopia
		libro[1] = this.librochiave;
		libro[2] = this.stato;
		libro[3] = this.dataacquisto;
		libro[4] = this.seriale;
		this.copie.add(libro);

	}

	// sezione metodi di istanza

	public void salvacopie(int chiavecopia) throws ParseException {
		this.oggettoSetcopia.IDLibro = this.getLibrochiave2();
		this.oggettoSetcopia.Stato = this.getStato();
		this.oggettoSetcopia.DataAcquisto = this.getDataacquisto();
		this.oggettoSetcopia.Seriale = this.getSeriale();
		this.oggettoSetcopia.IDCopia = this.getChiavecopia();
		this.oggettoSetcopia.ListaCopie = this.getCopie();
		if (chiavecopia == 0) {
			this.oggettoSetcopia.inserisciCopie();
		} else {
			this.oggettoSetcopia.aggiorna();
		}
	}

	public void salva() throws ParseException {

		this.oggettoSet.IDAutore = this.getChiaveautore();
		this.oggettoSet.IDGenere = this.getChiavegenere();
		this.oggettoSet.IDCasaEditrice = this.getChiavecasaeditrice();
		this.oggettoSet.Titolo = this.getTitolo();
		// this.oggettoSet.ListaCopie = this.getCopie();
		if (this.chiave == 0)
			this.oggettoSet.inserisci();
		else
			this.oggettoSet.aggiorna();

	}

	public void eliminaCopia() {
		this.oggettoSetcopia.elimina();
	}

	public void elimina() {
		this.oggettoSet.elimina();
	}

	public int checkCopie() {
		// this.chiave = chiave;
		trovato = false;
		this.oggettoSetcopia = new CopiaSet(chiave, 0);
		this.copie = new ArrayList<String[]>();
		for (int i = 0; i < this.oggettoSetcopia.ListaCopie.size(); i++) {
			this.copie.add(this.oggettoSetcopia.ListaCopie.get(i));
		}
		int val = 0;
		for (int i = 0; i < this.copie.size() && !trovato; i++) {
			if (this.copie.get(i)[2].equals("D")) {
				this.trovato = true;
				val = Integer.parseInt(this.copie.get(i)[0]);
			}
		}
		if (this.copie.size() == 0) {
			trovato = true;
		}

		return val;
	}

	// sezione metodi di classe
	public static ArrayList<Libro> elenco() {
		ArrayList<Libro> elenco = new ArrayList<Libro>();
		ArrayList<Integer> chiavi = LibroSet.listachiavi();
		for (int i = 0; i < chiavi.size(); i++) {
			elenco.add(new Libro(chiavi.get(i)));
		}
		return elenco;
	}

}
