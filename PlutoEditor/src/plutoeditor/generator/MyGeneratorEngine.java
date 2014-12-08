package plutoeditor.generator;

import java.io.File;
import java.io.FileWriter;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import plutoeditor.model.editor.Diagram;

public class MyGeneratorEngine {

	Diagram parent;

	public MyGeneratorEngine(Diagram model) {
		this.parent = model;
	}

	public void generate() {
		JFrame parentFrame = new JFrame();

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify a file to save");
		fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);

		int userSelection = fileChooser.showSaveDialog(parentFrame);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			try {
				File fileToSave = fileChooser.getSelectedFile();
				if (!fileToSave.exists()) {
					fileToSave.createNewFile();
				}
				FileWriter fw = new FileWriter(fileToSave);

				// Qui la logica che va a scrivere sul file
				String s = "ciao";
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
