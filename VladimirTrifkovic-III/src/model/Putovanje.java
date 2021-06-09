package model;

import java.util.Date;

public class Putovanje {
	private int idPutovanja;
	private Korisnik k;
	private Destinacija d;
	private Date vremePolaska;
	private Date datumPovratka;
	private int duzinaPuta;
	private double cena;
	private VrstaPrevoza p;
	
	public Putovanje() {
		super();
	}

	public Putovanje(int idPutovanja, Korisnik k, Destinacija d, Date vremePolaska,
			Date datumPovratka, int duzinaPuta, double cena, VrstaPrevoza p) {
		super();
		this.idPutovanja = idPutovanja;
		this.k = k;
		this.d = d;
		this.vremePolaska = vremePolaska;
		this.datumPovratka = datumPovratka;
		this.duzinaPuta = duzinaPuta;
		this.cena = cena;
		this.p = p;
	}

	public int getIdPutovanja() {
		return idPutovanja;
	}

	public void setIdPutovanja(int idPutovanja) {
		this.idPutovanja = idPutovanja;
	}

	public Korisnik getK() {
		return k;
	}

	public void setK(Korisnik k) {
		this.k = k;
	}

	public Destinacija getD() {
		return d;
	}

	public void setD(Destinacija d) {
		this.d = d;
	}

	public Date getVremePolaska() {
		return vremePolaska;
	}

	public void setVremePolaska(Date vremePolaska) {
		this.vremePolaska = vremePolaska;
	}

	public Date getDatumPovratka() {
		return datumPovratka;
	}

	public void setDatumPovratka(Date datumPovratka) {
		this.datumPovratka = datumPovratka;
	}

	public int getDuzinaPuta() {
		return duzinaPuta;
	}

	public void setDuzinaPuta(int duzinaPuta) {
		this.duzinaPuta = duzinaPuta;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}

	public VrstaPrevoza getP() {
		return p;
	}

	public void setP(VrstaPrevoza p) {
		this.p = p;
	}

	@Override
	public String toString() {
		return "Putovanje [idPutovanja=" + idPutovanja + ", k=" + k + ", d=" + d + ", vremePolaska=" + vremePolaska
				+ ", datumPovratka=" + datumPovratka + ", duzinaPuta=" + duzinaPuta + ", cena=" + cena + ", p=" + p
				+ "]";
	}
	
}
