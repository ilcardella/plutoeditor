package plutoeditor.commands.customcode;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;

import plutoeditor.editpart.MissionModifierEditPart;
import plutoeditor.model.editor.Diagram;
import plutoeditor.model.editor.Node;

public class WriteCustomCodeCommand extends Command {

	private Node node;

	public WriteCustomCodeCommand(Node node2) {
		this.node = node2;
	}

	public WriteCustomCodeCommand() {
		// TODO Auto-generated constructor stub
	}

	public void execute() {
		// TODO Fare qualcosa
		System.out.println("Node "+node.getName());
	}

	public void setModel(Object model) {
		this.node = (Node) model;
	}

}
