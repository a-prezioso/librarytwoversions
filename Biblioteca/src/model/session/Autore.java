package model.session;

import java.util.ArrayList;

import model.entity.AutoreSet;

public class Autore {
	// sezione attributi
	private int chiave;
	private String cognome;
	private String nome;
	private AutoreSet oggettoSet;

	// sezione metodi lettura e scrittura
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

	public int getChiave() {
		return chiave;
	}

	// sezione costruttori
	public Autore(int chiave) {
		this.inizializza(chiave);
	}

	private void inizializza(int chiave) {
		this.oggettoSet = new AutoreSet(chiave);
		this.chiave = chiave;
		this.setNome(this.oggettoSet.Nome);
		this.setCognome(this.oggettoSet.Cognome);
	}

	public Autore() {
		this.inizializza();
	}

	private void inizializza() {
		this.chiave = 0;
		this.setNome("");
		this.setCognome("");
		this.oggettoSet = new AutoreSet();
	}

	// sezione metodi di istanza
	public void salva() {
		this.oggettoSet.Nome = this.getNome();
		this.oggettoSet.Cognome = this.getCognome();
		if (this.chiave == 0)
			this.oggettoSet.inserisci();
		else
			this.oggettoSet.aggiorna();
	}

	public void elimina() {
		this.oggettoSet.elimina();
	}

	public boolean checkAutore() {
		return this.oggettoSet.checkLibroAutore();
	}

	// sezione metodi di classe
	public static ArrayList<Autore> elenco() {
		ArrayList<Autore> elenco = new ArrayList<Autore>();
		ArrayList<Integer> chiavi = AutoreSet.listachiavi();
		for (int i = 0; i < chiavi.size(); i++)
			elenco.add(new Autore(chiavi.get(i)));
		return elenco;
	}

}