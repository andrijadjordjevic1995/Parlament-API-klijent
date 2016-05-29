package gui;

import java.awt.EventQueue;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import domain.Poslanik;
import model.Parlament;
import util.ParlamentAPIKomunikacija;

public class GUIKontroler {
	private static GUIParlament glavniProzor;
	private static Parlament parlament;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					parlament = new Parlament();
					glavniProzor = new GUIParlament();
					glavniProzor.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static boolean vratiPoslanike() {
		LinkedList<Poslanik> poslanici = (LinkedList<Poslanik>)ParlamentAPIKomunikacija.vratiPoslanike();
		
		JsonArray poslaniciJson = new JsonArray();
		for (Poslanik poslanik : poslanici) {
			JsonObject p = new JsonObject();
			
			p.addProperty("id", poslanik.getId());
			p.addProperty("ime", poslanik.getIme());
			p.addProperty("prezime", poslanik.getPrezime());
			p.addProperty("datumRodjenja", ParlamentAPIKomunikacija.sdf.format(poslanik.getDatumRodjenja()));
			
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
}
