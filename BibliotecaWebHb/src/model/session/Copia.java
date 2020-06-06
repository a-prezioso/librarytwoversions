package model.session;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "HB_COPIA")
public class Copia
{	
	public static final String PROPERTY_id = "idcopia";
	public static final String PROPERTY_idlibro = "oCopiaLibro";
	public static final String PROPERTY_stato = "stato";
	public static final String PROPERTY_dataAcquisto = "dataAcquisto";
	public static final String PROPERTY_seriale = "seriale";
	
	@Column(name = "idcopia")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idcopia;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idlibro")
	private Libro oCopiaLibro;
	
	@Column(name = "stato")
	private String stato;
	
	@Column(name = "dataAcquisto")
	private Date dataAcquisto;
	
	@Column(name = "seriale")
	private String seriale;



	public int getIdcopia() {
		return idcopia;
	}

	public void setIdcopia(int idcopia) {
		this.idcopia = idcopia;
	}

	public Libro getoCopiaLibro() {
		return oCopiaLibro;
	}

	public void setoCopiaLibro(Libro oCopiaLibro) {
		this.oCopiaLibro = oCopiaLibro;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public Date getDataAcquisto() {
		return dataAcquisto;
	}

	public void setDataAcquisto(Date dataAcquisto) {
		this.dataAcquisto = dataAcquisto;
	}

	public String getSeriale() {
		return seriale;
	}

	public void setSeriale(String seriale) {
		this.seriale = seriale;
	}
	
	public Copia(Libro oLibro)
	{
		this.inizializza(oLibro);
	}
	
	private void inizializza(Libro oLibro)
	{
		this.idcopia = 0;
		this.setoCopiaLibro(oLibro);
		this.stato = "";
		this.dataAcquisto = null;
		this.seriale = "";				
	}
		
	public Copia()
	{
		this.inizializza();
	}
	
	private void inizializza()
	{
		this.idcopia = 0;
//		this.oCopiaLibro = oLib.findById(1);
		this.setoCopiaLibro(getoCopiaLibro());
		this.stato = "D";
		this.dataAcquisto = null;
		this.seriale = "";				
	}
}
