package plutoeditor.editpart.tree;

import java.beans.PropertyChangeEvent;
import java.util.List;

import plutoeditor.model.editor.Node;
import plutoeditor.model.editor.Diagram;

public class DiagramTreeEditPart extends AppAbstractTreeEditPart {

	protected List<Node> getModelChildren() {
		return ((Diagram) getModel()).getChildrenNodes();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(Node.PROPERTY_ADD))
			refreshChildren();
		if (evt.getPropertyName().equals(Node.PROPERTY_REMOVE))
			refreshChildren();
	}
}
