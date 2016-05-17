package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JTextArea;
import javax.swing.JButton;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
			btnGetMembers.setPreferredSize(new Dimension(120, 25));
		}
		return btnGetMembers;
	}
	private JButton getBtnFillTable() {
		if (btnFillTable == null) {
			btnFillTable = new JButton("Fill table");
			btnFillTable.setPreferredSize(new Dimension(120, 25));
		}
		return btnFillTable;
	}
	private JButton getBtnUpdateMembers() {
		if (btnUpdateMembers == null) {
			btnUpdateMembers = new JButton("Update members");
			btnUpdateMembers.setPreferredSize(new Dimension(120, 25));
		}
		return btnUpdateMembers;
	}
}
