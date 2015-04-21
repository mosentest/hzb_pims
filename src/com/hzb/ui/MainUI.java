package com.hzb.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.hzb.ui.panel.CoursePanel;
import com.hzb.ui.panel.DepartPanel;
import com.hzb.ui.panel.ResearchPanel;
import com.hzb.ui.panel.StaffPanel;
import com.hzb.ui.panel.TeachInfoPanel;

public class MainUI {

	private JFrame frame;
	private JMenu mainMenu;
	private JMenu helpMenu;
	private JPanel mainPanel;
	
	private static CoursePanel coursePanel;
	private static DepartPanel departPanel;
	private static ResearchPanel researchPanel;
	private static StaffPanel staffPanel;
	private static TeachInfoPanel teachInfoPanel;

	static {
		coursePanel = new CoursePanel();
		departPanel = new DepartPanel();
		researchPanel = new ResearchPanel();
		staffPanel = new StaffPanel();
		teachInfoPanel = new TeachInfoPanel();
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
		frame.setTitle("人事信息管理系统");
		frame.setBounds(400, 200, 737, 507);
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
				departPanel.setBounds(10, 10, 707, 477);
				mainPanel.add(departPanel);
				
			}
		});
		mainMenu.add(departItem);

		JMenuItem courseItem = new JMenuItem("课程管理");
		courseItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainPanel.repaint();
				mainPanel.removeAll();
				coursePanel.setBounds(10, 10, 707, 477);
				mainPanel.add(coursePanel);
			}
		});
		mainMenu.add(courseItem);

		JMenuItem teachItem = new JMenuItem("教学管理");
		teachItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainPanel.repaint();
				mainPanel.removeAll();
				teachInfoPanel.setBounds(10, 10, 707, 477);
				mainPanel.add(teachInfoPanel);				
			}
		});
		mainMenu.add(teachItem);

		JMenuItem reseachItem = new JMenuItem("科研管理");
		reseachItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mainPanel.repaint();
				mainPanel.removeAll();
				researchPanel.setBounds(10, 10, 707, 477);
				mainPanel.add(researchPanel);		
			}
		});
		mainMenu.add(reseachItem);

		helpMenu = new JMenu("帮助");
		menuBar.add(helpMenu);

		JMenuItem aboutItem = new JMenuItem("关于");
		helpMenu.add(aboutItem);

	}
}
