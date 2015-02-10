package plutoeditor.commands.changelayout;

import org.eclipse.draw2d.geometry.Rectangle;

import plutoeditor.model.editor.MissionRepeater;

public class MissionRepeaterChangeLayoutCommand extends AbstractLayoutCommand {

	private MissionRepeater model;
	private Rectangle layout;
	private Rectangle oldLayout;

	public void execute() {
		model.setLayout(layout);
	}

	public void setConstraint(Rectangle rect) {
		this.layout = rect;
	}

	public void setModel(Object model) {
		this.model = (MissionRepeater) model;
		this.oldLayout = ((MissionRepeater) model).getLayout();
	}
	
	public void undo() {
		this.model.setLayout(this.oldLayout);
	}


}
