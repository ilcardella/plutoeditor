package plutoeditor.editpart;

import java.beans.PropertyChangeListener;

import plutoeditor.model.editor.Node;

import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

public abstract class AppAbstractEditPart extends AbstractGraphicalEditPart implements
		PropertyChangeListener {

	public void activate() {
		super.activate();
		((Node) getModel()).addPropertyChangeListener(this);
	}

	public void deactivate() {
		super.deactivate();
		((Node) getModel()).removePropertyChangeListener(this);
	}

}
