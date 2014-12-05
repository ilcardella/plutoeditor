package plutoeditor.commands.generation;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreateRequest;

import plutoeditor.generator.MyGeneratorTemplate;
import plutoeditor.model.editor.Diagram;
import plutoeditor.model.editor.Node;

public class GenerateCodeCommand extends Command {

	Diagram model;

	public GenerateCodeCommand(CreateRequest createReq) {
		model = (Diagram) createReq.getExtendedData().get("diagram");
	}

	@Override
	public void execute() {
		MyGeneratorTemplate generator = new MyGeneratorTemplate(model);
		generator.generate();
		super.execute();
	}
}
