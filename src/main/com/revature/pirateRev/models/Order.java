package com.revature.pirateRev.models;

import com.revature.pirateRev.collections.ArrayList;

public class Order {

	private int orderID;
	private ArrayList<LineItem> lineItems;
	private String storeLocation;
	private String storeID;
	private double totalPrice;
	private int pirateID;

	@Override
	public String toString() {
		return "\t*********\n\nOrder: \n\n" + lineItems + "\n\n\t*********";
	}

	public int getPirateID() {
		return pirateID;
	}

	public ArrayList<LineItem> getLineItems() {
		return lineItems;
	}

	public void setLineItems(ArrayList<LineItem> lineItems) {
		this.lineItems = lineItems;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStoreFrontLocation() {
		return storeLocation;
	}

	public int getOrderID() {
		return orderID;
	}

	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}

	public void setPirateID(int pirateID) {
		this.pirateID = pirateID;

	}

	public String getStoreID() {

		return this.storeID;
	}

	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}

}
