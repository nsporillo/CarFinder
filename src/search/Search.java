package search;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class Search {

	private Map<Integer, Object> parameterIndex;

	public Search() {
		this.parameterIndex = new TreeMap<>();
	}

	public abstract String prepareSQL();

	public abstract List<?> execute(Connection connection);

	public PreparedStatement prepareStatement(Connection connection, String sql) throws SQLException {
		PreparedStatement ps;

		if (sql.endsWith(";")) {
			sql += ";";
		}

		ps = connection.prepareStatement(sql);

		System.out.println(ps.toString());

		for (Map.Entry<Integer, Object> entry : parameterIndex.entrySet()) {
			Object e = entry.getValue();

			if (e instanceof String) {
				ps.setString(entry.getKey(), (String) e);
			} else if (e instanceof Integer) {
				ps.setInt(entry.getKey(), (Integer) e);
			}
		}

		return ps;
	}

	public void setParameterIndex(Object o) {
		parameterIndex.put(nextParameterIndex(), o);
	}

	public void clearParameterIndex() {
		parameterIndex.clear();
	}

	public Map<Integer, Object> getParameterIndex() {
		return parameterIndex;
	}

	private int nextParameterIndex() {
		return parameterIndex.size() + 1;
	}
}
