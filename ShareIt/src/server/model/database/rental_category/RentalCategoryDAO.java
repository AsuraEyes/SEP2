package server.model.database.rental_category;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RentalCategoryDAO {
    ArrayList<String> getSelectedCategoriesOnRental(int rentalId) throws SQLException;
}
