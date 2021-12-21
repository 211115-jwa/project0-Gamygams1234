package com.bikedirectory.data.collections;

import com.bikedirectory.beans.Bike;
import com.bikedirectory.data.BikeDAO;
import org.junit.jupiter.api.Test;
import com.bikedirectory.data.collections.BikeCollection;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BikeCollectionTest {
    private BikeDAO bikeDao= new BikeCollection();

    @Test
    public void createTest() {
        Bike bike = new Bike();
        assertNotEquals(0, bikeDao.create(bike));
    }

    @Test
    void getByIdTest() {
        String expectedName = "Robbie Miranda";
        Bike actual = bikeDao.getById(1);
        assertEquals(expectedName, actual.getRider());
    }

    @Test
    void getAll() {
        assertEquals(1, bikeDao.getAll().size());
    }

    @Test
    void update() {
        Bike actual = bikeDao.getById(1);
        
        actual.setRider("Dave Mirra");
        assertEquals("Dave Mirra", actual.getRider());
    }
    


    @Test
    void getByYear() {
        assertEquals(1, bikeDao.getByYear(1980).size());
    }
}