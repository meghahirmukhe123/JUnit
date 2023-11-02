package com.http_methods.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
@Document(collection="httpMethodswithjunit")
@Builder
public class student {
	@Id
	private String stuid;
	private String stuname;
	private int stufees;
	public String getStuid() {
		return stuid;
	}
	public void setStuid(String stuid) {
		this.stuid = stuid;
	}
	public String getStuname() {
		return stuname;
	}
	public void setStuname(String stuname) {
		this.stuname = stuname;
	}
	public int getStufees() {
		return stufees;
	}
	public void setStufees(int stufees) {
		this.stufees = stufees;
	}
	@Override
	public String toString() {
		return "student [stuid=" + stuid + ", stuname=" + stuname + ", stufees=" + stufees + "]";
	}
	public student(String stuid, String stuname, int stufees) {
		super();
		this.stuid = stuid;
		this.stuname = stuname;
		this.stufees = stufees;
	}
	public student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
