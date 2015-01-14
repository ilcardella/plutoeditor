package plutoeditor.commands.customcode;

import org.eclipse.gef.commands.Command;
import plutoeditor.model.editor.MissionModifier;

public class WriteCustomCodeCommand extends Command {

	private MissionModifier model;
	private String code;

	public void execute() {
		this.model.setCustomCode(code);
	}

	public void setModel(Object model) {
		this.model = (MissionModifier) model;
	}
	
	public void setCustomCode(String string){
		this.code = string;
	}

}
