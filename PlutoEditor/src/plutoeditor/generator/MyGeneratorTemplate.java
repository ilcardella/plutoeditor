package plutoeditor.generator;

import java.io.File;
import java.io.FileWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import plutoeditor.model.editor.Diagram;

public class MyGeneratorTemplate {

	Diagram parent;

	public MyGeneratorTemplate(Diagram model) {
		this.parent = model;
	}

	public void generate() {
		JFrame parentFrame = new JFrame();

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");

		int userSelection = fileChooser.showSaveDialog(parentFrame);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			try {
				File fileToSave = fileChooser.getSelectedFile();
				if (!fileToSave.exists()) {
					fileToSave.createNewFile();
				}
				FileWriter fw = new FileWriter(fileToSave);
				
				// Qui la logica che va a scrivere sul file
				fw.write("prova");
				
				fw.flush();
				fw.close();
				System.out.println("Save as file: "
						+ fileToSave.getAbsolutePath());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
