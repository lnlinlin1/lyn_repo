package com.lyn.javacore.collection;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapTest {

	public static void main(String[] args) {

		LinkedHashMap<String,String> linkedHashMap = new LinkedHashMap<String, String>();
		linkedHashMap.put("1", "1");
		linkedHashMap.put("2", "2");
		linkedHashMap.put(null, "null");
		
		System.out.println(linkedHashMap.get(null));
		
		for(String str : linkedHashMap.keySet()){
			System.out.println("key="+str+", value="+linkedHashMap.get(str));
		}
		
		Map<String, String> linkedHashMap1 = Collections.synchronizedMap(new LinkedHashMap<String, String>(10, 0.75f, true));
		linkedHashMap1.put("1", "1");
		linkedHashMap1.put("2", "2");
		linkedHashMap1.put("3", "3");
		
		for(String key : linkedHashMap1.keySet()){
			System.out.println(key);
		}
		
		linkedHashMap1.get("1");
		linkedHashMap1.get("2");
		
		for(String key : linkedHashMap1.keySet()){
			System.out.println(key);
		}
		
	}

}
