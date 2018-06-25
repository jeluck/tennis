package com.project.test;

import java.util.ArrayList;
import java.util.List;

public class ProductAttr {

	private int id;
	private int ide;
	private String name;
	private String namee;
	private int dd;
	private List<TestName> testName = new ArrayList<>();
	public List<TestName> getTestName() {
		return testName;
	}
	public void setTestName(List<TestName> testName) {
		this.testName = testName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIde() {
		return ide;
	}
	public void setIde(int ide) {
		this.ide = ide;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNamee() {
		return namee;
	}
	public void setNamee(String namee) {
		this.namee = namee;
	}
	public int getDd() {
		return dd;
	}
	public void setDd(int dd) {
		this.dd = dd;
	}
	
	
}
