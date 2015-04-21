package com.hzb.ui.panel;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.hzb.bean.Course;
import com.hzb.bean.Depart;
import com.hzb.dao.CourseDao;
import com.hzb.ui.UpdateCourseDialog;
import com.hzb.ui.model.CourseTableModel;
import com.hzb.util.ConstantUtil;

public class CoursePanel extends JPanel {
	
	private static CourseDao dao = new CourseDao();
	
	private  static JTable table;
	
	private static  CourseTableModel model;
	private TextField idTextField;
	
	static {
		model = new CourseTableModel();
		java.util.List<Course> findAll = dao.findAll();
		model.setCourses(findAll);
		table = new JTable();
		table.setModel(model);
	}


	/**
	 * Create the panel.
	 */
	public CoursePanel() {
		setLayout(null);
		
		Label idLable = new Label("课程编号：");
		idLable.setBounds(76, 10, 69, 23);
		add(idLable);
		
		idTextField = new TextField();
		idTextField.setBounds(151, 10, 79, 23);
		add(idTextField);
		
		Button searchButton = new Button("查询");
		searchButton.setBounds(252, 10, 76, 23);
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String trim = idTextField.getText().trim();
				if(trim != null && trim.length() > 5){
					Course findOne = dao.findOne(trim);
					List<Course> list = new ArrayList<Course>();
					if (findOne != null) {
						list.add(findOne);
					}
					udpate(list);
				}
			}
		});
		add(searchButton);
		
		Button searchAllButton = new Button("查询全部");
		searchAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//重新刷新
				udpate(dao.findAll());
			}
		});
		searchAllButton.setBounds(76, 80, 76, 23);
		add(searchAllButton);
		
		Button addButton = new Button("添加课程");
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UpdateCourseDialog dialog = new UpdateCourseDialog("添加课程", null);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
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
				UpdateCourseDialog dialog = new UpdateCourseDialog("添加课程", (String) valueAt);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		add(updateButton);
		
		Button deleteButton = new Button("删除课程");
		deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if(selectedRow == -1){
					JOptionPane.showMessageDialog(null,"请选中表格其中一行","提示",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				int showConfirmDialog = JOptionPane.showConfirmDialog(null, "确定删除？",ConstantUtil.TIP,JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(showConfirmDialog ==JOptionPane.YES_OPTION){
					Object valueAt = table.getValueAt(selectedRow, 0);
					Course findOne = dao.findOne((String)valueAt);
					//设置为1就表示表示删除，并不是物理删除
					findOne.setIsDel(1);
					dao.update(findOne);
					//重新刷新
					udpate(dao.findAll());
				}
			}
		});
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
	
	public static void udpate(List<Course> one) {
		model.setCourses(one);
	}

}
