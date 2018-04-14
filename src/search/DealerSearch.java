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

/**
 * Wrapper class that is used to search Dealers
 */
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

    /**
     * Create a String containing the sql query based on input from the GUI
     *
     * @return String containing the query
     */
    @Override
    public String prepareSQL() {
        String query = "SELECT * FROM DEALER";
        boolean firstTime = true;
        if (dealerFields.size() > 0) {
            StringBuilder builder = new StringBuilder(" WHERE ");
            for (Map.Entry<String, Object> entry : dealerFields.entrySet()) {
                if (entry.getKey().equals("ZIP")) {
                    int zip = (Integer) entry.getValue();
                    if (firstTime) {
                        builder.append(entry.getKey()).append(" BETWEEN ").append((zip - 100)).append(" AND ").append(zip + 100).append(" ");
                        firstTime = false;
                    } else {
                        builder.append(" AND ").append(entry.getKey()).append(" BETWEEN ").append((zip - 100)).append(" AND ").append(zip + 100);
                    }
                } else {
                    if (firstTime) {
                        builder.append(entry.getKey()).append("=? ");
                        super.setParameterIndex(entry.getValue());
                        firstTime = false;
                    } else {
                        builder.append(" AND ").append(entry.getKey()).append(" =? ");
                        super.setParameterIndex(entry.getValue());
                    }
                }
            }
            query += builder.substring(0, builder.length());
        }

        return query;
    }

    /**
     * Execute the query based on the options in the GUI
     *
     * @param connection Connection to database
     * @return List of dealers that fulfill the query
     */
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
                dealer.setStreet(rs.getString("Street"));
                dealer.setCity(rs.getString("City"));
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
