package com.model;

public class HomeLoan extends Loan {

	private String propertyAddress;
	private int propertyValue;

	public HomeLoan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HomeLoan(String propertyAddress, int propertyValue) {
		super();
		this.propertyAddress = propertyAddress;
		this.propertyValue = propertyValue;
	}

	public String getPropertyAddress() {
		return propertyAddress;
	}

	public void setPropertyAddress(String propertyAddress) {
		this.propertyAddress = propertyAddress;
	}

	public int getPropertyValue() {
		return propertyValue;
	}

	public void setPropertyValue(int propertyValue) {
		this.propertyValue = propertyValue;
	}

	@Override
	public String toString() {
		return "HomeLoan [propertyAddress=" + propertyAddress + ", propertyValue=" + propertyValue + "]";
	}

}
