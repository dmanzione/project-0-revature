package com.revature.pirateRev.models;



import com.revature.pirateRev.booty.NauticalChart;
import com.revature.pirateRev.collections.ArrayList;

public class StoreFront {
	private String name;
	private ArrayList<Product> products;
	private ArrayList<Order> orders;
	private String address;

	public StoreFront(String name) {
		address = locate(name);
		this.name = name;
	}

	public StoreFront() {
		
	}

	public String locate(String name) {
		return NauticalChart.get(name);
	}

	public String getName() {

		return name;
	}


	@Override
	public String toString() {
		return  "\t"+name.toUpperCase() +"\n\tLocation:\n\t\t" + address + "\n\n";
	}

	public ArrayList<Product> getProducts() {
		return products;

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

	public void setAddress(String address) {
		this.address = address;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}


}
