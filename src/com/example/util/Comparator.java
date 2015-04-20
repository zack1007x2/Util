package com.example.util;

import java.util.ArrayList;
import java.util.Arrays;

public class Comparator {
	/**
	 * find difference item of A2
	 * 
	 * @param A1
	 * @param A2
	 * @return difference item of A2
	 */
	public static String[] findArrayDifference(String[] A1, String[] A2) {
		ArrayList<String> a1 = new ArrayList<String>(Arrays.asList(A1));
		ArrayList<String> a2 = new ArrayList<String>(Arrays.asList(A2));

		for (String s : a1) {
			a2.remove(s);
		}

		String[] diffArr = new String[a2.size()];
		diffArr = a2.toArray(diffArr);
		return diffArr;
	}
}
