package plutoeditor.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import plutoeditor.model.editor.Diagram;

public class MyGeneratorEngine {

	Diagram parent;
	File fileToSave;
	FileWriter fw;

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
				fw = new FileWriter(fileToSave);

				// Qui scrivo la parte di codice standard
				writeDefaultTemplateCode();

				// Qui la logica che va a completare i tag con il modello
				// ottenuto
				writeModelBasedCode();

				fw.flush();
				fw.close();
				System.out.println("Save as file: "
						+ fileToSave.getAbsolutePath());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	void writeDefaultTemplateCode() {
		try {
			fw.write("parte standard");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void writeModelBasedCode() {
		try {
			fw.write("parte di logica");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
