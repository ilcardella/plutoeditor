package plutoeditor.model.editor;

import plutoeditor.model.classes.Mission;


public class MissionModifier extends Node {
	
	private static final long serialVersionUID = 1L;
	// TODO put inside customCode, as default value, a string with
	// a template code of the final class
	private String customCode = "";

	@Override
	public Mission run(Mission m) {
		return null;
	}

	public String getCustomCode() {
		return customCode;
	}

	public void setCustomCode(String customCode) {
		this.customCode = customCode;
	}

}
