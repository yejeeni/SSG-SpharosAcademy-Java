package com.example.school.impl;

import org.springframework.stereotype.Component;

import com.example.school.service.StudentService;

/**
 * Student의 실제 동작을 구현한 클래스
 */
@Component
public class StudentServiceImpl implements StudentService{

	  @Override
	    public void getUp() {
	        System.out.println("기상합니다");
	    }

	    @Override
	    public void gotoSchool() {
	        System.out.println("등교합니다");
	    }

	    @Override
	    public void study() {
	        System.out.println("수업시작");
	    }

	    @Override
	    public void rest() {
	        System.out.println("쉬는 시간 갖기");
	    }

	    @Override
	    public void haveLunch() {
	        System.out.println("점심 먹기");
	    }

	    @Override
	    public void goHome() {
	        System.out.println("집에 갑니다");
	    }
}
