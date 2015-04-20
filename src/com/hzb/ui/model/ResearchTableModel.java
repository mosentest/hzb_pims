package com.hzb.ui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.hzb.bean.Research;
import com.hzb.bean.Staff;
import com.hzb.dao.StaffDao;

public class ResearchTableModel extends AbstractTableModel {

	private List<Research> researchs = new ArrayList<Research>();
	
	private StaffDao staffDao = new StaffDao();
	
	private String[] columnNames = new String[] { "科研编号", "教师名字", "研究方向", "课题研究情况", "专利","论文及著作发表情况" };

	public void setResearchs(List<Research> researchs) {
		this.researchs = researchs;
		this.fireTableDataChanged();// 同时通知JTabel数据对象更改, 重绘界面
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public int getRowCount() {
		return researchs.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Research research = researchs.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return research.getId();
		case 1:
			String staffId = research.getStaff().getStaffId();
			Staff findOne = staffDao.findOne(staffId);
			return findOne.getName();
		case 2:
			return research.getDirection();
		case 3:
			return research.getSituation();
		case 4:
			return research.getPatent();
		case 5:
			return research.getOther();
		}
		return null;
	}

}
