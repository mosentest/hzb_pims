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
import com.hzb.ui.panel.StaffPanel;

public class MainUI {

	private JFrame frame;
	private JMenu mainMenu;
	private JMenu helpMenu;
	private JPanel mainPanel;
	
	private static StaffPanel staffPanel;
	
	static {
		staffPanel = new StaffPanel();
	}

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
		frame.setBounds(400, 250, 737, 507);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		mainMenu = new JMenu("菜单");
		menuBar.add(mainMenu);
		
		mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(null);
		
//		staffPanel.setBounds(10, 10, 707, 477);
//		mainPanel.add(staffPanel);
		
		JMenuItem staffItem = new JMenuItem("员工管理");
		staffItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mainPanel.repaint();
				mainPanel.removeAll();
				staffPanel.setBounds(10, 10, 707, 477);
				mainPanel.add(staffPanel);
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
