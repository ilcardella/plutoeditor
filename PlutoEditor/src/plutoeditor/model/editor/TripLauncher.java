package plutoeditor.model.editor;

import java.text.SimpleDateFormat;

import plutoeditor.model.classes.Mission;
import java.util.Calendar;



public class TripLauncher extends Node {
	
	
	@Override
	public Mission run(Mission m) {

		// the mission status is set to RUNNING
		m.setStatus(2);
		Calendar cal=Calendar.getInstance();
		// the start time is set for the trip
		m.getTrips()
				.get(0)
				.setStartTime(
						new SimpleDateFormat("HH:mm:ss").format(cal.getTime()));
		

		return m;
	}


}
