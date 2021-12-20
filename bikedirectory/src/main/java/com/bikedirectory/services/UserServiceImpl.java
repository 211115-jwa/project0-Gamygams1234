package com.bikedirectory.services;


import com.bikedirectory.data.BikeDAO;
import com.bikedirectory.data.postgres.BikePostgres;
import com.bikedirectory.beans.Bike;
import java.util.Set;


public class UserServiceImpl implements UserService {
	private BikeDAO bikeDao = new BikePostgres();
	
	

	

	@Override
	public Set<Bike> viewAllBikes() {
	return bikeDao.getAll();
	}

	@Override
	public Set<Bike> searchByBrand(String brand) {
		return bikeDao.getByBrand(brand);
	}

	@Override
	public Set<Bike> searchByRider(String rider) {
		return bikeDao.getByRider(rider);
	}

	@Override
	public Set<Bike> searchByModel(String model) {
		return bikeDao.getByModel(model);
	}

	@Override
	public Bike updateBike(Bike bikeToUpdate) {
		Bike bikeFromDatabase = bikeDao.getById(bikeToUpdate.getId());
		if (bikeFromDatabase != null) {
			bikeDao.update(bikeToUpdate);
			return bikeDao.getById(bikeToUpdate.getId());
		}
		return null;
	}

	@Override
	public int addNewBike(Bike newBike) {
		return bikeDao.create(newBike);
	}

	@Override
	public Bike getBikeById(int id) {
		return bikeDao.getById(id);
	}

}
