package com.hzb.ui.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.hzb.bean.Course;

public class CourseTableModel extends AbstractTableModel {

	private List<Course> courses = new ArrayList<Course>();
	
	private String[] columnNames = new String[] { "课程编号", "课程名称", "课程时数", "课程学分", "课程性质" };

	public void setCourses(List<Course> courses) {
		this.courses = courses;
		this.fireTableDataChanged();// 同时通知JTabel数据对象更改, 重绘界面
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}

	@Override
	public int getRowCount() {
		return courses.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Course course = courses.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return course.getCourse_id();
		case 1:
			return course.getName();
		case 2:
			return course.getHours();
		case 3:
			return course.getCredit();
		case 4:
			return course.getNature();
		}
		return null;
	}

}
