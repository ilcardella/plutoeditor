package it.polimi.template.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import it.polimi.template.view.MissionsPage;
import it.polimi.template.view.MonitorPage;
import it.polimi.template.view.TripsPage;
import it.polimi.template.model.*;

public class MissionPageController {

	private MissionsPage missionPage;
	List<Mission> missions;

	public MissionPageController(MissionsPage view, List<Item> items,
			List<Drone> drones) {

		this.missionPage = view;
		this.missionPage
				.addMissionButtonListener(new AddMissionButtonListener());
		this.missionPage
				.deleteMissionButtonListener(new DeleteMissionButtonListener());
		this.missionPage
				.removeAllMissionButtonListener(new RemoveAllMissionButtonListener());
		this.missionPage
				.renameButtonListener(new RenameButtonListener());
		this.missionPage
				.setTripsListener(new SetTripsListener());
		this.missionPage
				.missionsPageOkButtonListener(new MissionsPageOkButtonListener());

	}

	class AddMissionButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Mission m = new Mission();
			String name = missionPage.showNewNamePanel("Write mission name");
			m.setName(name);
			if (missions == null)
				missions = new ArrayList<Mission>();
			missions.add(m);

			missionPage.addMissionToList(name);
		}

	}

	class DeleteMissionButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String name = missionPage.getSelectedMission();
			for (int i = 0; i < missions.size(); i++)
				if (missions.get(i).getName().equals(name))
					missions.remove(i);
			missionPage.removeMissionFromList(name);

		}

	}

	class RemoveAllMissionButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			missions.clear();

			missionPage.clearMissionList();
		}

	}

	class RenameButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String oldName = missionPage.getSelectedMission();
			String newName = missionPage.showNewNamePanel("Rename Mission");
			for (int i = 0; i < missions.size(); i++)
				if (missions.get(i).getName().equals(oldName))
					missions.get(i).setName(newName);
			missionPage.renameSelectedMission(newName);
		}
	}
	
	class SetTripsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			String missionName = missionPage.getSelectedMission();
			
			if (missionName!=""){
			
			TripsPage tripsPage = new TripsPage(missionName);
			
			for(int i = 0; i<missions.size();i++)
				if(missions.get(i).getName().equals(missionName)){
					TripsPageController tripsPageController = new TripsPageController(tripsPage, missions.get(i));
					tripsPage.setVisible(true);
				}
		}
		}
	}
	
	class MissionsPageOkButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			MonitorPage monitorPage = new MonitorPage();
			MonitorPageController monitorController = new MonitorPageController(monitorPage, missions);
			monitorPage.setVisible(true);
		}
	}

}
