package gui.model;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import domain.Poslanik;

public class PoslanikTableModel extends AbstractTableModel {
	private final String[] kolone = new String[] { "ID", "Name", "Last name", "Birth date" };
	private List<Poslanik> poslanici;

	public PoslanikTableModel(List<Poslanik> poslanici) {
		if (poslanici == null) {
			this.poslanici = new LinkedList<Poslanik>();
		} else {
			this.poslanici = poslanici;
		}
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return kolone.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return poslanici.size();
	}

	@Override
	public Object getValueAt(int red, int kolona) {
		Poslanik p = poslanici.get(red);
		
		switch (kolona) {
		case 0:
			return new Integer(p.getId());
		case 1:
			return p.getIme();
		case 2:
			return p.getPrezime();
		case 3:
			return p.getDatumRodjenja();
		default:
			return "/";
		}
		
	}
	
	@Override
	public String getColumnName(int column) {
		return kolone[column];
	}
	
	public void osveziTabelu(List<Poslanik> poslanici){
		this.poslanici = poslanici;
		fireTableDataChanged();
	}
}
