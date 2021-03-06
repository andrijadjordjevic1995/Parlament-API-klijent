package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import domain.Poslanik;
import gui.model.PoslanikTableModel;


import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.awt.event.ActionEvent;

public class GUIParlament extends JFrame {

	private JPanel contentPane;
	private JScrollPane scrollPane;
	private JTable table;
	private JScrollPane scrollPaneSouth;
	private JTextArea textAreaSouth;
	private JPanel panel;
	private JButton btnGetMembers;
	private JButton btnFillTable;
	private JButton btnUpdateMembers;

	

	/**
	 * Create the frame.
	 */
	public GUIParlament() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				GUIKontroler.izadji();
			}

		});
		setTitle("Parlament Members");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(getScrollPane(), BorderLayout.CENTER);
		contentPane.add(getScrollPaneSouth(), BorderLayout.SOUTH);
		contentPane.add(getPanel(), BorderLayout.EAST);
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTable());
		}
		return scrollPane;
	}
	private JTable getTable() {
		if (table == null) {
			table = new JTable();
			PoslanikTableModel tableModel = new PoslanikTableModel(null);
			table.setModel(tableModel);
		}
		return table;
	}
	private JScrollPane getScrollPaneSouth() {
		if (scrollPaneSouth == null) {
			scrollPaneSouth = new JScrollPane();
			scrollPaneSouth.setPreferredSize(new Dimension(2, 70));
			scrollPaneSouth.setBorder(new TitledBorder(null, "Status", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
			scrollPaneSouth.setViewportView(getTextAreaSouth());
		}
		return scrollPaneSouth;
	}
	private JTextArea getTextAreaSouth() {
		if (textAreaSouth == null) {
			textAreaSouth = new JTextArea();
		}
		return textAreaSouth;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setPreferredSize(new Dimension(140, 10));
			panel.add(getBtnGetMembers());
			panel.add(getBtnFillTable());
			panel.add(getBtnUpdateMembers());
		}
		return panel;
	}
	private JButton getBtnGetMembers() {
		if (btnGetMembers == null) {
			btnGetMembers = new JButton("Get members");
			btnGetMembers.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(GUIKontroler.vratiPoslanike()){
						textAreaSouth.setText(textAreaSouth.getText()+"Poslanici su "
								+ "preuzeti sa servisa\n");
					}else{
						textAreaSouth.setText(textAreaSouth.getText()+"Greska pri preuzimanju "
								+ "poslanika sa servisa\n");
					}
				}
			});
			btnGetMembers.setPreferredSize(new Dimension(120, 25));
		}
		return btnGetMembers;
	}
	private JButton getBtnFillTable() {
		if (btnFillTable == null) {
			btnFillTable = new JButton("Fill table");
			btnFillTable.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(GUIKontroler.ucitajPoslanike()){
						textAreaSouth.setText(textAreaSouth.getText()+"Tabela je popunjena podacima\n");
					}else{
						textAreaSouth.setText(textAreaSouth.getText()+"Greska pri popunjavanju tabele\n");
					}
					
				}
			});
			btnFillTable.setPreferredSize(new Dimension(120, 25));
		}
		return btnFillTable;
	}
	private JButton getBtnUpdateMembers() {
		if (btnUpdateMembers == null) {
			btnUpdateMembers = new JButton("Update members");
			btnUpdateMembers.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(GUIKontroler.dopuniPoslanike((LinkedList<Poslanik>)((PoslanikTableModel)table.getModel()).vratiSvePoslanike()))
						textAreaSouth.setText(textAreaSouth.getText()+"Izmenjeni podaci o poslanicima su sacuvani uspesno\n");
					else
						textAreaSouth.setText(textAreaSouth.getText()+"Greska pri cuvanju izmenjenih podataka o poslanicima\n");
				}
			});
			btnUpdateMembers.setPreferredSize(new Dimension(120, 25));
		}
		return btnUpdateMembers;
	}
	public void osveziTabelu(LinkedList<Poslanik> poslanici){
		PoslanikTableModel model = (PoslanikTableModel)table.getModel();
		model.osveziTabelu(poslanici);
	}
}
