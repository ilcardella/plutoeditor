package plutoeditor.commands.delete;

import org.eclipse.gef.commands.Command;

import plutoeditor.model.editor.Connection;


public class ConnectionDeleteCommand extends Command {

	private Connection conn;

	public void setLink(Object model) {
		this.conn = (Connection) model;
	}

	@Override
	public boolean canExecute() {
		if (conn == null)
			return false;
		return true;
	}

	@Override
	public void execute() {
		conn.disconnect();
	}

	@Override
	public boolean canUndo() {
		if (conn == null)
			return false;
		return true;
	}

	@Override
	public void undo() {
		conn.connect();
	}
}
