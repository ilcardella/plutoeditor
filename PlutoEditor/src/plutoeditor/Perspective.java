package plutoeditor;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {

		layout.setEditorAreaVisible(true);
		layout.setFixed(true);
		//layout.addView(IPageLayout.ID_OUTLINE,
		//		IPageLayout.LEFT, 0.3f, layout.getEditorArea());
	}
}
