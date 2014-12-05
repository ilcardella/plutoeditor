package plutoeditor.commands.delete;

import plutoeditor.model.editor.Diagram;
import plutoeditor.model.editor.Node;

import org.eclipse.gef.commands.Command;

public class DeleteCommand extends Command {
	private Node model;
	private Node parentModel;

	public void execute() {
		this.parentModel.removeChild(model);
	}

	public void setModel(Object model) {
		this.model = (Node) model;
	}

	public void setParentModel(Object model) {
		if( model instanceof Node)
			parentModel = (Node) model;
		else if( model instanceof Diagram)
			parentModel = (Diagram) model;
	}

	public void undo() {
		this.parentModel.addChild(model);
	}
}
