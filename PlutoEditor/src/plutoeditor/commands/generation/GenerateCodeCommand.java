package plutoeditor.commands.generation;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;

import plutoeditor.model.editor.Diagram;

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
		else
			System.out.println("Mannaggiaaddiocristoimpalato");
		super.execute();
	}
}
