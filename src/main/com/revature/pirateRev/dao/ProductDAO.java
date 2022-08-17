package com.revature.pirateRev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.pirateRev.collections.ArrayList;
import com.revature.pirateRev.models.Product;
import com.revature.pirateRev.util.CaptainsLogger;
import com.revature.pirateRev.util.ConnectionFactory;
import com.revature.pirateRev.util.CaptainsLogger.LogLevel;

public class ProductDAO implements DAO<Product> {

	private CaptainsLogger logger = CaptainsLogger.getLogger();

	@Override
	public void create(Product product) {

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String query = "INSERT INTO products (product_id, product_name, store_id, price, category) VALUES ( "
					+ product.getProductId() + ", '"+product.getName()+"','" + product.getStore() + "'," + product.getPrice() + ",'"
					+ product.getCategory() + "')";
			Statement stmt = conn.createStatement();
			
				logger.log(LogLevel.INFO, "Product: " + product + " added to database successfully");
			
		} catch (SQLException e) {
			logger.log(LogLevel.ERROR, "Error adding product " + product + "\n to database: \n" + e);
			System.out.println("Error adding product " + product + "\n to database: \n");
			e.printStackTrace();
		}

	}

	@Override
	public Product readByName(String name) {

		return null;
	}

	@Override
	public void update(Product product) {

		String query = "UPDATE products SET store_id = ?, product_name = ?, price=?,category=? WHERE product_id = ?;";
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, product.getStore());
			pstmt.setString(2, product.getName());
			pstmt.setDouble(3, product.getPrice());
			pstmt.setString(4, product.getCategory());
			pstmt.setInt(5, product.getProductId());
			if (!pstmt.execute()) {
				logger.log(LogLevel.ERROR, "Error updating product " + product + " in database");
				System.out.println("Product " + product + " could not be updated!");
			} else {
				logger.log(LogLevel.INFO, "Product: " + product + " updated successfully");
			}
		} catch (SQLException e) {

			logger.log(LogLevel.ERROR, "Error updating product " + product + " in database: \n" + e);
			System.out.println("Product " + product + " could not be updated! " + e);

		}

	}

	@Override
	public void delete(Product product) {
		String query = "DELETE FROM products WHERE product_id = " + product.getProductId() + ";";

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			Statement stmt = conn.createStatement();
			stmt.executeQuery(query);
		} catch (SQLException e) {
			logger.log(LogLevel.ERROR, "Error deleting product " + product + " fromt database: \n" + e);
			System.out.println("Error deleting product " + product + " from database: \n");
			e.printStackTrace();
		}
	}

	@Override
	public ArrayList<Product> readAll() {
		String query = "SELECT * FROM products";
		ArrayList<Product> products = new ArrayList<Product>();
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			Product product = null;
			while (rs.next()) {
				product = new Product();
				product.setProductId(rs.getInt("product_id"));
				product.setCategory(rs.getString("category"));
				product.setName(rs.getString("product_name"));
				product.setPrice(rs.getDouble("price"));
				product.setStore(rs.getString("store_id"));
				products.add(product);
			}

		} catch (SQLException e) {

			System.out.println(e);
		}
		return products;
	}



	public Product readById(int id) {
		String query = "SELECT * FROM products where product_id = "+id + ";";
		Product product = null;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()) {
				product = new Product();
				product.setProductId(rs.getInt("product_id"));
				product.setCategory(rs.getString("category"));
				product.setName(rs.getString("product_name"));
				product.setPrice(rs.getDouble("price"));
				product.setStore(rs.getString("store_id"));
				
			}

		} catch (SQLException e) {

			System.out.println(e);
		}
		return product;
		
	}
	public ArrayList<Product> readAllByStoreName(String name) {
		String query = "SELECT * FROM products WHERE store_id = '"+name+"';";
		ArrayList<Product> products = new ArrayList<Product>();
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			Product product = null;
			while (rs.next()) {
				product = new Product();
				product.setProductId(rs.getInt("product_id"));
				product.setCategory(rs.getString("category"));
				product.setName(rs.getString("product_name"));
				product.setPrice(rs.getDouble("price"));
				product.setStore(rs.getString("store_id"));
				products.add(product);
			}

		} catch (SQLException e) {

			System.out.println(e);
		}
		return products;
	}

}
