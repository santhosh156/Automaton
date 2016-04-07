package org.automaton.config.step.impl;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.automaton.config.step.Step;

public class DatabaseStep extends Step {
	
	private String id;
	
	private String name;
	
	private String url;
	
	private String userId;
	
	private String password;
	
	private String query;
	
	private List<String> outputParams;	
	
	private List<String> parameterInput;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
	public List<String> getOutputParams() {
		return outputParams;
	}

	public void setOutputParams(List<String> outputParams) {
		this.outputParams = outputParams;
	}
	
	public Map<String,List<String>> execute(Map<String,Map<String,List<String>>> globalResponse) {
		Connection conn = null;
		Map<String,List<String>> output = new HashMap<String,List<String>>();
		try {
			conn = ConnectionFactory.getInstance().createConnection(url, userId, password);
			PreparedStatement ps = conn.prepareStatement(query);
			Map<String,List<String>> input = prepareInputs(globalResponse);
			int size =0;
			boolean iterate = true;
			do {
			for(String param : inputParams.keySet()) {
				if(input.get(param).size() > size)
					ps.setString(parameterInput.indexOf(param), input.get(param).get(size));
				else
					iterate = false;
			}
			ResultSet set = ps.executeQuery();
			fillMap(set,output);
			size++;
			} while(iterate);
		} catch(Exception e) {
			throw new RuntimeException(e);
		} finally {
			if(conn!= null) {
				try {
					conn.close();
				} catch(Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
		
		return null;
	}
	
	private void fillMap(ResultSet set, Map<String, List<String>> output) throws SQLException {
		ResultSetMetaData metaData = set.getMetaData();
		for(int i=0; i < metaData.getColumnCount(); i++)
			if(output.get(metaData.getColumnLabel(i))== null)
				output.put(metaData.getColumnName(i), new ArrayList<String>());
		while(set.next()) {
			for(int i=0; i < metaData.getColumnCount(); i++)
				output.get(metaData.getColumnLabel(i)).add(set.getString(i));
		}
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, String> getInputParams() {
		return inputParams;
	}

	public void setParameterInput(List<String> parameterInput) {
		this.parameterInput = parameterInput;
	}

	@Override
	public String toString() {
		return "DatabaseStep [id=" + id + ", name=" + name + ", url=" + url + ", userId=" + userId + ", password="
				+ password + ", query=" + query + ", outputParams=" + outputParams + ", inputParams=" + inputParams
				+ ", parameterInput=" + parameterInput + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DatabaseStep other = (DatabaseStep) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
