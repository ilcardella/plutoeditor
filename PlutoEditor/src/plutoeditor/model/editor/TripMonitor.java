package plutoeditor.model.editor;

import plutoeditor.model.classes.Mission;
import plutoeditor.model.classes.Trip;

public class TripMonitor extends Node {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Mission run(Mission m) {
		// if the current trip is completed, remove it from the list of trips
		// associated to the mission
		if (m.getTrips().get(0).getStatus() == Trip.COMPLETED)
			m.getTrips().remove(0);
		// if the current trip is failed for an exception, the mission status is
		// set to failed
		else if (m.getTrips().get(0).getStatus() == Trip.FAILED) {
			m.setStatus(Mission.FAILED);

		}

		// if there are no more trips to perform, the mission is completed
		if (m.getTrips().isEmpty())
			m.setStatus(Mission.COMPLETED);
		// otherwise there is at least another trip in the mission and the
		// mission status is set to standby
		else {
			m.setStatus(Mission.STANDBY);

		}

		return m;
	}

}
