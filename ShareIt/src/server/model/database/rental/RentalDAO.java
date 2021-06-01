package server.model.database.rental;

import shared.transferobjects.Rental;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The interface of Rating Data Access Object.
 */
public interface RentalDAO {
    /**
     * Creates new rental by putting data provided by user into database
     * @param name               name of the rental input by user while creating new rental offer
     * @param pictureLink        picture storage path
     * @param description        description of the rental input by user while creating new rental offer
     * @param price              price of the rental input by user while creating new rental offer
     * @param otherInformation   other information of the rental input by user while creating new rental offer
     * @param stateName          state of the rental chosen from the list of possible states by user while creating new rental offer
     * @param username           username of the member who owns the rental
     * @param selectedCategories categories of the rental chosen from the list of possible categories by user while creating new rental offer
     * @return returns new object of Rental with data which was provided by user while creating new rental offer
     * @throws SQLException
     */
    Rental create(String name, String pictureLink, String description, int price, String otherInformation, String stateName, String username, ArrayList<String> selectedCategories) throws SQLException;

    /**
     * Checks all the rentals with given value
     * @param search the search
     * @return returns list of rentals where value matches with their name or description
     * @throws SQLException
     */
    List<Rental> readBySearch(String search) throws SQLException;
    /**
     * Checks all the rentals with given values
     * @param search     search is a phrase that was input by user in order to get rentals matched with it
     * @param city       city that member who posted rental should be from
     * @param categories categories that searched for rental should be in
     * @return returns list of rentals where values input matches with rentals values
     * @throws SQLException
     */
    List<Rental> readBySearchAndFilter(String search, String city, ArrayList<String> categories) throws SQLException;
    /**
     * Updates rental data based on data input provided by user
     *
     * @param name               name of the rental input by user while creating new rental offer
     * @param pictureLink        picture storage path
     * @param description        description of the rental input by user while creating new rental offer
     * @param price              price of the rental input by user while creating new rental offer
     * @param otherInformation   other information of the rental input by user while creating new rental offer
     * @param stateName          state of the rental chosen from the list of possible states by user while creating new rental offer
     * @param rentalId           Rental's ID
     * @param selectedCategories categories of the rental chosen from the list of possible categories by user while creating new rental offer
     * @throws SQLException
     */
    void update(String name, String pictureLink, String description, int price, String otherInformation, String stateName, int rentalId, ArrayList<String> selectedCategories) throws SQLException;
    /**
     * Deletes rental from the database
     * @param rental rental object that will be deleted
     * @return returns true if rental gets deleted from the database
     * @throws SQLException
     */
    boolean delete(Rental rental) throws SQLException;
    /**
     * Gets next available ID.
     * @return returns the next available ID
     * @throws SQLException
     */
    int getNextAvailableId() throws SQLException;
    /**
     * Gets all the possible rentals
     * @return returns a list of all rentals that are stored in the database
     * @throws SQLException
     */
    List<Rental> readRentals() throws SQLException;

    /**
     * Gets last rental.
     * @return returns the last rental
     * @throws SQLException
     */
    Rental getLastRental() throws SQLException;
    /**
     * Gets all rentals connected to the username
     * @param username username that all rentals will be connected with
     * @return returns a list of rentals that are matching with members username
     */
    ArrayList<Integer> getRentalsOfMemberList(String username);

}
