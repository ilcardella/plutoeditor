package plutoeditor.model.editor;

import plutoeditor.model.classes.Mission;


public class MissionModifier extends Node {
	
	private static final long serialVersionUID = 1L;
	// TODO put inside customCode, as default value, a string with
	// a template code of the final class
	private String customCode = "public Mission run(Mission m) {"+'\n'+"    // TODO Write here your custom code"+'\n'+""+'\n'+"    return m;"+'\n'+"}";
	private String templateCode = "package it.polimi.template.controller.block;"+'\n'+""+'\n'+"import java.util.Observable;"+'\n'+"import java.util.Observer;"+'\n'+""+'\n'+"import it.polimi.template.model.*;"+'\n'+""+'\n'+"public class MissionModifier extends Node implements Observer {"+'\n'+""+'\n'+"@Override"+'\n'+"<run>"+'\n'+""+'\n'+"@Override"+'\n'+"public void update(Observable o, Object arg) {"+'\n'+"Mission m = this.run((Mission) arg);"+'\n'+"setChanged();"+'\n'+"notifyObservers(m);"+'\n'+"}"+'\n'+""+'\n'+"}";

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

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

}
