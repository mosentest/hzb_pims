package com.hzb.util;

import java.util.HashMap;
import java.util.Map;

public class ConstantUtil {
	public final static String SAVE_FAILURE = "==>保存失败";
	public final static String UPDATE_FAILURE = "==>更新失败";
	public final static String DELETE_FAILURE = "==>删除失败";
	public final static String SELECT_FAILURE = "==>查询失败";
	
	public  static Map<Integer,String> sexMap = new HashMap<Integer,String>();
	//学历，0小学以下，1初中，2高中，3中专，4大专，5本科，6研究生，7博士生，8博士后，9社会人士，10其他
	public  static Map<Integer,String> eduMap = new HashMap<Integer,String>();
	static{
		sexMap.put(-1, "--请选中--");
		sexMap.put(0, "男");
		sexMap.put(1, "女");
		
		eduMap.put(-1, "--请选中--");
		eduMap.put(0, "小学以下");
		eduMap.put(1, "初中");
		eduMap.put(2, "高中");
		eduMap.put(3, "中专");
		eduMap.put(4, "大专");
		eduMap.put(5, "本科");
		eduMap.put(6, "研究生");
		eduMap.put(7, "博士生");
		eduMap.put(8, "博士后");
		eduMap.put(9, "社会人士");
		eduMap.put(10, "其他");
	}
	
}
