package it.polimi.template.controller;

import it.polimi.template.model.Action;

import it.polimi.template.model.Item;
import it.polimi.template.model.Mission;
import it.polimi.template.model.Trip;
import it.polimi.template.utils.ItemsManager;
import it.polimi.template.view.TripsPage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TripsPageController {

	private TripsPage tripsPage;
	private Mission mission;
	private char tripCounter;

	public TripsPageController(TripsPage tripsPage, Mission mission) {
		this.tripsPage = tripsPage;
		this.mission = mission;

		// Initialize the tripCounter
		if (mission.getTrips() != null && mission.getTrips().size() > 0) {
			String max = "A";
			for (Trip t : mission.getTrips()) {
				String[] array = t.getName().split("-");
				max = array[1];
			}
			tripCounter = max.charAt(0);
			tripCounter++;
		} else {
			tripCounter = 65; // equals to A
		}

		this.tripsPage.setOkButtonListener(new TripsPageOkButtonListener());
		this.tripsPage
				.setDeleteAllButtonListener(new TripsPageDeleteAllButtonListener());
		this.tripsPage.setExportDoneActionListener(new ExportDoneListener());

	}

	class TripsPageOkButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			tripsPage.killWindow();
		}
	}

	class TripsPageDeleteAllButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			mission.getTrips().clear();
			// update the view
			tripsPage.deleteAllTrips();
		}

	}

	public class ExportDoneListener {

		public void actionPerformed() {
			String actionName = tripsPage.getAction();
			manageDragAndDrop(actionName);
		}

	}

	public void manageDragAndDrop(String actionName) {

		// if drop event is ok, create the Trip and set the name
		Trip trip = new Trip();

		trip.setName(mission.getName() + "-" + tripCounter);
		tripCounter++;

		// cycles all the possible actions
		for (Action a : Action.values()) {

			// assign the right action to the trip
			if (a.toString().equals(actionName)) {
				trip.setAction(a);

				// some actions requires the items, other not
				if (a.isItemRequired()) {

					// create a string list with the name of the items
					List<String> itemsNames = new ArrayList<String>();
					for (Item i : ItemsManager.getItems())
						itemsNames.add(i.getName());

					// get the items from the user input
					String iName = tripsPage.showItemsPanel(itemsNames);

					// set the item to the trip
					for (Item i : ItemsManager.getItems())
						if (i.getName().equals(iName))
							trip.setItem(i);
				}
			}
		}

		// set the priority to the trip
		int priority = tripsPage.showPriorityPanel();
		trip.setPriority(priority);

		// set the delay
		int delay = tripsPage.showDelayPanel();
		trip.setDelay(delay);

		// add to the trip list of the mission
		mission.getTrips().add(trip);

		// update the view
		tripsPage.fillTripList(trip.getName());

	}

}
