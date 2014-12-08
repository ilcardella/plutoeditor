package plutoeditor.commands.save;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;

import plutoeditor.model.editor.Diagram;

public class SaveDiagramCommand extends Command {

	Diagram model;

	public SaveDiagramCommand(CreateRequest createReq) {
		model = (Diagram) createReq.getExtendedData().get("diagram");
	}
	
	@Override
	public void execute() {
		JFrame parentFrame = new JFrame();

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify destination file");
		fileChooser.setDialogType(JFileChooser.SAVE_DIALOG);

		int userSelection = fileChooser.showSaveDialog(parentFrame);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			try {
				File fileToSave = fileChooser.getSelectedFile();
				if (!fileToSave.exists()) {
					fileToSave.createNewFile();
				}

				// Qui la logica che va a scrivere sul file
				ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileToSave.getAbsolutePath()));
				out.writeObject(model);
				out.close();
				
				System.out.println("Save as file: "
						+ fileToSave.getAbsolutePath());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
