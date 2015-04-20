package com.hzb.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.hzb.bean.Depart;
import com.hzb.bean.Staff;
import com.hzb.dao.DepartDao;
import com.hzb.dao.StaffDao;
import com.hzb.util.ConstantUtil;

public class UpdateStaffDialog extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private StaffDao dao = new StaffDao();
	private DepartDao departDao = new DepartDao();
	private String staffId;
	private Choice sexChoice;
	private Choice eduChoice;
	/**
	 * Create the dialog.
	 */
	public UpdateStaffDialog(String title,String staffId) {
		this.staffId = staffId;
		Staff findOne = dao.findOne(staffId);
		getContentPane().setName(title);
		setBounds(100, 100, 450, 476);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		Label label = new Label("员工编号：");
		label.setBounds(23, 10, 69, 23);
		contentPanel.add(label);
		
		TextField staffIdTextField = new TextField();
		staffIdTextField.setText(findOne.getStaffId());
		staffIdTextField.setBounds(98, 10, 230, 23);
		contentPanel.add(staffIdTextField);
		
		Label label_1 = new Label("员工姓名：");
		label_1.setBounds(23, 39, 69, 23);
		contentPanel.add(label_1);
		
		TextField nameTextField = new TextField();
		nameTextField.setBounds(98, 39, 230, 23);
		nameTextField.setText(findOne.getName());
		contentPanel.add(nameTextField);
		
		Label label_2 = new Label("性别：");
		label_2.setBounds(23, 68, 69, 23);
		contentPanel.add(label_2);
		
		Label label_3 = new Label("学历：");
		label_3.setBounds(23, 97, 69, 23);
		contentPanel.add(label_3);
		
		Label label_4 = new Label("所属部门：");
		label_4.setBounds(23, 126, 69, 23);
		contentPanel.add(label_4);
		
		Label label_5 = new Label("毕业院校：");
		label_5.setBounds(23, 155, 69, 23);
		contentPanel.add(label_5);
		
		TextField textField_5 = new TextField();
		textField_5.setBounds(98, 155, 230, 23);
		contentPanel.add(textField_5);
		
		Label label_6 = new Label("健康情况：");
		label_6.setBounds(23, 184, 69, 23);
		contentPanel.add(label_6);
		
		Label label_7 = new Label("职称：");
		label_7.setBounds(23, 213, 69, 23);
		contentPanel.add(label_7);
		
		TextField textField_7 = new TextField();
		textField_7.setBounds(98, 213, 230, 23);
		contentPanel.add(textField_7);
		
		Label label_8 = new Label("职务：");
		label_8.setBounds(23, 242, 69, 23);
		contentPanel.add(label_8);
		
		TextField textField_8 = new TextField();
		textField_8.setBounds(98, 242, 230, 23);
		contentPanel.add(textField_8);
		
		Label label_9 = new Label("奖惩：");
		label_9.setBounds(23, 300, 69, 23);
		contentPanel.add(label_9);
		
		Label label_10 = new Label("员工状态：");
		label_10.setBounds(23, 271, 69, 23);
		contentPanel.add(label_10);
		
		TextArea textArea = new TextArea();
		textArea.setBounds(94, 300, 234, 95);
		contentPanel.add(textArea);
		
		Choice choice = new Choice();
		choice.setBounds(98, 271, 145, 21);
		contentPanel.add(choice);
		
		Choice choice_1 = new Choice();
		choice_1.setBounds(98, 184, 145, 21);
		contentPanel.add(choice_1);
		
		sexChoice = new Choice();
		sexChoice.setBounds(98, 68, 103, 21);
		Set<Entry<String,Integer>> sexEntrySet = ConstantUtil.sexMap.entrySet();
		Iterator<Entry<String,Integer>> sexIterator = sexEntrySet.iterator();
		while(sexIterator.hasNext()){
			sexChoice.add(sexIterator.next().getKey());
		}
		//需要加1
		sexChoice.select(findOne.getSex() + 1);
		contentPanel.add(sexChoice);
		
		eduChoice = new Choice();
		eduChoice.setBounds(98, 97, 145, 21);
		Set<Entry<String,Integer>> eduEntrySet = ConstantUtil.eduMap.entrySet();
		Iterator<Entry<String,Integer>> eduIterator = eduEntrySet.iterator();
		while(eduIterator.hasNext()){
			eduChoice.add(eduIterator.next().getKey());
		}
		//需要加1
		eduChoice.select(findOne.getEducation() + 1);
		contentPanel.add(eduChoice);
		
		Choice departChoice = new Choice();
		departChoice.setBounds(98, 126, 145, 21);
		List<Depart> findAll = departDao.findAll();
		contentPanel.add(departChoice);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				Button button = new Button("确认");
				button.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String selectedItem = sexChoice.getSelectedItem();
						System.out.println(selectedItem);
//						ConstantUtil.sexMap.
					}
				});
				buttonPane.add(button);
			}
			{
				Button button = new Button("取消");
				buttonPane.add(button);
			}
		}
	}
}
