package search;

import models.Model;
import models.Option;
import models.Vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Wrapper class for the Searching through the Dealers inventory
 */
public class DealerInventorySearch extends Search {

    private Map<String, Object> dealerFields;
    private Map<String, Object> vehicleFields;
    private Map<String, Object> modelFields;
    private Map<String, Object> optionFields;

    public DealerInventorySearch() {
        super();
        dealerFields = new HashMap<>();
        vehicleFields = new HashMap<>();
        modelFields = new HashMap<>();
        optionFields = new HashMap<>();
    }

    public void setDealerID(int dealerId) {
        dealerFields.put("DealerID", dealerId);
    }

    public void setDealerName(String dealerName) {
        if (!dealerFields.containsKey("DealerID")) {
            dealerFields.put("LikeName", "%" + dealerName + "%");
        }
    }

    public void setYear(int year) {
        vehicleFields.put("Year", year);
    }

    public void setMaxPrice(int maxPrice) {
        vehicleFields.put("MaxPrice", maxPrice);
    }

    public void setBrandName(String brandName) {
        modelFields.put("BrandName", brandName);
    }

    public void setBodyStyle(String bodyStyle) {
        modelFields.put("BodyStyle", bodyStyle);
    }

    public void setColor(String color) {
        optionFields.put("Color", color);
    }

    public void setEngine(String engine) {
        optionFields.put("Engine", engine);
    }

    public void setTransmission(String transmission) {
        optionFields.put("Transmission", transmission);
    }

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
        // To return any vehicle which is in some dealer inventory, we want to join
        // dealer inventory, vehicle, model, and option
        // If we want to make dealer specific queries, then add the Dealer table to the join party.
        String query = "SELECT * FROM DealerInventory " +
                "INNER JOIN Vehicle ON DealerInventory.VIN = Vehicle.VIN " +
                "INNER JOIN Model ON Vehicle.ModelID = Model.ModelID " +
                "INNER JOIN Option ON Vehicle.OptionID = Option.OptionID " +
                "INNER JOIN Dealer on Dealer.DealerID = DealerInventory.DealerID";

        /* Filter results by any number of vehicle or model fields */
        if (vehicleFields.size() > 0 || modelFields.size() > 0 || optionFields.size() > 0 || dealerFields.size() > 0) {
            StringBuilder builder = new StringBuilder(" WHERE ");

            /* Filter results with vehicle only fields*/
            for (Map.Entry<String, Object> entry : vehicleFields.entrySet()) {
                /* Support for handling max price, max anything... */
                if (entry.getKey().startsWith("Max")) {
                    String key = entry.getKey().replaceAll("Max", "");
                    builder.append("Vehicle.").append(key).append("<=? AND ");
                } else {
                    builder.append("Vehicle.").append(entry.getKey()).append("=? AND ");
                }

                super.setParameterIndex(entry.getValue());
            }

            /* Filter results with model fields */
            for (Map.Entry<String, Object> entry : modelFields.entrySet()) {
                builder.append("Model.").append(entry.getKey()).append("=? AND ");
                super.setParameterIndex(entry.getValue());
            }

            for (Map.Entry<String, Object> entry : optionFields.entrySet()) {
                builder.append("Option.").append(entry.getKey()).append("=? AND ");
                super.setParameterIndex(entry.getValue());
            }

            for (Map.Entry<String, Object> entry : dealerFields.entrySet()) {
                if (entry.getKey().startsWith("Like")) {
                    builder.append("Dealer.").append(entry.getKey().replaceAll("Like", "")).append(" LIKE ? AND ");
                } else {
                    builder.append("Dealer.").append(entry.getKey()).append("=? AND ");
                }

                super.setParameterIndex(entry.getValue());
            }

            query += builder.substring(0, builder.lastIndexOf("?") + 1);
        }

        return query;
    }

    /**
     * Function used to find price of vehicle
     *
     * @param connection Connection to the Database
     * @param func       SQL statement generated by the GUI
     * @return Price of vehicle
     */
    public Integer findPrice(Connection connection, String func) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String preparedSQL = prepareSQL();
            preparedSQL = preparedSQL.replace("*", func + "(Vehicle.Price)");

            ps = super.prepareStatement(connection, preparedSQL);
            rs = ps.executeQuery();

            super.clearParameterIndex();

            if (rs.next()) {
                return rs.getInt(1);
            }
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

        return null;
    }

    public List<String> findRemainingColumnRows(Connection connection, String column) {
        List<String> rows = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String preparedSQL = prepareSQL();

            // We want to replace the * with the proper column to find all distinct given all additional search criteria
            // Supports makes, models, colors, engines, transmissions
            // TODO: Other fields must employ additional column searching for full functionality
            switch (column) {
                case "BrandName": {
                    preparedSQL = preparedSQL.replace("*", "DISTINCT Model.BrandName");
                    preparedSQL += " ORDER BY Model.BrandName";
                    break;
                }
                case "BodyStyle": {
                    preparedSQL = preparedSQL.replace("*", "DISTINCT Model.BodyStyle");
                    preparedSQL += " ORDER BY Model.BodyStyle";
                    break;
                }
                case "Year": {
                    preparedSQL = preparedSQL.replace("*", "DISTINCT Vehicle.Year");
                    preparedSQL += " ORDER BY Vehicle.Year";
                    break;
                }
                case "Color": {
                    preparedSQL = preparedSQL.replace("*", "DISTINCT Option.Color");
                    preparedSQL += " ORDER BY Option.Color";
                    break;
                }
                case "Engine": {
                    preparedSQL = preparedSQL.replace("*", "DISTINCT Option.Engine");
                    preparedSQL += " ORDER BY Option.Engine";
                    break;
                }
                case "Transmission": {
                    preparedSQL = preparedSQL.replace("*", "DISTINCT Option.Transmission");
                    preparedSQL += " ORDER BY Option.Transmission";
                }
                break;
            }

            ps = super.prepareStatement(connection, preparedSQL);
            rs = ps.executeQuery();

            super.clearParameterIndex();

            while (rs.next()) {
                rows.add(rs.getString(1));
            }

            return rows;
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

    /**
     * Execute and parse out the results of the query
     *
     * @param connection Connection to the database that is being queried
     * @return List of vehicles that are the result of the query
     */
    @Override
    public List<Vehicle> execute(Connection connection) {
        List<Vehicle> vehicles = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = prepareSQL() + " ORDER BY Vehicle.Price DESC";
            ps = super.prepareStatement(connection, sql);
            rs = ps.executeQuery();
            super.clearParameterIndex();

            while (rs.next()) {
                Model model = new Model(
                        rs.getInt("ModelID"),
                        rs.getString("BrandName"),
                        rs.getString("BodyStyle"));
                Option option = new Option(
                        rs.getInt("OptionID"),
                        rs.getString("Color"),
                        rs.getString("Engine"),
                        rs.getString("Transmission"));
                Vehicle vehicle = new Vehicle(
                        rs.getString("VIN"),
                        model,
                        option,
                        rs.getInt("Year"),
                        rs.getInt("Price"));
                vehicle.setOwner(rs.getString("Name"));

                vehicles.add(vehicle);
            }

            return vehicles;
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
