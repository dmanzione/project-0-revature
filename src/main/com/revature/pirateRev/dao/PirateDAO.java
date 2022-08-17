package com.revature.pirateRev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.pirateRev.collections.ArrayList;
import com.revature.pirateRev.exceptions.NoSuchElementException;
import com.revature.pirateRev.models.Pirate;
import com.revature.pirateRev.util.CaptainsLogger;
import com.revature.pirateRev.util.CaptainsLogger.LogLevel;
import com.revature.pirateRev.util.ConnectionFactory;

public class PirateDAO implements DAO<Pirate> {
	private static CaptainsLogger logger = CaptainsLogger.getLogger();

	@Override
	public void create(Pirate pirate) {
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {

			PreparedStatement ps = conn
					.prepareStatement("insert into pirates (pirate_name, address, email) values (?, ?, ?)");
			ps.setString(1, pirate.getName());
			ps.setString(2, pirate.getAddress());
			ps.setString(3, pirate.getEmail());
			ps.execute();
		} catch (SQLException e) {
			logger.log(LogLevel.ERROR,
					"You not create connection to database. New pirate could not be added.\n\n\tException: "
							+ e.getMessage() + "\n\n\tStack Trace:\n\n" + e.getStackTrace().toString());
		}
	}

	@Override
	public Pirate readByName(String name) throws NoSuchElementException {
		for (Object p : readAll()) {
			Pirate pir = (Pirate) p;
			if (pir.getName().equals(name)) {
				return pir;
			}

		}
		throw new NoSuchElementException("There is no pirate called " + name);
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
	public void update(Pirate obj) {

	}

	@Override
	public void delete(Pirate pirate) {
		// TODO Auto-generated method stub

	}

}
