package com.shop.ui.swing.model;

import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class DataBaseTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2051173517601979667L;

	private boolean isEdit;
	
	private List<List<Object>> data = new ArrayList<>();
	private List<String> columnNames = new ArrayList<>();
	private List<Class> columnTypes = new ArrayList<>();
	
	public DataBaseTableModel(boolean isEdit) {
		
		this.isEdit =  isEdit;
	}
	
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return columnNames.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return data.get(rowIndex).get(columnIndex);
	}
	
	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub

		data.get(rowIndex).set(columnIndex, aValue);
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return isEdit;
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		// TODO Auto-generated method stub
		return columnTypes.get(columnIndex);
	}
	
	//передача данных в таблицу для их отображения
	public void setDataSource(ResultSet resultSet) {
		
		
	}
}
