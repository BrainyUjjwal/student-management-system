package com.app.main;

import com.app.util.StudentUtil;

public class MainApp {
	static {
		System.out.println("Welcome to my application");
	}
	public static void main(String[] args) {
		StudentUtil util = new StudentUtil();
		util.start();
	}
}