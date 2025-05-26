package db;

import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TableTest extends JFrame{
	JTable table;
	JScrollPane scroll;
	
	// 데이터에 사용될 2차원 배열
	String[][] data = {
			{"java", "python", "c#", "javascript", "R"},
			{"jsp", "servlet", "React", "vue", "Node.js"},
			{"oracle", "mysql", "mariadb", "db2", "mssql"},
	};
	
	// 컬럼에 사용될 1차원 배열
	String[] columns = {
			"과목1", "과목2", "과목3", "과목4", "과목5" 
	};

	public TableTest() {
		table = new JTable(data, columns);
		scroll = new JScrollPane(table);
		
		// BorderLayOut에 붙여버리면 테이블의 컬럼이 나오지 않음
		setLayout(new FlowLayout());
		add(scroll);
		
		setSize(500, 550);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new TableTest();
	}
}
