package server.model.database.state;

import shared.transferobjects.State;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Class that implements methods from its interface and provides access to a database(State in this case)
 *
 */
public class StateDAOImpl implements StateDAO
{
  private static StateDAOImpl instance;
  private String password;

  private StateDAOImpl()throws SQLException{
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized StateDAOImpl getInstance() throws SQLException{
    if(instance == null){
      instance = new StateDAOImpl();
    }
    return instance;
  }

  public void setPassword(String password){
    this.password = password;
  }

  private Connection getConnection() throws SQLException {
    return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", password);
  }

  /**
   * Reads all states from database by connecting to the database and get all table contents
   * @return returns all state names in a arraylist
   * @throws SQLException
   */
  @Override public List<State> readState()
      throws SQLException
  {
    try(Connection connection = getConnection()){
      PreparedStatement statement = connection.prepareStatement("SELECT * FROM share_it.state");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<State> arrayListToReturn = new ArrayList<>();
      while(resultSet.next()){
        arrayListToReturn.add(new State(resultSet.getString("name")));
      }
      //return array list
      return arrayListToReturn;
    }
  }
}
