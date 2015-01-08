package it.polimi.template.model.editor;

import it.polimi.template.model.*;

public class TripMonitor implements Node {

	@Override
	public Mission run(Mission m) {
		Trip t = m.getTrips().get(0);

		while (t.getStatus() != Trip.COMPLETED) {

			// if the current trip is failed for an exception, the mission
			// status is
			// set to failed
			if (t.getStatus() == Trip.FAILED) {
				m.setStatus(Mission.FAILED);
				System.out.println("TripMonitor: Mission " + m.getName()
						+ " is failed");
				return m;
			}

			// if there are no more trips to perform, the mission is completed
			if (m.getTrips().isEmpty()) {
				m.setStatus(Mission.COMPLETED);
				System.out.println("TripMonitor: Mission " + m.getName()
						+ " is completed");
				return m;
			}
			// otherwise there is at least another trip in the mission and the
			// mission status is set to standby
			else {
				m.setStatus(Mission.STANDBY);
				System.out.println("TripMonitor: Mission " + m.getName()
						+ " is in standby");
				return m;
			}

		}
		// if the current trip is completed, remove it from the list of trips
		// associated to the mission
		m.getTrips().remove(0);
		if(m.getTrips().isEmpty())
			m.setStatus(Mission.COMPLETED);
		System.out.println("TripMonitor: Trip " + t.getName()
				+ " completed and removed");

		return m;
	}

}
