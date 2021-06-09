package control;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.Dao;
import model.Destinacija;
import model.Korisnik;
import model.Putovanje;

public class Controler {
	private static Controler con;
	
	private Controler() {}
	
	public static Controler getInstanceOf() {
		if(con == null) {
			con = new Controler();
		}
		return con;
	}
	
	public ArrayList<Putovanje> vratiPutovanje() throws ClassNotFoundException, SQLException{
		return Dao.getInstance().putovanje();
	}
	
	public ArrayList<Korisnik> vratiListuKorisnika() throws ClassNotFoundException, SQLException{
		return Dao.getInstance().putnik();
	}
	
	public void insertPutovanja(Putovanje p) throws ClassNotFoundException, SQLException {
		Dao.getInstance().insertPutovanje(p);
	}

	public ArrayList<Destinacija> vratiuDestinacije() throws ClassNotFoundException, SQLException {
		return Dao.getInstance().destinacija();
	}
	
	public void deletePutovanje(int id) throws ClassNotFoundException, SQLException {
		Dao.getInstance().brisanjePutovanja(id);
	}
	
	public int insertPutovanjeGetID(Putovanje p) throws ClassNotFoundException, SQLException {
		return Dao.getInstance().putovanjeInsertedID(p);
	}
}
