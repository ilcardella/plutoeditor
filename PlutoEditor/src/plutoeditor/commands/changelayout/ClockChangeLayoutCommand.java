package plutoeditor.commands.changelayout;

import plutoeditor.model.editor.Clock;

import org.eclipse.draw2d.geometry.Rectangle;

public class ClockChangeLayoutCommand extends AbstractLayoutCommand {

	private Clock model;
	private Rectangle layout;
	private Rectangle oldLayout;

	public void execute() {
		model.setLayout(layout);
	}

	public void setConstraint(Rectangle rect) {
		this.layout = rect;
	}

	public void setModel(Object model) {
		this.model = (Clock) model;
		this.oldLayout = ((Clock) model).getLayout();
	}

	public void undo() {
		this.model.setLayout(this.oldLayout);
	}

}
