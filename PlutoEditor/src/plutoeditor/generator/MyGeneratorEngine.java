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
import java.io.OutputStream;
import java.net.URL;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

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

				// add the model-dependent code to the generated file
				generateModelCodeInTemplateApp();

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
	private void generateModelCodeInTemplateApp() {
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		String fileAsString = null; // String representation of the file
		File modelFile = new File(parentFolder.getAbsolutePath()
				+ "/src/it/polimi/template/controller/thread/MyWorker.java");

		try {

			fileInputStream = new FileInputStream(modelFile);

			// Read the file with default code already generated
			// and convert it in a String object
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

			// Initialize output stream
			fileOutputStream = new FileOutputStream(modelFile);

			// Filling the declaration tag in the template
			List<Node> children = diagram.getChildrenNodes();
			StringBuilder decStringBuilder = new StringBuilder();
			StringBuilder exeStringBuilder = new StringBuilder();
			Node firstNode = children.get(0); // Usually the first node is the first in the list

			for (Node n : children) {
				// Create the lines to add in declaration space
				String name = getClassNameFromObject(n);
				String line = name + " " + name.toLowerCase() + " = new "
						+ name + "(this);";
				decStringBuilder.append(line);
				decStringBuilder.append('\n');

				// Create the lines to add in execution space
				List<Connection> srcConn = n.getSourceConnections();
				// for each outgoing connections
				for (Connection c : srcConn) {
					// These lines will register the observers of each block
					exeStringBuilder.append(name.toLowerCase() + ".addObserver("
							+ getClassNameFromObject(c.getTargetNode()).toLowerCase() + ");");
					exeStringBuilder.append('\n');
				}
				
				// looking for the first node
				if(n.getTargetConnections() == null || n.getTargetConnections().size() == 0){
					firstNode = n;
				}
			}
			// This line will launch the first block
			exeStringBuilder.append(getClassNameFromObject(firstNode).toLowerCase()+".update(null, m);");
			
			// Write the lines to the fileAsString replacing the tags
			fileAsString = fileAsString.replaceAll("\\<dec>",
					decStringBuilder.toString());
			fileAsString = fileAsString.replaceAll("\\<exe>",
					exeStringBuilder.toString());

			// Convert string to byte[] and write to the outputStream
			byte[] fileStringInBytes = fileAsString.getBytes();
			fileOutputStream.write(fileStringInBytes);

		} catch (Exception e) {
			System.out.println("Error writing generated code from model.");
			e.printStackTrace();
		} finally {

			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					System.out.println("Error closing the fileInputStream.");
					e.printStackTrace();
				}
			}
			if (fileOutputStream != null) {
				try {
					fileOutputStream.flush();
					fileOutputStream.close();
				} catch (IOException e) {
					System.out.println("Error closing the outputStream.");
					e.printStackTrace();
				}
			}
		}
	}

	// return the className of the parameter without the package as prefix
	public String getClassNameFromObject(Object o) {
		String className = o.getClass().getName();
		String[] splittedName = className.split("\\.");
		String name = splittedName[splittedName.length - 1];
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
			    dialog.setDefaultCloseOperation(dialog.DISPOSE_ON_CLOSE);
			    dialog.pack(); 
			    dialog.setVisible(true);
				
			}
		});
	}

} // End
