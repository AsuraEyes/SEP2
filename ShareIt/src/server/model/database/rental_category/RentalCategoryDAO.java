package server.model.database.rental_category;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * The interface Rental Category Data Access Object.
 */
public interface RentalCategoryDAO {

    /**
     * Gets selected categories on rental.
     *
     * @param rentalId Rental's ID
     * @return returns a list of all selected categories that were chosen during creation of new Rental offer
     * @throws SQLException
     */
    ArrayList<String> getSelectedCategoriesOnRental(int rentalId) throws SQLException;
}
