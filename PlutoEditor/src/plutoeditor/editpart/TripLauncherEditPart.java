package plutoeditor.editpart;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.List;

import plutoeditor.model.editor.Connection;
import plutoeditor.model.editor.Node;
import plutoeditor.model.editor.TripLauncher;

import org.eclipse.draw2d.ChopboxAnchor;
import org.eclipse.draw2d.ConnectionAnchor;
import org.eclipse.draw2d.IFigure;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gef.NodeEditPart;
import org.eclipse.gef.Request;

import plutoeditor.editpolicies.AppDeletePolicy;
import plutoeditor.editpolicies.AppEditLayoutPolicy;
import plutoeditor.editpolicies.AppRenamePolicy;
import plutoeditor.figure.TripLauncherFigure;

public class TripLauncherEditPart extends AppAbstractEditPart implements NodeEditPart {

	@Override
	protected IFigure createFigure() {
		IFigure figure = new TripLauncherFigure();
		return figure;
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.LAYOUT_ROLE, new AppEditLayoutPolicy());
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new AppDeletePolicy());
		installEditPolicy(EditPolicy.NODE_ROLE, new AppRenamePolicy());
		installEditPolicy(EditPolicy.GRAPHICAL_NODE_ROLE,
				new AppConnectionPolicy());
	}

	protected void refreshVisuals() {
		TripLauncherFigure figure = (TripLauncherFigure) getFigure();
		TripLauncher model = (TripLauncher) getModel();

		figure.setName(model.getName());
		figure.setLayout(model.getLayout());
	}

	public List<Node> getModelChildren() {
		return new ArrayList<Node>();
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(Node.PROPERTY_LAYOUT))
			refreshVisuals();
		if (evt.getPropertyName().equals(Node.PROPERTY_RENAME))
			refreshVisuals();
		if (evt.getPropertyName().equals(Node.SOURCE_CONNECTION))
			refreshSourceConnections();
		if (evt.getPropertyName().equals(Node.TARGET_CONNECTION))
			refreshTargetConnections();
	}

	public List<Connection> getModelSourceConnections() {
		return ((TripLauncher) getModel()).getSourceConnections();
	}

	public List<Connection> getModelTargetConnections() {
		return ((TripLauncher) getModel()).getTargetConnections();
	}
	
	@Override
    public ConnectionAnchor getSourceConnectionAnchor(
            ConnectionEditPart connection) {
       return new ChopboxAnchor(getFigure());
    }

    @Override
    public ConnectionAnchor getSourceConnectionAnchor(Request request) {
        return new ChopboxAnchor(getFigure());
    }

    @Override
    public ConnectionAnchor getTargetConnectionAnchor(
            ConnectionEditPart connection) {
        return new ChopboxAnchor(getFigure());
    }

    @Override
    public ConnectionAnchor getTargetConnectionAnchor(Request request) {
        return new ChopboxAnchor(getFigure());
    }
}
