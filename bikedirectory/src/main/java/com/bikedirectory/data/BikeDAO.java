package com.bikedirectory.data;

import java.util.Set;

import com.bikedirectory.beans.Bike;


public interface BikeDAO extends GenericDAO<Bike>{
	public Set<Bike> getByRider(String rider);
	public Set<Bike> getByModel(String model);
	public Set<Bike> getByBrand(String brand);
	public Set<Bike> getByYear(int year);
}
