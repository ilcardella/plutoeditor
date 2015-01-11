package it.polimi.template.view;

import static javax.swing.GroupLayout.Alignment.LEADING;

import java.awt.Container;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class MissionsPage extends JFrame {

	private static final long serialVersionUID = 1L;

	private DefaultListModel<String> model;
	private JList<String> list;
	private JButton remallbtn;
	private JButton addbtn;
	private JButton renbtn;
	private JButton delbtn;
	private JButton tpsbtn;
	private JButton okbtn;

	public MissionsPage() {
		initUI();
	}

	private void createList() {

		model = new DefaultListModel<String>();
		list = new JList<String>(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	private void createButtons() {

		remallbtn = new JButton("Remove All");
		addbtn = new JButton("Add Mission");
		renbtn = new JButton("Rename");
		delbtn = new JButton("Delete");
		tpsbtn = new JButton("Set Trips");
		okbtn = new JButton("Ok");

	}

	private void initUI() {

		createList();
		createButtons();
		JScrollPane scrollpane = new JScrollPane(list);
		Container pane = getContentPane();
		GroupLayout gl = new GroupLayout(pane);
		pane.setLayout(gl);

		gl.setAutoCreateContainerGaps(true);
		gl.setAutoCreateGaps(true);

		gl.setHorizontalGroup(gl
				.createSequentialGroup()
				.addComponent(scrollpane)
				.addGroup(
						gl.createParallelGroup().addComponent(addbtn)
								.addComponent(renbtn).addComponent(delbtn)
								.addComponent(remallbtn).addComponent(tpsbtn)
								.addComponent(okbtn)

				));

		gl.setVerticalGroup(gl
				.createParallelGroup(LEADING)
				.addComponent(scrollpane)
				.addGroup(
						gl.createSequentialGroup().addComponent(addbtn)
								.addComponent(renbtn).addComponent(delbtn)
								.addComponent(tpsbtn).addComponent(okbtn)
								.addComponent(remallbtn)

				)

		);

		gl.linkSize(addbtn, renbtn, delbtn, remallbtn, tpsbtn, okbtn);
		pack();

		setTitle("Pluto-Missions Page");
		setSize(1000, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	// add button

	public String showNewNamePanel(String name) {
		String text = JOptionPane.showInputDialog(name);
		String missionName = null;

		if (text != null) {
			missionName = text.trim();

		} else {
			return "null";
		}

		return missionName;
	}

	public void setAddMissionButtonListener(ActionListener listener) {
		addbtn.addActionListener(listener);
	}

	public void addMissionToList(String name) {

		if (!name.isEmpty())
			model.addElement(name);
	}

	// delete button

	public String getSelectedMission() {
		ListSelectionModel selmodel = list.getSelectionModel();
		int index = selmodel.getMinSelectionIndex();
		if (index >= 0) {

			String mission = model.getElementAt(index).toString();
			return mission;
		}
		return "";
	}

	public void removeMissionFromList(String name) {

		ListSelectionModel selmodel = list.getSelectionModel();
		int index = selmodel.getMinSelectionIndex();

		model.remove(index);
	}

	public void setDeleteMissionButtonListener(ActionListener listener) {
		delbtn.addActionListener(listener);
	}

	// remove all button

	public void clearMissionList() {

		model.clear();
	}

	public void setRemoveAllMissionButtonListener(ActionListener listener) {
		remallbtn.addActionListener(listener);
	}

	// rename button

	public void setRenameButtonListener(ActionListener listener) {
		renbtn.addActionListener(listener);
	}

	public void renameSelectedMission(String name) {
		ListSelectionModel selmodel = list.getSelectionModel();
		int index = selmodel.getMinSelectionIndex();
		if (index >= 0) {
			model.remove(index);
			model.add(index, name);
		}

	}

	// set trips button

	public void setTripsButtonListener(ActionListener listener) {
		tpsbtn.addActionListener(listener);
	}

	// ok button

	public void setMissionsPageOkButtonListener(ActionListener listener) {
		okbtn.addActionListener(listener);

	}

	public void setListMouseListener(MouseListener listener) {
		list.addMouseListener(listener);
	}

}
