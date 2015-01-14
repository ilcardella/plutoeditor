package plutoeditor.actions;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import plutoeditor.commands.customcode.WriteCustomCodeCommand;
import plutoeditor.editpart.MissionModifierEditPart;
import plutoeditor.model.editor.Node;

public class WriteCustomCodeAction extends SelectionAction {

	public static final String WRITE_CUSTOM_CODE_ID = "write_custom_code";
	Request request;

	public WriteCustomCodeAction(IWorkbenchPart part) {
		super(part);
		setText("Write Custom Code...");
		setId(WRITE_CUSTOM_CODE_ID);
		setToolTipText("Write your custom code");
		this.request = new Request(WRITE_CUSTOM_CODE_ID);
	}

	@Override
	protected boolean calculateEnabled() {
		if (getSelectedObjects().isEmpty()) {
			return false;
		}
		if (getSelectedObjects().size() > 1) {
			return false;
		}
		Object obj = getSelectedObjects().get(0);
		if (!(obj instanceof MissionModifierEditPart)) {
			return false;
		}
		return true;
	}

	public void run() {
		EditPart part = (EditPart) getSelectedObjects().get(0);
		part.getCommand(request);
		WriteCustomCodeCommand cmd = new WriteCustomCodeCommand((Node)part.getModel());
		execute(cmd);
	}

//	// Helper
//	private Node getSelectedNode() {
//		List objects = getSelectedObjects();
//		if (objects.isEmpty())
//			return null;
//		if (!(objects.get(0) instanceof EditPart))
//			return null;
//		EditPart part = (EditPart) objects.get(0);
//		return (Node) part.getModel();
//	}

}
