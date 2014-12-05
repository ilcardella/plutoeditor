package plutoeditor.commands.create;

import org.eclipse.gef.commands.Command;

import plutoeditor.model.editor.Connection;
import plutoeditor.model.editor.Node;

public class ConnectionReconnectCommand extends Command {
	private Connection conn;
	private Node oldSourceNode;
	private Node oldTargetNode;
	private Node newSourceNode;
	private Node newTargetNode;

	public ConnectionReconnectCommand(Connection conn) {
		if (conn == null) {
			throw new IllegalArgumentException();
		}
		this.conn = conn;
		this.oldSourceNode = conn.getSourceNode();
		this.oldTargetNode = conn.getTargetNode();
	}

	public boolean canExecute() {
		if (newSourceNode != null) {
			return checkSourceReconnection();
		} else if (newTargetNode != null) {
			return checkTargetReconnection();
		}
		return false;
	}

	private boolean checkSourceReconnection() {
		if (newSourceNode == null)
			return false;
		else if (newSourceNode.equals(oldTargetNode))
			return false;
		else if (!newSourceNode.getClass().equals(oldTargetNode.getClass()))
			return false;
		return true;
	}

	private boolean checkTargetReconnection() {
		if (newTargetNode == null)
			return false;
		else if (oldSourceNode.equals(newTargetNode))
			return false;
		else if (!oldSourceNode.getClass().equals(newTargetNode.getClass()))
			return false;
		return true;
	}

	public void setNewSourceNode(Node sourceNode) {
		if (sourceNode == null) {
			throw new IllegalArgumentException();
		}
		this.newSourceNode = sourceNode;
		this.newTargetNode = null;
	}

	public void setNewTargetNode(Node targetNode) {
		if (targetNode == null) {
			throw new IllegalArgumentException();
		}
		this.newSourceNode = null;
		this.newTargetNode = targetNode;
	}

	public void execute() {
		if (newSourceNode != null) {
			conn.reconnect(newSourceNode, oldTargetNode);
		} else if (newTargetNode != null) {
			conn.reconnect(oldSourceNode, newTargetNode);
		} else {
			throw new IllegalStateException("Should not happen");
		}
	}

	public void undo() {
		conn.reconnect(oldSourceNode, oldTargetNode);
	}
}
