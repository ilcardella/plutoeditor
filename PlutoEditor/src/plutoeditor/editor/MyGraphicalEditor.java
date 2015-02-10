package plutoeditor.editor;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.Viewport;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.draw2d.parts.ScrollableThumbnail;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.DefaultEditDomain;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.KeyHandler;
import org.eclipse.gef.KeyStroke;
import org.eclipse.gef.LayerConstants;
import org.eclipse.gef.MouseWheelHandler;
import org.eclipse.gef.MouseWheelZoomHandler;
import org.eclipse.gef.dnd.TemplateTransferDragSourceListener;
import org.eclipse.gef.editparts.ScalableRootEditPart;
import org.eclipse.gef.editparts.ZoomManager;
import org.eclipse.gef.palette.CombinedTemplateCreationEntry;
import org.eclipse.gef.palette.ConnectionCreationToolEntry;
import org.eclipse.gef.palette.MarqueeToolEntry;
import org.eclipse.gef.palette.PaletteDrawer;
import org.eclipse.gef.palette.PaletteRoot;
import org.eclipse.gef.palette.PaletteSeparator;
import org.eclipse.gef.palette.SelectionToolEntry;
import org.eclipse.gef.requests.CreateRequest;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.ZoomInAction;
import org.eclipse.gef.ui.actions.ZoomOutAction;
import org.eclipse.gef.ui.parts.ContentOutlinePage;
import org.eclipse.gef.ui.parts.GraphicalEditorWithPalette;
import org.eclipse.gef.ui.parts.TreeViewer;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.IPageSite;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;

import plutoeditor.actions.GenerateCodeAction;
import plutoeditor.actions.LoadDiagramAction;
import plutoeditor.actions.RenameAction;
import plutoeditor.actions.SaveDiagramAction;
import plutoeditor.actions.WriteCustomCodeAction;
import plutoeditor.commands.save.SaveDiagramCommand;
import plutoeditor.contextmenu.AppContextMenuProvider;
import plutoeditor.creationfactory.NodeCreationFactory;
import plutoeditor.editpart.AppEditPartFactory;
import plutoeditor.editpart.tree.AppTreeEditPartFactory;
import plutoeditor.model.editor.ActionEvaluator;
import plutoeditor.model.editor.Clock;
import plutoeditor.model.editor.MissionRepeater;
import plutoeditor.model.editor.TimerMonitor;
import plutoeditor.model.editor.Connection;
import plutoeditor.model.editor.Diagram;
import plutoeditor.model.editor.DroneAllocator;
import plutoeditor.model.editor.MissionCreator;
import plutoeditor.model.editor.MissionModifier;
import plutoeditor.model.editor.PriorityManager;
import plutoeditor.model.editor.TripLauncher;
import plutoeditor.model.editor.TripMonitor;
import plutoeditor.creationfactory.ConnectionCreationFactory;

public class MyGraphicalEditor extends GraphicalEditorWithPalette {

	public static final String ID = "plutoeditor.mygraphicaleditor";
	private Diagram model;
	private KeyHandler keyHandler;

	public MyGraphicalEditor() {
		setEditDomain(new DefaultEditDomain(this));
	}

	public void setLoadedModel(Diagram newModel) {
		model = newModel;
		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setContents(model);
	}

	@Override
	protected PaletteRoot getPaletteRoot() {

		PaletteRoot root = new PaletteRoot();

		// PaletteGroup manipGroup = new PaletteGroup("Manipulation");
		PaletteDrawer manipGroup = new PaletteDrawer("Blocks Elements");
		root.add(manipGroup);
		SelectionToolEntry selectionToolEntry = new SelectionToolEntry();
		manipGroup.add(selectionToolEntry);
		manipGroup.add(new MarqueeToolEntry());

		PaletteSeparator sep2 = new PaletteSeparator();
		root.add(sep2);

		// PaletteGroup instGroup = new PaletteGroup("Creation");
		PaletteDrawer instGroup = new PaletteDrawer("Blocks Elements");
		root.add(instGroup);
		
		instGroup.add(new CombinedTemplateCreationEntry("Action Evaluator",
				"Create an action evaluator block", ActionEvaluator.class,
				new NodeCreationFactory(ActionEvaluator.class), null, null));
		
		instGroup.add(new CombinedTemplateCreationEntry("Clock",
				"Create a clock block", Clock.class, new NodeCreationFactory(
						Clock.class), null, null));
		
		instGroup.add(new CombinedTemplateCreationEntry("Drone Allocator",
				"Create a drone allocator block", DroneAllocator.class,
				new NodeCreationFactory(DroneAllocator.class), null, null));

		instGroup.add(new CombinedTemplateCreationEntry("Mission Creator",
				"Create a mission creator block", MissionCreator.class,
				new NodeCreationFactory(MissionCreator.class), null, null));
		
		instGroup.add(new CombinedTemplateCreationEntry("Mission Modifier",
				"Create a mission modifier block", MissionModifier.class,
				new NodeCreationFactory(MissionModifier.class), null, null));
		
		instGroup.add(new CombinedTemplateCreationEntry("Mission Repeater",
				"Create a mission repeater block", MissionRepeater.class,
				new NodeCreationFactory(MissionRepeater.class), null, null));
		
		instGroup.add(new CombinedTemplateCreationEntry("Priority Manager",
				"Create a priority manager block", PriorityManager.class,
				new NodeCreationFactory(PriorityManager.class), null, null));
		
		instGroup.add(new CombinedTemplateCreationEntry("Trip Launcher",
				"Create a trip launcher block", TripLauncher.class,
				new NodeCreationFactory(TripLauncher.class), null, null));
		
		instGroup.add(new CombinedTemplateCreationEntry("Timer Monitor",
				"Create a condition block", TimerMonitor.class,
				new NodeCreationFactory(TimerMonitor.class), null, null));

		instGroup.add(new CombinedTemplateCreationEntry("Trip Monitor",
				"Create a trip monitor block", TripMonitor.class,
				new NodeCreationFactory(TripMonitor.class), null, null));

		PaletteDrawer connectionElements = new PaletteDrawer(
				"Connecting Elements");
		root.add(connectionElements);
		connectionElements.add(new ConnectionCreationToolEntry("Connection",
				"Create Connections", new ConnectionCreationFactory(
						Connection.class), null, null));

		root.setDefaultEntry(selectionToolEntry);
		return root;
	}

