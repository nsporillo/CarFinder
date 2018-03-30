package search;

import java.sql.Connection;
import java.util.List;

public abstract class Search {

	public abstract String prepareSQL();

	public abstract List<?> execute(Connection connection);
}
