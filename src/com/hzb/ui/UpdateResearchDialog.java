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
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.hzb.bean.Research;
import com.hzb.bean.Staff;
import com.hzb.dao.ResearchDao;
import com.hzb.dao.StaffDao;
import com.hzb.ui.panel.ResearchPanel;
import com.hzb.util.ConstantUtil;

public class UpdateResearchDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private StaffDao staffDao = new StaffDao();
	private ResearchDao researchDao = new ResearchDao();
	private Integer id;
	
	private TextField nameTextField;
	private TextField situationTextField;
	private TextField patentTextField;
	private Choice staffIdChoice;
	
	private List<Staff> staffs;
	private TextField directionTextField;
	private TextArea otherTextArea;
	
	
	/**
	 * Create the dialog.
	 */
	public UpdateResearchDialog(String title, Integer id) {
		this.id= id;
		//用于遍历结果出来。
		staffs = staffDao.findAll();
		setTitle(title);
		setBounds(400, 200, 450, 320);
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
		nameTextField.setBounds(121, 39, 168, 23);
		contentPanel.add(nameTextField);
		
		Label label_2 = new Label("研究方向：");
		label_2.setBounds(23, 68, 69, 23);
		contentPanel.add(label_2);
		
		Label label_3 = new Label("课题研究情况：");
		label_3.setBounds(23, 97, 92, 23);
		contentPanel.add(label_3);
		
		situationTextField = new TextField();
		situationTextField.setBounds(121, 97, 168, 23);
		contentPanel.add(situationTextField);
		
		Label label_4 = new Label("专利：");
		label_4.setBounds(23, 126, 69, 23);
		contentPanel.add(label_4);
		
		patentTextField = new TextField();
		patentTextField.setBounds(121, 126, 168, 23);
		contentPanel.add(patentTextField);
		
		Label label_5 = new Label("论文及著作发表情况：");
		label_5.setBounds(23, 156, 128, 23);
		contentPanel.add(label_5);
		
		staffIdChoice = new Choice();
		staffIdChoice.add("--请选择--");
		for (int i = 0; i < staffs.size(); i++) {
			staffIdChoice.add(staffs.get(i).getStaffId());
		}
		staffIdChoice.setBounds(120, 10, 169, 21);
		contentPanel.add(staffIdChoice);
		
		directionTextField = new TextField();
		directionTextField.setBounds(121, 68, 168, 23);
		contentPanel.add(directionTextField);
		
		otherTextArea = new TextArea();
		otherTextArea.setBounds(121, 191, 168, 76);
		contentPanel.add(otherTextArea);
		
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
		
		//==================================================================
		if (id != null) {
			Research findOne = researchDao.findOne(id);
			int staffFlag=-1;
			for (int i = 0; i < staffs.size(); i++) {
				if(findOne.getStaff().getStaffId().equals(staffs.get(i).getStaffId())){
					staffFlag = i;
					break;
				}
			}
			// 需要加1
			staffIdChoice.select(staffFlag + 1);
			nameTextField.setText(staffs.get(staffFlag).getName());
			situationTextField.setText(findOne.getSituation());
			patentTextField.setText(findOne.getPatent());
			directionTextField.setText(findOne.getDirection());
			otherTextArea.setText(findOne.getOther());
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
						String staffSelectedItem = staffIdChoice.getSelectedItem();
						String situation = situationTextField.getText().trim();
						String patent = patentTextField.getText().trim();
						String direction = directionTextField.getText().trim();
						String other = otherTextArea.getText().trim();
						
						boolean flag = false;
						String msg="";
						if("--请选择--".equals(staffSelectedItem)){
							msg+="\r\n请选择教师\r";
							flag = true;
						}
						if("".equals(direction) || direction == null){
							msg+="\r\n研究方向不能为空\r";
							flag = true;
						}
						if("".equals(situation) || situation == null){
							msg+="\r\n课题研究情况不能为空\r";
							flag = true;
						}
						if("".equals(patent) || patent == null){
							msg+="\r\n专利不能为空\r";
							flag = true;
						}
						if("".equals(other) || other == null){
							msg+="\r\n论文及著作发表情况不能为空\r";
							flag = true;
						}
						if(flag){
							JOptionPane.showMessageDialog(null,msg,ConstantUtil.TIP,JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						//判断是修改还是添加
						if(UpdateResearchDialog.this.id != null ){
							updateMethod(staffSelectedItem, direction,situation,patent,other);
						}else{
							addMethod(staffSelectedItem, direction,situation,patent,other);
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
						UpdateResearchDialog.this.setVisible(false);
					}
				});
				buttonPane.add(button);
			}
		}
	}
	
	private void updateTable(){
		List<Research> result = researchDao.findAll();
		ResearchPanel.udpate(result);
	}
	private void addMethod(String staffSelectedItem,String direction, String situation, String patent,String other) {
		Research one = new Research(-1,staffSelectedItem,direction,situation,patent,other,0) ;
		int save = researchDao.save(one);
		if (save > 0) {
			JOptionPane.showMessageDialog(null,ConstantUtil.SAVE_SUCCESS,ConstantUtil.TIP,JOptionPane.INFORMATION_MESSAGE);
			updateTable();
		}
	}

	private void updateMethod(String staffSelectedItem,String direction, String situation, String patent, String other) {
		Research one = new Research(this.id,staffSelectedItem,direction,situation,patent,other,0) ;
		int save = researchDao.update(one);
		if (save > 0) {
			JOptionPane.showMessageDialog(null,ConstantUtil.UPDATE_SUCCESS,ConstantUtil.TIP,JOptionPane.INFORMATION_MESSAGE);
			updateTable();
		}
	}

}
