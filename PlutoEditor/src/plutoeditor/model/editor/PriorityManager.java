package plutoeditor.model.editor;

import plutoeditor.model.classes.Mission;


public class PriorityManager extends Node {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public Mission run(Mission m) {
		// the new priority for the trip is set by the user
		int newPriority = m.getTrips().get(0).getPriority() + 1;
		m.getTrips().get(0).setPriority(newPriority);
		m.setStatus(Mission.STANDBY);
		return m;
	}

}
