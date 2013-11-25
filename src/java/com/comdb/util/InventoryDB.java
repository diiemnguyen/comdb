package com.comdb.util;

import java.io.Serializable;

public class InventoryDB implements Serializable{
	
	private static final long serialVersionUID = 6297385302078200511L;
	
	private String title;
	private int cases;
	private double price;
	
	public InventoryDB(String title, int cases, double price){
		this.title=title;
		this.cases=cases;
		this.price=price;
	}

	public void setTitle(String title) {
		this.title = title;
	}


	public void setCase(int cases) {
		this.cases = cases;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public String getTitle() {
		return title;
	}

	public int getCases() {
		return cases;
	}

	public double getPrice() {
		return price;
	}

	
	@Override
	public String toString(){
		return "Title = " + this.title + ", Qty = " + this.cases + ", Price = " + this.price;
	}
}
