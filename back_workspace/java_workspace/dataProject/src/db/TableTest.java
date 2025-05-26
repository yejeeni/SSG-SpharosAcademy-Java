package db;

import javax.swing.JFrame;
import javax.swing.JTable;

public class TableTest extends JFrame{
	JTable table;
	
	public TableTest() {
		table = new JTable(14, 7);
		
		add(table);
		
		setSize(500, 550);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new TableTest();
	}
}
