package com.seleliumDemo.util;

public class Tests {
	public static void main(String[] args) {
        Thread thread1 =new Thread(new Seleliumthread());
        Thread thread2 =new Thread(new Seleliumthread());
        thread1.start();
        thread2.start();
	}
}
