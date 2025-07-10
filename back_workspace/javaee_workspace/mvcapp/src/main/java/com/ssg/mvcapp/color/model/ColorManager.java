package com.ssg.mvcapp.color.model;

public class ColorManager {
	public String getAdvice(String color) {
		String msg = "";
		
		if (color.equals("red")){
			msg = "활동적";
		} else if(color.equals("blue")){
			msg = "분석적";
		} else if(color.equals("yellow")){
			msg = "외향적";
		} else if(color.equals("green")){
			msg = "이타적";
		}
		
		return msg;
	}
}
