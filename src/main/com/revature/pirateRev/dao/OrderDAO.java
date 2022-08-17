package com.revature.pirateRev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.pirateRev.collections.ArrayList;
import com.revature.pirateRev.models.Order;
import com.revature.pirateRev.models.Pirate;
import com.revature.pirateRev.util.CaptainsLogger;
import com.revature.pirateRev.util.ConnectionFactory;
import com.revature.pirateRev.util.CaptainsLogger.LogLevel;

public class OrderDAO implements DAO<Order> {

	private CaptainsLogger logger = CaptainsLogger.getLogger();
	private LineItemDAO lineItemDAO = new LineItemDAO();
	private PirateDAO pirateDAO = new PirateDAO();

	@Override
	public void create(Order order) {

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String query = "INSERT INTO orders (order_id, pirate_id, store_id, total_price) VALUES ( "
					+ order.getOrderID() + ", " + order.getStoreID() + "," + order.getTotalPrice() + ")";
			Statement stmt = conn.createStatement();
			if (!stmt.execute(query)) {
				logger.log(LogLevel.ERROR, "Error adding order to database");
			} else {
				logger.log(LogLevel.INFO, "Order: " + order + " added to database successfully");
			}
		} catch (SQLException e) {
			logger.log(LogLevel.ERROR, "Error adding order " + order + " to database: \n" + e);
			System.out.println(e);
		}

	}

	@Override
	public Order readByName(String name) {

		return null;
	}

	@Override
	public void update(Order order) {

		String query = "UPDATE orders SET store_id = ?, pirate_id = ?, total_price=? WHERE order_id = ?;";
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, order.getStoreID());
			pstmt.setInt(2, order.getPirateID());
			pstmt.setDouble(3, order.getTotalPrice());
			pstmt.setInt(4, order.getOrderID());
			if (!pstmt.execute()) {
				logger.log(LogLevel.ERROR, "Error updating order " + order + " in database");
			} else {
				logger.log(LogLevel.INFO, "Order: " + order + " updated successfully");
			}
		} catch (SQLException e) {

			logger.log(LogLevel.ERROR, "Error updating order " + order + " in database: \n" + e);
			System.out.println(e);
		}

	}

	@Override
	public void delete(Order order) {
		String query = "DELETE FROM orders WHERE order_id = " + order.getOrderID() + ";";

		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			Statement stmt = conn.createStatement();
			if (!stmt.execute(query)) {
				logger.log(LogLevel.ERROR, "Error deleting order " + order + " from database");
				System.out.println("Order could not be deleted from database!");
			} else {
				logger.log(LogLevel.INFO, "Order: " + order + " deleted from database successfully");
			}
		} catch (SQLException e) {
			logger.log(LogLevel.ERROR, "Error updating order " + order + " in database: \n" + e);
			System.out.println(e);
		}
	}

	@Override
	public ArrayList<Order> readAll() {
		String query = "SELECT * FROM orders";
		ArrayList<Order> orders = new ArrayList<Order>();
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			Order order = null;
			while (rs.next()) {
				order = new Order();
				order.setOrderID(rs.getInt("order_id"));
				order.setLineItems(lineItemDAO.readAllByOrderID(order.getOrderID()));
				order.setTotalPrice(rs.getDouble("total_price"));
				order.setPirateID(rs.getInt("pirate_id"));

				orders.add(order);
			}

		} catch (SQLException e) {

			System.out.println(e);
		}
		return orders;
	}

	public ArrayList<Order> readAllByPirateName(String name) {
		Pirate pirate = pirateDAO.readByName(name);

		String query = "SELECT * FROM orders WHERE pirate_id = " + pirate.getId() + ";";
		ArrayList<Order> orders = new ArrayList<Order>();
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			Order order = null;
			while (rs.next()) {
				order = new Order();
				order.setOrderID(rs.getInt("order_id"));
				order.setLineItems(lineItemDAO.readAllByOrderID(order.getOrderID()));
				order.setTotalPrice(rs.getDouble("total_price"));
				order.setPirateID(rs.getInt("pirate_id"));

				orders.add(order);
			}

		} catch (SQLException e) {

			System.out.println(e);
		}
		return orders;

	}

}
