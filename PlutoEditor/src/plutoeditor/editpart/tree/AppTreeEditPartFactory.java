package plutoeditor.editpart.tree;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartFactory;

import plutoeditor.model.editor.Clock;
import plutoeditor.model.editor.TimerMonitor;
import plutoeditor.model.editor.Diagram;
import plutoeditor.model.editor.DroneAllocator;
import plutoeditor.model.editor.MissionCreator;
import plutoeditor.model.editor.MissionModifier;
import plutoeditor.model.editor.PriorityManager;
import plutoeditor.model.editor.TripLauncher;
import plutoeditor.model.editor.TripMonitor;

public class AppTreeEditPartFactory implements EditPartFactory {

	@Override
	public EditPart createEditPart(EditPart context, Object model) {
		
		EditPart part = null;
		
		if (model instanceof MissionCreator) {
			part = new MissionCreatorTreeEditPart();
		}
		else if (model instanceof Diagram) {
			part = new DiagramTreeEditPart();
		}
		else if (model instanceof Clock) {
			part = new ClockTreeEditPart();
		}
		else if (model instanceof TimerMonitor) {
			part = new TimerMonitorTreeEditPart();
		}
		else if (model instanceof DroneAllocator) {
			part = new DroneAllocatorTreeEditPart();
		}
		else if (model instanceof MissionModifier) {
			part = new MissionModifierTreeEditPart();
		}
		else if (model instanceof PriorityManager) {
			part = new PriorityManagerTreeEditPart();
		}
		else if (model instanceof TripLauncher) {
			part = new TripLauncherTreeEditPart();
		}
		else if (model instanceof TripMonitor) {
			part = new TripMonitorTreeEditPart();
		}
		
		if (part != null)
			part.setModel(model);
		return part;
	}

}
