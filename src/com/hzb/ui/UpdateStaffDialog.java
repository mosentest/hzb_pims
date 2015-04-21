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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.hzb.bean.Depart;
import com.hzb.bean.Staff;
import com.hzb.dao.DepartDao;
import com.hzb.dao.StaffDao;
import com.hzb.ui.panel.StaffPanel;
import com.hzb.util.ConstantUtil;
import com.hzb.util.ValueComparator;
/**
 * 新增/修改员工对话框
 * @author 
 *
 */
public class UpdateStaffDialog extends JDialog {
	
	private final JPanel contentPanel = new JPanel();
	private StaffDao dao = new StaffDao();
	private DepartDao departDao = new DepartDao();
	private String staffId;
	private Choice sexChoice;
	private Choice eduChoice;
	private Choice departChoice;
	private TextField staffIdTextField;
	private TextField nameTextField;
	private TextField collegeTextField;
	private TextField jobNameTextField;
	private TextField positionTextField;
	private TextArea rewardsPunishmentTextArea;
	private Choice stateChoice;
	private TextField healthTextField;
	private Staff updateStaff;
	
	/**
	 * Create the dialog.
	 */
	public UpdateStaffDialog(String title,String staffId) {
		this.staffId = staffId;
		setTitle(title);
		setBounds(400, 200, 450, 476);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		Label label = new Label("员工编号：");
		label.setBounds(23, 10, 69, 23);
		contentPanel.add(label);
		
		staffIdTextField = new TextField();
		if(this.staffId !=null){
			staffIdTextField.setEditable(false);
		}
		staffIdTextField.setBounds(98, 10, 230, 23);
		contentPanel.add(staffIdTextField);
		
		Label label_1 = new Label("员工姓名：");
		label_1.setBounds(23, 39, 69, 23);
		contentPanel.add(label_1);
		
		nameTextField = new TextField();
		nameTextField.setBounds(98, 39, 230, 23);
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
		
		collegeTextField = new TextField();
		collegeTextField.setBounds(98, 155, 230, 23);
		contentPanel.add(collegeTextField);
		
		Label label_6 = new Label("健康情况：");
		label_6.setBounds(23, 184, 69, 23);
		contentPanel.add(label_6);
		
		Label label_7 = new Label("职称：");
		label_7.setBounds(23, 213, 69, 23);
		contentPanel.add(label_7);
		
		jobNameTextField = new TextField();
		jobNameTextField.setBounds(98, 213, 230, 23);
		contentPanel.add(jobNameTextField);
		
		Label label_8 = new Label("职务：");
		label_8.setBounds(23, 242, 69, 23);
		contentPanel.add(label_8);
		
		positionTextField = new TextField();
		positionTextField.setBounds(98, 242, 230, 23);
		contentPanel.add(positionTextField);
		
		Label label_9 = new Label("奖惩：");
		label_9.setBounds(23, 300, 69, 23);
		contentPanel.add(label_9);
		
		Label label_10 = new Label("员工状态：");
		label_10.setBounds(23, 271, 69, 23);
		contentPanel.add(label_10);
		
		rewardsPunishmentTextArea = new TextArea();
		rewardsPunishmentTextArea.setBounds(94, 300, 234, 95);
		contentPanel.add(rewardsPunishmentTextArea);
		
		stateChoice = new Choice();
		if(this.staffId == null){
			stateChoice.setEnabled(false);
		}
		List<Entry<String, Integer>> stateList = new ArrayList<Entry<String, Integer>>();
		stateList.addAll(ConstantUtil.stateMap.entrySet());
		Collections.sort(stateList,new ValueComparator());
		Iterator<Entry<String, Integer>> iterator3 = stateList.iterator();
		while(iterator3.hasNext()){
			stateChoice.add(iterator3.next().getKey());
		}
		stateChoice.setBounds(98, 271, 145, 21);
		contentPanel.add(stateChoice);
		
		healthTextField = new TextField();
		healthTextField.setBounds(98, 184, 145, 21);
		contentPanel.add(healthTextField);
		
		sexChoice = new Choice();
		sexChoice.setBounds(98, 68, 103, 21);
		List<Entry<String, Integer>> list = new ArrayList<Entry<String, Integer>>();
		list.addAll(ConstantUtil.sexMap.entrySet());
		Collections.sort(list,new ValueComparator());
		Iterator<Entry<String, Integer>> iterator = list.iterator();
		while(iterator.hasNext()){
			sexChoice.add(iterator.next().getKey());
		}
		contentPanel.add(sexChoice);
		
		eduChoice = new Choice();
		eduChoice.setBounds(98, 97, 145, 21);
		List<Entry<String, Integer>> edulist = new ArrayList<Entry<String, Integer>>();
		edulist.addAll(ConstantUtil.eduMap.entrySet());
		Collections.sort(edulist,new ValueComparator());
		Iterator<Entry<String, Integer>> iterator2 = edulist.iterator();
		while(iterator2.hasNext()){
			eduChoice.add(iterator2.next().getKey());
		}
		contentPanel.add(eduChoice);
		
		departChoice = new Choice();
		departChoice.add("--请选择--");
		final List<Depart> departs = departDao.findAll();
		for(int i=0;i<departs.size();i++){
			departChoice.add(departs.get(i).getDepartName());
		}
		departChoice.setBounds(98, 126, 145, 21);
		contentPanel.add(departChoice);
		
		//TODO 针对修改的时候才用到的代码
		updateStaff = null;
		if(staffId != null){
			updateStaff = dao.findOne(staffId);
			staffIdTextField.setText(updateStaff.getStaffId());
			nameTextField.setText(updateStaff.getName());
			collegeTextField.setText(updateStaff.getCollege());
			jobNameTextField.setText(updateStaff.getJobName());
			positionTextField.setText(updateStaff.getPosition());
			rewardsPunishmentTextArea.setText(updateStaff.getRewardsPunishment());
			healthTextField.setText(updateStaff.getHealth());
			//需要加1
			sexChoice.select(updateStaff.getSex() + 1);
			//需要加1
			eduChoice.select(updateStaff.getEducation() + 1);
			int flag = -1;
			for (int i = 0; i < departs.size(); i++) {
				if(updateStaff.getDepart().getDepartId().equals(departs.get(i).getDepartId())){
					flag = i;
					break;
				}
			}
			//需要加1
			departChoice.select(flag + 1);
			//需要加1
			stateChoice.select(updateStaff.getState() + 1);
		}//(staffId != null)
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				Button button = new Button("确认");
				button.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String sex = sexChoice.getSelectedItem();
						String edu = eduChoice.getSelectedItem();
						String state = stateChoice.getSelectedItem();
						
						int sexvalue = ConstantUtil.getValue(ConstantUtil.sexMap, sex);
						int eduvalue = ConstantUtil.getValue(ConstantUtil.eduMap, edu);
						int statevalue = ConstantUtil.getValue(ConstantUtil.stateMap, state);
						
						String depart = departChoice.getSelectedItem();
						String depart_id = null;
						
						for(int i=0;i<departs.size();i++){
							if(depart.equals(departs.get(i).getDepartName())){
								depart_id = departs.get(i).getDepartId();
							}
						}
						
						boolean flag = false;
						String msg="";
						if(!ConstantUtil.checkId(staffIdTextField.getText().trim())){
							msg+="\r\n员工编号格式不正确（长度为5，第一个必须是字母/数字）\r";
							flag = true;
						}
						if("".equals(nameTextField.getText().trim())){
							msg+="\r\n员工名字不能为空\r";
							flag = true;
						}
						if(sexvalue == -1){
							msg+="\r\n请选择性别\r";
							flag = true;
						}
						if(eduvalue == -1){
							msg+="\r\n请选择学历\r";
							flag = true;
						}
						if("--请选择--".equals(depart)){
							msg+="\r\n请选择部门\r";
							flag = true;
						}
						if("".equals(collegeTextField.getText().trim())){
							msg+="\r\n毕业院校不能为空\r";
							flag = true;
						}
						if("".equals(jobNameTextField.getText().trim())){
							msg+="\r\n职称不能为空\r";
							flag = true;
						}
						if("".equals(positionTextField.getText().trim())){
							msg+="\r\n职务不能为空\r";
							flag = true;
						}
						if(UpdateStaffDialog.this.staffId !=null){
							if(statevalue == -1){
								msg+="\r\n请选择状态\r";
								flag = true;
							}
						}
						if(flag){
							JOptionPane.showMessageDialog(null,msg,ConstantUtil.TIP,JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						//判断是修改还是添加
						if(UpdateStaffDialog.this.staffId != null ){
							updateMethod(sexvalue, eduvalue, statevalue, depart_id);
						}else{
							addMethod(sexvalue, eduvalue, statevalue, depart_id);
						}
					}
				});
				buttonPane.add(button);
			}
			{
				Button button = new Button("取消");
				button.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						UpdateStaffDialog.this.setVisible(false);
					}
				});
				buttonPane.add(button);
			}
		}
	}
	
	private void updateTable(){
		List<Staff> staffs = dao.findAll();
		StaffPanel.udpate(staffs);
	}
	
	private void updateMethod(int sexvalue, int eduvalue, int statevalue, String depart_id) {
		Staff staff = new Staff(updateStaff.getId(),
				staffIdTextField.getText().trim(), 
				nameTextField.getText().trim(),
				sexvalue, eduvalue, depart_id,
				collegeTextField.getText().trim(),
				healthTextField.getText().trim(),
				jobNameTextField.getText().trim(),
				positionTextField.getText().trim(),
				rewardsPunishmentTextArea.getText().trim(),
				statevalue, 0);
		int update = dao.update(staff);
		if (update > 0) {
			JOptionPane.showMessageDialog(null,ConstantUtil.UPDATE_SUCCESS,ConstantUtil.TIP,JOptionPane.INFORMATION_MESSAGE);
			updateTable();
		}
	}
	
	private void addMethod(int sexvalue, int eduvalue, int statevalue, String depart_id) {
		Staff staff = new Staff(-1,
				staffIdTextField.getText().trim(), 
				nameTextField.getText().trim(),
				sexvalue, eduvalue, depart_id,
				collegeTextField.getText().trim(),
				healthTextField.getText().trim(),
				jobNameTextField.getText().trim(),
				positionTextField.getText().trim(),
				rewardsPunishmentTextArea.getText().trim(),
				0, 0);
		Staff findOne = dao.findOne(staffIdTextField.getText().trim());
		if(findOne !=null){
			JOptionPane.showMessageDialog(null,findOne.getStaffId()+ConstantUtil.HAVE,ConstantUtil.TIP,JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		int save = dao.save(staff);
		if (save > 0) {
			JOptionPane.showMessageDialog(null,ConstantUtil.SAVE_SUCCESS,ConstantUtil.TIP,JOptionPane.INFORMATION_MESSAGE);
			updateTable();
		}
	}
}
