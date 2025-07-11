package com.ssg.mvcapp.abo.repository;

public class ABOManager {
	public String getAdvice(String abo) {
		String msg = "";
		
		if (abo.equals("A")){
			msg = "신중함";
		} else if(abo.equals("B")){
			msg = "개성강함";
		} else if(abo.equals("O")){
			msg = "외향적";
		} else if(abo.equals("AB")){
			msg = "이성적";
		}
		
		return msg;
	}
}
