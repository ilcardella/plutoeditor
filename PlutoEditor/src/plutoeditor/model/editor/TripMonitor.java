package plutoeditor.model.editor;

import plutoeditor.model.classes.Mission;


public class TripMonitor extends Node {
	
	@Override
	public Mission run(Mission m) {
		// if the current trip is completed, remove it from the list of trips
		// associated to the mission
		if (m.getTrips().get(0).getStatus() == 2)
			m.getTrips().remove(0);
		// if the current trip is failed for an exception, the mission status is
		// set to failed
		else if (m.getTrips().get(0).getStatus() == 3) {
			m.setStatus(4);
		
		}

		// if there are no more trips to perform, the mission is completed
		if (m.getTrips().isEmpty())
			m.setStatus(3);
		// otherwise there is at least another trip in the mission and the
		// mission status is set to standby
		else {
			m.setStatus(5);
			
		}

		return m;
	}

}
