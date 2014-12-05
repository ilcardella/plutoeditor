package plutoeditor.commands.changelayout;

import plutoeditor.model.editor.TripLauncher;

import org.eclipse.draw2d.geometry.Rectangle;

public class TripLauncherChangeLayoutCommand extends AbstractLayoutCommand {

	private TripLauncher model;
	private Rectangle layout;
	private Rectangle oldLayout;

	public void execute() {
		model.setLayout(layout);
	}

	public void setConstraint(Rectangle rect) {
		this.layout = rect;
	}

	public void setModel(Object model) {
		this.model = (TripLauncher) model;
		this.oldLayout = ((TripLauncher) model).getLayout();
	}
	
	public void undo() {
		this.model.setLayout(this.oldLayout);
	}

}
