package server.model.database.rental;

import shared.transferobjects.Member;
import shared.transferobjects.Rental;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RentalDAOImpl implements RentalDAO{
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
    public Rental create(String name, String pictureLink, String description, int price, String otherInformation, String stateName, String username, ArrayList<String> selectedCategories) throws SQLException {
        try(Connection connection = getConnection()){
            File file = null;
            file = new File(pictureLink);
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            username = "bob";



            PreparedStatement statement = connection.prepareStatement("SELECT * FROM share_it.member WHERE username = ?");
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            int memberId = 0;
            if(resultSet.next()){
                memberId =  resultSet.getInt("id");
            }
            else{
                throw new SQLException("No keys generated");
            }


            statement = connection.prepareStatement("INSERT INTO share_it.rental(name, picture_link, description, price, other_information, state_name, member_id) VALUES (?, ?, ?, ?, ?, ?, ?);", PreparedStatement.RETURN_GENERATED_KEYS);
            statement.setString(1, name);
            //statement.setBinaryStream(2, fis, (int)file.length());
            statement.setBinaryStream(2, fis);
            statement.setString(3, description);
            statement.setInt(4, price);
            statement.setString(5, otherInformation);
            statement.setString(6, stateName);
            statement.setInt(7, memberId);
            statement.executeUpdate();

            int rentalId = 0;
            //this gets the generated id of the member
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next()){
                rentalId = generatedKeys.getInt(1);
            }
            else{
                throw new SQLException("No keys generated");
            }

            for (int i = 0; i < selectedCategories.size(); i++) {
                statement = connection.prepareStatement("INSERT INTO share_it.rental_category(rental_id, category_name) VALUES (?, ?);", PreparedStatement.RETURN_GENERATED_KEYS);
                statement.setInt(1, rentalId);
                statement.setString(2, selectedCategories.get(i));
                statement.executeUpdate();
            }
            return new Rental(rentalId, name, pictureLink, description, price, otherInformation, stateName, memberId, selectedCategories);
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

    public List<Rental> readBySearch(String search) throws SQLException
    {
        try (Connection connection = getConnection())
        {
            PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM share_it.rental WHERE name || description  ILIKE ?;");
            statement.setString(1, "%" + search + "%");
            ResultSet resultSet = statement.executeQuery();
            ArrayList<Rental> arrayListToReturn = new ArrayList<>();
            while (resultSet.next())
            {
                int idOfSearchedRental = resultSet.getInt("id");
                Rental rental = new Rental(idOfSearchedRental);
                arrayListToReturn.add(rental);

            }
            //return array list
            System.out.println(search);
            for (Rental rental : arrayListToReturn)
            {
                System.out.println(rental);
            }
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
            File file = new File(rental.getPictureLink());
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            PreparedStatement statement = connection.prepareStatement("UPDATE share_it.rental SET name = ?, picture_link = ?, description = ?, price = ?, other_information = ?, state_name = ?, member_id = ? WHERE id = ?");
            statement.setString(1, rental.getName());
            statement.setBinaryStream(2, fis, (int)file.length());
            statement.setString(3, rental.getDescription());
            statement.setInt(4, rental.getPrice());
            statement.setString(5, rental.getOtherInformation());
            statement.setString(6, rental.getStateName());
            statement.setInt(7, rental.getMemberId());
            statement.setInt(8, rental.getId());
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

    @Override
    public int getNextAvailableId() throws SQLException {
        try(Connection connection = getConnection()){
            PreparedStatement statement = connection.prepareStatement("SELECT nextval(pg_get_serial_sequence('share_it.rental', 'id')) AS available_id;");
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
}
