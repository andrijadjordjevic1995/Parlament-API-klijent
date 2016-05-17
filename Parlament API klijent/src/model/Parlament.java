package model;

import java.util.LinkedList;

import domain.Poslanik;

public class Parlament {
	private LinkedList<Poslanik> poslanici;
	
	public Parlament() {
		poslanici = new LinkedList<Poslanik>();
	}
	public LinkedList<Poslanik> getPoslanici() {
		return poslanici;
	}

	public void setPoslanici(LinkedList<Poslanik> poslanici) {
		this.poslanici = poslanici;
	}
	
	
}
