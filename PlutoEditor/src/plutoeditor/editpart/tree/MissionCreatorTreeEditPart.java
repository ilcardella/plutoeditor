package plutoeditor.editpart.tree;

import java.beans.PropertyChangeEvent;
import java.util.List;

import org.eclipse.gef.EditPolicy;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

import plutoeditor.editpolicies.AppDeletePolicy;
import plutoeditor.editpolicies.AppRenamePolicy;
import plutoeditor.model.editor.MissionCreator;
import plutoeditor.model.editor.Node;

public class MissionCreatorTreeEditPart extends AppAbstractTreeEditPart {
	protected List<Node> getModelChildren() {
		return ((MissionCreator) getModel()).getChildrenNodes();
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.COMPONENT_ROLE, new AppDeletePolicy());
		installEditPolicy(EditPolicy.NODE_ROLE, new AppRenamePolicy());
	}

	public void refreshVisuals() {
		MissionCreator model = (MissionCreator) getModel();
		setWidgetText(model.getName());
		setWidgetImage(PlatformUI.getWorkbench().getSharedImages()
				.getImage(ISharedImages.IMG_DEF_VIEW));
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if (evt.getPropertyName().equals(Node.PROPERTY_ADD))
			refreshChildren();
		if (evt.getPropertyName().equals(Node.PROPERTY_REMOVE))
			refreshChildren();
		if (evt.getPropertyName().equals(Node.PROPERTY_RENAME)) refreshVisuals();
	}
}
