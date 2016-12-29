package com.hjcr.wechat.entity;

public class Test {

	
	private int TestId;
	private String TestName;
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
