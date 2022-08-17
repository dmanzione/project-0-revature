package com.revature.pirateRev.models;

import com.revature.pirateRev.collections.ArrayList;

public class Pirate {
	private String name;
	private String address;
	private String email;
	private ArrayList<Order> orders;
	private int pirateID;

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Pirate - \n\t" + name + "\n\t" + address + "\n\t" + email;
	}

	public void setName(String name) {
		this.name = name;

	}

	public void setAddress(String address) {
		this.address = address;

	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<Order> getOrders() {
		return orders;
	}

	public void setOrders(ArrayList<Order> orders) {
		this.orders = orders;
	}

	public String getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}

	public void setId(int pirateID) {
		this.pirateID = pirateID;
		
	}

	public int getId() {
		return this.pirateID;
	}
}
