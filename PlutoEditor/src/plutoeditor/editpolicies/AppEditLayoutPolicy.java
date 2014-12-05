package plutoeditor.editpolicies;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import plutoeditor.commands.changelayout.AbstractLayoutCommand;
import plutoeditor.commands.changelayout.ClockChangeLayoutCommand;
import plutoeditor.commands.changelayout.ConditionBlockChangeLayoutCommand;
import plutoeditor.commands.changelayout.DroneAllocatorChangeLayoutCommand;
import plutoeditor.commands.changelayout.MissionCreatorChangeLayoutCommand;
import plutoeditor.commands.changelayout.MissionModifierChangeLayoutCommand;
import plutoeditor.commands.changelayout.PriorirtyManagerChangeLayoutCommand;
import plutoeditor.commands.changelayout.TripLauncherChangeLayoutCommand;
import plutoeditor.commands.changelayout.TripMonitorChangeLayoutCommand;
import plutoeditor.commands.create.NodeCreateCommand;
import plutoeditor.editpart.ClockEditPart;
import plutoeditor.editpart.ConditionBlockEditPart;
import plutoeditor.editpart.DiagramEditPart;
import plutoeditor.editpart.DroneAllocatorEditPart;
import plutoeditor.editpart.MissionCreatorEditPart;
import plutoeditor.editpart.MissionModifierEditPart;
import plutoeditor.editpart.PriorityManagerEditPart;
import plutoeditor.editpart.TripLauncherEditPart;
import plutoeditor.editpart.TripMonitorEditPart;
import plutoeditor.figure.ClockFigure;

public class AppEditLayoutPolicy extends XYLayoutEditPolicy {

	public static final int NODE_FIGURE_DEFWIDTH = 120;
	public static final int NODE_FIGURE_DEFHEIGHT = 40;
	
	@Override
	protected Command createChangeConstraintCommand(EditPart child,
			Object constraint) {

		AbstractLayoutCommand command = null;

		if (child instanceof MissionCreatorEditPart) {
			command = new MissionCreatorChangeLayoutCommand();
		} else if (child instanceof DroneAllocatorEditPart) {
			command = new DroneAllocatorChangeLayoutCommand();
		} else if (child instanceof ClockEditPart) {
			command = new ClockChangeLayoutCommand();
		} else if (child instanceof ConditionBlockEditPart) {
			command = new ConditionBlockChangeLayoutCommand();
		} else if (child instanceof MissionModifierEditPart) {
			command = new MissionModifierChangeLayoutCommand();
		} else if (child instanceof PriorityManagerEditPart) {
			command = new PriorirtyManagerChangeLayoutCommand();
		} else if (child instanceof TripLauncherEditPart) {
			command = new TripLauncherChangeLayoutCommand();
		} else if (child instanceof TripMonitorEditPart) {
			command = new TripMonitorChangeLayoutCommand();
		}

		command.setModel(child.getModel());
		command.setConstraint((Rectangle) constraint);
		return command;
	}

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		if (request.getType() == REQ_CREATE
				&& getHost() instanceof DiagramEditPart) {
			NodeCreateCommand cmd = new NodeCreateCommand();
			cmd.setDiagram(getHost().getModel());
			cmd.setNode(request.getNewObject());
			Rectangle constraint = (Rectangle) getConstraintFor(request);
			constraint.x = (constraint.x < 0) ? 0 : constraint.x;
			constraint.y = (constraint.y < 0) ? 0 : constraint.y;
			constraint.width = (constraint.width <= 0) ? NODE_FIGURE_DEFWIDTH : constraint.width;
			constraint.height = (constraint.height <= 0) ? NODE_FIGURE_DEFHEIGHT
					: constraint.height;
			cmd.setLayout(constraint);
			return cmd;
		}
		return null;
	}

}
