package model.session;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "HB_RichiestaAcquisto")
public class RichiestaAcquisto 
{
	public static final String PROPERTY_id = "id";
	public static final String PROPERTY_oProfessore = "idprofessore";
	public static final String PROPERTY_oLibro = "idlibro";
	public static final String PROPERTY_oAutore = "idautore";
	public static final String PROPERTY_oGenere = "idgenere";
	public static final String PROPERTY_titolo = "titolo";
	public static final String PROPERTY_stato = "stato";
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@JoinColumn(name =  "idprofessore")
	@ManyToOne
	private Professore oProfessore;
	
	@JoinColumn(name = "idlibro")				
	@ManyToOne
	private Libro oLibro;

	@JoinColumn(name = "idautore")
	@ManyToOne
	private Autore oAutore;
	
	@JoinColumn(name = "idgenere")
	@ManyToOne
	private Genere oGenere; 
	
	@Column(name = "titolo")
	private String titolo;
	
	@Column(name = "stato")
	private String stato;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Professore getoProfessore() {
		return oProfessore;
	}

	public void setoProfessore(Professore oProfessore) {
		this.oProfessore = oProfessore;
	}

	public Libro getoLibro() {
		return oLibro;
	}

	public void setoLibro(Libro oLibro) {
		this.oLibro = oLibro;
	}

	public Autore getoAutore() {
		return oAutore;
	}

	public void setoAutore(Autore oAutore) {
		this.oAutore = oAutore;
	}

	public Genere getoGenere() {
		return oGenere;
	}

	public void setoGenere(Genere oGenere) {
		this.oGenere = oGenere;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}


}