package plutoeditor.actions;

import org.eclipse.gef.ui.actions.EditorPartAction;
import org.eclipse.ui.IEditorPart;

import plutoeditor.commands.load.LoadDiagramCommand;

public class LoadDiagramAction extends EditorPartAction {
	
	public static final String LOAD_DIAGRAM_ID = "load_diagram";

	public LoadDiagramAction(IEditorPart editor) {
		super(editor);
		setText("Load Diagram...");
		setId(LOAD_DIAGRAM_ID);
		setToolTipText("Load a diagram");
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean calculateEnabled() {
		return true;
	}

	@Override
	public void run() {
		LoadDiagramCommand command = new LoadDiagramCommand();
		execute(command);
	}
}
