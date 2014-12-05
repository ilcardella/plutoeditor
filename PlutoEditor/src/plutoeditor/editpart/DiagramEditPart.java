package plutoeditor.editpart;

import java.beans.PropertyChangeEvent;
import java.util.List;

import plutoeditor.model.editor.Node;
import plutoeditor.model.editor.Diagram;

import org.eclipse.draw2d.ConnectionLayer;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.ShortestPathConnectionRouter;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.LayerConstants;
import org.eclipse.swt.SWT;

import plutoeditor.editpolicies.AppDeletePolicy;
import plutoeditor.editpolicies.AppEditLayoutPolicy;
import plutoeditor.figure.DiagramFigure;

public class DiagramEditPart extends AppAbstractEditPart {

	@Override
	protected IFigure createFigure() {
		DiagramFigure figure = new DiagramFigure();
		figure.setName("Diagram");
		
		ConnectionLayer connLayer = (ConnectionLayer)getLayer(LayerConstants.CONNECTION_LAYER);
        connLayer.setAntialias(SWT.ON);
        connLayer.setConnectionRouter(new ShortestPathConnectionRouter(figure));
        
		return figure;
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new AppEditLayoutPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new AppDeletePolicy());
	}

	public List<Node> getModelChildren() {
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
