package com.example.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Parser {


	// TODO JSON

	/**
	 * json數據解析 [{"":"","":""},{"":"","":""},{"":"","":""}] 單層嵌套模式
	 * 暫不支持[,,,,](數組中嵌套的不是Json對象而是普通對象)
	 * 
	 * @param clazz
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> parserJsonToList(Class<T> clazz, String json)
			throws Exception {
		Field[] names = clazz.getFields();
		JSONArray array = new JSONArray(json);
		List<T> list = new ArrayList<T>();
		int length = array.length();
		for (int i = 0; i < length; i++) {
			T object = clazz.newInstance();
			JSONObject jsonobj = array.getJSONObject(i);
			for (int j = 0; j < names.length; j++) {
				names[j].set(object, jsonobj.get(names[j].getName()));
			}
			list.add(object);
		}
		return list;
	}

	/**
	 * json數據解析 [{"":"","":""},{"":"","":""},{"":"","":""}]
	 * 
	 * @param clazz
	 * @param array
	 * @return
	 * @throws Exception
	 */
	public static <T> List<T> parserJsonToList(Class<T> clazz, JSONArray array)
			throws Exception {
		Field[] names = clazz.getFields();
		List<T> list = new ArrayList<T>();
		int length = array.length();
		for (int i = 0; i < length; i++) {
			T object = clazz.newInstance();
			JSONObject jsonobj = array.getJSONObject(i);
			for (int j = 0; j < names.length; j++) {
				names[j].set(object, jsonobj.get(names[j].getName()));
			}
			list.add(object);
		}
		return list;
	}

	/**
	 * json數據解析 {} 簡單模式
	 * 
	 * @param clazz
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public static <T> T parserJsonToObject(Class<T> clazz, String json)
			throws Exception {
		Field[] names = clazz.getFields();
		JSONObject jsonobj = new JSONObject(json);
		T object = clazz.newInstance();
		for (int i = 0; i < names.length; i++) {
			Object hah = jsonobj.get(names[i].getName());
			if (hah instanceof JSONObject) {
				hah = parserJsonToObject(names[i].getType(), (JSONObject) hah);
			}
			names[i].set(object, hah);
		}
		return object;
	}

	/**
	 * json數據解析 {} 簡單模式
	 * 
	 * @param clazz
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public static <T> T parserJsonToObject(Class<T> clazz, JSONObject jsonobj)
			throws Exception {
		Field[] names = clazz.getFields();
		T object = clazz.newInstance();
		for (int i = 0; i < names.length; i++) {
			Object hah = jsonobj.get(names[i].getName());
			if (hah instanceof JSONObject) {
				hah = parserJsonToObject(names[i].getType(), (JSONObject) hah);
			}
			names[i].set(object, hah);
		}
		return object;
	}

	/**
	 * trim JSON Object
	 * 
	 * @param json
	 * @param key
	 * @return
	 */
	public static String trimMessage(String json, String key) {
		try {
			JSONObject obj = new JSONObject(json);
			return obj.getString(key);
		} catch (JSONException e) {
			e.printStackTrace();
			return "";
		}
	}
}
