package plutoeditor.creationfactory;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.requests.CreationFactory;

import plutoeditor.model.editor.ActionEvaluator;
import plutoeditor.model.editor.Clock;
import plutoeditor.model.editor.GateFIFO;
import plutoeditor.model.editor.MissionRepeater;
import plutoeditor.model.editor.TimerMonitor;
import plutoeditor.model.editor.DroneAllocator;
import plutoeditor.model.editor.MissionCreator;
import plutoeditor.model.editor.MissionModifier;
import plutoeditor.model.editor.PriorityManager;
import plutoeditor.model.editor.TripLauncher;
import plutoeditor.model.editor.TripMonitor;

public class NodeCreationFactory implements CreationFactory {

	private Class<?> template;

	public NodeCreationFactory(Class<?> t) {
		this.template = t;
	}

	@Override
	public Object getNewObject() {
		if (template == null)
			return null;
		if (template == Clock.class) {
			Clock clk = new Clock();
			clk.setName("Clock");
			clk.setLayout(new Rectangle(25, 40, 120, 40));
			return clk;
		}
		else if (template == TimerMonitor.class){
			TimerMonitor cdb = new TimerMonitor();
			cdb.setName("Timer Monitor");
			cdb.setLayout(new Rectangle(25, 40, 120, 40));
			return cdb;
		}
		else if (template == DroneAllocator.class){
			DroneAllocator da = new DroneAllocator();
			da.setName("Drone Allocator");
			da.setLayout(new Rectangle(25, 40, 120, 40));
			return da;
		}
		else if (template == MissionCreator.class){
			MissionCreator mc = new MissionCreator();
			mc.setName("Mission Creator");
			mc.setLayout(new Rectangle(25, 40, 120, 40));
			return mc;
		}
		else if (template == MissionModifier.class){
			MissionModifier mm = new MissionModifier();
			mm.setName("Mission Modifier");
			mm.setLayout(new Rectangle(25, 40, 120, 40));
			return mm;
		}
		else if (template == PriorityManager.class){
			PriorityManager pm = new PriorityManager();
			pm.setName("Priority Manager");
			pm.setLayout(new Rectangle(25, 40, 120, 40));
			return pm;
		}
		else if (template == TripLauncher.class){
			TripLauncher tl = new TripLauncher();
			tl.setName("Trip Launcher");
			tl.setLayout(new Rectangle(25, 40, 120, 40));
			return tl;
		}
		else if (template == TripMonitor.class){
			TripMonitor tm = new TripMonitor();
			tm.setName("Trip Monitor");
			tm.setLayout(new Rectangle(25, 40, 120, 40));
			return tm;
		}
		else if (template == MissionRepeater.class){
			MissionRepeater tm = new MissionRepeater();
			tm.setName("Mission Repeater");
			tm.setLayout(new Rectangle(25, 40, 120, 40));
			return tm;
		}
		else if (template == ActionEvaluator.class){
			ActionEvaluator tm = new ActionEvaluator();
			tm.setName("Action Evaluator");
			tm.setLayout(new Rectangle(25, 40, 120, 40));
			return tm;
		}
		else if (template == GateFIFO.class){
			GateFIFO tm = new GateFIFO();
			tm.setName("Gate FIFO");
			tm.setLayout(new Rectangle(25, 40, 120, 40));
			return tm;
		}
		
		return null;
	}

	@Override
	public Object getObjectType() {
		return template;
	}

}
