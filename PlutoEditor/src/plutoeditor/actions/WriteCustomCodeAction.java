package plutoeditor.actions;

import java.util.HashMap;
import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

import plutoeditor.commands.customcode.WriteCustomCodeCommand;
import plutoeditor.editpart.MissionModifierEditPart;
import plutoeditor.model.editor.MissionModifier;
import plutoeditor.model.editor.Node;
import plutoeditor.view.frame.CustomCodeFrame;

public class WriteCustomCodeAction extends SelectionAction {

	public static final String WRITE_CUSTOM_CODE_ID = "write_custom_code";

	public WriteCustomCodeAction(IWorkbenchPart part) {
		super(part);
		setLazyEnablementCalculation(false);
	}

	protected void init() {
		setText("Write Custom Code...");
		setId(WRITE_CUSTOM_CODE_ID);
		setToolTipText("Write your custom code");
		setEnabled(false);
	}

	@Override
	protected boolean calculateEnabled() {
		Command cmd = createWriteCustomCodeCommand("");
		if (cmd == null)
			return false;
		return true;

		// if (getSelectedObjects().isEmpty()) {
		// return false;
		// }
		// if (getSelectedObjects().size() > 1) {
		// return false;
		// }
		// Object obj = getSelectedObjects().get(0);
		// if (!(obj instanceof MissionModifierEditPart)) {
		// return false;
		// }
		// return true;
	}

	public Command createWriteCustomCodeCommand(String string) {
		Request req = new Request(WriteCustomCodeAction.WRITE_CUSTOM_CODE_ID);
		HashMap<String, String> reqData = new HashMap<String, String>();
		reqData.put("customCode", string);
		req.setExtendedData(reqData);
		EditPart object = (EditPart) getSelectedObjects().get(0);
		Command cmd = object.getCommand(req);
		return cmd;
	}

	public void run() {
		MissionModifier node = getSelectedNode();
		CustomCodeFrame frame = new CustomCodeFrame(node.getCustomCode());
		// TODO set attribute to the frame
		frame.setVisible(true);

		String code = "";// TODO get the code from the frame
		execute(createWriteCustomCodeCommand(code));

	}

	private MissionModifier getSelectedNode() {
		List objects = getSelectedObjects();
		if (objects.isEmpty())
			return null;
		if (!(objects.get(0) instanceof EditPart))
			return null;
		EditPart part = (EditPart) objects.get(0);
		return (MissionModifier) part.getModel();
	}

}
