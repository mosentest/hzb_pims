package com.hzb.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.hzb.bean.Course;
import com.hzb.dao.CourseDao;
import com.hzb.ui.panel.CoursePanel;
import com.hzb.util.ConstantUtil;
/**
 * 新增/修改课程对话框
 * @author
 *
 */
public class UpdateCourseDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String id;
	private CourseDao dao = new CourseDao();
	private Course findOne;
	private TextField idTextField;
	private TextField nameTextField;
	private TextField hoursTextField;
	private TextField creditTextField;
	private TextField natureTextField;
	
	/**
	 * Create the dialog.
	 */
	public UpdateCourseDialog(String title,String id) {
		this.id= id;
		setTitle(title);
		setBounds(400, 200, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		Label label = new Label("课程编号：");
		label.setBounds(23, 10, 69, 23);
		contentPanel.add(label);
		
		idTextField = new TextField();
		if (this.id != null) {
			idTextField.setEditable(false);
		}
		idTextField.setBounds(98, 10, 168, 23);
		contentPanel.add(idTextField);
		
		Label label_1 = new Label("课程名称：");
		label_1.setBounds(23, 39, 69, 23);
		contentPanel.add(label_1);
		
		nameTextField = new TextField();
		nameTextField.setBounds(98, 39, 168, 23);
		contentPanel.add(nameTextField);
		
		Label label_2 = new Label("课程时数：");
		label_2.setBounds(23, 68, 69, 23);
		contentPanel.add(label_2);
		
		hoursTextField = new TextField();
		hoursTextField.setBounds(98, 68, 168, 23);
		contentPanel.add(hoursTextField);
		
		Label label_3 = new Label("课程学分：");
		label_3.setBounds(23, 97, 69, 23);
		contentPanel.add(label_3);
		
		creditTextField = new TextField();
		creditTextField.setBounds(98, 97, 168, 23);
		contentPanel.add(creditTextField);
		
		Label label_4 = new Label("课程性质：");
		label_4.setBounds(23, 126, 69, 23);
		contentPanel.add(label_4);
		
		natureTextField = new TextField();
		natureTextField.setBounds(98, 126, 168, 23);
		contentPanel.add(natureTextField);
		
		Label label_5 = new Label("小时");
		label_5.setBounds(272, 70, 68, 21);
		contentPanel.add(label_5);
		
		Label label_6 = new Label("学分");
		label_6.setBounds(272, 99, 68, 21);
		contentPanel.add(label_6);
		
		if (id != null){
			findOne = dao.findOne(id);
			idTextField.setText(findOne.getCourse_id());
			nameTextField.setText(findOne.getName());
			hoursTextField.setText(findOne.getHours());
			creditTextField.setText(findOne.getCredit());
			natureTextField.setText(findOne.getNature());
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				Button button = new Button("确认");
				button.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						String id = idTextField.getText().trim();
						String name = nameTextField.getText().trim();
						String hours=hoursTextField.getText().trim();
						String credit=creditTextField.getText().trim();
						String nature=natureTextField.getText().trim();
						
						boolean flag = false;
						String msg="";
						if("".equals(id) || id == null){
							msg+="\r\n课程编号不能为空\r";
							flag = true;
						}
						if("".equals(name) ||  name == null){
							msg+="\r\n课程名称不能为空\r";
							flag = true;
						}
						if(!ConstantUtil.checkNum(hours)){
							msg+="\r\n"+hours+"==>请输入大于0并小于200的数字\r";
							flag = true;
						}
						if(!ConstantUtil.checkNum(credit)){
							msg+="\r\n"+credit+"==>请输入大于0并小于200的数字\r";
							flag = true;
						}
						if("".equals(nature) ||  nature == null){
							msg+="\r\n课程性质不能为空\r";
							flag = true;
						}
						if(flag){
							JOptionPane.showMessageDialog(null,msg,ConstantUtil.TIP,JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						//判断是修改还是添加
						if(UpdateCourseDialog.this.id != null ){
							updateMethod(id, name,hours,credit,nature);
						}else{
							addMethod(id, name,hours,credit,nature);
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
						UpdateCourseDialog.this.setVisible(false);
					}
				});
				buttonPane.add(button);
			}
		}
	}
	
	private void updateTable(){
		List<Course> one = dao.findAll();
		CoursePanel.udpate(one);
	}
	
	private void addMethod(String id, String name, String hours, String credit, String nature) {
		Course one = new Course(-1, id, name, hours,credit,nature,0) ;
		Course findOne = dao.findOne(id);
		if(findOne !=null){
			JOptionPane.showMessageDialog(null,findOne.getCourse_id()+ConstantUtil.HAVE,ConstantUtil.TIP,JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		int save = dao.save(one);
		if (save > 0) {
			JOptionPane.showMessageDialog(null,ConstantUtil.SAVE_SUCCESS,ConstantUtil.TIP,JOptionPane.INFORMATION_MESSAGE);
			updateTable();
		}
	}

	private void updateMethod(String id, String name, String hours, String credit, String nature) {
		Course one = new Course(findOne.getId(), id, name,hours,credit,nature,0) ;
		int update = dao.update(one);
		if (update > 0) {
			JOptionPane.showMessageDialog(null,ConstantUtil.UPDATE_SUCCESS,ConstantUtil.TIP,JOptionPane.INFORMATION_MESSAGE);
			updateTable();
		}	
	}
}
