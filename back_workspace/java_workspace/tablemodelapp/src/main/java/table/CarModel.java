package table;

import javax.swing.table.AbstractTableModel;

/**
 * JTable은 MVC 패턴과 비슷한 컴포넌트인데, 데이터를 보유하고, 그 데이터를 디자인 영역에 반영하는 코드가 포함되어있어 MC의 역할을 동시에 수행하는 것과 비슷함
 * 중요한 것은 JTable과 데이터를 분리시켜놓았다는 점
 */
public class CarModel extends AbstractTableModel {

	/**
	 * 테이블의 총 레코드 수
	 */
	@Override
	public int getRowCount() {
		return 5;
	}

	/**
	 * 테이블의 컬럼 수
	 */
	@Override
	public int getColumnCount() {
		return 5;
	}

	/**
	 * 해당 위치의 데이터 값을 가져오는 메서드
	 */
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return "car";
	}
	
}
