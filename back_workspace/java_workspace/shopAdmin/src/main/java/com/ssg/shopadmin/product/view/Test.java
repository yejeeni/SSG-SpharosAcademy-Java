package com.ssg.shopadmin.product.view;

public class Test {
	public static void main(String[] args) {
		
		for(int i=0; i<5; i++) {
			long time =  System.currentTimeMillis();
			System.out.println(time);
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
		String path = "c://test/n/hello.test.jpg";
		int idx = path.lastIndexOf(".");
		System.out.println(idx);
		System.out.println(path.substring(idx+1, path.length()));
		
		String v = "a100";
		String v2 = "100";
		
		try {
			Integer.parseInt(v);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
	}
	
}
