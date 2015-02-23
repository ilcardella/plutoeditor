package plutoeditor.commands.changelayout;

import org.eclipse.draw2d.geometry.Rectangle;

import plutoeditor.model.editor.MissionEvaluator;

public class MissionEvaluatorChangeLayoutCommand extends AbstractLayoutCommand {

	private MissionEvaluator model;
	private Rectangle layout;
	private Rectangle oldLayout;

	public void execute() {
		model.setLayout(layout);
	}

	public void setConstraint(Rectangle rect) {
		this.layout = rect;
	}

	public void setModel(Object model) {
		this.model = (MissionEvaluator) model;
		this.oldLayout = ((MissionEvaluator) model).getLayout();
	}
	
	public void undo() {
		this.model.setLayout(this.oldLayout);
	}


}
