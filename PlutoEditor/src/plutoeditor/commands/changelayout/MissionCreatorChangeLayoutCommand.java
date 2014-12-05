package plutoeditor.commands.changelayout;

import plutoeditor.model.editor.MissionCreator;

import org.eclipse.draw2d.geometry.Rectangle;

public class MissionCreatorChangeLayoutCommand extends AbstractLayoutCommand {

	private MissionCreator model;
	private Rectangle layout;
	private Rectangle oldLayout;

	public void execute() {
		model.setLayout(layout);
	}

	public void setConstraint(Rectangle rect) {
		this.layout = rect;
	}

	public void setModel(Object model) {
		this.model = (MissionCreator) model;
		this.oldLayout = ((MissionCreator) model).getLayout();
	}
	
	public void undo() {
		this.model.setLayout(this.oldLayout);
	}

}
