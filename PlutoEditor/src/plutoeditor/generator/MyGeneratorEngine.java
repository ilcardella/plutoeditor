package plutoeditor.generator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import plutoeditor.model.editor.Diagram;

public class MyGeneratorEngine {

	Diagram parent;
	File fileToSave;

	public MyGeneratorEngine(Diagram model) {
		this.parent = model;
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

				// Qui scrivo la parte di codice standard
				writeDefaultTemplateCode();

				// Qui la logica che va a completare i tag con il modello
				// ottenuto
				writeModelBasedCode();

				System.out.println("Save as file: "
						+ fileToSave.getAbsolutePath());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// Write the code in the template .java file in the destination .java file
	// chosen by the user
	// The template contains the default code of the final app that doesn't
	// depend on the
	// diagram model
	void writeDefaultTemplateCode() {
		InputStream inputStream = null;
		FileOutputStream outputStream = null;

		try {
			inputStream = getClass().getResource("/template/MyTemplate.java")
					.openConnection().getInputStream();
			outputStream = new FileOutputStream(fileToSave);

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}
	}

	// This method looks for the tags in the fileToSave object and change them
	// with
	// the code generated from the model diagram
	void writeModelBasedCode() {
		try {
			System.out.println("sbam");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
