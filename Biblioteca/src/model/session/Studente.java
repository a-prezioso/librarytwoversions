package model.session;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import model.entity.StudenteSet;

public class Studente {
	// Sezione Attributi
	private int chiave;
	private String cognome;
	private String nome;
	private String matricola;
	private Date datanascita;
	private String indirizzo;
	private String comune;
	private String provincia;
	private String nazione;
	private String telefono;
	StudenteSet oggettoSet;

	// Sezione G&S
	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getMatricola() {
		return matricola;
	}

	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}

	public Date getDataNascita() {
		return datanascita;
	}

	public void setDataNascita(Date datanascita) throws ParseException {
		this.datanascita = datanascita;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getNazione() {
		return nazione;
	}

	public void setNazione(String nazione) {
		this.nazione = nazione;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getChiave() {
		return chiave;
	}

	// Sezione COSTRUTTORI
	public Studente() throws ParseException {
		this.inizializza();
	}

	private void inizializza() throws ParseException {
		this.chiave = 0;
		this.setCognome("");
		this.setNome("");
		this.setMatricola("");
		this.setDataNascita(null);
		this.setIndirizzo("");
		this.setComune("");
		this.setProvincia("");
		this.setNazione("");
		this.setTelefono("");
		this.oggettoSet = new StudenteSet();
	}

	public Studente(int chiave) throws ParseException {
		this.inizializza(chiave);
	}

	private void inizializza(int chiave) throws ParseException {
		this.oggettoSet = new StudenteSet(chiave);
		this.chiave = chiave;
		this.setCognome(this.oggettoSet.Cognome);
		this.setNome(this.oggettoSet.Nome);
		this.setMatricola(this.oggettoSet.Matricola);
		this.setDataNascita(new Date(this.oggettoSet.DataNascita.getTime()));
		this.setIndirizzo(this.oggettoSet.Indirizzo);
		this.setComune(this.oggettoSet.Comune);
		this.setProvincia(this.oggettoSet.Provincia);
		this.setNazione(this.oggettoSet.Nazione);
		this.setTelefono(this.oggettoSet.Telefono);
	}

	// Sezione Metodi di Istanza
	public void salva() {
		this.oggettoSet.Cognome = this.getCognome();
		this.oggettoSet.Nome = this.getNome();
		this.oggettoSet.Matricola = this.getMatricola();
		this.oggettoSet.DataNascita = new java.sql.Date(this.getDataNascita().getTime());
		this.oggettoSet.Indirizzo = this.getIndirizzo();
		this.oggettoSet.Comune = this.getComune();
		this.oggettoSet.Provincia = this.getProvincia();
		this.oggettoSet.Nazione = this.getNazione();
		this.oggettoSet.Telefono = this.getTelefono();
		if (this.chiave == 0)
			this.oggettoSet.inserisci();
		else
			this.oggettoSet.aggiorna();
	}

	public void elimina() {
		this.oggettoSet.elimina();
	}

	// Sezione Metodi di Classe
	public static ArrayList<Studente> elenco() throws ParseException {
		ArrayList<Studente> elenco = new ArrayList<Studente>();
		ArrayList<Integer> chiavi = StudenteSet.listachiavi();

		for (int i = 0; i < chiavi.size(); i++)
			elenco.add(new Studente(chiavi.get(i)));

		return elenco;
	}
}
