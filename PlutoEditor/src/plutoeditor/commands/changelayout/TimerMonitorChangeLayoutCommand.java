package plutoeditor.commands.changelayout;

import plutoeditor.model.editor.TimerMonitor;

import org.eclipse.draw2d.geometry.Rectangle;

public class TimerMonitorChangeLayoutCommand extends AbstractLayoutCommand {

	private TimerMonitor model;
	private Rectangle layout;
	private Rectangle oldLayout;

	public void execute() {
		model.setLayout(layout);
	}

	public void setConstraint(Rectangle rect) {
		this.layout = rect;
	}

	public void setModel(Object model) {
		this.model = (TimerMonitor) model;
		this.oldLayout = ((TimerMonitor) model).getLayout();
	}
	
	public void undo() {
		this.model.setLayout(this.oldLayout);
	}

}
