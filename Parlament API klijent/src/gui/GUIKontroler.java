package gui;

import java.awt.EventQueue;

import model.Parlament;

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
}
