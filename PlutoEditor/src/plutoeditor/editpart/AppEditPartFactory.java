package plutoeditor.editpart;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import plutoeditor.model.editor.*;

public class AppEditPartFactory implements EditPartFactory {

	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		AbstractGraphicalEditPart part = null;

		if (model instanceof MissionCreator) {
			part = new MissionCreatorEditPart();
		} else if (model instanceof Diagram) {
			part = new DiagramEditPart();
		} else if (model instanceof Clock) {
			part = new ClockEditPart();
		} else if (model instanceof TimerMonitor) {
			part = new TimerMonitorEditPart();
		} else if (model instanceof DroneAllocator) {
			part = new DroneAllocatorEditPart();
		} else if (model instanceof MissionModifier) {
			part = new MissionModifierEditPart();
		} else if (model instanceof PriorityManager) {
			part = new PriorityManagerEditPart();
		} else if (model instanceof TripLauncher) {
			part = new TripLauncherEditPart();
		} else if (model instanceof TripMonitor) {
			part = new TripMonitorEditPart();
		} else if (model instanceof Connection) {
			part = new ConnectionEditPart();
		} else if (model instanceof MissionEvaluator) {
			part = new MissionEvaluatorEditPart();
		} else if (model instanceof MissionRepeater) {
			part = new MissionRepeaterEditPart();
		} else if (model instanceof GateFIFO) {
			part = new GateFIFOEditPart();
		} else if (model instanceof GateFunnel) {
			part = new GateFunnelEditPart();
		} else if (model instanceof StartBlock) {
			part = new StartBlockEditPart();
		} else if (model instanceof EndBlock) {
			part = new EndBlockEditPart();
		}

		part.setModel(model);
		return part;
	}

}
