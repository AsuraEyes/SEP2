package client.model.database.member;

import ground_classes.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MemberDAOImpl implements MemberDAO{
    private static MemberDAOImpl instance;

    public MemberDAOImpl()throws SQLException{
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    public static synchronized MemberDAOImpl getInstance() throws SQLException{
        if(instance == null){
            instance = new MemberDAOImpl();
        }
        return instance;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres?currentSchema=share_it", "postgres", "SQLdatabaze");
    }

    @Override
    public Member create(String username, String password, String emailAddress, String otherInformation, String addressStreet, String addressNo, int addressPostalCode, String addressCity) throws SQLException {
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("INSERT INTO share_it.member(username, password, email_address, other_information, address_street, address_no, address_postal_code, address_city_name) VALUES (?, ?, ?, ?, ?, ?, ?, ?);", PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, emailAddress);
            statement.setString(4, otherInformation);
            statement.setString(5, addressStreet);
            statement.setString(6, addressNo);
            statement.setInt(7, addressPostalCode);
            statement.setString(8, addressCity);
            statement.executeUpdate();

            //this gets the generated id of the member
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()){
                return new Member(generatedKeys.getInt(1), username, password, emailAddress, otherInformation, null, null, addressStreet, addressNo, addressPostalCode, addressCity);
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
    public void update(Member member) throws SQLException {
        try(Connection connection  = getConnection()){
            //for updating member information
            //SET all except for primary key
            //WHERE member.get primary key = member.primary key
            //PreparedStatement statement = connection.prepareStatement("UPDATE share_it.member SET ")
        }
    }

    @Override
    public void delete(Member member) throws SQLException {
        //similar as update, delete by id of member I get in the constructor
    }
}
