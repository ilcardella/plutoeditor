package it.polimi.template.controller;

import it.polimi.template.controller.thread.WorkerThread;
import it.polimi.template.model.Mission;
import it.polimi.template.view.MonitorPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
			ExecutorService executor = Executors.newFixedThreadPool(missions
					.size());
			for (int i = 0; i < missions.size(); i++) {
				Runnable worker = new WorkerThread(missions.get(i));
				executor.execute(worker);
			}
			executor.shutdown();
			while (!executor.isTerminated()) {
			}
			System.out.println("Finished all threads");
		}
	}

	class StopButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Stop all the drones
			monitorPage.clearTable();
		}
	}

}
