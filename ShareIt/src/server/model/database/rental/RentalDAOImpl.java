package server.model.database.rental;

import server.model.database.member.MemberDAOImpl;
import server.model.database.rental_category.RentalCategoryDAOImpl;
import shared.transferobjects.Rental;

import java.io.*;
import java.nio.file.Files;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Class that implements methods from its interface and provides access to a database(Rental in this case)
 *
 */
public class RentalDAOImpl implements RentalDAO
{
  private static RentalDAOImpl instance;
  private String password;

  private RentalDAOImpl() throws SQLException
  {
    DriverManager.registerDriver(new org.postgresql.Driver());
  }

  public static synchronized RentalDAOImpl getInstance()
  {
    if (instance == null)
    {
      try
      {
        instance = new RentalDAOImpl();
      }
      catch (SQLException throwables)
      {
        throwables.printStackTrace();
      }
    }
    return instance;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }

  private Connection getConnection() throws SQLException {
    return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", password);
  }

  /**
   * Crates new rental object by connecting to a database then inserting data provided by user to the database
   * @param name name of the rental input by user while creating new rental offer
   * @param pictureLink
   * @param description description of the rental input by user while creating new rental offer
   * @param price price of the rental input by user while creating new rental offer
   * @param otherInformation other information of the rental input by user while creating new rental offer
   * @param stateName state of the rental chosen from the list of possible states by user while creating new rental offer
   * @param selectedCategories categories of the rental chosen from the list of possible categories by user while creating new rental offer
   * @return returns new object of Rental with data which was provided by user while creating new rental offer
   * @throws SQLException
   */
  @Override public Rental create(String name, String pictureLink,
      String description, int price, String otherInformation, String stateName, String username, ArrayList<String> selectedCategories) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      File file = null;
      file = new File(pictureLink);
      FileInputStream fis = null;
      try
      {
        fis = new FileInputStream(file);
      }
      catch (FileNotFoundException e)
      {
        e.printStackTrace();
      }

      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM share_it.member WHERE username = ?");
      statement.setString(1,username);
      ResultSet resultSet = statement.executeQuery();
      int memberId = 0;
      if (resultSet.next())
      {
        memberId = resultSet.getInt("id");
      }
      else
      {
        throw new SQLException("No keys generated");
      }

