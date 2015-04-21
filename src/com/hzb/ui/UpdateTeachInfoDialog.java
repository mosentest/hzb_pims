package com.hzb.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.hzb.bean.Course;
import com.hzb.bean.Staff;
import com.hzb.bean.TeachInfo;
import com.hzb.dao.CourseDao;
import com.hzb.dao.StaffDao;
import com.hzb.ui.panel.StaffPanel;
import com.hzb.ui.panel.TeachInfoPanel;
import com.hzb.util.ConstantUtil;

import java.awt.Choice;

/**
 * 新增/修改教学对话框
 * @author
 *
 */
public class UpdateTeachInfoDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private  CourseDao courseDao = new CourseDao();
	private  StaffDao staffDao = new StaffDao();
	private Integer id;
	private List<Course> courses;
	private List<Staff> staffs;
	private TextField nameTextField;
	private TextField sourseNameTextField;
	private TextField hoursTextField;
	private TextField creditTextField;
	private TextField natureTextField;
	private Choice staffIdChoice;
	private Choice courseIdchoice;
	
	/**
	 * Create the dialog.
	 */
	public UpdateTeachInfoDialog(String title, Integer id) {
		this.id= id;
		//用于遍历结果出来。
		courses = courseDao.findAll();
		staffs = staffDao.findAll();
		setTitle(title);
		setBounds(400, 200, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		Label label = new Label("教师编号：");
		label.setBounds(23, 10, 69, 23);
		contentPanel.add(label);
		
		Label label_1 = new Label("教师名称：");
		label_1.setBounds(23, 39, 69, 23);
		contentPanel.add(label_1);
		
		nameTextField = new TextField();
		nameTextField.setEditable(false);
		nameTextField.setBounds(98, 39, 168, 23);
		contentPanel.add(nameTextField);
		
		Label label_2 = new Label("课程编号：");
		label_2.setBounds(23, 68, 69, 23);
		contentPanel.add(label_2);
		
		Label label_3 = new Label("课程名称：");
		label_3.setBounds(23, 97, 69, 23);
		contentPanel.add(label_3);
		
		sourseNameTextField = new TextField();
		sourseNameTextField.setEditable(false);
		sourseNameTextField.setBounds(98, 97, 168, 23);
		contentPanel.add(sourseNameTextField);
		
		Label label_4 = new Label("课程时数：");
		label_4.setBounds(23, 126, 69, 23);
		contentPanel.add(label_4);
		
		hoursTextField = new TextField();
		hoursTextField.setEditable(false);
		hoursTextField.setBounds(98, 126, 168, 23);
		contentPanel.add(hoursTextField);
		
		Label label_5 = new Label("课程学分：");
		label_5.setBounds(23, 156, 69, 23);
		contentPanel.add(label_5);
		
		creditTextField = new TextField();
		creditTextField.setEditable(false);
		creditTextField.setBounds(98, 156, 168, 23);
		contentPanel.add(creditTextField);
		
		Label label_6 = new Label("课程性质：");
		label_6.setBounds(23, 185, 69, 23);
		contentPanel.add(label_6);
		
		natureTextField = new TextField();
		natureTextField.setEditable(false);
		natureTextField.setBounds(98, 185, 168, 23);
		contentPanel.add(natureTextField);
		
		Label label_7 = new Label("小时");
		label_7.setBounds(270, 128, 68, 21);
		contentPanel.add(label_7);
		
		Label label_8 = new Label("学分");
		label_8.setBounds(270, 158, 68, 21);
		contentPanel.add(label_8);
		
		staffIdChoice = new Choice();
		staffIdChoice.add("--请选择--");
		for (int i = 0; i < staffs.size(); i++) {
			staffIdChoice.add(staffs.get(i).getStaffId());
		}
		staffIdChoice.setBounds(98, 10, 169, 21);
		contentPanel.add(staffIdChoice);
		
		courseIdchoice = new Choice();
		courseIdchoice.add("--请选择--");
		for (int i = 0; i < courses.size(); i++) {
			courseIdchoice.add(courses.get(i).getCourse_id());
		}
		courseIdchoice.setBounds(98, 70, 168, 21);
		contentPanel.add(courseIdchoice);
		
		//===========================================================================
		//根据用户下来选择那个，就拿到那个数据
		staffIdChoice.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				String staffSelectedItem = staffIdChoice.getSelectedItem();
				if(!"--请选择--".equals(staffSelectedItem)){
					Staff findOne = staffDao.findOne(staffSelectedItem);
					nameTextField.setText(findOne.getName());
				}else{
					nameTextField.setText("");
				}
			}
		});
		
		//根据用户下来选择那个，就拿到那个数据
		courseIdchoice.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				String staffSelectedItem = courseIdchoice.getSelectedItem();
				if(!"--请选择--".equals(staffSelectedItem)){
					 Course findOne = courseDao.findOne(staffSelectedItem);
					 sourseNameTextField.setText(findOne.getName());
					hoursTextField.setText(findOne.getHours());
					creditTextField.setText(findOne.getCredit());
					natureTextField.setText(findOne.getNature());
				}else{
					sourseNameTextField.setText("");
					hoursTextField.setText("");
					creditTextField.setText("");
					natureTextField.setText("");
				}
			}
		});
		//==================================================================
		if (id != null) {
			TeachInfo findTeachInfoOne = courseDao.findTeachInfoOne(id);
			int staffFlag=-1;
			for (int i = 0; i < staffs.size(); i++) {
				if(findTeachInfoOne.getStaff().getStaffId().equals(staffs.get(i).getStaffId())){
					staffFlag = i;
					break;
				}
			}
			// 需要加1
			staffIdChoice.select(staffFlag + 1);
			
			int courseFlag = -1;
			for (int i = 0; i < courses.size(); i++) {
				if(findTeachInfoOne.getCourse().getCourse_id().equals(courses.get(i).getCourse_id())){
					courseFlag = i;
					break;
				}
			}
			//需要加1
			courseIdchoice.select(courseFlag + 1);
		}
		//=======================================================================
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				Button button = new Button("确认");
				button.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String staffSelectedItem = staffIdChoice.getSelectedItem();
						String courseSelectedItem = courseIdchoice.getSelectedItem();
						
						boolean flag = false;
						String msg="";
						if("--请选择--".equals(staffSelectedItem)){
							msg+="\r\n请选择教师\r";
							flag = true;
						}
						if("--请选择--".equals(courseSelectedItem)){
							msg+="\r\n请选择课程\r";
							flag = true;
						}
						if(flag){
							JOptionPane.showMessageDialog(null,msg,ConstantUtil.TIP,JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						//判断是修改还是添加
						if(UpdateTeachInfoDialog.this.id != null ){
							updateMethod(staffSelectedItem, courseSelectedItem);
						}else{
							addMethod(staffSelectedItem, courseSelectedItem);
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
						UpdateTeachInfoDialog.this.setVisible(false);
					}
				});
				buttonPane.add(button);
			}
		}
	}
	
	private void updateTable(){
		List<TeachInfo> findAllTeachInfo = courseDao.findAllTeachInfo();
		TeachInfoPanel.udpate(findAllTeachInfo);
	}
	
	private void addMethod(String staffSelectedItem, String courseSelectedItem) {
		TeachInfo one = new TeachInfo(courseSelectedItem,staffSelectedItem) ;
		int save = courseDao.saveTeachInfo(one);
		if (save > 0) {
			JOptionPane.showMessageDialog(null,ConstantUtil.SAVE_SUCCESS,ConstantUtil.TIP,JOptionPane.INFORMATION_MESSAGE);
			updateTable();
		}
	}

	private void updateMethod(String staffSelectedItem, String courseSelectedItem) {
		TeachInfo one = new TeachInfo(courseSelectedItem,staffSelectedItem) ;
		//TODO
	}
}
