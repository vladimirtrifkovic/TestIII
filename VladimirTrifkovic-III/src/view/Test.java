package view;

import java.sql.SQLException;
import java.util.ArrayList;

import control.Controler;
import model.Putovanje;

public class Test {

	public static void main(String[] args) {
		
		try {
//			ArrayList<Korisnik> korisnik = Controler.getInstanceOf().vratiListuKorisnika();
//			System.out.println(korisnik.toString());
			ArrayList<Putovanje> putovanje = Controler.getInstanceOf().vratiPutovanje();
			System.out.println(putovanje.toString());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("test");
	}
	

}
