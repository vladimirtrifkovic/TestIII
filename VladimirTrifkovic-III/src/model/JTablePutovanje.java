package model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class JTablePutovanje extends AbstractTableModel {
	ArrayList<Putovanje> putovanje;

	public JTablePutovanje(ArrayList<Putovanje> putovanje) {
		super();
		this.putovanje = putovanje;
	}

	@Override
	public int getRowCount() {
		return putovanje.size();
	}

	@Override
	public int getColumnCount() {
		return 9;
	}

	@Override
	public Object getValueAt(int r, int c) {
		Putovanje p = putovanje.get(r);
		switch(c) {
		case 0: return p.getIdPutovanja();
		case 1: return p.getK().getIme();
		case 2: return p.getK().getPrezime();
		case 3: return p.getD().getNaziv();
		case 4: return p.getVremePolaska();
		case 5: return p.getDatumPovratka();
		case 6: return p.getDuzinaPuta();
		case 7: return p.getCena();
		case 8: return p.getP().getNazivPrevoza();
		default: return "GRESKA";
		}
	}
	
	@Override
	public String getColumnName(int c) {
		switch(c) {
		case 0: return "PID";
		case 1: return "IME";
		case 2: return "PREZIME";
		case 3: return "DESTINACIJA";
		case 4: return "POZAZAK";
		case 5: return "POVRATAK";
		case 6: return "DALJINA";
		case 7: return "CENA";
		case 8: return "PREVOZ";
		default: return "GRESKA";
		}
	}
	
//	@Override
//	public boolean isCellEditable(int r, int c) {
//		switch(c) {
//		case 0: return false;
//		case 1: return false;
//		case 2: return false;
//		case 3: return false;
//		case 4: return true;
//		case 5: return true;
//		case 6: return true;
//		case 7: return true;
//		case 8: return false;
//		default: return false;
//		}
//	}

	public ArrayList<Putovanje> getPutovanje() {
		return putovanje;
	}
	
	

}
