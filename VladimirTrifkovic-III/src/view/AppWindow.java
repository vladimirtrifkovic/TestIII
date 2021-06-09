package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import control.Controler;
import model.Destinacija;
import model.JTablePutovanje;
import model.Korisnik;
import model.Putovanje;
import model.VrstaPrevoza;

import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle.Control;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JDateChooser;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Color;

public class AppWindow {

	private JFrame frame;
	private JTextField txtKorisnickoIme;
	private JPasswordField passwordField;
	private ArrayList<Korisnik> korisnik;
	private JPanel panel_1;
	private JPanel panel;
	private JTextField txtDuzinaPuta;
	private JTextField txtCena;
	private JTable table;
	private ArrayList<Putovanje> listaPutovanja;
	private JDateChooser dateDatumPovratka;
	private JDateChooser dateDatumPolaska;
	private ButtonGroup buttonGroup;
	private JRadioButton rdbAvion = new JRadioButton("AVION");
	private JRadioButton rdbAutobus = new JRadioButton("AUTOBUS");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AppWindow window = new AppWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public AppWindow() {
		initialize();
	}

	private void initialize() {
		try {
			listaPutovanja = Controler.getInstanceOf().vratiPutovanje();
		} catch (ClassNotFoundException e3) {
			e3.printStackTrace();
		} catch (SQLException e3) {
			e3.printStackTrace();
		}
//		System.out.println(listaPutovanja.toString());

		try {
			korisnik = Controler.getInstanceOf().vratiListuKorisnika();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

		buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbAvion);
		buttonGroup.add(rdbAutobus);
		rdbAutobus.setSelected(true);

		frame = new JFrame();
		frame.setBounds(100, 100, 1019, 765);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));

		panel = new JPanel();
		frame.getContentPane().add(panel, "name_389192157333200");
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("LOGIN PAGE");
		lblNewLabel.setFont(new Font("Monospaced", Font.PLAIN, 54));
		lblNewLabel.setBounds(415, 166, 351, 92);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("KORISNICKO IME:");
		lblNewLabel_1.setFont(new Font("Monospaced", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(280, 304, 260, 33);
		panel.add(lblNewLabel_1);

		txtKorisnickoIme = new JTextField();
		txtKorisnickoIme.setFont(new Font("Monospaced", Font.PLAIN, 25));
		txtKorisnickoIme.setBounds(567, 302, 272, 36);
		panel.add(txtKorisnickoIme);
		txtKorisnickoIme.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Monospaced", Font.PLAIN, 25));
		passwordField.setBounds(567, 362, 272, 46);
		panel.add(passwordField);

		JLabel lblNewLabel_2 = new JLabel("LOZINKA:");
		lblNewLabel_2.setFont(new Font("Monospaced", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(280, 365, 244, 43);
		panel.add(lblNewLabel_2);

		JButton btnUlogujSe = new JButton("ULOGUJ SE");
		btnUlogujSe.setForeground(new Color(255, 255, 255));
		btnUlogujSe.setBackground(new Color(0, 100, 0));
		btnUlogujSe.setFont(new Font("Monospaced", Font.PLAIN, 25));
		btnUlogujSe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String korisnickoIme = txtKorisnickoIme.getText().toString().trim();
					String lozinka = passwordField.getText().toString().trim();
					for (Korisnik k : korisnik) {
						if (k.getKorisnickoIme().equals(korisnickoIme) && k.getLozinka().equals(lozinka)) {
							JOptionPane.showMessageDialog(null, "Uspesno ste se ulogovali ");
							panel.setVisible(false);
							panel_1.setVisible(true);
						}
					}
					clearLogingFields();
					if(panel.isVisible()) {
						JOptionPane.showMessageDialog(null, "Doslo je do greske, pokusajte ponovo!");
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "DOSLO JE DO GRESKE!");
				}
			}
		});
		
		btnUlogujSe.setBounds(415, 461, 260, 61);
		panel.add(btnUlogujSe);

		panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, "name_389206402831500");
		panel_1.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("IME I PREZIME:");
		lblNewLabel_3.setBounds(10, 10, 98, 13);
		panel_1.add(lblNewLabel_3);

		JComboBox combImePrezime = new JComboBox();
		try {
			combImePrezime.setModel(dcmImePrezime());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		combImePrezime.setBounds(166, 6, 170, 21);
		panel_1.add(combImePrezime);

		JLabel lblNewLabel_4 = new JLabel("DESTINACIJA:");
		lblNewLabel_4.setBounds(10, 47, 126, 13);
		panel_1.add(lblNewLabel_4);

		JComboBox combDestinacija = new JComboBox();
		try {
			combDestinacija.setModel(dcmDestinacija());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		combDestinacija.setBounds(164, 43, 172, 21);
		panel_1.add(combDestinacija);

		dateDatumPolaska = new JDateChooser();
		dateDatumPolaska.setBounds(166, 86, 170, 19);
		panel_1.add(dateDatumPolaska);

		JLabel lblNewLabel_5 = new JLabel("DATUM POLASKA:");
		lblNewLabel_5.setBounds(10, 86, 126, 13);
		panel_1.add(lblNewLabel_5);

		dateDatumPovratka = new JDateChooser();
		dateDatumPovratka.setBounds(166, 124, 170, 19);
		panel_1.add(dateDatumPovratka);

		JLabel lblNewLabel_6 = new JLabel("DATUM POVRATKA:");
		lblNewLabel_6.setBounds(10, 130, 126, 13);
		panel_1.add(lblNewLabel_6);

		txtDuzinaPuta = new JTextField();
		txtDuzinaPuta.setBounds(166, 163, 170, 19);
		panel_1.add(txtDuzinaPuta);
		txtDuzinaPuta.setColumns(10);

		JLabel lblNewLabel_7 = new JLabel("DUZINA PUTA:");
		lblNewLabel_7.setBounds(10, 166, 110, 13);
		panel_1.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel("CENA KARTE:");
		lblNewLabel_8.setBounds(10, 201, 126, 13);
		panel_1.add(lblNewLabel_8);

		txtCena = new JTextField();
		txtCena.setBounds(166, 198, 170, 19);
		panel_1.add(txtCena);
		txtCena.setColumns(10);

		rdbAvion.setBounds(60, 242, 103, 21);
		panel_1.add(rdbAvion);

		rdbAutobus.setBounds(164, 242, 110, 21);
		panel_1.add(rdbAutobus);

		JButton btnDodaj = new JButton("DODAJ");
		btnDodaj.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int korisnikId = combImePrezime.getSelectedIndex() + 1;
					
					String imeIprezime = combImePrezime.getSelectedItem().toString();
					String [] reci = imeIprezime.split(" ", 2);
					String imeKorisnika = reci[0];
					String prezimeKorisnika = reci[1];
					Korisnik k = new Korisnik();
					k.setId(korisnikId);
					k.setIme(imeKorisnika);
					k.setPrezime(prezimeKorisnika);
					
//					System.out.println("korisnik id " + korisnikId);
					int destinacijaId = combDestinacija.getSelectedIndex() + 1;
					Destinacija d = new Destinacija();
					d.setId(destinacijaId);
					String nazivDestinacije = combDestinacija.getSelectedItem().toString();
					d.setNaziv(nazivDestinacije);
					
					Date vremePolaska = dateDatumPolaska.getDate();
					Date datumPovratka = dateDatumPovratka.getDate();
					
										
					String duzinaPutaS = txtDuzinaPuta.getText().trim();
					int duzinaPuta = Integer.parseInt(duzinaPutaS);
					String cenaS = txtCena.getText().trim();
					
					Double cena = Double.parseDouble(cenaS);
					
					
					boolean avion = rdbAvion.isSelected();
					boolean autobus = rdbAutobus.isSelected();
					VrstaPrevoza v = new VrstaPrevoza();
					
					int pom = 0;
					if (avion) {
						pom = 2;
						v.setNazivPrevoza("Avion");
					}
					if (autobus) {
						pom = 1;
						v.setNazivPrevoza("Autobus");
					}
					v.setIdPrevoza(pom);
					
					

					if (!datumPovratka.before(vremePolaska) || pom != 0) {

						Putovanje put = new Putovanje(0, k, d, vremePolaska, datumPovratka, duzinaPuta, cena, v);
//						Controler.getInstanceOf().insertPutovanja(put);
						int idPutovanja = Controler.getInstanceOf().insertPutovanjeGetID(put);
						put.setIdPutovanja(idPutovanja);
						System.out.println("id putovanja je : " + idPutovanja);
						
						listaPutovanja.add(put);
						updateTable(listaPutovanja);

						JOptionPane.showMessageDialog(null, "USPENSO STE DODALI PUTOVANJE!");
					} else {
						JOptionPane.showMessageDialog(null, "UNJELI STE POGRESAN DATUM ILI IMATE NEPOPUNJENA POLJA!");
					}
					clearFields();
					rdbAutobus.setSelected(true);

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "DOSLO JE DO GRESKE PRILIKOM UNOSA PUTOVANJA! " + e2.getMessage());
				}
			}
		});

		btnDodaj.setBounds(381, 86, 85, 21);
		panel_1.add(btnDodaj);

		JButton btnPrikazi = new JButton("PRIKAZI");
		btnPrikazi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateTable(listaPutovanja);
			}
		});
		btnPrikazi.setBounds(381, 124, 85, 21);
		panel_1.add(btnPrikazi);

		JButton btNObrisi = new JButton("OBRISI");
		btNObrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTablePutovanje model = (JTablePutovanje) table.getModel();
				int brojIndexa = table.getSelectedRow();
				System.out.println("broj selektovanog reda " + brojIndexa);
				if (brojIndexa != -1) {
					int id = listaPutovanja.get(brojIndexa).getIdPutovanja();
					try {
						Controler.getInstanceOf().deletePutovanje(id);
					} catch (ClassNotFoundException e1) {
						e1.printStackTrace();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					listaPutovanja.remove(brojIndexa);
					updateTable(listaPutovanja);
				} else {
					JOptionPane.showMessageDialog(null, "SELEKTUJTE RED KOJI ZELITE DA IZBRISETE!");
				}
			}
		});
		btNObrisi.setBounds(381, 162, 85, 21);
		panel_1.add(btNObrisi);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 293, 983, 310);
		panel_1.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
	}

	DefaultComboBoxModel dcmImePrezime() throws ClassNotFoundException, SQLException {
		DefaultComboBoxModel pom = new DefaultComboBoxModel();
		ArrayList<Korisnik> listaKorisnika = Controler.getInstanceOf().vratiListuKorisnika();
		for (Korisnik k : listaKorisnika) {
			pom.addElement(k.getIme() + " " + k.getPrezime());
		}
		return pom;
	}

	DefaultComboBoxModel dcmDestinacija() throws ClassNotFoundException, SQLException {
		DefaultComboBoxModel pom = new DefaultComboBoxModel();
		ArrayList<Destinacija> listaDestinacija = Controler.getInstanceOf().vratiuDestinacije();
		for (Destinacija d : listaDestinacija) {
			pom.addElement(d.getNaziv());
		}
		return pom;
	}

	private void updateTable(ArrayList<Putovanje> l) {
		try {
			JTablePutovanje model = new JTablePutovanje(l);
			table.setModel(model);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "DOSLO JE DO GRESKE PRILIKOM UPDATE TABELE!");
		}
	}

	private void clearFields() {
		txtCena.setText("");
		txtDuzinaPuta.setText("");
		txtCena.setText("");
//		buttonGroup.clearSelection();
		dateDatumPolaska.setCalendar(null);
		dateDatumPovratka.setCalendar(null);
	}

	private void clearLogingFields() {
		txtKorisnickoIme.setText("");
		passwordField.setText("");
	}

}
