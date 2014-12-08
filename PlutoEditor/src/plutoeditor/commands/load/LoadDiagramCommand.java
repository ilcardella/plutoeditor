package plutoeditor.commands.load;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.eclipse.core.resources.IFile;
import org.eclipse.gef.commands.Command;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;

import plutoeditor.editor.MyEditorInput;
import plutoeditor.editor.MyGraphicalEditor;
import plutoeditor.model.editor.Diagram;

public class LoadDiagramCommand extends Command {

	Diagram model;

	@Override
	public void execute() {
		JFrame parentFrame = new JFrame();

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setDialogTitle("Specify source file");
		fileChooser.setDialogType(JFileChooser.OPEN_DIALOG);

		int userSelection = fileChooser.showOpenDialog(parentFrame);

		if (userSelection == JFileChooser.APPROVE_OPTION) {
			try {
				File fileToLoad = fileChooser.getSelectedFile();
				if (!fileToLoad.exists()) {
					return;
				}

				// logica di caricamento
				try {
					FileInputStream door = new FileInputStream(
							fileToLoad.getAbsolutePath());
					ObjectInputStream reader = new ObjectInputStream(door);
					Diagram diagram = new Diagram();
					diagram = (Diagram) reader.readObject();
					reader.close();

					// setto il diagramma come modello dell'editor
					((MyGraphicalEditor) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
							.getActivePage().getActiveEditor())
							.setLoadedModel(diagram);

				} catch (IOException e) {
					e.printStackTrace();
				}

				System.out
						.println("Load file: " + fileToLoad.getAbsolutePath());

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
