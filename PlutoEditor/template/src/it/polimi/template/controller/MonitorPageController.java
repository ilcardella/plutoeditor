package it.polimi.template.controller;

import it.polimi.template.controller.thread.MyWorker;
import it.polimi.template.model.Drone;
import it.polimi.template.model.Mission;
import it.polimi.template.model.Trip;
import it.polimi.template.view.MonitorPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MonitorPageController {

	private MonitorPage monitorPage;
	private List<Mission> missions;

	public MonitorPageController(MonitorPage monitorPage, List<Mission> missions) {
		this.monitorPage = monitorPage;
		this.missions = missions;

		this.monitorPage.setStartButtonListener(new StartButtonListener());
		this.monitorPage.setStopButtonListener(new StopButtonListener());

	}

	class StartButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			launchExecution();
		}
	}

	protected void launchExecution() {
		
		for (int i = 0; i < missions.size(); i++) {
			MyWorker worker = new MyWorker(missions.get(i), this);
			worker.execute();
		}
		

	}

	class StopButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Stop all the drones
			monitorPage.clearTable();
		}
	}

	public void notifyUpdateOfStatus(Mission mission) {

		String missionName = "";
		String missionStatus = "";
		String tripName = "";
		String tripStatus = "";
		int droneID = 0;
		String droneStatus = "";

		missionName = mission.getName();
		missionStatus = Mission.getStatusNameFromValue(mission.getStatus());
		
		if (mission.getTrips().size() > 0) {

			// If there are other trips to complete
			tripName = mission.getTrips().get(0).getName();
			tripStatus = Trip.getStatusNameFromValue(mission.getTrips().get(0).getStatus());

			if (mission.getTrips().get(0).getDrone() != null) {
				droneID = mission.getTrips().get(0).getDrone().getId();
				droneStatus = Drone.getStatusNameFromValue(mission.getTrips().get(0).getDrone().getStatus());
			}
		}
		
		this.monitorPage.fillConsole("MissionName = " + missionName
				+ ", MissionStatus = " + missionStatus + ", DroneID = "
				+ droneID + ", DroneStatus = " + droneStatus + ", TripName = "
				+ tripName + ", TripStatus = " + tripStatus + '\n');
		
		this.monitorPage.updateTableRow(missionName, missionStatus, droneID, droneStatus, tripName, tripStatus);
		
	}

}
