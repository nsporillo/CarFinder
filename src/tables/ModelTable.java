package tables;

import models.Model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
    public static void populateModelTableFromCSV(Connection conn, String fileName) throws SQLException {
        ArrayList<Model> models = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                models.add(new Model(Integer.parseInt(split[0]), split[1], split[2]));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        String sql = createModelInsertSQL(models);

        Statement stmt = conn.createStatement();
        stmt.execute(sql);
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

    public static String createModelInsertSQL(ArrayList<Model> model) {
        StringBuilder sb = new StringBuilder();

        sb.append("INSERT INTO Model (ModelID, BrandName, BodyStyle) VALUES");

        for (int i = 0; i < model.size(); i++) {
            Model m = model.get(i);
            sb.append(String.format("(%d,\'%s\',\'%s\')",
                    m.getId(), m.getBrandName(), m.getBodyStyle()));
            if (i != model.size() - 1) {
                sb.append(",");
            } else {
                sb.append(";");
            }
        }

        return sb.toString();
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
