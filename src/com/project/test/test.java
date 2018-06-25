package com.project.test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class test {
	public static void main(String[] args) {
		String s = "Hello World My First Unit Test. Test";
		test test1 = new test();
		test1.text(s);
	}
	
	
	public void text(String s){
		HashMap<Object, Object> map = new HashMap<Object, Object>();
		String s1[] = s.split(" ");
		for (int i = 0; i < s1.length; i++) {
			String s2[] = new String[]{};
			if(s1[i].contains(",")){
				s2 = s1[i].split(",");
				for (int j = 0; j < s2.length; j++) {
					if(!map.containsKey(s2[j])){
						map.put(s2[j], 1);
					}else{
						map.put(s2[j], Integer.valueOf(map.get(s2[j]).toString())+1);
					}
				}
			}else if(s1[i].contains(".")){
				s2 = s1[i].split("\\.");
				for (int j = 0; j < s2.length; j++) {
					if(!map.containsKey(s2[j])){
						map.put(s2[j], 1);
					}else{
						map.put(s2[j], Integer.valueOf(map.get(s2[j]).toString())+1);
					}
				}
			}else{
				if(!map.containsKey(s1[i])){
					map.put(s1[i], 1);
				}else{
					map.put(s1[i], Integer.valueOf(map.get(s1[i]).toString())+1);
				}
			}
		}
		
		Set ss = map.keySet();
		Iterator i = ss.iterator();
		while (i.hasNext()) {
			Object o = i.next();
			System.out.println(o + "出现了" + map.get(o)+"次");
		}
 	}
}
