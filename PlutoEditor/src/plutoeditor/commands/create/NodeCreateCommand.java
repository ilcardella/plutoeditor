package plutoeditor.commands.create;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import plutoeditor.model.editor.*;

public class NodeCreateCommand extends Command {

	private Diagram diagram;
	private Node node;

	public NodeCreateCommand() {
		super();
		diagram = null;
		node = null;
	}

	public void setNode(Object c) {
		if (c instanceof Clock)
			this.node = (Clock) c;
		else if (c instanceof TimerMonitor)
			this.node = (TimerMonitor) c;
		else if (c instanceof DroneAllocator)
			this.node = (DroneAllocator) c;
		else if (c instanceof MissionCreator)
			this.node = (MissionCreator) c;
		else if (c instanceof MissionModifier)
			this.node = (MissionModifier) c;
		else if (c instanceof PriorityManager)
			this.node = (PriorityManager) c;
		else if (c instanceof TripLauncher)
			this.node = (TripLauncher) c;
		else if (c instanceof TripMonitor)
			this.node = (TripMonitor) c;
		else if (c instanceof MissionRepeater)
			this.node = (MissionRepeater) c;
		else if (c instanceof MissionEvaluator)
			this.node = (MissionEvaluator) c;
		else if (c instanceof GateFIFO)
			this.node = (GateFIFO) c;
		else if (c instanceof GateFunnel)
			this.node = (GateFunnel) c;
		else if (c instanceof StartBlock)
			this.node = (StartBlock) c;
		else if (c instanceof EndBlock)
			this.node = (EndBlock) c;
	}

	public void setDiagram(Object e) {
		if (e instanceof Diagram)
			this.diagram = (Diagram) e;
	}

	public void setLayout(Rectangle r) {
		if (node == null)
			return;
		node.setLayout(r);
	}

	@Override
	public boolean canExecute() {
		if (node == null || diagram == null)
			return false;
		return true;
	}

	@Override
	public void execute() {
		diagram.addChild(node);
	}

	@Override
	public boolean canUndo() {
		if (diagram == null || node == null)
			return false;
		return diagram.contains(node);
	}

	@Override
	public void undo() {
		diagram.removeChild(node);
	}
}
