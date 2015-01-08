package it.polimi.template.model.editor;

import it.polimi.template.model.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class Clock implements Node {
	
	ArrayList<Drone> drones = new ArrayList<Drone>();

	@Override
	public Mission run(final Mission m) {
		

		for (Trip t : m.getTrips())
			if (t.getDelay() > 0){
		
	}
		return m;
	}
	
	public Clock(ArrayList<Drone> drones){
		this.drones=drones;
	}

}
