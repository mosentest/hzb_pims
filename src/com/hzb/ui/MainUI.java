package com.hzb.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.hzb.bean.Depart;
import com.hzb.bean.Staff;
import com.hzb.dao.DepartDao;
import com.hzb.dao.StaffDao;
import com.hzb.ui.model.StaffTableModel;

public class MainUI {

	private JFrame frame;
	private JMenu mainMenu;
	private JMenu helpMenu;
	private JPanel mainPanel;
	private JTable staffTable;
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

	private StaffDao staffDao = new StaffDao();
	private DepartDao departDao = new DepartDao();
	private List<Depart> resultDeparts;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainUI window = new MainUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("人事信息管理系统——软工2班黄志彬");
		frame.setBounds(100, 100, 717, 487);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		mainMenu = new JMenu("菜单");
		menuBar.add(mainMenu);
		
		mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);
		
		staffIdLable = new Label("员工编号：");
		staffIdLable.setBounds(76, 10, 69, 23);
		mainPanel.add(staffIdLable);
		
		staffIdTextField = new TextField();
		staffIdTextField.setBounds(151, 10, 79, 23);
		mainPanel.add(staffIdTextField);
		
		staffNametextField = new TextField();
		staffNametextField.setBounds(321, 10, 79, 23);
		mainPanel.add(staffNametextField);
		
		staffNameLabel = new Label("员工名字：");
		staffNameLabel.setBounds(246, 10, 69, 23);
		mainPanel.add(staffNameLabel);
		
		staffSexLable = new Label("员工性别：");
		staffSexLable.setBounds(416, 10, 69, 23);
		mainPanel.add(staffSexLable);
		
		departNameLabel = new Label("所属部门：");
		departNameLabel.setBounds(76, 50, 69, 23);
		mainPanel.add(departNameLabel);
		
		jobNameLabel = new Label("职称：");
		jobNameLabel.setBounds(283, 50, 50, 23);
		mainPanel.add(jobNameLabel);
		
		jobNameTextField = new TextField();
		jobNameTextField.setBounds(339, 50, 130, 23);
		mainPanel.add(jobNameTextField);
		
		Button searchButton = new Button("查询");
		searchButton.setBounds(491, 50, 76, 23);
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = staffTable.getSelectedRow();
				Object valueAt = staffTable.getValueAt(selectedRow, 0);
				System.out.println(valueAt);
			}
		});
		mainPanel.add(searchButton);
		
		Button searchAllButton = new Button("查询全部");
		searchAllButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		searchAllButton.setBounds(76, 96, 76, 23);
		mainPanel.add(searchAllButton);
		
		Button addButton = new Button("添加员工");
		addButton.setBounds(170, 96, 76, 23);
		mainPanel.add(addButton);
		
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
				UpdateStaffUI dialog = new UpdateStaffUI("修改员工",(String)valueAt);
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		mainPanel.add(updateButton);
		
		Button deleteButton = new Button("删除员工");
		deleteButton.setBounds(361, 96, 76, 23);
		mainPanel.add(deleteButton);
		
		sexChoice = new Choice();
		sexChoice.add("--请选择--");
		sexChoice.add("男");
		sexChoice.add("女");
		sexChoice.setBounds(491, 12, 55, 21);
		mainPanel.add(sexChoice);
		
		departChoice = new Choice();
		resultDeparts = departDao.findAll();
		departChoice.add("--请选择--");
		for(int i=0;i<resultDeparts.size();i++){
			departChoice.add(resultDeparts.get(i).getDepartName());
		}
		departChoice.setBounds(151, 50, 111, 21);
		mainPanel.add(departChoice);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 136, 681, 292);
		panel_1.setLayout(null);

		java.util.List<Staff> findAll = staffDao.findAll();
		StaffTableModel model = new StaffTableModel();
		model.setStaffs(findAll);
		staffTable = new JTable();
		staffTable.setModel(model);
		staffTable.setBounds(0, 0, 681, 282);
		JScrollPane scrollPane = new JScrollPane(staffTable);
		staffTable.setPreferredScrollableViewportSize(new Dimension(500, 70));
		scrollPane.setBounds(0, 0, 681, 282);
		panel_1.add(scrollPane);
		
		mainPanel.add(panel_1);
		
		JMenuItem staffItem = new JMenuItem("员工管理");
		staffItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainPanel.repaint();
				mainPanel.removeAll();
				JButton button = new JButton("343434343");
				button.setBounds(100, 1, 93, 23);
				mainPanel.add(button);
			}
		});
		mainMenu.add(staffItem);

		JMenuItem departItem = new JMenuItem("部门管理");
		departItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainPanel.repaint();
				mainPanel.removeAll();
				JButton button = new JButton("34342222222222222222222");
				button.setBounds(20, 20, 93, 23);
				mainPanel.add(button);
				
			}
		});
		mainMenu.add(departItem);

		JMenuItem courseItem = new JMenuItem("课程管理");
		mainMenu.add(courseItem);

		JMenuItem teachItem = new JMenuItem("教学管理");
		mainMenu.add(teachItem);

		JMenuItem reseachItem = new JMenuItem("科研管理");
		mainMenu.add(reseachItem);

		helpMenu = new JMenu("帮助");
		menuBar.add(helpMenu);

		JMenuItem aboutItem = new JMenuItem("关于");
		helpMenu.add(aboutItem);

	}
}
