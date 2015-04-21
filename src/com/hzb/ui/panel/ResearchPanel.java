package com.hzb.ui.panel;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.hzb.bean.Research;
import com.hzb.bean.Staff;
import com.hzb.dao.ResearchDao;
import com.hzb.dao.StaffDao;
import com.hzb.ui.UpdateResearchDialog;
import com.hzb.ui.model.ResearchTableModel;
import com.hzb.util.ConstantUtil;

import java.awt.Choice;

public class ResearchPanel extends JPanel {
	
	private static ResearchDao dao = new ResearchDao();
	private StaffDao staffDao = new StaffDao();
	private static ResearchTableModel model;
	private static JTable table;
	
	private TextField researchNametextField;
	private Choice researchIdchoice;
	
	static{
		java.util.List<Research> findAll = dao.findAll();
		model = new ResearchTableModel();
		model.setResearchs(findAll);
		table = new JTable();
		table.setModel(model);
	}
	/**
	 * Create the panel.
	 */
	public ResearchPanel() {
		setLayout(null);
		
		List<Staff> findAll = staffDao.findAll();
		
		Label researchIdLable = new Label("教师编号：");
		researchIdLable.setBounds(76, 50, 69, 23);
		add(researchIdLable);
		
		researchIdchoice = new Choice();
		researchIdchoice.add("--请选择--");
		for (int i = 0; i < findAll.size(); i++) {
			researchIdchoice.add(findAll.get(i).getStaffId());
		}
		researchIdchoice.setBounds(151, 52, 89, 21);
		add(researchIdchoice);
		
		researchNametextField = new TextField();
		researchNametextField.setBounds(321, 50, 79, 23);
		add(researchNametextField);
		
		Label researchNameLabel = new Label("研究方向：");
		researchNameLabel.setBounds(246, 50, 69, 23);
		add(researchNameLabel);
		
		Button searchButton = new Button("查询");
		searchButton.setBounds(428, 50, 76, 23);
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String steacherId=researchIdchoice.getSelectedItem();
				String sdirection =researchNametextField.getText().trim();
				List<Research> findAll2 = dao.findAll(steacherId, sdirection);
				udpate(findAll2);
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
		searchAllButton.setBounds(76, 96, 76, 23);
		add(searchAllButton);
		
		Button addButton = new Button("添加科研");
		addButton.setBounds(170, 96, 76, 23);
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UpdateResearchDialog dialog = new UpdateResearchDialog("添加科研",null);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		add(addButton);
		
		Button updateButton = new Button("修改科研");
		updateButton.setBounds(266, 96, 76, 23);
		updateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if(selectedRow == -1){
					JOptionPane.showMessageDialog(null,"请选中表格其中一行","提示",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				Object valueAt = table.getValueAt(selectedRow, 0);
				UpdateResearchDialog dialog = new UpdateResearchDialog("修改科研",(Integer)valueAt);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		add(updateButton);
		
		Button deleteButton = new Button("删除科研");
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
					Research findOne = dao.findOne(((Integer)valueAt));
					findOne.setIs_del(1);
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
	public static void udpate(List<Research> teachInfos){
		model.setResearchs(teachInfos);
	}
}
