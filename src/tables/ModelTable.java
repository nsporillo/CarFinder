package tables;

import models.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModelTable {

    /**
     * Reads a cvs file for data and adds them to the model table
     * <p>
     * Does not create the table. It must already be created
     *
     * @param conn     database connection to work with
     * @param fileName fileName of CSV file containing model table data
     * @throws SQLException
     */
    public static List<Model> populateModelTableFromCSV(Connection conn, String fileName) throws SQLException {
        List<Model> models = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                models.add(new Model(split[2], split[3]));
            }
            br.close();
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        String sql = createModelInsertSQL(models);

        Statement stmt = conn.createStatement();
        stmt.execute(sql);

        return models;
    }

    /**
     * Adds a single model to the database
     *
     * @param conn
     * @param modelID
     * @param brandName
     * @param bodyStyle
     */
    public static void addModel(Connection conn, int modelID, String brandName, String bodyStyle) {

        String query = String.format("INSERT INTO Model "
                + "VALUES(%d,\'%s\',\'%s\');", modelID, brandName, bodyStyle);

        try {
            /**
             * create and execute the query
             */
            Statement stmt = conn.createStatement();
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String createModelInsertSQL(List<Model> model) {
        StringBuilder sb = new StringBuilder();

        sb.append("INSERT INTO Model (BrandName, BodyStyle) VALUES");

        for (int i = 0; i < model.size(); i++) {
            Model m = model.get(i);
            sb.append(String.format("(\'%s\',\'%s\')", m.getBrandName(), m.getBodyStyle()));
            if (i != model.size() - 1) {
                sb.append(",");
            } else {
                sb.append(";");
            }
        }

        return sb.toString();
    }

    public static int getModelId(Connection conn, String brandName, String bodyStyle) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("SELECT ModelID FROM Model WHERE BrandName=? AND BodyStyle=?");
        stmt.setString(1, brandName);
        stmt.setString(2, bodyStyle);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            rs.getInt(1);
        }
        return 0;
    }

    public static List<Model> getAllModels(Connection conn) {
        List<Model> models = new ArrayList<>();

        String query = "SELECT * FROM Model;";
        try {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while (result.next()) {
                models.add(new Model(result.getInt(1),
                        result.getString(2),
                        result.getString(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return models;
    }

    /**
     * Prints out Model Table
     *
     * @param conn connection
     */
    public static void printModelTable(Connection conn) {

        String query = "SELECT * FROM Model;";
        try {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while (result.next()) {
                System.out.printf("Model %d: %s %s\n",
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
