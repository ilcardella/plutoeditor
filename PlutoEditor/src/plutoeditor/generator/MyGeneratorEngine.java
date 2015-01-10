package plutoeditor.generator;

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

import javax.swing.JFileChooser;
import javax.swing.JFrame;

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

				// Generate the template app in the destination folder
				generateTemplateApp();

				// add the model-dependent code to the generated file
				// TODO generateModelCodeInTemplateApp();

				System.out.println("Code generated in: "
						+ parentFolder.getAbsolutePath());

			} catch (Exception e) {
				System.out.println("Error during generation.");
				e.printStackTrace();
			}
		}
	}

	// This method copy the content from the input stream to the output file
	// stream
	private void generateTemplateApp() {

		// ex: generateTemplateFile( physical name of the file,
		// resource path from template folder)
		//generateTemplateFile("/MyTemplate.java", "/template/MyTemplate.java");
		try {
			URL sourcePath = getClass().getProtectionDomain().getCodeSource().getLocation();
			URL url = getClass().getResource("/template/src");
			url = new URL(sourcePath.toString()+url.toString());
			File srcDir = new File(new File(url.toURI()).getAbsolutePath());
			File destDir = new File(parentFolder.getAbsolutePath());
			copy(srcDir, destDir);
		} catch (Exception e) {
			System.out.println("Error copying the template App");
			e.printStackTrace();
		}

	}

//	private void generateTemplateFile(String physicalName, String resourcePath) {
//		InputStream templateInputStream = null;
//		FileOutputStream fileOutputStream = null;
//		String physicalPath = parentFolder.getAbsolutePath() + physicalName;
//
//		try {
//			File f = new File(physicalPath);
//			if (!f.exists()) {
//				f.getParentFile().mkdirs(); // Create parent folders if needed
//				f.createNewFile(); // Create the file itself
//			}
//			// Getting the inputstream of the java file to generate
//			templateInputStream = getClass().getResource(resourcePath)
//					.openConnection().getInputStream();
//
//			// the output is the file with the generated code
//			fileOutputStream = new FileOutputStream(f);
//
//			// copy the inputstream to the outputstream
//			int read = 0;
//			byte[] bytes = new byte[1024];
//			while ((read = templateInputStream.read(bytes)) != -1) {
//				fileOutputStream.write(bytes, 0, read);
//			}
//
//		} catch (IOException e) {
//			System.out.println("Error copying from template file.");
//			e.printStackTrace();
//		} finally {
//			if (templateInputStream != null) {
//				try {
//					templateInputStream.close();
//				} catch (IOException e) {
//					System.out
//							.println("Error closing the template templateInputStream.");
//					e.printStackTrace();
//				}
//			}
//			if (fileOutputStream != null) {
//				try {
//					fileOutputStream.flush();
//					fileOutputStream.close();
//				} catch (IOException e) {
//					System.out.println("Error closing the outputStream.");
//					e.printStackTrace();
//				}
//
//			}
//		}
//
//	}

	// This method looks for the tags in the fileToSave object and change them
	// with the code generated from the model diagram
	private void generateModelCodeInTemplateApp() {
		FileInputStream fileInputStream = null;
		FileOutputStream fileOutputStream = null;
		String fileAsString = null; // String representation of the file
		// TODO caricare in modelfile il file da modificare
		File modelFile = new File(parentFolder.getAbsolutePath()
				+ "/MyTemplate.java");

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
			sb = new StringBuilder();

			for (Node n : children) {
				// Create the line to add

				String name = getClassNameFromObject(n);
				String line = name + " " + name.toLowerCase() + " = new "
						+ name + "();";
				sb.append(line);
				sb.append('\n');
			}

			// Write the line to the fileAsString
			fileAsString = fileAsString.replaceAll("\\<dec>", sb.toString());

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

	// ------------------------------------------------------

	public void copy(File sourceLocation, File targetLocation)
			throws IOException {
		if (sourceLocation.isDirectory()) {
			copyDirectory(sourceLocation, targetLocation);
		} else {
			copyFile(sourceLocation, targetLocation);
		}
	}

	private void copyDirectory(File source, File target) throws IOException {
		if (!target.exists()) {
			target.mkdir();
		}

		for (String f : source.list()) {
			copy(new File(source, f), new File(target, f));
		}
	}

	private void copyFile(File source, File target) throws IOException {
		try (InputStream in = new FileInputStream(source);
				OutputStream out = new FileOutputStream(target)) {
			byte[] buf = new byte[1024];
			int length;
			while ((length = in.read(buf)) > 0) {
				out.write(buf, 0, length);
			}
		}
	}

} // End
