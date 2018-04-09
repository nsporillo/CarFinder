package search;

import models.Dealer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DealerSearch extends Search {

	private Map<String, Object> dealerFields;

	public DealerSearch() {
		super();
		dealerFields = new HashMap<>();
	}

	public void setDealerID(Integer dealerID) {
		if (dealerID != null && dealerID >= 0) {
			dealerFields.put("DealerID", dealerID);
		}
	}

	public void setName(String name) {
		if (name != null && name.length() > 0) {
			dealerFields.put("Name", name);
		}
	}

	public void setStreet(String street) {
		if (street != null && street.length() > 0) {
			dealerFields.put("Street", street);
		}
	}

	public void setCity(String city) {
		if (city != null && city.length() > 0) {
			dealerFields.put("City", city);
		}
	}

	public void setState(String state) {
		if (state != null && state.length() > 0) {
			dealerFields.put("State", state);
		}
	}

	public void setZIP(Integer zip) {
		if (zip != null && zip >= 0) {
			dealerFields.put("ZIP", zip);
		}
	}

	public void setPhone(String phone) {
		if (phone != null && phone.length() > 0) {
			dealerFields.put("Phone", phone);
		}
	}

	@Override
	public String prepareSQL() {
		String query = "SELECT * FROM DEALER";

		if (dealerFields.size() > 0) {
			StringBuilder builder = new StringBuilder(" WHERE ");

			for (Map.Entry<String, Object> entry : dealerFields.entrySet()) {
				builder.append(entry.getKey()).append("=? AND ");
				super.setParameterIndex(entry.getValue());
			}

			query += builder.substring(0, builder.lastIndexOf("?") + 1);
		}

		return query;
	}

	@Override
	public List<Dealer> execute(Connection connection) {
		List<Dealer> dealers = new ArrayList<>();

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = super.prepareStatement(connection, prepareSQL());
			rs = ps.executeQuery();
			super.clearParameterIndex();

			while (rs.next()) {
				Dealer dealer = new Dealer();
				dealer.setId(rs.getInt("DealerID"));
				dealer.setName(rs.getString("Name"));
				dealer.setState(rs.getString("Street"));
				dealer.setState(rs.getString("State"));
				dealer.setZip(rs.getInt("ZIP"));
				dealer.setPhone(rs.getString("Phone"));
				dealers.add(dealer);
			}

			return dealers;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
