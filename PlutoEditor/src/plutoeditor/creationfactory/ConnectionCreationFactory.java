package plutoeditor.creationfactory;

import org.eclipse.gef.requests.CreationFactory;

import plutoeditor.model.editor.Connection;

public class ConnectionCreationFactory implements CreationFactory {

	Class<?> conn;
	
	public ConnectionCreationFactory(Class<?> c) {
		this.conn = c;
	}
	@Override
	public Object getNewObject() {
		if(conn == null){
			return null;
		}
		if(conn == Connection.class){
			Connection c = new Connection(null, null);
			return c;
		}
		return null;
		
	}

	@Override
	public Object getObjectType() {
		return conn;
	}

}
