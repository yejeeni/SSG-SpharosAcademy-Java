package com.ssg.dbproject;

import javax.swing.table.AbstractTableModel;

/**
 * JTable은 실제 데이터를 가진 주체가 아니고, 사용자들이 보게 되는 UI. 이곳에 보여질 데이터는 모델에 의존하게 됨
 * 디자인영역(View), 데이터 및 데이터 처리 로직(Model)을 분리시켜 개발하는 방법을 MVC 패턴이라고 함
 */
public class DataModel extends AbstractTableModel{
	
	String[][] data; // 데이터를 표현하는 2차원 배열
	String[] title;	// 컬럼을 표현하는 1차원 배열
	
	/**
	 * row를 반환하여 JTable이 참조할 수 있도록 제공되는 메서드
	 */
	@Override
	public int getRowCount() {
		return data.length;
	}

	@Override
	public int getColumnCount() {
		return title.length;
	}

	// JTable에 의해 row*col 수만큼 호출됨
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}
	
	@Override
	public String getColumnName(int column) {
		return title[column];
	}
}
