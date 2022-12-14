package com.revature.pirateRev.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.pirateRev.models.Pirate;
import com.revature.pirateRev.ui.Menu;
import com.revature.pirateRev.utils.ArrayList;
import com.revature.pirateRev.utils.CaptainsLogger;
import com.revature.pirateRev.utils.ConnectionFactory;
import com.revature.pirateRev.utils.NoSuchElementException;
import com.revature.pirateRev.utils.CaptainsLogger.LogLevel;

public class PirateDAO implements DAO<Pirate> {
	private static CaptainsLogger logger = CaptainsLogger.getLogger();
	

	@Override
	public void create(Pirate pirate) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			PreparedStatement ps = conn
					.prepareStatement("insert into pirates (pirate_name, address, email, pirate_username, pirate_password)"
							+ " values (?, ?, ?, ?, ?)");
			ps.setString(1, pirate.getName());
			ps.setString(2, pirate.getAddress());
			ps.setString(3, pirate.getEmail());
			ps.setString(4, pirate.getUsername());
			ps.setString(5, pirate.getPassword());
			ps.execute();
		} catch (SQLException e) {
			logger.log(LogLevel.ERROR,
					"You not create connection to database. New pirate could not be added.\n\n\tException: "
							+ e.getMessage() + "\n\n\tStack Trace:\n\n" + e.getStackTrace().toString());
		}
	}

	@Override
	public ArrayList<Pirate> readAll() {
		ArrayList<Pirate> pirates = new ArrayList<Pirate>();
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String query = "select * from pirates";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			Pirate pirate = null;
			while (rs.next()) {
				pirate = new Pirate();
				pirate.setId(rs.getInt("pirate_id"));
				pirate.setName(rs.getString("pirate_name"));
				pirate.setEmail(rs.getString("email"));
				pirate.setAddress(rs.getString("address"));
				pirates.add(pirate);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return pirates;
	}

	@Override
	public void update(Pirate pirate) {
		String query = "UPDATE pirates SET pirate_password = ?, pirate_name = ?, email = ?, address = ?,pirate_username = ? WHERE pirate_id = ?";
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, pirate.getPassword());
			pstmt.setString(2, pirate.getName());
			pstmt.setString(3, pirate.getEmail());
			pstmt.setString(4, pirate.getAddress());
			pstmt.setString(5, pirate.getUsername());
			pstmt.setInt(6, pirate.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {

			logger.log(LogLevel.ERROR, "Error updating pirate " + pirate + " in database: \n" + e);
			System.out.println("Product " + pirate + " could not be updated! " + e);

		}
	}

	@Override
	public void delete(Pirate pirate) {
		String query = "DELETE FROM pirates WHERE pirate_id = " +pirate.getId();
		
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			Statement stmt = conn.createStatement();
			stmt.execute(query);
			
		}catch(SQLException e) {
			System.out.println(e);
		}

	}

	@Override
	public Pirate readBySomeColumnValue(String username) {
		String query = "SELECT * FROM pirates WHERE pirate_username = '" + username + "'";
		Pirate retrievedPirate = null;
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				retrievedPirate = new Pirate();
				retrievedPirate.setName(rs.getString("pirate_name"));
				retrievedPirate.setAddress(rs.getString("address"));
				retrievedPirate.setId(rs.getInt("pirate_id"));
				retrievedPirate.setPassword(rs.getString("pirate_password"));
				retrievedPirate.setUsername(rs.getString("pirate_username"));
				retrievedPirate.setEmail(rs.getString("email"));
			}
			
			

		} catch (SQLException e) {
			String stackTrace= "";
			for(StackTraceElement s : e.getStackTrace()) {
				stackTrace+="\t"+s.toString()+"\n";
			}
			logger.log(LogLevel.ERROR, e.getClass().getName() + " thrown when trying to retrieve"
					+ " record from 'pirates' table.\n\tStack Trace: " + stackTrace);
			print("Unable to retrieve pirate record from database."+e.getClass().getName()+ " thrown.");
			e.printStackTrace();
		}
		
		
		return retrievedPirate;

	}

	private void print(String string) {
		System.out.println("\n\n" + string);
		
	}

}
