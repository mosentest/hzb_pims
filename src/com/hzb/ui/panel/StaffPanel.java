package com.hzb.ui.panel;

import java.awt.Button;
import java.awt.Choice;
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

import com.hzb.bean.Depart;
import com.hzb.bean.Staff;
import com.hzb.dao.DepartDao;
import com.hzb.dao.StaffDao;
import com.hzb.ui.UpdateStaffDialog;
import com.hzb.ui.model.StaffTableModel;
import com.hzb.util.ConstantUtil;

/**
 * 
 * 员工面板
 *
 */
public class StaffPanel extends JPanel {
	
	private static  StaffDao staffDao = new StaffDao();
	private DepartDao departDao = new DepartDao();
	private List<Depart> resultDeparts;
	private static StaffTableModel model;
	
	private static  JTable staffTable;
	private Label staffIdLable;
	private TextField staffIdTextField;
	private TextField staffNametextField;
	private Label staffNameLabel;
	private Label staffSexLable;
	private Label departNameLabel;
	private Label jobNameLabel;
	private TextField jobNameTextField;
	private Choice departChoice;
	private Choice sexChoice;
	
	static{
		java.util.List<Staff> findAll = staffDao.findAll();
		model = new StaffTableModel();
		model.setStaffs(findAll);
		staffTable = new JTable();
		staffTable.setModel(model);
	}
	/**
	 * Create the panel.
	 */
	public StaffPanel() {
		setLayout(null);
		staffIdLable = new Label("员工编号：");
		staffIdLable.setBounds(76, 10, 69, 23);
		add(staffIdLable);
		
		staffIdTextField = new TextField();
		staffIdTextField.setBounds(151, 10, 79, 23);
		add(staffIdTextField);
		
		staffNametextField = new TextField();
		staffNametextField.setBounds(321, 10, 79, 23);
		add(staffNametextField);
		
		staffNameLabel = new Label("员工名字：");
		staffNameLabel.setBounds(246, 10, 69, 23);
		add(staffNameLabel);
		
		staffSexLable = new Label("员工性别：");
		staffSexLable.setBounds(416, 10, 69, 23);
		add(staffSexLable);
		
		departNameLabel = new Label("所属部门：");
		departNameLabel.setBounds(76, 50, 69, 23);
		add(departNameLabel);
		
		jobNameLabel = new Label("职称：");
		jobNameLabel.setBounds(283, 50, 50, 23);
		add(jobNameLabel);
		
		jobNameTextField = new TextField();
		jobNameTextField.setBounds(339, 50, 130, 23);
		add(jobNameTextField);
		
		Button searchButton = new Button("查询");
		searchButton.setBounds(491, 50, 76, 23);
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String depart = departChoice.getSelectedItem();
				String sex = sexChoice.getSelectedItem();
				int sexvalue = ConstantUtil.getValue(ConstantUtil.sexMap, sex);
				String depart_id = null;
				List<Depart> resultDeparts = departDao.findAll();
				for(int i=0;i<resultDeparts.size();i++){
					if(depart.equals(resultDeparts.get(i).getDepartName())){
						depart_id = resultDeparts.get(i).getDepartId();
					}
				}
				List<Staff> findAll = staffDao.findAll(staffIdTextField.getText().trim(), staffNametextField.getText().trim(), sexvalue, depart_id, jobNameTextField.getText().trim());
				//重新刷新
				udpate(findAll);
			}
		});
		add(searchButton);
		
		Button searchAllButton = new Button("查询全部");
		searchAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//重新刷新
				udpate(staffDao.findAll());
			}
		});
		searchAllButton.setBounds(76, 96, 76, 23);
		add(searchAllButton);
		
		Button addButton = new Button("添加员工");
		addButton.setBounds(170, 96, 76, 23);
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				UpdateStaffDialog dialog = new UpdateStaffDialog("添加员工",null);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		add(addButton);
		
		Button updateButton = new Button("修改员工");
		updateButton.setBounds(266, 96, 76, 23);
		updateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = staffTable.getSelectedRow();
				if(selectedRow == -1){
					JOptionPane.showMessageDialog(null,"请选中表格其中一行","提示",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				Object valueAt = staffTable.getValueAt(selectedRow, 0);
				UpdateStaffDialog dialog = new UpdateStaffDialog("修改员工",(String)valueAt);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		add(updateButton);
		
		Button deleteButton = new Button("删除员工");
		deleteButton.setBounds(361, 96, 76, 23);
		deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = staffTable.getSelectedRow();
				if(selectedRow == -1){
					JOptionPane.showMessageDialog(null,"请选中表格其中一行","提示",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				int showConfirmDialog = JOptionPane.showConfirmDialog(null, "确定删除？",ConstantUtil.TIP,JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
				if(showConfirmDialog ==JOptionPane.YES_OPTION){
					Object valueAt = staffTable.getValueAt(selectedRow, 0);
					Staff findOne = staffDao.findOne((String)valueAt);
					//设置为1就表示表示删除，并不是物理删除
					findOne.setIsDel(1);
					staffDao.update(findOne);
					//重新刷新
					udpate(staffDao.findAll());
				}
			}
		});
		add(deleteButton);
		
		sexChoice = new Choice();
		sexChoice.add("--请选择--");
		sexChoice.add("男");
		sexChoice.add("女");
		sexChoice.setBounds(491, 12, 80, 21);
		add(sexChoice);
		
		departChoice = new Choice();
		resultDeparts = departDao.findAll();
		departChoice.add("--请选择--");
		for (int i = 0; i < resultDeparts.size(); i++) {
			departChoice.add(resultDeparts.get(i).getDepartName());
		}
		departChoice.setBounds(151, 50, 111, 21);
		add(departChoice);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 136, 681, 292);
		panel_1.setLayout(null);

		staffTable.setBounds(0, 0, 681, 282);
		JScrollPane scrollPane = new JScrollPane(staffTable);
		staffTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
		scrollPane.setBounds(0, 0, 681, 282);
		panel_1.add(scrollPane);
		
		add(panel_1);
	}
	
	public static void udpate(List<Staff> staffs){
		model.setStaffs(staffs);
	}
}
