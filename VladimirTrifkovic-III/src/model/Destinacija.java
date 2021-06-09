package model;

public class Destinacija {
	private  int id;
	private String naziv;
	
	public Destinacija() {
		super();
	}

	public Destinacija(int id, String naziv) {
		super();
		this.id = id;
		this.naziv = naziv;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	@Override
	public String toString() {
		return "Destinacija [id=" + id + ", naziv=" + naziv + "]";
	}
}
