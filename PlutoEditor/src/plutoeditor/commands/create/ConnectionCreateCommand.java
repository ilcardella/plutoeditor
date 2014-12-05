package plutoeditor.commands.create;

import org.eclipse.gef.commands.Command;

import plutoeditor.model.editor.Connection;
import plutoeditor.model.editor.Node;

public class ConnectionCreateCommand extends Command {

	private Node sourceNode, targetNode;
	private Connection conn;

	public void setSourceNode(Node sourceNode) {
		this.sourceNode = sourceNode;
	}

	public void setTargetNode(Node targetNode) {
		this.targetNode = targetNode;
	}

	@Override
	public boolean canExecute() {
		if (sourceNode == null || targetNode == null)
			return false;
		else if (sourceNode.equals(targetNode))
			return false;
		return true;
	}

	@Override
	public void execute() {
		conn = new Connection(sourceNode, targetNode);
		conn.connect();
	}

	@Override
	public boolean canUndo() {
		if (sourceNode == null || targetNode == null || conn == null)
			return false;
		return true;
	}

	@Override
	public void undo() {
		conn.disconnect();
	}

}
