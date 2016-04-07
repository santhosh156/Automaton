package org.automaton.config.step.impl;

import java.sql.Connection;

public final class ConnectionFactory {

	private static final ConnectionFactory INSTANCE = new ConnectionFactory();
	
	public static ConnectionFactory getInstance() {
		return INSTANCE;
	}
	
	private ConnectionFactory(){
		
	}
	
	public Connection createConnection(String url, String userId, String password) {
		return null;
	}
}
