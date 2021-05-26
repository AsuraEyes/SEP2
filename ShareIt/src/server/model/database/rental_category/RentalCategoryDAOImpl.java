package server.model.database.rental_category;

import java.sql.*;
import java.util.ArrayList;

public class RentalCategoryDAOImpl implements RentalCategoryDAO{
    private static RentalCategoryDAOImpl instance;
    private String password;

    private RentalCategoryDAOImpl()throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
    }

    public static synchronized RentalCategoryDAOImpl getInstance() throws SQLException{
        if(instance == null){
            instance = new RentalCategoryDAOImpl();
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
    public ArrayList<String> getSelectedCategoriesOnRental(int rentalId) throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM share_it.rental_category WHERE rental_id = ?");
            statement.setInt(1, rentalId);
            ResultSet resultSet = statement.executeQuery();
            ArrayList<String> listOfCategories = new ArrayList<>();
            while(resultSet.next()){
                listOfCategories.add(resultSet.getString("category_name"));
            }
            return listOfCategories;
        }
    }
}
