package plutoeditor.commands.changelayout;

import plutoeditor.model.editor.ConditionBlock;

import org.eclipse.draw2d.geometry.Rectangle;

public class ConditionBlockChangeLayoutCommand extends AbstractLayoutCommand {

	private ConditionBlock model;
	private Rectangle layout;
	private Rectangle oldLayout;

	public void execute() {
		model.setLayout(layout);
	}

	public void setConstraint(Rectangle rect) {
		this.layout = rect;
	}

	public void setModel(Object model) {
		this.model = (ConditionBlock) model;
		this.oldLayout = ((ConditionBlock) model).getLayout();
	}
	
	public void undo() {
		this.model.setLayout(this.oldLayout);
	}

}
