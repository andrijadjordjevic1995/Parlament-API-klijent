package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import domain.Poslanik;

public class ParlamentAPIKomunikacija {
	
	private static final String URL = "http://147.91.128.71:9090/parlament/api/members";
	private static final SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");
	
	//kopirao metode sa vezbe ali ih detaljno proucio
	public static List<Poslanik> vratiPoslanike(){
		try {
			String result = sendGet(URL);

			Gson gson = new GsonBuilder().create();
			JsonArray poslaniciJson = gson.fromJson(result, JsonArray.class);

			LinkedList<Poslanik> poslanici = new LinkedList<Poslanik>();

			for (int i = 0; i < poslaniciJson.size(); i++) {
				JsonObject poslanikJson = (JsonObject) poslaniciJson.get(i);

				Poslanik poslanik = new Poslanik();
				poslanik.setId(poslanikJson.get("id").getAsInt());
				poslanik.setIme(poslanikJson.get("name").getAsString());
				poslanik.setPrezime(poslanikJson.get("lastName").getAsString());
				if (poslanikJson.get("birthDate") != null)
					poslanik.setDatumRodjenja(sdf.parse(poslanikJson.get("birthDate").getAsString()));

				poslanici.add(poslanik);
			}

			return poslanici;

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new LinkedList<Poslanik>();
	}
	
	private static String sendGet(String url) {
		URL obj;
		String response = "";
		try {
			obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			con.setRequestMethod("GET");

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

			boolean endReading = false;
			

			while (!endReading) {
				String s = in.readLine();

				if (s != null) {
					response += s;
				} else {
					endReading = true;
				}
			}
			in.close();

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response.toString();
	}
	
}
