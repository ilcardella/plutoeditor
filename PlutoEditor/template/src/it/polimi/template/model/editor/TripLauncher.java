package it.polimi.template.model.editor;

import java.text.SimpleDateFormat;

import it.polimi.template.model.*;

import java.util.Calendar;

public class TripLauncher implements Node {

	private Calendar cal = Calendar.getInstance();

	@Override
	public Mission run(Mission m) {

		Trip t = m.getTrips().get(0);

		// the mission status is set to RUNNING
		m.setStatus(Mission.RUNNING);
		System.out.println("Mission " + m.getName() + " is running");

		// the trips which have an associated drone can start

		if (t.getDrone() != null) {
			t.setStartTime(new SimpleDateFormat("HH:mm:ss").format(cal
					.getTime()));
			t.setStatus(Trip.EXECUTING);
			System.out.println("Trip " + t.getName() + " is executing");
			t.getDrone().flyToAndDoAction(t.getTargetLocation(), t.getAction());

			t.getDrone().setStatus(Drone.FREE);
			System.out.println("Drone " + t.getDrone().getId() + " is free");
			t.setStatus(Trip.COMPLETED);
			System.out.println("Trip " + t.getName() + " is completed");

		}

		return m;
	}

}
