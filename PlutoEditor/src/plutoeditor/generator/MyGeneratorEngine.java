package plutoeditor.generator;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import plutoeditor.model.editor.Diagram;
import plutoeditor.model.editor.Node;

public class MyGeneratorEngine {

	Diagram parent;

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
				File fileToSave = fileChooser.getSelectedFile();
				if (!fileToSave.exists()) {
					fileToSave.createNewFile();
				}
				FileWriter fw = new FileWriter(fileToSave);
				
				String s = "ciao";
				
				// Qui la logica che va a scrivere sul file
				fw.write(s);
				
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