	protected void configureGraphicalViewer() {

		double[] zoomLevels;
		ArrayList<String> zoomContributions;

		super.configureGraphicalViewer();

		GraphicalViewer viewer = getGraphicalViewer();
		viewer.setEditPartFactory(new AppEditPartFactory());

		ScalableRootEditPart rootEditPart = new ScalableRootEditPart();
		viewer.setRootEditPart(rootEditPart);
		ZoomManager manager = rootEditPart.getZoomManager();
		getActionRegistry().registerAction(new ZoomInAction(manager));
		getActionRegistry().registerAction(new ZoomOutAction(manager));
		zoomLevels = new double[] { 0.25, 0.5, 0.75, 1.0, 1.5, 2.0, 2.5, 3.0,
				4.0, 5.0, 10.0, 20.0 };
		manager.setZoomLevels(zoomLevels);
		zoomContributions = new ArrayList<String>();
		zoomContributions.add(ZoomManager.FIT_ALL);
		zoomContributions.add(ZoomManager.FIT_HEIGHT);
		zoomContributions.add(ZoomManager.FIT_WIDTH);
		manager.setZoomLevelContributions(zoomContributions);

		KeyHandler keyHandler = new KeyHandler();

		keyHandler.put(KeyStroke.getPressed(SWT.DEL, 127, 0),
				getActionRegistry().getAction(ActionFactory.DELETE.getId()));
		keyHandler.put(KeyStroke.getPressed('+', SWT.KEYPAD_ADD, 0),
				getActionRegistry().getAction(GEFActionConstants.ZOOM_IN));

		keyHandler.put(KeyStroke.getPressed('-', SWT.KEYPAD_SUBTRACT, 0),
				getActionRegistry().getAction(GEFActionConstants.ZOOM_OUT));
		viewer.setProperty(MouseWheelHandler.KeyGenerator.getKey(SWT.NONE),
				MouseWheelZoomHandler.SINGLETON);

		viewer.setKeyHandler(keyHandler);

		ContextMenuProvider provider = new AppContextMenuProvider(viewer,
				getActionRegistry());
		viewer.setContextMenu(provider);

	}

	@Override
	protected void initializeGraphicalViewer() {

		GraphicalViewer viewer = getGraphicalViewer();
		//model = createDefaultModel();
		model = new Diagram();
		viewer.setContents(model);
		viewer.addDropTargetListener(new MyTemplateTransferDropTargetListener(
				viewer));
	}
	
	private Diagram createDefaultModel(){
		Diagram model = new Diagram();
		
		MissionCreator mc = new MissionCreator();
		mc.setName("Mission Creator");
		mc.setLayout(new Rectangle(25, 40, 120, 40));
		
		DroneAllocator da = new DroneAllocator();
		da.setName("Drone Allocator");
		da.setLayout(new Rectangle(220, 40, 120, 40));
		
		TripLauncher tl = new TripLauncher();
		tl.setName("Trip Launcher");
		tl.setLayout(new Rectangle(415, 40, 120, 40));
		
		TripMonitor tm = new TripMonitor();
		tm.setName("Trip Monitor");
		tm.setLayout(new Rectangle(415, 155, 120, 40));
		
		Connection conn = new Connection(mc, da);
		mc.getSourceConnections().add(conn);
		da.getTargetConnections().add(conn);
		
		conn = new Connection(da, tl);
		da.getSourceConnections().add(conn);
		tl.getTargetConnections().add(conn);
		
		conn = new Connection(tl, tm);
		tl.getSourceConnections().add(conn);
		tm.getTargetConnections().add(conn);
		
		conn = new Connection(tm, da);
		tm.getSourceConnections().add(conn);
		da.getTargetConnections().add(conn);
		
		model.getChildrenNodes().add(mc);
		model.getChildrenNodes().add(da);
		model.getChildrenNodes().add(tl);
		model.getChildrenNodes().add(tm);
		
		return model;
	}

