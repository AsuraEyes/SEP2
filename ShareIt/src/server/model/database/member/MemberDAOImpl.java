package server.model.database.member;

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
                return new Member(generatedKeys.getInt(1), username, password, emailAddress, phoneNumber, otherInformation, addressStreet, addressNo, addressPostalCode, addressCity);
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
                return true;
            }
            return false;
        }
    }

    @Override
    public int getNextAvailableId() throws SQLException {
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT nextval(pg_get_serial_sequence('share_it.member', 'id')) AS available_id;");
            ResultSet resultSet = statement.executeQuery();
            int nextAvailableId = 0;
            if(resultSet.next()){
                nextAvailableId =  resultSet.getInt("available_id");
            }
            else{
                throw new SQLException("No keys generated");
            }
            return nextAvailableId;
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
