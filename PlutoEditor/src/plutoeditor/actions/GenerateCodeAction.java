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
import plutoeditor.editpart.DiagramEditPart;
import plutoeditor.model.editor.Diagram;

public class GenerateCodeAction extends EditorPartAction {

	public static final String GENERATE_CODE_ID = "generate_code";
	
	public GenerateCodeAction(IEditorPart editor) {
		super(editor);
		setText("Generate Code");
		setId(GENERATE_CODE_ID);
		setToolTipText("Generate code from diagram");
//		setImageDescriptor(new ImageDescriptor() {
//
//			@Override
//			public ImageData getImageData() {
//				ImageData img = new ImageData("./icons/generate-icon.ico");
//				return img;
//			}
//		});
	}

	@Override
	protected boolean calculateEnabled() {
		return true;
	}

	@Override
	public void run() {

		CreateRequest createReq = new CreateRequest();
		HashMap<String, Diagram> dataMap = new HashMap<String, Diagram>();
		
		dataMap.put("diagram", (Diagram) getEditorPart().getAdapter(Diagram.class));
		
		createReq.setExtendedData(dataMap);

		GenerateCodeCommand command = new GenerateCodeCommand(createReq);
		execute(command);

	}

}
