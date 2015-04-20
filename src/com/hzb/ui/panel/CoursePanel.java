package com.hzb.ui.panel;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.hzb.bean.Course;
import com.hzb.dao.CourseDao;
import com.hzb.ui.model.CourseTableModel;

public class CoursePanel extends JPanel {
	
	private static CourseDao courseDao = new CourseDao();
	
	private  static JTable table;
	
	private static  CourseTableModel model;
	
	static {
		model = new CourseTableModel();
		java.util.List<Course> findAll = courseDao.findAll();
		model.setCourses(findAll);
		table = new JTable();
		table.setModel(model);
	}
	/**
	 * Create the panel.
	 */
	public CoursePanel() {
		setLayout(null);
		
		Button searchAllButton = new Button("查询全部");
		searchAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		searchAllButton.setBounds(76, 80, 76, 23);
		add(searchAllButton);
		
		Button addButton = new Button("添加课程");
		addButton.setBounds(170, 80, 76, 23);
		add(addButton);
		
		Button updateButton = new Button("修改课程");
		updateButton.setBounds(266, 80, 76, 23);
		updateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if(selectedRow == -1){
					JOptionPane.showMessageDialog(null,"请选中表格其中一行","提示",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				Object valueAt = table.getValueAt(selectedRow, 0);
			}
		});
		add(updateButton);
		
		Button deleteButton = new Button("删除课程");
		deleteButton.setBounds(361, 80, 76, 23);
		add(deleteButton);
				
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 136, 681, 292);
		panel_1.setLayout(null);

		table.setBounds(0, 0, 681, 282);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		scrollPane.setBounds(0, 0, 681, 282);
		panel_1.add(scrollPane);
		add(panel_1);
	}

}
