package plutoeditor.actions;

import java.util.HashMap;

import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.ui.actions.EditorPartAction;
import org.eclipse.ui.IEditorPart;

import plutoeditor.commands.save.SaveDiagramCommand;
import plutoeditor.model.editor.Diagram;

public class SaveDiagramAction extends EditorPartAction {
	
	public static final String SAVE_DIAGRAM_ID = "save_diagram";

	public SaveDiagramAction(IEditorPart editor) {
		super(editor);
		setText("Save Diagram...");
		setId(SAVE_DIAGRAM_ID);
		setToolTipText("Save your diagram");
		// TODO Auto-generated constructor stub
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

		SaveDiagramCommand command = new SaveDiagramCommand(createReq);
		execute(command);
	}

}
