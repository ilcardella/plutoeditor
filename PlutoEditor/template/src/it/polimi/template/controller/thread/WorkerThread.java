package it.polimi.template.controller.thread;

import it.polimi.template.model.Mission;
import it.polimi.template.model.Trip;
import it.polimi.template.model.editor.*;

public class WorkerThread implements Runnable {

	private Mission m;
	private MissionCreator mc = new MissionCreator();
	private DroneAllocator da = new DroneAllocator();
	private TripLauncher tl = new TripLauncher();
	private TripMonitor tm = new TripMonitor();

	public WorkerThread(Mission mission) {
		this.m = mission;
	}

	@Override
	public void run() {

		System.out.println("Mission " + m.getName() + " started");

		m = mc.run(m);

		while (m.getStatus() != Mission.COMPLETED) {

			m = da.run(m);

			m = tl.run(m);

			m = tm.run(m);

			if (m.getStatus() == Mission.FAILED)
				break;
		}

		System.out.println("Mission " + m.getName() + " ended with result: "
				+ m.getStatus());

	}
}
