package com.hzb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hzb.bean.Depart;
import com.hzb.util.DBUtil;

public class DepartDao implements BaseDao<Depart> {

	@Override
	public int save(Depart entity) {
		int result = -1;
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO tb_depart(");
		sql.append("depart_id,depart_name");
		sql.append(") VALUES(?,?)");
		Connection connetion = DBUtil.getConnetion();
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connetion.prepareStatement(sql.toString());
			prepareStatement.setString(1, entity.getDepartId());
			prepareStatement.setString(2, entity.getDepartName());
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
	public int update(Depart entity) {
		int result = -1;
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE tb_depart SET ");
		sql.append("depart_id=?,depart_name=?,is_del=? ");
		sql.append("WHERE id=?");
		Connection connetion = DBUtil.getConnetion();
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connetion.prepareStatement(sql.toString());
			prepareStatement.setString(1, entity.getDepartId());
			prepareStatement.setString(2, entity.getDepartName());
			prepareStatement.setInt(3, entity.getId());
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
	public List<Depart> findAll() {
		String sql ="SELECT * FROM tb_depart WHERE is_del=0";
		Connection connetion = DBUtil.getConnetion();
		List<Depart> result = new ArrayList<Depart>();
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = connetion.prepareStatement(sql);
			ResultSet executeQuery = prepareStatement.executeQuery();
			while(executeQuery.next()){
				int id = executeQuery.getInt(1);
				String depart_id = executeQuery.getString(2);
				String depart_name = executeQuery.getString(3);
				int is_del = executeQuery.getInt(4);
				Depart staff = new Depart(id, depart_id, depart_name, is_del);
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


	public Depart findOne(String departId) {
		String sql ="SELECT * FROM tb_depart WHERE is_del=0 AND depart_id=?";
		Connection connetion = DBUtil.getConnetion();
		PreparedStatement prepareStatement = null;
		Depart depart = null;
		try {
			prepareStatement = connetion.prepareStatement(sql);
			prepareStatement.setString(1, departId);
			ResultSet executeQuery = prepareStatement.executeQuery();
			if(executeQuery.next()){
				int id = executeQuery.getInt(1);
				String depart_id = executeQuery.getString(2);
				String depart_name = executeQuery.getString(3);
				int is_del = executeQuery.getInt(4);
				depart = new Depart(id, depart_id, depart_name, is_del);
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
		return depart;
	}
	
	@Override
	public Depart findOne(int id) {
		String sql ="SELECT * FROM tb_depart WHERE is_del=0 AND id=?";
		Connection connetion = DBUtil.getConnetion();
		PreparedStatement prepareStatement = null;
		Depart depart = null;
		try {
			prepareStatement = connetion.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			ResultSet executeQuery = prepareStatement.executeQuery();
			if(executeQuery.next()){
				String depart_id = executeQuery.getString(2);
				String depart_name = executeQuery.getString(3);
				int is_del = executeQuery.getInt(4);
				depart = new Depart(id, depart_id, depart_name, is_del);
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
		return depart;
	}

}
