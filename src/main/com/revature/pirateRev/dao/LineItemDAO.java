package com.revature.pirateRev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.pirateRev.collections.ArrayList;
import com.revature.pirateRev.models.Product;
import com.revature.pirateRev.models.LineItem;
import com.revature.pirateRev.util.CaptainsLogger;
import com.revature.pirateRev.util.CaptainsLogger.LogLevel;
import com.revature.pirateRev.util.ConnectionFactory;

public class LineItemDAO implements DAO<LineItem> {

	private CaptainsLogger logger = CaptainsLogger.getLogger();
	private ProductDAO productDAO = new ProductDAO();

	@Override
	public void create(LineItem obj) {
		Product product = obj.getProduct();
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String query = "insert into line_item (product_id, order_id, quantity) values ( " + product.getProductId()
					+ ", " + obj.getOrderID() + "," + obj.getQuantity() + ")";
			Statement stmt = conn.createStatement();
			if (!stmt.execute(query)) {
				logger.log(LogLevel.ERROR, "Error adding line item to database");
			} else {
				logger.log(LogLevel.INFO, "Line item: Product " + product.getName() + ": ID" + product.getProductId()
						+ ", Quantity " + obj.getQuantity() + " added to database successfully");
			}
		} catch (SQLException e) {
			logger.log(LogLevel.ERROR,
					"Error adding line item " + product.getName() + " ID:" + obj.getId() + " to database: \n" + e);
			System.out.println(e);
		}

	}

	@Override
	public LineItem readByName(String name) {

		return null;
	}

	@Override
	public void update(LineItem obj) {
		Product product = obj.getProduct();
		String query = "UPDATE line_item SET product_id = ?, order_id = ?, quantity=? where line_item_id = ?;";
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, product.getProductId());
			pstmt.setInt(2, obj.getOrderID());
			pstmt.setInt(3, obj.getQuantity());
			pstmt.setInt(4, obj.getId());
			if (!pstmt.execute()) {
				logger.log(LogLevel.ERROR, "Error updating line item " + obj.getProduct().getName() + " to database");
			} else {
				logger.log(LogLevel.INFO, "Line item: Product " + product.getName() + ": ID" + product.getProductId()
						+ ", Quantity " + obj.getQuantity() + " updated successfully");
			}
		} catch (SQLException e) {

			logger.log(LogLevel.ERROR,
					"Error updating line item " + product.getName() + " ID:" + obj.getId() + " in database: \n" + e);
			System.out.println(e);
		}

	}

	@Override
	public void delete(LineItem lineItem) {
		String query = "DELETE FROM line_item WHERE line_item_id = " + lineItem.getId() + ";";
		Product product = lineItem.getProduct();
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			Statement stmt = conn.createStatement();
			if (!stmt.execute(query)) {
				logger.log(LogLevel.ERROR, "Error deleting line item " + lineItem.getProduct().getName() + ", quantity "
						+ lineItem.getQuantity() + " from database");
				System.out.println("Line item could not be deleted from database!");
			} else {
				logger.log(LogLevel.INFO, "Line item: Product " + product.getName() + ": ID" + lineItem.getId()
						+ ", Quantity " + lineItem.getQuantity() + " deleted from database successfully");
			}
		} catch (SQLException e) {
			logger.log(LogLevel.ERROR, "Error updating line item " + product.getName() + " ID:" + lineItem.getId()
					+ " in database: \n" + e);
			System.out.println(e);
		}
	}

	@Override
	public ArrayList<LineItem> readAll() {
		String query = "SELECT * FROM line_item";
		ArrayList<LineItem> lineItems = new ArrayList<LineItem>();
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			LineItem lineItem = null;
			while (rs.next()) {
				lineItem = new LineItem();
				lineItem.setId(rs.getInt("line_item_id"));
				lineItem.setProduct(productDAO.readById(rs.getInt("product_id")));
				lineItem.setOrderID(rs.getInt("order_id"));
				lineItem.setQuantity(rs.getInt("quantity"));
				lineItems.add(lineItem);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return lineItems;
	}

	public ArrayList<LineItem> readAllByOrderID(int orderID) {

		String query = "SELECT * FROM line_item WHERE order_id = " + orderID + ";";
		ArrayList<LineItem> lineItems = new ArrayList<LineItem>();
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			LineItem lineItem = null;
			while (rs.next()) {
				lineItem = new LineItem();
				lineItem.setId(rs.getInt("line_item_id"));
				lineItem.setProduct(productDAO.readById(rs.getInt("product_id")));
				lineItem.setOrderID(rs.getInt("order_id"));
				lineItem.setQuantity(rs.getInt("quantity"));
				lineItems.add(lineItem);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return lineItems;
	}

}
