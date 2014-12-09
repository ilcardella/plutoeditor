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

				// Write the generated code to the file
				writeCodeToFile();

				System.out.println("Generated file: "
						+ fileToSave.getAbsolutePath());

			} catch (Exception e) {
				System.out.println("Error during generation.");
				e.printStackTrace();
			}
		}
	}

	private void writeCodeToFile() {
		InputStream templateInputStream = null;
		FileOutputStream fileOutputStream = null;
		FileInputStream fileInputStream = null;

		try {
			// The inputStream is the template file with default code
			templateInputStream = getClass()
					.getResource("/template/MyTemplate.java").openConnection()
					.getInputStream();

			// the output is the file with the generated code
			fileOutputStream = new FileOutputStream(fileToSave);

			// copy the content of the template to the final file
			copyInputStreamToFile(templateInputStream, fileOutputStream);

			// Get the FileInputStream of the generated file
			// Because we need to replace the tags lines
			fileInputStream = new FileInputStream(fileToSave);

			// add the model-dependent code to the generated file
			writeModelCodeToFile(fileInputStream, fileOutputStream);

		} catch (IOException e) {
			System.out.println("Error writing code.");
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
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					System.out.println("Error closing the fileInputStream.");
					e.printStackTrace();
				}

			}
		}

	}

	// This method copy the content from the input stream to the output file
	// stream
	private void copyInputStreamToFile(InputStream input,
			FileOutputStream output) {
		InputStream inputStream = input;
		FileOutputStream outputStream = output;

		try {
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}

		} catch (IOException e) {
			System.out.println("Error copying from template.");
			e.printStackTrace();
		}
	}

	// This method looks for the tags in the fileToSave object and change them
	// with the code generated from the model diagram
	private void writeModelCodeToFile(FileInputStream fileInputStream,
			FileOutputStream fileOutputStream) {
		String fileAsString = "";
		try {
			
			// Read the file with default code already generated
			// and convert it in a String object
			BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream, "UTF-8" ));
			StringBuilder sb = new StringBuilder();
			String l;
			while ((l = br.readLine()) != null) {
				sb.append(l);
				sb.append('\n');
			}
			fileAsString = sb.toString();

			// Filling the declaration tag in the template
			List<Node> children = diagram.getChildrenNodes();
			String line = "";
			for (Node n : children) {

				// Create the line to add
				String className = n.getClass().getName();
				String[] splittedName = n.getClass().getName().split(".");
				String name = splittedName[splittedName.length - 1];
				line = className + " " + name.toLowerCase() + " = new "
						+ className + "();";
				
				// Convert string to byte[]
				byte[] lineInBytes = line.getBytes();

				// Look for <declaration></> tag
				// TODO
				int start = fileAsString.indexOf("<declaration>");
				int end = fileAsString.indexOf("</declaration>");

				// Write the line to the file
				fileOutputStream.write(lineInBytes, start, end-start);
			}

			// Filling the execution tag
			// TODO

		} catch (Exception e) {
			System.out.println("Error copying generated from model code.");
			e.printStackTrace();
		}
	}
}
