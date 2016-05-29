package gui.model;

import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

import domain.Poslanik;
import util.ParlamentAPIKomunikacija;

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
	
	public List<Poslanik> vratiSvePoslanike(){
		return poslanici;
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
			Date d = p.getDatumRodjenja();
			if(d != null)
				return ParlamentAPIKomunikacija.sdf.format(d);
			else
				return null;
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
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return (columnIndex > 0)? true : false;
	}
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if(isCellEditable(rowIndex, columnIndex)){
			Poslanik p = poslanici.get(rowIndex);
			switch(columnIndex){
			case 1:
				String val = (String)aValue;
				if(!(val.isEmpty()))
					p.setIme(val);
				else
					JOptionPane.showMessageDialog(null, "Ime ne sme ostati prazno");
				break;
			case 2:
				String val1 = (String)aValue;
				if(!(val1.isEmpty()))
					p.setPrezime(val1);
				else
					JOptionPane.showMessageDialog(null, "Prezime ne sme ostati prazno");
				break;
			case 3:
				try {
					Date d = ParlamentAPIKomunikacija.sdf.parse((String)aValue);
					p.setDatumRodjenja(d);
				} catch (ParseException e) {
					JOptionPane.showMessageDialog(null, "Unesite datum u formatu dd.MM.yyyy");
				}
				
			}
		}
	}
}
