package it.polimi.template.model.editor;

import it.polimi.template.model.*;

public class ConditionBlock implements Node {

	@Override
	public Mission run(Mission m) {

		// get the next trip
		Trip t = m.getTrips().get(0);
		
		// if delay > 0 change the status
		if (t.getDelay() > 0)
			t.setStatus(Trip.DELAYED);

		return m;
	}

}
