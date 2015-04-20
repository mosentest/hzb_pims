package com.hzb.ui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.hzb.bean.Depart;

public class DepartTableModel extends AbstractTableModel {

	private List<Depart> departs = new ArrayList<Depart>();

	private String[] columnNames = new String[] { "部门编号", "部门名称" };

	public void setDeparts(List<Depart> departs) {
		this.departs = departs;
		this.fireTableDataChanged();// 同时通知JTabel数据对象更改, 重绘界面
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public int getRowCount() {
		return departs.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Depart depart = departs.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return depart.getDepartId();
		case 1:
			return depart.getDepartName();
		}
		return null;
	}

}
