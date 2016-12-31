package com.hjcr.wechat.entity;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Cacheable
@Table(name="amter")
@Entity
public class Test {

	
	private int TestId;
	private String TestName;
	
	
	@GeneratedValue
	@Id
	public int getTestId() {
		return TestId;
	}
	public void setTestId(int testId) {
		TestId = testId;
	}
	public String getTestName() {
		return TestName;
	}
	public void setTestName(String testName) {
		TestName = testName;
	}
	public Test(int testId, String testName) {
		super();
		TestId = testId;
		TestName = testName;
	}
	public Test() {
		super();
	}
	
	
	
}
