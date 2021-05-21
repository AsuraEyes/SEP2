package server.model.database.member;

import server.model.database.administrator.AdministratorDAOImpl;
import shared.transferobjects.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Class that implements methods from its interface and provides access to a database(Member in this case)
 *
 */
public class MemberDAOImpl implements MemberDAO{
    private static MemberDAOImpl instance;
    private String password;

    private MemberDAOImpl()throws SQLException{
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    public static synchronized MemberDAOImpl getInstance() throws SQLException {
        if(instance == null){
            instance = new MemberDAOImpl();
        }
        return instance;
    }

    public void setPassword(String password){
        this.password = password;
    }

    private Connection getConnection() throws SQLException {
        System.out.println("password: "+password);
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", password);
    }

    /**
     * Crates new member object by connecting to a database then inserting data provided by user to the database
     * @param username username input by user while creating new account
     * @param password password input by user while creating new account
     * @param emailAddress email address input by user while creating new account (can be null if phone number was put)
     * @param phoneNumber phone number input by user while creating new account (can be null if email address was put)
     * @param otherInformation other users information input by user while creating new account
     * @param addressStreet street of the place where user lives input by user while creating new account
     * @param addressNo street's number of the place where user lives input by user while creating new account
     * @param addressPostalCode postal code of the place where user lives input by user while creating new account
     * @param addressCity city where user lives chosen from the list of possible cities by user while creating new account
     * @return returns new object of Member with data which was provided by user while creating new account
     * @throws SQLException
     */
    @Override
    public Member create(String username, String password, String emailAddress, String phoneNumber, String otherInformation, String addressStreet, String addressNo, int addressPostalCode, String addressCity) throws SQLException {
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("INSERT INTO share_it.member(username, password, email_address, phone_number, other_information, address_street, address_no, address_postal_code, address_city_name) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);", PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, emailAddress);
            statement.setString(4, phoneNumber);
            statement.setString(5, otherInformation);
            statement.setString(6, addressStreet);
            statement.setString(7, addressNo);
            statement.setInt(8, addressPostalCode);
            statement.setString(9, addressCity);
            statement.executeUpdate();

            //this gets the generated id of the member
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()){
                return new Member(generatedKeys.getInt(1), username, password, emailAddress, phoneNumber, otherInformation, addressStreet, addressNo, addressPostalCode, addressCity, 0);
            }
            else{
                throw new SQLException("No keys generated");
            }
        }
    }

    /**
     *
     * @param username
     * @return
     * @throws SQLException
     */
    @Override
    public List<Member> readByUsername(String username) throws SQLException {
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM share_it.member WHERE username LIKE ?");
            statement.setString(1, "%"+username+"%");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Member> arrayListToReturn = new ArrayList<>();
            //correct this when doing search
            while (resultSet.next()){
                int idOfSearchedMember = resultSet.getInt("id");
                //get the member with this id or create it getting all the information
                // and add him to the array list
            }
            //return array list
            return arrayListToReturn;
        }
    }

    /**
     * Checks if the username is unique(not in database) by connecting to the database and try to match given username with existing ones
     * @param username username that has to be checked for being unique
     * @return returns true if it is unique or false if it is already in database
     * @throws SQLException
     */
    @Override
    public boolean uniqueUsername(String username) throws SQLException {
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM share_it.member WHERE username = ?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            int numberOfResults = 0;
            while (resultSet.next()){
                numberOfResults++;
            }
            if(numberOfResults == 0){
                if(AdministratorDAOImpl.getInstance().uniqueUsername(username)){
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Updates member object by connecting to a database then updating data provided by user to the database
     * @param username username cannot be changed
     * @param password new password input by user(if it was changed)
     * @param emailAddress new email address input by user(if it was changed)
     * @param phoneNumber new phone number input by user(if it was changed)
     * @param otherInformation new other information input by user(if it was changed)
     * @param addressStreet new street address input by user(if it was changed)
     * @param addressNo new street address input by user(if it was changed)
     * @param addressPostalCode new postal code input by user(if it was changed)
     * @param addressCity new city chosen from the list of possible cities by user(if it was changed)
     * @throws SQLException
     */
    @Override
    public void update(String username, String password, String emailAddress, String phoneNumber, String otherInformation, String addressStreet, String addressNo, int addressPostalCode, String addressCity) throws SQLException {
        try(Connection connection  = getConnection()){
            PreparedStatement statement = connection.prepareStatement("UPDATE share_it.member SET password = ?, email_address = ?, phone_number = ?, other_information = ?, address_street = ?, address_no = ?, address_postal_code = ?, address_city_name = ? WHERE username = ?");
            statement.setString(1, password);
            statement.setString(2, emailAddress);
            statement.setString(3, phoneNumber);
            statement.setString(4, otherInformation);
            statement.setString(5, addressStreet);
            statement.setString(6, addressNo);
            statement.setInt(7, addressPostalCode);
            statement.setString(8, addressCity);
            statement.setString(9, username);
            statement.executeUpdate();


        }
    }
    /*@Override
    public void update(Member member) throws SQLException {
        try(Connection connection  = getConnection()){
            //for updating member information
            //SET all except for primary key
            //WHERE member.get primary key = member.primary key

            PreparedStatement statement = connection.prepareStatement("UPDATE share_it.member SET username = ?, password = ?, email_address = ?, phone_number = ?, other_information = ?, address_street = ?, address_no = ?, address_postal_code = ?, address_city_name = ? WHERE member.getId = )");
            statement.setString(1, member.getUsername());
            statement.setString(2, member.getPassword());
            statement.setString(3, member.getEmailAddress());
            statement.setString(4, member.getPhoneNo());
            statement.setString(5, member.getOtherInformation());
            statement.setString(6, member.getAddressStreet());
            statement.setString(7, member.getAddressNo());
            statement.setInt(8, member.getAddressPostalCode());
            statement.setString(9, member.getAddressCity());
            statement.executeUpdate();

            /*ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()){
                return new Member(generatedKeys.getInt(1), username, password, emailAddress, phoneNumber, otherInformation, addressStreet, addressNo, addressPostalCode, addressCity, 0);
            }
            else{
                throw new SQLException("No keys generated");
            }
        }
    }*/

    /**
     * Deletes member from database by connecting to the database and deleting matched object's id with existing member
     * @param member Member object that is about to be deleted
     * @return returns true if deleting process was successful
     * @throws SQLException
     */
    @Override
    public boolean delete(Member member) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("DELETE FROM share_it.member WHERE id = ?");
            statement.setInt(1, member.getId());
            statement.executeUpdate();
            return true;
        }
    }

    /**
     * Gets member object based on given ID by connecting to the database and matching given id with existing member
     * @param id ID of the member that information is needed
     * @return returns member object with all his information
     * @throws SQLException
     */
    @Override
    public Member getMemberById(int id) throws SQLException{
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM share_it.member WHERE id = ?");
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                return new Member(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("email_address"), resultSet.getString("phone_number"), resultSet.getString("other_information"), resultSet.getString("address_street"), resultSet.getString("address_no"), resultSet.getInt("address_postal_code"), resultSet.getString("address_city_name"),resultSet.getFloat("average_review"));
            }
            else{
                throw new SQLException("No keys generated");
            }
        }
    }

    /**
     * Checks ID of member based on his username by connecting to the database and match existing member with given username
     * @param username username that has to be checked for ID
     * @return returns ID of checked member
     * @throws SQLException
     */
    @Override
    public int readIdByUsername(String username) throws SQLException{
        try(Connection connection = getConnection()){
            //username = "bob";
            PreparedStatement statement = connection.prepareStatement("SELECT id FROM share_it.member WHERE username = ?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            System.out.println("username "+username);
            if(resultSet.next()){
                return (resultSet.getInt("id"));
            }
            else{
                throw new SQLException("No keys generated");
            }
        }
    }

    /**
     * Checks to whom given username and password belongs to by connecting to the database and matching given data with existing data
     * @param username username that has to be checked
     * @param password password that has to be checked
     * @return returns username if the data matches from given username and password(if username and password belongs to an administrator it returns administrator data)
     * @throws SQLException
     */
    @Override
    public String checkLogInCredentials(String username, String password) throws SQLException{
        try(Connection connection = getConnection()){
            System.out.println("username: " + username + ", password: " + password);
            PreparedStatement statement = connection.prepareStatement("SELECT username FROM share_it.member WHERE username = ? AND password = ?");
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                return resultSet.getString("username");
            }
            else{
                String adminUsername = AdministratorDAOImpl.getInstance().checkLogInCredentials(username, password);
                if(adminUsername != null){
                    return adminUsername;
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets member object based on given username by connecting to the database and matching given username with existing member
     * @param username username of the member that information is needed
     * @return returns Member object with all his information
     * @throws SQLException
     */
    @Override
    public Member getMemberByUsername(String username) throws SQLException{
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM share_it.member WHERE username = ?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                return new Member(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getString("password"), resultSet.getString("email_address"), resultSet.getString("phone_number"), resultSet.getString("other_information"), resultSet.getString("address_street"), resultSet.getString("address_no"), resultSet.getInt("address_postal_code"), resultSet.getString("address_city_name"),resultSet.getFloat("average_review"));
            }
            else{
                throw new SQLException("No keys generated");
            }
        }
    }
}
