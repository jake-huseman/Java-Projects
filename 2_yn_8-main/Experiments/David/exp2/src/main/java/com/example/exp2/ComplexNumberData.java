package com.example.exp2;

public class ComplexNumberData {

	private double a, b;
	
	public ComplexNumberData(double a, double b) {
		this.a = a;
		this.b = b;
	}
	
	public void setNum(ComplexNumberData data) {
		this.a = data.getReal();
		this.b = data.getImg();
	}
	
	public void setNum(double a, double b) {
		this.a = a;
		this.b = b;
	}
	
	public void addNum(double a, double b) {
		this.a = a + a;
		this.b = b + b;
	}
	
	public double getReal() {
		return a;
	}
	
	public double getImg() {
		return b;
	}
	
	@Override
	public String toString() {
		return String.format("%.3f + %.3fi", a, b);
	}
	
}
