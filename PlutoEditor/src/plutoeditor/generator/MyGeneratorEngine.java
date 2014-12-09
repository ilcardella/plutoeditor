package plutoeditor.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import plutoeditor.model.editor.*;
import plutoeditor.model.classes.*;

public class MyGeneratorEngine {

	private Diagram diagram;
	private File fileToSave;

	public MyGeneratorEngine(Diagram model) {
		this.diagram = model;
	}

	public void generate() {
		JFrame parentFrame = new JFrame();

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");

		int userSelection = fileChooser.showSaveDialog(parentFrame);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			try {
				fileToSave = fileChooser.getSelectedFile();
				if (!fileToSave.exists()) {
					fileToSave.createNewFile();
				}

				// Generate all dependency classes in the same path of the file
				// TODO
				
				// copy the content of the template to the final file
				copyTemplateCodeToFile();

				// add the model-dependent code to the generated file
				writeModelCodeToFile();

				System.out.println("Generated file: "
						+ fileToSave.getAbsolutePath());

			} catch (Exception e) {
				System.out.println("Error during generation.");
				e.printStackTrace();
			}
		}
	}

	// This method copy the content from the input stream to the output file
	// stream
	private void copyTemplateCodeToFile() {
		InputStream templateInputStream = null;
		FileOutputStream fileOutputStream = null;

		try {
			// The inputStream is the template file with default code
			templateInputStream = getClass()
					.getResource("/template/MyTemplate.java").openConnection()
					.getInputStream();

			// the output is the file with the generated code
			fileOutputStream = new FileOutputStream(fileToSave);

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = templateInputStream.read(bytes)) != -1) {
				fileOutputStream.write(bytes, 0, read);
			}

		} catch (IOException e) {
			System.out.println("Error copying from template.");
			e.printStackTrace();
		} finally {
			if (templateInputStream != null) {
				try {
					templateInputStream.close();
				} catch (IOException e) {
					System.out
							.println("Error closing the template templateInputStream.");
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

	// This method looks for the tags in the fileToSave object and change them
	// with the code generated from the model diagram
	private void writeModelCodeToFile() {
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		String fileAsString = ""; // String representation of the file

		try {

			fileInputStream = new FileInputStream(fileToSave);

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
			fileOutputStream = new FileOutputStream(fileToSave);

			// Replace the <imp> import tag with the needed imports
			// TODO
			
			// Filling the declaration tag in the template
			List<Node> children = diagram.getChildrenNodes();
			sb = new StringBuilder();

			for (Node n : children) {

				// Create the line to add
				String className = n.getClass().getName();
				String[] splittedName = n.getClass().getName().split("\\.");
				String name = splittedName[splittedName.length - 1];
				String line = className + " " + name.toLowerCase() + " = new "
						+ className + "();";
				sb.append(line);
				sb.append('\n');
			}

			// Write the line to the fileAsString
			fileAsString = fileAsString.replaceAll("\\<dec>", sb.toString());

			// Filling the execution tag
			// TODO
			
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
}
