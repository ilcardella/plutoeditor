package it.polimi.template.utils;

import it.polimi.template.model.Drone;

import java.util.ArrayList;
import java.util.List;

public class DronesManager {
private static List<Drone> drones;
	
	public static List<Drone> getDrones(){
		if(drones == null){
			drones = new ArrayList<Drone>();
			
			Drone d1 = new Drone();
			 Drone d2 = new Drone();
			 Drone d3 = new Drone();
			 Drone d4 = new Drone();
			 Drone d5 = new Drone();
			 Drone d6 = new Drone();
			 Drone d7 = new Drone();
			 Drone d8 = new Drone();
			 
			 d1.setShapeCategory(2);
			 d2.setShapeCategory(3);
			 d3.setShapeCategory(1);
			 d4.setShapeCategory(4);
			 d5.setShapeCategory(2);
			 d6.setShapeCategory(3);
			 d7.setShapeCategory(1);
			 d8.setShapeCategory(4);
			 
			 drones.add(d1);
			 drones.add(d2);
			 drones.add(d3);
			 drones.add(d4);
			 drones.add(d5);
			 drones.add(d6);
			 drones.add(d7);
			 drones.add(d8);
		}
		return drones;
	}
}
