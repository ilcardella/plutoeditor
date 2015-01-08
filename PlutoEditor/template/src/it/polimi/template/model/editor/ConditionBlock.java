package it.polimi.template.model.editor;

import it.polimi.template.model.*;

public class ConditionBlock implements Node {

	@Override
	public Mission run(Mission m) {
	
for(Trip t: m.getTrips())		
	if (t.getDelay()>0)
		t.setStatus(5);
		
		
		return m;
	}

}
