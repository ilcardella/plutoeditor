package plutoeditor.commands.changelayout;

import org.eclipse.draw2d.geometry.Rectangle;

import plutoeditor.model.editor.ActionEvaluator;

public class ActionEvaluatorChangeLayoutCommand extends AbstractLayoutCommand {

	private ActionEvaluator model;
	private Rectangle layout;
	private Rectangle oldLayout;

	public void execute() {
		model.setLayout(layout);
	}

	public void setConstraint(Rectangle rect) {
		this.layout = rect;
	}

	public void setModel(Object model) {
		this.model = (ActionEvaluator) model;
		this.oldLayout = ((ActionEvaluator) model).getLayout();
	}
	
	public void undo() {
		this.model.setLayout(this.oldLayout);
	}


}
