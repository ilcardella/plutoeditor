package plutoeditor.editpart;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.editpolicies.ConnectionEndpointEditPolicy;
import org.eclipse.swt.SWT;

import plutoeditor.editpolicies.AppConnectionDeleteEditPolicy;

public class ConnectionEditPart extends AppAbstractConnectionEditPart {

	protected IFigure createFigure() {
		PolylineConnection connection = (PolylineConnection) super
				.createFigure();
		connection.setLineWidth(2);
		PolygonDecoration decoration = new PolygonDecoration();
		decoration.setTemplate(PolygonDecoration.TRIANGLE_TIP);
		connection.setTargetDecoration(decoration);
		connection.setLineStyle(SWT.LINE_SOLID);

		return connection;
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.CONNECTION_ROLE,
				new AppConnectionDeleteEditPolicy());
		installEditPolicy(EditPolicy.CONNECTION_ENDPOINTS_ROLE,
				new ConnectionEndpointEditPolicy());
	}
}
