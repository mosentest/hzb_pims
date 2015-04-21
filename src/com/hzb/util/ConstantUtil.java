package com.hzb.util;

import java.util.HashMap;
import java.util.Map;

public class ConstantUtil {
	
	public final static String TIP = "提示";
	public final static String HAVE = "已存在";
	
	public final static String SAVE_FAILURE = "==>保存失败";
	public final static String UPDATE_FAILURE = "==>更新失败";
	public final static String DELETE_FAILURE = "==>删除失败";
	public final static String SELECT_FAILURE = "==>查询失败";
	
	public final static String SAVE_SUCCESS = "保存成功";
	public final static String UPDATE_SUCCESS = "更新成功";
	public final static String DELETE_SUCCESS = "删除成功";
	public final static String SELECT_SUCCESS = "查询成功";
	
	public  static Map<String,Integer> sexMap = new HashMap<String,Integer>();
	//学历，0小学以下，1初中，2高中，3中专，4大专，5本科，6研究生，7博士生，8博士后，9社会人士，10其他
	public  static Map<String,Integer> eduMap = new HashMap<String,Integer>();
	//0在职，1转出，2辞职，3退休
	public  static Map<String,Integer> stateMap = new HashMap<String,Integer>();
	
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

		stateMap.put("--请选择--", -1);
		stateMap.put("在职", 0);
		stateMap.put("转出", 1);
		stateMap.put("辞职", 2);
		stateMap.put("退休", 3);
	}
	
	/**
	 * 根据value值取key
	 * @param hm
	 * @param value
	 * @return
	 */
	public static String getKey(Map<String,Integer> hm, int value) {
		String key = null;
		for (String getKey : hm.keySet()) {
			if (hm.get(getKey).equals(value)) {
				key = getKey;
			}
		}
		return key;
	}

	/**
	 * 根据key值取value
	 * @param hm
	 * @param key
	 * @return
	 */
	public static int getValue(Map<String, Integer> hm, String key) {
		int value = -1;
		for (String getKey : hm.keySet()) {
			if (getKey == key) {
				value = hm.get(getKey);
			}
		}
		return value;
	}

	/**
	 * 检验是否是大于0的数字，并小于200
	 * 
	 * @param num
	 */
	public static boolean checkNum(String num) {
		boolean isNum = num.matches("^[0-1]?[1-9][0-9]?$");
		return isNum;
	}
	
	public static boolean checkId(String id) {
		boolean matches = id.matches("^[a-zA-Z1-9]{1}+[0-9]{4,}+$");
		return matches;
	}
	
//	public static void main(String[] args) {
//		boolean checkId = checkId("156");
//		System.out.println(checkId);
//	}
}
