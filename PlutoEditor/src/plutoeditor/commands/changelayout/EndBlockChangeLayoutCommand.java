package plutoeditor.commands.changelayout;

import org.eclipse.draw2d.geometry.Rectangle;

import plutoeditor.model.editor.EndBlock;

public class EndBlockChangeLayoutCommand extends AbstractLayoutCommand {

	private EndBlock model;
	private Rectangle layout;
	private Rectangle oldLayout;

	public void execute() {
		model.setLayout(layout);
	}

	public void setConstraint(Rectangle rect) {
		this.layout = rect;
	}

	public void setModel(Object model) {
		this.model = (EndBlock) model;
		this.oldLayout = ((EndBlock) model).getLayout();
	}

	public void undo() {
		this.model.setLayout(this.oldLayout);
	}

}
