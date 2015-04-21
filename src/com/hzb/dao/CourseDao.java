package com.hzb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hzb.bean.Course;
import com.hzb.bean.Staff;
import com.hzb.bean.TeachInfo;
import com.hzb.util.ConstantUtil;
import com.hzb.util.DBUtil;

public class CourseDao implements BaseDao<Course> {

	/**
	 * 保存课程信息
	 */
	@Override
	public int save(Course entity) {
		int result = -1;
		String sql ="INSERT INTO tb_course(course_id,name,hours,credit,nature) VALUES(?,?,?,?,?)";
		Connection connetion = DBUtil.getConnetion();
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connetion.prepareStatement(sql);
			prepareStatement.setString(1, entity.getCourse_id());
			prepareStatement.setString(2, entity.getName());
			prepareStatement.setString(3, entity.getHours());
			prepareStatement.setString(4, entity.getCredit());
			prepareStatement.setString(5, entity.getNature());
			result = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(CourseDao.class.getName()+ConstantUtil.SAVE_FAILURE);
		}finally{
			if(prepareStatement!=null){
				try {
					prepareStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connetion !=null){
				try {
					connetion.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	/**
	 * 更新课程信息
	 */
	@Override
	public int update(Course entity) {
		int result = -1;
		String sql ="UPDATE tb_course SET course_id=?, name=?, hours=?, credit=?, nature=?, id_del=? WHERE id=? ";
		Connection connetion = DBUtil.getConnetion();
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connetion.prepareStatement(sql);
			prepareStatement.setString(1, entity.getCourse_id());
			prepareStatement.setString(2, entity.getName());
			prepareStatement.setString(3, entity.getHours());
			prepareStatement.setString(4, entity.getCredit());
			prepareStatement.setString(5, entity.getNature());
			prepareStatement.setInt(6, entity.getIsDel());
			prepareStatement.setInt(7, entity.getId());
			result = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(CourseDao.class.getName()+ConstantUtil.UPDATE_FAILURE);
		}finally{
			if(prepareStatement != null){
				try {
					prepareStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connetion != null){
				try {
					connetion.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	/**
	 * 查找所有课程信息
	 */
	@Override
	public List<Course> findAll() {
		String sql ="SELECT * FROM tb_course WHERE is_del=0";
		Connection connetion = DBUtil.getConnetion();
		List<Course> result = new ArrayList<Course>();
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connetion.prepareStatement(sql);
			ResultSet executeQuery = prepareStatement.executeQuery();
			while(executeQuery.next()){
				int id = executeQuery.getInt(1);
				String courseId = executeQuery.getString(2);
				String name = executeQuery.getString(3);
				String hours = executeQuery.getString(4);
				String credit = executeQuery.getString(5);
				String nature = executeQuery.getString(6);
				int isDel = executeQuery.getInt(7);
				Course course = new Course(id, courseId, name, hours, credit, nature, isDel);
				result.add(course);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(prepareStatement != null){
				try {
					prepareStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connetion != null){
				try {
					connetion.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

	@Override
	public Course findOne(int id) {
		String sql ="SELECT * FROM tb_course WHERE id=? AND is_del=0";
		Connection connetion = DBUtil.getConnetion();
		Course course = null;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connetion.prepareStatement(sql);
			prepareStatement.setInt(id, 1);
			ResultSet executeQuery = prepareStatement.executeQuery();
			if(executeQuery.next()){
				String courseId = executeQuery.getString(2);
				String name = executeQuery.getString(3);
				String hours = executeQuery.getString(4);
				String credit = executeQuery.getString(5);
				String nature = executeQuery.getString(6);
				int isDel = executeQuery.getInt(7);
				course = new Course(id, courseId, name, hours, credit, nature, isDel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(prepareStatement != null){
				try {
					prepareStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connetion != null){
				try {
					connetion.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return course;
	}
	
	public Course findOne(String  scourseId) {
		String sql ="SELECT * FROM tb_course WHERE course_id=? AND  is_del=0 ";
		Connection connetion = DBUtil.getConnetion();
		Course course = null;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connetion.prepareStatement(sql);
			prepareStatement.setString(1, scourseId);
			ResultSet executeQuery = prepareStatement.executeQuery();
			if(executeQuery.next()){
				int id = executeQuery.getInt(1);
				String courseId = executeQuery.getString(2);
				String name = executeQuery.getString(3);
				String hours = executeQuery.getString(4);
				String credit = executeQuery.getString(5);
				String nature = executeQuery.getString(6);
				int isDel = executeQuery.getInt(7);
				course = new Course(id, courseId, name, hours, credit, nature, isDel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(prepareStatement != null){
				try {
					prepareStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connetion != null){
				try {
					connetion.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return course;
	}
	
	/**
	 * 保存教学信息
	 * 
	 * @param entity
	 * @return
	 */
	public int saveTeachInfo(TeachInfo entity){
		int result = -1;
		String sql ="INSERT INTO tb_teach_info(teacher_id,course_id) VALUES(?,?)";
		Connection connetion = DBUtil.getConnetion();
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connetion.prepareStatement(sql);
			prepareStatement.setString(1, entity.getTeacherId());
			prepareStatement.setString(2, entity.getCourseId());
			result = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(CourseDao.class.getName()+ConstantUtil.SAVE_FAILURE);
		}finally{
			if(prepareStatement!=null){
				try {
					prepareStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connetion !=null){
				try {
					connetion.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	/**
	 * 删除教学信息
	 * @param entity
	 * @return
	 */
	public int deleteTeachInfo(TeachInfo entity){
		int result = -1;
		String sql ="DELETE FROM tb_teach_info WHERE id = ?";
		Connection connetion = DBUtil.getConnetion();
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connetion.prepareStatement(sql);
			prepareStatement.setInt(1, entity.getId());
			result = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(CourseDao.class.getName()+ConstantUtil.DELETE_FAILURE);
		}finally{
			if(prepareStatement!=null){
				try {
					prepareStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connetion !=null){
				try {
					connetion.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public List<TeachInfo> findAllTeachInfo() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT ti.id, s.staff_id,s.name,c.course_id,c.name,c.hours,c.credit,c.nature FROM tb_course c ");
		sql.append("JOIN tb_teach_info ti ON c.course_id=ti.course_id AND c.is_del=0 ");
		sql.append("JOIN tb_staff s ON s.staff_id=ti.teacher_id AND s.is_del=0 ");
		Connection connetion = DBUtil.getConnetion();
		List<TeachInfo> result = new ArrayList<TeachInfo>();
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connetion.prepareStatement(sql.toString());
			ResultSet executeQuery = prepareStatement.executeQuery();
			while(executeQuery.next()){
				int id = executeQuery.getInt(1);
				String staff_id = executeQuery.getString(2);
				String staff_name = executeQuery.getString(3);
				String course_id = executeQuery.getString(4);
				String course_name = executeQuery.getString(5);
				String course_hours = executeQuery.getString(6);
				String course_credit = executeQuery.getString(7);
				String course_nature = executeQuery.getString(8);
				TeachInfo teachInfo = new TeachInfo();
				Staff staff = new Staff();
				staff.setStaffId(staff_id);
				staff.setName(staff_name);
				Course course = new Course();
				course.setCourse_id(course_id);
				course.setName(course_name);
				course.setHours(course_hours);
				course.setCredit(course_credit);
				course.setNature(course_nature);
				teachInfo.setId(id);
				teachInfo.setCourse(course);
				teachInfo.setStaff(staff);
				result.add(teachInfo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(prepareStatement != null){
				try {
					prepareStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connetion != null){
				try {
					connetion.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	
	public TeachInfo findTeachInfoOne(int id) {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT s.staff_id,s.name,c.course_id,c.name,c.hours,c.credit,c.nature FROM tb_course c ");
		sql.append("JOIN tb_teach_info ti ON c.course_id=ti.course_id AND c.is_del=0 ");
		sql.append("JOIN tb_staff s ON s.staff_id=ti.teacher_id AND s.is_del=0 ");
		sql.append("WHERE ti.id=? ");
		Connection connetion = DBUtil.getConnetion();
		PreparedStatement prepareStatement = null;
		Staff staff = null;
		TeachInfo teachInfo = null;
		try {
			prepareStatement = connetion.prepareStatement(sql.toString());
			prepareStatement.setInt(id, 1);
			ResultSet executeQuery = prepareStatement.executeQuery();
			if(executeQuery.next()){
				String staff_id = executeQuery.getString(1);
				String staff_name = executeQuery.getString(2);
				String course_name = executeQuery.getString(3);
				String course_id = executeQuery.getString(4);
				String course_hours = executeQuery.getString(5);
				String course_credit = executeQuery.getString(6);
				String course_nature = executeQuery.getString(7);
				teachInfo = new TeachInfo();
				staff = new Staff();
				staff.setStaffId(staff_id);
				staff.setName(staff_name);
				Course course = new Course();
				course.setCourse_id(course_id);
				course.setName(course_name);
				course.setHours(course_hours);
				course.setCredit(course_credit);
				course.setNature(course_nature);
				teachInfo.setId(id);
				teachInfo.setCourse(course);
				teachInfo.setStaff(staff);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			if(prepareStatement != null){
				try {
					prepareStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connetion != null){
				try {
					connetion.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return teachInfo;
	}
	
	

}
