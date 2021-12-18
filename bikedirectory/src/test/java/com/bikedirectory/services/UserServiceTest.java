package com.bikedirectory.services;
import com.bikedirectory.beans.Bike;
import com.bikedirectory.data.BikeDAO;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	
	@Mock
	private BikeDAO bikeDao;
	
	
	// override the regular daos not the mock daos
	@InjectMocks
	private UserService userServ = new UserServiceImpl();
	private static Set<Bike> mockBikes;
	
	@Test
	public void addNewBikeSuccessfully() {
		Bike bike = new Bike();
		
		when(bikeDao.create(bike)).thenReturn(10);
		
		int newId = userServ.addNewBike(bike);
		
		assertNotEquals(0, newId);
	}
	
	@Test
	public void editBikeSuccessfully() {
		Bike bike = new Bike();
		bike.setId(2);
		bike.setYear(1999);
		bike.setBrand("Felt");
		bike.setModel("Franchise Killer");
		bike.setRider("Scotty Cranmer");
		
	
		when(bikeDao.getById(2)).thenReturn(bike);
		doNothing().when(bikeDao).update(Mockito.any(Bike.class));
		
		Bike actualBike = userServ.updateBike(bike);
		
		assertEquals(bike, actualBike);
	}
	
	@Test
	public void editPetDoesNotExist() {
		when(bikeDao.getById(2)).thenReturn(null);
		
		Bike bike = new Bike();
		bike.setId(2);
		bike.setYear(1999);
		bike.setBrand("Felt");
		bike.setModel("Franchise Killer");
		bike.setRider("Scotty Cranmer");
		
		Bike actualBike = userServ.updateBike(bike);
		
		assertNull(actualBike);
		
		verify(bikeDao, times(0)).update(Mockito.any(Bike.class));
	}
	
	@Test
	public void getByIdBikeExists() {
		Bike bike = new Bike();
		bike.setId(2);
		bike.setYear(1999);
		bike.setBrand("Felt");
		bike.setModel("Franchise Killer");
		bike.setRider("Scotty Cranmer");
		
		when(bikeDao.getById(2)).thenReturn(bike);
		
		Bike actualBike = userServ.getBikeById(2);
		assertEquals(bike, actualBike);
	}
	
	
	@Test
	public void getByIdPetDoesNotExist() {
		when(bikeDao.getById(2)).thenReturn(null);
		
		Bike actualBike = userServ.getBikeById(2);
		assertNull(actualBike);
	}


}
