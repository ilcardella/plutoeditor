package plutoeditor.commands.changelayout;

import plutoeditor.model.editor.PriorityManager;

import org.eclipse.draw2d.geometry.Rectangle;

public class PriorirtyManagerChangeLayoutCommand extends AbstractLayoutCommand {

	private PriorityManager model;
	private Rectangle layout;
	private Rectangle oldLayout;

	public void execute() {
		model.setLayout(layout);
	}

	public void setConstraint(Rectangle rect) {
		this.layout = rect;
	}

	public void setModel(Object model) {
		this.model = (PriorityManager) model;
		this.oldLayout = ((PriorityManager) model).getLayout();
	}

	public void undo() {
		this.model.setLayout(this.oldLayout);
	}
}
