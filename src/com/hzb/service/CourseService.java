package com.hzb.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.hzb.bean.Course;
import com.hzb.dao.CourseDao;
import com.hzb.util.DBUtil;

public class CourseService {
	private CourseDao dao = new CourseDao();
	public boolean save(Course entity){
		boolean flag=false;
		Connection connetion = DBUtil.getConnetion();
		try {
			connetion.setAutoCommit(false);
			int save = dao.save(entity);
			connetion.commit();
			if(save > 0){
				flag = true;
			}
		} catch (SQLException e) {
			try {
				connetion.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}finally{
			try {
				connetion.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return flag;
	}
	
}
