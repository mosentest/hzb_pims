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

import com.hzb.bean.Depart;
import com.hzb.dao.DepartDao;
import com.hzb.ui.panel.DepartPanel;
import com.hzb.util.ConstantUtil;

/**
 * 新增/修改部门对话框
 * 
 * @author
 *
 */
public class UpdateDepartDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private String id;
	private DepartDao dao = new DepartDao();
	private TextField idTextField;
	private TextField nameTextField;
	private Depart findOne;
	
	/**
	 * Create the dialog.
	 */
	public UpdateDepartDialog(String title,String id) {
		this.id= id;
		setTitle(title);
		setBounds(400, 200, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		Label label = new Label("部门编号：");
		label.setBounds(23, 10, 69, 23);
		contentPanel.add(label);
		
		idTextField = new TextField();
		if (this.id != null) {
			idTextField.setEditable(false);
		}
		idTextField.setBounds(98, 10, 168, 23);
		contentPanel.add(idTextField);
		
		Label label_1 = new Label("部门名称：");
		label_1.setBounds(23, 39, 69, 23);
		contentPanel.add(label_1);
		
		nameTextField = new TextField();
		nameTextField.setBounds(98, 39, 168, 23);
		contentPanel.add(nameTextField);
		
		if (id != null){
			findOne = dao.findOne(id);
			idTextField.setText(findOne.getDepartId());
			nameTextField.setText(findOne.getDepartName());
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
						
						boolean flag = false;
						String msg="";
						if("".equals(id) || id==null){
							msg+="\r\n部门编号不能为空\r";
							flag = true;
						}
						if("".equals(name) || name == null){
							msg+="\r\n部门名称不能为空\r";
							flag = true;
						}
						if(flag){
							JOptionPane.showMessageDialog(null,msg,ConstantUtil.TIP,JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						//判断是修改还是添加
						if(UpdateDepartDialog.this.id != null ){
							updateMethod(id, name);
						}else{
							addMethod(id, name);
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
						UpdateDepartDialog.this.setVisible(false);
					}
				});
				buttonPane.add(button);
			}
		}
	}

	private void updateTable(){
		List<Depart> one = dao.findAll();
		DepartPanel.udpate(one);
	}
	
	private void addMethod(String id, String name) {
		Depart one = new Depart(-1, id, name, 0) ;
		Depart findOne = dao.findOne(id);
		if(findOne !=null){
			JOptionPane.showMessageDialog(null,findOne.getDepartId()+ConstantUtil.HAVE,ConstantUtil.TIP,JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		int save = dao.save(one);
		if (save > 0) {
			JOptionPane.showMessageDialog(null,ConstantUtil.SAVE_SUCCESS,ConstantUtil.TIP,JOptionPane.INFORMATION_MESSAGE);
			updateTable();
		}
	}

	private void updateMethod(String id, String name) {
		Depart one = new Depart(findOne.getId(), id, name, 0) ;
		int update = dao.update(one);
		if (update > 0) {
			JOptionPane.showMessageDialog(null,ConstantUtil.UPDATE_SUCCESS,ConstantUtil.TIP,JOptionPane.INFORMATION_MESSAGE);
			updateTable();
		}
	}

}
