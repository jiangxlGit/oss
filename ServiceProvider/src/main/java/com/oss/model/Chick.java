package com.oss.model;

public class Chick {
	private String sex;
	private String feather;
	private int weight;
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getFeather() {
		return feather;
	}
	public void setFeather(String feather) {
		this.feather = feather;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public String toString(){
		return "Chick [sex=" + sex + ", feather=" + feather + ", weight=" + weight + "]";
	}

}
