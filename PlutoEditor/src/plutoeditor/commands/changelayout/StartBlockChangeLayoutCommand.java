package plutoeditor.commands.changelayout;

import org.eclipse.draw2d.geometry.Rectangle;

import plutoeditor.model.editor.StartBlock;

public class StartBlockChangeLayoutCommand extends AbstractLayoutCommand {

	private StartBlock model;
	private Rectangle layout;
	private Rectangle oldLayout;

	public void execute() {
		model.setLayout(layout);
	}

	public void setConstraint(Rectangle rect) {
		this.layout = rect;
	}

	public void setModel(Object model) {
		this.model = (StartBlock) model;
		this.oldLayout = ((StartBlock) model).getLayout();
	}

	public void undo() {
		this.model.setLayout(this.oldLayout);
	}

}
