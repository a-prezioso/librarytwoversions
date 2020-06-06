package model.session;

import java.util.ArrayList;

import model.entity.GenereSet;

public class Genere {

	// attributi
	private int chiave;
	private String nome;
	private GenereSet oggettoSet;

	// metodi di lettura e scrittura
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getChiave() {
		return chiave;
	}

	// sezione costruttore
	public Genere() {
		this.inizializza();
	}

	private void inizializza() {
		this.chiave = 0;
		this.setNome("");
		this.oggettoSet = new GenereSet();
	}

	public Genere(int chiave) {
		this.inizializza(chiave);
	}

	private void inizializza(int chiave) {
		this.oggettoSet = new GenereSet(chiave);
		this.chiave = chiave;
		this.setNome(this.oggettoSet.Nome);
	}

	// metodi di istanza
	public void salva() {
		this.oggettoSet.Nome = this.getNome();
		if (this.chiave == 0)
			this.oggettoSet.inserisci();
		else
			this.oggettoSet.aggiorna();
	}

	public void elimina() {
		this.oggettoSet.elimina();
	}

	public boolean checkGenere()
	{
		return this.oggettoSet.checkLibroGenere();
	}
	
	
	// sezione metodi di classe
	public static ArrayList<Genere> elenco() {
		ArrayList<Genere> elenco = new ArrayList<Genere>();
		ArrayList<Integer> chiavi = GenereSet.listachiavi();
		for (int i = 0; i < chiavi.size(); i++)
			elenco.add(new Genere(chiavi.get(i)));
		return elenco;
	}

}
