package tables;

import models.Option;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class OptionTable {

    /**
     * Reads a cvs file for data and adds them to the option table
     * <p>
     * Does not create the table. It must already be created
     *
     * @param conn     database connection to work with
     * @param fileName fileName of CSV file containing option table data
     * @throws SQLException
     */
    public static void populateOptionTableFromCSV(Connection conn, String fileName) throws SQLException {
        ArrayList<Option> option = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                String[] split = line.split(",");
                option.add(new Option(Integer.parseInt(split[0]), split[1], split[2], split[3]));
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        String sql = createOptionInsertSQL(option);

        Statement stmt = conn.createStatement();
        stmt.execute(sql);
    }

    /**
     * Adds a single option to the database
     *
     * @param conn         connection
     * @param optionID     optionID
     * @param color        car color
     * @param engine       car engine
     * @param transmission car transmission
     */
    public static void addOption(Connection conn, int optionID, String color, String engine, String transmission) {

        String query = String.format("INSERT INTO Option "
                + "VALUES(%d,\'%s\',\'%s\',\'%s\');", optionID, color, engine, transmission);

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

    /**
     * Creates SQL command for inserting Options into Option table
     *
     * @param option list of options to be added into Option table
     * @return SQL command
     */
    public static String createOptionInsertSQL(ArrayList<Option> option) {
        StringBuilder sb = new StringBuilder();

        sb.append("INSERT INTO Option (OptionID, Color, Engine, Transmission) VALUES");

        for (int i = 0; i < option.size(); i++) {
            Option o = option.get(i);
            sb.append(String.format("(%d,\'%s\',\'%s\',\'%s\')",
                    o.getId(), o.getColor(), o.getEngine(), o.getTransmission()));
            if (i != option.size() - 1) {
                sb.append(",");
            } else {
                sb.append(";");
            }
        }

        return sb.toString();
    }

    /**
     * Makes a query to the option table
     * with given columns and conditions
     *
     * @param conn
     * @param columns:      columns to return
     * @param whereClauses: conditions to limit query by
     * @return
     */
    public static ResultSet queryOptionTable(Connection conn,
                                             ArrayList<String> columns,
                                             ArrayList<String> whereClauses) {
        StringBuilder sb = new StringBuilder();

        /**
         * Start the select query
         */
        sb.append("SELECT ");

        /**
         * If we gave no columns just give them all to us
         *
         * other wise add the columns to the query
         * adding a comma top seperate
         */
        if (columns.isEmpty()) {
            sb.append("* ");
        } else {
            for (int i = 0; i < columns.size(); i++) {
                if (i != columns.size() - 1) {
                    sb.append(columns.get(i) + ", ");
                } else {
                    sb.append(columns.get(i) + " ");
                }
            }
        }

        /**
         * Tells it which table to get the data from
         */
        sb.append("FROM person ");

        /**
         * If we gave it conditions append them
         * place an AND between them
         */
        if (!whereClauses.isEmpty()) {
            sb.append("WHERE ");
            for (int i = 0; i < whereClauses.size(); i++) {
                if (i != whereClauses.size() - 1) {
                    sb.append(whereClauses.get(i) + " AND ");
                } else {
                    sb.append(whereClauses.get(i));
                }
            }
        }

        /**
         * close with semi-colon
         */
        sb.append(";");

        //Print it out to verify it made it right
        System.out.println("Query: " + sb.toString());
        try {
            /**
             * Execute the query and return the result set
             */
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sb.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Prints out Option
     *
     * @param conn connection
     */
    public static void printOptionTable(Connection conn) {

        String query = "SELECT * FROM Option;";
        try {
            Statement stmt = conn.createStatement();
            ResultSet result = stmt.executeQuery(query);

            while (result.next()) {
                System.out.printf("Option %d: %s %s %s\n",
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
