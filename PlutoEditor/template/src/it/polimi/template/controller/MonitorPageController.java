package it.polimi.template.controller;

import it.polimi.template.controller.thread.MyWorker;
import it.polimi.template.model.Mission;
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
//		ExecutorService executor = Executors
//				.newFixedThreadPool(missions.size());
//		for (int i = 0; i < missions.size(); i++) {
//			Runnable worker = new WorkerThread(missions.get(i), this);
//			executor.execute(worker);
//		}
//		executor.shutdown();
//		while (!executor.isTerminated()) {
//		}
//		System.out.println("Finished all threads");
		
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
		int missionStatus = 0;
		String tripName = "";
		int tripStatus = 0;
		int droneID = 0;
		int droneStatus = 0;

		if (mission.getTrips().size() > 0) {

			// If there are other trips to complete
			missionName = mission.getName();
			missionStatus = mission.getStatus();
			tripName = mission.getTrips().get(0).getName();
			tripStatus = mission.getTrips().get(0).getStatus();

			if (mission.getTrips().get(0).getDrone() != null) {
				droneID = mission.getTrips().get(0).getDrone().getId();
				droneStatus = mission.getTrips().get(0).getDrone().getStatus();
			}

		} else {
			// If it is the last update
			missionName = mission.getName();
			missionStatus = mission.getStatus();
		}

		this.monitorPage.fillConsole("MissionName = " + missionName
				+ ", MissionStatus = " + missionStatus + ", DroneID = "
				+ droneID + ", DroneStatus = " + droneStatus + ", TripName = "
				+ tripName + ", TripStatus = " + tripStatus + '\n');
		
		this.monitorPage.updateTableRow(missionName, missionStatus, droneID, droneStatus, tripName, tripStatus);
		
	}

}
