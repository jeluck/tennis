package com.project.test;

import java.util.ArrayList;
import java.util.List;

public class Product {
	public static void main(String[] args) {
		ProductAttr attr = new ProductAttr();
		attr.setDd(1);
		attr.setId(1);
		attr.setIde(10);
		attr.setName("小王");
		attr.setNamee("牛奶");
		ProductAttr attr1 = new ProductAttr();
		attr1.setDd(2);
		attr1.setId(2);
		attr1.setIde(11);
		attr1.setName("小照");
		attr1.setNamee("面包");
		ProductAttr attr2 = new ProductAttr();
		attr2.setDd(3);
		attr2.setId(1);
		attr2.setIde(12);
		attr2.setName("小王");
		attr2.setNamee("jj");
		
		List<ProductAttr> attrList = new ArrayList<>();
		attrList.add(attr);
		attrList.add(attr1);
		attrList.add(attr2);
		List<Integer> list = new ArrayList<>();
		List<ProductAttr> attrList3 = new ArrayList<>();
		for (ProductAttr o : attrList) {
			ProductAttr a = new ProductAttr();
			if(!list.contains(o.getId())){
				list.add(o.getId());
				a.setDd(o.getDd());
				a.setName(o.getName());
				a.setId(o.getId());
				attrList3.add(a);
			}
			
			for (ProductAttr o2 : attrList3) {
				if(o.getId() == o2.getId()){
					TestName t = new TestName();
					t.setId(o.getIde());
					t.setName(o.getNamee());
					o2.getTestName().add(t);
				}
			}
		}
		for (ProductAttr o : attrList3) {
			System.err.println(o.getId()+"-----"+o.getDd()+"------"+o.getName());
			List<TestName> testName = o.getTestName();
			for (TestName n : testName) {
				System.err.println("***********"+n.getId()+"--------------"+n.getName());
			}
		}
		
		
//		for (ProductAttr o : attrList2) {
//			System.err.println(o.getId()+"-----"+o.getIde()+"------"+o.getName());
//		}
//		for (int i = 0; i < list.size(); i++) {
//			System.err.println("----------------"+list.get(i));
//		}
	}
}
