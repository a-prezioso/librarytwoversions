package model.session;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "HB_Studente")
public class Studente {
	public static final String PROPERTY_id = "id";
	public static final String PROPERTY_cognome = "cognome";
	public static final String PROPERTY_nome = "nome";
	public static final String PROPERTY_matricola = "matricola";
	public static final String PROPERTY_datanascita = "datanascita";
	public static final String PROPERTY_indirizzo = "indirizzo";
	public static final String PROPERTY_comune = "comune";
	public static final String PROPERTY_provincia = "provincia";
	public static final String PROPERTY_nazione = "nazione";
	public static final String PROPERTY_telefono = "telefono";

	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "cognome")
	private String cognome;

	@Column(name = "nome")
	private String nome;

	@Column(name = "matricola")
	private String matricola;

	@Column(name = "datanascita")
	private String datanascita;

	@Column(name = "indirizzo")
	private String indirizzo;

	@Column(name = "comune")
	private String comune;

	@Column(name = "provincia")
	private String provincia;

	@Column(name = "nazione")
	private String nazione;

	@Column(name = "telefono")
	private String telefono;

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

	public String getDatanascita() {
		return datanascita;
	}

	public void setDatanascita(String datanascita) {
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Studente() 
	 {
	  this.inizializza();
	 }

	 private void inizializza() 
	 {
	  this.id = 0;
	  this.cognome = "";
	  this.nome = "";
	  this.matricola = "";
	  this.datanascita = null;
	  this.indirizzo = "";
	  this.comune = "";
	  this.provincia = "";
	  this.nazione = "";
	  this.telefono = "";
	 }

}
