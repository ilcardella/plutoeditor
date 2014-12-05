package plutoeditor.model.editor;

import java.util.ArrayList;

import plutoeditor.model.classes.Mission;
import plutoeditor.model.classes.Trip;



public class MissionCreator extends Node {
	
	public Mission run(ArrayList<Trip> trips) {

		Mission m = new Mission();
		m.setTrips(trips);
		m.setStatus(1);

		return m;

	}

}
