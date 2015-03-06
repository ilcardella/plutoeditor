package plutoeditor.generator;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import plutoeditor.generator.utils.UnzipUtility;
import plutoeditor.model.editor.*;

public class MyGeneratorEngine {

	private Diagram diagram; // Model of the diagram
	private File parentFolder; // Parent folder of all the generated code

	public MyGeneratorEngine(Diagram model) {
		this.diagram = model;
	}

	public void generate() {
		JFrame parentFrame = new JFrame();

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify the destination folder");
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		fileChooser.setAcceptAllFileFilterUsed(false);

		int userSelection = fileChooser.showDialog(parentFrame, "Generate");

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			try {
				// this is the directory chosen by the user, where to generate
				// the code
				parentFolder = fileChooser.getSelectedFile();

				// Extract the template app in the destination folder
				UnzipUtility unzipper = new UnzipUtility();
				InputStream inputStream = getClass()
						.getResource("/template/template.zip").openConnection()
						.getInputStream();
				unzipper.unzip(inputStream, parentFolder.getCanonicalPath());

				// add the model-dependent code to the generated app
				generateModelBasedCode();

				displayNotification("Generation Succeed!");

				System.out.println("Code generated in: "
						+ parentFolder.getAbsolutePath());

			} catch (Exception e) {
				System.out.println("Error during generation.");
				e.printStackTrace();
				displayNotification("Generation Failed!");
			}
		}
	}

	// This method looks for the tags in the fileToSave object and change them
	// with the code generated from the model diagram
	private void generateModelBasedCode() {

		File modelFile = new File(
				parentFolder.getAbsolutePath()
						+ "/src/it/polimi/template/controller/thread/MissionWorker.java");

		String fileAsString = readFromFileToString(modelFile);

		// Filling the tags in the template
		List<Node> children = diagram.getChildrenNodes();
		StringBuilder decStringBuilder = new StringBuilder();
		StringBuilder exeStringBuilder = new StringBuilder();

		// We need to know where the graph flow starts
		Node firstNode = findFirstNode();

		for (Node n : children) {
			// If n is a MissionModifier create the class with custom code
			if (n instanceof MissionModifier) {
				generateMissionModifierClass((MissionModifier) n);
			}
			if (n instanceof GateFIFO) {
				generateGatesModelCode((GateFIFO) n);
			}
			if (n instanceof GateFunnel) {
				generateGatesModelCode((GateFunnel) n);
			}
			if (n instanceof PriorityManager) {
				enableConditinalFeature("PRIORITY_MANAGER");
			}
			if (n instanceof MissionRepeater) {
				enableConditinalFeature("MISSION_REPEATER");
			}
			if (n instanceof Clock) {
				enableConditinalFeature("CLOCK");
			}
			if (n instanceof TimerMonitor) {
				enableConditinalFeature("TIMER_MONITOR");
			}

			// Create the lines to add in declaration space
			String name = getClassNameFromNode(n);
			String line = name + " " + name.toLowerCase() + " = new " + name
					+ "(this);";
			decStringBuilder.append(line);
			decStringBuilder.append('\n');

			// Create the lines to add in execution space
			List<Connection> srcConn = n.getSourceConnections();
			// for each outgoing connections
			for (Connection c : srcConn) {
				// These lines will register the observers of each block
				exeStringBuilder.append(name.toLowerCase() + ".addObserver("
						+ getClassNameFromNode(c.getTargetNode()).toLowerCase()
						+ ");");
				exeStringBuilder.append('\n');
			}
		}
		// This line will launch the first block
		exeStringBuilder.append(getClassNameFromNode(firstNode).toLowerCase()
				+ ".update(null, m);");

		// Now we write the built strings in the destination file
		fileAsString = fileAsString.replaceAll("\\<dec>",
				decStringBuilder.toString());
		fileAsString = fileAsString.replaceAll("\\<exe>",
				exeStringBuilder.toString());

		writeStringToFile(modelFile, fileAsString);
	}

	private void generateMissionModifierClass(MissionModifier n) {
		String classString = n.getTemplateCode(); // String representation of
													// the file
		File classFile = new File(parentFolder.getAbsolutePath()
				+ "/src/it/polimi/template/controller/block/" + n.getName()
				+ ".java");

		if (!classFile.exists()) {
			classFile.getParentFile().mkdirs();
			try {
				classFile.createNewFile();
			} catch (IOException e) {
				System.out.println("MissionModifier class NOT created!");
				e.printStackTrace();
			}
		}

		// Replace <name> tag in classString with the name of the class
		classString = classString.replaceAll("\\<name>", n.getName());

		// Replace <run> tag in classString with customcode
		classString = classString.replaceAll("\\<run>", n.getCustomCode());

		writeStringToFile(classFile, classString);

	}

	// return the className of the parameter without the package as prefix
	public String getClassNameFromNode(Node n) {
		String name = "null";
		if (n instanceof MissionModifier) {
			// The MissionModifier has the final class name as the name given by
			// the user
			name = ((MissionModifier) n).getName();
		} else {
			String className = n.getClass().getName();
			String[] splittedName = className.split("\\.");
			name = splittedName[splittedName.length - 1];
		}
		return name;
	}

	// Show message in a JDialog with an OK button
	private void displayNotification(final String message) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				final JDialog dialog = new JDialog();
				JPanel messagePane = new JPanel();
				messagePane.add(new JLabel(message));
				dialog.getContentPane().add(messagePane);
				JPanel buttonPane = new JPanel();
				JButton button = new JButton("OK");
				buttonPane.add(button);
				button.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						dialog.setVisible(false);
						dialog.dispose();
					}
				});
				dialog.getContentPane().add(buttonPane, BorderLayout.SOUTH);
				dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
				dialog.pack();
				dialog.setLocationRelativeTo(null);
				dialog.setVisible(true);

			}
		});
	}

	// return the node that has an incoming connection from the Start block
	private Node findFirstNode() {
		Node firstNode = null;
		List<Node> children = diagram.getChildrenNodes();
		for (Node n : children) {
			if (n.getName().equals("Start")) {
				List<Connection> conns = n.getSourceConnections();
				firstNode = conns.get(0).getTargetNode();
			}
		}
		return firstNode;
	}

	private void generateGatesModelCode(Node n) {

		File gate = null;

		if (n instanceof GateFIFO)
			gate = new File(parentFolder.getAbsolutePath()
					+ "/src/it/polimi/template/controller/block/GateFIFO.java");
		else if (n instanceof GateFunnel)
			gate = new File(
					parentFolder.getAbsolutePath()
							+ "/src/it/polimi/template/controller/block/GateFunnel.java");

		// We need to know how many incoming connections has the block
		String number = "" + n.getTargetConnections().size();
		String fileAsString = readFromFileToString(gate);
		fileAsString = fileAsString.replaceAll("\\<num>", number);
		writeStringToFile(gate, fileAsString);

	}

	private void enableConditinalFeature(String feature) {
		
		File f = null;
		String tag = null;
		
		switch(feature){
			case "PRIORITY_MANAGER:":
				f = new File(parentFolder.getAbsolutePath()
						+ "/src/it/polimi/template/controller/TripsPageController.java");
				tag = "<tPrt>";
				break;
			case "CLOCK":
				f = new File(parentFolder.getAbsolutePath()
						+ "/src/it/polimi/template/controller/TripsPageController.java");
				tag = "<tDelay>";
				break;
			case "TIMER_MONITOR":
				f = new File(parentFolder.getAbsolutePath()
						+ "/src/it/polimi/template/controller/MissionPageController.java");
				tag = "<tSafe>";
				break;
			case "MISSION_REPEATER":
				f = new File(parentFolder.getAbsolutePath()
						+ "/src/it/polimi/template/controller/MissionPageController.java");
				tag = "<mRep>";
				break;
			default:
				break;
		}
		
		String fileAsString = readFromFileToString(f);
		fileAsString = fileAsString.replaceAll("\\"+tag, "true");
		writeStringToFile(f, fileAsString);
	}
	
	// This method reads the source File content and returns a String object
	// containing that content.
	private String readFromFileToString(File source) {
		String fileAsString = null;

		try {
			FileInputStream fileInputStream = new FileInputStream(source);

			BufferedReader br = new BufferedReader(new InputStreamReader(
					fileInputStream, "UTF-8"));

			StringBuilder sb = new StringBuilder();
			String l;
			while ((l = br.readLine()) != null) {
				sb.append(l);
				sb.append('\n');
			}

			fileAsString = sb.toString();

			br.close();
			fileInputStream.close();

		} catch (IOException e) {
			System.out.println("Exception while reading file "
					+ source.getName() + " in a String");
		}

		return fileAsString;
	}

	// This method write the "string" parameter in the target File
	private void writeStringToFile(File target, String string) {
		try {

			FileOutputStream fileOutputStream = new FileOutputStream(target);
			byte[] fileStringInBytes = string.getBytes();
			fileOutputStream.write(fileStringInBytes);

			fileOutputStream.flush();
			fileOutputStream.close();

		} catch (IOException e) {
			System.out.println("Error writing string to file "
					+ target.getName());
			e.printStackTrace();
		}
	}

} // End
