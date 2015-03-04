package plutoeditor.commands.changelayout;

import org.eclipse.draw2d.geometry.Rectangle;

import plutoeditor.model.editor.GateFunnel;

public class GateFunnelChangeLayoutCommand extends AbstractLayoutCommand {

	private GateFunnel model;
	private Rectangle layout;
	private Rectangle oldLayout;

	public void execute() {
		model.setLayout(layout);
	}

	public void setConstraint(Rectangle rect) {
		this.layout = rect;
	}

	public void setModel(Object model) {
		this.model = (GateFunnel) model;
		this.oldLayout = ((GateFunnel) model).getLayout();
	}

	public void undo() {
		this.model.setLayout(this.oldLayout);
	}

}
