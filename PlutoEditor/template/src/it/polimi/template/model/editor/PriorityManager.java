package it.polimi.template.model.editor;

import it.polimi.template.model.*;

public class PriorityManager implements Node {

	@Override
	public Mission run(Mission m) {
		// increment the priority to HIGH level
		m.getTrips().get(0).setPriority(150);
		m.setStatus(Mission.STANDBY);
		return m;
	}

}
