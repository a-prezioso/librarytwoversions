package model.session;

import java.util.ArrayList;

import model.entity.CasaEditriceSet;

public class CasaEditrice {
	
	//sezione attributi
	private int chiave;
	private String nome;
	private CasaEditriceSet oggettoSet;

	

	
	//sezione metodi lettura e scrittura
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getChiave() {
		return chiave;
	}
	
	//sezione costruttori
	
	public CasaEditrice()
	{
		this.inizializza();
	}
	
	private void inizializza ()
	{
		this.chiave = 0;
		this.setNome("");
		this.oggettoSet = new CasaEditriceSet();
	}
	
	
	public CasaEditrice(int chiave)
	{
		this.inizializza(chiave);
	}
	
	private void inizializza(int chiave)
	{
		this.oggettoSet = new CasaEditriceSet (chiave);
		this.chiave = chiave;
		this.setNome(this.oggettoSet.Nome);
	}
	
	
	
	//sezione metodi di istanza
	public void salva() 
	{
		this.oggettoSet.Nome = this.getNome();
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
	public static ArrayList<CasaEditrice> elenco ()
	{
		ArrayList<CasaEditrice> elenco = new ArrayList<CasaEditrice>();
		ArrayList<Integer> chiavi = CasaEditriceSet.listachiavi();
		for (int i = 0; i < chiavi.size(); i++)
			elenco.add(new CasaEditrice(chiavi.get(i)));
		return elenco;
	}
	public boolean checkCasaEditrice() 
	{
		return this.oggettoSet.checkLibroCasaEditrice();
	}


}
