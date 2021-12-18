package com.bikedirectory.data.postgres;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.bikedirectory.beans.Bike;
import com.bikedirectory.data.BikeDAO;
import com.bikedirectory.utils.ConnectionUtil;



public class BikePostgres implements BikeDAO{
	
	private ConnectionUtil connUtil = ConnectionUtil.getConnectionUtil();
	
	
	@Override
	public int create(Bike dataToAdd) {
		int generatedId = 0;
		
		// try-with-resources auto-closes resources
		try (Connection conn = connUtil.getConnection()) {
			// when you run DML statements, you want to manage the TCL
			conn.setAutoCommit(false);
			
			String sql = "insert into bike (yr, brand , model , metal, rider) "
					+ "values (?, ?, ?, ?, ?)";
			String[] keys = {"id"}; // the name of the primary key column that will be autogenerated
			// creating the prepared statement
			PreparedStatement pStmt = conn.prepareStatement(sql, keys);
			// we need to set the values of the question marks
			pStmt.setInt(1, dataToAdd.getYear()); // question mark index starts at 1
			pStmt.setString(2, dataToAdd.getBrand());
			pStmt.setString(3, dataToAdd.getModel());
			pStmt.setString(4, dataToAdd.getMetal());
			pStmt.setString(5, dataToAdd.getRider());
			
			// after setting the values, we can run the statement
			pStmt.executeUpdate();
			ResultSet resultSet = pStmt.getGeneratedKeys();
			
			if (resultSet.next()) { // "next" goes to the next row in the result set (or the first row)
				// getting the ID value from the result set
				generatedId = resultSet.getInt("id");
				conn.commit(); // running the TCL commit statement
			} else {
				conn.rollback();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return generatedId;
	}

	@Override
	public Bike getById(int id) {
		Bike bike = null;
		
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from bike where id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, id);
			
			ResultSet resultSet = pStmt.executeQuery();
			
			if (resultSet.next()) {
				
				bike = new Bike();
				bike.setId(id);
				bike.setYear(resultSet.getInt("yr"));
				bike.setBrand(resultSet.getString("brand"));
				bike.setModel(resultSet.getString("model"));
				bike.setMetal(resultSet.getString("metal"));
				bike.setRider(resultSet.getString("rider"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return bike;
	}

	@Override
	public Set<Bike> getAll() {
		Set<Bike> allBikes = new HashSet<>();
		
		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from bike";
			Statement stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(sql);
			
			// while the result set has another row
			while (resultSet.next()) {
				// create the Pet object
				Bike bike = new Bike();
				// pull the data from each row in the result set
				// and put it into the java object so that we can use it here
				bike.setId(resultSet.getInt("id"));
				bike.setYear(resultSet.getInt("yr"));
				bike.setBrand(resultSet.getString("brand"));
				bike.setModel(resultSet.getString("model"));
				bike.setMetal(resultSet.getString("metal"));
				bike.setRider(resultSet.getString("rider"));

				
				allBikes.add(bike);
			}		
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return allBikes;
	}

	@Override
	public void update(Bike dataToUpdate) {
		
		try (Connection conn = connUtil.getConnection()) {
			conn.setAutoCommit(false);
			
			String sql = "update bike set "
					+ "yr=?,brand=?,model=?,metal=?,rider=? "
					+ "where id=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, dataToUpdate.getYear()); // question mark index starts at 1
			pStmt.setString(2, dataToUpdate.getBrand());
			pStmt.setString(3, dataToUpdate.getModel());
			pStmt.setString(4, dataToUpdate.getMetal());
			pStmt.setString(5, dataToUpdate.getRider());
			pStmt.setInt(6, dataToUpdate.getId());
			
			int rowsAffected = pStmt.executeUpdate();
			
			if (rowsAffected<=1) {
				conn.commit();
			} else {
				conn.rollback();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Bike dataToDelete) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Bike> getByRider(String rider) {
		Set<Bike> allBikes = new HashSet<>();

		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from bike where lower(rider)=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, rider.toLowerCase());
	
			ResultSet resultSet = pStmt.executeQuery();

			// while the result set has another row
			while (resultSet.next()) {
				// create the Pet object
				Bike bike = new Bike();
				// pull the data from each row in the result set
				// and put it into the java object so that we can use it here
				bike.setId(resultSet.getInt("id"));
				bike.setYear(resultSet.getInt("yr"));
				bike.setBrand(resultSet.getString("brand"));
				bike.setModel(resultSet.getString("model"));
				bike.setMetal(resultSet.getString("metal"));
				bike.setRider(resultSet.getString("rider"));

				allBikes.add(bike);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return allBikes;
	}

	@Override
	public Set<Bike> getByModel(String model) {
		Set<Bike> allBikes = new HashSet<>();

		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from bike where lower(model)=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, model.toLowerCase());
	
			ResultSet resultSet = pStmt.executeQuery();

			// while the result set has another row
			while (resultSet.next()) {
				// create the Pet object
				Bike bike = new Bike();
				// pull the data from each row in the result set
				// and put it into the java object so that we can use it here
				bike.setId(resultSet.getInt("id"));
				bike.setYear(resultSet.getInt("yr"));
				bike.setBrand(resultSet.getString("brand"));
				bike.setModel(resultSet.getString("model"));
				bike.setMetal(resultSet.getString("metal"));
				bike.setRider(resultSet.getString("rider"));

				allBikes.add(bike);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return allBikes;
	}

	@Override
	public Set<Bike> getByBrand(String brand) {
		Set<Bike> allBikes = new HashSet<>();

		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from bike where lower(brand)= ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, brand.toLowerCase());
	
			ResultSet resultSet = pStmt.executeQuery();

			// while the result set has another row
			while (resultSet.next()) {
				// create the Pet object
				Bike bike = new Bike();
				// pull the data from each row in the result set
				// and put it into the java object so that we can use it here
				bike.setId(resultSet.getInt("id"));
				bike.setYear(resultSet.getInt("yr"));
				bike.setBrand(resultSet.getString("brand"));
				bike.setModel(resultSet.getString("model"));
				bike.setMetal(resultSet.getString("metal"));
				bike.setRider(resultSet.getString("rider"));

				allBikes.add(bike);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return allBikes;
	}

	@Override
	public Set<Bike> getByYear(int year) {
		Set<Bike> allBikes = new HashSet<>();

		try (Connection conn = connUtil.getConnection()) {
			String sql = "select * from bike where yr=?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, year);
	
			ResultSet resultSet = pStmt.executeQuery();

			// while the result set has another row
			while (resultSet.next()) {
				// create the Pet object
				Bike bike = new Bike();
				// pull the data from each row in the result set
				// and put it into the java object so that we can use it here
				bike.setId(resultSet.getInt("id"));
				bike.setYear(resultSet.getInt("yr"));
				bike.setBrand(resultSet.getString("brand"));
				bike.setModel(resultSet.getString("model"));
				bike.setMetal(resultSet.getString("metal"));
				bike.setRider(resultSet.getString("rider"));

				allBikes.add(bike);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return allBikes;
	}



}