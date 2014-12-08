package plutoeditor.model.editor;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import plutoeditor.model.classes.Mission;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.draw2d.geometry.Rectangle;

public class Node implements IAdaptable, Serializable {

	private static final long serialVersionUID = 1L;
	
	public static final String PROPERTY_ADD = "NodeAddChild";
	public static final String PROPERTY_REMOVE = "NodeRemoveChild";
	public static final String PROPERTY_RENAME = "NodeRename";
	public static final String SOURCE_CONNECTION = "SourceConnectionAdded";
	public static final String TARGET_CONNECTION = "TargetConnectionAdded";
	public static final String PROPERTY_LAYOUT = "NodeLayout";

	protected Rectangle layout;
	protected PropertyChangeSupport listeners;
	
	protected List<Connection> sourceConnections;
	protected List<Connection> targetConnections;
	
	protected Node parent;
	protected String name = "Unknown";
	protected List<Node> childrenNodes;

	public Node() {
		this.name = "Unknown";
		this.layout = new Rectangle(10, 10, 100, 100);
		this.childrenNodes = new ArrayList<Node>();
		this.parent = null;
		this.listeners = new PropertyChangeSupport(this);
		this.sourceConnections = new ArrayList<Connection>();
		this.targetConnections = new ArrayList<Connection>();
	}

	public Mission run(Mission mission) {
		return mission;
	}

	public Rectangle getLayout() {
		return layout;
	}

	public void setLayout(Rectangle newLayout) {
		Rectangle oldLayout = this.layout;
		this.layout = newLayout;
		getListeners()
				.firePropertyChange(PROPERTY_LAYOUT, oldLayout, newLayout);
	}

	public void addPropertyChangeListener(PropertyChangeListener listener) {
		listeners.addPropertyChangeListener(listener);
	}

	public PropertyChangeSupport getListeners() {
		return listeners;
	}

	public void removePropertyChangeListener(PropertyChangeListener listener) {
		listeners.removePropertyChangeListener(listener);
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		String oldName = this.name;
		this.name = name;
		getListeners().firePropertyChange(PROPERTY_RENAME, oldName, this.name);
	}

	public List<Node> getChildrenNodes() {
		return childrenNodes;

	}

	public boolean removeChild(Node child) {
		boolean b = getChildrenNodes().remove(child);
		if (b)
			getListeners().firePropertyChange(PROPERTY_REMOVE, child, null);
		return b;
	}

	public boolean addChild(Node child) {
		boolean b = getChildrenNodes().add(child);
		if (b) {
			child.setParent(this);
			getListeners().firePropertyChange(PROPERTY_ADD, null, child);
		}
		return b;
	}

	public boolean contains(Node child) {
		return childrenNodes.contains(child);
	}

	@Override
	public Object getAdapter(Class adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean addConnections(Connection conn) {
		if (conn.getSourceNode() == this) {
			if (!sourceConnections.contains(conn)) {
				if (sourceConnections.add(conn)) {
					getListeners().firePropertyChange(SOURCE_CONNECTION, null,
							conn);
					return true;
				}
				return false;
			}
		} else if (conn.getTargetNode() == this) {
			if (!targetConnections.contains(conn)) {
				if (targetConnections.add(conn)) {
					getListeners().firePropertyChange(TARGET_CONNECTION, null,
							conn);
					return true;
				}
				return false;
			}
		}
		return false;

	}

	public boolean removeConnection(Connection conn) {
		if (conn.getSourceNode() == this) {
			if (sourceConnections.contains(conn)) {
				if (sourceConnections.remove(conn)) {
					getListeners().firePropertyChange(SOURCE_CONNECTION, null,
							conn);
					return true;
				}
				return false;
			}
		} else if (conn.getTargetNode() == this) {
			if (targetConnections.contains(conn)) {
				if (targetConnections.remove(conn)) {
					getListeners().firePropertyChange(TARGET_CONNECTION, null,
							conn);
					return true;
				}
				return false;
			}
		}
		return false;

	}
	
	public List<Connection> getSourceConnections() {
		return sourceConnections;
	}

	public List<Connection> getTargetConnections() {
		return targetConnections;
	}
	
	
}
