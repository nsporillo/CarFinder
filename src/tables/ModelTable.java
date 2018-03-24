package tables;

import models.Model;
import models.Option;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ModelTable {

  /**
   * Reads a cvs file for data and adds them to the model table
   *
   * Does not create the table. It must already be created
   *
   * @param conn     database connection to work with
   * @param fileName fileName of CSV file containing model table data
   * @throws SQLException
   */
  public static void populateOptionTableFromCSV(Connection conn, String fileName) throws SQLException {
    ArrayList<Model> model = new ArrayList<>();
    try {
      BufferedReader br = new BufferedReader(new FileReader(fileName));
      String line;
      while((line = br.readLine()) != null){
        String[] split = line.split(",");
        model.add(new Model(Integer.parseInt(split[0]), split[1], split[2]));
      }
      br.close();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (NumberFormatException e) {
      e.printStackTrace();
    }

    String sql = createModelInsertSQL(model);

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

    for(int i = 0; i < model.size(); i++){
      Model m = model.get(i);
      sb.append(String.format("(%d,\'%s\',\'%s\')",
              m.getId(), m.getBrandName(), m.getBodyStyle()));
      if( i != model.size()-1){
        sb.append(",");
      }
      else{
        sb.append(";");
      }
    }

    return sb.toString();
  }
}
