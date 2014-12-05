package plutoeditor.commands.changelayout;

import plutoeditor.model.editor.MissionModifier;

import org.eclipse.draw2d.geometry.Rectangle;

public class MissionModifierChangeLayoutCommand extends AbstractLayoutCommand {

	private MissionModifier model;
	private Rectangle layout;
	private Rectangle oldLayout;

	public void execute() {
		model.setLayout(layout);
	}

	public void setConstraint(Rectangle rect) {
		this.layout = rect;
	}

	public void setModel(Object model) {
		this.model = (MissionModifier) model;
		this.oldLayout = ((MissionModifier) model).getLayout();
	}
	
	public void undo() {
		this.model.setLayout(this.oldLayout);
	}

}
