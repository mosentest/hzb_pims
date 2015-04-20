package com.hzb.ui.panel;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.hzb.bean.Depart;
import com.hzb.dao.DepartDao;
import com.hzb.ui.model.DepartTableModel;

public class DepartPanel extends JPanel {
	
	private static DepartDao DepartDao = new DepartDao();
	
	private static DepartTableModel model;
	private static  JTable table;
	private Label departIdLable;
	private TextField departIdTextField;
	
	static{
		java.util.List<Depart> findAll = DepartDao.findAll();
		model = new DepartTableModel();
		model.setDeparts(findAll);
		table = new JTable();
		table.setModel(model);
	}
	/**
	 * Create the panel.
	 */
	public DepartPanel() {
		setLayout(null);
		departIdLable = new Label("部门编号：");
		departIdLable.setBounds(76, 10, 69, 23);
		add(departIdLable);
		
		departIdTextField = new TextField();
		departIdTextField.setBounds(151, 10, 79, 23);
		add(departIdTextField);
		
		Button searchButton = new Button("查询");
		searchButton.setBounds(252, 10, 76, 23);
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				Object valueAt = table.getValueAt(selectedRow, 0);
				System.out.println(valueAt);
			}
		});
		add(searchButton);
		
		Button searchAllButton = new Button("查询全部");
		searchAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		searchAllButton.setBounds(76, 80, 76, 23);
		add(searchAllButton);
		
		Button addButton = new Button("添加部门");
		addButton.setBounds(170, 80, 76, 23);
		add(addButton);
		
		Button updateButton = new Button("修改部门");
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
		
		Button deleteButton = new Button("删除部门");
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
