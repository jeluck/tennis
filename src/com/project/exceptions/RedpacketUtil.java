package com.project.exceptions;

import java.util.Random;
import java.util.regex.Pattern;


public class RedpacketUtil {
	//设置两个数组，放金额和出现的概率
	private static int []money;
	private static int []chance;
	//四种红包种类
	public static int Redpacket(int m1,int m2,int m3,int m4,float c1,float c2,float c3,float c4){
		int moneys=0;
		money=new int[]{m1,m2,m3,m4};
		//转换格式
		int b1=(int) (c1*100);
		int b2=(int) (c2*100);
		int b3=(int) (c3*100);
		int b4=(int) (c4*100);
		chance=new int[]{b1,b2,b3,b4};
		int aa=new Random().nextInt(100)+1;
		for(int j=0;j<4;j++){
			if(aa<=chance[j]){
				return money[j];
			}else{
				aa=aa-chance[j];
			}
		}
		return moneys;
	}
	//任意
	public static int Redpacket(int[] n,int []c){
		int moneys=0;
		money=n;
		chance=c;
		int aa=new Random().nextInt(100)+1;
		for(int j=0;j<n.length;j++){
			if(aa<=chance[j]){
				return money[j];
			}else{
				aa=aa-chance[j];
			}
		}
		return moneys;
	}
	
	//五种红包种类
	public static int Redpacket(int m1,int m2,int m3,int m4,int m5,float c1,float c2,float c3,float c4,float c5){
			int moneys=0;
			money=new int[]{m1,m2,m3,m4,m5};
			//转换格式
			int b1=(int) (c1*100);
			int b2=(int) (c2*100);
			int b3=(int) (c3*100);
			int b4=(int) (c4*100);
			int b5=(int) (c5*100);
			chance=new int[]{b1,b2,b3,b4,b5};
			int aa=new Random().nextInt(100)+1;
			for(int j=0;j<5;j++){
				if(aa<=chance[j]){
					return money[j];
				}else{
					aa=aa-chance[j];
				}
			}
			return moneys;
		}
	//六种红包种类
	public int Redpacket(int m1,int m2,int m3,int m4,int m5,int m6,
				float c1,float c2,float c3,float c4,float c5,float c6){
				int moneys=0;
				money=new int[]{m1,m2,m3,m4,m5,m6};
				//转换格式
				int b1=(int) (c1*100);
				int b2=(int) (c2*100);
				int b3=(int) (c3*100);
				int b4=(int) (c4*100);
				int b5=(int) (c5*100);
				int b6=(int) (c6*100);
				chance=new int[]{b1,b2,b3,b4,b5,b6};
				int aa=new Random().nextInt(100)+1;
				for(int j=0;j<6;j++){
					if(aa<=chance[j]){
						return money[j];
					}else{
						aa=aa-chance[j];
					}
				}
				return moneys;
			}
	public static boolean isInteger(String str) {    
	    Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");    
	    return pattern.matcher(str).matches();    
	  }
	public static void main(String[] args) {
//		String name = "20:0.7|50:0.15|100:0.1|300:0.05";
//		String namess = "";
//		String [] names = name.split("\\|");
//		for (int i = 0; i < names.length; i++) {
//			namess += names[i]+":";
//			
//		}
//		String n = "";
//		String y = "";
//		String [] s = namess.split(":");
//		for (int i = 0; i < s.length; i++) {
//			if(isInteger(s[i])){
//				n += s[i]+",";
//			}else{
//				int k = (int) (Float.valueOf(s[i])*100);
//				y += k+",";
//
//			}
//		}
//		
//		System.err.println(Redpacket(change(n.split(",")),change(y.split(","))));
		System.out.println(Redpacket("20:0.7/50:0.15/100:0.1/300:0.05"));

	}
	
	public static int[] change(String []s){
		int[] ia=new int[s.length];
		for(int i=0;i<s.length;i++){
		   ia[i]=Integer.valueOf(s[i]);
		}
		return ia;
	}
	
	
	public static int Redpacket(String string){
		int moneys=0;
		//设置一个装金额和概率的数组
		String [] strings=string.split("/");
		//产生随机数	
		
		int aa=new Random().nextInt(100)+1;
		for(int i=0;i<strings.length;i++){
			//设置一个临时装金额和概率分开后的数组
			String[] mc=strings[i].split(":");
			for (int j = 0; j < mc.length; j++) {
				System.err.println(mc[j]);
			}
			//得到金额
			int money=Integer.parseInt(mc[0]);
			//得到概率
			int chance=(int) (Float.parseFloat(mc[1])*100);
			if(aa<=chance){
				return money;
			}else{
				aa=aa-chance;
			}
		}
		return moneys;
	}

}
