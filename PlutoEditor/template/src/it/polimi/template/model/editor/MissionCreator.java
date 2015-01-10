package it.polimi.template.model.editor;

import it.polimi.template.model.*;

public class MissionCreator implements Node {
	
	@Override
	public Mission run(Mission m) {
		if (m.getTrips() != null && m.getStatus() == Mission.UNEXECUTED) {
			System.out.println("Mission " + m.getName()
					+ " created");
			return m;
			
		} else
			
			return null;
	}
	

}
