package plutoeditor.commands.changelayout;

import plutoeditor.model.editor.TripMonitor;

import org.eclipse.draw2d.geometry.Rectangle;

public class TripMonitorChangeLayoutCommand extends AbstractLayoutCommand {

	private TripMonitor model;
	private Rectangle layout;
	private Rectangle oldLayout;

	public void execute() {
		model.setLayout(layout);
	}

	public void setConstraint(Rectangle rect) {
		this.layout = rect;
	}

	public void setModel(Object model) {
		this.model = (TripMonitor) model;
		this.oldLayout = ((TripMonitor) model).getLayout();
	}
	
	public void undo() {
		this.model.setLayout(this.oldLayout);
	}

}
