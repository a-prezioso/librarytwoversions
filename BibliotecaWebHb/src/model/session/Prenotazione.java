package model.session;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "HB_PRENOTAZIONE")
public class Prenotazione 
{
	public static final String PROPERTY_id = "id";
	public static final String PROPERTY_oLibro = "idlibro";
	public static final String PROPERTY_idrichiedente = "idrichiedente";
	public static final String PROPERTY_richiedente = "richiedente";
	public static final String PROPERTY_data = "data";
	public static final String PROPERTY_evasa = "evasa";
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@JoinColumn(name =  "idlibro")
	@ManyToOne
	private Libro oLibro;
	
	@Column(name = "idrichiedente")				
	private int idrichiedente;

	@Column(name = "richiedente")
	private String richiedente;
	
	@Column(name = "data")
	private Date data; 
	
	@Column(name = "evasa")
	private String evasa;
	
	
	public int getId() 
	{
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRichiedente() {
		return richiedente;
	}
	public void setRichiedente(String richiedente) {
		this.richiedente = richiedente;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getEvasa() {
		return evasa;
	}
	public void setEvasa(String evasa) {
		this.evasa = evasa;
	}
	public Libro getoLibro() {
		return oLibro;
	}
	public void setoLibro(Libro oLibro) {
		this.oLibro = oLibro;
	}
	public int getIdrichiedente() {
		return idrichiedente;
	}
	public void setIdrichiedente(int idrichiedente) {
		this.idrichiedente = idrichiedente;
	}


}