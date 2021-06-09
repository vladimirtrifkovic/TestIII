package model;

public class VrstaPrevoza {
	private int idPrevoza;
	private String nazivPrevoza;
	
	public VrstaPrevoza() {
		super();
	}

	public VrstaPrevoza(int idPrevoza, String nazivPrevoza) {
		super();
		this.idPrevoza = idPrevoza;
		this.nazivPrevoza = nazivPrevoza;
	}

	public int getIdPrevoza() {
		return idPrevoza;
	}

	public void setIdPrevoza(int idPrevoza) {
		this.idPrevoza = idPrevoza;
	}

	public String getNazivPrevoza() {
		return nazivPrevoza;
	}

	public void setNazivPrevoza(String nazivPrevoza) {
		this.nazivPrevoza = nazivPrevoza;
	}

	@Override
	public String toString() {
		return "VrstaPrevoza [idPrevoza=" + idPrevoza + ", nazivPrevoza=" + nazivPrevoza + "]";
	}
	
}
