package com.revature.pirateRev.models;

public class Product {
	private String name;
	private double price;
	private String description;
	private int productId;
	private String store;

	public Product() {

		name = "Default Name";
		price = 10.00;
		description = "Default product ";

	}

	public Product(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String string) {
		this.category = string;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	private String category;

//	{
//		trinkets, clothingAndAccessories, magicTokensAndPotions, navigation, weaponry, howToGuides, miscellaneous
//	}

	public String getName() {

		return name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "\n\n\t" + name + " --- $" + price + "\n\t\tCategory: " + category + "\n\t\tLocation: " + store;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

}
