package com.project.exceptions;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;


public class Redpacket {
	private static int types;
	private static int []money;
	private static int []chance;
	public static void main(String[] args) {
		String name = "20:0.7|50:0.15|100:0.1|300:0.05";
		String namess = "";
		String [] names = name.split("\\|");
		for (int i = 0; i < names.length; i++) {
			namess += names[i]+":";
			
		}
		String [] s = namess.split(":");
		for (int i = 0; i < s.length; i++) {
			System.out.println(s[i]);
		}
//		
	}
	public static void cesi(){
		//测试参数，用来统计红包出现的次数
				int []bb=new int [types];
				System.out.println("下面随机生成100个红包，用来测试");
				for(int i=1;i<=100;i++){
					int aa=new Random().nextInt(100)+1;
					for(int j=0;j<types;j++){
						if(aa<=chance[j]){
							System.out.println("第"+i+"个红包金额为："+money[j]);
							bb[j]++;
							break;
						}else{
							aa=aa-chance[j];
						}
					}
					
				}
				for(int i=0;i<types;i++){
					System.out.println("金额为"+money[i]+"的红包出现"+bb[i]);
				}
				
				
				
	}
	

}
