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
@Table(name = "HB_PRESTITO")
public class Prestito 
{
	public static final String PROPERTY_id = "id";
	public static final String PROPERTY_iddestinatario = "iddestinatario";
	public static final String PROPERTY_oLibro = "idlibro";
	public static final String PROPERTY_destinatario = "destinatario";
	public static final String PROPERTY_dataInizio = "dataInizio";
	public static final String PROPERTY_dataFine = "dataFine";
	public static final String PROPERTY_dataRestituzione = "dataRestituzione";
	
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "iddestinatario")				
	private int iddestinatario;
	
	@JoinColumn(name = "idlibro")
	@ManyToOne
	private Copia oCopia;
	
	@Column(name = "destinatario")
	private String destinatario;
	
	@Column(name = "dataInizio")
	private	Date dataInizio;
	
	@Column(name = "dataFine")
	private Date dataFine;
	
	@Column(name = "dataRestituzione")
	private Date dataRestituzione;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIddestinatario() {
		return iddestinatario;
	}

	public void setIddestinatario(int iddestinatario) {
		this.iddestinatario = iddestinatario;
	}

	public Copia getoCopia() 
	{
		return oCopia;    // da modificare
	}

	public void setoCopia(Copia oCopia) {
		this.oCopia = oCopia;  
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataFine() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public Date getDataRestituzione() {
		return dataRestituzione;
	}

	public void setDataRestituzione(Date dataRestituzione) {
		this.dataRestituzione = dataRestituzione;
	}
	
	
}