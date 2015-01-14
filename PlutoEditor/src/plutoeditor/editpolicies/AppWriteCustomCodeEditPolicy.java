package plutoeditor.editpolicies;

import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.AbstractEditPolicy;

import plutoeditor.actions.WriteCustomCodeAction;
import plutoeditor.commands.customcode.WriteCustomCodeCommand;

public class AppWriteCustomCodeEditPolicy extends AbstractEditPolicy {

	@Override
	public Command getCommand(Request request) {
		if(request.getType().equals(WriteCustomCodeAction.WRITE_CUSTOM_CODE_ID)) {
            return createWriteCustomCodeCommand();
        }
        return super.getCommand(request);
	}

	private Command createWriteCustomCodeCommand() {
		WriteCustomCodeCommand command = new WriteCustomCodeCommand();
		command.setModel(getHost().getModel());
		return command;
	}
}
