package server.model.database.member;

import server.model.database.administrator.AdministratorDAOImpl;
import shared.transferobjects.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl implements MemberDAO{
    private static MemberDAOImpl instance;
    private String password;

    private MemberDAOImpl()throws SQLException{
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    public static synchronized MemberDAOImpl getInstance() throws SQLException{
        if(instance == null){
            instance = new MemberDAOImpl();
        }
        return instance;
    }

    public void setPassword(String password){
        this.password = password;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", password);
    }

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



    @Override
    public void update(Member member) throws SQLException {
        try(Connection connection  = getConnection()){
            //for updating member information
            //SET all except for primary key
            //WHERE member.get primary key = member.primary key
            //PreparedStatement statement = connection.prepareStatement("UPDATE share_it.member SET ")
        }
    }

    @Override
    public void delete(String username) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection
                    .prepareStatement("DELETE FROM share_it.member WHERE username = ?");
            statement.setString(1, username);
            statement.executeUpdate();
        }
    }

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

    @Override
    public int readIdByUsername(String username) throws SQLException{
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT id FROM share_it.member WHERE username = ?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){
                return (resultSet.getInt("id"));
            }
            else{
                throw new SQLException("No keys generated");
            }
        }
    }

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
}
