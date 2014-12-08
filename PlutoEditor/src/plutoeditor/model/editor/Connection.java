package plutoeditor.model.editor;

import java.io.Serializable;

public class Connection implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected Node sourceNode;
	protected Node targetNode;

	public Connection(Node sourceNode, Node targetNode) {
		this.sourceNode = sourceNode;
		this.targetNode = targetNode;
	}

	public Node getSourceNode() {
		return sourceNode;
	}

	public Node getTargetNode() {
		return targetNode;
	}

	public void connect() {
		sourceNode.addConnections(this);
		targetNode.addConnections(this);
	}

	public void disconnect() {
		sourceNode.removeConnection(this);
		targetNode.removeConnection(this);
	}

	public void reconnect(Node sourceNode, Node targetNode) {
		if (sourceNode == null || targetNode == null
				|| sourceNode == targetNode) {
			throw new IllegalArgumentException();
		}
		disconnect();
		this.sourceNode = sourceNode;
		this.targetNode = targetNode;
		connect();
	}

}
