package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import model.Destinacija;
import model.Korisnik;
import model.Putovanje;
import model.VrstaPrevoza;

public class Dao {
	private static Dao dao;
	
	private Dao() {};
	
	public static Dao getInstance() {
		if(dao == null) {
			dao = new Dao();
		}
		return dao;
	}
	
	private Statement statement = null;
	private Connection connect = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	private String PUTOVANJE = "SELECT pid, k.id_korisnika, k.ime, k.prezime, d.id_destinacije, d.naziv, vreme_polaska, \r\n"
			+ "datum_povratka, duzina_puta, cena_puta, pr.id_prevoza, pr.naziv \r\n"
			+ "FROM putovanje p JOIN korisnik k ON p.putnik_id = k.id_korisnika \r\n"
			+ "JOIN destinacija d ON p.destinacija_id = d.id_destinacije \r\n"
			+ "JOIN prevoz pr ON p.id_prevoz = pr.id_prevoza";
	
	private String BrisanjePutovanja = "DELETE FROM `putovanje` WHERE pid = ?";
	
	
	private String InsertPutovanje = "INSERT INTO `putovanje` (`putnik_id`, `destinacija_id`, `vreme_polaska`, `datum_povratka`, `duzina_puta`, `cena_puta`, `id_prevoz`) \r\n"
			+ "VALUES (?, ?, ?, ?, ?, ?, ?)";
	
	
	private void connect() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		connect = DriverManager.getConnection("jdbc:mysql://localhost/putovanje", "root", "");
	}
	
	public ArrayList<Korisnik> putnik() throws ClassNotFoundException, SQLException{
		ArrayList<Korisnik> pom = new ArrayList<Korisnik>();
		
		connect();
		preparedStatement = connect.prepareStatement("SELECT * FROM korisnik");
		preparedStatement.execute();
		resultSet = preparedStatement.getResultSet();
		
		while(resultSet.next()) {
			int id = resultSet.getInt("id_korisnika");
			String ime = resultSet.getString("ime");
			String prezime = resultSet.getString("prezime");
			String korisnickoIme = resultSet.getString("korisnicko_ime");
			String lozinka = resultSet.getString("lozinka");
			Korisnik k = new Korisnik(id, ime, prezime, korisnickoIme, lozinka);
			pom.add(k);
		}
		close();
		return pom;
	}
	
	public ArrayList<Destinacija> destinacija() throws ClassNotFoundException, SQLException{
		ArrayList<Destinacija> pom = new ArrayList<Destinacija>();
		connect();
		preparedStatement = connect.prepareStatement("SELECT * FROM `destinacija`");
		preparedStatement.execute();
		resultSet = preparedStatement.getResultSet();
		
		while(resultSet.next()) {
			int id = resultSet.getInt("id_destinacije");
			String naziv = resultSet.getString("naziv");
			Destinacija d = new Destinacija(id, naziv);
			pom.add(d);
		}
		close();
		return pom;
	}
	
	public ArrayList<VrstaPrevoza> prevoz() throws ClassNotFoundException, SQLException{
		ArrayList<VrstaPrevoza> pom = new ArrayList<VrstaPrevoza>();
		connect();
		preparedStatement = connect.prepareStatement("SELECT * FROM `prevoz`");
		preparedStatement.execute();
		resultSet = preparedStatement.getResultSet();
		
		while(resultSet.next()) {
			int id = resultSet.getInt("id_prevoza");
			String naziv = resultSet.getString("naziv");
			VrstaPrevoza p = new VrstaPrevoza(id, naziv);
			pom.add(p);
		}
		close();
		return pom;
	}
	
	public ArrayList<Putovanje> putovanje() throws ClassNotFoundException, SQLException{
		ArrayList<Putovanje> pom = new ArrayList<Putovanje>();
		connect();
		preparedStatement = connect.prepareStatement(PUTOVANJE);
		preparedStatement.execute();
		resultSet = preparedStatement.getResultSet();
		
		while(resultSet.next()) {
			int idPutovanja = resultSet.getInt("pid");
			
			int idKorisnika = resultSet.getInt("id_korisnika");
			String ime = resultSet.getString("ime");
			String prezime = resultSet.getString("prezime");
			Korisnik k = new Korisnik();
			k.setId(idKorisnika);
			k.setIme(ime);
			k.setPrezime(prezime);
			
			int idDestinacije = resultSet.getInt("id_destinacije");
			String naziv = resultSet.getString("naziv");
			Destinacija d = new Destinacija(idDestinacije, naziv);
			
			Date vremePolaska = resultSet.getDate("vreme_polaska");
			Date datumPovratka = resultSet.getDate("datum_povratka");
			int duzinaPuta = resultSet.getInt("duzina_puta");
			double cena = resultSet.getDouble("cena_puta");
			
			int idPrevoza = resultSet.getInt("id_prevoza");
			String nazivPrevoza = resultSet.getString(12);
			VrstaPrevoza vp = new VrstaPrevoza(idPrevoza, nazivPrevoza);
			
			Putovanje put = new Putovanje(idPutovanja, k, d, vremePolaska, datumPovratka, duzinaPuta, cena, vp);
			pom.add(put);
		}
		close();
		return pom;
	}
	
	public void insertPutovanje(Putovanje p) throws ClassNotFoundException, SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		String vremePolaska = sdf.format(p.getVremePolaska());
		String datumPovratka = sdf.format(p.getDatumPovratka());
		
		connect();
		preparedStatement = connect.prepareStatement(InsertPutovanje);
		preparedStatement.setInt(1, p.getK().getId());
		preparedStatement.setInt(2, p.getD().getId());
		preparedStatement.setString(3, vremePolaska);
		preparedStatement.setString(4, datumPovratka);
		preparedStatement.setInt(5, p.getDuzinaPuta());
		preparedStatement.setDouble(6, p.getCena());
		preparedStatement.setInt(7, p.getP().getIdPrevoza());
		preparedStatement.execute();
		
		close();
	}
	
	public void brisanjePutovanja(int id) throws ClassNotFoundException, SQLException {
		connect();
		preparedStatement = connect.prepareStatement(BrisanjePutovanja);
		preparedStatement.setInt(1, id);
		preparedStatement.execute();
		
		close();
	}
	
	public int putovanjeInsertedID(Putovanje p) throws ClassNotFoundException, SQLException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
		String vremePolaska = sdf.format(p.getVremePolaska());
		String datumPovratka = sdf.format(p.getDatumPovratka());
		
		connect();
		preparedStatement = connect.prepareStatement(InsertPutovanje,Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setInt(1, p.getK().getId());
		preparedStatement.setInt(2, p.getD().getId());
		preparedStatement.setString(3, vremePolaska);
		preparedStatement.setString(4, datumPovratka);
		preparedStatement.setInt(5, p.getDuzinaPuta());
		preparedStatement.setDouble(6, p.getCena());
		preparedStatement.setInt(7, p.getP().getIdPrevoza());
		preparedStatement.execute();
		
		resultSet =preparedStatement.getResultSet();
		ResultSet keys =preparedStatement.getGeneratedKeys();
		keys.next();
		int id = keys.getInt(1);
		
		close();
		return id;
	}
	
	
	
	
	
	
	
	private void close() {
		try {
			if(resultSet != null) {
				resultSet.close();
			}
			
			if(statement != null) {
				statement.close();
			}
			
			if(connect != null) {
				connect.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Doslo je do greske x " +e.getMessage());
		}
	}

}
