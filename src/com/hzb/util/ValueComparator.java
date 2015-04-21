package com.hzb.util;

import java.util.Comparator;
import java.util.Map;

public class ValueComparator implements Comparator<Map.Entry<String, Integer>> {
	public int compare(Map.Entry<String, Integer> mp1, Map.Entry<String, Integer> mp2) {
		return mp1.getValue() - mp2.getValue();
	}
}