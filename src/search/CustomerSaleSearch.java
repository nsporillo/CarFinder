package search;

import models.Sale;
import models.Vehicle;

import java.sql.Connection;
import java.util.*;

/**
 * Search wrapper class for querying DB for sales for a customer
 */
public class CustomerSaleSearch extends Search {

	private Map<String, Object> customerFields = new HashMap<>();
	private Map<String, Object> vehicleFields = new HashMap<>();
	private Map<String, Object> saleFields = new HashMap<>();
	private Map<String, Object> optionFields = new HashMap<>();
	private Map<Integer, Object> parameterIndex = new TreeMap<>();

	@Override
	public String prepareSQL() {
		return null;
	}

	@Override
	public List<Sale> execute(Connection connection) {
		return null;
	}
}
