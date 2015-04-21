package com.hzb.ui.panel;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.hzb.bean.TeachInfo;
import com.hzb.dao.CourseDao;
import com.hzb.ui.UpdateTeachInfoDialog;
import com.hzb.ui.model.TeachInfoTableModel;
import com.hzb.util.ConstantUtil;

public class TeachInfoPanel extends JPanel {
	private static CourseDao dao = new CourseDao();
	private static TeachInfoTableModel model;
	private static JTable table;

	
	static{
		java.util.List<TeachInfo> findAll = dao.findAllTeachInfo();
		model = new TeachInfoTableModel();
		model.setTeachInfos(findAll);
		table = new JTable();
		table.setModel(model);
	}
	
	/**
	 * Create the panel.
	 */
	public TeachInfoPanel() {
		setLayout(null);
		Button searchAllButton = new Button("查询全部");
		searchAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//重新刷新
				udpate(dao.findAllTeachInfo());
			}
		});
		searchAllButton.setBounds(76, 96, 76, 23);
		add(searchAllButton);
		
		Button addButton = new Button("添加教学");
		addButton.setBounds(170, 96, 76, 23);
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UpdateTeachInfoDialog dialog = new UpdateTeachInfoDialog("添加教学",null);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		add(addButton);
		
		Button updateButton = new Button("修改教学");
		updateButton.setBounds(266, 96, 76, 23);
		updateButton.setEnabled(false);
		updateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if(selectedRow == -1){
					JOptionPane.showMessageDialog(null,"请选中表格其中一行","提示",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				Object valueAt = table.getValueAt(selectedRow, 0);
				UpdateTeachInfoDialog dialog = new UpdateTeachInfoDialog("修改教学",(Integer)valueAt);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		add(updateButton);
		
		Button deleteButton = new Button("删除教学");
		deleteButton.setBounds(361, 96, 76, 23);
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
					TeachInfo findOne = dao.findTeachInfoOne(((Integer)valueAt));
					//这里真的删除
					dao.deleteTeachInfo(findOne);
					//重新刷新
					udpate(dao.findAllTeachInfo());
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
	public static void udpate(List<TeachInfo> teachInfos){
		model.setTeachInfos(teachInfos);
	}
}
