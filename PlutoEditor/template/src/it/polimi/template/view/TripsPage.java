package it.polimi.template.view;


import it.polimi.template.model.*;
import java.awt.BorderLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import java.awt.dnd.DragSource;
import java.awt.dnd.DragSourceDragEvent;
import java.awt.dnd.DragSourceDropEvent;
import java.awt.dnd.DragSourceEvent;
import java.awt.dnd.DragSourceListener;
import java.awt.dnd.DropTarget;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	private DefaultListModel model;
	private JList list;
	private JLabel label;
	private JButton ok;
	private JButton delete;

	private String nameMission;
	private ImageIcon icon;

	DragSource ds;
	StringSelection transferable;

	public TripsPage(String name) {
		this.nameMission = name;

		try {
			initUI();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void createList() {

		model = new DefaultListModel();
		list = new JList(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setDragEnabled(true);
		list.setTransferHandler(new TransferHandler("text"));

		for (Action a : Action.values())
			model.addElement(a.toString());

	}

	public final void initUI() throws IOException {

		setLayout(new BorderLayout());

		icon = new ImageIcon("Map/casa.gif");

		label = new JLabel(icon);
		label.setTransferHandler(new TransferHandler("text"));

		createList();
		JScrollPane actionsPane = new JScrollPane(list);
		ds = new DragSource();
		ds.createDefaultDragGestureRecognizer(list, DnDConstants.ACTION_COPY,
				this);
		setVisible(true);

		JPanel buttonsPane = new JPanel();
		ok = new JButton("Ok");

		delete = new JButton("Delete all trips");

		buttonsPane.add(ok);
		buttonsPane.add(delete);

		MouseListener listener = new DragMouseAdapter();
		label.addMouseListener(listener);

		getContentPane().add(label, BorderLayout.SOUTH);
		getContentPane().add(actionsPane, BorderLayout.NORTH);
		getContentPane().add(buttonsPane, BorderLayout.WEST);

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

	@Override
	public void dragEnter(DragSourceDragEvent dsde) {
		System.out.println("Drag Enter");

	}

	@Override
	public void dragOver(DragSourceDragEvent dsde) {
		System.out.println("Drag Over");

	}

	@Override
	public void dropActionChanged(DragSourceDragEvent dsde) {
		System.out.println("Drop Action Changed");

	}

	@Override
	public void dragExit(DragSourceEvent dse) {
		System.out.println("Drag Exit");

	}

	@Override
	public void dragDropEnd(DragSourceDropEvent dsde) {
		System.out.print("Drag Drop End: ");
		if (dsde.getDropSuccess()) {
			System.out.println("Succeeded");
		} else {
			System.out.println("Failed");
		}
	}

	public String getNameMission() {
		return nameMission;
	}

	public void setNameMission(String nameMission) {
		this.nameMission = nameMission;
	}

	class TransferableString implements Transferable {

		protected DataFlavor stringFlavor = new DataFlavor(String.class,
				"A String Object");

		public TransferableString(String string) {
			this.action = string;
		}

		protected DataFlavor[] supportedFlavors = { DataFlavor.stringFlavor };
		String action;

		@Override
		public DataFlavor[] getTransferDataFlavors() {
			return supportedFlavors;
		}

		@Override
		public boolean isDataFlavorSupported(DataFlavor flavor) {

			if (flavor.equals(DataFlavor.stringFlavor))
				return true;

			return false;
		}

		@Override
		public Object getTransferData(DataFlavor flavor)
				throws UnsupportedFlavorException, IOException {

			if (flavor.equals(DataFlavor.stringFlavor))
				return action;

			else
				throw new UnsupportedFlavorException(flavor);
		}
	}

	// drag and drop listeners

	public void setDropTargetListener() {

		label.setDropTarget(new DropTarget());

	}

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
		if (text3 != null)
			return Integer.parseInt(text3.trim());

		return 0;
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
	
	
	public void deleteAllTrips(){
		//TODO
	}

}
