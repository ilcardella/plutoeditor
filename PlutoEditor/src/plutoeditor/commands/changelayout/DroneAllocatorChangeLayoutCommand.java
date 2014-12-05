package plutoeditor.commands.changelayout;

import plutoeditor.model.editor.DroneAllocator;

import org.eclipse.draw2d.geometry.Rectangle;

public class DroneAllocatorChangeLayoutCommand extends AbstractLayoutCommand {

	private DroneAllocator model;
	private Rectangle layout;
	private Rectangle oldLayout;

	public void execute() {
		model.setLayout(layout);
	}

	public void setConstraint(Rectangle rect) {
		this.layout = rect;
	}

	public void setModel(Object model) {
		this.model = (DroneAllocator) model;
		this.oldLayout = ((DroneAllocator) model).getLayout();
	}

	public void undo() {
		this.model.setLayout(this.oldLayout);
	}
}
