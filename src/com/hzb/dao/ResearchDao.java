package com.hzb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hzb.bean.Research;
import com.hzb.util.DBUtil;

public class ResearchDao implements BaseDao<Research> {

	@Override
	public int save(Research entity) {
		int result = -1;
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO tb_research(");
		sql.append("teacher_id,direction,situation,patent,other");
		sql.append(") VALUES(?,?,?,?,?)");
		Connection connetion = DBUtil.getConnetion();
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connetion.prepareStatement(sql.toString());
			prepareStatement.setString(1, entity.getStaff().getStaffId());
			prepareStatement.setString(2, entity.getDirection());
			prepareStatement.setString(3, entity.getSituation());
			prepareStatement.setString(4, entity.getPatent());
			prepareStatement.setString(5, entity.getOther());
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
	public int update(Research entity) {
		int result = -1;
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE tb_research SET ");
		sql.append("teacher_id=?,direction=?,situation=?,patent=?,other=?,id_del=?");
		sql.append("WHERE id=?");
		Connection connetion = DBUtil.getConnetion();
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connetion.prepareStatement(sql.toString());
			prepareStatement.setString(1, entity.getStaff().getStaffId());
			prepareStatement.setString(2, entity.getDirection());
			prepareStatement.setString(3, entity.getSituation());
			prepareStatement.setString(4, entity.getPatent());
			prepareStatement.setString(5, entity.getOther());
			prepareStatement.setInt(6, entity.getIs_del());
			prepareStatement.setInt(7, entity.getId());
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
	public List<Research> findAll() {
		String sql ="SELECT * FROM tb_research WHERE is_del=0";
		Connection connetion = DBUtil.getConnetion();
		List<Research> result = new ArrayList<Research>();
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connetion.prepareStatement(sql);
			ResultSet executeQuery = prepareStatement.executeQuery();
			while(executeQuery.next()){
				int id = executeQuery.getInt(1);
				String teacher_id = executeQuery.getString(2);
				String direction = executeQuery.getString(3);
				String situation = executeQuery.getString(4);
				String patent = executeQuery.getString(5);
				String other = executeQuery.getString(6);
				int isDel = executeQuery.getInt(7);
				Research research = new Research(id, teacher_id, direction, situation, patent, other, isDel);
				result.add(research);
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
	
	public List<Research> findAll(String steacherId,String sdirection) {
//		String sql2 = "SELECT * FROM tb_research WHERE is_del=0";
		StringBuffer sql = new StringBuffer();
		List<String> params = new ArrayList<String>();
		sql.append("SELECT * FROM tb_research WHERE (1=1");
		if(steacherId != null && steacherId.length() > 5){
			sql.append(" OR ").append("teacher_id = ?");
			params.add(steacherId);
		}
		if(sdirection!=null && !"".equals(sdirection)){
			sql.append(" OR ").append("direction like ?");
			params.add(sdirection+"%");
		}
		sql.append(") AND is_del=?");
		Connection connetion = DBUtil.getConnetion();
		List<Research> result = new ArrayList<Research>();
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connetion.prepareStatement(sql.toString());
			for (int i = 0; i < params.size(); i++) {
				prepareStatement.setString(i+1,params.get(i));
			}
			ResultSet executeQuery = prepareStatement.executeQuery();
			while(executeQuery.next()){
				int id = executeQuery.getInt(1);
				String teacher_id = executeQuery.getString(2);
				String direction = executeQuery.getString(3);
				String situation = executeQuery.getString(4);
				String patent = executeQuery.getString(5);
				String other = executeQuery.getString(6);
				int isDel = executeQuery.getInt(7);
				Research research = new Research(id, teacher_id, direction, situation, patent, other, isDel);
				result.add(research);
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
	public Research findOne(int id) {
		String sql ="SELECT * FROM tb_research WHERE id=? AND is_del=0 ";
		Connection connetion = DBUtil.getConnetion();
		PreparedStatement prepareStatement = null;
		Research research = null;
		try {
			prepareStatement = connetion.prepareStatement(sql);
			ResultSet executeQuery = prepareStatement.executeQuery();
			while(executeQuery.next()){
				String teacher_id = executeQuery.getString(2);
				String direction = executeQuery.getString(3);
				String situation = executeQuery.getString(4);
				String patent = executeQuery.getString(5);
				String other = executeQuery.getString(6);
				int isDel = executeQuery.getInt(7);
				research = new Research(id, teacher_id, direction, situation, patent, other, isDel);
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
		return research;
	}

}
