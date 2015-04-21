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

import com.hzb.bean.Depart;
import com.hzb.dao.DepartDao;
import com.hzb.ui.UpdateDepartDialog;
import com.hzb.ui.model.DepartTableModel;
import com.hzb.util.ConstantUtil;

/**
 * 
 * 部门面板
 *
 */
public class DepartPanel extends JPanel {
	
	private static DepartDao dao = new DepartDao();
	
	private static DepartTableModel model;
	private static  JTable table;
	private Label departIdLable;
	private TextField departIdTextField;
	
	static{
		java.util.List<Depart> findAll = dao.findAll();
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
				String trim = departIdTextField.getText().trim();
				if (trim != null && trim.length() > 5) {
					Depart findOne = dao.findOne(trim);
					List<Depart> list = new ArrayList<Depart>();
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
		
		Button addButton = new Button("添加部门");
		addButton.setBounds(170, 80, 76, 23);
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UpdateDepartDialog dialog = new UpdateDepartDialog("添加部门", null);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
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
				UpdateDepartDialog dialog = new UpdateDepartDialog("修改部门", (String) valueAt);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		add(updateButton);
		
		Button deleteButton = new Button("删除部门");
		deleteButton.setBounds(361, 80, 76, 23);
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
					Depart findOne = dao.findOne((String)valueAt);
					//设置为1就表示表示删除，并不是物理删除
					findOne.setIsDel(1);
					dao.update(findOne);
					//重新刷新
					udpate(dao.findAll());
				}
			}
		});
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
	
	public static void udpate(List<Depart> departs){
		model.setDeparts(departs);
	}
}
