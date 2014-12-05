package plutoeditor.commands.generation;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;

import plutoeditor.model.editor.Diagram;
import plutoeditor.model.editor.Node;

public class GenerateCodeCommand extends Command {

	Diagram model;

	public GenerateCodeCommand(CreateRequest createReq) {
		model = (Diagram) createReq.getExtendedData().get("diagram");
	}

	@Override
	public void execute() {
		// TODO Implementation
		if (model != null)
			System.out.println("Hello GEF!");
		
		super.execute();
	}
}
