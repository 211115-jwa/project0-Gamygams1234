package com.bikedirectory.beans;


public class Bike {
	private int id;
	private int year;
	private String brand;
	private String model;
	private String metal;
	private String rider;
	
	
	public Bike () {
		id = 0;
		year = 1980;
		brand = "Free Agent";
		model = "Speed Way";
		metal = "Aluminum";
		rider = "Scotty Cranmer";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getYear(){
		return year;
	}
	public void setYear(int year){
		this.year = year;
	}
	
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;	
	}
	
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;	
	}
	
	public String getMetal() {
		return metal;
	}
	public void setMetal(String metal) {
		this.metal = metal;	
	}
	public String getRider() {
		return rider;
	}
	public void setRider(String rider) {
		this.rider = rider;	
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((brand == null) ? 0 : brand.hashCode());
		result = prime * result + id;
		result = prime * result + year;
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((metal == null) ? 0 : metal.hashCode());
		result = prime * result + ((rider == null) ? 0 : rider.hashCode());
		return result;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bike other = (Bike) obj;
		if (brand == null) {
			if (other.brand != null)
				return false;
		} else if (!brand.equals(other.brand))
			return false;
		if (id != other.id)
			return false;
		if (year != other.year)
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (rider == null) {
			if (other.rider != null)
				return false;
		} else if (!rider.equals(other.rider))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "Bike [id=" + id + ", year=" + year + ", brand=" + brand + ", model=" + model
				+ ", metal=" + metal + ", rider=" + rider + "]";
	}


}
