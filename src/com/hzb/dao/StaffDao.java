package com.hzb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hzb.bean.Staff;
import com.hzb.util.DBUtil;

public class StaffDao implements BaseDao<Staff>{

	@Override
	public int save(Staff entity) {
		int result = -1;
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO tb_staff(");
		sql.append("staff_id,name,sex,educational,depart_id,college,health,job_name,position,rewards_punishment");
		sql.append(") VALUES(?,?,?,?,?,?,?,?,?,?)");
		Connection connetion = DBUtil.getConnetion();
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connetion.prepareStatement(sql.toString());
			prepareStatement.setString(1, entity.getStaffId());
			prepareStatement.setString(2, entity.getName());
			prepareStatement.setInt(3, entity.getSex());
			prepareStatement.setInt(4, entity.getEducation());
			prepareStatement.setString(5, entity.getDepart().getDepartId());
			prepareStatement.setString(6, entity.getCollege());
			prepareStatement.setString(7, entity.getHealth());
			prepareStatement.setString(8, entity.getJobName());
			prepareStatement.setString(9, entity.getPosition());
			prepareStatement.setString(10, entity.getRewardsPunishment());
			result = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
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

	@Override
	public int update(Staff entity) {
		int result = -1;
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE tb_staff SET ");
		sql.append("staff_id=?,name=?,sex=?,educational=?,depart_id=?,college=?,health=?,job_name=?,position=?,rewards_punishment=?,state=?,is_del=?");
		sql.append("WHERE id=?");
		Connection connetion = DBUtil.getConnetion();
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connetion.prepareStatement(sql.toString());
			prepareStatement.setString(1, entity.getStaffId());
			prepareStatement.setString(2, entity.getName());
			prepareStatement.setInt(3, entity.getSex());
			prepareStatement.setInt(4, entity.getEducation());
			prepareStatement.setString(5, entity.getDepart().getDepartId());
			prepareStatement.setString(6, entity.getCollege());
			prepareStatement.setString(7, entity.getHealth());
			prepareStatement.setString(8, entity.getJobName());
			prepareStatement.setString(9, entity.getPosition());
			prepareStatement.setString(10, entity.getRewardsPunishment());
			prepareStatement.setInt(11, entity.getState());
			prepareStatement.setInt(12, entity.getIsDel());
			prepareStatement.setInt(13, entity.getId());
			result = prepareStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
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

	@Override
	public List<Staff> findAll() {
		String sql ="SELECT * FROM tb_staff WHERE state=0 AND is_del=0";
		Connection connetion = DBUtil.getConnetion();
		List<Staff> result = new ArrayList<Staff>();
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connetion.prepareStatement(sql);
			ResultSet executeQuery = prepareStatement.executeQuery();
			while(executeQuery.next()){
				int id = executeQuery.getInt(1);
				String staff_id = executeQuery.getString(2);
				String name = executeQuery.getString(3);
				int sex = executeQuery.getInt(4);
				int educational = executeQuery.getInt(5);
				String depart_id = executeQuery.getString(6);
				String college = executeQuery.getString(7);
				String health = executeQuery.getString(8);
				String job_name = executeQuery.getString(9);
				String position = executeQuery.getString(10);
				String rewards_punishment = executeQuery.getString(11);
				int state = executeQuery.getInt(12);
				int isDel = executeQuery.getInt(13);
				Staff staff = new Staff(id, staff_id, name, sex, educational, depart_id, college, health, job_name, position, rewards_punishment, state, isDel);
				result.add(staff);
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
	public Staff findOne(int id) {
		String sql ="SELECT * FROM tb_staff WHERE state=0 AND is_del=0 AND id=?";
		Connection connetion = DBUtil.getConnetion();
		Staff staff=null;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connetion.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			ResultSet executeQuery = prepareStatement.executeQuery();
			if(executeQuery.next()){
				String staff_id = executeQuery.getString(2);
				String name = executeQuery.getString(3);
				int sex = executeQuery.getInt(4);
				int educational = executeQuery.getInt(5);
				String depart_id = executeQuery.getString(6);
				String college = executeQuery.getString(7);
				String health = executeQuery.getString(8);
				String job_name = executeQuery.getString(9);
				String position = executeQuery.getString(10);
				String rewards_punishment = executeQuery.getString(11);
				int state = executeQuery.getInt(12);
				int isDel = executeQuery.getInt(13);
				staff = new Staff(id, staff_id, name, sex, educational, depart_id, college, health, job_name, position, rewards_punishment, state, isDel);
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
		return staff;
	}
	
	
	public Staff findOne(String staffId) {
		String sql ="SELECT * FROM tb_staff WHERE state=0 AND is_del=0 AND staff_id=?";
		Connection connetion = DBUtil.getConnetion();
		Staff staff=null;
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connetion.prepareStatement(sql);
			prepareStatement.setString(1, staffId);
			ResultSet executeQuery = prepareStatement.executeQuery();
			if(executeQuery.next()){
				int id = executeQuery.getInt(1);
				String staff_id = executeQuery.getString(2);
				String name = executeQuery.getString(3);
				int sex = executeQuery.getInt(4);
				int educational = executeQuery.getInt(5);
				String depart_id = executeQuery.getString(6);
				String college = executeQuery.getString(7);
				String health = executeQuery.getString(8);
				String job_name = executeQuery.getString(9);
				String position = executeQuery.getString(10);
				String rewards_punishment = executeQuery.getString(11);
				int state = executeQuery.getInt(12);
				int isDel = executeQuery.getInt(13);
				staff = new Staff(id, staff_id, name, sex, educational, depart_id, college, health, job_name, position, rewards_punishment, state, isDel);
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
		return staff;
	}
	
	/**
	 * 
	 * @param sstaffId 员工编号
	 * @param sname 员工名字
	 * @param ssex 员工性别
	 * @param sdepartId 所属部门
	 * @param sjobName 职称
	 * @return
	 */
	public List<Staff> findAll(String sstaffId,String sname,Integer ssex,Integer sdepartId,String sjobName) {
//		String sql2 ="SELECT * FROM tb_staff WHERE 1=1 AND state=0 AND is_del=0 AND (staff_id=? OR name like '?%' OR sex=? OR depart_id=? OR job_name like '?%')";
		StringBuffer sql = new StringBuffer();
		List<Object> params = new ArrayList<Object>();
		sql.append("SELECT * FROM tb_staff ");
		sql.append("WHERE (1=1 ");
		if(sstaffId != null && sstaffId.length() > 5){
			//员工编号要大于5个长度
			sql.append(" OR ").append(" staff_id = ? ");
			params.add(sstaffId);
		}
		if(sname != null && !"".equals(sname)){
			sql.append(" OR ").append(" name LIKE ? ");
			params.add(sname + "%");
		}
		if(ssex != null && ssex>=0){
			sql.append(" OR ").append(" sex = ? ");
			params.add(ssex);
		}
		if(sdepartId != null && sdepartId>=0){
			sql.append(" OR ").append(" depart_id = ? ");
			params.add(sdepartId);
		}
		if(sjobName != null && !"".equals(sjobName)){
			sql.append(" OR ").append(" job_name LIKE ? ");
			params.add(sjobName + "%");
		}
		sql.append(" ) AND state = 0 AND is_del = 0");
		Connection connetion = DBUtil.getConnetion();
		List<Staff> result = new ArrayList<Staff>();
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connetion.prepareStatement(sql.toString());
			for(int i=0;i<params.size();i++){
				prepareStatement.setObject(1+i, params.get(i));
			}
			ResultSet executeQuery = prepareStatement.executeQuery();
			while(executeQuery.next()){
				int id = executeQuery.getInt(1);
				String staff_id = executeQuery.getString(2);
				String name = executeQuery.getString(3);
				int sex = executeQuery.getInt(4);
				int educational = executeQuery.getInt(5);
				String depart_id = executeQuery.getString(6);
				String college = executeQuery.getString(7);
				String health = executeQuery.getString(8);
				String job_name = executeQuery.getString(9);
				String position = executeQuery.getString(10);
				String rewards_punishment = executeQuery.getString(11);
				int state = executeQuery.getInt(12);
				int isDel = executeQuery.getInt(13);
				Staff staff = new Staff(id, staff_id, name, sex, educational, depart_id, college, health, job_name, position, rewards_punishment, state, isDel);
				result.add(staff);
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

}
