package com.bikedirectory.data.collections;
import java.util.HashSet;
import java.util.Set;

import com.bikedirectory.beans.Bike;
import com.bikedirectory.data.BikeDAO;

public class BikeCollection implements BikeDAO {
	
	private Set<Bike> allBikes;
	private int currentId = 1;
	

	public BikeCollection() {
		allBikes = new HashSet<Bike>();
		Bike bike1 = new Bike();
		bike1.setRider("Robbie Miranda");
		bike1.setId(currentId);
		allBikes.add(bike1);
		currentId++;
	}

	
	
	public int create(Bike dataToAdd) {
		dataToAdd.setId(currentId);
		allBikes.add(dataToAdd);
		currentId++;
		return dataToAdd.getId();
	}

	public Bike getById(int id) {
		for (Bike bike : allBikes) {
			if (bike.getId() == id) {
				return bike;
			}
		}
		return null;

	}

	public Set<Bike> getAll() {
		return allBikes;
	}

	public void update(Bike dataToUpdate) {
		Bike previousData = getById(dataToUpdate.getId());
		if (previousData != null) {
			allBikes.remove(previousData);
			allBikes.add(dataToUpdate);
		}

		
	}

	public void delete(Bike dataToDelete) {
		Bike previousData = (Bike) getById(dataToDelete.getId());
		if (previousData != null) {
			allBikes.remove(previousData);
		}
	}

	public Set<Bike> getByRider(String rider) {
		Set<Bike> bikeWithRider = new HashSet<Bike>();
		for (Bike bike : allBikes) {
			if (bike.getRider().equals(rider)) {
				bikeWithRider.add(bike);
			}
		}
		return bikeWithRider;
	}

	public Set<Bike> getByModel(String model) {
		Set<Bike> bikeWithModel = new HashSet<Bike>();
		for (Bike bike : allBikes) {
			if (bike.getBrand().equals(bike)) {
				bikeWithModel.add(bike);
			}
		}
		return bikeWithModel;
	}

	public Set<Bike> getByBrand(String brand) {
		Set<Bike> bikeWithBrand = new HashSet<Bike>();
		for (Bike bike : allBikes) {
			if (bike.getBrand().equals(bike)) {
				bikeWithBrand.add(bike);
			}
		}
		return bikeWithBrand;
	}

	public Set<Bike> getByYear(int year) {
		Set<Bike> bikesByYear = new HashSet<Bike>();
		for (Bike bike : allBikes) {
			if (bike.getYear() == year) {
					bikesByYear.add(bike);
			}
		}
		return bikesByYear;
	}
	
	

}
