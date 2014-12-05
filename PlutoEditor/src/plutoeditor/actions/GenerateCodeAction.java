package plutoeditor.actions;

import java.util.HashMap;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.ui.actions.EditorPartAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.ui.IEditorPart;

import plutoeditor.commands.generation.GenerateCodeCommand;
import plutoeditor.model.editor.Diagram;

public class GenerateCodeAction extends EditorPartAction {

	public static final String GENERATE_CODE_ID = "generate_code";
	
	public GenerateCodeAction(IEditorPart editor) {
		super(editor);
		setText("Generate");
		setId(GENERATE_CODE_ID);
		setToolTipText("Generate code from diagram");
		setImageDescriptor(new ImageDescriptor() {

			@Override
			public ImageData getImageData() {
				ImageData img = new ImageData("icons/generate-icon.ico");
				return img;
			}
		});
	}

	@Override
	protected boolean calculateEnabled() {
		return true;
	}

	@Override
	public void run() {
		EditPartViewer viewer = (EditPartViewer) getEditorPart().getAdapter(
				EditPartViewer.class);
		EditPart target = viewer.getContents();

		CreateRequest createReq = new CreateRequest();
		HashMap<String, Diagram> dataMap = new HashMap<String, Diagram>();
		dataMap.put("diagram", (Diagram) target.getModel());
		createReq.setExtendedData(dataMap);

		GenerateCodeCommand command = new GenerateCodeCommand(createReq);
		if (command != null && command.canExecute()) {
			viewer.getEditDomain().getCommandStack().execute(command);
		}

	}

}
