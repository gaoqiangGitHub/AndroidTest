package com.example.androidtest.entity;

import java.io.Serializable;

/**
 * @author
 * @version 2016-1-22下午4:39:59
 * @description
 */

public class User implements Serializable {
	private static final long serialVersionUID = 6092917543924448968L;

	private String name;
	private int age;

	public User() {
	}

	public User(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
