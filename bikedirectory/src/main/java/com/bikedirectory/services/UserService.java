package com.bikedirectory.services;

import com.bikedirectory.beans.Bike;
import java.util.Set;


public interface UserService {
	public Set<Bike> viewAllBikes();
	public Set<Bike> searchByBrand(String brand);
	public Set<Bike> searchByRider(String rider);
	public Set<Bike> searchByModel(String model);
	public Bike updateBike(Bike bikeToUpdate);
	public int addNewBike(Bike newBike);
	public Bike getBikeById(int id);
	

}
