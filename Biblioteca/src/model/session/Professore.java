package model.session;

import java.util.ArrayList;

import model.entity.ProfessoreSet;

public class Professore {
	
	//sezione attributi
	private int chiave;
	private String nome;
	private String cognome;
	private String matricola;
	private ProfessoreSet oggettoSet;

	

	//sezione metodi di lettura e scrittura
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getMatricola() {
		return matricola;
	}
	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}
	public int getChiave() {
		return chiave;
	}
	
	//sezione costruttori
	public Professore(int chiave)
	{
		this.inizializza(chiave);
	}
	
	private void inizializza (int chiave)
	{
		this.oggettoSet = new ProfessoreSet (chiave);
		this.chiave = chiave;
		this.setNome(this.oggettoSet.Nome);
		this.setCognome(this.oggettoSet.Cognome);
		this.setMatricola(this.oggettoSet.Matricola);
	}

	public Professore()
	{
		this.inizializza();
	}
	
	private void inizializza()
	{
		this.chiave = 0;
		this.setNome("");
		this.setCognome("");
		this.setMatricola("");
		this.oggettoSet = new ProfessoreSet();
	}
	
	//sezione metodi di istanza
	public void salva()
	{
		this.oggettoSet.Nome = this.getNome();
		this.oggettoSet.Cognome = this.getCognome();
		this.oggettoSet.Matricola = this.getMatricola();
		if(this.chiave == 0)
			this.oggettoSet.inserisci();
		else
			this.oggettoSet.aggiorna();
	}
	
	
	public void elimina()
	{
		this.oggettoSet.elimina();
	}
	
	
	//sezione metodi di classe
	public static ArrayList<Professore> elenco()
	{
		ArrayList<Professore> elenco = new ArrayList<Professore>();
		ArrayList<Integer> chiavi = ProfessoreSet.listachiavi();
		for (int i = 0; i < chiavi.size(); i++)
			elenco.add(new Professore(chiavi.get(i)));
		return elenco;
	}

}
