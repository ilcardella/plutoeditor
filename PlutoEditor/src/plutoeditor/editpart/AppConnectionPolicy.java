package plutoeditor.editpart;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import plutoeditor.commands.create.ConnectionCreateCommand;
import plutoeditor.commands.create.ConnectionReconnectCommand;
import plutoeditor.model.editor.Connection;
import plutoeditor.model.editor.Node;

public class AppConnectionPolicy extends GraphicalNodeEditPolicy {

	@Override
	protected Command getConnectionCompleteCommand(
			CreateConnectionRequest request) {
		ConnectionCreateCommand cmd = (ConnectionCreateCommand) request
				.getStartCommand();
		Node targetNode = (Node) getHost().getModel();
		cmd.setTargetNode(targetNode);
		return cmd;
	}

	@Override
	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		ConnectionCreateCommand cmd = new ConnectionCreateCommand();
		Node sourceNode = (Node) getHost().getModel();
		cmd.setSourceNode(sourceNode);
		request.setStartCommand(cmd);
		return cmd;
	}

	@Override
	protected Command getReconnectSourceCommand(ReconnectRequest request) {
		Connection conn = (Connection) request.getConnectionEditPart()
				.getModel();
		Node sourceNode = (Node) getHost().getModel();
		ConnectionReconnectCommand cmd = new ConnectionReconnectCommand(conn);
		cmd.setNewSourceNode(sourceNode);
		return cmd;
	}

	@Override
	protected Command getReconnectTargetCommand(ReconnectRequest request) {
		Connection conn = (Connection) request.getConnectionEditPart()
				.getModel();
		Node targetNode = (Node) getHost().getModel();
		ConnectionReconnectCommand cmd = new ConnectionReconnectCommand(conn);
		cmd.setNewTargetNode(targetNode);
		return cmd;
	}

}
