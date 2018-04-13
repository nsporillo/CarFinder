package search;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class SaleSearch extends Search {

    /**
     * Test method for simulating PreparedStatement
     * Do not call before .execute(), only replace .execute() with this.
     *
     * @return full SQL query with parameters injected
     */
    private String injectParameters() {
        String sql = prepareSQL();

        for (Map.Entry<Integer, Object> entry : super.getParameterIndex().entrySet()) {
            Object e = entry.getValue();

            sql = sql.replaceFirst("\\?", e.toString());
        }

        return sql;
    }

    @Override
    public String prepareSQL() {
        return null;
    }

    @Override
    public List<?> execute(Connection connection) {
        return null;
    }
}
