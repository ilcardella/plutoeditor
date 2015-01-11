package it.polimi.template.view;

import it.polimi.template.model.*;

import java.awt.BorderLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import it.polimi.template.controller.TripsPageController.ExportDoneListener;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;

public class TripsPage extends JFrame implements DragSourceListener,
		DragGestureListener {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	private DefaultListModel<String> model;
	private DefaultListModel<String> model1;

	private JList<String> list;
	private JList<String> tripList;

	private ExportDoneListener edp;

	private JLabel label;
	private JButton ok;
	private JButton delete;

	List<String> tripsNames;
	private String nameMission;
	private String selectedAction;
	private ImageIcon icon;

	DragSource ds;
	StringSelection transferable;

	public TripsPage(String name, List<String> tripsNames) {
		this.nameMission = name;
		this.tripsNames = tripsNames;

		try {
			initUI();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createActionList() {

		model = new DefaultListModel<String>();
		list = new JList<String>(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setDragEnabled(true);
		list.setTransferHandler(new TransferHandler("text"));

		for (Action a : Action.values())
			model.addElement(a.toString());

	}

	private void createTripList() {

		model1 = new DefaultListModel<String>();
		tripList = new JList<String>(model1);
		tripList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		for(String name: tripsNames){
			 model1.addElement(name);
		}

	}

	public final void initUI() throws IOException {

		setLayout(new BorderLayout());

		icon = new ImageIcon("Map/casa.gif");

		label = new JLabel(icon);
		label.setTransferHandler(new TransferHandler("text"));
		createActionList();
		createTripList();
		JScrollPane actionsPane = new JScrollPane(list);
		JScrollPane tripsPane = new JScrollPane(tripList);

		ds = new DragSource();
		ds.createDefaultDragGestureRecognizer(list, DnDConstants.ACTION_COPY,
				this);

		setVisible(true);

		JPanel buttonsPane = new JPanel();
		ok = new JButton("Ok");

		delete = new JButton("Delete all trips");

		buttonsPane.add(ok);
		buttonsPane.add(delete);

		new MyDropTargetListener(label);
		MouseListener listener = new DragMouseAdapter();
		label.addMouseListener(listener);

		getContentPane().add(label, BorderLayout.WEST);
		getContentPane().add(actionsPane, BorderLayout.NORTH);
		getContentPane().add(buttonsPane, BorderLayout.SOUTH);
		getContentPane().add(tripsPane, BorderLayout.EAST);

		setTitle("Pluto-Trips Page (" + nameMission + ")");
		setSize(700, 600);
		setLocationRelativeTo(null);

	}

	private class DragMouseAdapter extends MouseAdapter {
		public void mousePressed(MouseEvent e) {
			JComponent c = (JComponent) e.getSource();
			TransferHandler handler = c.getTransferHandler();
			handler.exportAsDrag(c, e, TransferHandler.COPY);
		}
	}

	@Override
	public void dragGestureRecognized(DragGestureEvent dge) {

		System.out.println("Drag Gesture Recognized!");
		transferable = new StringSelection(list.getSelectedValue().toString());
		ds.startDrag(dge, DragSource.DefaultCopyDrop, transferable, this);

	}

	private class MyDropTargetListener extends DropTargetAdapter {

		public MyDropTargetListener(JLabel label) {
			new DropTarget(label, DnDConstants.ACTION_COPY, this,
					true, null);
		}

		@Override
		public void drop(DropTargetDropEvent event) {

			try {
				Transferable tr = event.getTransferable();
				selectedAction = (String) tr
						.getTransferData(DataFlavor.stringFlavor);

				if (event.isDataFlavorSupported(DataFlavor.stringFlavor)) {

					event.acceptDrop(DnDConstants.ACTION_COPY);

					edp.actionPerformed();
					event.dropComplete(true);

				}
			} catch (Exception e) {
				e.printStackTrace();
				event.rejectDrop();
			}

		}

	}

	@Override
	public void dragEnter(DragSourceDragEvent dsde) {}

	@Override
	public void dragOver(DragSourceDragEvent dsde) {}

	@Override
	public void dropActionChanged(DragSourceDragEvent dsde) {}

	@Override
	public void dragExit(DragSourceEvent dse) {}

	@Override
	public void dragDropEnd(DragSourceDropEvent dsde) {}

	// drag and drop listeners

	public String showItemsPanel(List<String> items) {
		ArrayList<String> choices = new ArrayList<String>();
		for (String i : items)
			choices.add(i);

		String[] simpleArray = new String[choices.size()];
		choices.toArray(simpleArray);

		String input = (String) JOptionPane
				.showInputDialog(null, "Set the item", "Choose the item",
						JOptionPane.QUESTION_MESSAGE, null, simpleArray,
						simpleArray[0]);
		System.out.println(input);

		return input;
	}

	public int showPriorityPanel() {

		ArrayList<String> choosePriority = new ArrayList<String>();

		choosePriority.add("NORMAL");
		choosePriority.add("LOW");
		choosePriority.add("HIGH");
		choosePriority.add("VERY HIGH");
		choosePriority.add("VERY LOW");

		String[] simpleArray = new String[choosePriority.size()];
		choosePriority.toArray(simpleArray);

		String input1 = (String) JOptionPane
				.showInputDialog(null, "Set the priority for the trip ",
						"Choose the priority", JOptionPane.QUESTION_MESSAGE,
						null, simpleArray, simpleArray[0]);

		if (input1.toString() == "NORMAL")
			return 100;
		if (input1.toString() == "LOW")
			return 50;
		if (input1.toString() == "HIGH")
			return 150;
		if (input1.toString() == "VERY HIGH")
			return 200;
		if (input1.toString() == "VERY LOW")
			return 1;

		return 100;
	}

	public int showDelayPanel() {

		String text3 = JOptionPane
				.showInputDialog("Indicate the delay for the trip");

		String delay = text3.trim();
		
		if (delay.isEmpty())
			return 0;

		else
		return Integer.parseInt(delay);

	}

	public void fillTripList(String name) {
		model1.addElement(name);
	}
	public void setExportDoneActionListener(ExportDoneListener listener) {
		edp = listener;

	}

	public String getAction() {
		return selectedAction;
	}

	// ok button

	public void setOkButtonListener(ActionListener listener) {
		ok.addActionListener(listener);

	}

	public void killWindow() {
		setVisible(false);
	}

	// delete button

	public void setDeleteAllButtonListener(ActionListener listener) {
		delete.addActionListener(listener);

	}

	public void deleteAllTrips() {
		model1.removeAllElements();
	}


}