	@Override
	protected void initializePaletteViewer() {
		super.initializePaletteViewer();
		getPaletteViewer().addDragSourceListener(
				new TemplateTransferDragSourceListener(getPaletteViewer()));
	}

	public Object getAdapter(Class type) {
		if (type == ZoomManager.class) {
			return ((ScalableRootEditPart) getGraphicalViewer()
					.getRootEditPart()).getZoomManager();
		} else if (type == IContentOutlinePage.class) {
			return new OutlinePage();
		} else if (type == Diagram.class) {
			return model;
		}
		return super.getAdapter(type);
	}

	@Override
	public void createActions() {
		super.createActions();
		ActionRegistry registry = getActionRegistry();

		IAction action = new RenameAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());

		action = new GenerateCodeAction(this);
		registry.registerAction(action);

		action = new SaveDiagramAction(this);
		registry.registerAction(action);

		action = new LoadDiagramAction(this);
		registry.registerAction(action);
		
		action = new WriteCustomCodeAction(this);
		registry.registerAction(action);
		getSelectionActions().add(action.getId());
	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		CreateRequest createReq = new CreateRequest();
		HashMap<String, Diagram> dataMap = new HashMap<String, Diagram>();

		dataMap.put("diagram", model);

		createReq.setExtendedData(dataMap);
		SaveDiagramCommand cmd = new SaveDiagramCommand(createReq);
		cmd.execute();
	}

	@Override
	public void doSaveAs() {
		return;
	}

	// Nested class for the outline view
	protected class OutlinePage extends ContentOutlinePage {
		private SashForm sash;
		private ScrollableThumbnail thumbnail;
		private DisposeListener disposeListener;

		public OutlinePage() {
			super(new TreeViewer());
		}

		@Override
		public void createControl(Composite parent) {
			sash = new SashForm(parent, SWT.VERTICAL);
			getViewer().createControl(sash);
			getViewer().setEditDomain(getEditDomain());
			getViewer().setEditPartFactory(new AppTreeEditPartFactory());
			getViewer().setContents(model);
			getSelectionSynchronizer().addViewer(getViewer());

			Canvas canvas = new Canvas(sash, SWT.BORDER);
			LightweightSystem lws = new LightweightSystem(canvas);
			thumbnail = new ScrollableThumbnail(
					(Viewport) ((ScalableRootEditPart) getGraphicalViewer()
							.getRootEditPart()).getFigure());
			thumbnail.setSource(((ScalableRootEditPart) getGraphicalViewer()
					.getRootEditPart())
					.getLayer(LayerConstants.PRINTABLE_LAYERS));
			lws.setContents(thumbnail);
			disposeListener = new DisposeListener() {
				@Override
				public void widgetDisposed(DisposeEvent e) {
					if (thumbnail != null) {
						thumbnail.deactivate();
						thumbnail = null;
					}
				}
			};
			getGraphicalViewer().getControl().addDisposeListener(
					disposeListener);
		}

		@Override
		public void init(IPageSite pageSite) {
			super.init(pageSite);

			IActionBars bars = getSite().getActionBars();
			bars.setGlobalActionHandler(ActionFactory.UNDO.getId(),
					getActionRegistry().getAction(ActionFactory.UNDO.getId()));
			bars.setGlobalActionHandler(ActionFactory.REDO.getId(),
					getActionRegistry().getAction(ActionFactory.REDO.getId()));
			bars.setGlobalActionHandler(ActionFactory.DELETE.getId(),
					getActionRegistry().getAction(ActionFactory.DELETE.getId()));
			bars.updateActionBars();

			getViewer().setKeyHandler(keyHandler);

			ContextMenuProvider provider = new AppContextMenuProvider(
					getViewer(), getActionRegistry());
			getViewer().setContextMenu(provider);
		}

		@Override
		public Control getControl() {
			return sash;
		}

		@Override
		public void dispose() {
			getSelectionSynchronizer().removeViewer(getViewer());
			if (getGraphicalViewer().getControl() != null
					&& !getGraphicalViewer().getControl().isDisposed())
				getGraphicalViewer().getControl().removeDisposeListener(
						disposeListener);
			super.dispose();
		}
	} // End nested class

}
