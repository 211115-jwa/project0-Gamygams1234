package com.bikedirectory.app;
import io.javalin.Javalin;
import io.javalin.http.HttpCode;
import java.util.Set;
import com.bikedirectory.data.postgres.BikePostgres;
import com.bikedirectory.data.BikeDAO;

import org.eclipse.jetty.http.HttpStatus;

//this static import is for the path and get/post/put methods
import static io.javalin.apibuilder.ApiBuilder.*;
import com.bikedirectory.beans.Bike;
public class BikeDirectory {
	public static void main(String[] args) {
		
		BikeDAO bikeDao = new BikePostgres();
		
		Javalin app = Javalin.create();
		
		app.start();
		app.routes(() -> {
			path("/bikes", () -> {
			
				get(ctx -> {
					
					String brandSearch = ctx.queryParam("brand");
					String riderSearch = ctx.queryParam("rider");
					String modelSearch = ctx.queryParam("model");
					
					if (brandSearch != null && !"".equals(brandSearch)) {
						Set<Bike> brandBikes = bikeDao.getByBrand(brandSearch);
						ctx.json(brandBikes);
					}else if(riderSearch != null && !"".equals(riderSearch)){
						Set<Bike> riderBikes = bikeDao.getByRider(riderSearch);
						ctx.json(riderBikes);
					}else if(modelSearch != null && !"".equals(modelSearch)){
						Set<Bike> modelBikes = bikeDao.getByModel(modelSearch);
						ctx.json(modelBikes);
					}else {
					Set<Bike> allBikes = bikeDao.getAll();
					ctx.json(allBikes);
					}
				});
				post(ctx -> {
					// bodyAsClass turns JSON into a Java object based on the class you specify
					Bike newBike = ctx.bodyAsClass(Bike.class);
					if (newBike !=null) {
						bikeDao.create(newBike);
						ctx.status(HttpStatus.CREATED_201);
					} else {
						ctx.status(HttpStatus.BAD_REQUEST_400);
					}
				});
				
				path("/{id}", () -> {
					get(ctx -> {
						try {
							int bikeId = Integer.parseInt(ctx.pathParam("id")); // num format exception
							 Bike bike = bikeDao.getById(bikeId);
							if (bike != null)
								ctx.json(bike);
							else
								ctx.status(404);
						} catch (NumberFormatException e) {
							ctx.status(400);
							ctx.result("Pet ID must be a numeric value");
						}
					});
					put(ctx -> {
						try {
							int bikeId = Integer.parseInt(ctx.pathParam("id")); // num format exception
							Bike bikeToEdit = ctx.bodyAsClass(Bike.class);
							if (bikeToEdit != null && bikeToEdit.getId() == bikeId) {
							bikeDao.update(bikeToEdit);					
								if (bikeToEdit != null)
									ctx.json(bikeToEdit);
								else
									ctx.status(404);
							} else {
								// conflict: the id doesn't match the id of the pet sent
								ctx.status(HttpCode.CONFLICT);
							}
						} catch (NumberFormatException e) {
							ctx.status(400);
							ctx.result("Bike ID must be a numeric value");
						}
					});
					
					
				});
				
			});
			
		});
		
	}

}
