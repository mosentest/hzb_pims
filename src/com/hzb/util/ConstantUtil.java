package com.hzb.util;

import java.util.HashMap;
import java.util.Map;

public class ConstantUtil {
	public final static String SAVE_FAILURE = "==>保存失败";
	public final static String UPDATE_FAILURE = "==>更新失败";
	public final static String DELETE_FAILURE = "==>删除失败";
	public final static String SELECT_FAILURE = "==>查询失败";
	
	public  static Map<String,Integer> sexMap = new HashMap<String,Integer>();
	//学历，0小学以下，1初中，2高中，3中专，4大专，5本科，6研究生，7博士生，8博士后，9社会人士，10其他
	public  static Map<String,Integer> eduMap = new HashMap<String,Integer>();
	static{
		sexMap.put("--请选择--", -1);
		sexMap.put("男", 0);
		sexMap.put("女", 1);

		eduMap.put("--请选择--", -1);
		eduMap.put("小学以下", 0);
		eduMap.put("初中", 1);
		eduMap.put("高中", 2);
		eduMap.put("中专", 3);
		eduMap.put("大专", 4);
		eduMap.put("本科", 5);
		eduMap.put("研究生", 6);
		eduMap.put("博士生", 7);
		eduMap.put("博士后", 8);
		eduMap.put("社会人士", 9);
		eduMap.put("其他", 10);
	}
	
}
