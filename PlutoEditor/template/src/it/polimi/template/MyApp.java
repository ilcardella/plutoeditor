package it.polimi.template;

import it.polimi.template.controller.MissionPageController;
import it.polimi.template.model.*;
import it.polimi.template.utils.DronesManager;
import it.polimi.template.utils.ItemsManager;
import it.polimi.template.view.*;

import java.awt.EventQueue;
import java.util.ArrayList;

public class MyApp {

	public static void main(String[] args) {

		ArrayList<Drone> drones = new ArrayList<Drone>();
		ArrayList<Item> items = new ArrayList<Item>();

		drones = (ArrayList<Drone>) DronesManager.getDrones();
		items = (ArrayList<Item>) ItemsManager.getItems();
		//populateDronesandItems(drones, items);
		
		MissionsPage mp = new MissionsPage();

		MissionPageController main = new MissionPageController(mp, items, drones);
		mp.setVisible(true);

	}
	
//	public static void populateDronesandItems(ArrayList<Drone> drones, ArrayList<Item> items) {
//		
//		 Drone d1 = new Drone();
//		 Drone d2 = new Drone();
//		 Drone d3 = new Drone();
//		 Drone d4 = new Drone();
//		 Drone d5 = new Drone();
//		 Drone d6 = new Drone();
//		 Drone d7 = new Drone();
//		 Drone d8 = new Drone();
//		
//		 Item i1 = new Item();
//		 Item i2 = new Item();
//		 Item i3 = new Item();
//		 Item i4 = new Item();
//		
//		 d1.setShapeCategory(2);
//		 d2.setShapeCategory(3);
//		 d3.setShapeCategory(1);
//		 d4.setShapeCategory(4);
//		 d5.setShapeCategory(2);
//		 d6.setShapeCategory(3);
//		 d7.setShapeCategory(1);
//		 d8.setShapeCategory(4);
//		
//		 i1.setShapeCategory(1);
//		 i2.setShapeCategory(2);
//		 i3.setShapeCategory(3);
//		 i4.setShapeCategory(4);
//		
//		 i1.setName("1");
//		 i2.setName("2");
//		 i3.setName("3");
//		 i4.setName("4");
//		
//		 drones.add(d1);
//		 drones.add(d2);
//		 drones.add(d3);
//		 drones.add(d4);
//		 drones.add(d5);
//		 drones.add(d6);
//		 drones.add(d7);
//		 drones.add(d8);
//		 items.add(i1);
//		 items.add(i2);
//		 items.add(i3);
//		 items.add(i4);
//		
//		 }

}