      statement = connection.prepareStatement(
          "INSERT INTO share_it.rental(name, picture_link, description, price, other_information, state_name, member_id) VALUES (?, ?, ?, ?, ?, ?, ?);",
          PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setString(1, name);
      statement.setBinaryStream(2, fis, (int) file.length());
      statement.setString(3, description);
      statement.setInt(4, price);
      statement.setString(5, otherInformation);
      statement.setString(6, stateName);
      statement.setInt(7, memberId);
      statement.executeUpdate();

      int rentalId;
      ResultSet generatedKeys = statement.getGeneratedKeys();
      if (generatedKeys.next())
      {
        rentalId = generatedKeys.getInt(1);
      }
      else
      {
        throw new SQLException("No keys generated");
      }

      for (int i = 0; i < selectedCategories.size(); i++)
      {
        statement = connection.prepareStatement(
            "INSERT INTO share_it.rental_category(rental_id, category_name) VALUES (?, ?);",
            PreparedStatement.RETURN_GENERATED_KEYS);
        statement.setInt(1, rentalId);
        statement.setString(2, selectedCategories.get(i));
        statement.executeUpdate();
      }
      return new Rental(rentalId, name, pictureLink, description, price,
          otherInformation, stateName, memberId, selectedCategories);
    }
  }

  /**
   * Checks all the rentals with given values
   * @param search search is a phrase that was input by user in order to get rentals matched with it
   * @param city city that member who posted rental should be from
   * @param categories categories that searched for rental should be in
   * @return returns list of rentals where values input matches with rentals values
   * @throws SQLException
   */
  @Override
  public List<Rental> readBySearchAndFilter(String search, String city, ArrayList<String> categories) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      String addToStatement = "";
      if(city != null && !city.equals("")){
        addToStatement += " AND r.member_id = m.id AND m.address_city_name = '"+city+"'";
      }
      if(categories.size() > 0){
        for (int i = 0; i < categories.size(); i++) {
          if(i == 0){
            if(categories.size() != 1){
              addToStatement += " AND ((r.id = rc.rental_id AND rc.category_name = '"+categories.get(i)+"')";
            }
            else{
              addToStatement += " AND r.id = rc.rental_id AND rc.category_name = '"+categories.get(i)+"'";
            }
          }
          else if(i == categories.size()-1){
            addToStatement += " OR (r.id = rc.rental_id AND rc.category_name = '"+categories.get(i)+"'))";
          }
          else{
            addToStatement += " OR (r.id = rc.rental_id AND rc.category_name = '"+categories.get(i)+"')";
          }
        }
      }

      PreparedStatement statement = connection.prepareStatement(
              "SELECT DISTINCT * FROM share_it.rental AS r, share_it.member AS m, share_it.rental_category AS rc WHERE name || description  ILIKE ? "+addToStatement+";");
      if(search != null){
        if(!search.equals("")){
          statement.setString(1, "%" + search + "%");
        }
        else{
          statement.setString(1, "%");
        }
      }
      else{
        statement.setString(1, "%");
      }

      ResultSet resultSet = statement.executeQuery();
      ArrayList<Integer> listOfIds = new ArrayList<>();
      while (resultSet.next())
      {
        int idOfSearchedRental = resultSet.getInt("id");
        if(!listOfIds.contains(idOfSearchedRental)){
          listOfIds.add(idOfSearchedRental);
        }
      }

      String sqlToAppend = "";
      if(listOfIds.size() != 0)
      {
        sqlToAppend = "SELECT * FROM share_it.rental WHERE";
        for (int i = 0; i < listOfIds.size(); i++)
        {
          if (i == 0)
          {
            sqlToAppend += " id=" + listOfIds.get(i);
          }
          else
          {
            sqlToAppend += " OR id=" + listOfIds.get(i);
          }
        }
      }
      statement = connection.prepareStatement(sqlToAppend);
      System.out.println(statement);
      if(!sqlToAppend.isEmpty())
      {
        resultSet = statement.executeQuery();
        ArrayList<Rental> arrayListToReturn = new ArrayList<>();

        while (resultSet.next())
        {
          String filename = "image" + resultSet.getInt("id") + ".jpeg";
          byte[] imgBytes = resultSet.getBytes(3);
          Files.write(new File(filename).toPath(), imgBytes);

          int rentalId = resultSet.getInt("id");
          String rentalName = resultSet.getString("name");
          String rentalDescription = resultSet.getString("description");
          int priceOfRental = resultSet.getInt("price");
          String rentalOtherInformation = resultSet.getString("other_information");
          String rentalState = resultSet.getString("state_name");
          int memberId = resultSet.getInt("member_id");

          arrayListToReturn.add(new Rental(rentalId, rentalName, "file:" + filename,
              rentalDescription, priceOfRental, rentalOtherInformation,
              rentalState, memberId, RentalCategoryDAOImpl.getInstance().getSelectedCategoriesOnRental(rentalId)));
        }
        resultSet.close();
        statement.close();
        return arrayListToReturn;
      }
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   *
   * @param
   * @return
   * @throws SQLException
   */
  @Override public Rental getRentalById(int id)
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM share_it.rental WHERE id = ?");
      statement.setInt(1, id);
      ResultSet resultSet = statement.executeQuery();

      if (resultSet.next())
      {
        String filename = "image"+resultSet.getInt("id")+".jpeg";
        byte[] imgBytes = resultSet.getBytes(3);
        Files.write(new File(filename).toPath(), imgBytes);

        int rentalId = resultSet.getInt("id");
        String rentalName = resultSet.getString("name");
        String rentalDescription = resultSet.getString("description");
        int priceOfRental = resultSet.getInt("price");
        String rentalOtherInformation = resultSet.getString("other_information");
        String rentalState = resultSet.getString("state_name");
        int memberId = resultSet.getInt("member_id");

        new Rental(rentalId, rentalName, "file:" + filename, rentalDescription,
                priceOfRental, rentalOtherInformation, rentalState, memberId,
                RentalCategoryDAOImpl.getInstance().getSelectedCategoriesOnRental(rentalId));
      }
    }
    catch (SQLException | IOException throwables)
    {
      throwables.printStackTrace();
    }
    return null;
  }

  /**
   *
   * @param search
   * @return
   * @throws SQLException
   */
  public List<Rental> readBySearch(String search) throws SQLException {
    try (Connection connection = getConnection()) {
      PreparedStatement statement = connection.prepareStatement(
              "SELECT * FROM share_it.rental WHERE name || description  ILIKE ?;");
      statement.setString(1, "%" + search + "%");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Rental> arrayListToReturn = new ArrayList<>();

      while (resultSet.next()) {
        String filename = "image" + resultSet.getInt("id") + ".jpeg";
        byte[] imgBytes = resultSet.getBytes(3);
        Files.write(new File(filename).toPath(), imgBytes);

        int rentalId = resultSet.getInt("id");
        String rentalName = resultSet.getString("name");
        String rentalDescription = resultSet.getString("description");
        int priceOfRental = resultSet.getInt("price");
        String rentalOtherInformation = resultSet.getString("other_information");
        String rentalState = resultSet.getString("state_name");
        int memberId = resultSet.getInt("member_id");

        arrayListToReturn.add(
                new Rental(rentalId, rentalName, "file:" + filename, rentalDescription,
                        priceOfRental, rentalOtherInformation, rentalState, memberId,
                        RentalCategoryDAOImpl.getInstance().getSelectedCategoriesOnRental(rentalId)));
      }
      resultSet.close();
      statement.close();
      return arrayListToReturn;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Updates rental object by connecting to a database then updating data provided by user to the database
   * @param name Name of rental that was changed
   * @throws SQLException
   */
  @Override public void update(String name, String pictureLink, String description, int price, String otherInformation, String stateName, int rentalId, ArrayList<String> selectedCategories) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      File file = new File(pictureLink);
      FileInputStream fis = null;
      try
      {
        fis = new FileInputStream(file);
      }
      catch (FileNotFoundException e)
      {
        e.printStackTrace();
      }
      PreparedStatement statement = connection.prepareStatement(
          "UPDATE share_it.rental SET name = ?, picture_link = ?, description = ?, price = ?, other_information = ?, state_name = ? WHERE id = ?");
      statement.setString(1, name);
      statement.setBinaryStream(2, fis, (int) file.length());
      statement.setString(3, description);
      statement.setInt(4, price);
      statement.setString(5, otherInformation);
      statement.setString(6, stateName);
      statement.setInt(7, rentalId);
      statement.executeUpdate();

      for (int i = 0; i < selectedCategories.size(); i++)
      {
        statement = connection.prepareStatement(
                "UPDATE  share_it.rental_category SET category_name = ? WHERE rental_id = ?");
        statement.setString(1, selectedCategories.get(i));
        statement.setInt(2, rentalId);
        statement.executeUpdate();
      }
    }
  }

  /**
   * Deletes rental from database by connecting to the database and deleting matched object's id with existing member
   * @param rental rental object that will be deleted
   * @throws SQLException
   * @return
   */
  @Override public boolean delete(Rental rental){
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("DELETE FROM share_it.rental WHERE id = ?");
      statement.setInt(1, rental.getId());
      statement.executeUpdate();
      return true;
    }
    catch (SQLException throwables)
    {
      throwables.printStackTrace();
    }
    return false;
  }

  /**
   *
   * @return
   * @throws SQLException
   */
  @Override public int getNextAvailableId() throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection.prepareStatement(
          "SELECT nextval(pg_get_serial_sequence('share_it.rental', 'id')) AS available_id;");
      ResultSet resultSet = statement.executeQuery();
      int nextAvailableId;
      if (resultSet.next()) {
        nextAvailableId = resultSet.getInt("available_id");
      }
      else {
        throw new SQLException("No keys generated");
      }
      return nextAvailableId;
    }
  }

  /**
   * Reads all rentals from database by connecting to the database and get all table contents
   * @return list of all rentals that are stored in the database
   * @throws SQLException
   */
  @Override public List<Rental> readRentals(){
    try (Connection connection = getConnection()) {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM share_it.rental");
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Rental> arrayListToReturn = new ArrayList<>();

      while (resultSet.next())
      {
        String filename = "image"+resultSet.getInt("id")+".jpeg";
        byte[] imgBytes = resultSet.getBytes(3);
        Files.write(new File(filename).toPath(), imgBytes);

        int rentalId = resultSet.getInt("id");
        String rentalName = resultSet.getString("name");
        String rentalDescription = resultSet.getString("description");
        int priceOfRental = resultSet.getInt("price");
        String rentalOtherInformation = resultSet.getString("other_information");
        String rentalState = resultSet.getString("state_name");
        int memberId = resultSet.getInt("member_id");

        arrayListToReturn.add(
            new Rental(rentalId, rentalName, "file:" + filename, rentalDescription,
                priceOfRental, rentalOtherInformation, rentalState, memberId,
                RentalCategoryDAOImpl.getInstance().getSelectedCategoriesOnRental(rentalId)));
      }
      resultSet.close();
      statement.close();
      return arrayListToReturn;
    }
    catch (SQLException|IOException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  @Override public Rental getLastRental()
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM share_it.rental WHERE rental.id = ?");
      statement.setInt(1, getNextAvailableId()-1);
      ResultSet resultSet = statement.executeQuery();
      Rental lastRental = null;
      if(resultSet.next())
      {
        String filename = "image" + resultSet.getInt("id") + ".jpeg";
        byte[] imgBytes = resultSet.getBytes(3);
        Files.write(new File(filename).toPath(), imgBytes);

        int rentalId = resultSet.getInt("id");
        String rentalName = resultSet.getString("name");
        String rentalDescription = resultSet.getString("description");
        int priceOfRental = resultSet.getInt("price");
        String rentalOtherInformation = resultSet.getString("other_information");
        String rentalState = resultSet.getString("state_name");
        int memberId = resultSet.getInt("member_id");

        lastRental = new Rental(rentalId, rentalName, "file:" + filename,
                rentalDescription, priceOfRental, rentalOtherInformation,
                rentalState, memberId, RentalCategoryDAOImpl.getInstance().getSelectedCategoriesOnRental(rentalId));
      }
      resultSet.close();
      statement.close();
      return lastRental;
    }
    catch (SQLException|IOException e)
    {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Reads all rentals connected to the username from database by connecting to the database then by using instance to get id by using username from member database then match all given data with existing database data
   * @param username username that all rentals will be connected with
   * @return list of rentals that are matching with members username
   */
//  @Override
//  public ArrayList<Rental> getRentalsOfMemberList(String username) {
//    try (Connection connection = getConnection())
//    {
//      int member_id = MemberDAOImpl.getInstance().readIdByUsername(username);
//      PreparedStatement statement = connection
//              .prepareStatement("SELECT * FROM share_it.rental WHERE rental.member_id = ?");
//      statement.setInt(1, member_id);
//      ResultSet resultSet = statement.executeQuery();
//      ArrayList<Rental> arrayListToReturn = new ArrayList<>();
//      while (resultSet.next())
//      {
//        String filename = "image" + resultSet.getInt("id") + ".jpeg";
//        byte[] imgBytes = resultSet.getBytes(3);
//        Files.write(new File(filename).toPath(), imgBytes);
//
//        int rentalId = resultSet.getInt("id");
//        String rentalName = resultSet.getString("name");
//
//        String rentalDescription = resultSet.getString("description");
//        int priceOfRental = resultSet.getInt("price");
//        String rentalOtherInformation = resultSet.getString("other_information");
//        String rentalState = resultSet.getString("state_name");
//        int memberId = resultSet.getInt("member_id");
//
//
//        arrayListToReturn.add(
//                new Rental(rentalId, rentalName, "file:" + filename, rentalDescription,
//                        priceOfRental, rentalOtherInformation, rentalState, memberId,
//                        RentalCategoryDAOImpl.getInstance().getSelectedCategoriesOnRental(rentalId)));
//      }
//      return arrayListToReturn;
//    } catch (SQLException | IOException e) {
//      e.printStackTrace();
//    }
//    return null;
//  }
  @Override
  public ArrayList<Integer> getRentalsOfMemberList(String username) {
    try (Connection connection = getConnection())
    {
      int member_id = MemberDAOImpl.getInstance().readIdByUsername(username);
      PreparedStatement statement = connection
          .prepareStatement("SELECT * FROM share_it.rental WHERE rental.member_id = ?");
      statement.setInt(1, member_id);
      ResultSet resultSet = statement.executeQuery();
      ArrayList<Integer> arrayListToReturn = new ArrayList<>();
      while (resultSet.next())
      {
        int rentalId = resultSet.getInt("id");

        arrayListToReturn.add(rentalId);

      }
      return arrayListToReturn;
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }
}
