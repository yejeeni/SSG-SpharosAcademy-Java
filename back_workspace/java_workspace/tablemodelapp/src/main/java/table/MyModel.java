package table;

import javax.swing.table.AbstractTableModel;

/**
 * JTable은 MVC 패턴과 비슷한 컴포넌트인데, 데이터를 보유하고, 그 데이터를 디자인 영역에 반영하는 코드가 포함되어있어 MC의 역할을 동시에 수행하는 것과 비슷함
 * 중요한 것은 JTable과 데이터를 분리시켜놓았다는 점
 */
public class MyModel extends AbstractTableModel {

	// 회원 정보 2차원 배열
	String[][] rows = new String[0][3];
	String[] columns = {"ID", "NAME", "TEL"};
	
	
	/**
	 * 테이블의 총 레코드 수
	 */
	@Override
	public int getRowCount() {
		return rows.length;
	}

	/**
	 * 테이블의 컬럼 수
	 */
	@Override
	public int getColumnCount() {
		return columns.length;
	}
	
	/**
	 * 컬럼의 이름을 반환하는 메서드
	 * 컬럼 수만큼 반복하면서 호출되는데, 이때 매개변수로 넘겨받는 col의 값은 자동으로 증가하면서 전달된다.
	 */
	public String getColumnName(int column) {
		return columns[column];
	}

	/**
	 * 해당 위치의 데이터 값을 가져오는 메서드
	 * row*col 만큼 호출되고, 표를 이루는 각 셀의 좌표마다 어떤 값을 넣을지 return
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return rows[rowIndex][columnIndex];
	}
	
	/**
	 * 셀을 수정할 수 있는지
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		System.out.println(rowIndex+"행, "+columnIndex+"열은 수정 가능");
		return true;
	}
	
}
