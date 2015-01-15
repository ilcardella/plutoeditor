package plutoeditor.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;

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
	private CustomCodeFrame frame;

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
		this.frame = new CustomCodeFrame(node.getCustomCode());
		setListenersForFrameButtons();
		frame.setVisible(true);
	}
	
	public void finishRun(String code){
		execute(createWriteCustomCodeCommand(code));
	}

	private void setListenersForFrameButtons() {
		frame.setSaveButtonListener(new SaveButtonListener());
		frame.setCancelButtonListener(new CancelButtonListener());
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
	
	class SaveButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String code = frame.getTextFromTextArea();
			finishRun(code);
			frame.dispose();
			frame.setVisible(false);
		}
	}
	
	class CancelButtonListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			frame.dispose();
			frame.setVisible(false);
		}
	}

}
