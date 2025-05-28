package table;

import java.awt.Window;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

/**
 * JTable은 MVC 패턴과 비슷한 컴포넌트인데, 데이터를 보유하고, 그 데이터를 디자인 영역에 반영하는 코드가 포함되어있어 MC의 역할을 동시에 수행하는 것과 비슷함
 * 중요한 것은 JTable과 데이터를 분리시켜놓았다는 점
 * TableModelListener: 모델이 보유한 데이터를 수정할 때 발생되는 이벤트를 감지하는 리스너
 */
public class MyModel extends AbstractTableModel implements TableModelListener{

	// 회원 정보 2차원 배열
	String[][] rows = new String[0][4];
	String[] columns = {"ID", "NAME", "TEL"};
	MemberRegist memberRegist;
	
	// 생성자
	public MyModel(MemberRegist memberRegist) {
		
		this.memberRegist = memberRegist;
		
		// 모델과 리스너 연결
		this.addTableModelListener(this);
	}
	
		
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
	
	// 테이블 셀에서 데이터를 편집해도, 현재 모델이 보유한 2차원배열을 수정하지 않는 한 값 수정 반영이 되지 않아, setter가 필요함
	/**
	 * 셀에서 원하는 데이터를 입력하고 엔터를 치는 순간, 해당 셀의 row, col, value가 전달
	 */
	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
//		System.out.println("setValueAt() " + value + rowIndex + columnIndex);
		
		// 모델의 2차원 배열에 값을 반영
		rows[rowIndex][columnIndex] = (String) value;
		// memberRegist의 edit() 메서드 호출하여 데이터베이스에 반영
		this.memberRegist.edit(rows[rowIndex]);
		
//		super.setValueAt(value, rowIndex, columnIndex);
	}
	
	/**
	 * 셀을 수정할 수 있는지
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex > 0) {
			return true;			
		} else {
			return false;
		}
	}

	/**
	 * 테이블의 셀을 수정 후 엔터를 누르면 호출되는 메서드
	 */
	@Override
	public void tableChanged(TableModelEvent e) {
		System.out.println("tableChanged()");
	}
	
}
