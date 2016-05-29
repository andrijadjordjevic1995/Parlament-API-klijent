package gui;

import java.awt.EventQueue;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import domain.Poslanik;
import gui.model.PoslanikTableModel;
import model.Parlament;
import util.ParlamentAPIKomunikacija;

public class GUIKontroler {
	private static GUIParlament glavniProzor;
	private static Parlament parlament;
	private static ParlamentAPIKomunikacija komunikacija;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					parlament = new Parlament();
					komunikacija = new ParlamentAPIKomunikacija();
					glavniProzor = new GUIParlament();
					glavniProzor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static boolean vratiPoslanike() {
		LinkedList<Poslanik> poslanici = (LinkedList<Poslanik>)komunikacija.vratiPoslanike();
		
		JsonArray poslaniciJson = new JsonArray();
		for (Poslanik poslanik : poslanici) {
			JsonObject p = new JsonObject();
			
			p.addProperty("id", poslanik.getId());
			p.addProperty("ime", poslanik.getIme());
			p.addProperty("prezime", poslanik.getPrezime());
			if(poslanik.getDatumRodjenja() != null)
				p.addProperty("datumRodjenja", komunikacija.sdf.format(poslanik.getDatumRodjenja()));
			
			poslaniciJson.add(p);
			
		}
		
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("data/serviceMembers.json")))){
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			out.println(gson.toJson(poslaniciJson));
			out.close();
			return true;
			
		}catch(Exception e){
			return false;
		}
		
	}
	public static void izadji() {
		int povratna = JOptionPane.showConfirmDialog(glavniProzor.getContentPane(),
				"Da li zelite da izadjete iz programa?", "Izlazak", JOptionPane.YES_NO_CANCEL_OPTION);

		if (povratna == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
		
	}
	public static boolean ucitajPoslanike() {
		FileReader reader;
		try {
			reader = new FileReader("data/serviceMembers.json");
			Gson gson = new GsonBuilder().create();
			
			JsonArray poslanici = gson.fromJson(reader, JsonArray.class);
			
			LinkedList<Poslanik> poslaniciLista = komunikacija.parseMembers(poslanici);

			glavniProzor.osveziTabelu(poslaniciLista);
			return true;
		} catch (FileNotFoundException e) {
			return false;
		}
		
	}
}
