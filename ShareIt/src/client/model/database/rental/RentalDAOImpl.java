package client.model.database.rental;

import shared.transferobjects.Member;
import shared.transferobjects.Rental;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RentalDAOImpl {
    private static RentalDAOImpl instance;
    private String password;

    private RentalDAOImpl()throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    public static synchronized RentalDAOImpl getInstance() throws SQLException{
        if(instance == null){
            instance = new RentalDAOImpl();
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
    public Rental create(String name, String description, int price, String otherInformation, String stateName, Member member) throws SQLException {
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("INSERT INTO share_it.rental(name, description, price, other_information, state_name, member_id) VALUES (?, ?, ?, ?, ?, ?);", PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            statement.setString(2, description);
            statement.setInt(3, price);
            statement.setString(4, otherInformation);
            statement.setString(5, stateName);
            statement.setInt(6, member.getId());
            statement.executeUpdate();

            //this gets the generated id of the member
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()){
                return new Rental(generatedKeys.getInt(1), name, description, price, otherInformation, stateName, member);
            }
            else{
                throw new SQLException("No keys generated");
            }
        }
    }

    @Override
    public List<Rental> readByName(String name) throws SQLException {
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM share_it.rental WHERE name LIKE ?");
            statement.setString(1, "%"+name+"%");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Rental> arrayListToReturn = new ArrayList<>();
            while (resultSet.next()){
                int idOfSearchedRental = resultSet.getInt("id");
                //get the member with this id or create it getting all the information
                // and add him to the array list
            }
            //return array list
            return arrayListToReturn;
        }
    }

    /*public static Rental createBook(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        int price = resultSet.getInt("price");
        String other_information = resultSet.getString("other_information");
        String state_name = resultSet.getString("state_name");
        int member_id = resultSet.getInt("member_id");
        String memberUsername = resultSet.getString("Username");
        Member member = new Member(member_id, memberUsername);
        return new Rental(id, name, description, price, other_information, state_name, member);
    }*/

    @Override
    public void update(Rental rental) throws SQLException {
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE share_it.rental SET name = ?, description = ?, price = ?, other_information = ?, state_name = ?, member_id = ? WHERE id = ?");
            statement.setString(1, rental.getName());
            statement.setString(2, rental.getDescription());
            statement.setInt(3, rental.getPrice());
            statement.setString(4, rental.getOtherInformation());
            statement.setString(5, rental.getStateName());
            statement.setInt(6, rental.getMember().getId());
            statement.setInt(7, rental.getId());
            statement.executeUpdate();
        }
    }

    @Override
    public void delete(Rental rental) throws SQLException {
        try(Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM share_it.rental WHERE id = ?");
            statement.setInt(1, rental.getId());
            statement.executeUpdate();
        }
    }
}
