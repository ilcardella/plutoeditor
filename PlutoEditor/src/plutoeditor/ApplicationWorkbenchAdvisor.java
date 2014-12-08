package plutoeditor;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import plutoeditor.editor.MyEditorInput;
import plutoeditor.editor.MyGraphicalEditor;

public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

	private static final String PERSPECTIVE_ID = "PlutoEditor.perspective"; //$NON-NLS-1$

	public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(
			IWorkbenchWindowConfigurer configurer) {
		return new ApplicationWorkbenchWindowAdvisor(configurer);
	}

	public String getInitialWindowPerspectiveId() {
		return PERSPECTIVE_ID;
	}

	@Override
	public void postStartup() {

		PlatformUI.getWorkbench().getDisplay().asyncExec(new Runnable() {
			public void run() {

				try {
					IWorkbench bench = PlatformUI.getWorkbench();
					IWorkbenchWindow bw = bench.getActiveWorkbenchWindow();
					IWorkbenchPage page = bw.getActivePage();

					page.openEditor(new MyEditorInput("PlutoEditor"), MyGraphicalEditor.ID, false);
					page.showView(IPageLayout.ID_OUTLINE);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}
}
