package com.hzb.ui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.hzb.bean.Course;
import com.hzb.bean.Staff;
import com.hzb.bean.TeachInfo;
import com.hzb.dao.CourseDao;
import com.hzb.dao.StaffDao;

public class TeachInfoTableModel extends AbstractTableModel {

	private List<TeachInfo> teachInfos = new ArrayList<TeachInfo>();
	
	private String[] columnNames = new String[] { "教学编号", "教师编号", "教师名字", "课程编号", "课程名称","课程时数","课程学分","课程性质" };

	public void setTeachInfos(List<TeachInfo> teachInfo) {
		this.teachInfos = teachInfo;
		this.fireTableDataChanged();// 同时通知JTabel数据对象更改, 重绘界面
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public int getRowCount() {
		return teachInfos.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		TeachInfo teachInfo = teachInfos.get(rowIndex);
		Staff staff = teachInfo.getStaff();
		Course course = teachInfo.getCourse();
		switch (columnIndex) {
		case 0:
			return teachInfo.getId();
		case 1:
			return staff.getStaffId();
		case 2:
			return staff.getName();
		case 3:
			return course.getCourse_id();
		case 4:
			return course.getName();
		case 5:
			return course.getHours();
		case 6:
			return course.getCredit();
		case 7:
			return course.getNature();
		}
		return null;
	}

}
