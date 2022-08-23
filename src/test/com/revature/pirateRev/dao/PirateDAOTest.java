package com.revature.pirateRev.dao;

import com.revature.pirateRev.data.PirateDAO;
import com.revature.pirateRev.models.Pirate;
import com.revature.pirateRev.util.Assertions;
import com.revature.pirateRev.util.Test;


public class PirateDAOTest {
	private PirateDAO pirateDAO;
	private Pirate createdPirate;
	private Pirate retrievedPirate;
	private Pirate updatedPirate;

	@Test
	public void createAndRetrieveTest() {

		pirateDAO = new PirateDAO();
		
		createdPirate = new Pirate();
		createdPirate.setUsername("piratetest");
		createdPirate.setPassword("12345678");
		createdPirate.setName("Test Pirate");
		createdPirate.setEmail("pirate@test.cove");
		createdPirate.setAddress("1234 pirate test lane");
		
		pirateDAO.create(createdPirate);
		
		retrievedPirate = pirateDAO.readBySomeColumnValue("piratetest");
		
		
		Assertions.equals(retrievedPirate.getAddress(), "1234 pirate test lane");
		Assertions.equals(retrievedPirate.getUsername(), "piratetest");
		Assertions.equals(retrievedPirate.getPassword(), "12345678");
	}
	
	@Test
	public void updateTest() {
		pirateDAO = new PirateDAO();
		retrievedPirate = pirateDAO.readBySomeColumnValue("piratetest");
		retrievedPirate.setAddress("changed address");
		retrievedPirate.setPassword("changed password");
		retrievedPirate.setEmail("changed email");
		retrievedPirate.setName("changed name");
		
		pirateDAO.update(retrievedPirate);
		
		updatedPirate = pirateDAO.readBySomeColumnValue("piratetest");
		
		Assertions.equals(updatedPirate.getAddress(), "changed address");
		Assertions.equals(updatedPirate.getEmail(), "changed email");
		Assertions.equals(updatedPirate.getName(), "changed name");
		Assertions.equals(updatedPirate.getPassword(), "changed password");
		
		
		}
	
	@Test
	public void deleteTest() {
		pirateDAO = new PirateDAO();
		
		updatedPirate = pirateDAO.readBySomeColumnValue("piratetest");
		pirateDAO.delete(updatedPirate);
		Pirate deletedPirate = pirateDAO.readBySomeColumnValue("piratetest");
		Assertions.isNull(deletedPirate);
		
	}

}
