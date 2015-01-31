package plutoeditor.editpart.tree;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.eclipse.gef.editparts.AbstractTreeEditPart;

import plutoeditor.model.editor.Node;

public class AppAbstractTreeEditPart extends AbstractTreeEditPart implements
		PropertyChangeListener {

	public void activate() {
		super.activate();
		((Node) getModel()).addPropertyChangeListener(this);
	}

	public void deactivate() {
		((Node) getModel()).removePropertyChangeListener(this);
		super.deactivate();
	}

	@Override
	public void propertyChange(PropertyChangeEvent arg0) {}

}
