package com.hzb.ui.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

import com.hzb.bean.Staff;
import com.hzb.dao.DepartDao;
import com.hzb.util.ConstantUtil;

public class StaffTableModel extends AbstractTableModel {

	private List<Staff> staffs = new ArrayList<Staff>();
	
	private DepartDao dao = new DepartDao();
	
	public String[] columnNames = new String[] { "员工编号", "名字", "性别", "学历", "所属部门", "毕业院校", "健康情况", "职称" };

	public void setStaffs(List<Staff> staffs) {
		this.staffs = staffs;
		this.fireTableDataChanged();// 同时通知JTabel数据对象更改, 重绘界面
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return staffs.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Staff staff = staffs.get(rowIndex);
		Set<Entry<String, Integer>> entrySet = ConstantUtil.eduMap.entrySet();
		switch (columnIndex) {
		case 0:
			return staff.getStaffId();
		case 1:
			return staff.getName();
		case 2:
			return staff.getSex() == 0 ? "男":"女";
		case 3:
			return  ConstantUtil.getKey(ConstantUtil.eduMap, staff.getEducation());
		case 4:
			return dao.findOne(staff.getDepart().getDepartId()).getDepartName();
		case 5:
			return staff.getCollege();
		case 6:
			return staff.getHealth();
		case 7:
			return staff.getJobName();
		default:
			break;
		}
		return null;
		
	}

}
