package model.session;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "HB_LIBRO")
public class Libro 
{
	public static final String PROPERTY_id = "id";
	public static final String PROPERTY_oAutore = "idautore";
	public static final String PROPERTY_oGenere = "idgenere";
	public static final String PROPERTY_oCasaEditrice = "idcasaeditrice";
	public static final String PROPERTY_titolo = "titolo";
	
	@Column(name = "id")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "idautore")
	private Autore oAutore;
	
	@ManyToOne
	@JoinColumn(name = "idgenere")
	private Genere oGenere;
	
	@ManyToOne
	@JoinColumn(name = "idcasaeditrice")
	private CasaEditrice oCasaEditrice;
	
	@Column(name = "titolo")
	private String titolo;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "oCopiaLibro")
	@Fetch(FetchMode.SUBSELECT)
	private List<Copia> listaCopie = new ArrayList<Copia>();
	
	

//	private ArrayList<Copia> copiedaeliminare = new ArrayList<Copia>();
//	
//	
//	public ArrayList<Copia> getCopiedaeliminare() {
//		return copiedaeliminare;
//	}
//
//	public void setCopiedaeliminare(ArrayList<Copia> copiedaeliminare) {
//		this.copiedaeliminare = copiedaeliminare;
//	}

	public List<Copia> getListaCopie() {
		return listaCopie;
	}

	public void setListaCopie(List<Copia> listaCopie) {
		this.listaCopie = listaCopie;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Autore getoAutore() 
	{
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

	public CasaEditrice getoCasaEditrice() {
		return oCasaEditrice;
	}

	public void setoCasaEditrice(CasaEditrice oCasaEditrice) {
		this.oCasaEditrice = oCasaEditrice;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	
	public Libro() 
	{
		this.inizializza();
	}

	private void inizializza() 
	{
		this.id = 0;
		this.oAutore = null;
		this.oCasaEditrice = null;
		this.oGenere = null;
		this.titolo = "";
	}
}