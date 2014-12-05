package plutoeditor.model.editor;

import plutoeditor.model.classes.Mission;


public class PriorityManager extends Node {
	
	@Override
	public Mission run(Mission m) {
		// the new priority for the trip is set by the user
		int newPriority = 110;
		m.getTrips().get(0).setPriority(newPriority);
		m.setStatus(5);
		return m;
	}

}
